package org.example.ntn15pln.scheduletracker;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jsibbold.zoomage.ZoomageView;


public class MapActivity extends AppCompatActivity {
    private int xPos, yPos, phoneHeight, phoneWidth;
    private ZoomageView zoom;
    float x;
    float y;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setMarkerPos(0.279f, 0.245f);
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

        layer.setLayerSize(1, 30, 35);
        layer.setLayerInsetLeft(1, getX());
        layer.setLayerInsetTop(1, getY());

        zoom.setImageDrawable(layer);
        zoom.setScaleRange(.5f, 8);
        zoom.setMinimumWidth(phoneWidth);
        zoom.setMaxWidth(phoneWidth);
        zoom.setMinimumHeight(phoneHeight);
        zoom.setMaxHeight(phoneHeight);

    }

    public void setMarkerPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) (phoneHeight * x);
    }

    public int getY() {
        return (int) (phoneHeight * y);
    }
}
