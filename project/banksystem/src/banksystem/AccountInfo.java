package banksystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AccountInfo extends MemTableInterface{
	private MemTableInterface tif = new MemTableInterface();
	private String[] accountMain = {"Account_id","Account_Num","Money","Account_pw","Limit_money","Limit_dod"};
	String path = "D:\\My_JAVA_Study\\My_JAVA_Study\\project\\banksystem\\data\\계좌정보관리.xlsx";
	
	//계좌 관련 멤버변수
	String account_num="";
	long convert;
	String money;
	String limit_money;
	String limit_dod;
	String account_pw;
	//엑셀 관련 멤버변수
//	XSSFWorkbook xwb;
//	FileOutputStream fos;
	
	Scanner scan = new Scanner(System.in);
	
	//메서드 사용하기 위한 생성자
		public AccountInfo() {}
	
	//계좌생성 생성자
	public AccountInfo(String id) {
		System.out.println("=======계좌를 생성합니다.=======");
		createAccSpace(id);
		new AccountTablelist();
	}
	
	//계좌 시트 생성
	void createAccSpace(String id) {
		XSSFSheet accsheet;
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			accsheet =  xwb.getSheet("계좌정보");
			/*시트 생성할 때 다시 만들기
//			if(accsheet==null) {
//				System.out.println("process1");
//				accsheet = xwb.createSheet("계좌정보");
//				Row cr = accsheet.createRow(0);
//				int index=0;
//				for(String str : accountMain) {
//					cr.createCell(index).setCellValue(str);
//					index++;
//				}
//				fos = new FileOutputStream(path);
//				xwb.write(fos);
//				fos.close();
//				xwb.close();
//			}
 */
			xwb.close();
			fis.close();
		}
		catch(NullPointerException e) {}
		catch(IOException e) {}
		finally {
			createAccount(id);
		}
	}
	
	//계좌번호 랜덤생성 000000 00 000000
	void createAccount(String id) {
		for(int i = 0; i<14;i++) {
			convert = new Random().nextInt(9);
			String temp = Long.toString(convert);
			account_num += temp;
		}
		//account_num = Long.parseLong(convert); //excel에 int형 타입을 적는 메서드가 없음.
		createAccountPw(id);
	}
	
	//계좌 비밀번호 설정 Scanner
	void createAccountPw(String id) {
		System.out.println("사용하실 계좌 비밀번호를 입력해주세요.");
		long acc_pw = scan.nextLong();
		System.out.println("다시 한번 계좌 비밀번호를 입력해주세요.");
		long pw_check = scan.nextLong();
		
		if(acc_pw==pw_check) {
			//일일최대한도 = 1,000,000
			//현재일일한도 = 0
			//계좌 초기 금액 입금 Scanner
			account_pw = Long.toString(acc_pw);
			limit_money = Long.toString(1000000);
			limit_dod = Long.toString(0);
			System.out.println("초기 금액을 입금해주세요.");
			money = Long.toString(scan.nextLong());
			pushExcel(id);
		}
		
		else createAccountPw(id);
	}
	
	//생성 후 액셀에 입력
	void pushExcel(String id) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet("계좌정보");
			System.out.println(xsheet);
			xsheet.createRow(xsheet.getLastRowNum()+1); // 다음줄 Row를 생성
			
			xsheet.getRow(xsheet.getLastRowNum()).createCell(0).setCellValue(id);
			xsheet.getRow(xsheet.getLastRowNum()).createCell(1).setCellValue(account_num);
			xsheet.getRow(xsheet.getLastRowNum()).createCell(2).setCellValue(money);
			xsheet.getRow(xsheet.getLastRowNum()).createCell(3).setCellValue(account_pw);
			xsheet.getRow(xsheet.getLastRowNum()).createCell(4).setCellValue(limit_money);
			xsheet.getRow(xsheet.getLastRowNum()).createCell(5).setCellValue(limit_dod);
			
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);
			
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
			
			System.out.println("계좌 생성이 완료되었습니다.");
		}
		catch(Exception e) {
			
		}
	}
}
