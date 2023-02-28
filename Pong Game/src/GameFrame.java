import javax.swing.*;


public class GameFrame extends JFrame {
	GameFrame(){
		this.setTitle("Ping Pong Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200,100);
		this.add(new GamePanel());
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
}
