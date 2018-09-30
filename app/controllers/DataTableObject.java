package controllers;

import models.User;

import java.util.List;

public class DataTableObject {
    int draw;
    int recordsTotal;

    int recordsFiltered;

    String search;

    List<User> data;

    public DataTableObject() {
        draw = 0;
        recordsTotal = 0;
        recordsFiltered = 0;
        search = "";
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setDraw(int draw) {
        this.draw += draw;
    }

    public int getDraw() {
        return draw;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

//    public String getsEcho() {
//        return sEcho;
//    }
//
//    public void setsEcho(String sEcho) {
//        this.sEcho = sEcho;
//    }

//    public String getsColumns() {
//        return sColumns;
//    }

//    public void setsColumns(String sColumns) {
//        this.sColumns = sColumns;
//    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

}
