package banksystem;

import java.util.Scanner;

public class ConsolePage {
	Scanner scan = new Scanner(System.in);
	int count = 0;
	
	public ConsolePage() {
		logIn();
		if(count==1) {
			mainPage();
		}
	}
	
	public void logIn() {
		while(count == 0){
			System.out.println("1.로그인 2.회원가입");
			int select = scan.nextInt();
			switch (select) {
				case 1: {
					if(new Login().getAccess()){
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
			
			}
		}
	}
	public void mainPage() {
		while(count == 1) {
			System.out.println("메뉴를 선택하세요.");
			System.out.print("1.계좌 조회 2. 계좌 이체 3.계좌 생성\n"
							+ "4.계좌 삭제 5.한도금액 설정 6.내정보 조회\n"
							+ "7로그 아웃");
			int select = scan.nextInt();
			switch (select) {
				case 1: {
					//계좌 조회
					break;
				}
				case 2: {
					//계좌 이체
					break;
				}
				case 3: {
					//계좌 생성
					break;
				}
				case 4: {
					//계좌 삭제
					break;
				}
				case 5: {
					//한도금액 설정
					break;
				}
				case 6: {
					//내정보 조회
					break;
				}
				case 7: {
					//로그 아웃
					count = 0;
					break;
				}
			}
		}
	}
}
