package org.example.ntn15pln.findmyclass;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.jsibbold.zoomage.ZoomageView;

/**
 * Denna klass skapar och hanterar själva kartan och markören.
 * ZoomageView är ett library för att få zoomfunktion på kartan. Det används via https://jsibbold.github.io/zoomage/
 */

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

    /**
     * ZoomageView är ett library som används för zoomen.
     * Diverse lager skapas för kartan och en fast storlek sätts på kartan och markören, för att positionerna ska stämma överens på alla / de flesta mobiler.
     */
    public void createMap() {
        zoom = (ZoomageView) findViewById(R.id.zoom);
        phoneHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        phoneWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        Drawable map = (Drawable) getResources().getDrawable(R.drawable.map);
        Drawable marker = (Drawable) getResources().getDrawable(R.drawable.map_marker);

        Drawable[] layers = {map, marker};
        LayerDrawable layer = new LayerDrawable(layers);

        layer.setLayerSize(0, 1000, 650);
        layer.setLayerSize(1, 30, 35);
        layer.setLayerInsetLeft(1, xPos);
        layer.setLayerInsetTop(1, yPos);

        zoom.setImageDrawable(layer);
        zoom.setScaleRange(.7f, 8);
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
