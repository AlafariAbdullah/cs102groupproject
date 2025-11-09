package org.cs101project;

public class Award {

    private String date;
    private String awardName;
    private String issuer;

    public Award(String date, String awardName, String issuer) {
        this.date = date;
        this.awardName = awardName;
        this.issuer = issuer;
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

}
