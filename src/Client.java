import java.io.*;
import java.util.*;

public class Client {
	
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	//create list of Ingredients from file
	public Client() {
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
		
		//create (ArrayList)recipes
	}
    
	//////////////////
	//getter methods//
	//////////////////
	public Ingredient getIngredientsIndex(int n) { return ingredients.get(n); }
	public int getIngredientsLength() { return ingredients.size(); }
	public ArrayList<Ingredient> getIngredientsArrayList() { return ingredients; }
	public ArrayList<Recipe> getRecipesArrayList() { return recipes; }
	
	public Object[][] getMacroList() { 
		Object[][] arr = new Object[ingredients.size()][];
		for(int i=0; i<ingredients.size(); i++) {
			arr[i] = ingredients.get(i).toMacroArray();
		}
		return arr;
	}
	
	public Object[][] getShortList() { 
		Object[][] arr = new Object[ingredients.size()][];
		for(int i=0; i<ingredients.size(); i++) {
			arr[i] = ingredients.get(i).toShortArray();
		}
		return arr;
	}
	

	
	public Recipe newRecipe() { return new Recipe(); }
	
	public void addIngredient(Ingredient i) {
		ingredients.add(i);
	}
	
	public void updateIngredient(Ingredient i) {
		if (!ingredients.contains(i)) { ingredients.add(i); }
		else { ingredients.set(ingredients.indexOf(i), i); }
	}
	
	public void removeIngredient(Ingredient i) {
		ingredients.remove(i);
	}
	
	public void display() {
		for (int i=1; i<ingredients.size(); i++) {
			System.out.println(ingredients.get(i));
		}
	}
	
	public static void main(String [] args) {
		
		@SuppressWarnings("unused")
		Client c = new Client();
		//c.display(); //test display all ingredients and their data
		
		/* test Object[][]
		for (int i=0; i<c.getIngredientList().length; i++) {
			System.out.println(c.getIngredientList()[i][1]);
		}
		*/
		
	}
	
}
