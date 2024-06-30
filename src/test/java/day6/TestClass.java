package day6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestClass {

	@Test
	public void testPOJO() {
		CourseClass course = new CourseClass();
		course.setSubject("Maths");

		CourseElement courseele = new CourseElement();
		courseele.stringValue = "HTML";
		//courseele.stringValue = "JavaScript";
		courseele.courseClassValue = course;

		CourseElement[] courses = { courseele };

		MainClass main = new MainClass();
		main.setID("3");
		main.setName("subhash");
		main.setAge(30);
		main.setEmail("kaluvakolusubhash@gmail.com");
		main.setGrade("B");
		main.setCourses(courses);

		Response res = given().contentType(ContentType.JSON).body(main).when().get("http://localhost:3000/student");
		System.out.println(res.body());
		System.out.println(main.getAge());
	}

}
