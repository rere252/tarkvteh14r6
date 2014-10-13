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
		ImageIcon img = new ImageIcon("img//beer.jpg");
		JLabel pilt = new JLabel(img);
		pilt.setIcon(img);
		File file = new File("application.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		Enumeration<Object> enuKeys = properties.keys();
		JLabel textLabel = new JLabel();
		JFrame frame = new JFrame("beeroverflow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (enuKeys.hasMoreElements()) {
			String key = (String) enuKeys.nextElement();
			String value = properties.getProperty(key);
			textLabel.setText( textLabel.getText() +"<html>"+ key.replace('_', ' ') + ": " + value + " <br>"+ "<html>");
		}	
			
		frame.getContentPane().add(textLabel, BorderLayout.PAGE_START);
		frame.getContentPane().add(pilt, BorderLayout.AFTER_LINE_ENDS);
		textLabel.setPreferredSize(new Dimension(180, 150));
		frame.pack();
		frame.setVisible(true);	
