package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import util.*;

public class MainMenu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 6400013318405434904L;
	private static Object lock = new Object();
	private JButton submit;
	private JTextArea top;
	private JTabbedPane bottom;
	private ArrayList<Identity> terms;
	private ArrayList<Function> functions;
	private Algebra[] algebras;
	private int algCount;
	
	public MainMenu(ArrayList<Identity> terms, ArrayList<Function> functions){
		this.terms = terms;
		this.functions = functions;
		this.algebras = new Algebra[3];
		this.algCount = 0;
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
		
		top = new JTextArea(50,50);
		top.setEditable(false);
		JScrollPane scrollTop = new JScrollPane(top);
		scrollTop.setBounds(10, 10, 780, 300);
		scrollTop.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//if(terms.size() >)
		if(terms != null)
			for(int x = 0; (x < terms.size()) && (x < 1000); x++)
				top.append(terms.get(x)+"\n");
		panel.add(scrollTop);
		
		/*
		bottom = new JTextArea(50,50);
		bottom.setEditable(false);
		JScrollPane scrollBottom = new JScrollPane(bottom);
		scrollBottom.setBounds(10,350,780,250);
		scrollBottom.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollBottom);
		*/
		bottom = new JTabbedPane();
		bottom.setBounds(10, 350, 780, 250);
		panel.add(bottom);
		
		submit = new JButton("End");
		//submit.setText("End");
		submit.addActionListener(this);
		submit.setBounds(695, 605, 100, 50);
		panel.add(submit);
		
		this.setTitle("Abstract Algerbra");
		this.setSize(800, 700);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMenu test = new MainMenu(null,null);
		test.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JButton){
			System.exit(0);
		}
		else{
			if(algCount < algebras.length){
				this.setVisible(false);
				AlgebraMenu algMenu = new AlgebraMenu(functions);
				algMenu.setModal(true);
				algMenu.setVisible(true);
				this.setVisible(true);
			
				algebras[algCount++] = algMenu.getAlgebra();
				JTextArea tab = new JTextArea(50,50);
				tab.setEditable(false);
				JScrollPane tabScroll = new JScrollPane(tab);
				tabScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				tab.append(algebras[algCount-1].toString());
				bottom.addTab("Algebra "+algCount, tabScroll);
				
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
