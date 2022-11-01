package src.view;;

import java.awt.*;

import javax.swing.*;

import src.controller.PlayerController;
import src.model.Bank;
import src.model.Fields;
import src.model.Player;
import src.model.TextDraw;

public class GameBoard {

	public static final int WIDTH = 1095;
	public static final int HEIGHT = 750;

	public static final int FPS = 20;
	public static final int DELAY = 1000 / FPS;

	private JFrame window;
	private Fields field;
	private MyCanvas canvas;
	private Player player1;
	private Player player2;
	private TextArea player1Area;
	private TextArea player2Area;
	private TextArea bankArea;
	private JButton player1Roll;
	private JButton player2Roll;
	private PlayerController con;
	private Bank bank;

	public GameBoard(JFrame window) {
		this.window = window;
	}

	public void init() {
		field = new Fields();
		con = new PlayerController(this);
		Container cp = window.getContentPane();

		canvas = new MyCanvas(this, WIDTH, HEIGHT);
		cp.add(BorderLayout.CENTER, canvas);
		canvas.requestFocusInWindow();
		canvas.setFocusable(true);

		JButton startButton = new JButton("Start");
		JButton quitButton = new JButton("Quit");
                
		String label[] = { "1 Player", "2 Player" };
		JComboBox<String> level = new JComboBox<String>(label);
		level.setFocusable(false);
		startButton.setFocusable(false);
		quitButton.setFocusable(false);

		JPanel southPanel = new JPanel();
		southPanel.add(startButton);
		southPanel.add(level);
		southPanel.add(quitButton);
		cp.add(BorderLayout.SOUTH, southPanel);

		player1Roll = new JButton("Roll Player 1 dice");
		player2Roll = new JButton("Roll Player 2 dice");
		
		canvas.getGameElements().add(new TextDraw(450, 445, "Click <Start> to Play", null, 30));
		bank = new Bank();

		startButton.addActionListener(event -> {
			canvas.getGameElements().clear();
			canvas.setBackground(Color.white);
			String name = JOptionPane.showInputDialog("Enter name of player 1");
                        if (name.length() <= 1) {
                JOptionPane.showMessageDialog(null, "please enter your correct name");
                name = JOptionPane.showInputDialog("Enter name of player 1");

            }
                            if (name.length() >= 8) {
                JOptionPane.showMessageDialog(null, "your name it should not be more than 8 ");
                name = JOptionPane.showInputDialog("Enter name of player 1");

            }
			player1 = new Player(name, field.getGo(), Color.RED, 15, 30);
			canvas.getGameElements().add(player1);
			if (level.getSelectedItem().equals("2 Player")) {
				name = JOptionPane.showInputDialog("Enter name of player 2");
                                if (name.length() <= 1) {
                JOptionPane.showMessageDialog(null, "please enter your correct name");
                name = JOptionPane.showInputDialog("Enter name of player 1");

            }
                            if (name.length() >= 8) {
                JOptionPane.showMessageDialog(null, "your name it should not be more than 8 ");
                name = JOptionPane.showInputDialog("Enter name of player 1");

            }
				player2 = new Player(name, field.getGo(), Color.GREEN, 50, 30);
				canvas.getGameElements().add(player2);
				player2Area = new TextArea(15, 40);
				player2Area.setEditable(false);
			}
			canvas.getGameElements().add(field);
			startButton.setVisible(false);
			level.setVisible(false);
			player1Area = new TextArea(15, 40);
			player1Area.setEditable(false);
			bankArea = new TextArea(15,40);
			bankArea.setText("Bank balance: "+bank.getBalance());
                        bankArea.setBackground(Color.LIGHT_GRAY);
			JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			panel.add(bankArea);
			c.gridx = 0;
			c.gridy = 1;
			panel.add(player1Area, c);
			player1Area.setText("Name: "+player1.getName()+" balance: "+player1.getBalance()+" Current cell (0, 0)");
			                        player1Area.setBackground(Color.LIGHT_GRAY);

                        c.gridx = 0;
			c.gridy = 2;
			panel.add(player1Roll, c);
			c.gridx = 0;
			c.gridy = 3;
			if (player2 != null) {
				panel.add(player2Area, c);
				c.gridx = 0;
				c.gridy = 4;
				panel.add(player2Roll, c);
				player2Roll.setVisible(false);
				player2Area.setText("Name: "+player2.getName()+" balance: "+player2.getBalance()+" Current cell (0, 0)");
						                        player2Area.setBackground(Color.LIGHT_GRAY);

                        }
			window.add(panel, BorderLayout.EAST);
			window.setLocationRelativeTo(null);
			player1Roll.addActionListener(con);
			player2Roll.addActionListener(con);
		});
quitButton.addActionListener(event ->{
        if (JOptionPane.showConfirmDialog( null,"are you sure you want to close the game ","monoply",
            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            System.exit(0);
        });
	}

	public MyCanvas getCanvas() {
		return canvas;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public Fields getField() {
		return field;
	}
	
	public TextArea getPlayer1Area() {
		return player1Area;
	}
	
	public JButton getPlayer1Roll() {
		return player1Roll;
	}
	
	public TextArea getPlayer2Area() {
		return player2Area;
	}
	
	public JButton getPlayer2Roll() {
		return player2Roll;
	}
	
	public Bank getBank() {
		return bank;
	}
	
	public TextArea getBankArea() {
		return bankArea;
	}

}
