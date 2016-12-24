import java.awt.Color;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	//initialize GUI variables
	private JFrame frame = new JFrame();
	private JTabbedPane tabs = new JTabbedPane();
	
	//initialize "database" (populate data)
	protected static Client client= new Client();;
	
	//window dimensions
	private int F_WIDTH = 1500;
	private int F_HEIGHT = 1000;
	
	protected static Color pink = new Color(255,180,180);
	//protected static Color yellow = new Color(255,255,180);
	protected static ImageIcon leftArrow = new ImageIcon("leftArrow.png");
	protected static ImageIcon rightArrow = new ImageIcon("rightArrow.png");
	
	//void constructor for GUI
	public GUI() {
		buildFrame();
		buildTabs();
		frame.add(tabs);
	    frame.setVisible(true);
	}
	
	public void buildFrame() {
		frame.setSize(F_WIDTH,F_HEIGHT);
	    frame.setTitle("Meal Planner");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(true);
	}
	
	public void buildTabs() {
	    IngredientTab it = new IngredientTab();
	    tabs.add("Ingredients", it.getIngredientTab());
	    
		RecipeTab rt = new RecipeTab();
		tabs.add("Recipes", rt.getRecipeTab());
		
		//MealTab mt = new MealTab();
		//tabs.add("Meals", mt.getMealTab());
		
		DayTab dt = new DayTab();
		tabs.add("Day Plan", dt.getDayTab());
		
		WeekTab wt = new WeekTab();
		tabs.add("Week Plan", wt.getWeekTab());
		
		GoalTab gt = new GoalTab();
		tabs.add("Goal/Targets", gt.getGoalTab());
	}
	
	@SuppressWarnings("unused")
	public static void main(String [] args) {
		
		GUI g = new GUI();
		
	}
	
}
