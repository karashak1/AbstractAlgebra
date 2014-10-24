package main;


import javax.swing.*;

public class FunctionTablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int arity;
	private Integer table[][];
	private JTextField jTable[][];
	
	public FunctionTablePanel(int arity){
		this.arity = arity;
		switch(arity){
		case 1:
			jTable = new JTextField[2][11];
			break;
		case 2:
			jTable = new JTextField[11][11];
			break;
		default:
			System.err.println("Something bad happened");
		}
		init();
	}
	
	private void init(){
		for(int i= 0; i < jTable.length; i++){
			for(int j = 0; j < jTable[j].length; i++){
				jTable[i][j] = new JTextField();
			}
		}
		switch(arity){
		case 1:
			/*arity one is a single row of inputs 
			 * first line skip 0,0 draw vertical line then other 10 text fields
			 * draw horizontal line
			 * skip 1,0, then draw next 10
			 */
			
			break;
		case 2:
			/*arity two we need a much larger table 
			 *first line skip 0,0 draw vertical line then other 10 text fields
			 *draw horizontal line
			 *write out 1,0 and skip a little then draw remaining ten
			 *do again 
			 */
			
			break;
		default:
			System.err.println("Something bad happened");
		}
	}
	
	//creates a table when is called
	public Integer[][] getTable(){
		return table;
	}

}
