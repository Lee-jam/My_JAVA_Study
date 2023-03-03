package banksystem;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
	private MemTableInterface tif = new MemTableInterface();
	
	private String inputId;
	private int errornum;
	private ArrayList<String> idlist = tif.getIdList();
	private ArrayList<String> pwlist = tif.getPwList();
	private ArrayList<String> errlist = tif.getErrList();
	boolean access;
	private Scanner scan = new Scanner(System.in);
	
	public Login() {
		idCheck();
	}
	
	
	private void idCheck() {
		System.out.print("아이디를 입력하세요 :");
		String id = scan.next();
		if(errlist.get(idlist.indexOf(id)).equals("5")){
			System.out.println("에러 횟수를 초과한 아이디입니다.\n가까운 지점에 방문해주세요.");
		}
		if(idlist.contains(id)) {
			inputId = id;
			pwCheck();
		}
		else {
			System.out.println("존재하지 않는 아이디입니다.");
			System.out.print("회원가입을 하시겠습니까? :");
			if(scan.next().toUpperCase().equals("Y")) {
				new CreateMember();
			}
			else if(scan.next().toUpperCase().equals("N")) {
				idCheck();
			}
			else {
				System.out.println("잘못 입력하였습니다. 프로그램을 종료합니다.");
			}
		}
	}
	private boolean pwCheck() {
		System.out.print("패스워드를 입력하세요 :");
		String pw = scan.next();
		
		if(pwlist.get(idlist.indexOf(inputId)).equals(pw)&&errornum<5) {
			try {
				System.out.println("접속 IP 주소 : "+InetAddress.getLocalHost().getHostAddress());
				errornum = 0;
			}catch(UnknownHostException e) {}
			
			access = true;
		}
		else {
			System.out.println("비밀번호가 올바르지 않습니다."+"틀린 횟수 : "+ ++errornum);
			//errornum를 엑셀에 넣어주기
			if(errornum>=5) {
				System.out.println("비밀번호를 5회 이상 틀렸습니다. 지점에 방문하세요.");
				tif.excelUpdate(inputId, Integer.toString(errornum));
				access = false;
			}
			else {
				pwCheck();
			}
		}
		return access;
	}
	String getResentId() {
		return inputId;
	}
	boolean getAccess() {
		return access;
	}
	
} 