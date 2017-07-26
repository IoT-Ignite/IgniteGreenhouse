package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class DateTime {

    /*@SerializedName("weekyear")
    private int weekYear;

    @SerializedName("monthOfYear")
    private int monthOfYear;

    @SerializedName("yearOfEra")
    private int yearOfEra;

    @SerializedName("yearOfCentury")
    private int yearOfCentury;

    @SerializedName("centuryOfEra")
    private int centuryOfEra;

    @SerializedName("millisOfSecond")
    private long millisOfSecond;

    @SerializedName("millisOfDay")
    private long millisOfDay;

    @SerializedName("secondOfMinute")
    private long secondOfMinute;

    @SerializedName("secondOfDay")
    private long secondOfDay;

    @SerializedName("minuteOfHour")
    private long minuteOfHour;

    @SerializedName("minuteOfDay")
    private long minuteOfDay;

    @SerializedName("hourOfDay")
    private int hourOfDay;

    @SerializedName("weekOfWeekyear")
    private int weekOfWeekYear;

    @SerializedName("year")
    private int year;

    @SerializedName("dayOfMonth")
    private int dayOfMonth;

    @SerializedName("dayOfWeek")
    private int dayOfWeek;

    @SerializedName("era")
    private int era;

    @SerializedName("dayOfYear")
    private int dayOfYear;*/

    @SerializedName("chronology")
    private Chronology chronology;

    @SerializedName("millis")
    private long millis;
/*
    @SerializedName("zone")
    private Zone zone;

    @SerializedName("beforeNow")
    private boolean beforeNow;

    @SerializedName("equalNow")
    private boolean equalNow;

    @SerializedName("afterNow")
    private boolean afterNow;*/
/*

    public int getWeekYear() {
        return weekYear;
    }

    public void setWeekYear(int weekYear) {
        this.weekYear = weekYear;
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(int monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    public int getYearOfEra() {
        return yearOfEra;
    }

    public void setYearOfEra(int yearOfEra) {
        this.yearOfEra = yearOfEra;
    }

    public int getYearOfCentury() {
        return yearOfCentury;
    }

    public void setYearOfCentury(int yearOfCentury) {
        this.yearOfCentury = yearOfCentury;
    }

    public int getCenturyOfEra() {
        return centuryOfEra;
    }

    public void setCenturyOfEra(int centuryOfEra) {
        this.centuryOfEra = centuryOfEra;
    }

    public long getMillisOfSecond() {
        return millisOfSecond;
    }

    public void setMillisOfSecond(long millisOfSecond) {
        this.millisOfSecond = millisOfSecond;
    }

    public long getMillisOfDay() {
        return millisOfDay;
    }

    public void setMillisOfDay(long millisOfDay) {
        this.millisOfDay = millisOfDay;
    }

    public long getSecondOfMinute() {
        return secondOfMinute;
    }

    public void setSecondOfMinute(long secondOfMinute) {
        this.secondOfMinute = secondOfMinute;
    }

    public long getSecondOfDay() {
        return secondOfDay;
    }

    public void setSecondOfDay(long secondOfDay) {
        this.secondOfDay = secondOfDay;
    }

    public long getMinuteOfHour() {
        return minuteOfHour;
    }

    public void setMinuteOfHour(long minuteOfHour) {
        this.minuteOfHour = minuteOfHour;
    }

    public long getMinuteOfDay() {
        return minuteOfDay;
    }

    public void setMinuteOfDay(long minuteOfDay) {
        this.minuteOfDay = minuteOfDay;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getWeekOfWeekYear() {
        return weekOfWeekYear;
    }

    public void setWeekOfWeekYear(int weekOfWeekYear) {
        this.weekOfWeekYear = weekOfWeekYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getEra() {
        return era;
    }

    public void setEra(int era) {
        this.era = era;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }*/

    public Chronology getChronology() {
        return chronology;
    }

    public void setChronology(Chronology chronology) {
        this.chronology = chronology;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

   /* public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public boolean isBeforeNow() {
        return beforeNow;
    }

    public void setBeforeNow(boolean beforeNow) {
        this.beforeNow = beforeNow;
    }

    public boolean isEqualNow() {
        return equalNow;
    }

    public void setEqualNow(boolean equalNow) {
        this.equalNow = equalNow;
    }

    public boolean isAfterNow() {
        return afterNow;
    }

    public void setAfterNow(boolean afterNow) {
        this.afterNow = afterNow;
    }
*/
    @Override
    public String toString() {
        return "DateTime{" +
              /*  "weekYear=" + weekYear +
                ", monthOfYear=" + monthOfYear +
                ", yearOfEra=" + yearOfEra +
                ", yearOfCentury=" + yearOfCentury +
                ", centuryOfEra=" + centuryOfEra +
                ", millisOfSecond=" + millisOfSecond +
                ", millisOfDay=" + millisOfDay +
                ", secondOfMinute=" + secondOfMinute +
                ", secondOfDay=" + secondOfDay +
                ", minuteOfHour=" + minuteOfHour +
                ", minuteOfDay=" + minuteOfDay +
                ", hourOfDay=" + hourOfDay +
                ", weekOfWeekYear=" + weekOfWeekYear +
                ", year=" + year +
                ", dayOfMonth=" + dayOfMonth +
                ", dayOfWeek=" + dayOfWeek +
                ", era=" + era +
                ", dayOfYear=" + dayOfYear +*/
                ", chronology=" + chronology +
                ", millis=" + millis +
               /* ", zone=" + zone +
                ", beforeNow=" + beforeNow +
                ", equalNow=" + equalNow +
                ", afterNow=" + afterNow + */
                '}';
    }
}
