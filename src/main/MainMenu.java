package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;

import util.*;

public class MainMenu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 6400013318405434904L;
	private JButton submit;
	private JButton moreIDs;
	private JButton moreReject;
	private JTabbedPane top;
	private JTabbedPane bottom;
	private JTextArea[] tabsLeft;
	private JTextArea[] tabsRight;
	private Algebra[] algebras;
	private Integer[] count;
	private int algCount;
	
	public MainMenu(Algebra[] algebras){
		this.algebras = algebras;
		algCount = 0;
		tabsLeft = new JTextArea[algebras.length];
		tabsRight = new JTextArea[algebras.length];
		count = new Integer[algebras.length];
		while(algebras[algCount] != null){
			algCount++;
		}
		init();
	}
	
	private void init(){
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		
		panel.setLayout(null);
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem algebra = new JMenuItem("Add Algebra");
		algebra.addActionListener(this);
		file.add(algebra);
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(this);
		file.add(save);
		JMenuItem exit = new JMenuItem("exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		file.add(exit);
		menu.add(file);
		this.setJMenuBar(menu);
		
		top = new JTabbedPane();
		top.setBounds(10, 10, 780, 300);
		panel.add(top);
		
		JPanel test = new JPanel();
		test.setLayout(new GridLayout(0,2));
		
		JTextArea temp = new JTextArea(50,50);
		tabsLeft[0] = temp;
		temp.setEditable(false);
		JScrollPane scrollTopLeft = new JScrollPane(temp);
		//scrollTop.setBounds(10, 10, 440, 300);
		scrollTopLeft.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTopLeft.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		if(algebras[0] != null){
			if(algebras[0].getIdenitites().size() > 10000){
				this.count[0] = 10000;
			}
			else{
				this.count[0] = algebras[0].getIdenitites().size();
			}
			temp.append(algebras[0].toString()+"\n");
		}
		/*
		 * must add for second scroll area
		 */
		temp = new JTextArea(50,50);
		tabsRight[0] = temp;
		temp.setEditable(false);
		JScrollPane scrollTopRight = new JScrollPane(temp);
		scrollTopRight.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTopRight.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		if(algebras[0] != null){
			if(algebras[0].getRejectIdenities().size() > 10000){
				this.count[0] = 10000;
			}
			else{
				this.count[0] = algebras[0].getRejectIdenities().size();
			}
			temp.append(algebras[0].toStringRejected()+"\n");
		}
		
		test.add(scrollTopLeft);
		test.add(scrollTopRight);
		top.addTab("Algebra "+algCount, test);
		
		
		bottom = new JTabbedPane();
		bottom.setBounds(10, 350, 780, 250);
		panel.add(bottom);
		
		JTextArea tab = new JTextArea(50,50);
		tab.setEditable(false);
		if(this.algCount >= 1){
			ArrayList<Function> funcs = this.algebras[0].getFunctions();
			for(int x = 0; x < funcs.size(); x++){
				tab.append(funcs.get(x).toStringWithTable()+"\n");
			}
		}
		JScrollPane tabScroll = new JScrollPane(tab);
		tabScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//tab.append(algebras[algCount-1].toString());
		bottom.addTab("Algebra "+algCount, tabScroll);
		
		moreIDs = new JButton("More IDs");
		moreIDs.addActionListener(this);
		moreIDs.setBounds(585, 310, 100, 50);
		panel.add(moreIDs);
		
		moreReject = new JButton("More Reject");
		moreReject.addActionListener(this);
		moreReject.setBounds(685, 310, 100, 50);
		panel.add(moreReject);
		
		
		
		
		submit = new JButton("End");
		//submit.setText("End");
		submit.addActionListener(this);
		submit.setBounds(685, 605, 100, 50);
		panel.add(submit);
		
		this.setTitle("Abstract Algerbra");
		this.setSize(800, 700);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Algebra[]  algs = new Algebra[3];
		MainMenu test = new MainMenu(algs);
		test.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JButton){
			//System.exit(0);
			JButton temp = (JButton)arg0.getSource();
			if(temp.getText().contains("More IDs")){
				ArrayList<Identity> ids;
				int selected = top.getSelectedIndex();
				int topEnd;
				ids = algebras[selected].getIdenitites();
				tabsLeft[selected].setText("");
				if(this.count[selected] < (ids.size() - 10000)){
					topEnd = count[selected] + 10000;
				}
				else if(this.count[selected] == ids.size()){
					count[selected] = 0;
					if(ids.size()< 10000)
						topEnd = ids.size();
					else
						topEnd = 10000;
				}
				else{
					topEnd = ids.size();
				}
				for(int x = count[selected]; x < topEnd; x++)
					tabsLeft[selected].append(ids.get(x)+"\n");
				tabsLeft[selected].setCaretPosition(0);
				count[selected] +=  topEnd - count[selected];
			}
			else if(temp.getText().contains("More Reject")){
				ArrayList<Identity> ids;
				int selected = top.getSelectedIndex();
				int topEnd;
				ids = algebras[selected].getRejectIdenities();
				tabsRight[selected].setText("");
				if(this.count[selected] < (ids.size() - 10000)){
					topEnd = count[selected] + 10000;
				}
				else if(this.count[selected] == ids.size()){
					count[selected] = 0;
					if(ids.size()< 10000)
						topEnd = ids.size();
					else
						topEnd = 10000;
				}
				else{
					topEnd = ids.size();
				}
				for(int x = count[selected]; x < topEnd; x++)
					tabsRight[selected].append(ids.get(x)+"\n");
				tabsRight[selected].setCaretPosition(0);
				count[selected] +=  topEnd - count[selected];
			}
			else{
				System.exit(0);
			}
		}
		else{
			JMenuItem temp = (JMenuItem)arg0.getSource();
			//System.out.println(temp.getText());
			if(temp.getText().contains("Add")){
				
				Object[] options = {"Rejected",
	            "Accepted"};
				int n = JOptionPane.showOptionDialog(null,
						"Would you like to see the accepted or rejected identities?",
						"Accepted or Rejected",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]);
						
				if(algCount < algebras.length){
					this.setVisible(false);
					ArrayList<Function> newFunctions = new ArrayList<Function>();
					ArrayList<Function> oldFunctions = algebras[0].getFunctions();
					
					for(Function x: oldFunctions){
						newFunctions.add(new Function(x));
					}
					AlgebraTablesMenu algMenu = new AlgebraTablesMenu(newFunctions,algebras[0].getConstants());
					algMenu.setModal(true);
					algMenu.setVisible(true);
					this.setVisible(true);
					
					if(algCount == 1)
						algebras[algCount] = new Algebra(algebras[0].getVariables(), newFunctions,algMenu.getConsts(),algMenu.getTableValues(),algebras[0].getIdenitites());
					else
						if(n == 0)
							algebras[algCount] = new Algebra(algebras[0].getVariables(), newFunctions,algMenu.getConsts(),algMenu.getTableValues(),algebras[algCount-1].getRejectIdenities());
						else
							algebras[algCount] = new Algebra(algebras[0].getVariables(), newFunctions,algMenu.getConsts(),algMenu.getTableValues(),algebras[algCount-1].getIdenitites());
					algCount++;
					//System.out.println(algebras[algCount].getIdenitites());
					JTextArea tab = new JTextArea(50,50);
					tab.setEditable(false);
					JScrollPane tabScroll = new JScrollPane(tab);
					ArrayList<Function> funcs = this.algebras[algCount-1].getFunctions();
					for(int x = 0; x < funcs.size(); x++){
						tab.append(funcs.get(x).toStringWithTable()+"\n");
					}
					tabScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					bottom.addTab("Algebra "+algCount, tabScroll);
					/*
					tab = new JTextArea(50,50);
					tab.setEditable(false);
					tabsLeft[algCount-1] = tab;
					tabScroll = new JScrollPane(tab);
					ArrayList<Identity> ids;
					if(n == 0){
						ids = this.algebras[algCount-1].getRejectIdenities();
						tab.append("Number of false identites:"+ids.size()+"\n");
					}
					else{
						ids = this.algebras[algCount-1].getIdenitites();
						tab.append("Number of approved identites:"+ids.size()+"\n");
					}
					if(ids.size() > 10000){
						count[algCount-1] = 10000;
						for(int x = 0; x < 10000; x++)
							tab.append(ids.get(x)+"\n");
					}
					else{
						count[algCount-1] = ids.size();
						for(int x = 0; x < ids.size(); x++)
							tab.append(ids.get(x)+"\n");
						
					}
					tab.setCaretPosition(0);
					tabScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					*/
					JPanel test = new JPanel();
					test.setLayout(new GridLayout(0,2));
					
					tab = new JTextArea(50,50);
					tabsLeft[algCount-1] = tab;
					tab.setEditable(false);
					JScrollPane scrollTopLeft = new JScrollPane(temp);
					//scrollTop.setBounds(10, 10, 440, 300);
					scrollTopLeft.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scrollTopLeft.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					if(algebras[algCount-1] != null){
						if(algebras[algCount-1].getIdenitites().size() > 10000){
							this.count[algCount-1] = 10000;
						}
						else{
							this.count[algCount-1] = algebras[algCount-1].getIdenitites().size();
						}
						tab.append(algebras[algCount-1].toString()+"\n");
					}
					/*
					 * must add for second scroll area
					 */
					tab = new JTextArea(50,50);
					tabsRight[algCount-1] = tab;
					tab.setEditable(false);
					JScrollPane scrollTopRight = new JScrollPane(temp);
					scrollTopRight.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					scrollTopRight.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					if(algebras[algCount-1] != null){
						if(algebras[algCount-1].getRejectIdenities().size() > 10000){
							this.count[algCount-1] = 10000;
						}
						else{
							this.count[algCount-1] = algebras[algCount-1].getRejectIdenities().size();
						}
						tab.append(algebras[algCount-1].toStringRejected()+"\n");
					}
					
					test.add(scrollTopLeft);
					test.add(scrollTopRight);
					top.addTab("Algebra "+algCount, tabScroll);
					
					/*
					 * after getting the algebra now use it to weed out the bad identities
					 */
					//bottom.append(algebras[algCount-1].toString());
					
				}
				else{
					System.err.println("to many algebras");
				}
			}
			else if(temp.getText().contains("Save")){
				//need to ask if multiple files or just one file
				//if multiple files get time stamp
				final JFileChooser file = new JFileChooser();
				int retVal = file.showSaveDialog(this);
				if(retVal == 0){
					Object[] options = {"Single","Multiple"};
					final int n = JOptionPane.showOptionDialog(null,
							"Would you like a single or multiple files",
							"Files",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[0]);
					if(algCount > 0){
						Thread t = new Thread(new Runnable() {
						    public void run() {
						        /*
						         * Do something
						         */
						    	try{
							    	//System.out.println("writing file");
						    		//System.out.println(n);
						    		if(n == 0){
						    			PrintWriter writer = new PrintWriter(new FileWriter(file.getSelectedFile()));
										for(int x = 0; x < algCount; x++){
											//writer.println(algebras[x].toStringForFile());
											writer.println("Algebra "+x);
											algebras[x].toStringForFile(writer);
										}
										writer.flush();
										writer.close();
						    		}
						    		else{
						    			long time = System.currentTimeMillis();
						    			for(int x = 0; x < algCount; x++){
						    				String fileName = file.getSelectedFile().getParent()+"/"+time+"-algebra"+(x+1)+"-"+file.getSelectedFile().getName();
						    				//System.out.println(fileName);
						    				PrintWriter writer = new PrintWriter(new FileWriter(fileName));
						    				algebras[x].toStringForFile(writer);
						    				writer.flush();
						    				writer.close();
						    			}
						    			
						    		}
									JOptionPane.showMessageDialog(null, "File Save is Done");
						    	}
						    	catch(Exception e){
						    		System.err.println(e.getLocalizedMessage());
						    	}
						    }
						});

						t.start();
					}
				}
			}
			else{
				//save
				System.out.println("broken");
			}
		}
		
	}

}
