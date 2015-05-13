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
	//private ArrayList<Integer[][]> intTables;
	private ArrayList<Integer> algebraConstants;
	private ArrayList<Integer> algebraTableValues;
	private ArrayList<Character> termTableVars;
	private ArrayList<Integer> termVarValues;
	private JLabel[] varNames;
	private JTextField[] varValues;
	private JTabbedPane tables;
	
	public AlgebraTablesMenu(ArrayList<Function> functions){
		funcs = functions;
		this.init();
	}
	
	public AlgebraTablesMenu(ArrayList<Function> functions, ArrayList<Integer> constants){
		funcs = functions;
		algebraConstants = constants;
		this.init();
	}
	
	public AlgebraTablesMenu(ArrayList<Function> functions, ArrayList<Integer> constants,ArrayList<Character> variables){
		funcs = functions;
		algebraConstants = constants;
		termTableVars = variables;
		init();
	}
	
	private void init(){
		algebraTableValues = new ArrayList<Integer>();
		int vars = 0;
		if(this.termTableVars != null)
			vars+=60;
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Please enter values in the function tables");
		panel.add(label);
		label.setBounds(0, 5, 500, 10);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		
		tables = new JTabbedPane();
		//tables.registerKeyboardAction(this,"TAB", KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		tablePanels = new ArrayList<FunctionTablePanel>();
		tables.setBounds(0,20,500,500);
		for(int x = 0; x < funcs.size(); x++){
			/*JScrollPane scroll = new JScrollPane();
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);*/
			FunctionTablePanel functionTable = new FunctionTablePanel(funcs.get(x).getArity(),this.algebraConstants);
			tablePanels.add(functionTable);
			functionTable.setVisible(true);
			//scroll.add(functionTable);
			tables.add(funcs.get(x).getSymbol()+"",functionTable);
			
		}
		panel.add(tables);
		if(this.termTableVars != null){
			int x,y;
			x = 10; 
			y = 520;
			varNames = new JLabel[this.termTableVars.size()];
			varValues = new JTextField[this.termTableVars.size()];
			for(int i = 0; i < this.termTableVars.size(); i++){
				varNames[i] = new JLabel(termTableVars.get(i)+":");
				varNames[i].setBounds(x, y, 20, 50);
				panel.add(varNames[i]);
				varValues[i] = new JTextField();
				varValues[i].setBounds(x+20,y,50,50);
				panel.add(varValues[i]);
				x+=80;
			}
		}
		
		JButton button = new JButton("Done");
		button.setBounds(390, vars+520, 100, 50);
		button.addActionListener(this);
		panel.add(button);
		
		this.setTitle("Algerbra");
		this.setSize(500, 600+vars);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public ArrayList<Integer> getConsts(){
		return this.algebraConstants;
	}
	
	public ArrayList<Integer> getTableValues(){
		return this.algebraTableValues;
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
		ArrayList<Integer> localVals = null;
		//find a table with arity 2
		for(y = 0; y < this.funcs.size(); y++){
			if(funcs.get(y).getArity() == 2){
				//System.out.println("table "+y);
				break;
			}
		}
		Integer[][] table = tablePanels.get(y).getTable();
		if(table == null)
			System.out.println("ERROR:null table");
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
		//System.out.println("Rows "+rows+"; Columns "+columns);
		for(int i = 0; i < tablePanels.size(); i++){
			table = tablePanels.get(i).getTable();
			//System.out.println("Checking table "+i);
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
							//System.out.println("table:"+i+", row:" +x+", columns:"+y);
							JOptionPane.showMessageDialog(null,"table:"+i+", row:" +x+", columns:"+y,"Table Error", JOptionPane.ERROR_MESSAGE);
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
							//System.out.println("table:"+i+", row:" +x+", columns:"+y);
							JOptionPane.showMessageDialog(null,"table:"+i+", row:" +x+", columns:"+y,"Table Error", JOptionPane.ERROR_MESSAGE);
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
		localVals = new ArrayList<Integer>();
		for(x = 1; x < columns; x++){
			if(!localVals.contains(table[0][x])){
				localVals.add(table[0][x]);
			}
			else{
				//System.err.println("Error: duplicate constants");
				JOptionPane.showMessageDialog(null,"Duplicate Constants","Constant Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if(funcs.get(0).getArity() == 2){
			for(x = 1; x < rows; x++){
				if(!localVals.contains(table[x][0])){
					//System.err.println("ERROR: missing constant");
					JOptionPane.showMessageDialog(null,"Missing Constant","Constant Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		//got constants now must test other tables
		//System.out.println(constants);
		for(x = 1; x < tablePanels.size(); x++){
			table = tablePanels.get(x).getTable();
			//check columns
			for(y = 1; y < columns; y++){
				if(!localVals.contains(table[0][x])){
					//System.err.println("ERROR: missing constant at table:"+x+", row:"+y+", value:"+table[0][x]);
					JOptionPane.showMessageDialog(null,"Missing constant at table:"+x+", row:"+y+", value:"+table[0][x],"Constant Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			//if arity 2 check rows
			if(funcs.get(x).getArity() == 2){
				for(y = 1; y < rows; y++){
					if(!localVals.contains(table[y][0])){
						//System.err.println("ERROR: missing constant at table:"+x+", row:"+y);
						JOptionPane.showMessageDialog(null,"Missing constant at table:"+x+", row:"+y,"Constant Error", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
			}
		}
		this.algebraTableValues = localVals;
		return true;
	}
	
	private void createTables(int tableSize){
		//System.out.println("called create tables, constants="+constants);
		for(int i = 0; i < funcs.size(); i++){
			Integer[][] table = tablePanels.get(i).getTable();
			Integer[][] temp;
			int x,y,xLimit,yLimit;
			if(funcs.get(i).getArity() == 1){//just two rows
				yLimit = 2;
				xLimit = tableSize+1;
			}
			else{//multiple rows
				yLimit = xLimit = tableSize+1;
				
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
	
	private boolean filledVars(){
		if(this.termTableVars != null){
			this.termVarValues = new ArrayList<Integer>();
			for(int x = 0; x < this.varValues.length; x++){
				try{
					Integer value = Integer.parseInt(varValues[x].getText());
					//System.out.println(this.varNames[x].getText()+" value "+value);
					termVarValues.add(value);
				}
				catch(Exception e){
					System.err.println(""+this.varNames[x].getText()+" was not filled in");
					return false;
				}
			}
			return true;
		}
		else{
			return true;
		}
	}
	
	public ArrayList<Integer> getVariableValues(){
		return this.termVarValues;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Function> funcs = new ArrayList<Function>();
		funcs.add(new Function('^',2));
		funcs.add(new Function('!',1));
		ArrayList<Character> vars = new ArrayList<Character>();
		vars.add('x');
		vars.add('y');
		vars.add('z');
		vars.add('w');
		AlgebraTablesMenu test = new AlgebraTablesMenu(funcs,null,vars);
		//test.setDefaultCloseOperation(EXIT_ON_CLOSE);
		test.setVisible(true);
		test.setModal(true);
		//test = null;
		//System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
		if(command.contains("TAB")){
			System.out.println("tab");
		}
		else{
			ArrayList<Integer[][]> tempTables = new ArrayList<Integer[][]>();
			boolean valid = this.validate(tempTables);
			boolean filled = this.filledVars();
			if(valid && filled){
				//do stuff
				//System.out.println("So far so good");
				//must now create the tables
				this.createTables(this.algebraTableValues.size());
				this.setVisible(false);
			}
			else{
				System.err.println("No good bub");
			}
		}
		//System.exit(0);
		
	}

}
