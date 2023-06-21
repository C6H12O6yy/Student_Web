package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class StudentController2 {
	@GetMapping("/students")
	public List<Student> getStudents(Model model) throws IOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Student> students = new ArrayList<Student>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "long2407");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from student");
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String dob = resultSet.getString("dob");
				String major = resultSet.getString("major");
				int vaccinated = resultSet.getInt("vaccinated");
				students.add(new Student(id, name, dob, major, vaccinated == 0 ? false:true));
			}
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		//model.addAttribute("students", students);
		return students;
	}
	@GetMapping("/student/{id}")
	public Student getStudent(Model model, @PathVariable String id) {
		model.addAttribute("id", id);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;

		Student student = new Student();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "long2407");
			ps = connection.prepareStatement("select * from student where id = ?");
			ps.setInt(1, Integer.valueOf(id));
			result = ps.executeQuery();
			while (result.next()) {
				student.setId(result.getInt("id"));
				student.setName(result.getString("name"));
				student.setDob(result.getString("dob"));
				student.setMajor(result.getString("major"));
				student.setVaccinated(result.getInt("vaccinated") != 0 ? true : false);
			}
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
		}
		//model.addAttribute("student", student);
		return student;
	}
	@PostMapping("/student/save/{id}")
	public String addStudent(@RequestBody Student student, @PathVariable String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "long2407");
			ps = connection.prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?, ?)");
			ps.setInt(1, Integer.valueOf(student.getId()));
			ps.setString(2, student.getName());
			ps.setString(3, student.getDob());
			ps.setString(4, student.getMajor());
			ps.setInt(5, student.isVaccinated() ? 1 : 0);
			result = ps.executeUpdate();
			ps.close();
			connection.close();
			// Redirect the response to success page
			return "Add success";
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		//return "error"; // tạo trang Error
	}
	@PutMapping("/student/save/{id}")
	public String updateStudent(@RequestBody Student student, @PathVariable String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "long2407");
			ps = connection.prepareStatement("UPDATE student SET name=?,dob=?,major=?,vaccinated=? WHERE id=?");
			ps.setString(1, student.getName());
			ps.setString(2, student.getDob());
			ps.setString(3, student.getMajor());
			ps.setInt(4, student.isVaccinated() ? 1 : 0);
			ps.setInt(5, Integer.valueOf(student.getId()));
			result = ps.executeUpdate();
			ps.close();
			connection.close();
			// Redirect the response to success page
			return "Update success";
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		//return "error"; // tạo trang Error
	}
	@DeleteMapping("/student/delete/{id}")
	public String deleteStudent(Student student, @PathVariable String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "long2407");
			ps = connection.prepareStatement("DELETE FROM student WHERE id=?");
			ps.setInt(1, Integer.valueOf(student.getId()));
			result = ps.executeUpdate();
			ps.close();
			connection.close();
			// Redirect the response to success page
			return "Delete book success";
		} // End of try block
		catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		//return "error"; // tạo trang Error
	}
	
}
