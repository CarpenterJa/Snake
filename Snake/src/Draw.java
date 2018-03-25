import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
//
@SuppressWarnings("serial")
public class Draw extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 800);
		Snake snake = Snake.snake;
		g.setColor(Color.GREEN);
		for (Point point : snake.body) {
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.RED);
		g.fillRect(snake.apple.x * Snake.SCALE, snake.apple.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		String s="Score: " + snake.score;
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		g.drawString(s, (int) snake.jframe.getWidth() / 2 - s.length() / 2, 15);
	} 
}
