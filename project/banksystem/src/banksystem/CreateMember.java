package banksystem;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/* 
 * 
 */

public class CreateMember {
	private MemTableInterface tif = new MemTableInterface();
	private ArrayList<String> idlist = tif.getIdList();
	private ArrayList<String> maillist = tif.getMailList();
	private String name;
	private String id;
	private String pw;
	private String mail;
	private boolean create_access;
	private Scanner scan = new Scanner(System.in);
	
	String getCreateId() {
		return id;
	}
	
	public void createMem() {
		inputName();
		//엑셀에 포맷에 맡게 데이터 생성되도록 인풋되도록 구현
		if(create_access) {
			System.out.println("이름 :"+name+"\n"
					+ "id : "+id+"\n"
					+ "pw : "+pw+"\n"
					+ "mail : "+mail);
			tif.excelUpdate(name, id, pw, mail);
		}
	}
	
	private void inputName() {
		System.out.print("이름을 입력하세요. : ");
		String inputname = scan.nextLine();
		if(!inputname.matches("^[가-힣]*$")) {
			System.out.println("잘못된 문자가 포함되어 있습니다.");
			inputName();
		}
		else {
			name = inputname;
			inputId();
			
		}
	}
	
	private void inputId() {
//		fis = new FileInputStream("/banksystem/data/회원정보관리.xlsx");
		System.out.println("아이디를 입력하세요. :");
		String inputid = scan.nextLine();
		
		if(inputid.length()<7||inputid.length()>11) {
			System.out.println("아이디는 7자 이상 10자 이내 입니다.");
			inputId();
		}
		
		if(idlist.contains(inputid)) {
			System.out.println("중복된 아이디입니다.");
			inputId();
		}
		else {
			System.out.println("사용 가능한 ID입니다.");
			id = inputid;
			inputPW();
		}
	}
	private void inputPW() {
		System.out.println("비밀번호를 입력하세요. :");
		String inputpw = scan.nextLine();
		//0~9까지 중복 없애는 것은 시간 남으면
		if(inputpw.matches("(?=.*[a-zA-Z0-9!@#$%^&*]).{8,15}$")) {
			System.out.println("사용 가능한 비밀번호입니다.");
			pw = inputpw;
			inputMail();
		}
		else {
			System.out.println("숫자,영문,특수문자를 조합해야 합니다.");
			inputPW();
		}
	}
	private boolean inputMail() {
		System.out.println("이메일을 입력하세요. :");
		String inputmail = scan.nextLine();
		if(!inputmail.matches("[a-z0-9]+@[a-z0-9.]+$")) {
			System.out.println("잘못된 메일 형식입니다.");
			inputMail();
			return false;
		}
		else if(maillist.contains(inputmail)) {
			System.out.println("이미 회원가입된 이메일입니다.");
			inputMail();
			return false;
		}
		else {
			mail = inputmail;
			System.out.println("사용가능한 이메일입니다.");
			return create_access=true;
		}
	}
	

}
