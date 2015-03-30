package com.shfb.common.dao;

import org.hibernate.Hibernate;
import org.hibernate.dialect.Oracle9iDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class OracleDialect extends Oracle9iDialect {
	@SuppressWarnings("deprecation")
	public OracleDialect(){
		super();
		registerFunction( "regexp", new SQLFunctionTemplate(Hibernate.BOOLEAN,
		          "(case when (regexp_like(?1, ?2)) then 1 else 0 end)") );
	}
}
