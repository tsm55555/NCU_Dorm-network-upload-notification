import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;


public class SearchController {
	
	@FXML
	TextField ip;
	
	@FXML
	public void onBackPressed() {
		finalproject.currentStage.setScene(finalproject.menuScene);	
	}
	@FXML
	public void onSubmitPressed() throws IOException {
		Parent netflow = FXMLLoader.load(getClass().getResource("netflow.fxml")); 
		Scene netflowScene = new Scene(netflow);
		netflowScene.getRoot().requestFocus();
		
		finalproject.ipstr = ip.getText();
		File file = new File("ip.txt");//紀錄ip
		PrintWriter writer = new PrintWriter(file); 
		writer.write(finalproject.ipstr);
		writer.close();
		ip.setText("");

        if(finalproject.from.equals("1") && !finalproject.ipstr.equals("")) {
    		finalproject.from ="";
    		Parent suspend = FXMLLoader.load(getClass().getResource("suspendinfo.fxml")); 
    		Scene suspendScene = new Scene(suspend);
    		suspendScene.getRoot().requestFocus();
    		finalproject.currentStage.setScene(suspendScene);
        } 
        else if(!finalproject.ipstr.equals("")) {
        	finalproject.netflowsearch();
    		finalproject.currentStage.setScene(netflowScene);
        		
	}
	}
}