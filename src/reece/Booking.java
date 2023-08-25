/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reece;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author C.D Chabalala
 */
public class Booking implements Serializable {

    private String IDnr;
    private String name;
    private String roomSize;
    private int noOfPeople;
    private int noDays;

    public Booking() {

        name = roomSize = IDnr = " ";
        noOfPeople = noDays = 0;

    }

    public Booking(String IDNr, String name, String roomSize, int noOfPeople, int noDays) {

        setIDnr(IDNr);
        setName(name);
        setRoomSize(roomSize);
        setNoOfPeople(noOfPeople);
        setNoDays(noDays);

    }

    public String getIDnr() {
        return IDnr;
    }

    public void setIDnr(String IDnr) {

        if (IDnr.length() == 8) {
            this.IDnr = IDnr;
        } else {
            throw new IllegalArgumentException("Invalid Id Number. ID is less or more than  8 Digits");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name.length() > 3) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid Name. Name is too short");
        }
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public int getNoDays() {
        return noDays;
    }

    public void setNoDays(int noDays) {
        this.noDays = noDays;
    }

    public double calcPrice() {

        final double SINGLE = 250;
        final double DOUBLE = 380;
        final double GRAND_DELUXE = 495;

        double price = 0;

        if (roomSize.equalsIgnoreCase("single")) {
            price = SINGLE * noDays * noOfPeople;
        } else if (roomSize.equalsIgnoreCase("Double")) {
            price = DOUBLE * noDays * noOfPeople;
        } else if (roomSize.equalsIgnoreCase("Grand_Deluxe")) {
            price = GRAND_DELUXE * noDays * noOfPeople;
        }

        return price;
    }

    public double calcDisAmt() {

        final double DISC_15 = 0.15;
        final double DISC_7_5 = 0.075;
        final double DISC_01 = 0.1;

        double disAmt = 0;
        double price = calcPrice();

        if (noDays > 14) {
            disAmt = DISC_15 * price;
        } else if (noDays >= 10 && noDays <= 14) {
            disAmt = DISC_7_5 * price;
        } else if (noDays >= 7 && noDays < 10) {
            disAmt = DISC_01 * price;
        }

        return disAmt;
    }

    public double calcTotalPayment() {

        double disAmt = calcDisAmt();
        double price = calcPrice();

        double totalPrice = 0;

        totalPrice = price - disAmt;
        return totalPrice;
    }

    NumberFormat curr = NumberFormat.getCurrencyInstance();

    @Override
    public String toString() {

        return IDnr + "\t" + name + "\t" + roomSize + "\t" + noOfPeople + "\t" + noDays
                + "\t" + curr.format(calcPrice()) + "\t" + curr.format(calcDisAmt()) + "\t" + curr.format(calcTotalPayment()) + "\n";
    }

    public static void intilize() throws DataStorageException {

        BookingDA.intilize();
    }

    public static void terminate() throws DataStorageException {

        BookingDA.terminate();
    }

    public void addNew(Booking obj) throws DuplicateException {

        BookingDA.addNew(obj);
    }

    public static ArrayList<Booking> getAll() {

        return BookingDA.getAll();
    }

    public static Booking searchClient(String name) throws NotFoundException {

        return BookingDA.search(name);
    }

    public static void countBooking() {

        BookingDA.countRoomType();
    }

    public static void delete(String name) throws NotFoundException {

        BookingDA.delete(name);

    }
}
