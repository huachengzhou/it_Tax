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
//		-------------����excel
//		1������������
		XSSFWorkbook workbook = new XSSFWorkbook();
//		2������������
		XSSFSheet sheet = workbook.createSheet("hello");
//		3������ȡ��
		XSSFRow row = sheet.createRow(4);
//		4��������Ԫ��
		XSSFCell cell = row.createCell(3);
		cell.setCellValue("hello world!");
		String url ="D:\\GCC\\����.xls";
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(url));
		workbook.write(out);
		workbook.close();
		out.flush();
		out.close();
	}
	@Test
	public void createExcel_07()throws Exception{
//		-------------����excel
//		1������������
		HSSFWorkbook workbook = new HSSFWorkbook();
//		2������������
		HSSFSheet sheet = workbook.createSheet("hello");
//		3������ȡ��
		HSSFRow row = sheet.createRow(4);
//		4��������Ԫ��
		HSSFCell cell = row.createCell(3);
		cell.setCellValue("hello world!");
		String url ="D:\\GCC\\����.xlsx";
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(url));
		workbook.write(out);
		workbook.close();
		out.flush();
		out.close();
	}
	
	@Test
	public void readingExcel_03()throws Exception{
		String url ="D:\\GCC\\����.xls";
//		-------------����excel
//		1����ȡ������
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(url));
//		2����ȡ������
		XSSFSheet sheet = workbook.getSheetAt(0);
//		3����ȡ��
		XSSFRow row = sheet.getRow(4);
//		4����ȡ��Ԫ��
		XSSFCell cell = row.getCell(3);
		System.out.println("content:"+cell.getStringCellValue());
	}
	
	@Test
	public void readingExcel_07()throws Exception{
		String url ="D:\\GCC\\����.xlsx";
//		-------------����excel
//		1����ȡ������
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(url));
//		2����ȡ������
		HSSFSheet sheet = workbook.getSheetAt(0);
//		3����ȡ��
		HSSFRow row = sheet.getRow(4);
//		4����ȡ��Ԫ��
		HSSFCell cell = row.getCell(3);
		System.out.println("content:"+cell.getStringCellValue());
	}
	
	@Test
	public void readingExcel_07And_03()throws Exception{
		String fileName = "D:\\GCC\\����.xlsx";
		if(fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){//�ж��Ƿ�excel�ĵ�
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
            FileInputStream inputStream = new FileInputStream(fileName);
			//1����ȡ������
			Workbook workbook = is03Excel ?new XSSFWorkbook(inputStream):new HSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(4);
			Cell cell = row.getCell(3);
			System.out.println("data string:"+cell.getStringCellValue());
		}
	}
	
	@Test
	public void testExcelStyle()throws Exception{
		//1:����������
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1�������ϲ���Ԫ�����;�ϲ���3�еĵ�3�е���5��
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 2, 4);
		//1.2��������Ԫ����ʽ
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//ˮƽ����
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//��ֱ����
		//1.3����������
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 16);//���������С
		style.setFont(font);
		//��Ԫ�񱳾�
		//���ñ������ģʽ
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//ģʽ
		//������䱳��ɫ
		style.setFillBackgroundColor(HSSFColor.RED.index);
		//�������ǰ��ɫ
		style.setFillForegroundColor(HSSFColor.GREEN.index);
		//2:����������
		HSSFSheet sheet = workbook.createSheet("xx");
		//���غϲ��ĵ�Ԫ�����
		sheet.addMergedRegion(cellRangeAddress);
		//3:����������
		HSSFRow row = sheet.createRow(2);
		
		//4:������
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("soday");
		cell.setCellStyle(style);
		
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\GCC\\��ʽ.xlsx"));
		workbook.write(out);
		workbook.close();
		out.flush();
		out.close();
	}
}
