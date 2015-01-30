package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import util.Function;

public class AlgebraTablesMenu extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Function> funcs;
	private ArrayList<FunctionTablePanel> tablePanels;
	private ArrayList<Integer[][]> intTables;
	private ArrayList<Integer> algebraConstants;
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
		tablePanels = new ArrayList<FunctionTablePanel>();
		tables.setBounds(0,20,500,500);
		for(int x = 0; x < funcs.size(); x++){
			/*JScrollPane scroll = new JScrollPane();
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);*/
			FunctionTablePanel functionTable = new FunctionTablePanel(funcs.get(x).getArity());
			tablePanels.add(functionTable);
			functionTable.setVisible(true);
			//scroll.add(functionTable);
			tables.add(funcs.get(x).getSymbol()+"",functionTable);
			
		}
		panel.add(tables);
		
		JButton button = new JButton("Done");
		button.setBounds(390, 520, 100,	 50);
		button.addActionListener(this);
		panel.add(button);
		
		this.setTitle("Algerbra");
		this.setSize(500, 600);
		this.setResizable(false);
		//this.setDefaultCloseOperation();
		this.setLocationRelativeTo(null);
	}
	
	private boolean validate(ArrayList<Integer[][]> tables){
		/*
		 * TODO
		 * [x] check the number row and columns
		 * [x] now check each other table to be sure that the other tables have the same
		 * [x] make sure that for any given position its valid
		 * [x] no duplicates
		 * [x] checked other tables
		 */
		int columns, rows, x,y;
		ArrayList<Integer> constants = null;
		//find a table with arity 2
		for(y = 0; y < this.funcs.size(); y++){
			if(funcs.get(y).getArity() == 2){
				System.out.println("table "+y);
				break;
			}
		}
		Integer[][] table = tablePanels.get(y).getTable();
		if(table == null)
			System.out.println("This is null stupid");
		for(x = 1; x < table[0].length; x++){
			if(table[0][x] == null)
				break;
		}
		columns = x;
		for(x = 1; x < table.length; x++){
			if(table[x][0] == null)
				break;
		}
		rows = x;
		System.out.println("Rows "+rows+"; Columns "+columns);
		for(int i = 0; i < tablePanels.size(); i++){
			table = tablePanels.get(i).getTable();
			System.out.println("Checking table "+i);
			switch(funcs.get(i).getArity()){
			case 1://just check columns
				for(x = 0; x < 2;x++){
					for( y = 0; y < rows; y++){
						if((x == 0) & (y == 0))
							continue;
						if((x == 1) & (y == 0))
							continue;
						if(table[x][y] == null){
							//print a warning of where the error is
							System.out.println("table:"+i+", row:" +x+", columns:"+y);
							return false;
						}
					}
				}
				break;
			case 2://check rows and columns
				for(x = 0; x < rows; x++){
					for(y = 0; y < columns; y++){
						if((x == 0) & (y == 0))
							continue;
						if(table[x][y] == null){
							//print a warning of where the error is
							System.out.println("table:"+i+", row:" +x+", columns:"+y);
							return false;
						}
					}
				}
				break;
			default:
				System.err.println("Bad juju");
			}	
		}
		/*
		 * get the numbers from the top row columns then 
		 * for arity 1 need to need to check to make sure they are there
		 * for arity 2 must check both columsn and rows
		 */
		table = tablePanels.get(0).getTable();
		constants = new ArrayList<Integer>();
		for(x = 1; x < columns; x++){
			if(!constants.contains(table[0][x])){
				constants.add(table[0][x]);
			}
			else{
				System.err.println("Error: duplicate constants");
				return false;
			}
		}
		if(funcs.get(0).getArity() == 2){
			for(x = 1; x < rows; x++){
				if(!constants.contains(table[x][0])){
					System.err.println("ERROR: missing constant");
					return false;
				}
			}
		}
		//got constants now must test other tables
		System.out.println(constants);
		for(x = 1; x < tablePanels.size(); x++){
			table = tablePanels.get(x).getTable();
			//check columns
			for(y = 1; y < columns; y++){
				if(!constants.contains(table[0][x])){
					System.err.println("ERROR: missing constant at table:"+x+", row:"+y+", value:"+table[0][x]);
					return false;
				}
			}
			//if arity 2 check rows
			if(funcs.get(x).getArity() == 2){
				for(y = 1; y < rows; y++){
					if(!constants.contains(table[y][0])){
						System.err.println("ERROR: missing constant at table:"+x+", row:"+y);
						return false;
					}
				}
			}
		}
		this.algebraConstants = constants;
		return true;
	}
	
	private void createTables(int constants){
		//System.out.println("called create tables, constants="+constants);
		for(int i = 0; i < funcs.size(); i++){
			Integer[][] table = tablePanels.get(i).getTable();
			Integer[][] temp;
			int x,y,xLimit,yLimit;
			if(funcs.get(i).getArity() == 1){//just two rows
				yLimit = 2;
				xLimit = constants+1;
			}
			else{//multiple rows
				yLimit = xLimit = constants+1;
				
			}
			temp = new Integer[yLimit][xLimit];
			for(y = 0; y < yLimit; y++ ){
				for(x = 0; x < xLimit; x++){
					temp[y][x] = table[y][x];
				}
			}
			funcs.get(i).setTable(temp);
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Function> funcs = new ArrayList<Function>();
		funcs.add(new Function('^',2));
		funcs.add(new Function('!',1));
		AlgebraTablesMenu test = new AlgebraTablesMenu(funcs);
		//test.setDefaultCloseOperation(EXIT_ON_CLOSE);
		test.setVisible(true);
		test.setModal(true);
		//test = null;
		//System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ArrayList<Integer[][]> tempTables = new ArrayList<Integer[][]>();
		boolean valid = this.validate(tempTables);
		if(valid){
			//do stuff
			System.out.println("So far so good");
			//must now create the tables
			this.createTables(this.algebraConstants.size());
			/*
			for(int i = 0; i < funcs.size(); i++){
				Integer[][] temp = funcs.get(i).getTable();
				System.out.println("table for function "+funcs.get(i).getSymbol());
				for(int y = 0; y < temp.length; y++){
					for(int x = 0; x < temp[y].length; x++){
						if(temp[y][x] == null)
							System.out.print("\t");
						else
							System.out.print(temp[y][x]+"\t");
					}
					System.out.println();
				}
			}
			*/
		}
		else{
			System.err.println("No good bub");
		}
		
		System.exit(0);
		
	}

}
