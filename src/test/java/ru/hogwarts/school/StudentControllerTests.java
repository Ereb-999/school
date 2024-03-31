package ru.hogwarts.school;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class StudentControllerTests {
	List<Student> studentsSave;
	@LocalServerPort
	private int port;
	@Autowired
	private StudentController studentController;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TestRestTemplate testRestTemplate;
	private static final ObjectMapper objectMapper = new ObjectMapper();
	@BeforeEach
	void setUp(){
		Student student = new Student("Voldemort", 70, 4l);
		Student student1 = new Student("Рон", 15,2l);
		List<Student> studentList = List.of(student, student1);

		studentsSave = studentRepository.saveAll(studentList);
	}
	@Test
	public void contextLoads() {
		Assertions.assertThat(studentController).isNotNull();
	}
	@Test
	void studentGetInfoTest() throws JSONException, JsonProcessingException {

		String check = objectMapper.writeValueAsString(studentsSave.get(0));

		ResponseEntity<String> response =
				testRestTemplate.getForEntity("http://localhost:" + port + "/student/" + studentsSave.get(0).getId(),
						String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		JSONAssert.assertEquals(check, response.getBody(), false);
	}
	@Test
	void getStudentsTest(){
		ResponseEntity<List<Student>>response = testRestTemplate.exchange(
				"http://localhost:" + port + "/student", HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {
				});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		List<Student> studentList = response.getBody();
		assertEquals(studentsSave, studentList);
	}

@Test
	void createTest() throws  JsonProcessingException, JSONException{
		Student student = new Student("Гермиона", 15, 3l);
		String check = objectMapper.writeValueAsString(student);
		ResponseEntity<String> response = testRestTemplate.postForEntity("/student", student, String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(check, response.getBody(), false);
}

@Test
	void editTest(){
		Student student = new Student("Рон", 15, 3l);
	HttpEntity<Student>entity = new HttpEntity<>(student);
	student.setId(studentsSave.get(0).getId());
	ResponseEntity<Student>response= this.testRestTemplate
			.exchange("/student/" + studentsSave.get(0).getId(), HttpMethod.PUT, entity, Student.class);
	assertEquals(response.getStatusCode(), HttpStatus.OK);
	assertEquals(student, response.getBody());
}

@Test
	void deleteTest(){
	HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
	ResponseEntity <String> response =
			testRestTemplate
					.exchange("/students/"+studentsSave.get(0).getId(),HttpMethod.DELETE,entity,String.class);
	assertEquals(HttpStatus.OK,response.getStatusCode());
}










}
