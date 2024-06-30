package day6;

import java.util.HashMap;

public class Student {
	String id;
	HashMap subject;
	String name;
	int age;
	String grade;
	String email;
	Object[] courses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Object[] getCourses() {
		return courses;
	}

	public void setCourses(Object[] courses) {
		this.courses = courses;
	}
}

