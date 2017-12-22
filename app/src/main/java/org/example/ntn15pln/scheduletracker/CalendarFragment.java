package org.example.ntn15pln.scheduletracker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.Calendar;

public class CalendarFragment extends Fragment {
    private CalendarView mCalendarView;
    private static String chosenDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_cal, container, false);
        mCalendarView = (CalendarView) mView.findViewById(R.id.calendarView);

        return mView;
    }

    private void setListeners() {
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Uppdatera schema under kalender efter vilken dag som är vald.
                chosenDate = "" + year + month + dayOfMonth;
            }
        });
    }



    public static String getChosenDate() {
        return chosenDate;
    }
}
