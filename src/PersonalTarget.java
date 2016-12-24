
public class PersonalTarget {
	
	private String name;
	private int age;
	private int gender; //0=male, 1=female
	private int height; //inches
	private int weight; //pounds
	private double bodyFat = .15; //between 0 and 1, inclusive (percentage) [default to 15%]
	private double leanMass;  //weight*(1-bodyFat)
	private int lifestyle; //between 0 and 10, inclusive
	private int diet; //0 = cut, 1 = maintain, 2 = bulk
	private int protein;
	private int carbs;
	private int fat;

	public PersonalTarget() { }
	
	//////////////////
	//getter methods//
	//////////////////
	public String getName() { return this.name; }
	public int getAge() { return this.age; }
	public int getHeightUS() { return this.height; }
	public double getHeightUK() { return this.height * 2.54; }
	public int getWeightUS() { return this.weight; }
	public double getWeightUK() { return this.weight * 0.45359237; }
	public double getBodyFat() { return this.bodyFat; }
	public double getLeanMass() { return this.leanMass; }
	public int getLifstyle() { return this.lifestyle; }
	public int getAverageBMR() {
		int sum = this.harrisBMR() + this.mifflinBMR() + this.katchBMR();
		return sum/3;
	}
	public int getTDEE() { return (int) (this.getAverageBMR() * (1 + ((double) this.lifestyle/10))); }
	
	
	//////////////////
	//setter methods//
	//////////////////
	public void setName(String s) { this.name = s; }
	public void setGender(int n) { this.gender = n; }
	public void setAge(int n) {  this.age = n; }
	public void setHeight(int n) { this.height = n; }
	public void setWeight(int n) { this.weight = n; }
	public void setBodyFat(double n) { this.bodyFat = n; }
	public void setLifestyle(int n) { this.lifestyle = n; }
	public void setDiet(int n) { this.diet = n; }
	
	//Harris-Benedict Formula
	public int harrisBMR() { //male
		int modifier = 0;
		double wtMod = this.getWeightUK();
		double htMod = this.getHeightUK();
		double ageMod = this.getAge();
		
		if (this.gender == 0) { 
			modifier = 66;
			wtMod *= 13.7;
			htMod *= 5;
			ageMod *= 6.67;
			}
		else if (this.gender == 1) { //female
			modifier = 655;
			wtMod *= 9.6;
			htMod *= 1.8;
			ageMod *= 4.7;
			}
		return modifier + (int) (wtMod + htMod - ageMod);
	}
	
	//Mifflin-St Jeor Method
	public int mifflinBMR() {
		int modifier = 0;
		double wtMod = this.getWeightUK();
		double htMod = this.getHeightUK();
		double ageMod = this.getAge();
		
		if (this.gender == 0) { //male
			modifier = 5;
			wtMod *= 9.99;
			htMod *= 6.25;
			ageMod *= 4.95;
			}
		else if (this.gender == 1) { //female
			modifier = -16;
			wtMod *= 9.99;
			htMod *= 6.25;
			ageMod *= 4.92;
			}
		return modifier + (int) (wtMod + htMod - ageMod);
	}
	
	//Katch McArdle Method
	public int katchBMR() {
		double lbm = (1-this.bodyFat)*this.getWeightUK();
		double mod = 21.6*lbm;
		return 370 + (int) (mod);
	}
	
	//calories
	public int getCalories() {
		double n = 1;
		if (diet == 0) { n = .9; }
		else if (diet == 2) { n = 1.1; }
		return (int) (n * this.getTDEE());
	}
	
	//grams of protein
	public int getProtein() {
		
		
		return 1;
	}
		
	
	//grams of fat
	
	//grams of carbs
	
	public String inchesToFeet(int n) {
		int ft = n/12;
		int inches = n % 12;
		return ft + "'" + inches + "\"";
	}
	
	public static void main(String [] args) {
		
		PersonalTarget pt = new PersonalTarget();
		pt.setAge(19);
		pt.setGender(1);
		pt.setHeight(71);
		pt.setWeight(172);
		pt.setBodyFat(.22);
		pt.setDiet(1);
		pt.setLifestyle(4);
		
		System.out.println("ht " + pt.getHeightUK());
		System.out.println("wt " + pt.getWeightUK());
		System.out.println("age " + pt.getAge());
		System.out.println("BMR1 " + pt.harrisBMR());
		System.out.println("BMR2 " + pt.katchBMR());
		System.out.println("BMR3 " + pt.mifflinBMR());
		System.out.println("BMRavg " + pt.getAverageBMR());
		System.out.println("TDEE " + pt.getTDEE());
		System.out.println("cals " + pt.getCalories());
	}
	
}
