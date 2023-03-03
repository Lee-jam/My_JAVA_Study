package banksystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Account extends MemTableInterface{
	private String[] accountMain = {"Account_id","Account_Num","Money","Account_pw","Limit_money","Limit_dod"};
	private String path = "D:\\My_JAVA_Study\\My_JAVA_Study\\project\\banksystem\\data\\계좌정보관리.xlsx";
	
	//계좌 정보 저장 변수
	private static ArrayList<String> acc_idList = new ArrayList<>(); 	//0sheet 0 Row : (1~x)column(Cell)
	private static ArrayList<String> acc_numList = new ArrayList<>();		//0sheet 1 Row : (1~x)column(Cell)
	private static ArrayList<String> acc_moneyList = new ArrayList<>();		//0sheet 2 Row
	private static ArrayList<String> acc_pwList = new ArrayList<>();		//0sheet 3 Row
	private static ArrayList<String> acc_limitList = new ArrayList<>();
	private static ArrayList<String> acc_dodList = new ArrayList<>();
	
	//계좌 관련 멤버변수
	String account_num="";
	long convert;
	String money;
	String limit_money;
	String limit_dod;
	String account_pw;
	int count;
	boolean sucess_state;
	String select;
	//엑셀 관련 멤버변수
//	XSSFWorkbook xwb;
//	FileOutputStream fos;
	
	Scanner scan = new Scanner(System.in);
	
	//메서드 사용하기 위한 생성자
	public Account(int state) {
		//1일 경우 계좌관리 엑셀 로드
		if(state == 1) {
			try {
				excelAccountLoad();			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//계좌생성 생성자
	public Account(String id) {
		System.out.println("=======계좌를 생성합니다.=======");
		createAccSpace(id);
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
			
			acc_idList.add(id);
			acc_numList.add(account_num);
			acc_moneyList.add(money);
			acc_pwList.add(account_pw);
			acc_limitList.add(limit_money);
			acc_dodList.add(limit_dod);
			
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
			
			System.out.println("계좌 생성이 완료되었습니다.");
		}
		catch(Exception e) {
			
		}
	}
	//엑셀에 계좌 업데이트
	void excelAccountLoad() throws Exception{
		FileInputStream test = new FileInputStream(path);
		XSSFWorkbook xwb = new XSSFWorkbook(test);
		XSSFSheet a = xwb.getSheet("계좌정보");
		try {
			
			for(Row row:a) {
				for(Cell c:row) {
					if(c.getColumnIndex()==0){
						this.acc_idList.add(c.getStringCellValue());
					}
					else if(c.getColumnIndex()==1){
						this.acc_numList.add(c.getStringCellValue());
					}
					else if(c.getColumnIndex()==2){
						this.acc_moneyList.add(c.getStringCellValue());
					}
					else if(c.getColumnIndex()==3){
						this.acc_pwList.add(c.getStringCellValue());
					}					
					else if(c.getColumnIndex()==4){
						this.acc_limitList.add(c.getStringCellValue());
					}
					else if(c.getColumnIndex()==5){
						this.acc_dodList.add(c.getStringCellValue());
					}
					
				}
			}
//			System.out.println("0"+acc_idList.toString());
			xwb.close();
			test.close();
			System.out.println("Finish Update!");
			}
		catch(Exception e) {
			System.out.println("out_account");
		}
	}
	//계좌 조회
	void memberAccountInfo(String id){
		count = 0;
		int num = 0;
		System.out.println("============보유하신 계좌 목록============");
//		System.out.println("1"+this.acc_idList.toString());
		for(String ids : this.acc_idList) {
			if(id.equals(ids)) {
				num++;
				System.out.println(num+" 계좌번호 : "+this.acc_numList.get(count)+"|| 보유금액 : "+this.acc_moneyList.get(count));
			}
			if(num==0&&count==acc_idList.size()-1) {
				System.out.println("보유하신 계좌가 존재하지 않습니다.");
			}
			count++;
		}
	}
	//tier 설정
	void memberTierSet(String id) {
		count = 0;
		Long total = null;
		for(String ids : this.acc_idList) {
			if(id.equals(ids)) {
				total = Long.parseLong(acc_moneyList.get(count));
			}
			count++;
		}
		//계좌 총액이 1000만원 이상일 경우 VIP || 아니면 Normal
		if(total>=10000000) {
			tierUpdate(id, "VIP");
			new Account(1);
		}
		else if(total<10000000&&total>0) {
			tierUpdate(id, "Normal");
			new Account(1);
		}
	}
	//금액 변경 설정
	void updateMoney(String accountNum, long money) {
		String tomoney = Long.toString(money);
		try{
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet("계좌정보");
			xsheet.getRow(acc_numList.indexOf(accountNum)).createCell(2).setCellValue(tomoney);
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);			
			acc_moneyList.set(acc_numList.indexOf(accountNum), tomoney);
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
		}catch(IOException e) {System.out.println(1);}
		catch(IndexOutOfBoundsException e) {}
	}
	//상대 금액 변경 설정
	void fromupdateMoney(String accountNum, long money) {
		Long existMoney = Long.parseLong(acc_moneyList.get(acc_numList.indexOf(accountNum)));
		String tomoney = Long.toString(money+existMoney);
		try{
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet xsheet = xwb.getSheet("계좌정보");
			xsheet.getRow(acc_numList.indexOf(accountNum)).createCell(2).setCellValue(tomoney);
			FileOutputStream fos = new FileOutputStream(path);
			xwb.write(fos);			
			acc_moneyList.set(acc_numList.indexOf(accountNum), tomoney);
			fos.flush();
			fos.close();
			xwb.close();
			fis.close();
		}catch(IOException e) {System.out.println(1);}
		catch(IndexOutOfBoundsException e) {}
	}
	//한도금액 설정
	//한도를 알려면 해당 id 한도리스트에서 인덱스 값에 위치한 현재 한도를 받아옴. 티어도 동일
	//한도를 받고 조건이 맞으면 
	
	void AccountLimitSet(String id) {
		memberAccountInfo(id);
		System.out.println("한도를 설정할 계좌를 선택해주세요.");
		int select = scan.nextInt();
		if(select>acc_numList.size()) {
			System.out.println("잘못된 접근입니다. 다시 시도해주세요.");
			AccountLimitSet(id);
		}
		long now_limit = Long.parseLong(acc_limitList.get(select));
		String now_tier = getTierList().get(getIdList().indexOf(id));
		System.out.println("선택하신 계좌의 현재 한도 : "+now_limit+"원"
						+"\n고객님의 등급 : "+now_tier+"등급");
		System.out.println("한도금액을 설정하세요.");
		long set = scan.nextLong();
		if(now_tier.equals("Normal")&&now_limit>=10000000) {
			System.out.println(set+"원은 고객님의 등급에서는 설정할 수 없는 한도 금액입니다.");
		}
		else {
			try{
				FileInputStream fis = new FileInputStream(path);
				XSSFWorkbook xwb = new XSSFWorkbook(fis);
				XSSFSheet xsheet = xwb.getSheet(xwb.getSheetName(0));
				xsheet.getRow(select).createCell(4).setCellValue(Long.toString(set));
				FileOutputStream fos = new FileOutputStream(path);
				xwb.write(fos);			
				acc_limitList.set(select, Long.toString(set));
				fos.flush();
				fos.close();
				xwb.close();
				fis.close();
				System.out.println(set+"원으로 고객님의 한도가 변경되었습니다.");
			}catch(IOException e) {System.out.println(1);}
			catch(IndexOutOfBoundsException e) {}

		}
	}
	//같은 계좌 이체
	//계좌 입력하고, 돈입력하고, 비밀번호 입력하고 이체, 확인
	boolean accountTransfer(String id) {
		sucess_state = false;
		int index = 0;
		System.out.print("계좌번호를 입력하세요 :");
		String num = scan.next();
		System.out.print("이체하실 금액을 입력하세요 :");
		long mon = scan.nextLong();
		System.out.println("보내실 고객님의 계좌를 선택해주세요.");
		memberAccountInfo(id);
		int a = scan.nextInt(); //선택 계좌랑 비밀번호 인덱스와 같음.
		Long my_money = Long.parseLong(acc_moneyList.get(a));
		Long my_limit = Long.parseLong(acc_limitList.get(a));
		System.out.println(my_money);
		
		if(!acc_numList.contains(num)) {
			System.out.print("입력하신 계좌는 없는 계좌입니다. (Y.다시입력 N.뒤로가기):");
			select = scan.next();
			if(select.equals("Y")) {
				accountTransfer(id);				
			}
			else if(select.equals("N")) {
				return sucess_state;
			}
		}
		else if(my_money<mon) {
			System.out.print("금액이 부족합니다. (1.다시입력 2.뒤로가기):");
			if(scan.nextInt()==1) {
				accountTransfer(id);				
			}
			else if(scan.nextInt()==2) {
				return sucess_state;
			}
		}
		else if(mon>my_limit) {
			System.out.println("이체 한도를 초과하였습니다. 다시 시도해주세요.");
			return sucess_state;
		}
		
		else {
			//계좌넘버 배열에서 num 의 인덱스 번호를 찾고 계좌 idList에서 인덱스 번호값에 맞는 아이디를 찾고 회원관리 아이디 배열에서 아이디 값에 있는 인덱스값을 반환
			index = getIdList().indexOf(acc_idList.get(acc_numList.indexOf(num))); //이름 찾기 인덱스
			
			System.out.println("예금주 :"+getNameList().get(index)+"님에게 "+mon+"원을 이체하시겠습니까?");
			select =scan.next().toUpperCase();
			System.out.println(1);
			if(select.equals("Y")) {
				System.out.print("비밀번호 입력 :");
				System.out.println(acc_pwList.toString());
				System.out.println(index);
				String pw = scan.next();
				if(acc_pwList.get(a).equals(pw)) {
					System.out.println("예금주 :"+getNameList().get(index)+"님에게 "+mon+"원을 이체합니다.");
					
					my_money -= mon;
					
					updateMoney(acc_numList.get(a), my_money);
					fromupdateMoney(num, mon);
					sucess_state = true;
				}
				else {
					System.out.println("비밀번호를 틀렸습니다. (Y.다시입력 N.뒤로가기):");
					select = scan.next().toUpperCase();
					if(select.equals("Y")) {
						accountTransfer(id);				
					}
					else if(select.equals("N")) {
					}
				}
			}
			else {System.out.println("계좌이체를 종료합니다.");}
		}
		
		return sucess_state;
		
	}
	
	//계좌 삭제
	void accountRemoveAll() {
		
	}
	
	//내정보 조회
	
	void myInfo(String id){
		System.out.println("===============");
		System.out.println("이름 : "+getNameList().get(getIdList().indexOf(id)));
		System.out.println("아이디 : "+id);
		System.out.println("등급 : "+getTierList().get(getIdList().indexOf(id)));
		memberAccountInfo(id);
		
		
	}
	
	//타 은행 계좌 이체는 나중에
		
}

