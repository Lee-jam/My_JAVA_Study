package review2;

public class Quest {
	public static void main(String[] args) {

		Developer tom = new Developer("Tom",5);
		Developer john = new Developer("John",8);
		System.out.println(tom.salary());
		System.out.println(tom.level());
		System.out.println(john.salary());
		Corperation corp = new Corperation("Google");
		corp.addDeveloper(john);
		corp.addDeveloper(tom);
		System.out.println(corp.salaryExpen());
	}

}
