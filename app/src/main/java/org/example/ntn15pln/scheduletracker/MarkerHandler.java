package org.example.ntn15pln.scheduletracker;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MarkerHandler extends Fragment{
    private int xPos, yPos;
    private Drawable marker;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_map, container, false);
        setViews();
        return rootView;
    }

    public void setViews() {
        marker = (Drawable) getActivity().getResources().getDrawable(R.drawable.map_marker);
    }


}
