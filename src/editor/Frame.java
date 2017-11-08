package editor;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Frame extends JFrame implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6589372618786481380L;
	private JPanel contentPane;
	private JTextArea textArea;
	private File dir = null;
	private int fontSize;
	private String savedText="x";
	private boolean scroll = false;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					
					
					/*
					 * Layout 1
					 */
					
					
					try {
			            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			        } catch (ClassNotFoundException | InstantiationException
			                | IllegalAccessException | UnsupportedLookAndFeelException e) {
			            e.printStackTrace();
			        }
			        SwingUtilities.updateComponentTreeUI(frame);
					
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
	public Frame() {
		setResizable(false);
		setTitle("CoolPad\u00AE");
		
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		
		
			
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!(textArea.getText().equals("")) && !(textArea.getText().equals(savedText))){
					int reply = JOptionPane.showConfirmDialog(null, "Do you want to save your file?", "clear", JOptionPane.YES_NO_CANCEL_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						test();
						textArea.setText("");
					}
					else if(reply == JOptionPane.NO_OPTION){
						textArea.setText("");
					}
				}
				else{
					textArea.setText("");
				}
				dir = null;		
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(textArea.getText().equals("")) && !(textArea.getText().equals(savedText))){
					int reply = JOptionPane.showConfirmDialog(null, "Do you want to Save your unsaved file?", "clear", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION){
						saveAs();
					}
				}
					
					
				else{
					open();
				}
					
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				test();
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as");
		mntmSaveAs.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveAs();
				//JOptionPane.showMessageDialog(null, "Save as Done");
			}
		});
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnAction = new JMenu("Action");
		mnAction.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnAction);
		
		JMenuItem mntmEncrypt = new JMenuItem("Encrypt");
		mntmEncrypt.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Encrypt your file?", "clear", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Encrypt();
				}
			}
		});
		mnAction.add(mntmEncrypt);
		
		JMenuItem mntmDecrypt = new JMenuItem("Decrypt");
		mntmDecrypt.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Decrypt your file?", "clear", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					Decrypt();
				}
			}
		});
		mnAction.add(mntmDecrypt);
		
		JMenu mnView = new JMenu("View");
		mnView.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnView);
		
		JMenuItem mntmFont = new JMenuItem("Font Size");
		mntmFont.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				font();
			}
		});
		mnView.add(mntmFont);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(49, 0, 545, 747);
		contentPane.add(scrollPane);
		
		JMenuItem unwrap = new JMenuItem("UnWrap Font");
		unwrap.setFont(new Font("Segoe UI", Font.BOLD, 14));
		unwrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scroll = !scroll;
				if(scroll){
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					unwrap.setText("Wrap Font");
					textArea.setLineWrap(false);
				    textArea.setWrapStyleWord(false);
				}
				else{
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					unwrap.setText("UnWrap Font");
					textArea.setLineWrap(true);
				    textArea.setWrapStyleWord(true);
				}
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Font Color");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] list = {"Black", "Red", "Blue", "Purple"};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				JComboBox jcb = new JComboBox(list);
				jcb.setEditable(true);
				JOptionPane.showMessageDialog( null, jcb, "select or type a value", JOptionPane.QUESTION_MESSAGE);
				String font = String.valueOf((jcb.getSelectedItem()));
				if(font == "Black"){
					textArea.setForeground(new Color(0, 0, 0));
				}
				else if(font == "Red"){
					textArea.setForeground(new Color(255, 0, 51));
				}
				else if(font == "Blue"){
					textArea.setForeground(new Color(0, 51, 255));
				}
				else if(font == "Purple"){
					textArea.setForeground(new Color(204,51, 255));
				}
				
			}
		});
		mnView.add(mntmNewMenuItem);
		mnView.add(unwrap);
		
		
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Thanks for using the Notepad. Credit Goes to T@nveer. Dedicated to Blu3virus", "Blu3virus®", 1);
			}
		});
		mnView.add(mntmAbout);
		
		textArea = new JTextArea();
		textArea.setForeground(new Color(204, 51, 255));
		textArea.setBackground(new Color(255, 255, 153));
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
		textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    
	    JLabel Background = new JLabel("");
	    Background.setIcon(new ImageIcon(Frame.class.getResource("/Image/Notepad Image.png")));
	    Background.setBounds(0, 0, 584, 740);
	    contentPane.add(Background);
	    
	}
	
	public void saveAs(){
		dir = null;
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int resp = fc.showOpenDialog(null);
		if (resp == JFileChooser.APPROVE_OPTION) {
		    dir = fc.getSelectedFile();
		   // JOptionPane.showMessageDialog(null, dir);
		   
		    FileWriter fw = null;
		    try {
		    	if(!(String.valueOf(dir).contains(".txt"))){
		    		fw = new FileWriter(dir+".txt");
		    	}
		    	else{
		    		fw = new FileWriter(dir);
		    	}
		    	savedText = textArea.getText();
		    	
		        fw.write(savedText);
		        
		        fw.flush();
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error in Save As");
		    } finally {
		        if(fw != null) {
		            try {
		                fw.close();
		            } catch (IOException ex) {
		            }
		        }
		    }
		}
	}
	
	
	
	public void save(){
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir))) {

			String content = textArea.getText();
			savedText = content;
			bw.write(content);
			

			// no need to close it.
			//bw.close();


		} catch (Exception e1){	//IOException e1) {

			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error in Saving");

		}
	}
	
	public void test(){
		
		File f;
		if(!(String.valueOf(dir).contains(".txt"))){
			f = new File(String.valueOf(dir+".txt"));
		}
		else{
			f = new File(String.valueOf(dir));
		}
		if(f.exists() && !f.isDirectory()) { 
		    save();
		}
		else{
			saveAs();
		}
	}
	
	public void font(){
		String[] list = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "42", "72"};
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox jcb = new JComboBox(list);
		jcb.setEditable(true);
		JOptionPane.showMessageDialog( null, jcb, "select or type a value", JOptionPane.QUESTION_MESSAGE);
		fontSize = Integer.parseInt((jcb.getSelectedItem().toString()));
		textArea.setFont(new Font("Lithos Pro Regular", Font.PLAIN, fontSize));
	}
	
	public void Encrypt(){
		String s = textArea.getText();
		StringBuilder sb = new StringBuilder();
		for(char c: s.toCharArray()){
			sb.append(((int)c)+22);
			sb.append(" ");
		}
		String s1 = sb.toString();
		textArea.setText(s1);
	}
	
	
	public void Decrypt(){
		String ch = "";
		String s = "";
		String s1 = textArea.getText();
		int n=0;
		try{
			for(int i=0; i<s1.length(); i++){
				if(s1.charAt(i) != ' '){
					ch = ch+ s1.charAt(i);
					//System.out.println(i+". ch: "+ ch);
				}
				else{
					n = (int)Integer.parseInt(ch) - 22;
					ch = "";
					s = s + String.valueOf((char)n);
					//System.out.println(i+". S: "+ s);
				}
			}
			
		}
		catch(Exception e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error in Decryption");
		}
		textArea.setText(s);
	}
	
	void open(){
		try{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int resp = fc.showOpenDialog(null);
		if (resp == JFileChooser.APPROVE_OPTION) {
		    dir = fc.getSelectedFile();
		    //JOptionPane.showMessageDialog(null, dir);
		
		    String fileName;
		    if(!(String.valueOf(dir).contains(".txt"))){
	    		fileName = String.valueOf(dir)+".txt";
	    	}
	    	else{
	    		fileName = String.valueOf(dir);
	    	}
		
		    BufferedReader br = new BufferedReader(new FileReader(fileName));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append(System.lineSeparator());
		            line = br.readLine();
		        }
		        String everything = sb.toString();
		        savedText = everything;
		        textArea.setText(savedText);
		    } finally {
		        br.close();
		    }
		}
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error in opening");
		}
		
	}

	

}
