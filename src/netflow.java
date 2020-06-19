import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class netflow {
	
	@FXML
	Label lab1;
	
	@FXML
	Label lab2;
	
	@FXML
	Label lab3;
	
	@FXML
	Label lab4;
	
	
	@FXML
	public void initialize() throws IOException{
		new AnimationTimer() {
			public void handle(long now) {
				lab1.setText(finalproject.flow[0]);
				lab2.setText(finalproject.flow[1]);
				lab3.setText(finalproject.flow[2]);
				lab4.setText(finalproject.flow[3]);				
			}

		}.start();
	}
	

	public void onBackPressed() {
		finalproject.currentStage.setScene(finalproject.menuScene);
	}
}