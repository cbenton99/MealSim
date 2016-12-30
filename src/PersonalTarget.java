

public class PersonalTarget {
	
	private String name;
	private int age;
	private boolean male; //true = male, false = female
	private int height; //inches
	private int weight; //pounds
	private double bodyFat = .15; //between 0 and 1, inclusive (percentage) [default to 15%]
	private double leanMass;  //weight*(1-bodyFat)
	private int lifestyle; //between 0 and 10, inclusive
	private int diet; //0 = cut, 1 = maintain, 2 = bulk

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
	public double getDiet() { return this.diet; }
	public int getProteinGrams() { return ProteinGrams(); }
	public int getProteinCalories() { return ProteinCalories(); }
	public int getCarbsGrams() { return CarbsGrams(); }
	public int getCarbsCalories() { return CarbsCalories(); }
	public int getFatGrams() { return FatGrams(); }
	public int getFatCalories() { return FatCalories(); }
	public int getAverageBMR() {
		int sum = harrisBMR() + mifflinBMR() + katchBMR();
		return sum / 3;
	}
	public int getTDEE() { return (int) (getAverageBMR() * (1 + ((double) this.lifestyle / 10))); }
	
	public int getCalories() {
		double n = 1;
		if (diet == 0) { n = .9; }
		else if (diet == 2) { n = 1.1; }
		return (int) (n * getTDEE());
	}
	
	//////////////////
	//setter methods//
	//////////////////
	public void setName(String s) { this.name = s; }
	public void setMale(boolean b) { this.male = b; }
	public void setAge(int n) { this.age = n; }
	public void setHeight(int n) { this.height = n; }
	public void setWeight(int n) { this.weight = n; }
	public void setBodyFat(double n) { this.bodyFat = n; }
	public void setLifestyle(int n) { this.lifestyle = n; }
	public void setDiet(int n) { this.diet = n; }
	
	//Harris-Benedict Formula
	public int harrisBMR() {
		int modifier = 0;
		double wtMod = getWeightUK();
		double htMod = getHeightUK();
		double ageMod = getAge();
		
		if (this.male == true) { //male
			modifier = 66;
			wtMod *= 13.7;
			htMod *= 5;
			ageMod *= 6.67;
			}
		else if (this.male == false) { //female
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
		double wtMod = getWeightUK();
		double htMod = getHeightUK();
		double ageMod = getAge();
		
		if (this.male == true) { //male
			modifier = 5;
			wtMod *= 9.99;
			htMod *= 6.25;
			ageMod *= 4.95;
			}
		else if (this.male == false) { //female
			modifier = -16;
			wtMod *= 9.99;
			htMod *= 6.25;
			ageMod *= 4.92;
			}
		return modifier + (int) (wtMod + htMod - ageMod);
	}
	
	//Katch McArdle Method
	public int katchBMR() {
		double lbm = (1 - this.bodyFat) * getWeightUK();
		double mod = 21.6 * lbm;
		return 370 + (int) (mod);
	}
		
	//protein		
	public int ProteinGrams() {
		if (this.diet == 0 || this.diet == 1) {
			return (int) (getLeanMass() * 1.5); }
		else if (this.diet == 2) {
			return (int) (getLeanMass()); }
		else {
			System.out.println("error calculating grams of protein");
			return -1; }
	}
	public int ProteinCalories() { return ProteinGrams() * 4; }
	
	//carbs
	public int CarbsGrams() { return CarbsCalories() / 4; }
	public int CarbsCalories() { return (getCalories() - (ProteinCalories() + FatCalories())); }
	
	//fat
	public int FatGrams() { return (int) (.45 * getLeanMass()); }
	public int FatCalories() { return FatGrams() * 9; }
	
	
	
	public String inchesToFeet(int n) {
		int ft = n / 12;
		int inches = n % 12;
		return ft + "'" + inches + "\"";
	}
	
	public static void main(String [] args) {
		
		PersonalTarget pt = new PersonalTarget();
		pt.setAge(19);
		pt.setMale(true);
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
