package org.example.ntn15pln.scheduletracker;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class KronoxParser {
    private static ArrayList<InfoHandler> infos = new ArrayList<InfoHandler>();
    private boolean first = true;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    private InfoHandler infoHandler;
    private BufferedReader br;
    private FileInputStream fis;
    private File file;
    public static String control;

    public KronoxParser() {
        parseICS();
    }
    /* FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);*/
    public void parseICS(){

        infoHandler = new InfoHandler();
        try {
            //file = new File(path);
            fis = new FileInputStream(new File(path, "SC1444.ics"));
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            int x = 0;
            String s;

            while((s = br.readLine()) != null) {
                String[] holder = s.split(" ");
                if(s.contains("DTSTART:")) infoHandler.setStart(s.substring(s.lastIndexOf(":") + 1));
                if(s.contains("DTEND:")) infoHandler.setStop(s.substring(s.lastIndexOf(":") + 1));

                if(holder[0].equals("SUMMARY:Program:")){
                    infoHandler.setProgramCode(holder[1]);
                    for(int i = 2; i < holder.length; i++) {
                        switch(holder[i]) {
                            case "Kurs.grp:":
                                infoHandler.setCourseCode(holder[i+1]);
                            case "Sign:":
                                infoHandler.setTeacherSignature(holder[i+1]);
                                if(!holder[i+2].equals("Moment:")) infoHandler.setSecondTeacherSignature(holder[i+2]);
                                else infoHandler.setSecondTeacherSignature("");
                            case "Moment:":
                                String info = "";
                                for(int c = i+1; c < holder.length; c++) {
                                    if(!holder[c].equals("Aktivitetstyp:")) info += holder[c] + " ";
                                    if(holder[c].equals("Aktivitetstyp:")) c = holder.length;
                                }
                                infoHandler.setLectureInfo(info);
                        }
                    }
                }
                if(holder[0].equals("LOCATION:")) infoHandler.setRoomNr(holder[1]);
                if(first) {

                    control = infoHandler.getCourseCode();
                    first = false;
                }
                infos.add(infoHandler);
            }

        } catch(IOException e) {

        }
    }

    public String getPath() {
        return path;
    }

    public ArrayList<InfoHandler> getInfoList(){
        return infos;
    }
}
