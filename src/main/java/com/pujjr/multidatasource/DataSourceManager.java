package com.pujjr.multidatasource;

public class DataSourceManager {
	private static final ThreadLocal<DataSources> dataSources = new ThreadLocal<DataSources>() {
		@Override
		protected DataSources initialValue() {
			// TODO Auto-generated method stub
			return DataSources.MYSQL;
		}
	};

	public static DataSources get() {
		return dataSources.get();
	}

	public static void set(DataSources dataSourceType) {
		dataSources.set(dataSourceType);
	}

	public static void reset() {
		dataSources.set(DataSources.MYSQL);
	}
}
