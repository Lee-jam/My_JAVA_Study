package review2;
import java.util.ArrayList;

public class Corperation extends Developer{
	private String name;
	private ArrayList<Developer> developers;
	
	public Corperation() {
		developers = new ArrayList<>();
	}
	public Corperation(String name) {
		this();
		this.name = name;
	}
	
	int salaryExpen() {
		int total=0;
		for(Developer i:developers) {
			System.out.println("소속 개발자 "+i.getname()+"의 연봉은 :"+i.salary());
			total+=i.salary();
		}
		return total;
	}
	void addDeveloper(Developer developer) {
		developers.add(developer);
	}
}
