import java.util.Objects;

public class Student {
    // NOTE:  Student number is not intended as a Student ID!
    private int studentNo;
    private String firstName;
    private String lastName;

    private static int totalStudents = 0;

    public Student(String first, String last) {
        this.studentNo = totalStudents++;
        this.firstName = first;
        this.lastName = last;
    }

    public int getStudentNo() {

        return studentNo;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getStudentNo() == student.getStudentNo() &&
                Objects.equals(getFirstName(), student.getFirstName()) &&
                Objects.equals(getLastName(), student.getLastName());
    }

    @Override
    public String toString() {

        return firstName + " " + lastName + ", #" + studentNo;
    }
}
