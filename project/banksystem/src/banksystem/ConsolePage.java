package banksystem;

import java.util.Scanner;

public class ConsolePage{
	private AccountInfo acc = new AccountInfo();
	private MemTableInterface tif;
	private Scanner scan = new Scanner(System.in);
	private int count = 0;
	private String resent_id;
	
	public ConsolePage() {
		loopPage();
	}
	void loopPage() {
		logIn();
		if(count==1) {
			mainPage(resent_id);
		}
	}
	
	private void logIn() {
		while(count == 0){
			System.out.println("=====환영합니다 호갱님======");
			System.out.println("=1.로그인 2.회원 가입 3.종료=");
			int select = scan.nextInt();
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
					new CreateMember().createMem();
					System.out.println("회원 가입이 완료되었습니다!");
					System.out.println("바로 로그인 하시겠습니까?(Y/N)");
					String sel = scan.next().toUpperCase();
					if(sel.equals("Y")) {
						select = 1;
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
			System.out.print("1.계좌 조회 2. 계좌 이체 3.계좌 생성\n"
							+ "4.계좌 삭제 5.한도금액 설정 6.내정보 조회\n"
							+ "7로그 아웃\n");
			System.out.print("메뉴를 선택하세요. :");
			int select = scan.nextInt();
			switch (select) {
				case 1: {
					//계좌 조회
					new Account(0).memberAccountInfo(resent_id);
					break;
				}
				case 2: {
					//계좌 이체
					if(new Account(0).accountTransfer(resent_id))
						System.out.println("이체 성공!");
					else System.out.println("이체 실패...");
					
					
					break;
				}
				case 3: {
					//계좌 생성
					new Account(resent_id);
					break;
				}
				case 4: {
					//계좌 삭제
					break;
				}
				case 5: {
					//한도금액 설정
					new Account(0).AccountLimitSet(resent_id);
					break;
				}
				case 6: {
					//내정보 조회
					new Account(0).myInfo(resent_id);
					break;
				}
				case 7: {
					//로그 아웃
					count = 0;
					loopPage();
					break;
				}
			}
		}
	}
}
