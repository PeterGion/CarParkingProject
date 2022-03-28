package com.example.carparkingtracker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class thirdFloorFragment extends Fragment {
    private static final String DESCRIBABLEKEY = "thirdFloorKey";
    private ParkingView parkingView;
    private Floor floor;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shakespeare_third_floor_fragment, container, false);
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.FloorBackground);
        floor = (Floor) getArguments().getSerializable(DESCRIBABLEKEY);
        parkingView = new ParkingView(getContext(), floor.getParkingRows(), floor.getFloorNumber());
        relativeLayout.addView(parkingView);
        return view;
    }
    public static thirdFloorFragment newInstance(Floor thirdFloor)
    {
        thirdFloorFragment fragment = new thirdFloorFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLEKEY, thirdFloor);
        fragment.setArguments(bundle);
        return fragment;
    }
}
