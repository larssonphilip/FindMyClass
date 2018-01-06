package org.example.ntn15pln.scheduletracker.Controllers;

public class MarkerPositionHandler {

    int x, y;

    public void setMarker(String room) {

        if(Integer.parseInt(room.substring(0, 2)) == 99) {
            x = 252;
            y = 412;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 97) {
            x = 103;
            y = 335;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 96) {
            x = 306;
            y = 265;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 93) {
            x = 545;
            y = 105;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 92) {
            x = 499;
            y = 140;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 91) {
            x = 505;
            y = 236;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 81) {
            x = 598;
            y = 220;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 74) {
            x = 812;
            y = 155;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 73) {
            x = 849;
            y = 190;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 72) {
            x = 819;
            y = 253;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 71) {
            x = 679;
            y = 188;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 61) {
            x = 748;
            y = 225;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 55) {
            x = 902;
            y = 313;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 51) {
            x = 762;
            y = 417;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 41) {
            x = 774;
            y = 578;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 33) {
            x = 705;
            y = 514;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 32) {
            x = 610;
            y = 512;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 31) {
            x = 657;
            y = 563;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 23) {
            x = 496;
            y = 430;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 22) {
            x = 495;
            y = 515;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 21) {
            x = 496;
            y = 590;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 13) {
            x = 379;
            y = 513;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 12) {
            x = 285;
            y = 513;
        }
        if(Integer.parseInt(room.substring(0, 2)) == 11) {
            x = 331;
            y = 566;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
