package org.cs101project;


public class Award implements Comparable<Award>{

    private String date;
    private String awardName;
    private String issuer;

    public Award(String awardName, String date, String issuer) {
        this.awardName = awardName;
        this.date = date;
        this.issuer = issuer;
    }
    @Override
    public String toString(){
        return this.getAwardName() + "-" + this.getDate() + "-" + this.getIssuer() ;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public int compareTo(Award o) {
        return this.getAwardName().compareToIgnoreCase(o.getAwardName());
    }

}
