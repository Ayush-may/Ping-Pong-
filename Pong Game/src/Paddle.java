
public class Paddle {
	
	private int paddleDimX;
	private int paddleDimY;
	private int paddleX ;
	private int paddleY ;
	private int SCREEN_HEIGHT;
	private int velocityY;
	
	Paddle(){}
	Paddle(int paddleDimX,int paddleDimY,int paddleX, int paddleY,int SCREEN_HEIGHT,int SCREEN_WIDTH){
		this.paddleX = paddleX;
		this.paddleY = paddleY;
		this.paddleDimX = paddleDimX;
		this.paddleDimY = paddleDimY;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT;
	}
	public void AIMove(int ballX, int ballY) {
		
	}
	public void collision() {
		if(paddleY < 0) {
			paddleY = 0;
		}
		if(paddleY > SCREEN_HEIGHT - paddleDimY) {
			paddleY = SCREEN_HEIGHT-paddleDimY; 
		}
	}
	public void moveY(int y) {
		velocityY = y;
		this.paddleY -=y;
	}
	public void move() {
		paddleY += velocityY;
	}
	public void resetPaddle() {
		paddleY = SCREEN_HEIGHT/2 - paddleDimY; 
	}
	public int getDimX() {
		return paddleDimX;
	}
	
	public int getDimY() {
		return paddleDimY;
	}
	
	public int getX() {
		return paddleX;
	}
	
	public int getY() {
		return paddleY;
	}	
}
