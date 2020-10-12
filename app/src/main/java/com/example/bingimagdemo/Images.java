package com.example.bingimagdemo;

import java.util.ArrayList;
import java.util.List;

public class Images {
    private String startdate;
    private String fullstartdate;
    private String enddate;
    private String url;
    private String urlbase;
    private String copyright;
    private String copyrigthlink;
    private String title;
    private String quiz;
    private boolean wp;
    private String hsh;
    private int drk;
    private int top;
    private int bot;
    private ArrayList<Integer> hs;

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getFullstartdate() {
        return fullstartdate;
    }

    public void setFullstartdate(String fullstartdate) {
        this.fullstartdate = fullstartdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlbase() {
        return urlbase;
    }

    public void setUrlbase(String urlbase) {
        this.urlbase = urlbase;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyrigthlink() {
        return copyrigthlink;
    }

    public void setCopyrigthlink(String copyrigthlink) {
        this.copyrigthlink = copyrigthlink;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public boolean isWp() {
        return wp;
    }

    public void setWp(boolean wp) {
        this.wp = wp;
    }

    public String getHsh() {
        return hsh;
    }

    public void setHsh(String hsh) {
        this.hsh = hsh;
    }

    public int getDrk() {
        return drk;
    }

    public void setDrk(int drk) {
        this.drk = drk;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBot() {
        return bot;
    }

    public void setBot(int bot) {
        this.bot = bot;
    }

    public List<Integer> getHs() {
        return hs;
    }

    public void setHs(ArrayList<Integer> hs) {
        this.hs = hs;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
