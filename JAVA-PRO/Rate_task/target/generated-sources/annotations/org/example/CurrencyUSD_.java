package org.example;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CurrencyUSD.class)
public abstract class CurrencyUSD_ {

	public static volatile SingularAttribute<CurrencyUSD, Double> rate;
	public static volatile SingularAttribute<CurrencyUSD, Integer> id;
	public static volatile SingularAttribute<CurrencyUSD, Date> exchangedate;

	public static final String RATE = "rate";
	public static final String ID = "id";
	public static final String EXCHANGEDATE = "exchangedate";

}

