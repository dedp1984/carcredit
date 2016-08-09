package com.pujjr.multidatasource;

import org.springframework.stereotype.Component;

public class DataSourceInterceptor 
{
	public void useSqlserver()
	{
		DataSourceManager.set(DataSources.SQLSERVER);
	}
}
