import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Game extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton[][] t;
	private BufferedImage red, blue;
	private ImageIcon redicon, blueicon;
	private int bs, r, p = 1, col,row;
	private JTextField textField;
	private Border b = BorderFactory.createLineBorder(Color.cyan, 7);
	private final int ROW = 8;
	private final int COLUMN = 8;
	private final int PLAYER = 1;
	private final int AI = 0;
	private final int WHITE = 2;
	private int rrr = 0;
	// private long nodes = 0;

	/**
	 * Launch the application.
	 */
	public static void game(String name) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game(name);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param name
	 * @param size
	 */
	public Game(String name) {
		int size = 8;
		t = new JButton[size][size];
		bs = size;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 780, 610);
		contentPane.add(panel);
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 607, 780, 93);
		panel2.setBackground(Color.black);
		panel2.setLayout(null);
		contentPane.add(panel2);
		JLabel lblNewLabel_1 = new JLabel("1");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(40, 20, 27, 33);
		panel2.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("2");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(140, 20, 27, 33);
		panel2.add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel("3");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(240, 20, 27, 33);
		panel2.add(lblNewLabel_3);
		JLabel lblNewLabel_4 = new JLabel("4");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(340, 20, 27, 33);
		panel2.add(lblNewLabel_4);
		JLabel lblNewLabel_5 = new JLabel("5");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(440, 20, 27, 33);
		panel2.add(lblNewLabel_5);
		JLabel lblNewLabel_6 = new JLabel("6");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(540, 20, 27, 33);
		panel2.add(lblNewLabel_6);
		JLabel lblNewLabel_7 = new JLabel("7");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(640, 20, 27, 33);
		panel2.add(lblNewLabel_7);
		JLabel lblNewLabel_8 = new JLabel("8");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(740, 20, 27, 33);
		panel2.add(lblNewLabel_8);
		JButton btnNewButton = new JButton("Menu"); // getting back to the menu
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu.main(null);
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(Color.white);
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD, 17));
		btnNewButton.setBounds(833, 520, 116, 84);
		contentPane.add(btnNewButton);
		JLabel lblNewLabel = new JLabel("COL:");
		lblNewLabel.setBounds(793, 130, 61, 16);
		contentPane.add(lblNewLabel);
		textField = new JTextField();
		textField.setBounds(866, 125, 130, 26);
		textField.setColumns(10);
		contentPane.add(textField);
		JLabel lblNewLabel_9 = new JLabel(name + "'s turn");
		lblNewLabel_9.setBounds(856, 285, 85, 26);
		contentPane.add(lblNewLabel_9);
		JLabel lblNewLabel_10 = new JLabel("AI's turn");
		lblNewLabel_10.setBounds(856, 285, 85, 26);
		contentPane.add(lblNewLabel_10);
		lblNewLabel_10.setVisible(false);
		JButton btnNewButton_1 = new JButton("ok");
		btnNewButton_1.setBounds(833, 164, 117, 29);
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(Color.white);
		btnNewButton_1.setFont(new Font("SSnap ITC", Font.BOLD, 17));
		contentPane.add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("Switch");
		btnNewButton_2.setBounds(833, 209, 117, 29);
		contentPane.add(btnNewButton_2);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {

				String rowstring = textField.getText();
				col = Integer.parseInt(rowstring) - 1;
				if (0 > col && col > size) {
					JOptionPane.showInternalMessageDialog(contentPane, "Enter valid number!!!", "",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						btnNewButton_1.setEnabled(false);
						btnNewButton_2.setEnabled(true);
						lblNewLabel_9.setVisible(false);
						lblNewLabel_10.setVisible(true);
						row = play();
						int[][] board2 = new int[size][size];

						for (int i = 0; i < size; i++) {
							for (int j = 0; j < size; j++) {
								if (t[i][j].getBackground().equals(Color.white)) {
									board2[i][j] = WHITE;
								}

								if (t[i][j].getForeground().equals(Color.red)) {
									board2[i][j] = AI;
								}

								if (t[i][j].getForeground().equals(Color.blue)) {
									board2[i][j] = PLAYER;
								}
							}
						}
						board2[row][col] = PLAYER;
						if (winner(board2, PLAYER)) {
							JOptionPane.showInternalMessageDialog(contentPane, "player wins", "",
									JOptionPane.INFORMATION_MESSAGE);

						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		if (size > 0) {
			panel.setLayout(new GridLayout(size, size, 0, 0));
		}
		panel.setBackground(Color.GRAY);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    btnNewButton_2.setEnabled(false);
                    btnNewButton_1.setEnabled(true);
					lblNewLabel_10.setVisible(false);
					lblNewLabel_9.setVisible(true);
					int[][] board = new int[size][size];

					for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							if (t[i][j].getBackground().equals(Color.white)) {
								board[i][j] = WHITE;
							}

							if (t[i][j].getForeground().equals(Color.red)) {
								board[i][j] = AI;
							}

							if (t[i][j].getForeground().equals(Color.blue)) {
								board[i][j] = PLAYER;
							}
						}
					}

					int[] column_score = negamax(board, -999999999, 999999999, 10, size);
					int rr = AIplay(column_score[0]);
					board[rr][column_score[0]] = AI;

					if (winner(board, AI)) {
						JOptionPane.showInternalMessageDialog(contentPane, "ai wins", "",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});

		JButton btnNewButton_3 = new JButton("Restart");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.game(name);
			}
		});
		btnNewButton_3.setFont(new Font("Dialog", Font.BOLD, 17));
		btnNewButton_3.setBounds(832, 440, 116, 84);
		contentPane.add(btnNewButton_3);

		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size; j++) {
				t[i][j] = new JButton("");
				t[i][j].setBackground(Color.white);
				t[i][j].setOpaque(true);
				t[i][j].setBounds(0, 0, 100, 100);
				panel.add(t[i][j]);

			}
		}

	}

	public int play() throws IOException {
		for (int i = 0; i < bs;) {
			if (t[i][col].getBackground().equals(Color.white)) {
				blue = ImageIO.read(getClass().getResource("/Pic/blue.png"));
				blueicon = new ImageIcon(blue.getScaledInstance(70, -1, Image.SCALE_SMOOTH));
				t[i][col].setIcon(blueicon);
				t[i][col].setBackground(Color.black);
				t[i][col].setForeground(Color.blue);
				return i;
				// break;
			} else {
				while (t[i][col].getBackground().equals(Color.black)) {
					i++;
				}
				blue = ImageIO.read(getClass().getResource("/Pic/blue.png"));
				blueicon = new ImageIcon(blue.getScaledInstance(70, -1, Image.SCALE_SMOOTH));
				t[i][col].setIcon(blueicon);
				t[i][col].setBackground(Color.black);
				t[i][col].setForeground(Color.blue);
				return i;
				// break;
			}

		}
		return 0;
	}

	public int AIplay(int c) throws IOException {
		if (0 <= c && c < 8) {
			for (int i = 0; i < bs;) {
				if (t[i][c].getBackground().equals(Color.white)) {
					red = ImageIO.read(getClass().getResource("/Pic/red.png"));
					redicon = new ImageIcon(red.getScaledInstance(70, -1, Image.SCALE_SMOOTH));
					t[i][c].setIcon(redicon);
					t[i][c].setBackground(Color.black);
					t[i][c].setForeground(Color.red);
					return i;
					// break;
				} else {
					while (t[i][c].getBackground().equals(Color.black)) {

						i++;

					}
					red = ImageIO.read(getClass().getResource("/Pic/red.png"));
					redicon = new ImageIcon(red.getScaledInstance(70, -1, Image.SCALE_SMOOTH));
					t[i][c].setIcon(redicon);
					t[i][c].setBackground(Color.black);
					t[i][c].setForeground(Color.red);
					return i;
					// break;
				}

			}
		}

		return 0;
	}

	public void undo(int cl, int rrw) {
		t[rrw][cl].setIcon(null);
		t[rrw][cl].setBackground(Color.white);
		t[rrw][cl].setForeground(Color.white);
	}

	public int[] negamax(int[][] nodes, int alpha, int beta, int depth, int size) throws IOException {
		// return array ... colunm_value[0] = colunm number .... colunm_value[1] =
		// computed score of that colunm
		int[] colunm_value = new int[2];

		// every changes will apply on temp
		// JButton[][] temp = new JButton[size][size];
		ArrayList<Integer> colunms = new ArrayList<>();

		// find free colunms
		colunms = possible_moves(nodes);

		if (depth == 0 || colunms.size() == 0 || winner(nodes, AI) || winner(nodes, PLAYER)) {
			if (colunms.size() == 0 || winner(nodes, AI) || winner(nodes, PLAYER)) {

				if (winner(nodes, AI)) {
					int[] ans = new int[2];
					ans[0] = 0;
					ans[1] = 999999999;
					return ans;
				}

				else if (winner(nodes, PLAYER)) {
					int[] ans = new int[2];
					ans[0] = 0;
					ans[1] = -999999999;
					return ans;
				} else {
					int[] ans = new int[2];
					ans[0] = 0;
					ans[1] = 0;
					return ans;

				}

			}

			else {

				// compute the score if depth equals to 0
				int score = compute_score(nodes);
				int[] z = new int[2];
				z[0] = 0;
				z[1] = score;
				return z;

			}
			
		}

		colunm_value[1] = -999999999;
		int[] new_score = new int[2];
		Random rand = new Random();
		colunm_value[0] = colunms.get(rand.nextInt(colunms.size()));

		// find free row in selected column

		for (int colunm = 0; colunm < colunms.size(); colunm++) {
			int row = -1;
			for (int i = 0; i < ROW; i++) {
				if (nodes[i][colunms.get(colunm)] == WHITE) {
					row = i;
					break;

				}

			}

			// JButton[][] temp = new JButton[size][size];
			rrr++;

			int[][] temp = Arrays.stream(nodes).map(int[]::clone).toArray(int[][]::new);

			// temp = nodes;
			// if(nodes[row][colunms.get(colunm)]== WHITE) {
			// System.out.println("yes nodes before" + rrr);
			// }
			temp[row][colunms.get(colunm)] = AI;
			// temp[row][colunms.get(colunm)].setForeground(Color.red);

			// if(nodes[row][colunms.get(colunm)]!= WHITE) {
			// System.out.println("yes temp"+ rrr);
			// }

			// System.out.println(rrr++);
			new_score = negamax(temp, -beta, -alpha, depth - 1, size);

			if ((-new_score[1]) > colunm_value[1]) {
				colunm_value[0] = colunms.get(colunm);
				colunm_value[1] = -new_score[1];
			}
			alpha = Math.max(alpha, colunm_value[1]);

			// pruning
			if (alpha >= beta) {
				break;
			}

		}

		return colunm_value;
	}

	public ArrayList<Integer> possible_moves(int[][] game) {
		ArrayList<Integer> colunms = new ArrayList<>();

		for (int i = 0; i < COLUMN; i++) {
			if (game[ROW - 1][i] == WHITE) {
				colunms.add(i);

			}

		}
		return colunms;
	}

	public int compute_score(int[][] game) {
		int score = 0;
		int counter = 0;
		int user = 0;
		int ai = 0;
		int white = 0;

		// calculate center score

		for (int i = 0; i < ROW; i++) {
			if (game[i][(COLUMN / 2)-1] == AI) {
				counter++;
			}
		}
		score += counter * 2;

		// calculate horizontal score

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN - 3; j++) {
				for (int z = 0; z < 4; z++) {
					if (game[i][j + z] == AI) {
						ai++;
					} else if (game[i][j + z] == PLAYER) {
						user++;
					} else {
						white++;
					}
				}
				score += ux_score(user, ai, white);
				user = 0;
				ai = 0;
				white = 0;

			}
		}

		user = 0;
		ai = 0;
		white = 0;

		// calculate vertical score
		for (int j = 0; j < COLUMN; j++) {
			for (int i = 0; i < ROW - 3; i++) {
				for (int z = 0; z < 4; z++) {
					if (game[i + z][j] == AI) {
						ai++;
					} else if (game[i + z][j] == PLAYER) {
						user++;
					} else {
						white++;
					}
				}

				score += ux_score(user, ai, white);
				user = 0;
				ai = 0;
				white = 0;

			}
		}

		user = 0;
		ai = 0;
		white = 0;

		// calculate diagonal => /
		for (int i = 0; i < ROW - 3; i++) {
			for (int j = 0; j < COLUMN - 3; j++) {
				for (int z = 0; z < 4; z++) {
					if (game[i + z][j + z] == AI) {
						ai++;
					} else if (game[i + z][j + z] == PLAYER) {
						user++;
					} else {
						white++;
					}
				}
				score += ux_score(user, ai, white);
				user = 0;
				ai = 0;
				white = 0;

			}
		}

		user = 0;
		ai = 0;
		white = 0;

		// calculate diagonal => \
		for (int i = 0; i < ROW - 3; i++) {
			for (int j = 0; j < COLUMN - 3; j++) {
				for (int z = 0; z < 4; z++) {
					if (game[i + 3 - z][j + z] == AI) {
						ai++;
					} else if (game[i + 3 - z][j + z] == PLAYER) {
						user++;
					} else {
						white++;
					}
				}
				score += ux_score(user, ai, white);
				user = 0;
				ai = 0;
				white = 0;

			}
		}

		return score;
	}

	public int ux_score(int user, int ai, int white) {
		int score = 0;

		if (ai == 4 && white == 0) {
			score += 999999999;
		}

		if (ai == 3 && white == 1) {
			score += 1;
		}

		if (ai == 2 && white == 2) {
			score += 10;
		}

		if (user == 3 && white == 1) {
			score -= 99999999;
		}
		/*if (user == 2 && white == 2) {
			score -= 2;
		}*/

		return score;

	}

	public boolean winner(int[][] board, int round) {

		for (int j = 0; j < COLUMN - 3; j++) {
			for (int i = 0; i < ROW; i++) {
				if (board[i][j] == round && board[i][j + 1] == round && board[i][j + 2] == round
						&& board[i][j + 3] == round) {
					return true;
				}

			}
		}

		for (int j = 0; j < COLUMN; j++) {
			for (int i = 0; i < ROW - 3; i++) {
				if (board[i][j] == round && board[i + 1][j] == round && board[i + 2][j] == round
						&& board[i + 3][j] == round) {
					return true;
				}

			}
		}

		for (int j = 0; j < COLUMN-3; j++) {
			for (int i = 0; i < ROW - 3; i++) {
				if (board[i][j] == round && board[i + 1][j + 1] == round && board[i + 2][j + 2] == round
						&& board[i + 3][j + 3] == round) {
					return true;
				}

			}
		}

		for (int j = 0; j < COLUMN - 3; j++) {
			for (int i = 3; i < ROW; i++) {
				if (board[i][j] == round && board[i - 1][j + 1] == round && board[i - 2][j + 2] == round
						&& board[i - 3][j + 3] == round) {
					return true;
				}

			}
		}
		return false;
	}

}
