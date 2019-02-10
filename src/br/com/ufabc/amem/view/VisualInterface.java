package br.com.ufabc.amem.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import br.com.ufabc.amem.controller.FunctionController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.FunctionAndParams;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Strings;

import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class VisualInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualInterface frame = new VisualInterface();
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
	public VisualInterface() {
		
		setResizable(false);
		setTitle("AMEM- Anchor Modeling Evolution Manager");
		setForeground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTree tree = new JTree();
		tree.setForeground(Color.WHITE);
		tree.setBackground(Color.GRAY);
		tree.setBounds(784, 33, 100, 428);
		contentPane.add(tree);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.GRAY);
		textPane.setBounds(10, 33, 764, 312);
		StyledDocument styledDocument = textPane.getStyledDocument();
		contentPane.add(textPane);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.YELLOW);
		separator.setBackground(Color.BLUE);
		separator.setBounds(10, 356, 764, 2);
		contentPane.add(separator);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.GRAY);
		textArea.setBounds(10, 369, 675, 92);
		contentPane.add(textArea);
		
		JButton btnSend = new JButton("Send");
		btnSend.setForeground(Color.WHITE);
		
		//TODO use enter
		btnSend.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				FunctionController functionController = new FunctionController();
				FunctionAndParams functionAndParams = functionController.read(textArea.getText());
				String showScreen = textArea.getText() + "\n";
				textArea.setText("");
								
				try {
					
					if(functionAndParams.getFunction() != null) {
											
						showScreen += functionAndParams.getFunction().execute(functionAndParams.getParams());
						//fill the impact list
						//confirma action
					
					} else {
						
						showScreen += Strings.getString("invalidCommand");
					}
				} catch (InvalidObject | SQLException | InvalidParameterNumber | ObjectAlreadyCreated e) {
						
					showScreen = e.getMessage();
				}
				
				try {
					
					styledDocument.insertString(styledDocument.getLength(), showScreen + "\n", null);
					
				} catch (BadLocationException e) {
					
					e.printStackTrace();
				}
			}
		});
		btnSend.setBackground(Color.GRAY);
		btnSend.setBounds(695, 369, 79, 92);
		contentPane.add(btnSend);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(Color.DARK_GRAY);
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.setBounds(0, 0, 210, 21);
		contentPane.add(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("File");
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		mntmNewMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmNewMenuItem.setForeground(Color.WHITE);
		mntmNewMenuItem.setBackground(Color.DARK_GRAY);
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.setHorizontalAlignment(SwingConstants.LEFT);
		mntmEdit.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmEdit.setForeground(Color.WHITE);
		mntmEdit.setBackground(Color.DARK_GRAY);
		menuBar.add(mntmEdit);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setHorizontalAlignment(SwingConstants.LEFT);
		mntmHelp.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmHelp.setForeground(Color.WHITE);
		mntmHelp.setBackground(Color.DARK_GRAY);
		menuBar.add(mntmHelp);
	}
}
