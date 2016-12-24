import java.io.*;
import java.util.*;

@SuppressWarnings("resource")
public class Meal {
	
	//initialize variables for meal
	private String name;
	private String type;
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	//void constructor
	public Meal() { }
	
	//create Meal object from file
	public Meal(File file) { mealFromFile(file); }
	
	//create Meal object from a string
	public Meal(String fileName) {
		File file = new File("Meals/" + fileName + ".txt");
		mealFromFile(file);
	}
	
	//open and scan specified file
	//to create Meal object
	public void mealFromFile(File file) {
		Scanner in = null;
		try { in = new Scanner(file).useDelimiter(","); }
		catch (FileNotFoundException e) {
			System.out.println("Error finding meal:" + file.getName());
			e.printStackTrace();
		}
		setName(in.nextLine());
		setType(in.nextLine());
		while(in.hasNextLine()) {
			Recipe r = new Recipe(in.nextLine());
			recipes.add(r);
		}
		in.close();
	}
	
	//////////////////
	//getter methods//
	//////////////////
	public String getName() { return this.name; }
	public String getType() { return this.type; }
	public ArrayList<Recipe> getRecipes() { return this.recipes; }
	
	//////////////////
	//setter methods//
	//////////////////
	public void setName(String s) { this.name = s; }
	public void setType(String s) { this.type = s; }
	
	//write meal to .TXT file
	public void saveToTXT() {
		File file = new File(getName() + ".txt");
		try { 
			PrintWriter out = new PrintWriter("Meals/" + file);
			out.write(toString());
			out.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("error wrtiting meal to .txt");
			e.printStackTrace();
		}
	}
	
	//print meal name
	public String toString() {
		return getName();
	}
	
	//main method for testing
	public static void main (String [] args) {
		
		Meal m = new Meal(new File("Meals/SpaghettiDinner.txt"));
		System.out.println(m.getName());
		System.out.println(m.getType());
		System.out.println(m.getRecipes());
		
	}
	
}
