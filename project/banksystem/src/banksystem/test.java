package banksystem;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class test {
	public static void main(String[] args) throws Exception{
		String path = "D:\\My_JAVA_Study\\My_JAVA_Study\\project\\banksystem\\data\\회원정보관리.xlsx"; 
		FileInputStream test = new FileInputStream(path);
		String[] str = {"Account_Num","Money","Account_pw","Limit_money","Limit_dod"};
		XSSFSheet a=null;
		XSSFWorkbook hs = new XSSFWorkbook(test);
		a = hs.getSheet(hs.getSheetName(0));
		a.getRow(1).createCell(5).setCellValue("6");
		FileOutputStream fos = new FileOutputStream(path);
		hs.write(fos);
		a.
		System.out.println(a.getRow(a.dele);
//		a.removeRowBreak(2);
//		a.
		
		System.out.println(Integer.toString(1).equals("1"));
		try {
			XSSFWorkbook xwb = new XSSFWorkbook(test);
			XSSFSheet xsheet = xwb.getSheet("회원정보");
			System.out.println(xsheet);
	 //아이디 값과 같은 인덱스 위치에 있는 error 값을 업데이트
			Row r = xsheet.createRow(1);
			Cell c = r.createCell(5);
			System.out.println(c.getStringCellValue());
			c.setCellValue("5");
			xwb.write(fos);			
		}catch(Exception e) {}
		
		
		
		
		
		
		
//		
//		try {
//			a = hs.getSheet("hong1234@naver.com");			
//		}catch(Exception e) {
//			a = hs.createSheet("2hong1234@naver.com");
//		}
//		FileOutputStream fos = new FileOutputStream(path);
//		
//		Row r = a.createRow(0);
//		Cell c = null;
//		int i = 0;
//		for(String s : str) {
//			c = r.createCell(i);
//			c.setCellValue(s);
//			i++;
//		}
//		hs.write(fos);
//		System.out.println(c.getCellType());
//		
//		int i = 0;
//		System.out.println(a);
//		Row r = a.getRow(0);
//		Cell c = r.getCell(0);
//		System.out.println(c);
		

		
		
		
		
		

		
	}
}
