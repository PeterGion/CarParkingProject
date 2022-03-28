package com.example.carparkingtracker;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.carparkingtracker.ParkingView;

import java.util.ArrayList;

public class firstFloorFragement extends Fragment implements GestureDetector.OnGestureListener{
    private ParkingView parkingView;
    //private Floor firstFloor;
    //private GestureDetector gestureDetector;
    private static final String DESCRIBABLE_KEY = "describable_key";
    private Floor floor;


    //private Bitmap shakespearFirstFloor;
    //private ZoomInImageViewAttacher;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shakespeare_first_floor_fragment, container, false);
        super.onCreate(savedInstanceState);
        //backgroundPic = (ImageView) view.findViewById(R.id.backgroundPic);
        //ZoomInImageViewAttacher mIvAttacter = new ZoomInImageViewAttacher();
        //mIvAttacter.attachImageView(backgroundPic);
        //gestureDetector = new GestureDetector(getActivity(), new GestureListener());
        //shakespearFirstFloor = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.newer);
        //DisplayMetrics displayMetrics = new DisplayMetrics();
        //getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.FloorBackground);
        //firstFloor = new Floor(1);
        //parkingView = new ParkingView(getActivity(), firstFloor.getParkingRows(), firstFloor.getFloorNumber());
        floor = (Floor) getArguments().getSerializable(DESCRIBABLE_KEY);
        parkingView = new ParkingView(getContext(), floor.getParkingRows(), floor.getFloorNumber());
        //Log.d("toString", mDescribable.toString());
                /*
                shakespearFirstFloor,
                                                this doesn't take into account the navigation bar's height that's why the - 200 is there
                displayMetrics.widthPixels, displayMetrics.heightPixels - 350);
                 */
        //shakespearFirstFloor.getWidth(),shakespearFirstFloor.getHeight());
        //relativeLayout.setOnTouchListener(touchListener);
        relativeLayout.addView(parkingView);
        //ImageView.addView(new ParkingView(getActivity()));
        //((ImageView) view.findViewById(R.id.backgroundPic)).setImageDrawable(new ParkingView(shakespearFirstFloor));
        //shakespearFirstFloor = new Picture(shakespearFirstFloor);
        //parkingView = new ParkingView(this);
        //setContentView(parkingView);
        Log.d("fragment one", "onCreateView: ");
        return view;
    }

    public static firstFloorFragement newInstance (Floor firstFloor)
    {
        firstFloorFragement fragment = new firstFloorFragement();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, firstFloor);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d("TAG", "onFling: ");
        return false;
    }
}
