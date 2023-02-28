import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener , KeyListener{
	
	private static final int SCREEN_WIDTH = 1600;
	private static final int SCREEN_HEIGHT = 400;
	private static final int DELAY = 2;
	private Timer timer;
	private Paddle player1;
	private Paddle player2;
	private Ball ball;
	private int speed;
	private Score player1Score;
	private Score player2Score;
	private boolean running, left;
	private JButton replay;
	
	GamePanel(){
		this.setLayout(null);
		running = true;
		player1 = new Paddle(16,120,0,0,SCREEN_HEIGHT,SCREEN_WIDTH);
		player2 = new Paddle(16,120,SCREEN_WIDTH-16,10,SCREEN_HEIGHT,SCREEN_WIDTH);
		player1Score = new Score((SCREEN_WIDTH/2)-100,0);
		player2Score = new Score((SCREEN_WIDTH/2)+100,0);
		player1Score.setBounds((SCREEN_WIDTH/2)-50, 10, 50, 50);
		player2Score.setBounds((SCREEN_WIDTH/2) + 15, 10, 50, 50);
		ball = new Ball(16,0,0,SCREEN_HEIGHT,SCREEN_WIDTH,10);
		replay = new JButton("REPLAY");
		replay.setBounds(SCREEN_WIDTH/2-50, 70,100,30);
		replay.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.add(replay);
		this.add(player1Score);
		this.add(player2Score);
		player1Score.changeColor(Color.BLUE);
		player2Score.changeColor(Color.RED);
		ball.setPaddleHeight(player1.getDimY());
		ball.setPaddleWidth(player1.getDimX());
		timer = new Timer(DELAY, this);
		timer.start();
		speed = 15;
		replay.setFocusable(false);
		ball.setScoreReference(player1Score, player2Score);
		replay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ball.reset();
					player1.resetPaddle();
					player2.resetPaddle();
					running = true;
					repaint();
					timer.restart();
			}
		});
		ball.reset();
		player1.resetPaddle();
		player2.resetPaddle();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(running) {
			g.setColor(Color.BLUE);
			g.fillRect(player1.getX(),player1.getY(),player1.getDimX(),player1.getDimY());
			g.setColor(Color.RED);
			g.fillRect(player2.getX(),player2.getY(),player2.getDimX(),player2.getDimY());
			g.setColor(Color.WHITE);
			g.drawLine(SCREEN_WIDTH/2, 0, SCREEN_WIDTH/2, SCREEN_WIDTH);
			g.fillOval((int)ball.getX(), (int)ball.getY(), ball.getSize(), ball.getSize());			
		}
		else {
			g.drawLine(SCREEN_WIDTH/2, 0, SCREEN_WIDTH/2, SCREEN_HEIGHT/3);
			if(left) {
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.BOLD, 60)) ;
				g.drawString("RED WINNSS!!!", SCREEN_WIDTH/3 + 50, SCREEN_HEIGHT/2);
			}
			else {
				g.setColor(Color.BLUE);
				g.setFont(new Font("Arial", Font.BOLD, 60)) ;
				g.drawString("BLUE WINNSS!!!", SCREEN_WIDTH/3 + 50, SCREEN_HEIGHT/2);
			}
			timer.stop();
		}
}
	
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			ball.move();
			ball.collision(player1.getX(), player1.getY(),player2.getX()-10, player2.getY());
			player1.collision();
			player2.collision();
			checkScore();
			repaint();			
		}
		else {
			timer.stop();
		}
	}
	public void checkScore() {
		if(player1Score.check()) {
			running = false;
			left = true;
		}
		else if(player2Score.check()) {
			running = false;
			left = false;
		}
	}
	//This add smoothness to the paddle
	public void move() {
		player1.move();
		player2.move();
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) {
			player1.moveY(-speed);
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			player1.moveY(speed);
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player2.moveY(-speed);
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player2.moveY(speed);
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) {
			player1.moveY(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			player1.moveY(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player2.moveY(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player2.moveY(0);
		}		
	}
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) {
			player1.moveY(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			player1.moveY(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player2.moveY(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player2.moveY(0);
		}		

	}
}
