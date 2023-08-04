import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	static Menu frame = new Menu();
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 534);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1;

			public void paintComponent(Graphics g) {
				Image img = Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Pic/menu.jpg"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Board Size:");
		lblNewLabel.setForeground(new Color(255, 255, 153));
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 21));
		lblNewLabel.setBounds(200, 64, 138, 72);
		//contentPane.add(lblNewLabel);

		JLabel lblBoardSize = new JLabel("Player Name:");
		lblBoardSize.setForeground(new Color(255, 255, 153));
		lblBoardSize.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 21));
		lblBoardSize.setBounds(200, 130, 164, 79);
		contentPane.add(lblBoardSize);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(350, 150, 143, 43);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(350, 83, 143, 43);
		//contentPane.add(textField_2);

		JButton btnNewButton = new JButton("START"); // start the game
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_1.getText();
				
					dispose();
					Game.game(name);
				

			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Segoe UI Black", Font.BOLD, 23));
		btnNewButton.setBounds(251, 300, 200, 113);
		contentPane.add(btnNewButton);
        
	}

}
