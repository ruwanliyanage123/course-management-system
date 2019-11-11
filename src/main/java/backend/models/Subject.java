package backend.models;

public class Subject {
    private String subjectId;
    private String subjectName;
    private int numberOfCredits;

    public Subject(String subjectId, String subjectName, int numberOfCredits) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.numberOfCredits = numberOfCredits;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }
}
