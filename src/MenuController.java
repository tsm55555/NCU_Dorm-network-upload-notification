import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuController {

	@FXML
	public void onOverPressed() throws IOException {
		Parent search = FXMLLoader.load(getClass().getResource("Search.fxml")); 
		Scene searchScene = new Scene(search);
		Parent suspend = FXMLLoader.load(getClass().getResource("suspendinfo.fxml")); 
		Scene suspendScene = new Scene(suspend);
		suspendScene.getRoot().requestFocus();
		FileReader reader = new FileReader("ip.txt");
		BufferedReader br2 = new BufferedReader(reader);
		finalproject.ipstr = br2.readLine();
		br2.close();
		if(finalproject.ipstr==null) {
			searchScene.getRoot().requestFocus();
   			finalproject.from ="1";
		    finalproject.currentStage.setScene(searchScene);		    
		}
		else if(finalproject.ipstr != null) {
			finalproject.suspendsearch();
		    finalproject.currentStage.setScene(suspendScene);
		}
	}
	
	@FXML
	public void onSearchPressed() throws IOException, InterruptedException {
		Parent search = FXMLLoader.load(getClass().getResource("Search.fxml")); 
		Scene searchScene = new Scene(search);

		Parent netflow = FXMLLoader.load(getClass().getResource("netflow.fxml")); 
		Scene netflowhScene = new Scene(netflow);
		
		FileReader reader = new FileReader("ip.txt");
		BufferedReader br2 = new BufferedReader(reader);
		finalproject.ipstr = br2.readLine();
		br2.close();
		if (finalproject.ipstr == null) {
			searchScene.getRoot().requestFocus();
		    finalproject.currentStage.setScene(searchScene);
		}
		else if (finalproject.ipstr != null) {
			finalproject.netflowsearch();
    		finalproject.currentStage.setScene(netflowhScene);
		}
	  }
	
	@FXML
	public void onWarningPressed() throws IOException, InterruptedException {
		FileReader reader = new FileReader("ip.txt");
		BufferedReader br2 = new BufferedReader(reader);
		finalproject.ipstr = br2.readLine();
		br2.close();
		Parent Warning = FXMLLoader.load(getClass().getResource("Warning.fxml")); 
		Scene WarningScene = new Scene(Warning);
		finalproject.currentStage.setScene(WarningScene);	
	}
	
	@FXML
	public void onSettingPressed() throws IOException{
		Parent Setting = FXMLLoader.load(getClass().getResource("Setting.fxml")); 
		Scene SettingScene = new Scene(Setting);
		SettingScene.getRoot().requestFocus();
		finalproject.currentStage.setScene(SettingScene);
	}
	
	@FXML
	public void onClosePressed() throws IOException{
		finalproject.currentStage.close();	
		Platform.exit();
	}
}
	
/*String a="https://uncia.cc.ncu.edu.tw/dormnet/index.php?section=flowdel";//流量超過 - 鎖卡資料
URL url;
try {		 
	 url = new URL(a);
     URLConnection conn = url.openConnection();
     BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     String inputLine; 
     int count = 0;
     while ((inputLine = br.readLine()) != null) {
    	 boolean isFound = inputLine.contains("140.115");
    	 if(isFound==true) {
    		 int index = inputLine.indexOf("</TD>");
        	 System.out.print(inputLine.substring(6, index) +" ");
        	 count++;
    	 }
    	 boolean isFound2 = inputLine.contains("MB");
    	 if(isFound2==true) {
    		 int index = inputLine.indexOf(">");
    		 int index2 = inputLine.indexOf("MB");
        	 System.out.println(inputLine.substring(index+1, index2) + "MB");
    	 }
    	 }
     System.out.println(count);
     br.close();
     } 
catch (MalformedURLException e) {
	e.printStackTrace();
	} 
catch (IOException e) {
	e.printStackTrace();
	}*/



