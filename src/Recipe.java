import java.util.*;

public class Recipe {
	
	private String name;
	private String mealType;
	private int calories;
	private double totalFat;
	private double satFat;
	private double transFat;
	private double polyFat;
	private double monoFat;
	private int carbs;
	private int fiber;
	private int sugar;
	private int protien;
	private int sodium;
	private double price;
	private List<Integer> multipliers = new ArrayList<>();
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

	public Recipe() { 
		/*
		File ingredientFile = new File("Ingredients.csv");
		Scanner in = null;
		try { in = new Scanner(ingredientFile);
				in.nextLine(); }
		catch (FileNotFoundException e) {
			System.out.println("Error finding ingredient file");
			e.printStackTrace();
		}
		
		while (in.hasNextLine()) {
			String s = in.nextLine();
			Ingredient i = new Ingredient(s);
			ingredients.add(i);
		}
		*/
	}
	
	
	//////////////////
	//getter methods//
	//////////////////
	public String getName() { return this.name; }
	public String getMealType() { return this.mealType; }
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
	
	public Object[][] getRecipeList() { 
		Object[][] arr = new Object[ingredients.size()][];
		for(int i=0; i<ingredients.size(); i++) {
			arr[i] = ingredients.get(i).toRecipeArray();
		}
		return arr;
	}
	

}
