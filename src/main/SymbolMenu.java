package main;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

import util.SharedValues;

public class SymbolMenu extends JDialog implements WindowListener,SharedValues{
	
	private static final long serialVersionUID = -4651732366352035200L;
	private ArrayList<String> returnValues;
	private JLabel resultLabel;
	private JTextField resultText;
	private JButton submit;
	private boolean visible;
	
	public SymbolMenu(Character[] values, String title){
		returnValues = new ArrayList<String>();
		visible = true;
		init(values, title);
	}
	
	public SymbolMenu(Character[] values, String title, ArrayList<String> valuesArray){
		returnValues = valuesArray;
		visible = true;
		init(values, title);
	}
	
	public boolean checkVisibility(){
		return visible;
	}
	
	public void resetVisible(){
		visible = true;
	}
	
	private void init(Character[] values, String title){
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		
		panel.setLayout(null);
		int x,y;
		x = y = 0;
		for(int i = 0; i < values.length; i++){
			JButton temp = new JButton(Character.toString(values[i]));
			temp.setBounds(x, y, 50, 50);
			x+= 55;
			temp.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					JButton action = (JButton)event.getSource();
					if(!returnValues.contains(action.getText()))
						returnValues.add(action.getText());
					else
						returnValues.remove(action.getText());
					update();
				}
			});
			panel.add(temp);
		}
		y += 55;
		x = 5;
		resultLabel = new JLabel("Values:");
		resultLabel.setBounds(x, y, 50, 50);
		x+= 55;
		panel.add(resultLabel);
		
		resultText = new JTextField();
		resultText.setBounds(x, y, 50*(values.length), 50);
		resultText.setEditable(false);
		panel.add(resultText);
		
		y+= 55;
		x = 55*(values.length-2);
		submit = new JButton("Submit");
		submit.setBounds(x,y,100,50);
		submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				//System.out.println("disappering now");
				setVisible(false);
				
			}
		});
		panel.add(submit);
		
		if(55*values.length > 200)
			this.setSize(55*values.length, 200);
		else
			this.setSize(200,200);
		this.setTitle(title);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}
	
	public ArrayList<String> getValues(){
		return this.returnValues;
	}
	
	private void update(){
		String text = returnValues.toString();
		resultText.setText(text.substring(1, text.length()-1));
	}
	
	public static void main(String[] args){
		SymbolMenu sm = new SymbolMenu(SharedValues.symbolValues,"Symbols");
		while(sm.isVisible()){
			//System.out.println(sm.isVisible());
		}
		System.out.println("Done");
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
