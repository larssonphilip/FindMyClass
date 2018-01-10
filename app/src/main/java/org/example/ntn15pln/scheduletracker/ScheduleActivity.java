package org.example.ntn15pln.scheduletracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.example.ntn15pln.scheduletracker.Controllers.ICalParser;
import org.example.ntn15pln.scheduletracker.Fragments.ScheduleFragment;

public class ScheduleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

    }
}
