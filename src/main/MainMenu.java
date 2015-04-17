package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import util.*;

public class MainMenu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 6400013318405434904L;
	private JButton submit;
	private JButton more;
	private JTabbedPane top;
	private JTabbedPane bottom;
	private JTextArea[] tabs;
	//private ArrayList<Identity> terms;
	//private ArrayList<Function> functions;
	private Algebra[] algebras;
	private Integer[] count;
	private int algCount;
	
	public MainMenu(Algebra[] algebras){
		this.algebras = algebras;
		algCount = 0;
		tabs = new JTextArea[algebras.length];
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
		
		JTextArea temp = new JTextArea(50,50);
		tabs[0] = temp;
		temp.setEditable(false);
		JScrollPane scrollTop = new JScrollPane(temp);
		//scrollTop.setBounds(10, 10, 780, 300);
		scrollTop.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//if(terms.size() >)
		
		if(algebras[0] != null){
			if(algebras[0].getIdenitites().size() > 10000){
				this.count[0] = 10000;
			}
			else{
				this.count[0] = algebras[0].getIdenitites().size();
			}
			temp.append(algebras[0].toString()+"\n");
		}
		top.addTab("Algebra "+algCount, scrollTop);
		
		
		bottom = new JTabbedPane();
		bottom.setBounds(10, 350, 780, 250);
		panel.add(bottom);
		
		JTextArea tab = new JTextArea(50,50);
		tab.setEditable(false);
		if(this.algCount >= 1){
			ArrayList<Function> funcs = this.algebras[0].getFunctions();
			for(int x = 0; x < funcs.size(); x++){
				tab.append(funcs.get(x).toStrinWithTable()+"\n");
			}
		}
		JScrollPane tabScroll = new JScrollPane(tab);
		tabScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//tab.append(algebras[algCount-1].toString());
		bottom.addTab("Algebra "+algCount, tabScroll);
		
		/*bottom = new JTextArea(50,50);
		//bottom.setEditable(false);
		JScrollPane scrollBottom = new JScrollPane(bottom);
		scrollBottom.setBounds(10,350,780,250);
		scrollBottom.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		if(this.algCount >= 1){
			ArrayList<Function> funcs = this.algebras[0].getFunctions();
			for(int x = 0; x < funcs.size(); x++){
				bottom.append(funcs.get(x).toStrinWithTable()+"\n");
			}
			
		}
		*/
		
		more = new JButton("More");
		more.addActionListener(this);
		more.setBounds(685, 310, 100, 50);
		panel.add(more);
		
		
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
			if(temp.getText().contains("More")){
				ArrayList<Identity> ids;
				int selected = top.getSelectedIndex();
				int topEnd;
				if(selected == 0){
					ids = algebras[selected].getIdenitites();
				}
				else{
					ids = algebras[selected].getRejectIdenities();
				}
				tabs[selected].setText("");
				if(this.count[selected] < (ids.size() - 10000)){
					topEnd = count[selected] + 10000;
				}
				else{
					topEnd = ids.size();
				}
				for(int x = count[selected]; x < topEnd; x++)
					tabs[selected].append(ids.get(x)+"\n");
				tabs[selected].setCaretPosition(0);
			}
			else{
				System.exit(0);
			}
		}
		else{
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
					algebras[algCount] = new Algebra(algebras[0].getVariables(), newFunctions,algMenu.getConsts(),algMenu.getTableValues(),algebras[algCount-1].getRejectIdenities());
				algCount++;
				//System.out.println(algebras[algCount].getIdenitites());
				JTextArea tab = new JTextArea(50,50);
				tab.setEditable(false);
				JScrollPane tabScroll = new JScrollPane(tab);
				ArrayList<Function> funcs = this.algebras[algCount-1].getFunctions();
				for(int x = 0; x < funcs.size(); x++){
					tab.append(funcs.get(x).toStrinWithTable()+"\n");
				}
				tabScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				bottom.addTab("Algebra "+algCount, tabScroll);
				
				tab = new JTextArea(50,50);
				tab.setEditable(false);
				tabs[algCount-1] = tab;
				tabScroll = new JScrollPane(tab);
				ArrayList<Identity> ids = this.algebras[algCount-1].getRejectIdenities();
				tab.append("Number of false identites:"+ids.size()+"\n");
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
				top.addTab("Algebra "+algCount, tabScroll);
				
				/*
				 * after getting the algebra now use it to weed out the bad identities
				 */
				//bottom.append(algebras[algCount-1].toString());
			}
			else{
				//throw pop up about to many algebras
			}
		}
		
	}

}
