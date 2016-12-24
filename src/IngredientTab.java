import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


@SuppressWarnings("serial")
public class IngredientTab {
	
	private JPanel ingredientTabPanel = new JPanel();
	private JTextField ingredientName, ingredientType, ingredientBase,
	   				   ingredientMeasure, ingredientCalories, ingredientFat,
	   				   ingredientSodium, ingredientSatFat, ingredientCarbs,
	   				   ingredientTransFat, ingredientFiber, ingredientPolyFat,
	   				   ingredientSugar, ingredientMonoFat, ingredientProtein;
	private JButton ingredientAdd, ingredientEdit, ingredientDelete, ingredientUpdate;
	private DefaultTableModel ingredientModel;
	private JTable ingredientList;
	private Object[] ingredientHeaders = new Object[6];
	private ArrayList<JTextField> ingredientTextFields = new ArrayList<JTextField>();

	
	public IngredientTab() { buildIngredientTab(this.ingredientTabPanel); }
	
	public void buildIngredientTab(Container iTab) {
		
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
		ingredientModel = new DefaultTableModel(GUI.client.getMacroList(), ingredientHeaders);
    		ingredientList = new JTable() {
    				public boolean isCellEditable(int row, int column) {                
    					return false;               
    				};
    		};
		ingredientList.setModel(ingredientModel);
		TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(ingredientModel);
		ingredientList.setRowSorter(sort);
		ingredientList.addMouseListener(new iTabMouseListener());
		ingredientList.addKeyListener(new iTabKeyListener());
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
		
		//ingredientName field
		ingredientName = new JTextField();
		ingredientName.setBorder(BorderFactory.createTitledBorder("Name"));
		ingredientName.setEditable(false);
		ingredientName.setHorizontalAlignment(JTextField.CENTER);
		ingredientName.setPreferredSize(new Dimension(200,20));
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 0;
	    iTab.add(ingredientName, c);
	    ingredientTextFields.add(ingredientName);
	    
		//ingredientType field
	    ingredientType = new JTextField();
	    ingredientType.setBorder(BorderFactory.createTitledBorder("Type"));
	    ingredientType.setEditable(false);
	    ingredientType.setHorizontalAlignment(JTextField.CENTER);
	    ingredientType.setPreferredSize(new Dimension(200,20));
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 1;
	    iTab.add(ingredientType, c);
	    ingredientTextFields.add(ingredientType);
	    
		//ingredientBase field
	    ingredientBase = new JTextField();
	    ingredientBase.setBorder(BorderFactory.createTitledBorder("Base Amount"));
	    ingredientBase.setEditable(false);
	    ingredientBase.setHorizontalAlignment(JTextField.CENTER);
	    ingredientBase.setPreferredSize(single);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 2;
	    iTab.add(ingredientBase, c);
	    ingredientTextFields.add(ingredientBase);
	    
		//ingredientMeasure field
	    ingredientMeasure = new JTextField();
	    ingredientMeasure.setBorder(BorderFactory.createTitledBorder("Measurement"));
	    ingredientMeasure.setEditable(false);
	    ingredientMeasure.setHorizontalAlignment(JTextField.CENTER);
	    ingredientMeasure.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 2;
	    iTab.add(ingredientMeasure, c);
	    ingredientTextFields.add(ingredientMeasure);
	    
		//ingredientCalories field
	    ingredientCalories = new JTextField();
	    ingredientCalories.setBorder(BorderFactory.createTitledBorder("Calories"));
	    ingredientCalories.setEditable(false);
	    ingredientCalories.setHorizontalAlignment(JTextField.CENTER);
	    ingredientCalories.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 3;
	    iTab.add(ingredientCalories, c);
	    ingredientTextFields.add(ingredientCalories);
	    
		//ingredientFat field
	    ingredientFat = new JTextField();
	    ingredientFat.setBorder(BorderFactory.createTitledBorder("Total Fat (g)"));
	    ingredientFat.setEditable(false);
	    ingredientFat.setHorizontalAlignment(JTextField.CENTER);
	    ingredientFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 3;
	    iTab.add(ingredientFat, c);
	    ingredientTextFields.add(ingredientFat);
	    
		//ingredientSodium field
	    ingredientSodium = new JTextField();
	    ingredientSodium.setBorder(BorderFactory.createTitledBorder("Sodium (mg)"));
	    ingredientSodium.setEditable(false);
	    ingredientSodium.setHorizontalAlignment(JTextField.CENTER);
	    ingredientSodium.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 4;
	    iTab.add(ingredientSodium, c);
	    ingredientTextFields.add(ingredientSodium);
	    
		//ingredientSatFat field
	    ingredientSatFat = new JTextField();
	    ingredientSatFat.setBorder(BorderFactory.createTitledBorder("Saturated Fat (g)"));
	    ingredientSatFat.setEditable(false);
	    ingredientSatFat.setHorizontalAlignment(JTextField.CENTER);
	    ingredientSatFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 4;
	    iTab.add(ingredientSatFat, c);
	    ingredientTextFields.add(ingredientSatFat);
	    
		//ingredientCarbs field
	    ingredientCarbs = new JTextField();
	    ingredientCarbs.setBorder(BorderFactory.createTitledBorder("Carboydrates (g)"));
	    ingredientCarbs.setEditable(false);
	    ingredientCarbs.setHorizontalAlignment(JTextField.CENTER);
	    ingredientCarbs.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 5;
	    iTab.add(ingredientCarbs, c);
	    ingredientTextFields.add(ingredientCarbs);
	    
		//ingredientTransFat field
	    ingredientTransFat = new JTextField();
	    ingredientTransFat.setBorder(BorderFactory.createTitledBorder("Trans Fat (g)"));
	    ingredientTransFat.setEditable(false);
	    ingredientTransFat.setHorizontalAlignment(JTextField.CENTER);
	    ingredientTransFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 5;
	    iTab.add(ingredientTransFat, c);
	    ingredientTextFields.add(ingredientTransFat);
	    
		//ingredientFiber field
	    ingredientFiber = new JTextField();
	    ingredientFiber.setBorder(BorderFactory.createTitledBorder("Fiber (g)"));
	    ingredientFiber.setEditable(false);
	    ingredientFiber.setHorizontalAlignment(JTextField.CENTER);
	    ingredientFiber.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 6;
	    iTab.add(ingredientFiber, c);
	    ingredientTextFields.add(ingredientFiber);
	    
		//ingredientPolyFat field
	    ingredientPolyFat = new JTextField();
	    ingredientPolyFat.setBorder(BorderFactory.createTitledBorder("Polyunsaturated Fat (g)"));
	    ingredientPolyFat.setEditable(false);
	    ingredientPolyFat.setHorizontalAlignment(JTextField.CENTER);
	    ingredientPolyFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 6;
	    iTab.add(ingredientPolyFat, c);
	    ingredientTextFields.add(ingredientPolyFat);
	    
		//ingredientSugar field
	    ingredientSugar = new JTextField();
	    ingredientSugar.setBorder(BorderFactory.createTitledBorder("Sugar (g)"));
	    ingredientSugar.setEditable(false);
	    ingredientSugar.setHorizontalAlignment(JTextField.CENTER);
	    ingredientSugar.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 7;
	    iTab.add(ingredientSugar, c);
	    ingredientTextFields.add(ingredientSugar);
	    
		//ingredientMonoFat field
	    ingredientMonoFat = new JTextField();
	    ingredientMonoFat.setBorder(BorderFactory.createTitledBorder("Monosaturated Fat (g)"));
	    ingredientMonoFat.setEditable(false);
	    ingredientMonoFat.setHorizontalAlignment(JTextField.CENTER);
	    ingredientMonoFat.setPreferredSize(single);
	    c.gridx = 2;
	    c.gridy = 7;
	    iTab.add(ingredientMonoFat, c);
	    ingredientTextFields.add(ingredientMonoFat);
	    
		//ingredientProtein field
	    ingredientProtein = new JTextField();
	    ingredientProtein.setBorder(BorderFactory.createTitledBorder("Protein (g)"));
	    ingredientProtein.setEditable(false);
	    ingredientProtein.setHorizontalAlignment(JTextField.CENTER);
	    ingredientProtein.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 8;
	    iTab.add(ingredientProtein, c);
	    ingredientTextFields.add(ingredientProtein);
	    
		//ingredientAdd button
	    ingredientAdd = new JButton("<html><font size=5><b>ADD</b></font></html>");
	    ingredientAdd.addActionListener(new iTabAddListener());
	    ingredientAdd.setHorizontalAlignment(JTextField.CENTER);
	    ingredientAdd.setPreferredSize(single);
	    c.gridx = 1;
	    c.gridy = 9;
	    iTab.add(ingredientAdd, c);
	    
		//ingredientEdit button
	    ingredientEdit = new JButton("<html><font size=5><b>EDIT</b></font></html>");
	    ingredientEdit.addActionListener(new iTabEditListener());
	    ingredientEdit.setHorizontalAlignment(JTextField.CENTER);
	    ingredientEdit.setPreferredSize(single);
	    ingredientEdit.setEnabled(false);
	    c.gridx = 2;
	    c.gridy = 9;
	    iTab.add(ingredientEdit, c);
	    
		//ingredientDelete button
	    ingredientDelete = new JButton("<html><font size=5><b>DELETE</b></font></html>");
	    ingredientDelete.addActionListener(new iTabDeleteListener());
	    ingredientDelete.setHorizontalAlignment(JTextField.CENTER);
	    ingredientDelete.setPreferredSize(single);
	    ingredientDelete.setEnabled(false);
	    c.gridx = 1;
	    c.gridy = 10;
	    iTab.add(ingredientDelete, c);
	    
		//ingredientUpdate button
	    ingredientUpdate = new JButton("<html><font size=5><b>UPDATE</b></font></html>");
	    ingredientUpdate.addActionListener(new iTabUpdateListener());
	    ingredientUpdate.setHorizontalAlignment(JTextField.CENTER);
	    ingredientUpdate.setPreferredSize(single);
	    ingredientUpdate.setEnabled(false);
	    c.gridx = 2;
	    c.gridy = 10;
	    iTab.add(ingredientUpdate, c);
		
	}
	
	
	
	
	
	//listener for when ingredient is clicked in iTab
	private class iTabMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (ingredientList.isEnabled()) {
				int row = ingredientList.convertRowIndexToModel(ingredientList.rowAtPoint(e.getPoint()));
				setIngredientTextFields(row);
				
				if (ingredientList.getSelectedRow() >= 0) {
					ingredientAdd.setEnabled(true);
					ingredientDelete.setEnabled(true);
					ingredientEdit.setEnabled(true);
				}
			}
		}
		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }
		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) { }
	}
	
	//listener for when keyboard is used to change ingredient in iTab
	private class iTabKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) { }
		public void keyPressed(KeyEvent e) { }
		public void keyReleased(KeyEvent e) {
			int row = ingredientList.convertRowIndexToModel(ingredientList.getSelectedRow());
			setIngredientTextFields(row);
			if (ingredientList.getSelectedRow() >= 0) {
				ingredientAdd.setEnabled(true);
				ingredientDelete.setEnabled(true);
				ingredientEdit.setEnabled(true);
			}
		}
	}
	
	//listener for add/delete/edit/update buttons in iTab
	private class iTabAddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//add ingredient to ingredientList in iTab
			if (e.getSource() == ingredientAdd) {
				if (e.getSource().toString().contains("ADD")) {
					clearIngredientTextFields();
					ingredientDelete.setEnabled(false);
					ingredientEdit.setEnabled(false);
					ingredientList.setEnabled(false);
					//ingredientList.setCellSelectionEnabled(false);
					setIngredientEditable(true);
					ingredientAdd.setText("<html><font size = 5><b>SUBMIT</b></font></html>");
				}
				else if (e.getSource().toString().contains("SUBMIT")) {
					boolean canSubmit = validateIngredientSubmission();
					if (canSubmit) {
						int row = ingredientList.getSelectedRow();
						Ingredient i = createIngredient();
						GUI.client.addIngredient(i);
						ingredientAdd.setText("<html><font size = 5><b>ADD</b></font></html>");
						ingredientModel.addRow(i.toMacroArray());
						ingredientList.setModel(ingredientModel);
						clearIngredientTextFields();
						ingredientList.setEnabled(true);
						ingredientEdit.setEnabled(true);
						ingredientDelete.setEnabled(true);
						if (row >= 0) { 
							int sorted = ingredientList.convertRowIndexToModel(row);
							setIngredientTextFields(sorted);
						}
					}
				}
			}
		}	
	}
	
	//delete ingredient
	private class iTabDeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int row = ingredientList.getSelectedRow();
			int length = GUI.client.getIngredientsLength();
			if (row >= 0) {
				int sortedRow = ingredientList.convertRowIndexToModel(row);
				GUI.client.removeIngredient(GUI.client.getIngredientsIndex(sortedRow));
				ingredientModel.removeRow(sortedRow);
				//adjust row if deleting last row
				if (row == GUI.client.getIngredientsLength()) { row--; }
				//set selected row to new row
				if (length > 1) {
					ingredientList.setRowSelectionInterval(row, row);
					setIngredientTextFields(ingredientList.convertRowIndexToModel(ingredientList.getSelectedRow()));
				}
				//if deleting last ingredient, clear/disable
				//everything except addIngredient
				else if (length == 1) {
					clearIngredientTextFields();
					ingredientAdd.setEnabled(true);
					ingredientDelete.setEnabled(false);
					ingredientEdit.setEnabled(false);
				}
			}
		}
	}
	
	//edit ingredient
	private class iTabEditListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ingredientList.setEnabled(false);
			setIngredientEditable(true);
			ingredientAdd.setEnabled(false);
			ingredientDelete.setEnabled(false);
			ingredientEdit.setEnabled(false);
			ingredientUpdate.setEnabled(true);
		}
	}

	
	//update ingredient
	private class iTabUpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean canSubmit = validateIngredientSubmission();
			int row = ingredientList.getSelectedRow();
			int sorted = ingredientList.convertRowIndexToModel(row);
			
			if (canSubmit) {
				Ingredient i = GUI.client.getIngredientsIndex(sorted);
				updateIngredient(i);
				try { 
					//i.getFile().delete();
					System.out.println("original: " + i.getFile().getName());
					
					GUI.client.updateIngredient(i);

				}
				catch (Exception e2) {
					System.out.println("Error updating ingredient from rTab");
					e2.printStackTrace();
				}
				ingredientList.setEnabled(true);
				ingredientAdd.setEnabled(true);
				ingredientDelete.setEnabled(true);
				ingredientEdit.setEnabled(true);
				ingredientUpdate.setEnabled(false);
				updateIngredientList(sorted);
				ingredientList.setModel(ingredientModel);
				setIngredientTextFields(sorted);
				setIngredientEditable(false);
			}
		}
	}
	
	public Ingredient createIngredient() {
		Ingredient i = new Ingredient();
		i.setName(ingredientName.getText().trim());
		i.setType(ingredientType.getText().trim());
		i.setBaseAmount(Double.parseDouble(ingredientBase.getText()));
		i.setMeasurement(ingredientMeasure.getText().trim());
		i.setCalories(Double.parseDouble(ingredientCalories.getText()));
		i.setTotalFat(Double.parseDouble(ingredientFat.getText()));
		i.setSodium(Double.parseDouble(ingredientSodium.getText()));
		i.setSatFat(Double.parseDouble(ingredientSatFat.getText()));
		i.setCarbs(Double.parseDouble(ingredientCarbs.getText()));
		i.setTransFat(Double.parseDouble(ingredientTransFat.getText()));
		i.setFiber(Double.parseDouble(ingredientFiber.getText()));
		i.setPolyFat(Double.parseDouble(ingredientPolyFat.getText()));
		i.setSugar(Double.parseDouble(ingredientSugar.getText()));
		i.setMonoFat(Double.parseDouble(ingredientMonoFat.getText()));
		i.setProtien(Double.parseDouble(ingredientProtein.getText()));
		return i;
	}
	
	public void updateIngredient(Ingredient i) {
		//i.setFileName(ingredientName.getText().trim() + ".txt");
		i.setName(ingredientName.getText().trim());
		i.setType(ingredientType.getText().trim());
		i.setBaseAmount(Double.parseDouble(ingredientBase.getText()));
		i.setMeasurement(ingredientMeasure.getText().trim());
		i.setCalories(Double.parseDouble(ingredientCalories.getText()));
		i.setTotalFat(Double.parseDouble(ingredientFat.getText()));
		i.setSodium(Double.parseDouble(ingredientSodium.getText()));
		i.setSatFat(Double.parseDouble(ingredientSatFat.getText()));
		i.setCarbs(Double.parseDouble(ingredientCarbs.getText()));
		i.setTransFat(Double.parseDouble(ingredientTransFat.getText()));
		i.setFiber(Double.parseDouble(ingredientFiber.getText()));
		i.setPolyFat(Double.parseDouble(ingredientPolyFat.getText()));
		i.setSugar(Double.parseDouble(ingredientSugar.getText()));
		i.setMonoFat(Double.parseDouble(ingredientMonoFat.getText()));
		i.setProtien(Double.parseDouble(ingredientProtein.getText()));
	}
	
	public void setIngredientTextFields(int row) {
		ingredientName.setText(GUI.client.getIngredientsIndex(row).getName());
		ingredientType.setText(GUI.client.getIngredientsIndex(row).getType());
		ingredientBase.setText(Double.toString(GUI.client.getIngredientsIndex(row).getBaseAmount()));
		ingredientMeasure.setText(GUI.client.getIngredientsIndex(row).getMeasurement());
		ingredientCalories.setText(Double.toString(GUI.client.getIngredientsIndex(row).getCalories()));
		ingredientFat.setText(Double.toString(GUI.client.getIngredientsIndex(row).getTotalFat()));
		ingredientSodium.setText(Double.toString(GUI.client.getIngredientsIndex(row).getSodium()));
		ingredientSatFat.setText(Double.toString(GUI.client.getIngredientsIndex(row).getSatFat()));
		ingredientCarbs.setText(Double.toString(GUI.client.getIngredientsIndex(row).getCarbs()));
		ingredientTransFat.setText(Double.toString(GUI.client.getIngredientsIndex(row).getTransFat()));
		ingredientFiber.setText(Double.toString(GUI.client.getIngredientsIndex(row).getFiber()));
		ingredientPolyFat.setText(Double.toString(GUI.client.getIngredientsIndex(row).getPolyFat()));
		ingredientSugar.setText(Double.toString(GUI.client.getIngredientsIndex(row).getSugar()));
		ingredientMonoFat.setText(Double.toString(GUI.client.getIngredientsIndex(row).getMonoFat()));
		ingredientProtein.setText(Double.toString(GUI.client.getIngredientsIndex(row).getProtein()));
	}
	
	public void clearIngredientTextFields() {
		ingredientName.setText("");
		ingredientType.setText("");
		ingredientBase.setText("");
		ingredientMeasure.setText("");
		ingredientCalories.setText("");
		ingredientFat.setText("");
		ingredientSodium.setText("");
		ingredientSatFat.setText("");
		ingredientCarbs.setText("");
		ingredientTransFat.setText("");
		ingredientFiber.setText("");
		ingredientPolyFat.setText("");
		ingredientSugar.setText("");
		ingredientMonoFat.setText("");
		ingredientProtein.setText("");
	}
	
	public void setIngredientEditable(boolean b) {
		ingredientName.setEditable(b);
		ingredientType.setEditable(b);
		ingredientBase.setEditable(b);
		ingredientMeasure.setEditable(b);
		ingredientCalories.setEditable(b);
		ingredientFat.setEditable(b);
		ingredientSodium.setEditable(b);
		ingredientSatFat.setEditable(b);
		ingredientCarbs.setEditable(b);
		ingredientTransFat.setEditable(b);
		ingredientFiber.setEditable(b);
		ingredientPolyFat.setEditable(b);
		ingredientSugar.setEditable(b);
		ingredientMonoFat.setEditable(b);
		ingredientProtein.setEditable(b);
	}
	
	
	public boolean validateIngredientSubmission() {
		boolean temp = true;
		
		for (JTextField field : ingredientTextFields) {
			String s = field.getText().trim();
			if (( field.equals(this.ingredientType) || field.equals(this.ingredientMeasure)) && s.isEmpty()) {
				field.setBackground(GUI.pink);
				temp = false;
			}
			else if ((field.equals(this.ingredientName) || field.equals(this.ingredientType) || 
									field.equals(this.ingredientMeasure)) && !s.isEmpty()) {
					field.setBackground(Color.WHITE);
			}
			else if (!isNumeric(s)) {
				field.setBackground(GUI.pink);
				temp = false;
			}
			else if (isNumeric(s)) {
				field.setBackground(Color.WHITE);
			}
		}
		return temp;
	}
	
	public void updateIngredientList(int row) {
		ingredientModel.setValueAt(ingredientName.getText(), row, 0);
		ingredientModel.setValueAt(ingredientType.getText(), row, 1);
		ingredientModel.setValueAt(ingredientCalories.getText(), row, 2);
		ingredientModel.setValueAt(ingredientFat.getText(), row, 3);
		ingredientModel.setValueAt(ingredientCarbs.getText(), row, 4);
		ingredientModel.setValueAt(ingredientProtein.getText(), row, 5);
	}
	
	public boolean isNumeric(String s) {  
		if (s.isEmpty()) { return false; }
	    return s.matches("[+]?\\d*\\.?\\d*");  
	}  
	
	
	
	public JPanel getIngredientTab() { return this.ingredientTabPanel; }
	
}
