package cn.tax.core.tax.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.tax.nswf.entity.User;

public class ExcelUtil {
	
	public static byte[] excelUtil(List<User> userList){
        //1������������
		HSSFWorkbook workbook = new HSSFWorkbook();
		//2�������ϲ���Ԫ�����
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);
		//1.2��ͷ������ʽ
		HSSFCellStyle style1 = createCellStyle(workbook,(short)16);
		//1.3���б�����ʽ
		HSSFCellStyle style2 = createCellStyle(workbook,(short)13);
		//2������������
		HSSFSheet sheet = workbook.createSheet("�û��б�");
		//2.1�����غϲ���Ԫ�����
		sheet.addMergedRegion(cellRangeAddress);
		//����Ĭ���п�
		sheet.setDefaultColumnWidth(25);
		
		//3��������
		//3.1������ͷ�����У���������ͷ����
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell1 = row1.createCell(0);
		//���ص�Ԫ����ʽ
		cell1.setCellStyle(style1);
		cell1.setCellValue("�û��б�");
		
		//3.2�������б����У����������б���
		HSSFRow row2 = sheet.createRow(1);
		String[] titles = {"�û���","�ʺ�", "��������", "�Ա�", "��������"};
		HSSFCell cell  = null;
		for (int i = 0; i < titles.length; i++) {
			cell = row2.createCell(i);
			cell.setCellStyle(style2);
			cell.setCellValue(titles[i]);
		}
		
		//4��������Ԫ�񣻽��û��б�д��excel
		if (userList!=null) {
			int num = userList.size();
			for (int i = 0; i < num; i++) {
				User sUser = userList.get(i);
				HSSFRow row = sheet.createRow(i+2);
				
				HSSFCell cella = row.createCell(0);
				cella.setCellValue(sUser.getName());
				HSSFCell cellb = row.createCell(1);
				cellb.setCellValue(sUser.getAccount());
				HSSFCell cellc = row.createCell(2);
				cellc.setCellValue(sUser.getDept());
				HSSFCell celld = row.createCell(3);
				celld.setCellValue(sUser.getGender()?"��":"Ů");
				HSSFCell celle = row.createCell(4);
				celle.setCellValue(sUser.getMobile());
			}
		}
		return workbook.getBytes();
	}
	/**
	 * ������Ԫ����ʽ
	 * @param workbook ������
	 * @param fontSize �����С
	 * @return ��Ԫ����ʽ
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//ˮƽ����
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//��ֱ����
		//��������
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//�Ӵ�����
		font.setFontHeightInPoints(fontSize);
		//��������
		style.setFont(font);
		return style;
	}
}
