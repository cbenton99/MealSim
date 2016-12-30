import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class RecipeTab {
	
	JPanel recipeTabPanel = new JPanel();
	
	private JTextField recipeName, recipeCalories, recipeTotalFat,
	   				   recipeTransFat, recipeSatFat, recipeMonoFat,
	   				   recipePolyFat, recipeCarbs, recipeFiber,
	   				   recipeSugar, recipeProtein, recipeSodium,
	   				   recipePrice;
	@SuppressWarnings("rawtypes")
	private JComboBox recipeType;
	private JButton recipeNew, recipeAdd, recipeRemove, recipeLoad, recipeSubmit;
	private DefaultTableModel recipeShortModel;
	private DefaultTableModel recipeLongModel;
	private JTable recipeShortList, recipeLongList;
	
	private Object[] recipeShortHeaders = new Object[2];
	private Object[] recipeLongHeaders = new Object[5];
	
	public RecipeTab() { buildRecipeTab(this.recipeTabPanel); }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void buildRecipeTab(Container rTab) {
		//initialize layout and constraints
		rTab.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		
		
		recipeShortHeaders[0] = "Name";
		recipeShortHeaders[1] = "Type";
		recipeLongHeaders[0] = "Name";
		recipeLongHeaders[1] = "Multiplier";
		recipeLongHeaders[2] = "Base Amount";
		recipeLongHeaders[3] = "Measurement";
		recipeLongHeaders[4] = "Calories";
		
		
		
		//recipeShortList
		//display ingredients in JTable
		recipeShortModel = new DefaultTableModel(GUI.client.getShortList(), recipeShortHeaders);
		recipeShortList = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
    		recipeShortList.setModel(recipeShortModel);
		TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<DefaultTableModel>(recipeShortModel);
		recipeShortList.setRowSorter(sorted);
		recipeShortList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recipeShortList.getColumnModel().getColumn(0).setPreferredWidth(100);
		recipeShortList.getColumnModel().getColumn(1).setPreferredWidth(70);
		recipeShortList.setPreferredScrollableViewportSize(new Dimension(170,360));
		c.gridwidth = 1;
		c.gridheight = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		JScrollPane scroll = new JScrollPane(recipeShortList);
		
		rTab.add(scroll, c);
		
		//recipeNew button
		recipeNew = new JButton("<html><font size=5><b>NEW</b></font></html>");
		recipeNew.addActionListener(new rTabNewListener());
		recipeNew.setHorizontalAlignment(JTextField.CENTER);
		recipeNew.setPreferredSize(GUI.STANDARD_SINGLE);
		recipeNew.setEnabled(true);
	    c.gridwidth = 1;
		c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 0;
	    rTab.add(recipeNew, c);
		
		//recipeAdd button
	    recipeAdd = new JButton(GUI.rightArrow);
	    recipeAdd.addActionListener(new rTabAddListener());
	    recipeAdd.setHorizontalAlignment(JTextField.CENTER);
	    recipeAdd.setPreferredSize(GUI.STANDARD_SINGLE);
	    recipeAdd.setEnabled(true);
	    c.gridx = 1;
	    c.gridy = 1;
	    rTab.add(recipeAdd, c);
	    
		//recipeRemove button
	    recipeRemove = new JButton(GUI.leftArrow);
	    recipeRemove.addActionListener(new rTabDeleteListener());
	    recipeRemove.setHorizontalAlignment(JTextField.CENTER);
	    recipeRemove.setPreferredSize(GUI.STANDARD_SINGLE);
	    recipeRemove.setEnabled(true);
	    c.gridx = 1;
	    c.gridy = 2;
	    rTab.add(recipeRemove, c);
	    
		//recipeLoad button
	    recipeLoad = new JButton("<html><font size=5><b>LOAD</b></font></html>");
	    recipeLoad.addActionListener(new rTabLoadListener());
	    recipeLoad.setHorizontalAlignment(JTextField.CENTER);
	    recipeLoad.setPreferredSize(GUI.STANDARD_SINGLE);
	    recipeLoad.setEnabled(true);
	    c.gridx = 1;
	    c.gridy = 3;
	    rTab.add(recipeLoad, c);
	    
		//recipeRecipeList
		//display ingredients in JTable
	    recipeLongModel = new DefaultTableModel(null, recipeLongHeaders);
		recipeLongList = new JTable() {
    				public boolean isCellEditable(int row, int column) {
    					if (column == 1) { return true; }
    					else { return false; }
    				};
		};
    		recipeLongList.setModel(recipeLongModel);
		TableRowSorter<DefaultTableModel> sorted2 = new TableRowSorter<DefaultTableModel>(recipeLongModel);
		recipeLongList.setRowSorter(sorted2);
		recipeLongList.addKeyListener(new rTabKeyListener());
		recipeLongList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recipeLongList.getColumnModel().getColumn(0).setPreferredWidth(100);
		recipeLongList.getColumnModel().getColumn(1).setPreferredWidth(70);
		recipeLongList.setPreferredScrollableViewportSize(new Dimension(170,360));
		c.gridwidth = 3;
		c.gridheight = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		JScrollPane scroll2 = new JScrollPane(recipeLongList);
		
		rTab.add(scroll2, c);
		
		//recipeName field
		recipeName = new JTextField();
		recipeName.setBorder(BorderFactory.createTitledBorder("Recipe Name"));
		recipeName.setEditable(true);
		recipeName.setHorizontalAlignment(JTextField.CENTER);
	    recipeName.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridwidth = 1;
		c.gridheight = 1;
	    c.gridx = 0;
	    c.gridy = 4;
	    rTab.add(recipeName, c);
	    
		//recipeType field
	    String[] recipeTypes = { "Any", "Breakfast", "Lunch", "Dinner", "Snack" };
	    recipeType = new JComboBox(recipeTypes);
	    recipeType.setBorder(BorderFactory.createTitledBorder("Type"));
	    recipeType.setEditable(false);
	    recipeType.setSelectedIndex(-1);
	    recipeType.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 0;
	    c.gridy = 5;
	    rTab.add(recipeType, c);
	    
		//recipeSubmit button
	    recipeSubmit = new JButton("<html><font size=5><b>SUBMIT</b></font></html>");
	    recipeSubmit.addActionListener(new rTabSubmitListener());
	    recipeSubmit.setHorizontalAlignment(JTextField.CENTER);
	    recipeSubmit.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 0;
	    c.gridy = 6;
	    rTab.add(recipeSubmit, c);
	    
		//recipeCalories field
	    recipeCalories = new JTextField();
	    recipeCalories.setBorder(BorderFactory.createTitledBorder("Calories"));
	    recipeCalories.setEditable(false);
	    recipeCalories.setHorizontalAlignment(JTextField.CENTER);
	    recipeCalories.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 1;
	    c.gridy = 4;
	    rTab.add(recipeCalories, c);
	    
		//recipeTransFat field
	    recipeTransFat = new JTextField();
	    recipeTransFat.setBorder(BorderFactory.createTitledBorder("Trans Fat (g)"));
	    recipeTransFat.setEditable(false);
	    recipeTransFat.setHorizontalAlignment(JTextField.CENTER);
	    recipeTransFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 1;
	    c.gridy = 5;
	    rTab.add(recipeTransFat, c);
	    
		//recipeSatFat field
	    recipeSatFat = new JTextField();
	    recipeSatFat.setBorder(BorderFactory.createTitledBorder("Saturated Fat (g)"));
	    recipeSatFat.setEditable(false);
	    recipeSatFat.setHorizontalAlignment(JTextField.CENTER);
	    recipeSatFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 1;
	    c.gridy = 6;
	    rTab.add(recipeSatFat, c);
	    
		//recipeTotalFat field
	    recipeTotalFat = new JTextField();
	    recipeTotalFat.setBorder(BorderFactory.createTitledBorder("Total Fat (g)"));
	    recipeTotalFat.setEditable(false);
	    recipeTotalFat.setHorizontalAlignment(JTextField.CENTER);
	    recipeTotalFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 2;
	    c.gridy = 4;
	    rTab.add(recipeTotalFat, c);
	    
		//recipePolyFat field
	    recipePolyFat = new JTextField();
	    recipePolyFat.setBorder(BorderFactory.createTitledBorder("Polyunsaturated Fat (g)"));
	    recipePolyFat.setEditable(false);
	    recipePolyFat.setHorizontalAlignment(JTextField.CENTER);
	    recipePolyFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 2;
	    c.gridy = 5;
	    rTab.add(recipePolyFat, c);
	    
		//recipeMonoFat field
	    recipeMonoFat = new JTextField();
	    recipeMonoFat.setBorder(BorderFactory.createTitledBorder("Monosaturated Fat (g)"));
	    recipeMonoFat.setEditable(false);
	    recipeMonoFat.setHorizontalAlignment(JTextField.CENTER);
	    recipeMonoFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 2;
	    c.gridy = 6;
	    rTab.add(recipeMonoFat, c);
	    
		//recipeCarbs field
	    recipeCarbs = new JTextField();
	    recipeCarbs.setBorder(BorderFactory.createTitledBorder("Carbs (g)"));
	    recipeCarbs.setEditable(false);
	    recipeCarbs.setHorizontalAlignment(JTextField.CENTER);
	    recipeCarbs.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 3;
	    c.gridy = 4;
	    rTab.add(recipeCarbs, c);
	    
		//recipeFiber field
	    recipeFiber = new JTextField();
	    recipeFiber.setBorder(BorderFactory.createTitledBorder("Fiber (g)"));
	    recipeFiber.setEditable(false);
	    recipeFiber.setHorizontalAlignment(JTextField.CENTER);
	    recipeFiber.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 3;
	    c.gridy = 5;
	    rTab.add(recipeFiber, c);
	    
		//recipeSugar field
	    recipeSugar = new JTextField();
	    recipeSugar.setBorder(BorderFactory.createTitledBorder("Sugar (g)"));
	    recipeSugar.setEditable(false);
	    recipeSugar.setHorizontalAlignment(JTextField.CENTER);
	    recipeSugar.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 3;
	    c.gridy = 6;
	    rTab.add(recipeSugar, c);
	    
		//recipeProtein field
	    recipeProtein = new JTextField();
	    recipeProtein.setBorder(BorderFactory.createTitledBorder("Protein (g)"));
	    recipeProtein.setEditable(false);
	    recipeProtein.setHorizontalAlignment(JTextField.CENTER);
	    recipeProtein.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 4;
	    c.gridy = 4;
	    rTab.add(recipeProtein, c);
	    
		//recipeSodium field
	    recipeSodium = new JTextField();
	    recipeSodium.setBorder(BorderFactory.createTitledBorder("Sodium (mg)"));
	    recipeSodium.setEditable(false);
	    recipeSodium.setHorizontalAlignment(JTextField.CENTER);
	    recipeSodium.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 4;
	    c.gridy = 5;
	    rTab.add(recipeSodium, c);
	    
		//recipePrice field
	    recipePrice = new JTextField();
	    recipePrice.setBorder(BorderFactory.createTitledBorder("Price per serving"));
	    recipePrice.setEditable(false);
	    recipePrice.setHorizontalAlignment(JTextField.CENTER);
	    recipePrice.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 4;
	    c.gridy = 6;
	    rTab.add(recipePrice, c);
	    
	    clearRecipeTextFields();
	}
	
	//listener for when keyboard is used to change multipliers in rTab
	private class rTabKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) { }
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) {
			int row = recipeLongList.convertRowIndexToModel(recipeShortList.getSelectedRow());
			if (row >= 0) {
				//get multiplier from column 1 in selected row,
				//check string to make sure its a number, isNumeric()
				//set multiplier at selected index
				//setRecipeTextFields();
			}
		}
	}
	
	//create new recipe in rTab
	private class rTabNewListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//clear all fields and recipe list
			while (recipeLongModel.getRowCount() > 0) {
				recipeLongModel.removeRow(recipeLongModel.getRowCount() - 1);
			}
			recipeLongList.setModel(recipeLongModel);
			
			//create new recipe object
			GUI.client.currentRecipe = new Recipe();
		}	
	}
	
	//add ingredient to recipe list in rTab
	private class rTabAddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//get ingredient at selected index of shortlist
			int row = recipeShortList.convertRowIndexToModel(recipeShortList.getSelectedRow());
			//add ingredient to current recipe and longlist
			Ingredient i = GUI.client.getIngredientsIndex(row);
			GUI.client.addIngredientToRecipe(i);
			//update longlist
			recipeLongModel.addRow(i.toRecipeArray());
			recipeLongList.setModel(recipeLongModel);
			setRecipeTextFields();
		}	
	}
	
	//delete ingredient from recipe list in rTab
	private class rTabDeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int row = recipeLongList.getSelectedRow();
			int length = GUI.client.getCurrentRecipeLength();
			if (row >= 0) {
				int sorted = recipeLongList.convertRowIndexToModel(row);
				Ingredient i = GUI.client.getIngredientFromRecipe(sorted);
				GUI.client.removeIngredientFromRecipe(i);
				recipeLongModel.removeRow(sorted);
				//adjust row if deleting last row of list
				if (row == GUI.client.getCurrentRecipeLength()) { row--; }
				//set selected row to new row
				if (length > 1) { recipeLongList.setRowSelectionInterval(row, row); }
			}
			setRecipeTextFields();
		}	
	}
	
	//load ingredient to recipe list in rTab
	private class rTabLoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//jfile chooser to select recipe file in /Recipes/
			
			System.out.println("(load) recipe tab");
		}	
	}
	
	//create new recipe object from ingredients in longlist in rTab
	private class rTabSubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//jfile chooser to select recipe file in /Recipes/
		}	
	}
	
	public void setRecipeTextFields() {
		recipeCalories.setText(Double.toString(GUI.client.currentRecipe.getCalories()));
		recipeTotalFat.setText(Double.toString(GUI.client.currentRecipe.getTotalFat()));
		recipeTransFat.setText(Double.toString(GUI.client.currentRecipe.getTransFat()));
		recipeSatFat.setText(Double.toString(GUI.client.currentRecipe.getSatFat()));
		recipeMonoFat.setText(Double.toString(GUI.client.currentRecipe.getMonoFat()));
		recipePolyFat.setText(Double.toString(GUI.client.currentRecipe.getPolyFat()));
		recipeCarbs.setText(Double.toString(GUI.client.currentRecipe.getCarbs()));
		recipeFiber.setText(Double.toString(GUI.client.currentRecipe.getFiber()));
		recipeSugar.setText(Double.toString(GUI.client.currentRecipe.getSugar()));
		recipeProtein.setText(Double.toString(GUI.client.currentRecipe.getProtein()));
		recipeSodium.setText(Double.toString(GUI.client.currentRecipe.getSodium()));
		recipePrice.setText(Double.toString(GUI.client.currentRecipe.getPrice()));		
	}
	
	public void clearRecipeTextFields() {
		recipeCalories.setText("0");
		recipeTotalFat.setText("0");
		recipeTransFat.setText("0");
		recipeSatFat.setText("0");
		recipeMonoFat.setText("0");
		recipePolyFat.setText("0");
		recipeCarbs.setText("0");
		recipeFiber.setText("0");
		recipeSugar.setText("0");
		recipeProtein.setText("0");
		recipeSodium.setText("0");
		recipePrice.setText("0");		
	}
	
	public boolean isNumeric(String s) {  
		if (s.isEmpty()) { return false; }
	    return s.matches("[+]?\\d*\\.?\\d*");  
	}  
	
	public JPanel getRecipeTab() { return this.recipeTabPanel; }
}
