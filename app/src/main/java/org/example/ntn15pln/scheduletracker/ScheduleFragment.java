package org.example.ntn15pln.scheduletracker;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    TextView startTime, stopTime, courseName, roomNr, teacherSignature;
    KronoxParser kp;
    private static ArrayList<InfoHandler> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_schedule, container, false);
        kp = new KronoxParser();

        int x = kp.getInfoList().size();
        //kp.parseICS();
        list = kp.getInfoList();

        ListView scheduleList = (ListView) mView.findViewById(R.id.schedule_list);
        MyAdapter adapter = new MyAdapter();
        scheduleList.setAdapter(adapter);

        return mView;
    }

    class MyAdapter extends BaseAdapter {
        public class ViewHolder{
            TextView startTime, stopTime, courseName, roomNr, teacherSignature;
        }
        @Override
        public int getCount() {
            return 8;
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
            //ViewHolder holder = new ViewHolder();
            if(view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = inflater.inflate(R.layout.list_style, parent, false);
            }

            startTime = (TextView) view.findViewById(R.id.start_time);
            stopTime = (TextView) view.findViewById(R.id.stop_time);
            courseName = (TextView) view.findViewById(R.id.course_name);
            roomNr = (TextView) view.findViewById(R.id.room);
            teacherSignature = (TextView) view.findViewById(R.id.teacher);

            startTime.setText("Start");
            stopTime.setText("Stop");
            //Ändra till .getCourseName() när det är fixat.
            courseName.setText("Course");
            roomNr.setText("Room");
            teacherSignature.setText("Teacher");

            /*startTime.setText(list.get(position).getStart());
            stopTime.setText(list.get(position).getStop());
            //Ändra till .getCourseName() när det är fixat.
            courseName.setText(list.get(position).getCourseCode());
            roomNr.setText(list.get(position).getRoomNr());
            teacherSignature.setText(list.get(position).getTeacherSignature());*/
            return view;
        }
    }

}
