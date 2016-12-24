import java.io.*;
import java.util.*;

@SuppressWarnings("resource")
public class Recipe {
	
	//initialize variables for recipe
	private String name;
	private String mealType;
	private double calories;
	private double totalFat;
	private double satFat;
	private double transFat;
	private double polyFat;
	private double monoFat;
	private double carbs;
	private double fiber;
	private double sugar;
	private double protien;
	private double sodium;
	private double price;
	private List<Double> multipliers = new ArrayList<>();
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	//void constructor
	public Recipe() { }
	
	//create Recipe object from file
	public Recipe(File file) { recipeFromFile(file); }
	
	//create Recipe object from a string
	public Recipe(String fileName) {
		File file = new File("Recipes/" + fileName + ".txt");
		recipeFromFile(file);
	}
	
	//open specified file and scan first line
	//to pass to createIngredient
	public void recipeFromFile(File file) {
		Scanner in = null;
		try { in = new Scanner(file).useDelimiter(","); }
		catch (FileNotFoundException e) {
			System.out.println("Error finding recipe:" + file.getName());
			e.printStackTrace();
		}
		setName(in.nextLine());
		setMealType(in.nextLine());
		setMultipliers(in.nextLine());
		while(in.hasNextLine()) {
			Ingredient i = new Ingredient(in.nextLine());
			ingredients.add(i);
		}
		in.close();
	}
	
	//////////////////
	//getter methods//
	//////////////////
	public String getName() { return this.name; }
	public String getMealType() { return this.mealType; }
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
	public double getProtien() { return this.protien; }
	public double getPrice() { return this.price; }
	public double getMultiplier(int n) { return this.multipliers.get(n); }
	public List<Double> getMultipliers() { return this.multipliers; }
	public ArrayList<Ingredient> getIngredients() { return this.ingredients; }
	
	//////////////////
	//setter methods//
	//////////////////
	public void setName(String s) { this.name = s; }
	public void setMealType(String s) { this.mealType = s; }
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
	public void setMultipliers(String s) {
		Scanner in = new Scanner(s).useDelimiter(",");
		while (in.hasNextDouble()) { multipliers.add(in.nextDouble()); }
		in.close();
	}
	
	//write recipe to .TXT file
	public void saveToTXT() {
		File file = new File(getName() + ".txt");
		try { 
			PrintWriter out = new PrintWriter("Recipes/" + file);
			out.write(toString());
			out.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("error wrtiting recipe to .txt");
			e.printStackTrace();
		}
	}
	
	//print ingredient name
	public String toString() {
		return getName();
	}
	
	//main method for testing
	public static void main (String [] args) {
		
		Recipe r = new Recipe(new File("Recipes/Spaghetti.txt"));
		System.out.println(r.getName());
		System.out.println(r.getMealType());
		System.out.println(r.getMultipliers());
		System.out.println(r.getIngredients());
		
	}
	
}
