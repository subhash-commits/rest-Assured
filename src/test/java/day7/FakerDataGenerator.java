package day7;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class FakerDataGenerator {

	@Test
	public void fakeDataGenerator() {
		Faker faker = new Faker();
		String fullName = faker.name().fullName();
		String firstName = faker.name().firstName();

		String userName = faker.name().username();
		String passeword = faker.internet().password();
		String PhoneNmuber = faker.phoneNumber().cellPhone();
		String emailAddress = faker.internet().safeEmailAddress();

		System.out.println(
				fullName + ":" + firstName + ":" + userName + ":" + passeword + ":" + PhoneNmuber + ":" + emailAddress);
	}

}
