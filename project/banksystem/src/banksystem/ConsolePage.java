package banksystem;


import java.util.Scanner;

public class ConsolePage{
	private Account accountFunc = new Account();
//	private MemTableInterface mtif = new MemTableInterface();
	private Scanner scan = new Scanner(System.in);
	private int count = 0;
	private String resent_id;
	CreateMember a = new CreateMember();
	
	public ConsolePage() {
		accountFunc.excelLoad();
		accountFunc.excelAccountLoad();
//		System.out.println(accountFunc.getAcc_numList());
		loopPage();
	}
	void loopPage() {
		if(count==0) {
			logIn();			
		}
		if(count==1) {
			mainPage(resent_id);
		}
	}
	
	private void logIn() {
		while(count == 0){
			System.out.println("=====환영합니다 호갱님======");
			System.out.println("=1.로그인 2.회원 가입 3.종료=");
			System.out.print("======메뉴를 선택해 주세요 : ");
			int select = 0;
			try {
				select = scan.nextInt();
			}catch(IndexOutOfBoundsException e) {}
			
			switch (select) {
				case 1: {
					Login a = new Login();
					if(a.getAccess()){
						resent_id=a.getResentId();
						count=1;
					}
					break;
				}
				case 2: {
					System.out.println("=======회원가입을 페이지로 이동합니다.======");
					a.createMem();
					System.out.println("회원 가입이 완료되었습니다!");
					System.out.println("바로 로그인 하시겠습니까?(Y/N)");
					String sel = scan.next().toUpperCase();
					if(sel.equals("Y")) {
						resent_id = a.getCreateId();
						count = 1;
						break;
						//로그인 바로 하는 메서드?생성자? 만들기
					}
					else if(sel.equals("N")){
						break;
					}
				}
				case 3:{
					count = 99;
					break;
				}
			}
		}
	}
	private void mainPage(String resent_id) {
		while(count == 1) {
			System.out.println("===== 환영합니다. "+accountFunc.getNameList().get(accountFunc.getIdList().indexOf(resent_id))+" 고객님! =====");
			System.out.print("1.계좌 조회 2. 계좌 이체 3.계좌 생성\n"
							+ "4.계좌 삭제 5.한도금액 설정 6.내정보 조회\n"
							+ "7로그 아웃\n");
			System.out.print("메뉴를 선택하세요. :");
			int select = scan.nextInt();
			if(!accountFunc.getAcc_idList().contains(resent_id)) {
				System.out.println("계좌가 존재하지않습니다. 계좌 생성으로 이동합니다.");
				select = 3;
			}
			try {
				
			switch (select) {
				case 1: {
					//계좌 조회
					accountFunc.memberAccountInfo(resent_id);
					break;
				}
				case 2: {
					//계좌 이체
					if(accountFunc.accountTransfer(resent_id))
						System.out.println("이체 성공!");
					else System.out.println("이체 실패...");
					
					
					break;
				}
				case 3: {
					//계좌 생성
					accountFunc.createAccount(resent_id);
					break;
				}
				case 4: {
					//계좌 삭제
					accountFunc.accountRemoveProcess(resent_id);
					break;
				}
				case 5: {
					//한도금액 설정
					accountFunc.AccountLimitSet(resent_id);
					break;
				}
				case 6: {
					//내정보 조회
					accountFunc.myInfo(resent_id);
					break;
				}
				case 7: {
					//로그 아웃
					count = 0;
					loopPage();
					break;
				}
				default: {
					System.out.println("잘못된 접근입니다.");
					break;
				}
			}
			}catch(IndexOutOfBoundsException e) {}
		}
	}
}
