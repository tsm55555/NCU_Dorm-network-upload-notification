import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class snake extends Application {
	static int speed = 5;
	static int foodcolor = 0;
	static int width = 20;
	static int height = 20;
	static int cs = 25;
	static int foodX = 0;
	static int foodY = 0;
	static List<Corner> snake = new ArrayList<>();
	static Dir direction = Dir.left;
	static boolean GameOver = false;
	static Random rand = new Random();

	public enum Dir {
		left, right, up, down
	}

	public static class Corner {
		int x;
		int y;

		public Corner(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			Canvas c = new Canvas(width * cs, height * cs);
			GraphicsContext gc = c.getGraphicsContext2D();
			root.getChildren().add(c);
			newFood();
			new AnimationTimer() {
				long lastTick = 0;

				public void handle(long now) {
					if (lastTick == 0) {
						lastTick = now;
						tick(gc);
						return;
					}

					if (now - lastTick > 1000000000 / speed) {
						lastTick = now;
						tick(gc);
					}
				}

			}.start();

			Scene scene = new Scene(root, width * cs, height * cs);

			scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
				if (key.getCode() == KeyCode.W) {//wasd & 上下左右
					direction = Dir.up;
				}
				if (key.getCode() == KeyCode.A) {
					direction = Dir.left;
				}
				if (key.getCode() == KeyCode.S) {
					direction = Dir.down;
				}
				if (key.getCode() == KeyCode.D) {
					direction = Dir.right;
				}
				if (key.getCode() == KeyCode.UP) {
					direction = Dir.up;
				}
				if (key.getCode() == KeyCode.LEFT) {
					direction = Dir.left;
				}
				if (key.getCode() == KeyCode.DOWN) {
					direction = Dir.down;
				}
				if (key.getCode() == KeyCode.RIGHT) {
					direction = Dir.right;
				}

			});
			snake.add(new Corner(width / 2, height / 2));
			snake.add(new Corner(width / 2, height / 2));
			snake.add(new Corner(width / 2, height / 2));
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("SNAKE GAME");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void newFood() {
		start: while (true) {
			foodX = rand.nextInt(width);
			foodY = rand.nextInt(height);

			for (Corner c : snake) {
				if (c.x == foodX && c.y == foodY) {
					continue start;
				}
			}
			foodcolor = rand.nextInt(5);
			speed++;
			break;
		}
	}

	public static void tick(GraphicsContext gc) {
		if (GameOver) {
			gc.setFill(Color.RED);
			gc.setFont(new Font("", 50));
			if((speed - 6)==0) {
				gc.fillText("Retarded", 140, 250);
			}
			else if((speed - 6)<3) {
				gc.fillText("Weak", 180, 250);	
			}
			else {
				gc.fillText("GAME OVER", 100, 250);
			}
			return;
		}

		for (int i = snake.size() - 1; i >= 1; i--) {
			snake.get(i).x = snake.get(i - 1).x;
			snake.get(i).y = snake.get(i - 1).y;
		}

		switch (direction) {
		case up:
			snake.get(0).y--;
			if (snake.get(0).y < 0) {
				GameOver = true;
			}
			break;
		case down:
			snake.get(0).y++;
			if (snake.get(0).y > height) {
				GameOver = true;
			}
			break;
		case left:
			snake.get(0).x--;
			if (snake.get(0).x < 0) {
				GameOver = true;
			}
			break;
		case right:
			snake.get(0).x++;
			if (snake.get(0).x > width) {
				GameOver = true;
			}
			break;

		}

		if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
			snake.add(new Corner(-1, -1));
			newFood();
		}

		for (int i = 1; i < snake.size(); i++) {
			if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
				GameOver = true;
			}
		}
		
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width * cs, height * cs);

		gc.setFill(Color.WHITE);
		gc.setFont(new Font("", 30));
		gc.fillText("分數: " + (speed - 6), 10, 30);

		Color fc = Color.WHITE;

		switch (foodcolor) {
		case 0:
			fc = Color.PURPLE;
			break;
		case 1:
			fc = Color.ALICEBLUE;
			break;
		case 2:
			fc = Color.YELLOW;
			break;
		case 3:
			fc = Color.PINK;
			break;
		case 4:
			fc = Color.AZURE;
			break;
		}
		gc.setFill(fc);
		gc.fillOval(foodX * cs, foodY * cs, cs, cs);

		for (Corner c : snake) {
			gc.setFill(Color.LIGHTGREEN);
			gc.fillRect(c.x * cs, c.y * cs, cs - 1, cs - 1);
			gc.setFill(Color.GREEN);
			gc.fillRect(c.x * cs, c.y * cs, cs - 2, cs - 2);

		}

	}


}
