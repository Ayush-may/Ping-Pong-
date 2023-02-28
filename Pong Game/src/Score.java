import javax.swing.*;
import java.awt.*;

public class Score extends JLabel{
	private int limit;
	private int score;
	Score(int x, int y){
		this.setText(score + "");
		this.setBounds(x,y,20,20);
		this.setFont(new Font("Arial", Font.BOLD, 60));
		limit = 5;
		this.setVisible(true);
	}
	public void setScore() {
		score++;
		this.setText(score + "");
	}
	public boolean check() {
		if(score == limit){
			return true;
		}
		return false;
	}
	public void changeColor(Color color) {
		this.setForeground(color);
	}
	public void resetScore() {
		score = 0;
		this.setText(score + "");
	}
}
