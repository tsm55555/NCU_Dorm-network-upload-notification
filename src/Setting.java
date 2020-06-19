import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Setting {
	public void onBackPressed() {
		finalproject.currentStage.setScene(finalproject.menuScene);		
	}
	
	public void onNotifPressed() throws AWTException {
        notif td = new notif();//呼叫通知
    	td.displayTray();
	}
	
	public void onipresetPressed() throws IOException {
		File file = new File("ip.txt");//重置ip
		PrintWriter writer = new PrintWriter(file); 
		writer.write("");
		writer.close();
	}
	
	public void onlimitresetPressed() throws IOException {
		File file = new File("limit.txt");//重置流量上限
		PrintWriter writer = new PrintWriter(file); 
		writer.write("");
		writer.close();
	}
}