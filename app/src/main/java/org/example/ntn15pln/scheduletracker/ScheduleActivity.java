package org.example.ntn15pln.scheduletracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.example.ntn15pln.scheduletracker.Controllers.ICalParser;
import org.example.ntn15pln.scheduletracker.Fragments.CalendarMonthFragment;
import org.example.ntn15pln.scheduletracker.Fragments.ScheduleFragment;

public class ScheduleActivity extends Activity {
    CalendarMonthFragment calendar;
    ScheduleFragment schedule;
    ICalParser kp = new ICalParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        kp.parseICS();
        schedule = (ScheduleFragment) getFragmentManager().findFragmentById(R.id.schedule);
        schedule.setList(kp.getInfoList());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
