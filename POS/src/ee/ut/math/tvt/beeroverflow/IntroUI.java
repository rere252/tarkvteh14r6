package ee.ut.math.tvt.beeroverflow;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IntroUI extends JPanel{
	
	public static void createWindow(){
		JFrame frame = new JFrame("beeroverflow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ImageIcon imgThisImg = new ImageIcon("logo.jpg");
		//JLabel pilt = new JLabel(imgThisImg);
		//pilt.setIcon(imgThisImg);
		JLabel textLabel = new JLabel("<html>Team name: Beeroverflow<br>"
				+ "Team leader: Rene Juuse<br>"
				+ "Team leader e-mail: rene.juuse@gmail.com<br>"
				+ "Team members: Iris Merilo, Airis Kruus, Ann Lember<br>"
				+ "Logo: <br>"
				+ "<IMG SRC=http://www.manatee-lanes.com/wp-content/uploads/2012/04/iStock_000018060649XSmall.jpg>  </html>",SwingConstants.CENTER);
		frame.getContentPane().add(textLabel, BorderLayout.PAGE_START);
		//frame.getContentPane().add(pilt, BorderLayout.AFTER_LINE_ENDS);
		textLabel.setPreferredSize(new Dimension(300, 500));
		frame.pack();
		frame.setVisible(true);		
	}
	
}
