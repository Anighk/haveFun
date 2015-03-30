package com.shfb.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;


public class ExcelUtil {
	
	private static String rPath;

	/**根据文件打开poi的Excel操作类*/
	public static Workbook openWorkbook(File file){
		String filename=file.getName();
		InputStream in=null;
		Workbook wb = null;
		try {
			in = new FileInputStream(file);
			if(filename.endsWith(".xlsx")){
				wb = new XSSFWorkbook(in);//Excel 2007
			} else {
				wb = new HSSFWorkbook(in);//Excel 2003
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					in = null;
					e.printStackTrace();
				}
			}
		}
		return wb;
	}
	
	/**得到一行内容的字符串数组*/
	public static String[] getFirstRowArray(Row row){
		List<String> list = new ArrayList<String>();
		int totalCells = row.getPhysicalNumberOfCells();
		Cell cell = null;
		for(int c = 0; c < totalCells; c++)
		{
			cell = row.getCell(c);
			String cellValue = "";
			if(null != cell){
					cellValue = cell.getStringCellValue();
					list.add(ExcelConfig.getKeyValue(cellValue));
			}
		}
		final int size=list.size();
		String[] array=(String[])list.toArray(new String[size]);
		return array;
	}


}
