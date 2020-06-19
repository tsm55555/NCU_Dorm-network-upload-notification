import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class finalproject extends Application {
	public static Stage currentStage;
	public static Stage snakeStage;
	public static Scene menuScene;
	public static snake snakeGame;
	public static String[] flow = new String[4];
	public static String[] sus = new String[3];
	public static String ipstr="";
	public static String from="";
	public static int count=0;
	public static int hasnotif = 0;
	public static void startSnake() {
		snakeGame.start(snakeStage);
		snakeStage.show();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		currentStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		menuScene = new Scene(root);
		currentStage.setTitle("宿網小工具");
		currentStage.setScene(menuScene);
		currentStage.show();
		snakeStage = new Stage();

	}

	public static void main(String[] args) throws AWTException {
		snakeGame = new snake();
    	launch(args);
    	}
	
	public static  void netflowsearch() throws IOException {
		//my ip: 140.115.204.240
        var url = "https://uncia.cc.ncu.edu.tw/dormnet/index.php?section=netflow";
        var urlParameters = "ip="+ipstr;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        HttpURLConnection con = null;
        
        try {
            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            try (var wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }
            StringBuilder content;
            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }		                    
            int myStart = 0;
            int pos2;
            int count = 0;
            while(true) {
            	int pos = content.toString().indexOf("GB",myStart);
            	 if(myStart==0) {
            	        pos2 = content.toString().indexOf("(", pos-10);
            	 }
            	 else {
            	        pos2 = content.toString().indexOf("(", myStart);
            	   }
            	 if (pos == -1) {
            		 break;
            	 }
            	 myStart = pos+1;
            	 count = count+1;
            	 if(count==1) {
            		 flow[0] = content.toString().substring(pos2+1, pos-1);
            	 }
            	 if(count==2) {
            		 flow[1] = content.toString().substring(pos2+1, pos-1);
            	 }
            	 if(count==3) {
            		 flow[2] = content.toString().substring(pos2+1, pos-1);
            	 }
            	 if(count==4) {
            		 flow[3] = content.toString().substring(pos2+1, pos-1);
            	 }	                
            }
        } 
		finally {
            con.disconnect();
        }
    }	
	
	public static void suspendsearch() throws IOException {
		var url = "https://uncia.cc.ncu.edu.tw/dormnet/index.php?section=whylock";//鎖卡/ip查詢
		var urlParameters = "param[ip]="+finalproject.ipstr;
		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		HttpURLConnection con = null;

		try {
		    var myurl = new URL(url);
		    con = (HttpURLConnection) myurl.openConnection();
		    con.setDoOutput(true);
		    con.setRequestMethod("POST");
		    con.setRequestProperty("User-Agent", "Java client");
		    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    try (var wr = new DataOutputStream(con.getOutputStream())) {
		        wr.write(postData);
		    }
		    StringBuilder content;
		    try (var br = new BufferedReader(
		            new InputStreamReader(con.getInputStream()))) {

		        String line;
		        content = new StringBuilder();

		        while ((line = br.readLine()) != null) {
		            content.append(line);
		            content.append(System.lineSeparator());
		        }
		    }
		    int myStart = 0;
		    int count = 0;
		   // System.out.println(content);
		    while(true) {
		    	
	           	int pos = content.toString().indexOf("NO",myStart);
           	    if (pos == -1) {
           	    	break;
           	    }              
           	 myStart = pos+1;
        	 count = count+1;
        	 if(count==1) {
        		 if(content.toString().substring(9745, 9745+2).equals("NO")) {
        			 sus[0] = "NO";
        			//System.out.println(pos);
        		 }
        		 else {
        			 sus[0] = "YES";
        		 }
        		 //System.out.println(content.toString().substring(pos, pos+2));
        	 }
        	 if(count==2) {
        		 if(content.toString().substring(10107, 10107+2).equals("NO")) {
        			 sus[1] = "NO";
        			// System.out.println(pos);
        		 }
        		 else {
        			 sus[1] = "YES";
        		 }
        		 //System.out.println(content.toString().substring(pos, pos+2));
        	 }
        	 if(count==3) {
        		 if(content.toString().substring(10131, 10131+2).equals("NO")) {
        			 sus[2] = "NO";
        			// System.out.println(pos);
        		 }
        		 else {
        			 sus[2] = "YES";
        		 }
        		 //System.out.println(content.toString().substring(pos, pos+2));
        	 }  	 
		    }
		} 
		finally {
		    con.disconnect();
		}
	}
	
	public static void notifbackground() throws IOException {
		FileReader reader = new FileReader("limit.txt");
		BufferedReader br2 = new BufferedReader(reader);
		String setlimitstr = br2.readLine();
		float setlimit = Float.parseFloat(setlimitstr);
		br2.close();
		new AnimationTimer() {
		public void handle(long now) {
			float limit = Float.parseFloat(flow[0]);
			try {
				netflowsearch();
				//System.out.println(flow[0]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(hasnotif<1) {
				if(limit > setlimit) {
			        notif td = new notif();//呼叫通知
			    	try {
						td.displayTray();
						//System.out.println("1");
					} catch (AWTException e) {
						e.printStackTrace();
					}
			    	finalproject.currentStage.setScene(menuScene);
			    	currentStage.show();
			    	this.stop();
				}
			}

			}
		}.start();	
	}
}
	
 

