package org.example.ntn15pln.scheduletracker.Controllers;

public class InfoHandler{
    private String courseName, courseCode, programCode, lectureInfo, start, stop, startDate, startTime, stopTime, roomNr, teacherSignature, secondTeacherSignature;


    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setProgramCode(String programCode) { this.programCode = programCode; }
    public void setLectureInfo(String lectureInfo) { this.lectureInfo = lectureInfo; }
    public void setRoomNr(String roomNr) { this.roomNr = roomNr; }
    public void setSecondTeacherSignature(String secondTeacherSignature) { this.secondTeacherSignature = secondTeacherSignature; }
    public void setTeacherSignature(String teacherSignature) { this.teacherSignature = teacherSignature; }
    public void setDate() { startDate = start.substring(0, start.lastIndexOf("T")); }

    public void setStart(String start) {
        this.start = start;
        setDate();
        setStartTime();
    }

    public void setStop(String stop) {
        this.stop = stop;
        setStopTime();
    }

    /**
     * Tar de relevanta delarna ifrån "DTSTART"-biten i schemat och
     * lägger till ett kolon för att separera timme och minut.
     */
    public void setStartTime() {
        int firstPart = Integer.parseInt(start.substring(start.lastIndexOf("T") + 1, start.lastIndexOf("Z")-4)) + 1;
        startTime = firstPart + ":" + start.substring(start.lastIndexOf("T") + 3, start.lastIndexOf("Z")-2);
    }

    /**
     * Tar de relevanta delarna ifrån "DTSTOP"-biten i schemat och
     * lägger till ett kolon för att separera timme och minut.
     */
    public void setStopTime() {
        int firstPart = Integer.parseInt(stop.substring(stop.lastIndexOf("T") + 1, stop.lastIndexOf("Z")-4)) + 1;
        stopTime = firstPart + ":" + stop.substring(stop.lastIndexOf("T") + 3, stop.lastIndexOf("Z")-2);
    }

    public String getDate() { return startDate; }
    public String getStartTime() { return startTime; }
    public String getStopTime() { return stopTime; }
    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public String getProgramCode() { return programCode; }
    public String getLectureInfo() { return lectureInfo; }
    public String getStart() { return start; }
    public String getStop() { return stop; }
    public String getRoomNr() { return roomNr; }
    public String getTeacherSignature() { return teacherSignature + " " + secondTeacherSignature; }

}
