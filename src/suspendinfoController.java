import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class suspendinfoController {
	
	@FXML
	Label no1;
	
	@FXML
	Label no2;
	
	@FXML
	Label no3;
	
	@FXML
	Label yes1;
	
	@FXML
	Label yes2;
	
	@FXML
	Label yes3;
	

	@FXML
	public void initialize() throws IOException{
		finalproject.sus[0] = "NO";
		finalproject.sus[1] = "NO";
		finalproject.sus[2] = "NO";
    	finalproject.suspendsearch();
		 new AnimationTimer() {
			public void handle(long now) {
				//System.out.println(finalproject.sus[0]);
				if(finalproject.sus[0].equals("NO")) {
					no1.setVisible(true);
				}
				else {
					yes1.setVisible(true);
				}
				if(finalproject.sus[1].equals("NO")) {
					no2.setVisible(true);
				}
				else {
					yes2.setVisible(true);
				}
				if(finalproject.sus[2].equals("NO")) {
					no3.setVisible(true);
				}
				else {
					yes3.setVisible(true);
				}	
			}		
		}.start();	

	}

	public void onBackPressed() {
		finalproject.currentStage.setScene(finalproject.menuScene);		
	}
}
