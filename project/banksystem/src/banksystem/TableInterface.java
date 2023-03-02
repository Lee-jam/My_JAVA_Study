package banksystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/* 엑셀(DB 대신 사용)의 데이터를 ㄴ
 * 
 */
public class TableInterface {
	private ArrayList<String> nameList = new ArrayList<>(); 	//0sheet 0 Row : (1~x)column(Cell)
	private ArrayList<String> idList = new ArrayList<>();		//0sheet 1 Row : (1~x)column(Cell)
	private ArrayList<String> pwList = new ArrayList<>();		//0sheet 2 Row
	private ArrayList<String> mailList = new ArrayList<>();		//0sheet 3 Row
	private ArrayList<String> tierList = new ArrayList<>();		//
	private ArrayList<String> errList = new ArrayList<>();
	private ArrayList<String> accountList = new ArrayList<>();	//
	private String path = "D:\\My_JAVA_Study\\My_JAVA_Study\\project\\banksystem\\data\\회원정보관리.xlsx";
	//	XSSFWorkbook xwb = null;
	
	
	public TableInterface() {
		try {
			excelLoad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void excelLoad() throws Exception{
		FileInputStream test = new FileInputStream(path);
		XSSFWorkbook xwb = new XSSFWorkbook(test);
		XSSFSheet a = xwb.getSheetAt(0);
		try {
			
			for(Row row:a) {
				for(Cell c:row) {
					if(c.getColumnIndex()==0){
						nameList.add(c.getStringCellValue());
					}
					else if(c.getColumnIndex()==1){
						idList.add(c.getStringCellValue());
					}
					else if(c.getColumnIndex()==2){
						pwList.add(c.getStringCellValue());
					}
					else if(c.getColumnIndex()==3){
						mailList.add(c.getStringCellValue());
					}					
					else if(c.getColumnIndex()==4){
						tierList.add(c.getStringCellValue());
					}
					else if(c.getColumnIndex()==5){
						errList.add(c.getStringCellValue());
					}
					
				}
			}
			xwb.close();
			test.close();
		}catch(NullPointerException e) {
			System.out.println("out");
			}
		
	}
	
	void excelUpdate() throws Exception {
		XSSFWorkbook xwb = new XSSFWorkbook();
		//내부메소드 회원가입으로 추가된 회원 정보 업데이트
//		FileOutputStream fos = new FileOutputStream(path);
		xwb.close();
	}
	
	//error 업데이트
	void excelUpdate(String id,String error){
		try{
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet(xwb.getSheetName(0));
			xsheet.getRow(idList.indexOf(id)).createCell(5).setCellValue(error);
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);			
			errList.set(idList.indexOf(id), error); //아이디 값과 같은 인덱스 위치에 있는 error 값을 업데이트
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
		}catch(IOException e) {System.out.println(1);}
		catch(IndexOutOfBoundsException e) {}

	}
	void excelUpdate(String name,String id,String pw,String mail) {
		try{
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet(xwb.getSheetName(0));
//			int loc = xsheet.getRow(0).getLastCellNum(); //컬럼 최대 길이 구하기
			xsheet.createRow(xsheet.getLastRowNum()+1);
			
			for(int i=0;i<4;i++) {
				if(i==0)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue(name);				
				if(i==1)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue(id);				
				if(i==2)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue(pw);				
				if(i==3)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue(mail);				
							
			}
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);
			
			nameList.add(name);
			idList.add(id);
			pwList.add(pw);
			mailList.add(mail);
			
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
		}catch(IOException e) {System.out.println(1);}
		catch(IndexOutOfBoundsException e) {}
	}

	public ArrayList<String> getNameList() {
		return nameList;
	}

	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}

	public ArrayList<String> getIdList() {
		return idList;
	}

	public void setIdList(ArrayList<String> idList) {
		this.idList = idList;
	}

	public ArrayList<String> getPwList() {
		return pwList;
	}

	public void setPwList(ArrayList<String> pwList) {
		this.pwList = pwList;
	}

	public ArrayList<String> getMailList() {
		return mailList;
	}

	public void setMailList(ArrayList<String> mailList) {
		this.mailList = mailList;
	}
	public ArrayList<String> getErrList() {
		return errList;
	}
	public void setErrList(ArrayList<String> errList) {
		this.errList = errList;
	}
	
//	Writable
}
