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
public class MemTableInterface {
	private static ArrayList<String> nameList = new ArrayList<>(); 	//0sheet 0 Row : (1~x)column(Cell)
	private static ArrayList<String> idList = new ArrayList<>();		//0sheet 1 Row : (1~x)column(Cell)
	private static ArrayList<String> pwList = new ArrayList<>();		//0sheet 2 Row
	private static ArrayList<String> mailList = new ArrayList<>(5);		//0sheet 3 Row
	private static ArrayList<String> tierList = new ArrayList<>(5);		//
	private static ArrayList<String> errList = new ArrayList<>(5);

	private String path = "../banksystem/data/회원정보관리.xlsx";
	//	XSSFWorkbook xwb = null;
	
	public MemTableInterface() {
		
	}
	
	public MemTableInterface(int State) {
		try {
			excelLoad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void excelLoad() {
		FileInputStream test = null;
		try {
			test = new FileInputStream(path);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		XSSFWorkbook xwb = null;
		try {
			xwb = new XSSFWorkbook(test);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		XSSFSheet a = xwb.getSheet("회원정보");
		int count =0;
		try {
			
			for(Row row:a) {
				for(Cell c:row) {
					if(c.getColumnIndex()==0){
						nameList.add(count,c.getStringCellValue());
					}
					else if(c.getColumnIndex()==1){
						idList.add(count,c.getStringCellValue());
					}
					else if(c.getColumnIndex()==2){
						pwList.add(count,c.getStringCellValue());
					}
					else if(c.getColumnIndex()==3){
						mailList.add(count,c.getStringCellValue());
					}
					else if(c.getColumnIndex()==4){
						errList.add(count,c.getStringCellValue());
					}
					else if(c.getColumnIndex()==5){
						tierList.add(count,c.getStringCellValue());
					}
					
					
				}
				count++;
			}
//			System.out.println(nameList.toString());
//			System.out.println(idList.toString());
//			System.out.println(pwList.toString());
//			System.out.println(mailList.toString());
//			System.out.println(errList.toString());
			try {
				xwb.close();
				test.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
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
			xsheet.getRow(idList.indexOf(id)).createCell(4).setCellValue(error);
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
	
	//Member를 엑셀에 업데이트 하면서 리스트에도 바로 추가해줘서 한번더 읽어오는 일 없도록 함.
	void excelUpdate(String name,String id,String pw,String mail) {
		try{
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet(xwb.getSheetName(0));
//			int loc = xsheet.getRow(0).getLastCellNum(); //컬럼 최대 길이 구하기
			xsheet.createRow(xsheet.getLastRowNum()+1);
			
			for(int i=0;i<6;i++) {
				if(i==0)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue(name);				
				if(i==1)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue(id);				
				if(i==2)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue(pw);				
				if(i==3)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue(mail);
				if(i==4)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue("0");
				if(i==5)xsheet.getRow(xsheet.getLastRowNum()).createCell(i).setCellValue("Normal");
							
			}
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
			
		}catch(IOException e) {System.out.println(1);}
		catch(IndexOutOfBoundsException e) {}
		
		nameList.add(name);
		idList.add(id);
		pwList.add(pw);
		mailList.add(mail);
		errList.add("0");
	}
	//tier 업데이트
	void tierUpdate(String id, String tier) {
		
		try{
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet(xwb.getSheetName(0));
			xsheet.getRow(idList.indexOf(id)).createCell(5).setCellValue(tier);
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);			
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
		}catch(IOException e) {System.out.println(1);}
		catch(IndexOutOfBoundsException e) {}


	}
//	public excelupdate(String path, String SheetName,ArrayList A,ArrayList B, String id)
	

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
	public ArrayList<String> getTierList() {
		return tierList;
	}
	public void setTierList(ArrayList<String> tierList) {
		this.tierList = tierList;
	}
//	Writable
}
