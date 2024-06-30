package day6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.Pojo_PostRequest;

public class SerializationDeserilaization {

	// POJO to JSON(Serialization)
	@Test
	public void convertPOJO2JSON() throws JsonProcessingException {
		// created java object using POJO class
		Student data = new Student();
		data.setId("3");
		data.setName("subhash");
		data.setAge(20);
		data.setGrade("B");
		data.setEmail("kalvakolusubhash@gmail.com");
		HashMap subject = new HashMap();
		subject.put("subject", "Maths");
		Object[] courses = { "HTML", "JavaScript", subject };
		data.setCourses(courses);

		// conver POJO object to JSON object(serialization)
		ObjectMapper mapper = new ObjectMapper();
		String jsondata = mapper.writer().writeValueAsString(data);
		System.out.println(jsondata);

	}

	@Test
	public void convertJSON2POJO() throws JsonProcessingException {
		String jsondata = "{\"id\":\"3\"," + "\"name\":\"subhash\"," + "\"age\":20," + "\"grade\":\"B\","
				+ "\"email\":\"kalvakolusubhash@gmail.com\"," + "\"courses\":[\"HTML\",\"JavaScript\","
				+ "{\"subject\":\"Maths\"}]}";

		// conver JSON object to POJO object(DEserialization)
		ObjectMapper mapper = new ObjectMapper();
		Student stupojo = mapper.readValue(jsondata, Student.class);
		System.out.println(stupojo.getId() + "::" + stupojo.getName() + "::" + stupojo.getEmail() + "::"
				+ stupojo.getGrade() + "::" + stupojo.getCourses()[1] + "::" + stupojo.getCourses()[2]);
	}

}
