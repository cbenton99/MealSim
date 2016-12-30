import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	//initialize GUI variables
	private JFrame frame = new JFrame();
	private JTabbedPane tabs = new JTabbedPane();
	
	//initialize "database" (populate data)
	protected static Client client = new Client();
	
	//window dimensions
	private int F_WIDTH = 1600;
	private int F_HEIGHT = 1100;
	//container dimensions
	protected static Dimension STANDARD_SINGLE = new Dimension(100,20);
	protected static Dimension STANDARD_WIDE = new Dimension(100,20);
	protected static Dimension STANDARD_TALL = new Dimension(100,20);
	protected static Dimension STANDARD_NARROW = new Dimension(100,20);
	protected static Dimension STANDARD_SLIDER = new Dimension(100,20);
	protected static Dimension SLIDER_LABEL = new Dimension(100,20);
	protected static Dimension PIE_CHART = new Dimension(100,20);
	protected static Dimension ALT_SINGLE = new Dimension(100,20);
	protected static Dimension ALT_TALL = new Dimension(100,20);
	protected static Dimension ALT_NARROW = new Dimension(100,20);
	
	//colors and images
	protected static Color RED = new Color(150,45,45);
	//protected static Color YELLOW = new Color(210,195,125);
	//protected static Color GREEN = new Color(80,170,95);
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
		
		MealTab mt = new MealTab();
		tabs.add("Meals", mt.getMealTab());
		
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
