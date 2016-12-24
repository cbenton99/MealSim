import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private DefaultTableModel recipeShortModel, recipeLongModel;
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
		
		Dimension single = new Dimension(100,20);
		
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
		
		//recipeNew button
		recipeNew = new JButton("<html><font size=5><b>NEW</b></font></html>");
		recipeNew.addActionListener(new IngredientListener());
		recipeNew.setHorizontalAlignment(JTextField.CENTER);
		recipeNew.setPreferredSize(single);
		recipeNew.setEnabled(true);
	    c.gridwidth = 1;
		c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 0;
	    rTab.add(recipeNew, c);
		
		//recipeAdd button
	    recipeAdd = new JButton(GUI.rightArrow);
	    recipeAdd.addActionListener(new IngredientListener());
	    recipeAdd.setHorizontalAlignment(JTextField.CENTER);
	    recipeAdd.setPreferredSize(single);
	    recipeAdd.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 1;
	    rTab.add(recipeAdd, c);
	    
		//recipeRemove button
	    recipeRemove = new JButton(GUI.leftArrow);
	    recipeRemove.addActionListener(new IngredientListener());
	    recipeRemove.setHorizontalAlignment(JTextField.CENTER);
	    recipeRemove.setPreferredSize(single);
	    recipeRemove.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 2;
	    rTab.add(recipeRemove, c);
	    
		//recipeLoad button
	    recipeLoad = new JButton("<html><font size=5><b>LOAD</b></font></html>");
	    recipeLoad.addActionListener(new IngredientListener());
	    recipeLoad.setHorizontalAlignment(JTextField.CENTER);
	    recipeLoad.setPreferredSize(single);
	    recipeLoad.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 3;
	    rTab.add(recipeLoad, c);
	    
		//recipeRecipeList
		//display ingredients in JTable
	    recipeLongModel = new DefaultTableModel(null, recipeLongHeaders);
		recipeLongList = new JTable() {
    				public boolean isCellEditable(int row, int column) {                
    					return false;               
    				};
    		};
    		recipeLongList.setModel(recipeLongModel);
		TableRowSorter<DefaultTableModel> sort2 = new TableRowSorter<DefaultTableModel>(recipeLongModel);
		recipeLongList.setRowSorter(sort2);
		recipeLongList.addMouseListener(new IngredientListListener());
		recipeLongList.addKeyListener(new IngredientListListener2());
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
	    recipeName.setPreferredSize(single);
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
	    recipeType.setPreferredSize(single);
	    c.gridx = 0;
	    c.gridy = 5;
	    rTab.add(recipeType, c);
	    
		//recipeSubmit button
	    recipeSubmit = new JButton("<html><font size=5><b>SUBMIT</b></font></html>");
	    //recipeSubmit.setEnabled(false);
	    recipeSubmit.setHorizontalAlignment(JTextField.CENTER);
	    recipeSubmit.setPreferredSize(single);
	    c.gridx = 0;
	    c.gridy = 6;
	    rTab.add(recipeSubmit, c);
	    
		//recipeCalories field
	    recipeCalories = new JTextField();
	    recipeCalories.setBorder(BorderFactory.createTitledBorder("Calories"));
	    recipeCalories.setEditable(false);
	    recipeCalories.setHorizontalAlignment(JTextField.CENTER);
	    recipeCalories.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 4;
	    rTab.add(recipeCalories, c);
	    
		//recipeTransFat field
	    recipeTransFat = new JTextField();
	    recipeTransFat.setBorder(BorderFactory.createTitledBorder("Trans Fat (g)"));
	    recipeTransFat.setEditable(false);
	    recipeTransFat.setHorizontalAlignment(JTextField.CENTER);
	    recipeTransFat.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 5;
	    rTab.add(recipeTransFat, c);
	    
		//recipeSatFat field
	    recipeSatFat = new JTextField();
	    recipeSatFat.setBorder(BorderFactory.createTitledBorder("Saturated Fat (g)"));
	    recipeSatFat.setEditable(false);
	    recipeSatFat.setHorizontalAlignment(JTextField.CENTER);
	    recipeSatFat.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 6;
	    rTab.add(recipeSatFat, c);
	    
		//recipeTotalFat field
	    recipeTotalFat = new JTextField();
	    recipeTotalFat.setBorder(BorderFactory.createTitledBorder("Total Fat (g)"));
	    recipeTotalFat.setEditable(false);
	    recipeTotalFat.setHorizontalAlignment(JTextField.CENTER);
	    recipeTotalFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 4;
	    rTab.add(recipeTotalFat, c);
	    
		//recipePolyFat field
	    recipePolyFat = new JTextField();
	    recipePolyFat.setBorder(BorderFactory.createTitledBorder("Polyunsaturated Fat (g)"));
	    recipePolyFat.setEditable(false);
	    recipePolyFat.setHorizontalAlignment(JTextField.CENTER);
	    recipePolyFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 5;
	    rTab.add(recipePolyFat, c);
	    
		//recipeMonoFat field
	    recipeMonoFat = new JTextField();
	    recipeMonoFat.setBorder(BorderFactory.createTitledBorder("Monosaturated Fat (g)"));
	    recipeMonoFat.setEditable(false);
	    recipeMonoFat.setHorizontalAlignment(JTextField.CENTER);
	    recipeMonoFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 6;
	    rTab.add(recipeMonoFat, c);
	    
		//recipeCarbs field
	    recipeCarbs = new JTextField();
	    recipeCarbs.setBorder(BorderFactory.createTitledBorder("Carbs (g)"));
	    recipeCarbs.setEditable(false);
	    recipeCarbs.setHorizontalAlignment(JTextField.CENTER);
	    recipeCarbs.setPreferredSize(single);
	    c.gridx = 3;
	    c.gridy = 4;
	    rTab.add(recipeCarbs, c);
	    
		//recipeFiber field
	    recipeFiber = new JTextField();
	    recipeFiber.setBorder(BorderFactory.createTitledBorder("Fiber (g)"));
	    recipeFiber.setEditable(false);
	    recipeFiber.setHorizontalAlignment(JTextField.CENTER);
	    recipeFiber.setPreferredSize(single);
	    c.gridx = 3;
	    c.gridy = 5;
	    rTab.add(recipeFiber, c);
	    
		//recipeSugar field
	    recipeSugar = new JTextField();
	    recipeSugar.setBorder(BorderFactory.createTitledBorder("Sugar (g)"));
	    recipeSugar.setEditable(false);
	    recipeSugar.setHorizontalAlignment(JTextField.CENTER);
	    recipeSugar.setPreferredSize(single);
	    c.gridx = 3;
	    c.gridy = 6;
	    rTab.add(recipeSugar, c);
	    
		//recipeProtein field
	    recipeProtein = new JTextField();
	    recipeProtein.setBorder(BorderFactory.createTitledBorder("Protein (g)"));
	    recipeProtein.setEditable(false);
	    recipeProtein.setHorizontalAlignment(JTextField.CENTER);
	    recipeProtein.setPreferredSize(single);
	    c.gridx = 4;
	    c.gridy = 4;
	    rTab.add(recipeProtein, c);
	    
		//recipeSodium field
	    recipeSodium = new JTextField();
	    recipeSodium.setBorder(BorderFactory.createTitledBorder("Sodium (mg)"));
	    recipeSodium.setEditable(false);
	    recipeSodium.setHorizontalAlignment(JTextField.CENTER);
	    recipeSodium.setPreferredSize(single);
	    c.gridx = 4;
	    c.gridy = 5;
	    rTab.add(recipeSodium, c);
	    
		//recipePrice field
	    recipePrice = new JTextField();
	    recipePrice.setBorder(BorderFactory.createTitledBorder("Price per serving"));
	    recipePrice.setEditable(false);
	    recipePrice.setHorizontalAlignment(JTextField.CENTER);
	    recipePrice.setPreferredSize(single);
	    c.gridx = 4;
	    c.gridy = 6;
	    rTab.add(recipePrice, c);
	}
	
	//listener for when ingredient is clicked in iTab
	private class IngredientListListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			
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
			
		}
	}
	
	//listener for add/delete/edit/update buttons in iTab
	private class IngredientListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				
		}	
	}
	
	
	
	public JPanel getRecipeTab() { return this.recipeTabPanel; }
}
