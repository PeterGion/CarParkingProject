package com.example.carparkingtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.app.Fragment;

public class secondFloorFragment extends Fragment {
    private static final String DESCRIBABLEKEY = "SecondFloorKey";
    private ParkingView parkingView;
    private Floor floor;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shakespeare_second_floor_fragment, container, false);
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.FloorBackground);
        floor = (Floor) getArguments().getSerializable(DESCRIBABLEKEY);
        parkingView = new ParkingView(getContext(), floor.getParkingRows(), floor.getFloorNumber());
        relativeLayout.addView(parkingView);
        return view;
    }
    public static secondFloorFragment newInstance(Floor secondFloor)
    {
        secondFloorFragment fragment = new secondFloorFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLEKEY, secondFloor);
        fragment.setArguments(bundle);
        return fragment;
    }
}
