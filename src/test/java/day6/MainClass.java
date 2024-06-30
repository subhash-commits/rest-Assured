package day6;

public class MainClass {
	    private String id;
	    private String name;
	    private long age;
	    private String grade;
	    private String email;
	    private CourseElement[] courses;

	    public String getID() { return id; }
	    public void setID(String value) { this.id = value; }

	    public String getName() { return name; }
	    public void setName(String value) { this.name = value; }

	    public long getAge() { return age; }
	    public void setAge(long value) { this.age = value; }

	    public String getGrade() { return grade; }
	    public void setGrade(String value) { this.grade = value; }

	    public String getEmail() { return email; }
	    public void setEmail(String value) { this.email = value; }

	    public CourseElement[] getCourses() { return courses; }
	    public void setCourses(CourseElement[] value) { this.courses = value; }
}
