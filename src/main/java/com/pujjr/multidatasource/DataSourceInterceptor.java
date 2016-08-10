package com.pujjr.multidatasource;

import org.springframework.stereotype.Component;

public class DataSourceInterceptor 
{
	public void beforeUseSqlserver()
	{
		DataSourceManager.set(DataSources.SQLSERVER);
	}
	
	public void afterUserSqlServer()
	{
		DataSourceManager.reset();
	}
}
