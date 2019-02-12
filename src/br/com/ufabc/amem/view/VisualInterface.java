package br.com.ufabc.amem.view;

import br.com.ufabc.amem.controller.FunctionController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.Attribute;
import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.am.Tie;
import br.com.ufabc.amem.model.function.FunctionAndParams;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Strings;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class VisualInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel            contentPane;
	private JTextArea         textArea;
	private StyledDocument    styledDocument;
	private JButton           btnSend;
	private JTree             tree;
	private JButton           btnConfirm;
	private JButton           btnDeny;
	private FunctionAndParams confirmFunction;
	private static final String USER = "RGHA";
	private static final String SYSTEM= "AMEM";
	private JTextPane textPane;
	private Style styleUser;

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
		
		ImageIcon img = new ImageIcon("src/br/com/ufabc/amem/images/database.png");
		this.setIconImage(img.getImage());
				
		this.confirmFunction = null;
		
		setResizable(false);
		setTitle(Strings.getString("title"));
		setForeground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.tree = new JTree();
		tree.setForeground(Color.WHITE);
		tree.setBackground(Color.GRAY);
		tree.setBounds(784, 33, 200, 380);
		tree.setModel(null);
		
		JScrollPane jScrollPaneTextTree = new JScrollPane(this.tree);
		jScrollPaneTextTree.setBounds(784, 33, 200, 380);
		jScrollPaneTextTree.setForeground(Color.WHITE);
		jScrollPaneTextTree.setBackground(Color.GRAY);
		jScrollPaneTextTree.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPaneTextTree.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(jScrollPaneTextTree);
		
		this.textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.GRAY);
		textPane.setBounds(10, 33, 764, 312);
		this.styledDocument = textPane.getStyledDocument();
		contentPane.add(textPane);
		textPane.setText(Strings.getString("welcome") + "\n");
		
		this.styleUser = this.styledDocument.addStyle("styleRed", null);
		StyleConstants.setForeground(this.styleUser, Color.WHITE);
		StyleConstants.setBold(this.styleUser, true);
		StyleConstants.setForeground(styleUser, Color.WHITE);
		
		JScrollPane jScrollPaneTextPane = new JScrollPane(textPane);
		jScrollPaneTextPane.setBounds(10, 33, 764, 312);
		jScrollPaneTextPane.setForeground(Color.WHITE);
		jScrollPaneTextPane .setBackground(Color.GRAY);
		jScrollPaneTextPane .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(jScrollPaneTextPane);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.GRAY);
		separator.setBounds(10, 356, 764, 2);
		contentPane.add(separator);
		
		this.textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.GRAY);
		textArea.setBounds(10, 369, 675, 92);
		
		JScrollPane scrollPaneTextArea = new JScrollPane(textArea);
		scrollPaneTextArea.setBounds(10, 369, 675, 92);
		scrollPaneTextArea.setForeground(Color.WHITE);
		scrollPaneTextArea.setBackground(Color.GRAY);
		scrollPaneTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPaneTextArea);

		btnSend = new JButton(Strings.getString("enter"));
		btnSend.setForeground(Color.WHITE);
		
		btnSend.setBackground(Color.GRAY);
		btnSend.setBounds(695, 369, 79, 92);
		contentPane.add(btnSend);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(Color.DARK_GRAY);
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.setBounds(0, 0, 210, 21);
		contentPane.add(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem(Strings.getString("file"));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		mntmNewMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmNewMenuItem.setForeground(Color.WHITE);
		mntmNewMenuItem.setBackground(Color.DARK_GRAY);
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmEdit = new JMenuItem(Strings.getString("edit"));
		mntmEdit.setHorizontalAlignment(SwingConstants.LEFT);
		mntmEdit.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmEdit.setForeground(Color.WHITE);
		mntmEdit.setBackground(Color.DARK_GRAY);
		menuBar.add(mntmEdit);
		
		JMenuItem mntmHelp = new JMenuItem(Strings.getString("help"));
		mntmHelp.setHorizontalAlignment(SwingConstants.LEFT);
		mntmHelp.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmHelp.setForeground(Color.WHITE);
		mntmHelp.setBackground(Color.DARK_GRAY);
		menuBar.add(mntmHelp);
		
		this.btnConfirm = new JButton(Strings.getString("confirm"));
		this.btnConfirm.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					styledDocument.insertString(styledDocument.getLength(), "[" + getTime() + "] - $" + USER + ": " + Strings.getString("userConfirmed") + "\n", styleUser);
					textArea.setText(null);
					
				} catch (BadLocationException e1) {

					e1.printStackTrace();
				}
				executeFunctionWriteReturn(confirmFunction);
			}
		});
		
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setBackground(Color.GRAY);
		btnConfirm.setBounds(784, 423, 93, 38);
		btnConfirm.setEnabled(false);
		contentPane.add(btnConfirm);
		
		this.btnDeny = new JButton(Strings.getString("cancel"));
		btnDeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					styledDocument.insertString(styledDocument.getLength(), "[" + getTime() + "] - $" + USER + ": " + Strings.getString("userCancel") + "\n", styleUser);
					
				} catch (BadLocationException e1) {

					e1.printStackTrace();
				}
				
				confirmFunction = null;
				btnConfirm.setEnabled(false);
				btnDeny.setEnabled(false);;
				tree.setModel(null);
			}
		});
		btnDeny.setBackground(Color.GRAY);
		btnDeny.setForeground(Color.WHITE);
		btnDeny.setBounds(891, 424, 93, 37);
		btnDeny.setEnabled(false);
		contentPane.add(btnDeny);
		
		btnSend.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				sendCommands();
			}
		});

		KeyListener keyListener = new KeyListener() {
			 
		     public void keyReleased(KeyEvent keyEvent) {
		     }

			@Override
			public void keyPressed(KeyEvent keyEvent) {
		         if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) { 
		        	 
		        	 keyEvent.consume();
		        	 sendCommands();
		         }
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
		};
		textArea.addKeyListener(keyListener);
	}
	
	@SuppressWarnings("serial")
	private void sendCommands() {
		
		tree.setModel(null);
		confirmFunction = null;
		btnConfirm.setEnabled(false);
		btnDeny.setEnabled(false);
		
		FunctionController functionController = new FunctionController();
		FunctionAndParams functionAndParams = functionController.read(textArea.getText());
		String showScreen = "";
		
		try {
			
			this.styledDocument.insertString(styledDocument.getLength(), "[" + getTime() + "] - $" + USER + ": " + textArea.getText() + "\n", styleUser);
			textArea.setText(null);
			
		} catch (BadLocationException e1) {

			e1.printStackTrace();
		}
						
		try {
			
			if(functionAndParams.getFunction() != null) {
				
				if(functionAndParams.getFunction().getImpact(functionAndParams.getParams()) != null) {
					
					ImpactList impactList = functionAndParams.getFunction().getImpact(functionAndParams.getParams());
					Map<Anchor, String>    anchorImpacts    = impactList.getAnchorImpacts();
					Map<Tie, String>       tieImpacts       = impactList.getTieImpacts();
					Map<Knot, String>      knotImpacts      = impactList.getKnotImpacts();
					Map<Attribute, String> attributeImpacts = impactList.getAttributeImpacts();
					
					tree.setModel(new DefaultTreeModel(
							new DefaultMutableTreeNode(Strings.getString("impacts")) {
								{
									if(!anchorImpacts.isEmpty() || !attributeImpacts.isEmpty() || !tieImpacts.isEmpty() || !knotImpacts.isEmpty()) {
										
										DefaultMutableTreeNode anchorModelingNode = new DefaultMutableTreeNode("Anchor Modeling");
										
										if(!anchorImpacts.isEmpty()) {
											
											DefaultMutableTreeNode anchorNode     = new DefaultMutableTreeNode(Strings.getString("anchor"));
											anchorModelingNode.add(anchorNode);
											
											for (Entry<Anchor, String> impact : anchorImpacts.entrySet()) {
												
												anchorNode.add(new DefaultMutableTreeNode(impact.getKey().getDescriptor() + ": " + impact.getValue()));
											}
										}
										
										if(!attributeImpacts.isEmpty()) {
											
											DefaultMutableTreeNode attributeNode  = new DefaultMutableTreeNode(Strings.getString("attribute"));
											anchorModelingNode.add(attributeNode);
											
											for (Entry<Attribute, String> impact : attributeImpacts.entrySet()) {
												
												attributeNode.add(new DefaultMutableTreeNode(impact.getKey().getDescriptor() + ": " + impact.getValue()));
											}
										}

										if(!tieImpacts.isEmpty()) {
											
											DefaultMutableTreeNode tieNode        = new DefaultMutableTreeNode(Strings.getString("tie"));
											anchorModelingNode.add(tieNode);
											
											for (Entry<Tie, String> impact : tieImpacts.entrySet()) {
												
												tieNode.add(new DefaultMutableTreeNode(impact.getKey().getDescriptor() + ": " + impact.getValue()));
											}
										}
										
										if(!knotImpacts.isEmpty()) {
											
											DefaultMutableTreeNode knotNode       = new DefaultMutableTreeNode(Strings.getString("knot"));
											anchorModelingNode.add(knotNode);
											
											for (Entry<Knot, String> impact : knotImpacts.entrySet()) {
												
												knotNode.add(new DefaultMutableTreeNode(impact.getKey().getDescriptor() + ": " + impact.getValue()));
											}
										}
										
									add(anchorModelingNode);
									}
									
									
									//TODO fill the impact list for database objects
									DefaultMutableTreeNode DataBaseNode        = new DefaultMutableTreeNode(Strings.getString("database"));
									
										DefaultMutableTreeNode tableNode       = new DefaultMutableTreeNode(Strings.getString("table"));
										DataBaseNode.add(tableNode);
										
										DefaultMutableTreeNode functionNode    = new DefaultMutableTreeNode(Strings.getString("function"));
										DataBaseNode.add(functionNode);
										
										DefaultMutableTreeNode procedureNode   = new DefaultMutableTreeNode(Strings.getString("procedure"));
										DataBaseNode.add(procedureNode);
										
										DefaultMutableTreeNode triggerNode     = new DefaultMutableTreeNode(Strings.getString("trigger"));
										DataBaseNode.add(triggerNode);
										
										DefaultMutableTreeNode constraintsNode = new DefaultMutableTreeNode(Strings.getString("constraints"));
										DataBaseNode.add(constraintsNode);

									add(DataBaseNode);
								}
							}
						)
					);
					
					confirmFunction = functionAndParams;
					btnConfirm.setEnabled(true);
					btnDeny.setEnabled(true);
					showScreen += Strings.getString("requiresConfirm");
				
				} else {
					
					showScreen += functionAndParams.getFunction().execute(functionAndParams.getParams());
				}
			
			} else {
				
				showScreen += Strings.getString("invalidCommand");
			}
		} catch (InvalidObject | SQLException | InvalidParameterNumber | ObjectAlreadyCreated e) {
				
			showScreen = e.getMessage();
		}
		
		try {
			
			styledDocument.insertString(styledDocument.getLength(), "[" + getTime() + "] - $" + SYSTEM + ": " + showScreen + "\n", null);
			
		} catch (BadLocationException e) {
			
			e.printStackTrace();
		}
	}
	
	public void executeFunctionWriteReturn(FunctionAndParams functionAndParams) {
		
		String showScreen = "";
		
		try {
			
			try {
				
				showScreen += functionAndParams.getFunction().execute(functionAndParams.getParams());
				
			} catch (InvalidObject | SQLException | InvalidParameterNumber | ObjectAlreadyCreated e) {

				showScreen += e.getMessage();
			}
			
			styledDocument.insertString(styledDocument.getLength(), "[" + getTime() + "] - $" + SYSTEM+ ": " + showScreen + "\n", null);
			
		} catch (BadLocationException e) {
			
			e.printStackTrace();
			
		} finally {
			
			confirmFunction = null;
			btnConfirm.setEnabled(false);
			btnDeny.setEnabled(false);;
			tree.setModel(null);
		}
	}
	
	public String getTime() {
		
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
		//return new Timestamp(new Date().getTime()) + "";
	}
}