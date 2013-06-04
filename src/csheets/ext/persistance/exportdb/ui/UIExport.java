package csheets.ext.persistance.exportdb.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



import csheets.core.Cell;
import csheets.ext.persistance.exportdb.controllers.ControllerExport;
import csheets.ext.persistance.exportdb.core.ThreadExport;

/**
 * Representes the dialog with the user
 * Receive the pharameters to create a connection to any dataBaseTechnology and execute thread
 * @author 1110333 Tiago Pacheco
 */
public class UIExport extends JFrame{
	
	private String [] databaseDrivers={"HSQLDB","Others"};
	private JComboBox driversCombo;
	private ControllerExport control;
	private JButton ok=new JButton("Ok");
	private JButton cancel=new JButton("Cancel");
    private Cell[][]cells;
    private JTextField userText;
    private JTextField passText;
    private JTextField tableText;
    private JTextField databaseText;
    private ThreadExport threadExp;

	public UIExport(Cell[][] cells) throws Exception
	{
		super("Export DataBase");
		this.cells=cells;
		
		driversCombo=new JComboBox(databaseDrivers);
		JPanel mainPanel=new JPanel(new BorderLayout());
        //labels with some requirements to export to database
       
        JLabel labelDrivers=new JLabel("DataBase drivers");
        JLabel labelUser=new JLabel("UserName");
        JLabel labelPassword=new JLabel("Password");
        JLabel labelTable=new JLabel("Table's name");
  
       
        userText=new JTextField(10);
        passText=new JTextField(10);
        tableText=new JTextField(10);
 
        
        JPanel panel=new JPanel(new GridLayout(5,1));
        
        panel.add(labelDrivers);
        panel.add(driversCombo);
        panel.add(labelUser);
        panel.add(userText);
        panel.add(labelPassword);
        panel.add(passText);
        panel.add(labelTable);
        panel.add(tableText);

        
        JPanel panelButton=new JPanel();
        panelButton.add(ok);
        panelButton.add(cancel);
        
        //okButton actionListener
        ok.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent eve) {
                butOkActionPerformed(eve);
            }
        });
        //cancelButton actionListener
        cancel.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                CancelActionPerformed(event);
            }
        });
        //comboBox actionListener (not implemented)
        driversCombo.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent ev) {
               comboDriverAction(ev);
            }
        });
       
    	Container c = getContentPane();
        mainPanel.add(panel);
        mainPanel.add(panelButton,BorderLayout.SOUTH);
		c.add(mainPanel);
		;
		pack();
		setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
	}
    private void butOkActionPerformed(ActionEvent event) {
    
    
    	try {
          
        
    	 		threadExp=new ThreadExport(cells, userText.getText(), passText.getText(), tableText.getText());
           		threadExp.run();
           
       } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Try Again");
       }
    }
    private void CancelActionPerformed(ActionEvent event) {
        this.setVisible(false);
    }

    private void comboDriverAction(ActionEvent event) {
      
        	
        
    }
    
   

}
