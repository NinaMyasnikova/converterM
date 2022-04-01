package com.myasnikova.converterm;

import java.util.Map;

class Root
{
    String Date;
    String PreviousDate;
    String PreviousURL;
    String Timestamp;

    private Map<String, Valute> Valute;

    public Map<String, com.myasnikova.converterm.Valute> getValute() {
        return Valute;
    }

    public void setValute(Map<String, com.myasnikova.converterm.Valute> valute) {
        Valute = valute;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPreviousDate() {
        return PreviousDate;
    }

    public void setPreviousDate(String previousDate) {
        PreviousDate = previousDate;
    }

    public String getPreviousURL() {
        return PreviousURL;
    }

    public void setPreviousURL(String previousURL) {
        PreviousURL = previousURL;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }
}