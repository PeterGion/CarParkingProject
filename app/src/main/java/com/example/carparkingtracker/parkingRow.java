package com.example.carparkingtracker;

import android.graphics.Canvas;

import java.util.ArrayList;

//this class is essentially an arraylist of Spots
public class parkingRow {
    private int length;
    private ArrayList<Spot>parkingRow;
    //coordinates of initial rectangle so you can calculate the coordinates of the others
    private int x1Coordinate, y1Coordinate,x2Coordinate,y2Coordinate;
    //creates an arraylist of parking spots with a desired length
    //the coordinates are set based on the coordinates of the first parking spot
    public parkingRow(int length,int x1Coordinate,int y1Coordinate,int x2Coordinate,int y2Coordinate)
    {
        this.x1Coordinate = x1Coordinate;
        this.y1Coordinate = y1Coordinate;
        this.x2Coordinate = x2Coordinate;
        this.y2Coordinate = y2Coordinate;
        this.length = length;
        parkingRow = new ArrayList<Spot>();
        //populates the arraylist of parking spots
        for(int parkingSpotCount = 0; parkingSpotCount < length; parkingSpotCount++)
        {
            //flips a coin weather or not the spotStatus is going to be true or false
            if(Math.random() < .5)
                parkingRow.add(new Spot(true));
            else
                parkingRow.add(new Spot(false));
        }

    }

    //calculates the coordinates of the parking spots if they are going horizontally
    public void calculateHorizontalCoordinates()
    {
        parkingRow.get(0).setSpotCoordinates(x1Coordinate, y1Coordinate, x2Coordinate, y2Coordinate);
        for(int parkingSpotCount = 1; parkingSpotCount < length; parkingSpotCount++)
        {
            parkingRow.get(parkingSpotCount)
                    .setSpotCoordinates(x1Coordinate + (60 * parkingSpotCount),
                    y1Coordinate, x2Coordinate + (60 * parkingSpotCount),
                    y2Coordinate);
        }
    }
    //calculates the coordinates of the parking spots if they are going vertically
    public void calculateVerticalCoordinates()
    {
        int secondXCoordMultiplyer;
        parkingRow.get(0).setSpotCoordinates(x1Coordinate, y1Coordinate, x2Coordinate, y2Coordinate);
        for(int parkingSpotCount = 1; parkingSpotCount < length; parkingSpotCount++)
        {
            parkingRow.get(parkingSpotCount)
                    .setSpotCoordinates(x1Coordinate,
                            y1Coordinate + (60 * parkingSpotCount), x2Coordinate,
                            y2Coordinate + (60 * parkingSpotCount));
        }
    }
    public boolean getSpotStatus(int index)
    {
        return (parkingRow.get(index)).getSpotSatus();
    }
    public int getLength()
    {
        return length;
    }
    public int getCurrx1(int index)
    {
        return parkingRow.get(index).getX1Coordinate();
    }
    public int getCurry1(int index)
    {
        return parkingRow.get(index).getY1Coordinate();
    }
    public int getCurrx2(int index)
    {
        return parkingRow.get(index).getX2Coordinate();
    }
    public int getCurry2(int index)
    {
        return parkingRow.get(index).getY2Coordinate();
    }
    public String listElements()
    {
        return parkingRow + "";
    }

    @Override
    public String toString() {
        return "parkingRow{" +
                "length=" + length +
                ", parkingRow=" + parkingRow +
                ", x1Coordinate=" + x1Coordinate +
                ", y1Coordinate=" + y1Coordinate +
                ", x2Coordinate=" + x2Coordinate +
                ", y2Coordinate=" + y2Coordinate +
                '}';
    }
}
