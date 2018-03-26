import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener{
	public static Snake snake;
	public Draw draw;
	public JFrame jframe;
	public static JFrame end;
	public Random random;
	public Timer timer;
	public boolean gameOver = false;
	public ArrayList<Point> body = new ArrayList<Point>();
	public int count = 0, score = 0, tailLength = 5;
	public static final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3, SCALE=10;
	public int direction = DOWN;
	public Point head, apple;

	public Snake() {
		jframe = new JFrame("Snake");
		draw = new Draw();
		timer = new Timer(20, this);
		jframe.setVisible(true);
		jframe.setSize(800, 800);
		jframe.setResizable(false);
		jframe.setLocation(550, 125);

		jframe.add(draw);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.addKeyListener(this);
		game();
		
	}
	
	public void game()
	{
		gameOver=false;
		score=0;
		tailLength=5;
		direction=DOWN;
		head = new Point(0,-1);
		random = new Random();
		body.clear();
		apple = new Point(random.nextInt(78), random.nextInt(76));
		
		for(int i=0; i<tailLength; i++)
		{
			body.add(new Point(head.x, head.y));
		}
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		draw.repaint();
		count++;
		if (count % 2 == 0 && head != null && gameOver != true) {
			body.add(new Point(head.x, head.y));
			if (direction == UP) {
				if (head.y - 1 >= 0 && hitSelf(head.x, head.y - 1)) {
					head = new Point(head.x, head.y - 1);
				} else {
					gameOver = true;
				}

			}
			if (direction == DOWN) {
				if (head.y + 1 <=76 && hitSelf(head.x, head.y + 1)) {
					head=new Point(head.x, head.y + 1);
				} else {
					gameOver = true;
				}
			}
			if (direction == RIGHT) {
				if (head.x + 1 <= 78.5 && hitSelf(head.x + 1, head.y)) {
					head=new Point(head.x + 1, head.y);
				} else {
					gameOver = true;
				}
			}
			if (direction == LEFT) {
				if (head.x - 1 >= 0 && hitSelf(head.x - 1, head.y)) {
					head=new Point(head.x - 1, head.y);
				} else {
					gameOver = true;
				}
			}
			if(body.size() > tailLength)
			{
				body.remove(0);
			}
		
			if (apple != null) {
				if (head.x == apple.x && head.y == apple.y) {
					
					score+=10;
					if(score==100)
					{
						gameOver=true;
					}
					else
					{
					apple.setLocation(random.nextInt(78), random.nextInt(76));
					}
					tailLength+=10;
					
				}
			}
			if(gameOver)
			{
				jframe.dispose();
				EndGame endGame=new EndGame();
				end=new JFrame("Game Over");
				end.setVisible(true);
				end.setSize(800, 800);
				end.setResizable(false);
				end.setLocation(550, 125);
				end.setVisible(true);
				end.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				end.add(endGame);
				
			
				
			}
		}
		

	}

	public static void main(String[] args) {
		snake=new Snake();
	}
	
	public boolean hitSelf(int x, int y)
	{
		for(Point point: body)
		{
			if(point.equals(new Point(x, y)))
			{
				return false;
			}
				
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i=e.getKeyCode();
		if(i==KeyEvent.VK_A && direction != RIGHT)
		{
			direction = LEFT;
		}
		if(i==KeyEvent.VK_D && direction != LEFT)
		{
			direction = RIGHT;
		}
		if(i==KeyEvent.VK_S && direction != UP)
		{
			direction = DOWN;
		}
		if(i==KeyEvent.VK_W && direction != DOWN)
		{  
			direction = UP;
		}
		if(i==KeyEvent.VK_SPACE && gameOver==true)
		{
			game();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}