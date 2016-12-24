import java.io.*;
import java.util.*;

@SuppressWarnings("resource")
public class Ingredient {
	
	//initialize variables for ingredient
	private File file;
	private String name;
	private String type;
	private double baseAmount;
	private String measurement;
	private double calories;
	private double totalFat;
	private double satFat;
	private double transFat;
	private double polyFat;
	private double monoFat;
	private double sodium;
	private double carbs;
	private double fiber;
	private double sugar;
	private double protein;
	private double price = 1; //1 for testing
	
	//void constructor
	public Ingredient() { }
	
	//create Ingredient object from a file
	public Ingredient(File file) { ingredientFromFile(file); }
	
	//create Ingredient object from a string
	public Ingredient(String s) { createIngredient(s); }
	
	//open specified file and scan first line
	//to pass to createIngredient
	public void ingredientFromFile(File file) {
		Scanner in = null;
		try { in = new Scanner(file).useDelimiter(","); }
		catch (FileNotFoundException e) {
			System.out.println("Error finding ingredient:" + file.getName());
			e.printStackTrace();
		}
		createIngredient(in.nextLine());
		in.close();
	}
	
	//convert string to Ingredient data
	public void createIngredient(String s) {
		
		Scanner in = new Scanner(s).useDelimiter(",");
		
		this.name = in.next();
		this.type = in.next();
		this.baseAmount = in.nextDouble();
		this.measurement = in.next();
		this.calories = in.nextDouble();
		this.totalFat = in.nextDouble();
		this.satFat = in.nextDouble();
		this.transFat = in.nextDouble();
		this.polyFat = in.nextDouble();
		this.monoFat = in.nextDouble();
		this.sodium = in.nextDouble();
		this.carbs = in.nextDouble();
		this.fiber = in.nextDouble();
		this.sugar = in.nextDouble();
		this.protein = in.nextDouble();
		file = new File(getName() + ".txt");
				
		in.close();
	}
	
	//////////////////
	//getter methods//
	//////////////////
	public File getFile() { return this.file; }
	public String getName() { return this.name; }
	public String getType() { return this.type; }
	public double getBaseAmount() { return this.baseAmount; }
	public String getMeasurement() { return this.measurement; }
	public double getCalories() { return this.calories; }
	public double getTotalFat() { return this.totalFat; }
	public double getSatFat() { return this.satFat; }
	public double getTransFat() { return this.transFat; }
	public double getPolyFat() { return this.polyFat; }
	public double getMonoFat() { return this.monoFat; }
	public double getSodium() { return this.sodium; }
	public double getCarbs() { return this.carbs; }
	public double getFiber() { return this.fiber; }
	public double getSugar() { return this.sugar; }
	public double getProtein() { return this.protein; }
	public double getPrice() { return this.price; }
	
	//////////////////
	//setter methods//
	//////////////////
	public void setFile(File f) { this.file = f; }
	public void setName(String s) { this.name = s; }
	public void setType(String s) { this.type = s; }
	public void setBaseAmount(double n) { this.baseAmount = n; }
	public void setMeasurement(String s) { this.measurement = s; }
	public void setCalories(double n) { this.calories = n; }
	public void setTotalFat(double n) { this.totalFat = n; }
	public void setSatFat(double n) { this.satFat = n; }
	public void setTransFat(double n) { this.transFat = n; }
	public void setPolyFat(double n) { this.polyFat = n; }
	public void setMonoFat(double n) { this.monoFat = n; }
	public void setSodium(double n) { this.sodium = n; }
	public void setCarbs(double n) { this.carbs = n; }
	public void setFiber(double n) { this.fiber = n; }
	public void setSugar(double n) { this.sugar = n; }
	public void setProtien(double n) { this.protein = n; }
	public void setPrice(double n) { this.price = n; }
	
	//return condensed Ingredient data for ingredientList in GUI
	public Object[] toMacroArray() {
		Object[] arr = new Object[6];
		arr[0] = getName();
		arr[1] = getType();
		arr[2] = getCalories();
		arr[3] = getTotalFat();
		arr[4] = getCarbs();
		arr[5] = getProtein();
		
		return arr;
	}
	
	//return short item info for recipeSelectList in GUI
	public Object[] toShortArray() {
		Object[] arr = new Object[2];
		arr[0] = getName();
		arr[1] = getType();
			
		return arr;
	}
	
	//return recipe info for recipeRecipeList in GUI
	public Object[] toRecipeArray() {
		Object[] arr = new Object[5];
		arr[0] = getName();
		arr[1] = 1;
		arr[2] = getBaseAmount();
		arr[3] = getMeasurement();
		arr[4] = getCalories();
			
		return arr;
	}
	
	//write ingredient to .TXT file
	public void saveToTXT() {
		File file = new File(getName() + ".txt");
		try { 
			PrintWriter out = new PrintWriter("Ingredients/" + file.getName());
			out.write(toString());
			out.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("error wrtiting ingredient to .txt");
			e.printStackTrace();
		}
	}
	
	//print ingredient name
	public String toString() {
		return getName() + "," + getType() + "," + getBaseAmount() + "," + getMeasurement()
		 + "," + getCalories() + "," + getTotalFat() + "," + getSatFat()
		 + "," + getTransFat() + "," + getPolyFat() + "," + getMonoFat()
		 + "," + getSodium() + "," + getCarbs() + "," + getFiber()
		 + "," + getSugar() + "," + getProtein() + "," + getPrice() + "\n";
	}
	
	//main method for testing
	public static void main (String [] args) {
		
		Ingredient i = new Ingredient(new File("Ingredients/Chicken.txt"));
		System.out.println(i.getName());
		System.out.println(i.getType());
		System.out.println(i);
		System.out.println(i.getFile());
	}
	
}
