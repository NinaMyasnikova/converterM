package com.myasnikova.converterm;

class Valute
{
    private String ID;
    private String NumCode;
    private String CharCode;
    private int Nominal;
    private String Name;
    private Double Value;
    private Double Previous;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumCode() {
        return NumCode;
    }

    public void setNumCode(String numCode) {
        NumCode = numCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public void setNominal(int nominal) {
        Nominal = nominal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getValue() {
        return Value;
    }

    public void setValue(Double value) {
        Value = value;
    }

    public Double getPrevious() {
        return Previous;
    }

    public void setPrevious(Double previous) {
        Previous = previous;
    }
}