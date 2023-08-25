/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reece.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import reece.Booking;
import reece.DataStorageException;
import reece.NotFoundException;

/**
 *
 * @author R.S Mkhari
 */
public class HomeScreen extends JFrame {
    
    JMenu mnFile, mnBooking, mnMore;
    JMenuItem miclientID, micountBooking, miExit, miAddClient, miDelete, miViewAll, miAbout;
    
    JLabel lblbackground;
    
    private ArrayList<Booking> arBooking;
    
    public HomeScreen() {
        
        arBooking = new ArrayList<>();
        
        ImageIcon exit = new ImageIcon("Images/exit.png");
        ImageIcon about = new ImageIcon("Images/about.png");
        ImageIcon search = new ImageIcon("Images/search.png");
        ImageIcon addBooking = new ImageIcon("Images/add.png");
        ImageIcon display = new ImageIcon("Images/display.png");
        ImageIcon delete = new ImageIcon("Images/delete.png");
        ImageIcon count = new ImageIcon("Images/edit.png");
        
        miclientID = new JMenuItem("Search Client Name", search);
        
        micountBooking = new JMenuItem("Count Booking", count);
        
        miExit = new JMenuItem("Exit", exit);
        
        miAddClient = new JMenuItem("Add Client", addBooking);
        
        miViewAll = new JMenuItem("View All Clients", display);
        
        miAbout = new JMenuItem("About", about);
        
        miDelete = new JMenuItem("Delete", delete);
        
        mnFile = new JMenu("File");
        mnBooking = new JMenu("Booking");
        mnMore = new JMenu("More");
        
        mnFile.add(miclientID);
        mnFile.add(micountBooking);
        mnFile.add(miDelete);
        mnFile.add(miExit);
        
        mnBooking.add(miAddClient);
        mnBooking.add(miViewAll);
        
        mnMore.add(miAbout);
        
        JMenuBar jmb = new JMenuBar();
        
        jmb.add(mnFile);
        jmb.add(mnBooking);
        jmb.add(mnMore);
        
        Image backImage = Toolkit.getDefaultToolkit().createImage("Images/HotelBackground.jpg");
        lblbackground = new JLabel(new ImageIcon(backImage));
        add(lblbackground);
        
        setJMenuBar(jmb);
        
        miclientID.addActionListener(new MiClientID());
        micountBooking.addActionListener(new MiCountBooking());
        miExit.addActionListener(new MiExit());
        miAddClient.addActionListener(new MiAddNew());
        miViewAll.addActionListener(new MiViewAll());
        miDelete.addActionListener(new MiDelete());
        miAbout.addActionListener(new MiMore());
        
        try {
            
            Booking.intilize();
        } catch (DataStorageException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    public static void main(String[] args) {
        
        HomeScreen frm = new HomeScreen();
        
        frm.setTitle("Hotel Booking System, by R.S Mkhari");
        frm.setVisible(true);
        frm.setSize(500, 450);
        frm.setResizable(false);
        frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    private class MiClientID implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String name;
            
            try {
                name = JOptionPane.showInputDialog("Enter the name of the client");
                if (!name.isEmpty()) {
                    
                    Booking clientNam = Booking.searchClient(name);
                    JOptionPane.showMessageDialog(null, clientNam);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Input the name");
                }
                
            } catch (NotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
        
    }
    
    private class MiCountBooking implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Booking.countBooking();
            
        }
        
    }
    
    private class MiDelete implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String name;
            
            try {
                name = JOptionPane.showInputDialog("Input the name to delete");
                if (!name.isEmpty()) {
                    
                    Booking.delete(name);
                    JOptionPane.showMessageDialog(null, "Deleted!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please Input the Name");
                }
            } catch (NotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
    }
    
    private class MiExit implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int res;
            
            try {
                
                res = JOptionPane.showConfirmDialog(null, "Do You Want To Exit?", "Exit", JOptionPane.YES_NO_OPTION);
                
                if (res == JOptionPane.YES_OPTION) {
                    Booking.terminate();
                    System.exit(0);
                }
            } catch (DataStorageException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
        
    }
    
    private class MiAddNew implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            AddNewClient frm = new AddNewClient(arBooking);
            frm.setTitle("Adding New Clients");
            frm.setVisible(true);
            frm.setSize(600, 350);
            
        }
    }
    
    private class MiViewAll implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            ViewAllBookings frm = new ViewAllBookings(arBooking);
            frm.setTitle("Viewing All Bookings");
            frm.setVisible(true);
            frm.setSize(750, 250);
            frm.setResizable(false);
            
        }
    }
    
    private class MiMore implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            JOptionPane.showMessageDialog(null, "This application was developed by R.S Mkhari as an exericise to further his knowledge about java Programming", "Message", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }
}
