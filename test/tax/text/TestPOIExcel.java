package tax.text;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOIExcel {
	@Test
	public void createExcel_03()throws Exception{
//		-------------操作excel
//		1、创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
//		2、创建工作表
		XSSFSheet sheet = workbook.createSheet("hello");
//		3、创建取行
		XSSFRow row = sheet.createRow(4);
//		4、创建单元格
		XSSFCell cell = row.createCell(3);
		cell.setCellValue("hello world!");
		String url ="D:\\GCC\\测试.xls";
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(url));
		workbook.write(out);
		workbook.close();
		out.flush();
		out.close();
	}
	@Test
	public void createExcel_07()throws Exception{
//		-------------操作excel
//		1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
//		2、创建工作表
		HSSFSheet sheet = workbook.createSheet("hello");
//		3、创建取行
		HSSFRow row = sheet.createRow(4);
//		4、创建单元格
		HSSFCell cell = row.createCell(3);
		cell.setCellValue("hello world!");
		String url ="D:\\GCC\\测试.xlsx";
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(url));
		workbook.write(out);
		workbook.close();
		out.flush();
		out.close();
	}
	
	@Test
	public void readingExcel_03()throws Exception{
		String url ="D:\\GCC\\测试.xls";
//		-------------操作excel
//		1、读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(url));
//		2、读取工作表
		XSSFSheet sheet = workbook.getSheetAt(0);
//		3、读取行
		XSSFRow row = sheet.getRow(4);
//		4、读取单元格
		XSSFCell cell = row.getCell(3);
		System.out.println("content:"+cell.getStringCellValue());
	}
	
	@Test
	public void readingExcel_07()throws Exception{
		String url ="D:\\GCC\\测试.xlsx";
//		-------------操作excel
//		1、读取工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(url));
//		2、读取工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
//		3、读取行
		HSSFRow row = sheet.getRow(4);
//		4、读取单元格
		HSSFCell cell = row.getCell(3);
		System.out.println("content:"+cell.getStringCellValue());
	}
	
	@Test
	public void readingExcel_07And_03()throws Exception{
		String fileName = "D:\\GCC\\测试.xlsx";
		if(fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){//判断是否excel文档
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
            FileInputStream inputStream = new FileInputStream(fileName);
			//1、读取工作簿
			Workbook workbook = is03Excel ?new XSSFWorkbook(inputStream):new HSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(4);
			Cell cell = row.getCell(3);
			System.out.println("data string:"+cell.getStringCellValue());
		}
	}
	
	@Test
	public void testExcelStyle()throws Exception{
		//1:创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1、创建合并单元格对象;合并第3行的第3列到第5列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 2, 4);
		//1.2、创建单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//1.3、创建字体
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 16);//设置字体大小
		style.setFont(font);
		//单元格背景
		//设置背景填充模式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//模式
		//设置填充背景色
		style.setFillBackgroundColor(HSSFColor.RED.index);
		//设置填充前景色
		style.setFillForegroundColor(HSSFColor.GREEN.index);
		//2:创建工作表
		HSSFSheet sheet = workbook.createSheet("xx");
		//加载合并的单元格对象
		sheet.addMergedRegion(cellRangeAddress);
		//3:创建工作行
		HSSFRow row = sheet.createRow(2);
		
		//4:创建列
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("soday");
		cell.setCellStyle(style);
		
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\GCC\\样式.xlsx"));
		workbook.write(out);
		workbook.close();
		out.flush();
		out.close();
	}
}
