package com.example.carparkingtracker;

public class Spot{
    private boolean SpotStatus;// this is if the spot is taken or not
    private int x1Coordinate, y1Coordinate,x2Coordinate,y2Coordinate; //these are the coordinates for the spot
    //creates a spot with a spot status and no coordinates
    public Spot(boolean spotStatus)
    {
        this.SpotStatus = spotStatus;
    }

    //this is where you can set the coordinates for the spot
    public void setSpotCoordinates(int x1Coordinate,int y1Coordinate,int x2Coordinate,int y2Coordinate) {
        this.x1Coordinate = x1Coordinate;
        this.y1Coordinate = y1Coordinate;
        this.x2Coordinate = x2Coordinate;
        this.y2Coordinate = y2Coordinate;
    }

    public boolean getSpotSatus()
    {
        return SpotStatus;
    }
    public int getX1Coordinate()
    {
        return x1Coordinate;
    }
    public int getY1Coordinate()
    {
        return y1Coordinate;
    }
    public int getX2Coordinate()
    {
        return x2Coordinate;
    }
    public int getY2Coordinate()
    {
        return y2Coordinate;
    }
    @Override
    public String toString() {
        return "Spot{" +
                "SpotStatus=" + SpotStatus +
                ", x1Coordinate=" + x1Coordinate +
                ", y1Coordinate=" + y1Coordinate +
                ", x2Coordinate=" + x2Coordinate +
                ", y2Coordinate=" + y2Coordinate +
                '}';
    }
}
