package banksystem;

import java.net.InetAddress;

public class Login {
	private int errornum;
	private Set<String> idlist;
	private Set<String> idlist;
	
	boolean idCheck(String id) {
		return id.equals(엑셀에서 불러온 id);
	}
	boolean pwCheck(String pw) {
		if(pw.equals(엑셀에서 불러온 pw)) {
			System.out.println("접속 IP 주소 : "+InetAddress.getLocalHost().getHostAddress());
			return true;
		}
		else {
			System.out.println("비밀번호가 올바르지 않습니다."+"틀린 횟수 : "+ ++errornum);
			//errornum를 엑셀에 넣어주기
			return false;
		}
	}
	
} 