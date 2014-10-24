package main;


import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.*;

public class FunctionTablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int arity;
	private Integer table[][];
	private JTextField jTable[][];
	private Line2D horz,vert;
	
	public FunctionTablePanel(int arity){
		super();
		this.setSize(450, 450);
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
			for(int j = 0; j < jTable[i].length; j++){
				jTable[i][j] = new JTextField();
			}
		}
		int x,y,width, height;
		x = y = 0;
		width = height = 20;
		/*
		 * draw vertical line then 10 boxes
		 * draw horizontal line
		 * drop into switch statement
		 */
		x = 25;
		vert = new Line2D.Float(x, y, x, this.getHeight());
		x += 5;
		for(int i = 1; i < jTable[0].length; i++){
			jTable[0][i].setBounds(x, y, width, height);
			this.add(jTable[0][i]);
			x+=25;
		}
		//draw horizontal line
		y += 25;
		horz = new Line2D.Float(0, y, this.getWidth(), y);
		y += 5;
		switch(arity){
		case 1:
			/*arity one is a single row of inputs 
			 * first line skip 0,0 draw vertical line then other 10 text fields
			 * draw horizontal line
			 * skip 1,0, then draw next 10
			 */
			x = 30;
			for(int i = 1; i < jTable[1].length; i++ ){
				jTable[1][i].setBounds(x, y, width, height);
				this.add(jTable[1][i]);
				x+=25;
			}
				
			break;
		case 2:
			/*arity two we need a much larger table 
			 *first line skip 0,0 draw vertical line then other 10 text fields
			 *draw horizontal line
			 *write out 1,0 and skip a little then draw remaining ten
			 *do again 
			 */
			for(int i = 1; i < jTable.length; i++){
				x= 0;
				for(int j = 0; j < jTable[i].length; j++, x+=25){
					jTable[i][j].setBounds(x, y, width, height);
					jTable[i][j].setVisible(true);
					this.add(jTable[i][j]);
				}
				y+=25;
			}
			break;
		default:
			System.err.println("Something bad happened");
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.draw(horz);
		g2D.draw(vert);
	}
	
	//creates a table when is called
	public Integer[][] getTable(){
		return table;
	}
	
	public static void main(String[] args){
		FunctionTablePanel s = new FunctionTablePanel(1);
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(new Dimension(450, 450));
        frame.add(s);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
