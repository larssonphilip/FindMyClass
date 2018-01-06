package org.example.ntn15pln.scheduletracker;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jsibbold.zoomage.ZoomageView;


public class MapActivity extends AppCompatActivity {
    private static int xPos, yPos;
    private int phoneHeight, phoneWidth;
    private ZoomageView zoom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        createMap();
    }

    public void createMap() {
        //Fixa så att mappen scalear beroende på telefonens screen size.
        zoom = (ZoomageView) findViewById(R.id.zoom);
        phoneHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        phoneWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        Drawable map = (Drawable) getResources().getDrawable(R.drawable.map);
        Drawable marker = (Drawable) getResources().getDrawable(R.drawable.map_marker);

        Drawable[] layers = {map, marker};
        LayerDrawable layer = new LayerDrawable(layers);

        layer.setLayerSize(0, 1000, 650);
        layer.setLayerSize(1, 30, 35);
        layer.setLayerInsetLeft(1, 379);
        layer.setLayerInsetTop(1, 513);

        zoom.setImageDrawable(layer);
        zoom.setScaleRange(.5f, 8);
        zoom.setMinimumWidth(phoneWidth);
        zoom.setMaxWidth(phoneWidth);
        zoom.setMinimumHeight(phoneHeight);
        zoom.setMaxHeight(phoneHeight);

    }

    public static void setMarkerPos(int x, int y) {
        xPos = x;
        yPos = y;
    }
}
