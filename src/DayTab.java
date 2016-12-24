import java.awt.*;
import javax.swing.*;

public class DayTab {
	
	private JPanel dayTabPanel = new JPanel();
	private JLabel day;
	
	public DayTab() {
		buildDayTab(this.dayTabPanel);
	}
	
	private void buildDayTab(Container dayTab) {
		day = new JLabel("Days test 2");
		dayTab.add(day);
		}
	
	public JPanel getDayTab() { return this.dayTabPanel; }
	
}
