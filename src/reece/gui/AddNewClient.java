/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reece.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import reece.Booking;
import reece.DuplicateException;

/**
 *
 * @author C.D Chabalala
 */
public class AddNewClient extends JFrame {
    
    JLabel lblname = new JLabel("Name:");
    JLabel lblIdNr = new JLabel("ID Number:");
    JLabel lblroomSize = new JLabel("Room Size:");
    JLabel lblnoPeople = new JLabel("No of People:");
    JLabel lblnoDays = new JLabel("No of Days:");
    
    JTextField txtname = new JTextField();
    JTextField txtnoPeople = new JTextField();
    JTextField txtnoDays = new JTextField();
    JTextField txtIDNr = new JTextField();
    
    JButton btnaddBoking = new JButton("Add Booking");
    JButton btnClear = new JButton("Clear");
    JButton btnclose = new JButton("Close");
    ButtonGroup g = new ButtonGroup();
    
    JRadioButton radsingle = new JRadioButton("Single");
    JRadioButton radDouble = new JRadioButton("Double");
    JRadioButton radGrandD = new JRadioButton("Grand Deluxe");
    
    private ArrayList<Booking> arBooking;
    
    public AddNewClient(ArrayList<Booking> arBooking) {
        
        this.arBooking = arBooking;
        g.add(radsingle);
        g.add(radDouble);
        g.add(radGrandD);
        
        JPanel panel = new JPanel();
        
        panel.setLayout(null);
        
        panel.add(lblname);
        panel.add(lblroomSize);
        panel.add(lblnoPeople);
        panel.add(lblnoDays);
        panel.add(lblIdNr);
        panel.add(txtIDNr);
        panel.add(txtname);
        panel.add(txtnoPeople);
        panel.add(txtnoPeople);
        panel.add(txtnoDays);
        panel.add(btnaddBoking);
        panel.add(btnClear);
        panel.add(btnclose);
        panel.add(radDouble);
        panel.add(radGrandD);
        panel.add(radsingle);
        
        lblIdNr.setBounds(10, 20, 140, 20);
        txtIDNr.setBounds(100, 20, 140, 20);
        lblname.setBounds(10, 50, 140, 20);
        txtname.setBounds(100, 50, 140, 20);
        lblroomSize.setBounds(10, 80, 140, 20);
        radsingle.setBounds(100, 80, 100, 20);
        radDouble.setBounds(200, 80, 120, 20);
        radGrandD.setBounds(330, 80, 130, 20);
        lblnoPeople.setBounds(10, 110, 140, 20);
        txtnoPeople.setBounds(100, 110, 140, 20);
        lblnoDays.setBounds(10, 140, 140, 20);
        txtnoDays.setBounds(100, 140, 140, 20);
        
        btnaddBoking.setBounds(50, 190, 120, 23);
        btnClear.setBounds(200, 190, 120, 20);
        btnclose.setBounds(390, 190, 120, 20);
        
        setContentPane(panel);
        
        btnclose.addActionListener(new btnCloseEvent());
        btnaddBoking.addActionListener(new btnaddBookingEvent());
        btnClear.addActionListener(new btnClearEvent());
        
    }
    
    private class btnaddBookingEvent implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String name, IdNr, roomsize = " ";
            int noOfPeople, noDays;
            
            try {
                name = txtname.getText();
                IdNr = txtIDNr.getText();
                noOfPeople = Integer.parseInt(txtnoPeople.getText());
                noDays = Integer.parseInt(txtnoDays.getText());
                
                if (radsingle.isSelected()) {
                    roomsize = "Single";
                } else if (radDouble.isSelected()) {
                    roomsize = "Double";
                } else if (radGrandD.isSelected()) {
                    roomsize = "Grand_Deluxe";
                }
                
                Booking obj = new Booking(IdNr, name, roomsize, noOfPeople, noDays);
                obj.addNew(obj);
                JOptionPane.showMessageDialog(null, "Client Saved!");
                
            } catch (NumberFormatException | DuplicateException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (IllegalArgumentException ex) {
                
                JOptionPane.showMessageDialog(null, ex.getMessage());
                
            }
            
        }
    }

    /**
     * public static void main(String[] args) {
     *
     * AddNewClient frm = new AddNewClient(); frm.setVisible(true);
     * frm.setSize(600,450);
     *
     *
     * }
     */
    private class btnClearEvent implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            txtIDNr.setText("");
            txtname.setText("");
            txtnoDays.setText("");
            txtnoPeople.setText("");
            radDouble.setSelected(false);
            radsingle.setSelected(false);
            radGrandD.setSelected(false);
            
        }
    }
    
    private class btnCloseEvent implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            dispose();
        }
    }
}
