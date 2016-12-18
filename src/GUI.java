import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	//GUI variables
	private JFrame frame = new JFrame();
	private JTabbedPane tabs = new JTabbedPane();
	private JPanel ingredientTab, recipeTab, mealTab, dayTab, weekTab,goalTab;
	private JLabel meals, days, week, goal;
	private JTextField name, type, base, measure, calories,
					   fat, sodium, satFat, carbs, transFat,
					   fiber, polyFat, sugar, monoFat, protein;
	private JButton addIngredient, editIngredient, deleteIngredient, updateIngredient;
	private JButton newRecipe, addRecipe, removeRecipe, loadRecipe;
	private DefaultTableModel ingredientModel, recipeShortModel, recipeRecipeModel;
	private JTable ingredientList, recipeShortList, recipeRecipeList;
	
	
	//initialize "database" (populate data)
	Client client = new Client();
	
	//window dimensions
	private int F_WIDTH = 1500;
	private int F_HEIGHT = 1000;
	
	//headers
	private Object[] ingredientHeaders = new Object[6];
	private Object[] recipeShortHeaders = new Object[6];
	private Object[] recipeRecipeHeaders = new Object[6];
	
	//sets of text boxes
	private ArrayList<JTextField> ingredientTextFields = new ArrayList<JTextField>();
	
	private Color pink = new Color(255,180,180);
	//private Color yellow = new Color(255,255,180);
	
	//void constructor for GUI
	public GUI() {
		frame.setSize(F_WIDTH,F_HEIGHT);
	    frame.setTitle("Meal Planner");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(true);
	    
	    buildFrame(frame);
	    
	    frame.setVisible(true);
	}
	
	//initialize tabs and add tabs to frame
	private void buildFrame(Container pane) {
		ingredientTab = new JPanel();
		buildIngredientTab(ingredientTab);
		
		recipeTab = new JPanel();
		buildRecipeTab(recipeTab);
		
		mealTab = new JPanel();
		buildMealTab(mealTab);
		
		dayTab = new JPanel();
		buildDayTab(dayTab);
		
		weekTab = new JPanel();
		buildWeekTab(weekTab);
		
		goalTab = new JPanel();
		buildGoalTab(goalTab);
		
		tabs.add("Ingredients", ingredientTab);
		tabs.add("Recipes", recipeTab);
		tabs.add("Meals", mealTab);
		tabs.add("Day Plan", dayTab);
		tabs.add("Week Plan", weekTab);
		tabs.add("Goal Plan", goalTab);
		pane.add(tabs);
	}
	
	private void buildIngredientTab(Container iTab) {
		//initialize layout and constraints
		iTab.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		
		Dimension single = new Dimension(100,20);
		
		ingredientHeaders[0] = "Name";
		ingredientHeaders[1] = "Type";
		ingredientHeaders[2] = "Calories";
		ingredientHeaders[3] = "Fats";
		ingredientHeaders[4] = "Carbs";
		ingredientHeaders[5] = "Protien";
		
		
		
		//ingredientList
		//display ingredients in JTable
		ingredientModel = new DefaultTableModel(client.getMacroList(), ingredientHeaders);
    		ingredientList = new JTable() {
    				public boolean isCellEditable(int row, int column) {                
    					return false;               
    				};
    		};
		ingredientList.setModel(ingredientModel);
		TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(ingredientModel);
		ingredientList.setRowSorter(sort);
		ingredientList.addMouseListener(new IngredientListListener());
		ingredientList.addKeyListener(new IngredientListListener2());
		ingredientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ingredientList.getColumnModel().getColumn(0).setPreferredWidth(100);
		ingredientList.getColumnModel().getColumn(1).setPreferredWidth(70);
		ingredientList.getColumnModel().getColumn(2).setPreferredWidth(50);
		ingredientList.getColumnModel().getColumn(3).setPreferredWidth(50);
		ingredientList.getColumnModel().getColumn(4).setPreferredWidth(50);
		ingredientList.getColumnModel().getColumn(5).setPreferredWidth(50);
		ingredientList.setPreferredScrollableViewportSize(new Dimension(370,360));
		c.gridwidth = 1;
		c.gridheight = 11;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		JScrollPane scroll = new JScrollPane(ingredientList);
		
		iTab.add(scroll, c);
		
		//name field
	    name = new JTextField();
	    name.setBorder(BorderFactory.createTitledBorder("Name"));
	    name.setEditable(false);
	    name.setHorizontalAlignment(JTextField.CENTER);
	    name.setPreferredSize(new Dimension(200,20));
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 0;
	    iTab.add(name, c);
	    ingredientTextFields.add(name);
	    
		//type field
	    type = new JTextField();
	    type.setBorder(BorderFactory.createTitledBorder("Type"));
	    type.setEditable(false);
	    type.setHorizontalAlignment(JTextField.CENTER);
	    type.setPreferredSize(new Dimension(200,20));
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 1;
	    iTab.add(type, c);
	    ingredientTextFields.add(type);
	    
		//base field
	    base = new JTextField();
	    base.setBorder(BorderFactory.createTitledBorder("Base Amount"));
	    base.setEditable(false);
	    base.setHorizontalAlignment(JTextField.CENTER);
	    base.setPreferredSize(single);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 2;
	    iTab.add(base, c);
	    ingredientTextFields.add(base);
	    
		//measure field
	    measure = new JTextField();
	    measure.setBorder(BorderFactory.createTitledBorder("Measurement"));
	    measure.setEditable(false);
	    measure.setHorizontalAlignment(JTextField.CENTER);
	    measure.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 2;
	    iTab.add(measure, c);
	    ingredientTextFields.add(measure);
	    
		//calories field
	    calories = new JTextField();
	    calories.setBorder(BorderFactory.createTitledBorder("Calories"));
	    calories.setEditable(false);
	    calories.setHorizontalAlignment(JTextField.CENTER);
	    calories.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 3;
	    iTab.add(calories, c);
	    ingredientTextFields.add(calories);
	    
		//fat field
	    fat = new JTextField();
	    fat.setBorder(BorderFactory.createTitledBorder("Total Fat (g)"));
	    fat.setEditable(false);
	    fat.setHorizontalAlignment(JTextField.CENTER);
	    fat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 3;
	    iTab.add(fat, c);
	    ingredientTextFields.add(fat);
	    
		//sodium field
	    sodium = new JTextField();
	    sodium.setBorder(BorderFactory.createTitledBorder("Sodium (mg)"));
	    sodium.setEditable(false);
	    sodium.setHorizontalAlignment(JTextField.CENTER);
	    sodium.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 4;
	    iTab.add(sodium, c);
	    ingredientTextFields.add(sodium);
	    
		//satFat field
	    satFat = new JTextField();
	    satFat.setBorder(BorderFactory.createTitledBorder("Saturated Fat (g)"));
	    satFat.setEditable(false);
	    satFat.setHorizontalAlignment(JTextField.CENTER);
	    satFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 4;
	    iTab.add(satFat, c);
	    ingredientTextFields.add(satFat);
	    
		//carbs field
	    carbs = new JTextField();
	    carbs.setBorder(BorderFactory.createTitledBorder("Carboydrates (g)"));
	    carbs.setEditable(false);
	    carbs.setHorizontalAlignment(JTextField.CENTER);
	    carbs.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 5;
	    iTab.add(carbs, c);
	    ingredientTextFields.add(carbs);
	    
		//transFat field
	    transFat = new JTextField();
	    transFat.setBorder(BorderFactory.createTitledBorder("Trans Fat (g)"));
	    transFat.setEditable(false);
	    transFat.setHorizontalAlignment(JTextField.CENTER);
	    transFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 5;
	    iTab.add(transFat, c);
	    ingredientTextFields.add(transFat);
	    
		//fiber field
	    fiber = new JTextField();
	    fiber.setBorder(BorderFactory.createTitledBorder("Fiber (g)"));
	    fiber.setEditable(false);
	    fiber.setHorizontalAlignment(JTextField.CENTER);
	    fiber.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 6;
	    iTab.add(fiber, c);
	    ingredientTextFields.add(fiber);
	    
		//polyFat field
	    polyFat = new JTextField();
	    polyFat.setBorder(BorderFactory.createTitledBorder("Polyunsaturated Fat (g)"));
	    polyFat.setEditable(false);
	    polyFat.setHorizontalAlignment(JTextField.CENTER);
	    polyFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 6;
	    iTab.add(polyFat, c);
	    ingredientTextFields.add(polyFat);
	    
		//sugar field
	    sugar = new JTextField();
	    sugar.setBorder(BorderFactory.createTitledBorder("Sugar (g)"));
	    sugar.setEditable(false);
	    sugar.setHorizontalAlignment(JTextField.CENTER);
	    sugar.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 7;
	    iTab.add(sugar, c);
	    ingredientTextFields.add(sugar);
	    
		//monoFat field
	    monoFat = new JTextField();
	    monoFat.setBorder(BorderFactory.createTitledBorder("Monosaturated Fat (g)"));
	    monoFat.setEditable(false);
	    monoFat.setHorizontalAlignment(JTextField.CENTER);
	    monoFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 7;
	    iTab.add(monoFat, c);
	    ingredientTextFields.add(monoFat);
	    
		//protein field
	    protein = new JTextField();
	    protein.setBorder(BorderFactory.createTitledBorder("Protein (g)"));
	    protein.setEditable(false);
	    protein.setHorizontalAlignment(JTextField.CENTER);
	    protein.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 8;
	    iTab.add(protein, c);
	    ingredientTextFields.add(protein);
	    
		//addIngredient button
	    addIngredient = new JButton("<html><font size=5><b>ADD</b></font></html>");
	    addIngredient.addActionListener(new IngredientListener());
	    addIngredient.setHorizontalAlignment(JTextField.CENTER);
	    addIngredient.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 9;
	    iTab.add(addIngredient, c);
	    
		//editIngredient button
	    editIngredient = new JButton("<html><font size=5><b>EDIT</b></font></html>");
	    editIngredient.addActionListener(new IngredientListener());
	    editIngredient.setHorizontalAlignment(JTextField.CENTER);
	    editIngredient.setPreferredSize(single);
	    editIngredient.setEnabled(false);
	    c.gridx = 2;
	    c.gridy = 9;
	    iTab.add(editIngredient, c);
	    
		//deleteIngredient button
	    deleteIngredient = new JButton("<html><font size=5><b>DELETE</b></font></html>");
	    deleteIngredient.addActionListener(new IngredientListener());
	    deleteIngredient.setHorizontalAlignment(JTextField.CENTER);
	    deleteIngredient.setPreferredSize(single);
	    deleteIngredient.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 10;
	    iTab.add(deleteIngredient, c);
	    
		//updateIngredient button
	    updateIngredient = new JButton("<html><font size=5><b>UPDATE</b></font></html>");
	    updateIngredient.addActionListener(new IngredientListener());
	    updateIngredient.setHorizontalAlignment(JTextField.CENTER);
	    updateIngredient.setPreferredSize(single);
	    updateIngredient.setEnabled(false);
	    c.gridx = 2;
	    c.gridy = 10;
	    iTab.add(updateIngredient, c);
		
	}
	
	private void buildRecipeTab(Container rTab) {
		//initialize layout and constraints
		rTab.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		
		Dimension single = new Dimension(100,20);
		
		recipeShortHeaders[0] = "Name";
		recipeShortHeaders[1] = "Type";
		recipeRecipeHeaders[0] = "Name";
		recipeRecipeHeaders[1] = "Multiplier";
		recipeRecipeHeaders[2] = "Base Amount";
		recipeRecipeHeaders[3] = "Measurement";
		recipeRecipeHeaders[4] = "Calories";
		
		
		
		//recipeShortList
		//display ingredients in JTable
		recipeShortModel = new DefaultTableModel(client.getShortList(), recipeShortHeaders);
		recipeShortList = new JTable() {
    				public boolean isCellEditable(int row, int column) {                
    					return false;               
    				};
    		};
    		recipeShortList.setModel(recipeShortModel);
		TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(recipeShortModel);
		recipeShortList.setRowSorter(sort);
		recipeShortList.addMouseListener(new IngredientListListener());
		recipeShortList.addKeyListener(new IngredientListListener2());
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
		
		//deleteIngredient button
	    newRecipe = new JButton("<html><font size=5><b>NEW</b></font></html>");
	    newRecipe.addActionListener(new IngredientListener());
	    newRecipe.setHorizontalAlignment(JTextField.CENTER);
	    newRecipe.setPreferredSize(single);
	    newRecipe.setEnabled(false);
	    c.gridwidth = 1;
		c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 0;
	    rTab.add(newRecipe, c);
		
		//addIngredient button
	    addRecipe = new JButton("<html><font size=5><b>ADD</b></font></html>");
	    addRecipe.addActionListener(new IngredientListener());
	    addRecipe.setHorizontalAlignment(JTextField.CENTER);
	    addRecipe.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 1;
	    rTab.add(addRecipe, c);
	    
		//editIngredient button
	    removeRecipe = new JButton("<html><font size=5><b>EDIT</b></font></html>");
	    removeRecipe.addActionListener(new IngredientListener());
	    removeRecipe.setHorizontalAlignment(JTextField.CENTER);
	    removeRecipe.setPreferredSize(single);
	    removeRecipe.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 2;
	    rTab.add(removeRecipe, c);
	    
		//updateIngredient button
	    loadRecipe = new JButton("<html><font size=5><b>UPDATE</b></font></html>");
	    loadRecipe.addActionListener(new IngredientListener());
	    loadRecipe.setHorizontalAlignment(JTextField.CENTER);
	    loadRecipe.setPreferredSize(single);
	    loadRecipe.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 3;
	    rTab.add(loadRecipe, c);
	    
		//recipeRecipeList
		//display ingredients in JTable
	    recipeRecipeModel = new DefaultTableModel(client.getRecipeList(), recipeRecipeHeaders);
		recipeRecipeList = new JTable() {
    				public boolean isCellEditable(int row, int column) {                
    					return false;               
    				};
    		};
    		recipeRecipeList.setModel(recipeRecipeModel);
		TableRowSorter<DefaultTableModel> sort2 = new TableRowSorter<DefaultTableModel>(recipeRecipeModel);
		recipeRecipeList.setRowSorter(sort2);
		recipeRecipeList.addMouseListener(new IngredientListListener());
		recipeRecipeList.addKeyListener(new IngredientListListener2());
		recipeRecipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recipeRecipeList.getColumnModel().getColumn(0).setPreferredWidth(100);
		recipeRecipeList.getColumnModel().getColumn(1).setPreferredWidth(70);
		recipeRecipeList.setPreferredScrollableViewportSize(new Dimension(170,360));
		c.gridwidth = 1;
		c.gridheight = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		JScrollPane scroll2 = new JScrollPane(recipeRecipeList);
		
		rTab.add(scroll2, c);
		
		
	}
	
	private void buildMealTab(Container mTab) {
		meals = new JLabel("Meals");
		mTab.add(meals);
	}
	
	private void buildDayTab(Container dTab) {
		days = new JLabel("Days");
		dTab.add(days);
	}
	
	private void buildWeekTab(Container wTab) {
		week = new JLabel("Week");
		wTab.add(week);
	}
	
	private void buildGoalTab(Container gTab) {
		goal = new JLabel("Goal");
		gTab.add(goal);
	}
	
	//listener for when ingredient is clicked in iTab
	private class IngredientListListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (ingredientList.isEnabled()) {
				int row = ingredientList.convertRowIndexToModel(ingredientList.rowAtPoint(e.getPoint()));
				setIngredientTextFields(row);
				//System.out.println(row);  //use for testing
			if (ingredientList.getSelectedRow() >= 0) {
				addIngredient.setEnabled(true);
				deleteIngredient.setEnabled(true);
				editIngredient.setEnabled(true);
			}
			}
		}
		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }
		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) { }
	}
	
	//listener for when keyboard is used to change ingredient in iTab
	private class IngredientListListener2 implements KeyListener {
		public void keyTyped(KeyEvent e) { }
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) {
			int row = ingredientList.convertRowIndexToModel(ingredientList.getSelectedRow());
			setIngredientTextFields(row);
			//System.out.println(row); //use for testing
			if (ingredientList.getSelectedRow() >= 0) {
				addIngredient.setEnabled(true);
				deleteIngredient.setEnabled(true);
				editIngredient.setEnabled(true);
			}
		}
	}
	
	//listener for add/delete/edit/update buttons in iTab
	private class IngredientListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//add ingredient to ingredientList in iTab
			if (e.getSource() == addIngredient) {
				if (e.getSource().toString().contains("ADD")) {
					clearIngredientTextFields();
					deleteIngredient.setEnabled(false);
					editIngredient.setEnabled(false);
					ingredientList.setEnabled(false);
					//ingredientList.setCellSelectionEnabled(false);
					setIngredientEditable(true);
					addIngredient.setText("<html><font size = 5><b>SUBMIT</b></font></html>");
				}
				else if (e.getSource().toString().contains("SUBMIT")) {
					boolean canSubmit = validateIngredientSubmission();
					if (canSubmit) {
						Ingredient i = new Ingredient();
						i.setName(name.getText().trim());
						i.setType(type.getText().trim());
						i.setBaseAmount(Integer.parseInt(base.getText()));
						i.setMeasurement(measure.getText().trim());
						i.setCalories(Integer.parseInt(calories.getText()));
						i.setTotalFat(Double.parseDouble(fat.getText()));
						i.setSodium(Integer.parseInt(sodium.getText()));
						i.setSatFat(Double.parseDouble(satFat.getText()));
						i.setCarbs(Integer.parseInt(carbs.getText()));
						i.setTransFat(Double.parseDouble(transFat.getText()));
						i.setFiber(Integer.parseInt(fiber.getText()));
						i.setPolyFat(Double.parseDouble(polyFat.getText()));
						i.setSugar(Integer.parseInt(sugar.getText()));
						i.setMonoFat(Double.parseDouble(monoFat.getText()));
						i.setProtien(Integer.parseInt(protein.getText()));
						client.addIngredient(i);
						addIngredient.setText("<html><font size = 5><b>ADD</b></font></html>");
						ingredientModel.addRow(i.toMacroArray());
						ingredientList.setModel(ingredientModel);
						clearIngredientTextFields();
						ingredientList.setEnabled(true);
						editIngredient.setEnabled(true);
						deleteIngredient.setEnabled(true);
						setIngredientTextFields(ingredientList.convertRowIndexToModel(ingredientList.getSelectedRow()));
					}
				}
			}
			
			//delete ingredient from ingredientList in iTab
			else if (e.getSource() == deleteIngredient) {
				int row = ingredientList.getSelectedRow();
				int length = client.getIngredientsLength();
				if (row >= 0) {
					int sortedRow = ingredientList.convertRowIndexToModel(row);
					//System.out.println(row); //use for testing
					//System.out.println(sortedRow); //use for testing
					client.removeIngredient(client.getIngredientsIndex(sortedRow));
					ingredientModel.removeRow(sortedRow);
					//adjust row if deleting last row
					if (row == client.getIngredientsLength()) { row--; }
					//set selected row to new row
					if (length > 1) {
						ingredientList.setRowSelectionInterval(row, row);
						setIngredientTextFields(ingredientList.convertRowIndexToModel(ingredientList.getSelectedRow()));
					}
					//if deleting last ingredient, clear/disable
					//everything except addIngredient
					else if (length == 1) {
						clearIngredientTextFields();
						addIngredient.setEnabled(true);
						deleteIngredient.setEnabled(false);
						editIngredient.setEnabled(false);
					}
				}
			}
			
			//edit ingredient from ingredientList in iTab
			else if (e.getSource() == editIngredient) {
				ingredientList.setEnabled(false);
				setIngredientEditable(true);
				addIngredient.setEnabled(false);
				deleteIngredient.setEnabled(false);
				editIngredient.setEnabled(false);
				updateIngredient.setEnabled(true);
			}
			
			//update ingredient from ingredientList in iTab
			else if (e.getSource() == updateIngredient) {
				boolean canSubmit = validateIngredientSubmission();
				int row = ingredientList.getSelectedRow();
				int sorted = ingredientList.convertRowIndexToModel(row);
				if (canSubmit) {
					Ingredient i = client.getIngredientsIndex(sorted);
					i.setName(name.getText().trim());
					i.setType(type.getText().trim());
					i.setBaseAmount(Double.parseDouble(base.getText()));
					i.setMeasurement(measure.getText().trim());
					i.setCalories(Integer.parseInt(calories.getText()));
					i.setTotalFat(Double.parseDouble(fat.getText()));
					i.setSodium(Integer.parseInt(sodium.getText()));
					i.setSatFat(Double.parseDouble(satFat.getText()));
					i.setCarbs(Integer.parseInt(carbs.getText()));
					i.setTransFat(Double.parseDouble(transFat.getText()));
					i.setFiber(Integer.parseInt(fiber.getText()));
					i.setPolyFat(Double.parseDouble(polyFat.getText()));
					i.setSugar(Integer.parseInt(sugar.getText()));
					i.setMonoFat(Double.parseDouble(monoFat.getText()));
					i.setProtien(Integer.parseInt(protein.getText()));
					try { 
						client.updateIngredient(i); 
						updateIngredient.setEnabled(false);
						ingredientList.setEnabled(true);
						addIngredient.setEnabled(true);
						deleteIngredient.setEnabled(true);
						editIngredient.setEnabled(true);
						updateIngredientList(sorted);
						ingredientList.setModel(ingredientModel);
						setIngredientTextFields(sorted);
						setIngredientEditable(false);
					}
					catch (Exception e2) {
						System.out.println("Error updating ingredient from iTab");
						e2.printStackTrace();
					}
				}
			}
			
			
			
			
		}	
	}
	
	public void setIngredientTextFields(int row) {
		name.setText(client.getIngredientsIndex(row).getName());
		type.setText(client.getIngredientsIndex(row).getType());
		base.setText(Double.toString(client.getIngredientsIndex(row).getBaseAmount()));
		measure.setText(client.getIngredientsIndex(row).getMeasurement());
		calories.setText(Integer.toString(client.getIngredientsIndex(row).getCalories()));
		fat.setText(Double.toString(client.getIngredientsIndex(row).getTotalFat()));
		sodium.setText(Integer.toString(client.getIngredientsIndex(row).getSodium()));
		satFat.setText(Double.toString(client.getIngredientsIndex(row).getSatFat()));
		carbs.setText(Integer.toString(client.getIngredientsIndex(row).getCarbs()));
		transFat.setText(Double.toString(client.getIngredientsIndex(row).getTransFat()));
		fiber.setText(Integer.toString(client.getIngredientsIndex(row).getFiber()));
		polyFat.setText(Double.toString(client.getIngredientsIndex(row).getPolyFat()));
		sugar.setText(Integer.toString(client.getIngredientsIndex(row).getSugar()));
		monoFat.setText(Double.toString(client.getIngredientsIndex(row).getMonoFat()));
		protein.setText(Integer.toString(client.getIngredientsIndex(row).getProtien()));
		//System.out.println(row);   //use for testing
	}
	
	public void clearIngredientTextFields() {
		name.setText("");
		type.setText("");
		base.setText("");
		measure.setText("");
		calories.setText("");
		fat.setText("");
		sodium.setText("");
		satFat.setText("");
		carbs.setText("");
		transFat.setText("");
		fiber.setText("");
		polyFat.setText("");
		sugar.setText("");
		monoFat.setText("");
		protein.setText("");
	}
	
	public void setIngredientEditable(boolean b) {
		name.setEditable(b);
		type.setEditable(b);
		base.setEditable(b);
		measure.setEditable(b);
		calories.setEditable(b);
		fat.setEditable(b);
		sodium.setEditable(b);
		satFat.setEditable(b);
		carbs.setEditable(b);
		transFat.setEditable(b);
		fiber.setEditable(b);
		polyFat.setEditable(b);
		sugar.setEditable(b);
		monoFat.setEditable(b);
		protein.setEditable(b);
	}
	
	
	public boolean validateIngredientSubmission() {
		boolean temp = true;
		
		for (JTextField field : ingredientTextFields) {
			String s = field.getText().trim();
			if (( field.equals(this.type) || field.equals(this.measure)) && s.isEmpty()) {
				field.setBackground(pink);
				temp = false;
			}
			else if ((field.equals(this.name) || field.equals(this.type) || 
									field.equals(this.measure)) && !s.isEmpty()) {
					field.setBackground(Color.WHITE);
			}
			else if (!isNumeric(s)) {
				field.setBackground(pink);
				temp = false;
			}
			else if (isNumeric(s)) {
				field.setBackground(Color.WHITE);
			}
		}
		return temp;
	}
	
	public void updateIngredientList(int row) {
		ingredientModel.setValueAt(name.getText(), row, 0);
		ingredientModel.setValueAt(type.getText(), row, 1);
		ingredientModel.setValueAt(calories.getText(), row, 2);
		ingredientModel.setValueAt(fat.getText(), row, 3);
		ingredientModel.setValueAt(carbs.getText(), row, 4);
		ingredientModel.setValueAt(protein.getText(), row, 5);
	}
	
	public boolean isNumeric(String s) {  
		if (s.isEmpty()) { return false; }
	    return s.matches("[+]?\\d*\\.?\\d*");  
	}  
	
	public static void main(String [] args) {
		
		@SuppressWarnings("unused")
		GUI g = new GUI();
		
	}
	
	
	
}
