import java.io.*;
import java.util.*;

public class Client {
	
	//initialize variables for client
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	private ArrayList<Meal> meals = new ArrayList<Meal>();
	Recipe currentRecipe = new Recipe();
	private PersonalTarget pt;
	
	//create "database" of ingredients/recipes/meals/etc
	//by reading from .txt files and creating objects
	public Client() {
		buildIngredients();
		buildRecipes();
		buildMeals();
		buildPersonalTarget();
	}
	
	//////////////////
	////Ingredients///
	//////////////////
	public void buildIngredients() {
		//open directory to read ingredients from
		File ingredientDir = new File("Ingredients/");
		File[] ingredientList = ingredientDir.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return !name.equals(".DS_Store");
	        }
	    });
		//create a Recipe object from each file and
		//add it to (ArrayList) recipes 
		for (File ingredientFile : ingredientList) {
			if (ingredientFile.isFile()) {
				Ingredient i = new Ingredient(ingredientFile);
				ingredients.add(i);
			}
		}
	}

	public Ingredient getIngredientsIndex(int n) { return ingredients.get(n); }
	public int getIngredientsLength() { return ingredients.size(); }
	public ArrayList<Ingredient> getIngredients() { return ingredients; }
	
	public void addIngredient(Ingredient i) {
		ingredients.add(i);
		i.saveToTXT();
	}
	
	public void removeIngredient(Ingredient i) {
		ingredients.remove(i);
		File file = new File("Ingredients/" + i.getFile().getName());
		file.delete();
	}
	
	public void updateIngredient(Ingredient i) {
		File file = new File("Ingredients/" + i.getFile());
		file.delete();
		i.saveToTXT();		
	}
	
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
	
	public Object[][] getLongList() { 
		Object[][] arr = new Object[ingredients.size()][];
		for(int i=0; i<ingredients.size(); i++) {
			arr[i] = ingredients.get(i).toRecipeArray();
		}
		return arr;
	}
	
	public Object[][] getCaloriesList() { 
		Object[][] arr = new Object[ingredients.size()][];
		for(int i=0; i<ingredients.size(); i++) {
			arr[i] = ingredients.get(i).toCaloriesArray();
		}
		return arr;
	}
	
	//////////////////
	//////Recipes/////
	//////////////////
	public void buildRecipes() {
		//open directory to read recipes from
		File recipeDir = new File("Recipes/");
		File[] recipeList = recipeDir.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return !name.equals(".DS_Store");
	        }
	    });
		//create a Recipe object from each file and
		//add it to (ArrayList) recipes 
		for (File recipeFile : recipeList) {
			if (recipeFile.isFile()) {
				Recipe r = new Recipe(recipeFile);
				recipes.add(r);
			}
		}
	}
	
	public ArrayList<Recipe> getRecipes() { return recipes; }
	public void addIngredientToRecipe(Ingredient i) { currentRecipe.getIngredients().add(i); }
	public int getCurrentRecipeLength() { return currentRecipe.ingredients.size(); }
	public Ingredient getIngredientFromRecipe(int n) { return currentRecipe.ingredients.get(n); }
	public void removeIngredientFromRecipe(Ingredient i) { currentRecipe.ingredients.remove(i); }
	
	public void addRecipe(Recipe r) { recipes.add(r); }
	public void removeRecipe(Recipe r) { ingredients.remove(r); }
	public void updateRecipe(Recipe r) {
		if (!recipes.contains(r)) { recipes.add(r); }
		else { recipes.set(recipes.indexOf(r), r); }
	}
	
	//////////////////
	///////Meals//////
	//////////////////
	public void buildMeals() {
		//open directory to read meals from
		File mealDir = new File("Meals/");
		File[] mealList = mealDir.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return !name.equals(".DS_Store");
	        }
	    });
		//create a Meal object from each file and
		//add it to (ArrayList) meals 
		for (File mealFile : mealList) {
			if (mealFile.isFile()) {
				Meal m = new Meal(mealFile);
				meals.add(m);
			}
		}
	}
	
	public ArrayList<Meal> getMeals() { return meals; }
	
	//////////////////
	//PersonalTarget//
	//////////////////
	public void buildPersonalTarget() { }
    
	public PersonalTarget getPersonalTarget() { return pt; }
	public void getBMR() {
		
	}	
	
	public static void main(String [] args) {
		
		Client c = new Client();

		System.out.println("Ingredients:");
		System.out.println(c.getIngredients());
		
		System.out.println("Recipes:");
		System.out.println(c.getRecipes());
		
		System.out.println("Meals:");
		System.out.println(c.getMeals());
		
	}
	
}
