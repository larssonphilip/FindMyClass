package org.example.ntn15pln.scheduletracker.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import org.example.ntn15pln.scheduletracker.Controllers.InfoHandler;
import org.example.ntn15pln.scheduletracker.MapActivity;
import org.example.ntn15pln.scheduletracker.Controllers.MarkerPositionHandler;
import org.example.ntn15pln.scheduletracker.R;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    //private TextView startTime, stopTime, courseName, roomNr, teacherSignature;
    private ListView scheduleList;
    private MarkerPositionHandler mph;
    private MyAdapter adapter;
    private ArrayList<InfoHandler> list;
    private CalendarView mCalendarView;
    private static String chosenDate;
    private View viewtest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_schedule, container, false);

        initItems();

        scheduleList = (ListView) mView.findViewById(R.id.schedule_list);
        mCalendarView = (CalendarView) mView.findViewById(R.id.calendarView);

        setAdapter();

        setListeners();

        return mView;
    }

    public void setList(ArrayList<InfoHandler> infoList) {
        list.clear();
        this.list = infoList;
    }

    public void initItems() {
        list = new ArrayList<>();
        mph = new MarkerPositionHandler();
    }

    public void setAdapter() {
        adapter = new MyAdapter();
    }

    //Fixa så att enbart den valda dagens lektioner visas.
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder viewHolder;
            if(view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = inflater.inflate(R.layout.schedule_list_style, parent, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.startTime.setText(list.get(position).getStartTime());
            viewHolder.stopTime.setText(list.get(position).getStopTime());
            //Ändra till .getCourseName() när det är fixat.
            viewHolder.courseName.setText(list.get(position).getCourseCode());
            viewHolder.roomNr.setText(list.get(position).getRoomNr());
            viewHolder.teacherSignature.setText(list.get(position).getTeacherSignature());
            return view;
        }

        private class ViewHolder {
            TextView startTime, stopTime, courseName, roomNr, teacherSignature;

            public ViewHolder(View view) {
                startTime = (TextView) view.findViewById(R.id.start_time);
                stopTime = (TextView) view.findViewById(R.id.stop_time);
                courseName = (TextView) view.findViewById(R.id.course_name);
                roomNr = (TextView) view.findViewById(R.id.room);
                teacherSignature = (TextView) view.findViewById(R.id.teacher);
            }
        }
    }

    private void setListeners() {
        scheduleList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mph.setMarker(list.get(position).getRoomNr());
                MapActivity.setMarkerPos(mph.getX(), mph.getY());
                Intent intent = new Intent(getActivity(), MapActivity.class);
                getActivity().startActivity(intent);
            }
        };

        scheduleList.setOnItemClickListener(clickListener);


        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Uppdatera schema under kalender efter vilken dag som är vald.
                chosenDate = "" + year + month + dayOfMonth;
                scheduleList.invalidateViews();

            }
        });
    }

    public static String getChosenDate() {
        return chosenDate;
    }
}
