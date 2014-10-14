package ee.ut.math.tvt.beeroverflow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroUI extends JPanel{
	
	public static void createWindow() throws IOException{
		JFrame frame = new JFrame("beeroverflow");
		JPanel panel = new JPanel(new BorderLayout());
		JLabel textLabel = new JLabel();
		ImageIcon img = new ImageIcon("img//beer.jpg");
		JLabel pilt = new JLabel(img);
		pilt.setIcon(img);
		File file = new File("application.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		Enumeration<Object> enuKeys = properties.keys();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (enuKeys.hasMoreElements()) {
			String key = (String) enuKeys.nextElement();
			String value = properties.getProperty(key);
			textLabel.setText( textLabel.getText() +"<html>"+ key.replace('_', ' ') + ": " + value + " <br>"+ "<html>");
		}	
			
		panel.add(textLabel, BorderLayout.NORTH);
	    	panel.add(pilt, BorderLayout.SOUTH);
	    	frame.add(panel, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}

}
