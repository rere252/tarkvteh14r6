package ee.ut.math.tvt.beeroverflow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class IntroUI extends JPanel{
	
	public static void createWindow(){
		JLabel textLabel;
		Properties appProperties;
		Properties verProperties;
		JFrame frame = new JFrame("BeerOverflow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		File appFile = new File("application.properties");
		File verFile = new File("version.properties");
		String teamKey = "Our_team_logo";
		String verKey = "version.number";
	    Box box = Box.createVerticalBox();

		//Loading the files in
		appProperties = makeProperties(appFile);
		verProperties = makeProperties(verFile);

	    // Deals with application.properties labels
		Enumeration<Object> appKeys = appProperties.keys();
		while (appKeys.hasMoreElements()) {
			String key = (String) appKeys.nextElement();
			String value = appProperties.getProperty(key);
			if(!key.equals(teamKey)) {
				textLabel = new JLabel(key.replace('_', ' ') + ": " + value);
				box.add(textLabel);
			}
		}
		
		// Insert the version number
		String versionNum = verProperties.getProperty(verKey);
		box.add(new JLabel("Version: " + versionNum));
		
		// Insert the logo
		ImageIcon img = new ImageIcon(appProperties.getProperty(teamKey));
		JLabel pilt = new JLabel(img);
		pilt.setIcon(img);
		box.add(pilt);
		
		// Compose the frame
		frame.add(box);
		frame.pack();
		frame.setVisible(true);
	}
	
	private static Properties makeProperties(File f){
		FileInputStream fis;
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(f);
			prop = new Properties();
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		return prop;
	}

}
