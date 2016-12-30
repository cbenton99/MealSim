import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;


public class GoalTab {
	
	private JPanel goalTabPanel = new JPanel();
	
	private JPanel goalGenderPanel, goalGoalPanel;
	private JButton goalCalculateButton;
	private JRadioButton goalMale, goalFemale, goalCut, goalMaintain, goalBulk;
	private JTextField goalName;
	private JLabel targetCalories, targetBMR, targetProtein, targetTDEE, targetFat,
				   targetGoal, targetCarbs, targetDiet, goalPieChart;
	private JSlider goalAgeSlider, goalHeightSlider, goalWeightSlider,
					goalActivitySlider, goalBodyFatSlider;
	private JLabel goalAgeLabel, goalHeightLabel, goalWeightLabel,
				   goalActivityLabel, goalBodyFatLabel;
	
	public GoalTab() { buildGoalTab(this.goalTabPanel); }
		
		
	public void buildGoalTab(Container gTab) {
		//initialize layout and constraints
		gTab.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		
		
		//goalName field
		goalName = new JTextField();
		goalName.setBorder(BorderFactory.createTitledBorder("Name"));
		goalName.setEditable(true);
		goalName.setHorizontalAlignment(JTextField.CENTER);
		goalName.setPreferredSize(GUI.ALT_SINGLE);
	    c.gridwidth = 5;
	    c.gridheight = 2;
	    c.weightx = 1;
		c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 0;
	    gTab.add(goalName, c);
	    
		//goalGender field
	    ButtonGroup genderGroup = new ButtonGroup();
	    goalGenderPanel = new JPanel(new GridBagLayout());
	    goalGenderPanel.setPreferredSize(GUI.ALT_SINGLE);
	    GridBagConstraints c2 = new GridBagConstraints();
	    c2.fill = GridBagConstraints.BOTH;
	    goalMale = new JRadioButton("MALE", true);
		goalFemale = new JRadioButton("FEMALE", false);
		
		genderGroup.add(goalMale);
		genderGroup.add(goalFemale);
		goalGenderPanel.add(goalMale, c2);
		goalGenderPanel.add(goalFemale, c2);
	    c.gridwidth = 5;
	    c.gridheight = 1;
	    c.gridx = 0;
	    c.gridy = 2;
	    gTab.add(goalGenderPanel, c);
	    
		//goalGoal field
	    ButtonGroup goalGroup = new ButtonGroup();
	    goalGoalPanel = new JPanel(new GridBagLayout());
	    goalGoalPanel.setPreferredSize(GUI.ALT_SINGLE);
	    
	    goalCut = new JRadioButton("CUT", false);
		goalMaintain = new JRadioButton("MAINTAIN", true);
		goalBulk = new JRadioButton("BULK", false);
		
		goalGroup.add(goalCut);
		goalGroup.add(goalMaintain);
		goalGroup.add(goalBulk);
		goalGoalPanel.add(goalCut, c2);
		goalGoalPanel.add(goalMaintain, c2);
		goalGoalPanel.add(goalBulk, c2);
		
	    c.gridx = 0;
	    c.gridy = 3;
	    gTab.add(goalGoalPanel, c);
	    
	    

	    
	    
	    
	    
	    
	    
	    //goalAge slider
	    TitledBorder gaTitle = BorderFactory.createTitledBorder("Age");
	    gaTitle.setTitleJustification(TitledBorder.CENTER);
	    goalAgeSlider = new JSlider(JSlider.VERTICAL, 15, 90, 28);
	    goalAgeSlider.addChangeListener(new goalSliderListener());
	    goalAgeSlider.setLabelTable(goalAgeSlider.createStandardLabels(75));
	    goalAgeSlider.setPaintLabels(true);
	    goalAgeSlider.setSnapToTicks(true);
	    goalAgeSlider.setBorder(gaTitle);
	    goalAgeSlider.setPreferredSize(GUI.STANDARD_SLIDER);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 0;
	    c.gridy = 5;
	    gTab.add(goalAgeSlider, c);
	    
	    //goalHeight slider
	    TitledBorder ghTitle = BorderFactory.createTitledBorder("Height (inches)");
	    ghTitle.setTitleJustification(TitledBorder.CENTER);
	    goalHeightSlider = new JSlider(JSlider.VERTICAL, 50, 80, 74);
	    goalHeightSlider.addChangeListener(new goalSliderListener());
	    goalHeightSlider.setLabelTable(goalHeightSlider.createStandardLabels(30));
	    goalHeightSlider.setPaintLabels(true);
	    goalHeightSlider.setSnapToTicks(true);
	    goalHeightSlider.setBorder(ghTitle);
	    goalHeightSlider.setPreferredSize(GUI.STANDARD_SLIDER);
	    c.gridx = 1;
	    c.gridy = 5;
	    gTab.add(goalHeightSlider, c);
	    
	    //goalWeight slider
	    TitledBorder gwTitle = BorderFactory.createTitledBorder("Weight (pounds)");
	    gwTitle.setTitleJustification(TitledBorder.CENTER);
	    goalWeightSlider = new JSlider(JSlider.VERTICAL, 100, 300, 190);
	    goalWeightSlider.addChangeListener(new goalSliderListener());
	    goalWeightSlider.setLabelTable(goalWeightSlider.createStandardLabels(200));
	    goalWeightSlider.setPaintLabels(true);
	    goalWeightSlider.setSnapToTicks(true);
	    goalWeightSlider.setBorder(gwTitle);
	    goalWeightSlider.setPreferredSize(GUI.STANDARD_SLIDER);
	    c.gridx = 2;
	    c.gridy = 5;
	    gTab.add(goalWeightSlider, c);
	    
	    //goalActivity slider
	    TitledBorder gactTitle = BorderFactory.createTitledBorder("Activity");
	    gactTitle.setTitleJustification(TitledBorder.CENTER);
	    goalActivitySlider = new JSlider(JSlider.VERTICAL, 0, 10, 5);
	    goalActivitySlider.addChangeListener(new goalSliderListener());
	    goalActivitySlider.setLabelTable(goalActivitySlider.createStandardLabels(10));
	    goalActivitySlider.setPaintLabels(true);
	    goalActivitySlider.setSnapToTicks(true);
	    goalActivitySlider.setBorder(gactTitle);
	    goalActivitySlider.setPreferredSize(GUI.STANDARD_SLIDER);
	    c.gridx = 3;
	    c.gridy = 5;
	    gTab.add(goalActivitySlider, c);
	    
	    //goalBodyFat slider
	    TitledBorder gbfTitle = BorderFactory.createTitledBorder("Body Fat %");
	    gbfTitle.setTitleJustification(TitledBorder.CENTER);
	    goalBodyFatSlider = new JSlider(JSlider.VERTICAL, 0, 50, 15);
	    goalBodyFatSlider.addChangeListener(new goalSliderListener());
	    goalBodyFatSlider.setLabelTable(goalBodyFatSlider.createStandardLabels(50));
	    goalBodyFatSlider.setPaintLabels(true);
	    goalBodyFatSlider.setSnapToTicks(true);
	    goalBodyFatSlider.setBorder(gbfTitle);
	    goalBodyFatSlider.setPreferredSize(GUI.STANDARD_SLIDER);
	    c.gridx = 4;
	    c.gridy = 5;
	    gTab.add(goalBodyFatSlider, c);
	    
	    
	    //goalAgeLabel
	    goalAgeLabel = new JLabel(Integer.toString(goalBodyFatSlider.getValue()));
	    goalAgeLabel.setHorizontalAlignment(JLabel.CENTER);
	    goalAgeLabel.setPreferredSize(GUI.SLIDER_LABEL);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 0;
	    c.gridy = 4;
	    gTab.add(goalAgeLabel, c);
	    
	    //goalHeightLabel
	    goalHeightLabel = new JLabel("6'2\"");
	    goalHeightLabel.setHorizontalAlignment(JLabel.CENTER);
	    goalHeightLabel.setPreferredSize(GUI.SLIDER_LABEL);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 1;
	    c.gridy = 4;
	    gTab.add(goalHeightLabel, c);
	    
	    //goalWeightLabel
	    goalWeightLabel = new JLabel(Integer.toString(goalWeightSlider.getValue()) + " lbs");
	    goalWeightLabel.setHorizontalAlignment(JLabel.CENTER);
	    goalWeightLabel.setPreferredSize(GUI.SLIDER_LABEL);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 2;
	    c.gridy = 4;
	    gTab.add(goalWeightLabel, c);
	    
	    //goalActivityLabel
	    goalActivityLabel = new JLabel("Moderate Activity");
	    goalActivityLabel.setHorizontalAlignment(JLabel.CENTER);
	    goalActivityLabel.setPreferredSize(GUI.SLIDER_LABEL);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 3;
	    c.gridy = 4;
	    gTab.add(goalActivityLabel, c);
	    
	    //goalBodyFatLabel
	    goalBodyFatLabel = new JLabel(Integer.toString(goalBodyFatSlider.getValue()) + "% Body Fat");
	    goalBodyFatLabel.setHorizontalAlignment(JLabel.CENTER);
	    goalBodyFatLabel.setPreferredSize(GUI.SLIDER_LABEL);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 4;
	    c.gridy = 4;
	    gTab.add(goalBodyFatLabel, c);
	    
	    
	    Dimension goalLabels = new Dimension(200,50);
	    
	    //goalCalories
	    targetCalories = new JLabel();
	    targetCalories.setHorizontalAlignment(JLabel.CENTER);
	    targetCalories.setPreferredSize(goalLabels);
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.gridx = 5;
	    c.gridy = 0;
	    gTab.add(targetCalories, c);
	    
	    //goalAgeLabel
	    targetBMR = new JLabel(Integer.toString(goalBodyFatSlider.getValue()));
	    targetBMR.setHorizontalAlignment(JLabel.CENTER);
	    targetBMR.setPreferredSize(goalLabels);
	    c.gridx = 6;
	    c.gridy = 0;
	    gTab.add(targetBMR, c);
	    
	    //goalHeightLabel
	    targetProtein = new JLabel("6'2\"");
	    targetProtein.setHorizontalAlignment(JLabel.CENTER);
	    targetProtein.setPreferredSize(goalLabels);
	    c.gridx = 5;
	    c.gridy = 1;
	    gTab.add(targetProtein, c);
	    
	    //goalWeightLabel
	    targetTDEE = new JLabel(Integer.toString(goalWeightSlider.getValue()) + " lbs");
	    targetTDEE.setHorizontalAlignment(JLabel.CENTER);
	    targetTDEE.setPreferredSize(goalLabels);
	    c.gridx = 6;
	    c.gridy = 1;
	    gTab.add(targetTDEE, c);
	    
	    //goalActivityLabel
	    targetFat = new JLabel("Moderate Activity");
	    targetFat.setHorizontalAlignment(JLabel.CENTER);
	    targetFat.setPreferredSize(goalLabels);
	    c.gridx = 5;
	    c.gridy = 2;
	    gTab.add(targetFat, c);
	    
	    //goalBodyFatLabel
	    targetGoal = new JLabel(Integer.toString(goalBodyFatSlider.getValue()) + "% Body Fat");
	    targetGoal.setHorizontalAlignment(JLabel.CENTER);
	    targetGoal.setPreferredSize(goalLabels);
	    c.gridx = 6;
	    c.gridy = 2;
	    gTab.add(targetGoal, c);
	    
	    //targetCarbs
	    targetCarbs = new JLabel("Moderate Activity");
	    targetCarbs.setHorizontalAlignment(JLabel.CENTER);
	    targetCarbs.setPreferredSize(goalLabels);
	    c.gridx = 5;
	    c.gridy = 2;
	    gTab.add(targetCarbs, c);
	    
	    //goalBodyFatLabel
	    targetDiet = new JLabel(Integer.toString(goalBodyFatSlider.getValue()) + "% Body Fat");
	    targetDiet.setHorizontalAlignment(JLabel.CENTER);
	    targetDiet.setPreferredSize(goalLabels);
	    c.gridx = 6;
	    c.gridy = 2;
	    gTab.add(targetDiet, c);
	    
	    //goalPieChart
	    goalPieChart = new JLabel("PIE CHART");
	    goalPieChart.setHorizontalAlignment(JLabel.CENTER);
	    c.gridwidth = 2;
	    c.gridheight = 4;
	    c.gridx = 5;
	    c.gridy = 3;
	    gTab.add(goalPieChart, c);
	    
	    //goalCalculateButton
	    goalCalculateButton = new JButton("SUBMIT");
	    goalCalculateButton.setHorizontalAlignment(JLabel.CENTER);
	    goalCalculateButton.addActionListener(new GoalSubmitListener());
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.gridx = 5;
	    c.gridy = 7;
	    gTab.add(goalCalculateButton, c);
	}
	
	public class GoalSubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean g = true;
			int d = 1;
			
			GUI.client.getPersonalTarget().setName(goalName.getText());
			
			if (goalMale.isSelected()) { g = true; }
			else if (goalFemale.isSelected()) { g = false; }
			GUI.client.getPersonalTarget().setMale(g);
			
			if (goalCut.isSelected()) { d = 0; }
			else if (goalMaintain.isSelected()) { d = 1; }
			else if (goalBulk.isSelected()) { d = 2; }
			GUI.client.getPersonalTarget().setDiet(d);
			
			GUI.client.getPersonalTarget().setAge(goalAgeSlider.getValue());
			GUI.client.getPersonalTarget().setHeight(goalHeightSlider.getValue());
			GUI.client.getPersonalTarget().setWeight(goalWeightSlider.getValue());
			GUI.client.getPersonalTarget().setLifestyle(goalActivitySlider.getValue()/10);
			GUI.client.getPersonalTarget().setBodyFat(goalBodyFatSlider.getValue());
			
			//targetCalories.setText(client.getPersonalTarget().getCalories()));
			targetBMR.setText(Integer.toString(GUI.client.getPersonalTarget().getAverageBMR()));
			//targetProtein.setText(Integer.toString(client.getPersonalTarget().getProtein()));
			targetTDEE.setText(Integer.toString(GUI.client.getPersonalTarget().getTDEE()));
			//targetFat.setText(Integer.toString(client.getPersonalTarget().getFat()));
			//targetCarbs.setText(Integer.toString(client.getPersonalTarget().getCarbs()));
			targetDiet.setText(Integer.toString(GUI.client.getPersonalTarget().getLifstyle()));
		}
		
	}
	
	public class goalSliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == goalAgeSlider) {
				int val = goalAgeSlider.getValue();
				goalAgeLabel.setText("" + val);
			}
			else if(e.getSource() == goalHeightSlider) {
				int val = goalHeightSlider.getValue();
				int feet = val/12;
				int inches = val%12;
				goalHeightLabel.setText("" + feet + "'" + inches + "\"");
			}
			else if(e.getSource() == goalWeightSlider) {
				int val = goalWeightSlider.getValue();
				goalWeightLabel.setText("" + val + " lbs");
			}
			else if(e.getSource() == goalActivitySlider) {
				int val = goalActivitySlider.getValue();
				String s = "";
				if (val < 2) { s = "Sedentary"; }
				else if (val >= 2 && val < 4) { s = "Light Activity"; }
				else if (val >= 4 && val <= 6) { s = "Moderate Activity"; }
				else if (val > 6 && val <= 8) { s = "Heavy Activity"; }
				else if (val > 8 && val <= 10) { s = "Athlete"; }
				goalActivityLabel.setText(s);
			}
			else if(e.getSource() == goalBodyFatSlider) {
				int val = goalBodyFatSlider.getValue();
				goalBodyFatLabel.setText("" + val + "% Body Fat");
			}
		}
	}
	
	public JPanel getGoalTab() { return this.goalTabPanel; }
	
}
