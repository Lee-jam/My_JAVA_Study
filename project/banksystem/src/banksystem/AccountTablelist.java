package banksystem;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AccountTablelist extends MemTableInterface{
//	private static ArrayList<String> acc_idList = new ArrayList<>(); 	//0sheet 0 Row : (1~x)column(Cell)
//	private static ArrayList<String> acc_numList = new ArrayList<>();		//0sheet 1 Row : (1~x)column(Cell)
//	private static ArrayList<String> acc_moneyList = new ArrayList<>();		//0sheet 2 Row
//	private static ArrayList<String> acc_pwList = new ArrayList<>();		//0sheet 3 Row
//	private static ArrayList<String> acc_limitList = new ArrayList<>();
//	private static ArrayList<String> acc_dodList = new ArrayList<>();
	private String path = "D:\\My_JAVA_Study\\My_JAVA_Study\\project\\banksystem\\data\\계좌정보관리.xlsx";
	
	public AccountTablelist() {
		try {
			excelAccountLoad();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	AccountTablelist(String id){
		memberAccountInfo(id);
	}
	
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
	void memberAccountInfo(String id){
		int count = 0;
		int num = 0;
		System.out.println("========보유하신 계좌 목록========");
		System.out.println("1"+this.acc_idList.toString());
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
	
}
