package banksystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelupload {
	void excelUpdate(String path, String sheetName, int index, int cellNum, String cellValue) {
		try{
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet(sheetName);
			xsheet.getRow(index).createCell(cellNum).setCellValue(cellValue);
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);			
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
		}catch(IOException e) {System.out.println(1);}
		catch(IndexOutOfBoundsException e) {}
	}
	
	void exceldelete(String path, String sheetName, int index) {
		try{
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet(sheetName);
			xsheet.removeRow(xsheet.getRow(index));
			
			for (int i = 1; i <= xsheet.getLastRowNum(); i++) {
                XSSFRow row = xsheet.getRow(i);

                if (row == null) {
                    xsheet.shiftRows(i+1, xsheet.getLastRowNum(), -1);
                    i--;
                }
            }
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);			
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
		}catch(IOException e) {System.out.println(1);}
		catch(IndexOutOfBoundsException e) {}
	}
}
