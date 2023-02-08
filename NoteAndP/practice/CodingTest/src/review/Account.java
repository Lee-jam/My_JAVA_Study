package review;

import java.util.Scanner;
//은행업무 만들기
public class Account {
	//필드 : 멤버변수
	int id;
	String password;
	String  ownerName;
	int balance;

	//생성자
	public Account(int id,String password, String ownerName) {
		this.id = id;
		this.password = password;
		this.ownerName = ownerName;
		System.out.println("계좌 생성");
	}
	
	//입금
	public void deposit(int money) {
		balance+=money;
		System.out.println(money+"원을 입금합니다.");
		System.out.println("현재 계좌 잔액은 "+balance+"원입니다.");
	}
	
	//출금
	public int withdraw(int money, String password) {
		if(isCorrectPassword(password)){			
			if(balance<money) {
				System.out.println("금액이 부족합니다.");
				return 0;
			}
			else {
				balance-=money;
				System.out.println(money+"원을 출금합니다.");
				System.out.println("잔액이 "+balance+"원을 남았습니다.");
				return money;
			}		
		}
		else {
			System.out.println("패스워드가 틀렸습니다.");
			return 0;
		}
	}
	
	//잔액 확인
	public int checkBalance(String password) {
		if(isCorrectPassword(password)) {
			System.out.println("계좌 잔액이 "+balance+"원을 남았습니다.");
			return balance;
		}
		else System.out.println("패스워드가 틀렸습니다.");
		return 0;
	}
	
	//패스워드 확인
	public boolean isCorrectPassword(String password) {
		return this.password.equals(password)?true:false;
	}
	
	//패스워드 입력
	public String passWordInput() {
		Scanner scan = new Scanner(System.in);
		System.out.print("비빌번호를 입력하세요. :");
		String pw = scan.next();
		return pw;
	}
	
	//금액 입력
	public int moneyInput() {
		Scanner scan = new Scanner(System.in);
		System.out.print("숫자를 입력하세요. :");
		int pw = scan.nextInt();
		return pw;
	}
	//메뉴 선택
	public int menuInput() {
		Scanner scan = new Scanner(System.in);
		System.out.println("ex)1:입금, 2:출금, 3:잔액확인, 4:종료");
		System.out.print("메뉴를 고르세요. : ");
		int pw = scan.nextInt();
		return pw;
	}
	
	
	//메인
	public static void main(String[] args) {
		Account account = new Account(1,"password!@#","park");
		boolean bank = true;
		
		while (bank) {
			switch (account.menuInput()) {
			case 1: {
				System.out.print("입금하실 ");
				account.deposit(account.moneyInput());
				continue;
			}
			case 2: {
				System.out.print("출금하실 ");
				account.withdraw(account.moneyInput(),account.passWordInput());
				continue;
			}
			case 3: {
				account.checkBalance(account.passWordInput());
				continue;
			}
			case 4: {
				bank = false;
				System.out.println("은행 업무를 종료합니다.");
				break;
			}
			default:
				System.out.println("그런 메뉴는 없습니다.");
				continue;
			}
		}
	}
}