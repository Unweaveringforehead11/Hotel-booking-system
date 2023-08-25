/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reece.gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.util.*;
import javax.swing.JPanel;
import reece.Booking;

/**
 *
 * @author R.S Mkhari
 */
public class ViewAllBookings extends JFrame {

    JTextArea tadisp = new JTextArea();

    public ViewAllBookings(ArrayList<Booking> arBooking) {

        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.add(tadisp);

        tadisp.setBounds(0, 0, 750, 250);

        setContentPane(panel);

        arBooking = Booking.getAll();

        tadisp.append("IDN" + "\tName" + "\tRoomSize" + "\tNoOfPeople" + "\tDays" + "\tStdPrice" + "\tDiscAmt" + "\tTotal Payement " + "\n");

        for (int i = 0; i < arBooking.size(); i++) {

            tadisp.append(arBooking.get(i).toString());

        }

    }

}
