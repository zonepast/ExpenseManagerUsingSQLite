package com.example.aff02.expansemanager.Model;

/**
 * Created by AFF02 on 23-Aug-17.
 */

public class DataModel {

    String name,desc,date,payment;
    int rupees,id;

    public DataModel() {
    }

    public DataModel(int id,String name, String desc, int rupees,String date,String payment) {
        this.name = name;
        this.desc = desc;
        this.rupees = rupees;
        this.date = date;
        this.payment = payment;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRupees() {
        return rupees;
    }

    public void setRupees(int rupees) {
        this.rupees = rupees;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
