package com.example.demo;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraTemplate;

import javax.validation.constraints.NotNull;

@SpringBootApplication
public class CassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraApplication.class, args);
	}

	@Autowired
	CassandraConfig cassandraConfig;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ProfessorRepository professorRepository;

	public void setCourseIfNotExist(String name, String faculty, int studentCap){
		Course course = new Course(name, faculty, studentCap);

		if(courseRepository.existsByNameEquals(course.getName())){
			System.out.println("Course already exists!");
		}
		else {
			courseRepository.save(course);
		}
	}

	public void setProfessorIfCourseNotTaken(String firstname, String lastname, String course, String title){
		Professor professor = new Professor(firstname, lastname, course, title);
		if(professorRepository.existsByCourseEquals(course)){
			System.out.println("A professor is already teaching that course, it doesn't need a second one");
		}
		else {
			professorRepository.save(professor);
		}
	}

	public boolean studentExists(int studentNumber){
		if(studentRepository.existsByStudentNumber(studentNumber)){
			return true;
		}
		return false;
	}

	public void setStudentIfNotExist(Student student){
		if(studentExists(student.getStudentNumber()) &&
				studentRepository.findByStudentNumber(student.getStudentNumber()).getCourse().equals(student.getCourse())){
			System.out.println("The Student is already taking this course.");
		}
		else if(!courseRepository.existsByNameEquals(student.getCourse())){
			System.out.println("Course does not exist.");
		}
		else {
			Course course = courseRepository.findByName(student.getCourse());
			course.setAmountOfStudents(course.getAmountOfStudents()+1);
			if(course.getAmountOfStudents()>course.getStudentCap()){
				System.out.println("Course is full, student will not be added");
			}
			else{
				studentRepository.save(student);
				courseRepository.save(course);
			}
		}
	}
	@Bean
	public CommandLineRunner commandLineRunner() {

		return args -> {
			setProfessorIfCourseNotTaken("Billas", "Asde", "Bimbalo musimas",
					"Not a real professor");
			setCourseIfNotExist("Nerealiacines duomenu bazes", "MIF", 10);
			//setCourseIfNotExist("Bimbalo musimas", "barakas", 2000);

			Student jons = new Student("Jonas", "Makaronas",
					"Nerealiacines duomenu bazes", 6513800);
			Student dzons = new Student("Dzonis","Bravo" ,
					"Nerealiacines duomenu bazes", 2123456);

			setStudentIfNotExist(jons);

			System.out.println(studentRepository.findAll());
			System.out.println(professorRepository.findAll());
			System.out.println(courseRepository.findAll());

			//System.out.println(studentRepository.findAllByCourse("Nerealiacines duomenu bazes"));
		};
	}

}