import java.util.*;

public class Ingredient {
	
	private double multiplier = 1;
	private String name;
	private String type;
	private double baseAmount;
	private String measurement;
	private int calories;
	private double totalFat;
	private double satFat;
	private double transFat;
	private double polyFat;
	private double monoFat;
	private int sodium;
	private int carbs;
	private int fiber;
	private int sugar;
	private int protien;
	private double price = 1;
	
	//void constructor
	public Ingredient() { }
	
	//convert line from .txt to new Ingredient
	public Ingredient(String s) {
		Scanner scanner = new Scanner(s);
		Scanner in = scanner.useDelimiter(",");
		
		this.name = in.next();
		this.type = in.next();
		this.baseAmount = in.nextDouble();
		this.measurement = in.next();
		this.calories = in.nextInt();
		this.totalFat = in.nextDouble();
		this.satFat = in.nextDouble();
		this.transFat = in.nextDouble();
		this.polyFat = in.nextDouble();
		this.monoFat = in.nextDouble();
		this.sodium = in.nextInt();
		this.carbs = in.nextInt();
		this.fiber = in.nextInt();
		this.sugar = in.nextInt();
		this.protien = in.nextInt();
		
		in.close();
		scanner.close();
	}
	
	
	//////////////////
	//getter methods//
	//////////////////
	public double getMultiplier() { return this.multiplier; }
	public String getName() { return this.name; }
	public String getType() { return this.type; }
	public double getBaseAmount() { return this.baseAmount; }
	public String getMeasurement() { return this.measurement; }
	public int getCalories() { return this.calories; }
	public double getTotalFat() { return this.totalFat; }
	public double getSatFat() { return this.satFat; }
	public double getTransFat() { return this.transFat; }
	public double getPolyFat() { return this.polyFat; }
	public double getMonoFat() { return this.monoFat; }
	public int getSodium() { return this.sodium; }
	public int getCarbs() { return this.carbs; }
	public int getFiber() { return this.fiber; }
	public int getSugar() { return this.sugar; }
	public int getProtien() { return this.protien; }
	public double getPrice() { return this.price; }
	
	//////////////////
	//setter methods//
	//////////////////
	public void setMultiplier(double n) { this.multiplier = n; }
	public void setName(String s) { this.name = s; }
	public void setType(String s) { this.type = s; }
	public void setBaseAmount(double n) { this.baseAmount = n; }
	public void setMeasurement(String s) { this.measurement = s; }
	public void setCalories(int n) { this.calories = n; }
	public void setTotalFat(double n) { this.totalFat = n; }
	public void setSatFat(double n) { this.satFat = n; }
	public void setTransFat(double n) { this.transFat = n; }
	public void setPolyFat(double n) { this.polyFat = n; }
	public void setMonoFat(double n) { this.monoFat = n; }
	public void setSodium(int n) { this.sodium = n; }
	public void setCarbs(int n) { this.carbs = n; }
	public void setFiber(int n) { this.fiber = n; }
	public void setSugar(int n) { this.sugar = n; }
	public void setProtien(int n) { this.protien = n; }
	public void setPrice(double n) { this.price = n; }
	
	//print condensed item info for ingredientList in GUI
	public Object[] toMacroArray() {
		Object[] arr = new Object[6];
		arr[0] = getName();
		arr[1] = getType();
		arr[2] = getCalories();
		arr[3] = getTotalFat();
		arr[4] = getCarbs();
		arr[5] = getProtien();
		
		return arr;
	}
	
	//print short item info for recipeSelectList in GUI
	public Object[] toShortArray() {
		Object[] arr = new Object[2];
		arr[0] = getName();
		arr[1] = getType();
			
		return arr;
	}
	
	//print recipe info for recipeRecipeList in GUI
	public Object[] toRecipeArray() {
		Object[] arr = new Object[5];
		arr[0] = getName();
		arr[1] = null;
		arr[2] = getBaseAmount();
		arr[3] = getMeasurement();
		arr[4] = getCalories();
			
		return arr;
	}
	
	
	//print condensed item info for ingredientList in GUI
	public String toString() {
		String s = "";
		s += getName() + "," + getType() + "," + getCalories()
		+ "," + getTotalFat() + "," + getCarbs() + "," + getProtien();
		
		return s;
	}
	
}
