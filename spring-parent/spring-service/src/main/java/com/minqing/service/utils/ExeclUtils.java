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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExeclUtils {

	private static Logger logger = LoggerFactory.getLogger(ExeclUtils.class);
	
	/**
	 * @desc 将数据分多个sheet
	 * @param dateList
	 *            要处理的数据
	 * @param headers
	 *            表格的头信息
	 * @param sheetName
	 *            sheet名称
	 */
	public static Workbook manageSheet(int execlDateNum,List<Map<Integer, Object>> dateList, String[] headers, String sheetName) {
		logger.info("每一个sheet的数据大小:dataNum:{}", execlDateNum);
		if (dateList == null || dateList.size() == 0) {
			return null;
		}
		// 需要处理的总数据大小
		int totalDate = dateList.size();
		// sheet的总数量
		int sheetToatl = (totalDate % execlDateNum == 0) ? (totalDate / execlDateNum) : (totalDate / execlDateNum) + 1;
		int start =0;
		int end = execlDateNum;
		List<Map<Integer, Object>> map =null;
		Workbook wb = new HSSFWorkbook();
		for(int i=0;i<sheetToatl;i++) {
			map=dateList.subList(start, end);
			wb=fillExeclDate(map, headers, sheetName+"_"+(i+1),wb);
			start+=execlDateNum;
			end+=execlDateNum;
			if(end>totalDate) {
				end=totalDate;
			}
		}
		return wb;
	}

	/**
	 * 
	 * @param dateList：execl数据信息
	 * @param headers：exexl标题
	 * @param sheetName：sheet名称
	 */
	public static Workbook fillExeclDate(List<Map<Integer, Object>> dateList, String[] headers, String sheetName,Workbook wb) {
		Sheet sheet = wb.createSheet(sheetName);// 创建excel sheet名称
		Row headerRow = sheet.createRow(0);// 创建第一行数据，即excel的表头
		for (int i = 0; i < headers.length; i++) {
			headerRow.createCell(i).setCellValue(headers[i]);
		}
		if (dateList == null || dateList.size() == 0) {
			return wb;
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
		for (int i = 0; i < headers.length; i++) {
			sheet.autoSizeColumn(i);
		}
		return wb;
	}

	/**
	 * @desc execl导出
	 * @param wb
	 * @param response
	 * @param sheetName
	 */
	public static void exportExecl(Workbook wb, HttpServletResponse response, String sheetName) {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-disposition",
					"attachment;filename=" + new String(sheetName.getBytes("utf-8"), "iso-8859-1") + ".xls");// 默认Excel名称
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
