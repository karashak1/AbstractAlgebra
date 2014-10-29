package main;

import java.util.ArrayList;

import javax.swing.*;

import util.Function;

public class AlgebraTablesMenu extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Function> funcs;
	private JTabbedPane tables;
	
	public AlgebraTablesMenu(ArrayList<Function> functions){
		funcs = functions;
		this.init();
	}
	
	private void init(){
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Please enter values in the function tables");
		panel.add(label);
		label.setBounds(0, 5, 500, 10);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		
		tables = new JTabbedPane();
		tables.setBounds(0,20,500,500);
		for(int x = 0; x < funcs.size(); x++){
			/*JScrollPane scroll = new JScrollPane();
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);*/
			FunctionTablePanel functionTable = new FunctionTablePanel(funcs.get(x).getArity());
			functionTable.setVisible(true);
			//scroll.add(functionTable);
			tables.add(funcs.get(x).getSymbol()+"",functionTable);
			
		}
		panel.add(tables);
		
		JButton button = new JButton("Done");
		button.setBounds(390, 520, 100,	 50);
		panel.add(button);
		
		this.setTitle("Algerbra");
		this.setSize(500, 600);
		this.setResizable(false);
		//this.setDefaultCloseOperation();
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Function> funcs = new ArrayList<Function>();
		funcs.add(new Function('*',1));
		AlgebraTablesMenu test = new AlgebraTablesMenu(funcs);
		//test.setDefaultCloseOperation(EXIT_ON_CLOSE);
		test.setVisible(true);
		test.setModal(true);
		//test = null;
		//System.exit(0);
	}

}
