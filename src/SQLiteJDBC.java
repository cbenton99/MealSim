/////////////////////////////////////////
//Database setup script
//Run this class to create the database
//and populate it with csv files
////////////////////////////////////////
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class SQLiteJDBC
{
  public static void main( String args[] )
  {
	//create or connect to the db
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:mealSimDB.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
    
    //add our tables
    Statement stmt = null;
    String sql = null;
    try {
      Class.forName("org.sqlite.JDBC");
      
      //Ingredients table
      stmt = c.createStatement();
      sql = "CREATE TABLE INGREDIENT " +
            "(ID INTEGER PRIMARY KEY 	 AUTOINCREMENT," +
            " NAME           TEXT    NOT NULL, " +
            " TYPE           TEXT    NOT NULL, " +
            " BASEAMOUNT     REAL    NOT NULL, " + 
            " MEASUREMENT    TEXT    NOT NULL, " +
            " CALORIES       REAL    NOT NULL, " +
            " TOTALFAT       REAL    NOT NULL, " +
            " SATURATEDFAT   REAL    NOT NULL, " +
            " TRANSFAT       REAL    NOT NULL, " +
            " POLYFAT        REAL    NOT NULL, " +
            " MONOFAT        REAL    NOT NULL, " +
            " SODIUM         REAL    NOT NULL, " +
            " CARBS          REAL    NOT NULL, " +
            " FIBER          REAL    NOT NULL, " +
            " SUGAR          REAL    NOT NULL, " +
            " PROTEIN        REAL    NOT NULL) ";
      stmt.executeUpdate(sql);
      stmt.close();
      
      //RecipeIngredients table 
      //intermediate table
      stmt = c.createStatement();
      sql = "CREATE TABLE RECIPEINGREDIENTS " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " MULTIPLIER     REAL    NOT NULL, " +
            " RECIPEID       INT     NOT NULL, " + 
            " INGREDIENTID   INT     NOT NULL, " +
            " FOREIGN KEY(RECIPEID) REFERENCES RECIPE(ID)," +
            " FOREIGN KEY(INGREDIENTID) REFERENCES INGREDIENTS(ID)) ";
      stmt.executeUpdate(sql);
      stmt.close();
      
      //Recipes table
      stmt = c.createStatement();
      sql = "CREATE TABLE RECIPE " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " MEALTYPE       TEXT    NOT NULL, " +
            " NOTES          TEXT) ";
      stmt.executeUpdate(sql);
      stmt.close();
      
      //Meals table
      stmt = c.createStatement();
      sql = "CREATE TABLE MEAL " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " RECIPEID       INT     NOT NULL, " +
            " MEALTYPE       TEXT, " +
            " FOREIGN KEY(RECIPEID) REFERENCES RECIPE(ID)) ";
      stmt.executeUpdate(sql);
      stmt.close();
      
      //DayMeals table
      //intermediate table
      stmt = c.createStatement();
      sql = "CREATE TABLE RECIPEINGREDIENTS " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " DAYID          INT     NOT NULL, " + 
            " MEALID         INT     NOT NULL, " +
            " FOREIGN KEY(DAYID)     REFERENCES DAY(ID)," +
            " FOREIGN KEY(MEALID)    REFERENCES MEAL(ID)) ";
      
      //Days table
      stmt = c.createStatement();
      sql = "CREATE TABLE DAY " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " DAYOFWEEK      INT     NOT NULL, " +
            " DATE           DATE    NOT NULL) ";
      stmt.executeUpdate(sql);
      stmt.close();
      
      //WeekDays table
      //intermediate table
      stmt = c.createStatement();
      sql = "CREATE TABLE WEEKDAYS " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " DAYID          INT     NOT NULL, " + 
            " WEEKID         INT     NOT NULL, " +
            " FOREIGN KEY(DAYID)     REFERENCES DAY(ID)," +
            " FOREIGN KEY(WEEKID)    REFERENCES WEEK(ID)) ";
      
      //Week table
      stmt = c.createStatement();
      sql = "CREATE TABLE WEEK " +
            "(ID INT PRIMARY KEY     NOT NULL) ";
      stmt.executeUpdate(sql);
      stmt.close();
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Tables created successfully");
    try {
		    //pull the ingredients out of the CSV
		    BufferedReader br = new BufferedReader(new FileReader("Ingredients.csv"));
		    String line;
		    while ( (line = br.readLine()) != null)
		    {
		    	stmt = c.createStatement();
		        String[] values = line.split(",");
		        //Convert String to right type. Integer, double, date etc.
		        stmt.executeUpdate("INSERT INTO INGREDIENT" 
		        					+ "(NAME, TYPE, BASEAMOUNT, MEASUREMENT, CALORIES,"
		        					+ "TOTALFAT, SATURATEDFAT, TRANSFAT, POLYFAT, MONOFAT, "
		        					+ "SODIUM, CARBS, FIBER, SUGAR, PROTEIN)" 
		        					+ " VALUES('"
		        					+ values[0] + "','" + values[1] + "'," + values[2]
		        					+ ",'" + values[3] + "'," + values[4]
		        					+ "," + values[5] + "," + values[6]
		        					+ "," + values[7] + "," + values[8]
		        					+ "," + values[9] + "," + values[10]
		        					+ "," + values[11] + "," + values[12]
		        					+ "," + values[13] + "," + values[14] 
		        					+ ")");
		        //Use a PeparedStatemant, it´s easier and safer 
		    }
		    br.close();
		    c.close();
    	} catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	    }
    
    	System.out.println("CSV inserted into tables successfully.");
  }
}
