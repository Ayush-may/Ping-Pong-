import java.util.*;

public class Ball {
	
	private double speed;
	private int ballSize;
	private double ballX, ballY;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;
	private int paddleHeight, paddleWidth;
	private double velocityX, velocityY;
	private boolean right;
	private Random random;
	private Score player1Score;
	private Score player2Score;
	
	Ball(int ballSize, int ballX,int ballY,int SCREEN_HEIGHT,int SCREEN_WIDTH,int speed){
		this.ballSize = ballSize;
		this.ballX = ballX;
		this.ballY = ballY;
		this.speed = speed;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT;
		this.SCREEN_WIDTH = SCREEN_WIDTH;
		random = new Random();
		this.ballX = SCREEN_WIDTH/2;
		right = false;
		setInitBall();
	}
	public void move() {
		ballX += velocityX;
		ballY += velocityY;
		
		if(ballY + ballSize > SCREEN_HEIGHT) {
			velocityY = -velocityY;
		}
		if(ballX + ballSize > SCREEN_WIDTH) {
			right = true;
			player2Score.setScore();
			setInitBall();  
		}
		if(ballY < 0) {
			velocityY = -velocityY;
		}
		if(ballX < 0) {
			right = false;
			player1Score.setScore();
			setInitBall();
		}
	}
	public void collision(int paddleX,int paddleY,int paddle2X, int paddle2Y) {
		//Left direction Ball Collision
		if( (ballX < (paddleX + paddleWidth + 5)) && ((ballY + ballSize/2) > paddleY - 5) && ((ballY + ballSize/2) < (paddleY + paddleHeight + 5)) ) {
			setSpeed();
			if( (int)Math.abs(paddleY - (ballY+ballSize)) < paddleHeight/4 ) {
				if(velocityY < 0 && velocityX < 0) {
					velocityX = (Math.abs(velocityX) + 3);
				}
				if(velocityX < 0 && velocityY > 0) {
					velocityX = (Math.abs(velocityX) + 3);
				}
			}
			else if((int)Math.abs(paddleY - (ballY+ballSize)) < (paddleHeight/4)*2 ) {
				if(velocityY < 0 && velocityX < 0) {
					velocityX = Math.abs(velocityX) + 1;
				}
				if(velocityX < 0 && velocityY > 0) {
					velocityX = Math.abs(velocityX) + 1;
				}				
			}
			else if((int)Math.abs(paddleY - (ballY+ballSize)) < (paddleHeight/4)*3 ) {
				if(velocityY < 0 && velocityX < 0) {
					velocityX = Math.abs(velocityX) + 1;
				}
				if(velocityX < 0 && velocityY > 0) {
					velocityX = Math.abs(velocityX) + 1;
				}				
			}
			else if((int)Math.abs(paddleY - (ballY+ballSize)) < (paddleHeight/4)*4 ) {
				System.out.println("part 1");
				//down to top
				if(velocityY < 0 && velocityX < 0) {
					velocityX = (Math.abs(velocityX) + 3);
				}
				//top to down
				if(velocityX < 0 && velocityY > 0) {
					velocityX = (Math.abs(velocityX) + 3);
				}
			}
		}
		//This Section is fully done !! finally
		//Right paddle
		//Right direction Ball Collision
		if( (ballX > paddle2X-10) && ((ballY+ballSize/2) > paddle2Y - 5) && ((ballY + ballSize/2) < (paddle2Y + paddleHeight + 5))   )  {
			setSpeed();
			if( (int)Math.abs(paddle2Y - (ballY)) < paddleHeight/4 ) {
				if(velocityY > 0 && velocityX > 0) {
					velocityX = -(velocityX + 3);
				}
				if(velocityX > 0 && velocityY < 0) {
					velocityX  = -(velocityX + 3);
				}
			}
			else if((int)Math.abs(paddle2Y - (ballY)) < (paddleHeight/4)*2 ) {
				if(velocityY > 0 && velocityX > 0) {
					velocityX = -(velocityX + 1);
				}
				if(velocityX > 0 && velocityY < 0) {
					velocityX = -(velocityX + 1);
				}				
			}
			else if((int)Math.abs(paddle2Y - (ballY)) < (paddleHeight/4)*3 ) {
				if(velocityY > 0 && velocityX > 0) {
					velocityX = -(velocityX + 1);
				}
				if(velocityX > 0 && velocityY < 0) {
					velocityX = -(velocityX + 1);
				}				
			}
			else if((int)Math.abs(paddle2Y - (ballY)) < paddleHeight ) {
				if(velocityY > 0 && velocityX > 0) {
					velocityX = -(velocityX + 3);
				}
				if(velocityX > 0 && velocityY < 0) {
					velocityX = -(velocityX + 3);
				}
			}
		}	
	}
	public void setInitBall() {
		int dir;
		this.ballX = SCREEN_WIDTH/2;
		ballY = random.nextInt(SCREEN_HEIGHT);
		speed = 4;
		if(right) {
			velocityX = speed;
			dir = random.nextInt(2);
			if(dir == 1)
				velocityY = speed;
			else
				velocityY = -speed;			
		}
		else {
			velocityX = -speed;
			dir = random.nextInt(2);
			if(dir == 1)
				velocityY = speed;
			else
				velocityY = -speed;
		}
	}
	public void setSpeed() {
		double cnstSpeed = 0.5;
		velocityX += cnstSpeed;
		speed += cnstSpeed;
	}
	public void reset() {
		setInitBall();
		player1Score.resetScore();
		player2Score.resetScore();
	}
	public void setScoreReference(Score s1, Score s2) {
		player1Score = s1;
		player2Score = s2;
	}
	public void setPaddleWidth(int x) {
		this.paddleWidth = x;
	}
	public void setPaddleHeight(int y) {
		this.paddleHeight = y;
	}
	public int getSize() {
		return ballSize;
	}
	public double getX() {
		return ballX;
	}
	public double getY() {
		return ballY;
	}
}
