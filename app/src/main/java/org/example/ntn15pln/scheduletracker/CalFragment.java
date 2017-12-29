package org.example.ntn15pln.scheduletracker;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalFragment extends Fragment {
    //Temp button
    private Button goToMap;

    private int week, month, year;
    private View rootView;
    private ImageButton incWeek, decWeek;
    private TextView currentWeek, currentYear, monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private Calendar calendar;
    private Date date1 = new Date();
    private LocalDate date;
    private DateTime dt;
    private List<TextView> days = new ArrayList<TextView>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cal, container, false);
        calendar = Calendar.getInstance();
        week = calendar.get(Calendar.WEEK_OF_YEAR);

        setViews();
        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                getActivity().startActivity(intent);
            }
        });
        addDaysToList();
        currentWeek.setText(String.valueOf(week));
        year = calendar.get(Calendar.YEAR);
        currentYear.setText(String.valueOf(year));
        setDatesOfWeek();
        setListeners();
        return rootView;
    }

    public void setViews() {
        incWeek = (ImageButton)rootView.findViewById(R.id.increment_week);
        decWeek = (ImageButton) rootView.findViewById(R.id.decrement_week);
        currentWeek = (TextView) rootView.findViewById(R.id.text_week);
        currentYear = (TextView) rootView.findViewById(R.id.current_year);
        monday = (TextView) rootView.findViewById(R.id.cal_day_monday);
        tuesday = (TextView) rootView.findViewById(R.id.cal_day_tuesday);
        wednesday = (TextView) rootView.findViewById(R.id.cal_day_wednesday);
        thursday = (TextView) rootView.findViewById(R.id.cal_day_thursday);
        friday = (TextView) rootView.findViewById(R.id.cal_day_friday);
        saturday = (TextView) rootView.findViewById(R.id.cal_day_saturday);
        sunday = (TextView) rootView.findViewById(R.id.cal_day_sunday);
        goToMap = (Button) rootView.findViewById(R.id.go_to_map);

    }

    public void addDaysToList() {
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);
        days.add(thursday);
        days.add(friday);
        days.add(saturday);
        days.add(sunday);

    }

    public void setYear() {

    }

    public void setDatesOfWeek() {
        for(int i = 1; i < 8; i++) {
            dt = new DateTime().withYear(year).withWeekOfWeekyear(week).withDayOfWeek(i);
            days.get(i-1).setText(dt.dayOfMonth().getAsText());
        }
    }

    public void setListeners() {


        incWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(week < 52) {
                    week++;
                }
                else if(week == 52) {
                    week = 1;
                    year++;
                }

                currentYear.setText(String.valueOf(year));
                currentWeek.setText(String.valueOf(week));
                setDatesOfWeek();
            }
        });

        decWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(week > 1) week--;
                else if(week == 1) {
                    week = 52;
                    year--;
                }

                currentYear.setText(String.valueOf(year));
                currentWeek.setText(String.valueOf(week));
                setDatesOfWeek();
            }
        });


    }
}
