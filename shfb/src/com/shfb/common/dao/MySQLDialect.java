package com.shfb.common.dao;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class MySQLDialect extends MySQL5Dialect {
	
	@SuppressWarnings("deprecation")
	public MySQLDialect(){
		super();
		registerFunction( "regexp", new SQLFunctionTemplate(Hibernate.BOOLEAN, "?1 REGEXP ?2") );
	}
}
