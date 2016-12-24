import java.awt.*;
import javax.swing.*;

public class WeekTab {
	
	private JPanel weekTabPanel = new JPanel();
	private JLabel week;
	
	public WeekTab() {
		buildWeekTab(this.weekTabPanel);
	}
	
	private void buildWeekTab(Container weekTab) {
		week = new JLabel("week meals");
		weekTab.add(week);
		}
	
	public JPanel getWeekTab() { return this.weekTabPanel; }
	
}