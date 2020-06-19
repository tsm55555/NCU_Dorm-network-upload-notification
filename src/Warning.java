import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Warning  {
	@FXML
	Label noinput;
	
	@FXML
	TextField limit;
	
	@FXML
	Button btn; 
		
	public void onBackPressed() {
		finalproject.currentStage.setScene(finalproject.menuScene);		
	}
	
	public void onresetPressed() {
		finalproject.hasnotif=0;		
	}
	
	public void onConfirmPressed() throws IOException, AWTException{
		String str = limit.getText();
		File file = new File("limit.txt");
		PrintWriter writer = new PrintWriter(file); 
		writer.write(str);
		writer.close();
		limit.setText("");

		if(str.equals("snake")) {
			finalproject.startSnake();
			finalproject.currentStage.close();
		}
		else if(!str.equals("")) {

			finalproject.netflowsearch();
			finalproject.notifbackground();
			finalproject.currentStage.hide();		
			Platform.setImplicitExit(false);
			/*SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
			TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
	        trayIcon.setToolTip("System tray icon demo");
	        tray.add(trayIcon);*/	
			/*if(finalproject.hasnotif<=1) {
				btn.setVisible(true);
			}*/
		}
		else {
			noinput.setVisible(true);
		}
	}

}