package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;

import tree.Node;
import util.Function;
import util.Identity;

public class TermMenu extends JDialog implements ActionListener{
	
	/*
	 * TODO
	 * [x] save prompt about all or selected
	 * [x] sort the terms by height
	 * [] add test button
	 * 		[] bring up test menu
	 * 		[] modded algebra table menu with variable spaces
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Node> terms;
	private ArrayList<Function> funcs;
	private ArrayList<Character> vars;
	private JList list;
	
	public TermMenu(ArrayList<Node> terms, ArrayList<Function> funcs, ArrayList<Character> vars){
		this.terms = terms;
		this.funcs = funcs;
		this.vars = vars;
		init();
	}

	private void init(){
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setLayout(null);
		String[] termStrings = new String[terms.size()];
		for(int x = 0; x < terms.size(); x++)
			termStrings[x] = terms.get(x).toString();
		list = new JList(termStrings);
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(0,20,500,500);
		panel.add(scroll);
		
		
		
		//buttons at the bottom
		JButton temp = new JButton("Save");
		temp.setBounds(10, 520, 100, 20);
		temp.addActionListener(this);
		panel.add(temp);
		
		temp = new JButton("Test");
		temp.setBounds(120, 520, 100, 20);
		temp.addActionListener(this);
		panel.add(temp);
		
		temp = new JButton("Done");
		temp.setBounds(230, 520, 100, 20);
		temp.addActionListener(this);
		panel.add(temp);
		
		this.setTitle("Terms");
		this.setSize(500, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton button = (JButton)arg0.getSource();
		if(button.getText().contains("Save")){
			JFileChooser file = new JFileChooser();
			int retVal = file.showSaveDialog(this);
			//System.out.println(retVal);
			if(retVal == 0){
				Object[] options = {"Yes, all the terms",
                "No, selected terms"};
				int n = JOptionPane.showOptionDialog(null,
						"Save all the terms or just selected?",
						"Terms",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]);
				int[] indices;
				if(n == 1)
					indices = list.getSelectedIndices();
				else{
					indices = new int[terms.size()];
					for(int x = 0; x < indices.length; x++)
						indices[x] = x;
				}
				//System.out.println("index entries "+indices.length);
				if(indices.length > 0){
					try{
						PrintWriter writer = new PrintWriter(new FileWriter(file.getSelectedFile()));
						for(int x = 0; x < indices.length; x++)
							writer.println(terms.get(indices[x]));
						writer.flush();writer.close();
					}
					catch(Exception e){
						System.err.println(e.getLocalizedMessage());
					}
				}
			}
		}
		else if(button.getText().contains("Test")){
			if(list.getSelectedIndex() != -1){
				int x, y,z,w;
				x = y = z = w = -1;
				AlgebraTablesMenu algebra = new AlgebraTablesMenu(funcs,null,vars);
				algebra.setModal(true);
				algebra.setVisible(true);
				ArrayList<Integer> values = algebra.getVariableValues();
				for(int i = 0; i < values.size(); i++){
					switch(vars.get(i)){
					case 'x':
						x = values.get(i);
						break;
					case 'y':
						y = values.get(i);
						break;
					case 'z':
						z = values.get(i);
						break;
					case 'w':
						w = values.get(i);
						break;
					default:
						System.err.println("Something bad happened getting the values from the algebra menu");
					}
				}
				
				int returnValue = Identity.evaluate(terms.get(list.getSelectedIndex()), x, y, z, w, funcs);
				JOptionPane.showMessageDialog(null,""+terms.get(list.getSelectedIndex())+"="+returnValue);
				
			}
		}
		else{
			this.setVisible(false);
		}
	
	}

}
