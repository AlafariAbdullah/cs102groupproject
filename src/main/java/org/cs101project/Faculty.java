package org.cs101project;

public class Faculty extends Employee {

    private String rank; // (lecturer, assistant professor, associate professor or professor)
    private String specialisation;

    public Faculty(String fname, String lname, String username, String password, String birthDate, String department, String officeNum, String rank, String specialization) {
        super(fname, lname, username, password, birthDate, "Faculty", department, officeNum);
        this.setRank(rank);
        this.SetSpecialisation(specialization);
    }

    public Faculty(String fname, String lname, String username, String password, String birthDate, String department, String officeNum, String rank, String specialization, boolean isEncrypted) {
        super(fname, lname, username, password, birthDate, "Faculty", department, officeNum, true);
        this.rank = rank;
        this.specialisation = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.getRank() + "," + this.getSpecialisation();
    }
    @Override
    public String toDisplayString() {
        return super.toDisplayString()
            + "\nRank: " + this.getRank()
            + "\nSpecialisation: " + this.getSpecialisation();
    }

    public String getRank() {
        return this.rank;
    }

    public String getSpecialisation() {
        return this.specialisation;
    }

    public void setRank(String newRank) {
        if (newRank == null) {
            throw new IllegalArgumentException("Rank cannot be null");
        }

        String trimmed = newRank.trim();

        if (!(trimmed.equalsIgnoreCase("Lecturer") ||
              trimmed.equalsIgnoreCase("Assistant Professor") ||
              trimmed.equalsIgnoreCase("Associate Professor") ||
              trimmed.equalsIgnoreCase("Professor"))) {

            throw new IllegalArgumentException("Rank must be one of: Lecturer, Assistant Professor, Associate Professor, Professor");
        }

        this.rank = trimmed;
    }

    public void SetSpecialisation(String newSpecialisation) {
        if (newSpecialisation == null) {
            throw new IllegalArgumentException("Specialization cannot be null");
        }

        String trimmed = newSpecialisation.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be empty");
        }

        this.specialisation = trimmed;
    }

}
