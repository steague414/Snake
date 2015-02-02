import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Snake extends JFrame implements Runnable
{
	public Snake()
	{
		super("Snake 1");
	}
	public void run()
	{
		setSize(600,600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(new CheckerPanel());
		setVisible(true);
	}
	public static void main(String[] args)
	{
		Snake kd = new Snake();
		javax.swing.SwingUtilities.invokeLater(kd);
	}
	class CheckerPanel extends JPanel implements KeyListener
	{
		private LittlePanel[][] grid;
		private Point activeLocation;
		public CheckerPanel()
		{
			super(new GridLayout(30,30));
			addKeyListener(this);
			setFocusable(true);
			grid = new LittlePanel[30][30];
			for (int j = 0; j < 30; j++) {
				for (int k = 0; k < 30; k++) {
					grid[j][k] = new LittlePanel();
					add(grid[j][k]);
				}
			}
			activeLocation = new Point(0,0);
			grid[activeLocation.x][activeLocation.y].toggleActive();
		}

		public void keyPressed(KeyEvent e)
		{
			if( e.getKeyCode() == KeyEvent.VK_RIGHT && activeLocation.y < 29)
			{
				grid[activeLocation.x][activeLocation.y].toggleActive();
				activeLocation.y++;
				grid[activeLocation.x][activeLocation.y].toggleActive();
				repaint();
			}
			if( e.getKeyCode() == KeyEvent.VK_LEFT && activeLocation.y > 0)
			{
				grid[activeLocation.x][activeLocation.y].toggleActive();
				activeLocation.y--;
				grid[activeLocation.x][activeLocation.y].toggleActive();
				repaint();
			}
			if( e.getKeyCode() == KeyEvent.VK_UP && activeLocation.x > 0)
			{
				grid[activeLocation.x][activeLocation.y].toggleActive();
				activeLocation.x--;
				grid[activeLocation.x][activeLocation.y].toggleActive();
				repaint();
			}
			if( e.getKeyCode() == KeyEvent.VK_DOWN && activeLocation.x < 29)
			{
				grid[activeLocation.x][activeLocation.y].toggleActive();
				activeLocation.x++;
				grid[activeLocation.x][activeLocation.y].toggleActive();
				repaint();
			}
		}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e)
		{
		}
	}
}
class LittlePanel extends JPanel
{
	boolean isActive;
	public LittlePanel()
	{
		super();
		isActive = false;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	public void toggleActive()
	{
		isActive = !isActive;
	}
	public void paintComponent(Graphics g)
	{
		Color c = isActive? Color.RED: Color.WHITE;
		g.setColor(c);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
