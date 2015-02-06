package main;

import util.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class AlgebraMenu extends JDialog implements ActionListener, SharedValues {
	
	private static final long serialVersionUID = 8775445882529480950L;
	private String[] sets ={"P","Q", "N","Z","R"};
	private JTextArea identities;
	private Algebra alg;
	private JComboBox setChoice;
	private JTextField constantText;
	private ArrayList<Function> funcs;
	private JTabbedPane tables;
	
	
	public AlgebraMenu(ArrayList<Function> functions){
		this.funcs = functions;
		init(functions);
	}
	
	public Algebra getAlgebra(){
		return alg;
	}
	
	private void init(ArrayList<Function> functions){
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JLabel setLabel = new JLabel("Set:");
		setLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(setLabel,gbc);
		
		
		
		setChoice = new JComboBox(sets);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(setChoice,gbc);
		
		JLabel constantLabel = new JLabel("Constants:",SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(constantLabel, gbc);
		
		constantText = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(constantText, gbc);
		
		JLabel IdLabel = new JLabel("Identies:",SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.gridwidth = 2;
		panel.add(IdLabel,gbc);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		JButton button;
		for(int x = 0; x < functions.size(); x++){
			button = new JButton(Character.toString(functions.get(x).getSymbol()));
			button.addActionListener(this);
			button.setPreferredSize(new Dimension(20,20));
			buttonPanel.add(button);
		}
		button = new JButton(Character.toString((char) 0x2261));
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(20,20));
		buttonPanel.add(button);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		panel.add(buttonPanel,gbc);
		
		
		identities = new JTextArea(50,25);
		JScrollPane idScroll = new JScrollPane(identities);
		idScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		gbc.insets = new Insets(10,10,10,10);
		gbc.ipady = 120;
		panel.add(idScroll,gbc);
		
		
		JButton done = new JButton("Done");
		done.addActionListener(this);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(10,0,0,0);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.gridy = 5;
		panel.add(done,gbc);
		
		this.setSize(350, 300);
		this.setTitle("An Algebra");
		this.setResizable(true);
		//this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Function> funcs = new ArrayList<Function>();
		for(int x = 0; x < SharedValues.symbolValues.length; x++)
			funcs.add(new Function(symbolValues[x],2));
		AlgebraMenu alg = new AlgebraMenu(funcs);
		alg.setModal(true);
		alg.setVisible(true);
		System.out.println(alg.getAlgebra());

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton event = (JButton)arg0.getSource();
		if(!event.getText().contains("Done"))
			identities.insert(event.getText(), identities.getCaretPosition());
		else{
			ArrayList<Identity> idents = new ArrayList<Identity>();
			ArrayList<Object> constants = new ArrayList<Object>();
			
			String set = (String)setChoice.getSelectedItem();
			String consts[] = this.constantText.getText().split(",");
			for(int x = 0; x < consts.length; x++){
				constants.add(consts[x]);
			}
			String text = identities.getText();
			String[] ids = text.split("\n");
			for(int x = 0; x < ids.length; x++){
				try{
					String[] id = ids[x].split(Character.toString((char) 0x2261));
					//idents.add(new Identity(this.validateAndParanthesize(id[0]),this.validateAndParanthesize(id[1])));
				}
				catch(IndexOutOfBoundsException ioob){
					System.err.println("invalid identity:"+ids[x]);
				}
			}
			ArrayList<Function> functions = new ArrayList<Function>();
			for(int x = 0; x < this.funcs.size(); x++)
				if(text.contains(funcs.get(x).getSymbol().toString()))
					functions.add(funcs.get(x));
			//alg = new Algebra(set,functions,constants,idents);
			this.dispose();;
		}
			
	}
	
	private String validateAndParanthesize(String id){
		Stack<Object> vars = new Stack<Object>();
		Stack<Character> ops = new Stack<Character>();
		String finaleString = "";
		if(id.length() == 1)
			return id;
		for(int x = 0; x < id.length(); x++){
			if(Character.isDigit(id.charAt(x))){
				String temp = ""+id.charAt(x++);
				//have to loop 
				while(Character.isDefined(id.charAt(x))){
					temp += id.charAt(x);
					x++;
				}
				vars.push(temp);
			}
			else if(Character.isLetter(id.charAt(x))){
				vars.push(new Character(id.charAt(x)));
			}
			else if(id.charAt(0) == '(' || id.charAt(0) == ')'){
				//do nothing but have to watch for it
			}
			else if(id.charAt(x) == ' '){
				//do nothing but had to watch for it to come up
			}
			else{
				//this will be for the functions must check 
				//check that it is a function
				//then make sure that right number of vars are there 
			}
		}
		return id;
	}
}
