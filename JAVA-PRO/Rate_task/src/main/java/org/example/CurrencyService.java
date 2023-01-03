package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletContext;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

public class CurrencyService {
    private static final CurrencyService currencyService = new CurrencyService();
    private final String REQUEST_PATH = "https://bank.gov.ua/NBU_Exchange/exchange_site?start=20220101&end=20221212&valcode=usd&sort=exchangedate&order=desc&json";
    private final Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();

    public static CurrencyService getInstance() {
        return currencyService;
    }

    public List<CurrencyRate> getRates() throws IOException {
        URL url = new URL(REQUEST_PATH);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try (InputStream is = http.getInputStream()) {
            byte[] buf = is.readAllBytes();
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            Type type = new TypeToken<List<CurrencyRate>>() {
            }.getType();
            return gson.fromJson(strBuf, type);
        }
    }

    public void saveToDB(List<CurrencyRate> rates, ServletContext context) {
        EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("EntityManagerFactory");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            for (CurrencyRate cr : rates) {
                CurrencyUSD currencyUSD = new CurrencyUSD(cr.getExchangedate(), cr.getRate());
                em.persist(currencyUSD);
            }
            tr.commit();
        } catch (Exception var5) {
            System.out.println(var5.getMessage());
            tr.rollback();
        }
    }

    public CurrencyUSD selectByDate(ServletContext context) throws ParseException {
        EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("EntityManagerFactory");
        EntityManager em = emf.createEntityManager();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = formatter.parse("02.12.2022");
        TypedQuery<CurrencyUSD> query = em.createQuery("SELECT NEW org.example.CurrencyUSD(c.exchangedate,c.rate) FROM CurrencyUSD c WHERE c.exchangedate = :dateParam", CurrencyUSD.class);
        query.setParameter("dateParam", date, TemporalType.DATE);
        return query.getSingleResult();
    }

    public double getAverageRateForPerid(ServletContext context, String dateFromStr, String dateToStr) throws ParseException {
        EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("EntityManagerFactory");
        EntityManager em = emf.createEntityManager();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = formatter.parse(dateFromStr);
        Date dateTo = formatter.parse(dateToStr);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CurrencyUSD> criteria = builder.createQuery(CurrencyUSD.class);
        Root<CurrencyUSD> root = criteria.from(CurrencyUSD.class);
        criteria.select(root).where(builder.between(root.get(CurrencyUSD_.exchangedate), dateFrom, dateTo));
        List<CurrencyUSD> resultList = em.createQuery(criteria).getResultList();
        OptionalDouble result = resultList.stream().mapToDouble(CurrencyUSD::getRate).average();
        if (result.isEmpty()) {
            System.out.println("Something went wrong");
            return 0;
        }
        return result.getAsDouble();
    }
}
