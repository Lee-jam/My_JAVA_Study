package review2;

public class Developer {
	private String name;
	private int career;
	
	public Developer() {}
	public Developer(String name, int career){
		this.name = name;
		this.career = career;
		
	}
	
	public int level() {
		int money = 0;
		return money=career<3?28000000:career<7?35000000:career>=7?45000000:0;
	}
	
	public int salary() {
		int money = 0;
		return money=career<3?(28000000+1000000*career):career<7?(35000000+1000000*career):career>=7?(45000000+1000000*career):0;
	}
	public String getname() {
		return name;
	}
}
