import java.io.*;
import java.util.*;

@SuppressWarnings("resource")
public class Recipe {
	
	//initialize variables for recipe
	private String name;
	private String mealType;
	private double calories = 0;
	private double totalFat = 0;
	private double satFat = 0;
	private double transFat = 0;
	private double polyFat = 0;
	private double monoFat = 0;
	private double carbs = 0;
	private double fiber = 0;
	private double sugar = 0;
	private double protein = 0;
	private double sodium = 0;
	private double price = 0;
	protected List<Double> multipliers = new ArrayList<Double>();
	protected ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	
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
	public double getProtein() { return this.protein; }
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
	public void setProtien(int n) { this.protein = n; }
	public void setPrice(double n) { this.price = n; }
	public void setMultipliers(String s) {
		Scanner in = new Scanner(s).useDelimiter(",");
		while (in.hasNextDouble()) { multipliers.add(in.nextDouble()); }
		in.close();
	}
	
	public void updateRecipe() {
		for (int n = 0; n < ingredients.size(); n++) {
			double multi = multipliers.get(n);
			Ingredient i = ingredients.get(n);
			this.calories += (i.getCalories() * multi);
			this.totalFat += (i.getTotalFat() * multi);
			this.carbs += (i.getCarbs() * multi);
			this.protein += (i.getProtein() * multi);
			this.transFat += (i.getTransFat() * multi);
			this.polyFat += (i.getPolyFat() * multi);
			this.fiber += (i.getFiber() * multi);
			this.sodium += (i.getSodium() * multi);
			this.satFat += (i.getSatFat() * multi);
			this.monoFat += (i.getMonoFat() * multi);
			this.sugar += (i.getSugar() * multi);
			this.price += (i.getPrice() * multi);
		}
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
