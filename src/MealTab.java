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
public class MealTab {
	
	private JPanel mealTabPanel = new JPanel();
	
	private JTextField mealName, mealCalories, mealTotalFat,
	   				   mealTransFat, mealSatFat, mealMonoFat,
	   				   mealPolyFat, mealCarbs, mealFiber,
	   				   mealSugar, mealProtein, mealSodium,
	   				   mealPrice;
	@SuppressWarnings("rawtypes")
	private JComboBox mealType;
	private JButton mealNew, mealAdd, mealRemove, mealLoad, mealSubmit;
	private DefaultTableModel mealShortModel, mealLongModel;
	private JTable mealShortList, mealLongList;
	private Object[] mealShortHeaders = new Object[2];
	private Object[] mealLongHeaders = new Object[5];
	
	
	public MealTab() { buildMealTab(this.mealTabPanel); }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void buildMealTab(Container mTab) {
		
		//initialize layout and constraints
		mTab.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		
		
		mealShortHeaders[0] = "Name";
		mealShortHeaders[1] = "Calories";
		mealLongHeaders[0] = "Name";
		mealLongHeaders[1] = "Calories";
		mealLongHeaders[2] = "Fat";
		mealLongHeaders[3] = "Carbs";
		mealLongHeaders[4] = "Protein";
		
		
		
		//recipeShortList
		//display ingredients in JTable
		mealShortModel = new DefaultTableModel(GUI.client.getCaloriesList(), mealShortHeaders);
		mealShortList = new JTable() {
    				public boolean isCellEditable(int row, int column) {                
    					return false;               
    				};
    		};
    		mealShortList.setModel(mealShortModel);
		TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(mealShortModel);
		mealShortList.setRowSorter(sort);
		mealShortList.addMouseListener(new IngredientListListener());
		mealShortList.addKeyListener(new IngredientListListener2());
		mealShortList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mealShortList.getColumnModel().getColumn(0).setPreferredWidth(100);
		mealShortList.getColumnModel().getColumn(1).setPreferredWidth(70);
		mealShortList.setPreferredScrollableViewportSize(new Dimension(170,360));
		c.gridwidth = 1;
		c.gridheight = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		JScrollPane scroll = new JScrollPane(mealShortList);
		mTab.add(scroll, c);
		
		//mealNew button
	    mealNew = new JButton("<html><font size=5><b>NEW</b></font></html>");
	    mealNew.addActionListener(new IngredientListener());
	    mealNew.setHorizontalAlignment(JTextField.CENTER);
	    mealNew.setPreferredSize(GUI.STANDARD_SINGLE);
	    mealNew.setEnabled(true);
	    c.gridwidth = 1;
		c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 0;
	    mTab.add(mealNew, c);
		
		//mealAdd button
	    mealAdd = new JButton(GUI.rightArrow);
	    mealAdd.addActionListener(new IngredientListener());
	    mealAdd.setHorizontalAlignment(JTextField.CENTER);
	    mealAdd.setPreferredSize(GUI.STANDARD_SINGLE);
	    mealAdd.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 1;
	    mTab.add(mealAdd, c);
	    
		//mealRemove button
	    mealRemove = new JButton(GUI.leftArrow);
	    mealRemove.addActionListener(new IngredientListener());
	    mealRemove.setHorizontalAlignment(JTextField.CENTER);
	    mealRemove.setPreferredSize(GUI.STANDARD_SINGLE);
	    mealRemove.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 2;
	    mTab.add(mealRemove, c);
	    
		//mealLoad button
	    mealLoad = new JButton("<html><font size=5><b>LOAD</b></font></html>");
	    mealLoad.addActionListener(new IngredientListener());
	    mealLoad.setHorizontalAlignment(JTextField.CENTER);
	    mealLoad.setPreferredSize(GUI.STANDARD_SINGLE);
	    mealLoad.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 3;
	    mTab.add(mealLoad, c);
	    
		//recipeRecipeList
		//display ingredients in JTable
	    mealLongModel = new DefaultTableModel(null, mealLongHeaders);
	    mealLongList = new JTable() {
    				public boolean isCellEditable(int row, int column) {                
    					return false;               
    				};
    		};
    		mealLongList.setModel(mealLongModel);
		TableRowSorter<DefaultTableModel> sort2 = new TableRowSorter<DefaultTableModel>(mealLongModel);
		mealLongList.setRowSorter(sort2);
		mealLongList.addMouseListener(new IngredientListListener());
		mealLongList.addKeyListener(new IngredientListListener2());
		mealLongList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mealLongList.getColumnModel().getColumn(0).setPreferredWidth(100);
		mealLongList.getColumnModel().getColumn(1).setPreferredWidth(70);
		mealLongList.setPreferredScrollableViewportSize(new Dimension(170,360));
		c.gridwidth = 3;
		c.gridheight = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		JScrollPane scroll2 = new JScrollPane(mealLongList);
		
		mTab.add(scroll2, c);
		
		//mealName field
		mealName = new JTextField();
		mealName.setBorder(BorderFactory.createTitledBorder("Meal Name"));
		mealName.setEditable(true);
		mealName.setHorizontalAlignment(JTextField.CENTER);
		mealName.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridwidth = 1;
		c.gridheight = 1;
	    c.gridx = 0;
	    c.gridy = 4;
	    mTab.add(mealName, c);
	    
		//mealType field
	    String[] mealTypes = { "Any", "Breakfast", "Lunch", "Dinner", "Snack" };
	    mealType = new JComboBox(mealTypes);
	    mealType.setBorder(BorderFactory.createTitledBorder("Type"));
	    mealType.setEditable(false);
	    mealType.setSelectedIndex(-1);
	    mealType.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 0;
	    c.gridy = 5;
	    mTab.add(mealType, c);
	    
		//mealSubmit button
	    mealSubmit = new JButton("<html><font size=5><b>SUBMIT</b></font></html>");
	    //mealSubmit.setEnabled(false);
	    mealSubmit.setHorizontalAlignment(JTextField.CENTER);
	    mealSubmit.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 0;
	    c.gridy = 6;
	    mTab.add(mealSubmit, c);
	    
		//mealCalories field
	    mealCalories = new JTextField();
	    mealCalories.setBorder(BorderFactory.createTitledBorder("Calories"));
	    mealCalories.setEditable(false);
	    mealCalories.setHorizontalAlignment(JTextField.CENTER);
	    mealCalories.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 1;
	    c.gridy = 4;
	    mTab.add(mealCalories, c);
	    
		//mealTransFat field
	    mealTransFat = new JTextField();
	    mealTransFat.setBorder(BorderFactory.createTitledBorder("Trans Fat (g)"));
	    mealTransFat.setEditable(false);
	    mealTransFat.setHorizontalAlignment(JTextField.CENTER);
	    mealTransFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 1;
	    c.gridy = 5;
	    mTab.add(mealTransFat, c);
	    
		//mealSatFat field
	    mealSatFat = new JTextField();
	    mealSatFat.setBorder(BorderFactory.createTitledBorder("Saturated Fat (g)"));
	    mealSatFat.setEditable(false);
	    mealSatFat.setHorizontalAlignment(JTextField.CENTER);
	    mealSatFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 1;
	    c.gridy = 6;
	    mTab.add(mealSatFat, c);
	    
		//mealTotalFat field
	    mealTotalFat = new JTextField();
	    mealTotalFat.setBorder(BorderFactory.createTitledBorder("Total Fat (g)"));
	    mealTotalFat.setEditable(false);
	    mealTotalFat.setHorizontalAlignment(JTextField.CENTER);
	    mealTotalFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 2;
	    c.gridy = 4;
	    mTab.add(mealTotalFat, c);
	    
		//mealPolyFat field
	    mealPolyFat = new JTextField();
	    mealPolyFat.setBorder(BorderFactory.createTitledBorder("Polyunsaturated Fat (g)"));
	    mealPolyFat.setEditable(false);
	    mealPolyFat.setHorizontalAlignment(JTextField.CENTER);
	    mealPolyFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 2;
	    c.gridy = 5;
	    mTab.add(mealPolyFat, c);
	    
		//mealMonoFat field
	    mealMonoFat = new JTextField();
	    mealMonoFat.setBorder(BorderFactory.createTitledBorder("Monosaturated Fat (g)"));
	    mealMonoFat.setEditable(false);
	    mealMonoFat.setHorizontalAlignment(JTextField.CENTER);
	    mealMonoFat.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 2;
	    c.gridy = 6;
	    mTab.add(mealMonoFat, c);
	    
		//mealCarbs field
	    mealCarbs = new JTextField();
	    mealCarbs.setBorder(BorderFactory.createTitledBorder("Carbs (g)"));
	    mealCarbs.setEditable(false);
	    mealCarbs.setHorizontalAlignment(JTextField.CENTER);
	    mealCarbs.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 3;
	    c.gridy = 4;
	    mTab.add(mealCarbs, c);
	    
		//mealFiber field
	    mealFiber = new JTextField();
	    mealFiber.setBorder(BorderFactory.createTitledBorder("Fiber (g)"));
	    mealFiber.setEditable(false);
	    mealFiber.setHorizontalAlignment(JTextField.CENTER);
	    mealFiber.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 3;
	    c.gridy = 5;
	    mTab.add(mealFiber, c);
	    
		//mealSugar field
	    mealSugar = new JTextField();
	    mealSugar.setBorder(BorderFactory.createTitledBorder("Sugar (g)"));
	    mealSugar.setEditable(false);
	    mealSugar.setHorizontalAlignment(JTextField.CENTER);
	    mealSugar.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 3;
	    c.gridy = 6;
	    mTab.add(mealSugar, c);
	    
		//mealProtein field
	    mealProtein = new JTextField();
	    mealProtein.setBorder(BorderFactory.createTitledBorder("Protein (g)"));
	    mealProtein.setEditable(false);
	    mealProtein.setHorizontalAlignment(JTextField.CENTER);
	    mealProtein.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 4;
	    c.gridy = 4;
	    mTab.add(mealProtein, c);
	    
		//mealSodium field
	    mealSodium = new JTextField();
	    mealSodium.setBorder(BorderFactory.createTitledBorder("Sodium (mg)"));
	    mealSodium.setEditable(false);
	    mealSodium.setHorizontalAlignment(JTextField.CENTER);
	    mealSodium.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 4;
	    c.gridy = 5;
	    mTab.add(mealSodium, c);
	    
		//mealPrice field
	    mealPrice = new JTextField();
	    mealPrice.setBorder(BorderFactory.createTitledBorder("Price per serving"));
	    mealPrice.setEditable(false);
	    mealPrice.setHorizontalAlignment(JTextField.CENTER);
	    mealPrice.setPreferredSize(GUI.STANDARD_SINGLE);
	    c.gridx = 4;
	    c.gridy = 6;
	    mTab.add(mealPrice, c);
		
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
	
	
	public JPanel getMealTab() { return this.mealTabPanel; }
}
