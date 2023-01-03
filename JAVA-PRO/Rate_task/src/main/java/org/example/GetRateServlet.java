package org.example;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class GetRateServlet extends HttpServlet {

    private final CurrencyService currencyService = CurrencyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<CurrencyRate> cr = currencyService.getRates();
        ServletContext context = req.getServletContext();
        currencyService.saveToDB(cr, context);
        try {
            CurrencyUSD rateByCertainDate = (currencyService.selectByDate(context));
            System.out.println("Exchange rate on the selected date is " + rateByCertainDate.getRate());
            double averageRate = currencyService.getAverageRateForPerid(context, "18.02.2022", "21.02.2022");
            System.out.println("Average rate is " + averageRate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

