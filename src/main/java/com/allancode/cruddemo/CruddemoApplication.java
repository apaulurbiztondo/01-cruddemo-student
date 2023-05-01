package com.allancode.cruddemo;

import com.allancode.cruddemo.dao.StudentDAO;
import com.allancode.cruddemo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->
//			updateStudent(studentDAO,18);
			createStudent(studentDAO);
//		createMultipleStudents(studentDAO);
//			queryForStudents(studentDAO);
//			queryStudentsByLastName(studentDAO, "Diesta");
//			deleteStudent(studentDAO, 16);
//			deleteAllStudents(studentDAO);
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		log.warn("Deleting all students...");
		int deletedRowCount = studentDAO.deleteAll();
		log.warn("Deleted student row count: {}", deletedRowCount);
	}

	private void deleteStudent(StudentDAO studentDAO, int id) {
		studentDAO.delete(id);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();
		students.forEach(System.out::println);
	}

	private void queryStudentsByLastName(StudentDAO studentDAO, String lastName){
		List<Student> students = studentDAO.findByLastName(lastName);
		students.forEach(System.out::println);
	}

	private void createMultipleStudents(StudentDAO studentDAO){
		Student student1 = Student.builder()
				.firstName("Allan 1")
				.lastName("Urbiztondo")
				.email("allanpaulourbiztondo@gmail.com")
				.build();
		Student student2 = Student.builder()
				.firstName("Allan 2")
				.lastName("Urbiztondo")
				.email("allanpaulourbiztondo@gmail.com")
				.build();
		Student student3 = Student.builder()
				.firstName("Allan 3")
				.lastName("Urbiztondo")
				.email("allanpaulourbiztondo@gmail.com")
				.build();
		List<Student> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);
		students.add(student3);

		students.forEach(studentDAO::save);
	}

	private void readStudent(StudentDAO studentDAO, Integer id){
		Student student = studentDAO.findById(id);
		log.warn("Found student {} ", student);
	}

	private void createStudent(StudentDAO studentDAO) {
		log.warn("Creating new student");
		Student student = Student.builder()
				.firstName("Raki")
				.lastName("Diesta")
				.email("rakidiesta@gmail.com")
				.build();
		studentDAO.save(student);
		log.warn("Saved student with id: {}", student.getId());
		readStudent(studentDAO, Math.toIntExact(student.getId()));
	}

	private void updateStudent(StudentDAO studentDAO, Integer id) {
		Student existingStudent = studentDAO.findById(id);
		log.warn("Updating existing student with id: {}", existingStudent.getId());
		Student student = Student.builder()
				.id(existingStudent.getId())
				.firstName("Elora")
				.lastName("Urbiztondo")
				.email("elorabraga@gmail.com")
				.build();
		studentDAO.update(student);
		log.warn("Updated student with id: {}", student.getId());
		readStudent(studentDAO, Math.toIntExact(student.getId()));
	}

}
