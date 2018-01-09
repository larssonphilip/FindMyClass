package org.example.ntn15pln.scheduletracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import org.example.ntn15pln.scheduletracker.Controllers.ICalParser;
import org.example.ntn15pln.scheduletracker.Fragments.ScheduleFragment;
import org.example.ntn15pln.scheduletracker.Fragments.CalendarMonthFragment;

public class ScheduleActivity extends Activity {
    ScheduleFragment schedule;
    CalendarMonthFragment mCalender;
    ICalParser kp = new ICalParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }


}
