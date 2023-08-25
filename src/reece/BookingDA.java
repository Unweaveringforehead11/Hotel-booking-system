/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reece;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * 
 * @author C.D Chabalala
 */
public class BookingDA {

    private static ArrayList<Booking> arBooking;
    private static final String HotelFile = "BookingFile.dat";

    public static void intilize() throws DataStorageException {

        try {
            FileInputStream fis = new FileInputStream(HotelFile);
            ObjectInputStream objread = new ObjectInputStream(fis);

            arBooking = (ArrayList<Booking>) objread.readObject();

            objread.close();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, "No data in the file " + e.getMessage());
            arBooking = new ArrayList<>();

        } catch (ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {

            throw new DataStorageException(" No Data  to read!! " + e.getMessage());

        }

    }

    public static void terminate() throws DataStorageException {

        try {

            FileOutputStream fos = new FileOutputStream(HotelFile);
            ObjectOutputStream objwrite = new ObjectOutputStream(fos);
            objwrite.writeObject(arBooking);
            objwrite.close();

        } catch (IOException e) {

            throw new DataStorageException("Cannot Write" + e.getMessage());
        }
    }

    public static void addNew(Booking obj) throws DuplicateException {

        boolean duplicate = false;

        for (int i = 0; i < arBooking.size() && !duplicate; i++) {

            if (arBooking.get(i).getIDnr().equals(obj.getIDnr())) {
                duplicate = true;
            }

        }

        if (duplicate) {
            throw new DuplicateException("File Entry  already exists");
        } else {
            arBooking.add(obj);
        }

    }

    public static ArrayList<Booking> getAll() {

        return arBooking;
    }

    public static Booking search(String name) throws NotFoundException {

        Booking obj = null;
        boolean found = false;

        for (int i = 0; i < arBooking.size() && !found; i++) {

            obj = arBooking.get(i);

            if (obj.getName().equalsIgnoreCase(name)) {
                found = true;

            }

        }

        if (found) {
            return obj;
        } else {
            throw new NotFoundException("Name Does Not Exist");
        }

    }

    public static void countRoomType() {

        int cntSingle = 0;
        int cntDouble = 0;
        int cntGrandDeluxe = 0;
        int totalBooking = 0;

        for (int i = 0; i < arBooking.size(); i++) {

            if (arBooking.get(i).getRoomSize().equalsIgnoreCase("Single")) {
                cntSingle += 1;
            } else if (arBooking.get(i).getRoomSize().equalsIgnoreCase("Double")) {
                cntDouble += 1;

            } else if (arBooking.get(i).getRoomSize().equalsIgnoreCase("Grand_Deluxe")) {
                cntGrandDeluxe += 1;
            }

        }

        totalBooking = cntSingle + cntGrandDeluxe + cntDouble;

        JOptionPane.showMessageDialog(null, "Single Rooms: " + cntSingle + " Double Rooms: " + cntDouble + " Grand_Deluxe: " + cntGrandDeluxe + "\n Total Bookings: " + totalBooking, "Number of Booking per Roomsize", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void delete(String name) throws NotFoundException {

        boolean found = false;

        Booking obj = null;

        for (int i = 0; i < arBooking.size() && !found; i++) {

            if (arBooking.get(i).getName().equalsIgnoreCase(name)) {
                found = true;
            }
            if (found) {
                arBooking.remove(i);
            }
        }

    }

}
