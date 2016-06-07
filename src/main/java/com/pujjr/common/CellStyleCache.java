package com.pujjr.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class CellStyleCache
{
	
	private Map<String,CellStyle> styleCache;
	
	private Workbook wb;
	
	public CellStyleCache(Workbook wb)
	{
		styleCache=new HashMap<String,CellStyle>();
		this.wb=wb;
	}
	private String generatorKey(short align,short color,boolean isBold,boolean isCurrent)
	{
		return "ALIGN"+String.valueOf(align)+":"+"COLOR"+String.valueOf(color)+":"+"FONT"+String.valueOf(isBold)+":"+"DATAFORMAT"+String.valueOf(isCurrent);
	}
	public CellStyle getCellStyle(short align,short color,boolean isBold,boolean isCurrent)
	{
		String styleKey=generatorKey(align,color,isBold,isCurrent);
		if(styleCache.containsKey(styleKey))
		{
			return styleCache.get(styleKey);
		}
		else
		{
			Font font=wb.createFont();
			font.setFontHeightInPoints((short)10);
			font.setBoldweight(isBold==true?Font.BOLDWEIGHT_BOLD:Font.BOLDWEIGHT_NORMAL);
			
			CellStyle cellStyle=wb.createCellStyle();
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setAlignment(align);
			cellStyle.setFillBackgroundColor(color);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellStyle.setDataFormat(isCurrent==true?(short)4:(short)0);
			styleCache.put(styleKey, cellStyle);
			
			return cellStyle;
		}
		
	}
}