package org.example.ntn15pln.scheduletracker;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jsibbold.zoomage.ZoomageView;

import org.json.JSONException;

import java.io.IOException;


public class MapActivity extends AppCompatActivity {
    private int xPos, yPos, phoneHeight, phoneWidth;
    private ZoomageView zoom;
    float x;
    float y;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setMarkerPos(0.185f, 0.175f);
        createMap();
    }

    public void createMap() {
        //Fixa så att mappen scalear beroende på telefonens screen size.
        zoom = (ZoomageView) findViewById(R.id.zoom);
        Drawable map = (Drawable) getResources().getDrawable(R.drawable.map_placeholder);
        Drawable marker = (Drawable) getResources().getDrawable(R.drawable.map_marker);
        phoneHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        phoneWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        Drawable[] layers = {map, marker};
        LayerDrawable layersTest = new LayerDrawable(layers);
        layersTest.setLayerWidth(1, 30);
        layersTest.setLayerHeight(1, 35);
        layersTest.setLayerInsetLeft(1, getX());
        layersTest.setLayerInsetTop(1, getY());
        zoom.setImageDrawable(layersTest);
        zoom.setScaleRange(1, 8);
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
