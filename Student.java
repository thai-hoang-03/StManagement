package ManageStudents;

public class Student {
    private int id;
    private String name;
    private int semester;
    private String course;

    public Student(int id, String name, int semester, String course) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Student name: " + name + "\tCourse: " + course;
    }
    
}
