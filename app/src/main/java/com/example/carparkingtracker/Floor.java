package com.example.carparkingtracker;

import java.io.Serializable;
import java.util.ArrayList;

//class that organizes and creates all of the parking rows by creating another arraylist of the parking rows
public class Floor implements Serializable {
    private int floorNumber;
    private ArrayList<parkingRow> parkingRows;
    private parkingRow D;
    private parkingRow A;
    private parkingRow C;
    private parkingRow B;
    private parkingRow E;
    private parkingRow F;
    private parkingRow G;
    private parkingRow H;
    private parkingRow I;
    public Floor(int floorNumber)
    {
        this.floorNumber = floorNumber;
        A = new parkingRow(19,30,150,30 + 120,150 + 60);
        A.calculateVerticalCoordinates();
        B = new parkingRow(13,150,30,150 + 60,30 + 120);
        B.calculateHorizontalCoordinates();
        E = new parkingRow(19,1080 - 30 - 120,150,1080 - 30,150 + 60);
        E.calculateVerticalCoordinates();
        C = new parkingRow(9,270,400 - 120,270 + 60,400);
        C.calculateHorizontalCoordinates();
        D = new parkingRow(9,270,430 ,270 + 60,430 + 120);
        D.calculateHorizontalCoordinates();
        F = new parkingRow(9,270,800 - 120 ,270 + 60,800);
        F.calculateHorizontalCoordinates();
        G = new parkingRow(9,280,830,280 + 60,830 + 120);
        G.calculateHorizontalCoordinates();
        H = new parkingRow(9,280,1444 - 250 - 120,280 + 60,1444 - 250);
        H.calculateHorizontalCoordinates();
        I = new parkingRow(13,150,1444 - 120 - 30,150 + 60,1444 - 30);
        I.calculateHorizontalCoordinates();
        parkingRows = new ArrayList<parkingRow>();
        parkingRows.add(A);
        parkingRows.add(B);
        parkingRows.add(E);
        parkingRows.add(C);
        parkingRows.add(D);
        parkingRows.add(F);
        parkingRows.add(G);
        parkingRows.add(H);
        parkingRows.add(I);
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorNumber=" + floorNumber +
                ", parkingRows=" + parkingRows +
                ", D=" + D +
                ", A=" + A +
                ", C=" + C +
                ", B=" + B +
                ", E=" + E +
                ", F=" + F +
                ", G=" + G +
                ", H=" + H +
                ", I=" + I +
                '}';
    }

    public ArrayList<parkingRow> getParkingRows() {
        return parkingRows;
    }
    public int getFloorNumber()
    {
        return floorNumber;
    }
}
