package Main;

public class Student {
    private String matricule;
    private String firstName;
    private String lastName;
    private String birthDay;

    private String section;
    private String origin;

    private  boolean stranger;
    private  boolean scholarship;

    private boolean newStudent;
    private boolean reRegistration;

    public Student() { }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setOrigins(String origins) {
        this.origin = origins;
    }

    public void setStranger(boolean stranger) {
        this.stranger = stranger;
    }

    public void setScholarship(boolean scholarship) {
        this.scholarship = scholarship;
    }

    public void setNewStudent(boolean newStudent) {
        this.newStudent = newStudent;
    }

    public void setReRegistration(boolean reRegistration) {
        this.reRegistration = reRegistration;
    }

    @Override
    public String toString() {
        return "Student{" +
                "matricule='" + matricule + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", section='" + section + '\'' +
                ", origin='" + origin + '\'' +
                ", stranger=" + stranger +
                ", scholarship=" + scholarship +
                ", newStudent=" + newStudent +
                ", reRegistration=" + reRegistration +
                '}';
    }
}
