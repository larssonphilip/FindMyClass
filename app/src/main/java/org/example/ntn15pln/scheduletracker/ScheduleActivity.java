package org.example.ntn15pln.scheduletracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

public class ScheduleActivity extends Activity {
    MarkerPositionHandler mph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        mph = new MarkerPositionHandler();
    }
}
