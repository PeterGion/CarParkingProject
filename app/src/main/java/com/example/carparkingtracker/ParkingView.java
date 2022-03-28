package com.example.carparkingtracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;

import java.io.Serializable;
import java.util.ArrayList;

//https://www.youtube.com/watch?v=XXvupm4SNWA
//https://www.youtube.com/watch?v=66B2tc2kuow&t=301s
//scrollable bitmap https://developer.android.com/reference/android/graphics/Canvas#drawBitmap(android.graphics.Bitmap,%20float,%20float,%20android.graphics.Paint)
//gesture detector code https://stackoverflow.com/questions/45054908/how-to-add-a-gesture-detector-to-a-view-in-android
public class ParkingView extends View implements Serializable {
    private Paint paint;
    ArrayList<parkingRow> parkingRows;
    int floorNumber;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    //old code, I was going to make a bitmap that you could scroll on but was changed due to time constraints
    /*
    private ScaleGestureDetector scaleDetector;
    private Bitmap floorPicture;
    private float scaleFactor;
    private float mPositionX,mPositionY;
    private float refX, refY;
    private Rect displayRect = null;
    private Rect scrollRect = null;
    private int scrollRectX = 0;
    private int scrollRectY = 0;
    private float scrollByX = 0;
    private float scrollByY = 0;
    private int width, height;
    private GestureDetector gestureDetector;
     */
    public ParkingView(Context context, ArrayList<parkingRow> parkingRows, int floorNumber) {
        super(context);
        paint = new Paint();
        this.parkingRows = parkingRows;
        this.floorNumber = floorNumber;
        //old code, I was going to make a bitmap that you could scroll on but was changed due to time constraints
        /*
        scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        this.floorPicture = floorPicture;
        scaleFactor = 1f;
        this.width = width;
        this.height = height;
        displayRect = new Rect(0, 0, width, height);
        scrollRect = new Rect(0, 0, width, height);
        gestureDetector = new GestureDetector(context, this);
        setOnTouchListener(touchListener);
        //gestureDetector = new GestureDetector(context, this);
        //gestureDetector.setOnDoubleTapListener(this);
        //paint.setStrokeWidth(2);
    }
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // pass the events to the gesture detector
            // a return value of true means the detector is handling it
            // a return value of false means the detector didn't
            // recognize the event
            return gestureDetector.onTouchEvent(event);

        }
    };
    public ParkingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ParkingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context ctx) {
        image = getResources().getDrawable(R.drawable.newer);
        image.setBounds(0, 0, image.getIntrinsicWidth(),
                image.getIntrinsicHeight());
        scaleDetector = new ScaleGestureDetector(ctx, new ScaleListener());
    */
    }
    public void onDraw(Canvas canvas)
    {
        //x1 and y1 are the top left corner x2 and y2 are the bottom right corner
        //canvas.drawRect(x1, y1 ,x2 , y2,paint);
        paint.setColor(Color.BLACK);
        //creating the outline
        canvas.drawRect(0, 0, canvas.getWidth(), 30,paint);
        canvas.drawRect(0, 0,30 , canvas.getHeight(),paint);
        canvas.drawRect(0, canvas.getHeight() - 30, canvas.getWidth(), canvas.getHeight(),paint);
        canvas.drawRect(canvas.getWidth() - 30, 0 ,canvas.getWidth() , canvas.getHeight(),paint);
        //the bar dividing the top two parking rows
        canvas.drawRect(250,400,canvas.getWidth() - 250, 400 + 30, paint);
        //the C at the bottom
        canvas.drawRect(250,800,canvas.getWidth() - 250,800 + 30,paint);
        canvas.drawRect(250,800,250 + 30,canvas.getHeight() - 250, paint);
        canvas.drawRect(250,canvas.getHeight() - 250,canvas.getWidth() - 250,canvas.getHeight() - 250 + 30, paint);
        drawParkingRows(canvas);
        //this creates the text for the row labels on the canvas
        paint.setTextSize(50);
        paint.setColor(Color.YELLOW);
        canvas.drawText("A" + floorNumber,0,canvas.getHeight() / 2, paint);
        canvas.drawText("B" + floorNumber + "↓",canvas.getWidth()/2,40, paint);
        canvas.drawText("C" + floorNumber + "↑",(250 + canvas.getWidth()) / 3,430, paint);
        canvas.drawText("D" + floorNumber + "↓",(canvas.getWidth() - 250 + 100) / 1.5f,430, paint);
        canvas.drawText("E" + floorNumber,canvas.getWidth() - 50,canvas.getHeight() / 2, paint);
        canvas.drawText("F" + floorNumber + "↑",(250 + canvas.getWidth()) / 3,830, paint);
        canvas.drawText("G" + floorNumber + "↓",(canvas.getWidth() - 250 + 100) / 1.5f,830, paint);
        canvas.drawText("H" + floorNumber + "↑",canvas.getWidth() / 2,canvas.getHeight() - 250 + 30, paint);
        canvas.drawText("I" + floorNumber + "↑",canvas.getWidth() / 2,canvas.getHeight() - 20, paint);

        //this was testing code to see where the objects were drawn
        /*
        paint.setTextSize(15f);
        canvas.drawText("");
        I1 horizontal
        paint.setColor(Color.YELLOW);
        canvas.drawRect(150,1444 - 120 - 30,150 + 60,1444 - 30,paint);
        horizontal H1
        paint.setColor(Color.YELLOW);
        canvas.drawRect(280,1444 - 250 - 120,280 + 60,1444 - 250,paint);
        Log.d("TAG", canvas.getHeight()+ "") = 1444;
        Horizontal G1
        paint.setColor(Color.YELLOW);
        canvas.drawRect(280,830,270 + 60,830 + 120,paint);
        Horizontal F1
        paint.setColor(Color.YELLOW);
        canvas.drawRect(270,800 - 120 ,270 + 60,800,paint);
        Horizontal E1
        paint.setColor(Color.YELLOW);
        canvas.drawRect(270,430 ,270 + 60,430+ 120,paint);
        Horizontal B1
        paint.setColor(Color.YELLOW);
        canvas.drawRect(270,400 - 120,270 + 60,400,paint);
        Vertical C1
        paint.setColor(Color.YELLOW);
        canvas.drawRect(1080 - 30 - 120,150,1080 - 30,150 + 60,paint);
        Log.d("TAG", canvas.getWidth() + "") = 1080;
        Horizontal D1
        paint.setColor(Color.GREEN);
        canvas.drawRect(120,30,120 + 60,30 + 120,paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(120 + 60,30,120 + (60 * 2),30 + 120,paint);
                        180     ,30,240,          ,
        Vertical A1
        paint.setColor(Color.YELLOW);
        //120,30,120 + 60,30 + 120
        canvas.drawRect(30,150,30 + 120,150 + 60,paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(30,150 + 60,30 + 120,150 +  (60 * 2),paint);
        paint.setColor(Color.BLACK);

        */
        //old code, I was going to make a bitmap that you could scroll on but was changed due to time constraints
        /*
        canvas.save();

        //canvas.translate(mPositionX,mPositionY);
        canvas.scale(scaleFactor,scaleFactor);
        canvas.drawBitmap(floorPicture,0,0,null);
        canvas.drawRect(20, 56, 200, 112,paint);
        canvas.restore();

        if (scrollByX != 0 || scrollByY != 0) {
            // Our move updates are calculated in ACTION_MOVE in the opposite     direction
            // from how we want to move the scroll rect. Think of this as
            // dragging to
            // the left being the same as sliding the scroll rect to the right.
            int newScrollRectX = scrollRectX - (int) scrollByX;
            int newScrollRectY = scrollRectY - (int) scrollByY;
            scrollByX = 0;
            scrollByY = 0;

            // Don't scroll off the left or right edges of the bitmap.
            if (newScrollRectX < 0)
                newScrollRectX = 0;
            else if (newScrollRectX > (floorPicture.getWidth() - width))
                newScrollRectX = (floorPicture.getWidth() - width);

            // Don't scroll off the top or bottom edges of the bitmap.
            if (newScrollRectY < 0)
                newScrollRectY = 0;
            else if (newScrollRectY > (floorPicture.getHeight() - height))
                newScrollRectY = (floorPicture.getHeight() - height);
            scrollRect.set(newScrollRectX, newScrollRectY, newScrollRectX
                    + width, newScrollRectY + height);

            scrollRectX = newScrollRectX;
            scrollRectY = newScrollRectY;
        }
        //displayRect.contains(2d,2d,2d,2d);
        paint.setColor(Color.BLACK);
        //canvas.scale(0.5f,0.5f);
        canvas.drawRect(displayRect, paint);


        canvas.drawBitmap(floorPicture, scrollRect, displayRect, paint);
        canvas.save();
        canvas.drawRect(20, 56, 200, 112,paint);
        canvas.restore();
        //canvas.drawBitmap(floorPicture,);
        */
    }
    public void drawParkingRows(Canvas canvas)
    {
        //goes through each parking row in the arraylist parkingRows
        for(int parkingRowCount = 0; parkingRowCount < parkingRows.size(); parkingRowCount++)
        {
            parkingRow getCurrentParkingRow = parkingRows.get(parkingRowCount);
            int parkingRowLength = getCurrentParkingRow.getLength();
            //goes through each parking spot within each parking row and draws them on the canvas
            for (int parkingSpotCount = 0; parkingSpotCount < parkingRowLength; parkingSpotCount++) {
                int x1 = getCurrentParkingRow.getCurrx1(parkingSpotCount);
                int y1 = getCurrentParkingRow.getCurry1(parkingSpotCount);
                int x2 = getCurrentParkingRow.getCurrx2(parkingSpotCount);
                int y2 = getCurrentParkingRow.getCurry2(parkingSpotCount);
                //changes color based on the SpotStatus
                if(getCurrentParkingRow.getSpotStatus(parkingSpotCount) == true)
                    paint.setColor(Color.GREEN);
                else
                    paint.setColor(Color.RED);
                canvas.drawRect(x1, y1, x2, y2, paint);
            }
        }

    }
    //old code, I was going to make a bitmap that you could scroll on but was changed due to time constraints
    /*
    public void notifyScroll(float distX, float distY) {
        //Log.d("notifyScroll", "notifyScroll is going off");
        scrollByX = distX; // move update x increment
        scrollByY = distY; // move update y increment
    }
    private void drawBitmap(Canvas canvas)
    {
        canvas.save();
        canvas.translate(mPositionX,mPositionY);
        canvas.scale(scaleFactor,scaleFactor);
        canvas.drawBitmap(floorPicture,0,0,null);
        canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        //scaleDetector.onTouchEvent(event);
        switch(event.getAction())
        {
            /*
            case MotionEvent.ACTION_MOVE:

                float nx = event.getX();
                float ny = event.getY();

                mPositionX += nx - refX;
                mPositionY += ny - refY;
                invalidate();
                break;


            case MotionEvent.ACTION_DOWN:
                //Log.d("action Down", "X value: " + event.getX() + " Y value: " + event.getY());


                /*
                refX = event.getX();
                refY = event.getY();

                Log.d("refVariables", "refX: " + refX);
                Log.d("refVariables", "refY: " + refY);
                Log.d("refVariables", "mPositionX: " + mPositionX);
                Log.d("refVariables", "mPositionY: " + mPositionY);




                break;
        }



        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        //Log.d("TAG", "X value: " + getX() + "Y value: " + getY());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        //Log.d("scroll", "distanceX: " + distanceX + " distanceY: " + distanceY);
        notifyScroll(-distanceX, -distanceY);
        invalidate();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        //Log.d("TAG", "onLongPress: ");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //Log.d("TAG", "onFling: ");
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        //Log.d("TAG", "onDoubleTap: ");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        //Log.d("TAG", "onDoubleTapEvent: ");
        return false;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector detector)
        {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f,Math.min(scaleFactor,5.0f));
            invalidate();
            return true;
        }

    }
    public boolean onTouchEvent(MotionEvent event)
    {
        mScaleDetector.onTouchEvent(event);
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                refX = event.getX();
                refY = event.getY();
                paint((int)((refX - mPositionX)/ mScaleFactor),(int)((refY - mPositionY)/ mScaleFactor));
                break;
        }
        return true;
    }

    public ParkingView(Bitmap floorPicture)
    {
        this.floorPicture = floorPicture;
        mScaleFactor = 1.0f;
    }



    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        drawBitmap(canvas);
        canvas.drawBitmap(floorPicture,0,0, paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(20, 56, 200, 112,paint);
    }

    private void drawBitmap(Canvas canvas)
    {
        canvas.save();
        canvas.translate(mPositionX,mPositionY);
        canvas.scale(mScaleFactor,mScaleFactor);
        canvas.drawBitmap(floorPicture,0,0,null);
        canvas.restore();
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        mScaleDetector.onTouchEvent(event);
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                refX = event.getX();
                refY = event.getY();
                paint((int)((refX - mPositionX)/ mScaleFactor),(int)((refY - mPositionY)/ mScaleFactor));
                break;
        }
        return true;
    }

    private void paint(int x, int y)
    {

    }
    public PaintView(Context)
    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

     */
}
