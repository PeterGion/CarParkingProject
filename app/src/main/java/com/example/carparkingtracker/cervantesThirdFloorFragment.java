package com.example.carparkingtracker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class cervantesThirdFloorFragment extends Fragment {
    private static final String DESCRIBABLEKEY = "cThirdFloorKey";
    private ParkingView parkingView;
    private Floor floor;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cervantes_third_floor_fragment, container, false);
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.FloorBackground);
        floor = (Floor) getArguments().getSerializable(DESCRIBABLEKEY);
        parkingView = new ParkingView(getContext(), floor.getParkingRows(), floor.getFloorNumber());
        relativeLayout.addView(parkingView);
        return view;
    }
    public static cervantesThirdFloorFragment newInstance(Floor thirdFloor)
    {
        cervantesThirdFloorFragment fragment = new cervantesThirdFloorFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLEKEY, thirdFloor);
        fragment.setArguments(bundle);
        return fragment;
    }
}
