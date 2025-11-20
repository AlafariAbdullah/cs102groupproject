package org.cs101project;

import java.util.ArrayList;

public class Student extends Person {

    private String status;
    private String major;
    private ArrayList<Award> Awards;

    public Student(String fname, String lname, String username, String password, String birthDate, String status, String major, ArrayList<Award> Awards) {
        super(fname, lname, username, password, birthDate, "Student");
        this.setStatus(status);
        this.major = major;
        if (Awards != null) {
            this.Awards = Awards;
        } else {
            this.Awards = new ArrayList<Award>();
        }
    }

    // to handle importing from file without ruining the password that's already encrypted
    public Student(String fname, String lname, String username, String password, String birthDate, String status, String major, ArrayList<Award> Awards, boolean isEncrypted) {
        super(fname, lname, username, password, birthDate, "Student", true);
        this.setStatus(status);
        this.major = major;
        if (Awards != null) {
            this.Awards = Awards;
        } else {
            this.Awards = new ArrayList<Award>();
        }
    }

    @Override
    public String toString() {
        String awards = "Awards:";
        for (Award a : this.Awards) {
            awards = awards + "\n" + a.toString();
        }
        return super.toString() + "," + this.getStatus() + "," + this.getMajor() + ",\n" + awards;
    }

    // Modify a single Award:
    public void setAward(int index, Award newAward) {
        Awards = this.getAwards();
        if (index >= Awards.size()) {
            throw new IllegalStateException("Index outOfBound");
        }
        this.getAwards().set(index, newAward);
    }

    public void addAward(Award newAward) {
        this.Awards.add(newAward);
    }

    public void sortAwards(boolean nameOrDate, boolean ascOrDesc) {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        String trimmed = status.trim();

        if (!(trimmed.equalsIgnoreCase("Freshman") ||
              trimmed.equalsIgnoreCase("Sophomore") ||
              trimmed.equalsIgnoreCase("Junior") ||
              trimmed.equalsIgnoreCase("Senior"))) {

            throw new IllegalArgumentException("Status must be one of: Freshman, Sophomore, Junior, Senior");
        }

        this.status = trimmed;
    }
    @Override
    public String toDisplayString() {
        String out = super.toDisplayString()
            + "\nStatus: " + this.getStatus()
            + "\nMajor: " + this.getMajor()
            + "\nAwards:";
    
        if (Awards == null || Awards.isEmpty()) {
            out += "\n  No awards.";
        } else {
            for (Award a : Awards) {
                out += "\n  - " + a.getAwardName() + " (" + a.getDate() + ") — " + a.getIssuer();
            }
        }
    
        return out;
    }    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public ArrayList<Award> getAwards() {
        return this.Awards;
    }

    public void setAwards(ArrayList<Award> Awards) {
        this.Awards = Awards;
    }

}
