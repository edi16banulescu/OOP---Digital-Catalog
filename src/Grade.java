public class Grade implements Comparable, Cloneable {
    private Double partialScore, examScore;
    private Student student;
    private String course;

    public void setPartialScore(Double partialScore) {
        this.partialScore = partialScore;
    }

    public Double getPartialScore() {
        return partialScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public Double getExamScore() {
        return examScore;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public double getTotal() {
        return partialScore + examScore;
    }

    public String toString() {
        return "Student: " + getStudent() + " has a new grade: " + getCourse() + "\n grades: "
                        + getPartialScore() + " " + getExamScore();
    }

    @Override
    public int compareTo(Object o) {
        Grade g = (Grade) o;
        double result = getTotal() - g.getTotal();

        if(result < 0)
            return -1;
        else
            return 1;
    }

    public Object clone() {
        try {
            Grade grade = (Grade) super.clone();
            return grade;

        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
