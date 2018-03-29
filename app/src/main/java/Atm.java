import android.text.format.Time;

import java.net.URL;

/**
 * Created by daniyar on 3/29/18.
 */

public class Atm {

    String bank;
    String lat;
    String lon;
    Time hour_start;
    Time hour_end;
    String desc;
    URL url;

    public Atm(){

    }

    public Atm(String bank, String lat, String lon, Time hour_start, Time hour_end, String desc, URL url) {
        this.bank = bank;
        this.lat = lat;
        this.lon = lon;
        this.hour_start = hour_start;
        this.hour_end = hour_end;
        this.desc = desc;
        this.url = url;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Time getHour_start() {
        return hour_start;
    }

    public void setHour_start(Time hour_start) {
        this.hour_start = hour_start;
    }

    public Time getHour_end() {
        return hour_end;
    }

    public void setHour_end(Time hour_end) {
        this.hour_end = hour_end;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
