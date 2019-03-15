package com.minqing.service.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExeclUtils {

	/**
	 * 
	 * @param dateList：execl数据信息
	 * @param headers：exexl标题
	 * @param sheetName：sheet名称
	 */
	@SuppressWarnings("resource")
	public static void fillExeclDate(List<Map<Integer, Object>> dateList, String[] headers, String sheetName,HttpServletResponse response) {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(sheetName);// 创建excel sheet名称
		Row headerRow = sheet.createRow(0);// 创建第一行数据，即excel的表头
		for (int i = 0; i < headers.length; i++) {
			headerRow.createCell(i).setCellValue(headers[i]);
		}
		// 从第二行开始塞入数据
		int rowIndex = 1;
		Row row;
		Object obj;
		for (Map<Integer, Object> map : dateList) {
			try {
				row = sheet.createRow(rowIndex++);
				// 遍历表头行的每个key-->取到key对应的value
				for (int i = 0; i < headers.length; i++) {
					obj = map.get(i);
					if (obj == null) {
						row.createCell(i).setCellValue("");
					} else {
						row.createCell(i).setCellValue(String.valueOf(obj));
					}
				}
			} catch (Exception e) {
				e.getMessage();
			}
		}
		sheet.setDefaultRowHeight((short) (16.5 * 20));
		// 列宽自适应
		for (int i = 0; i <headers.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
	    OutputStream os;
		try {
			os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-disposition", "attachment;filename="+new String(sheetName.getBytes("utf-8"),"iso-8859-1")+".xls");//默认Excel名称
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
