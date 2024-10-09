package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

import model.Student;



public class StudentDAOImpl implements StudentDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/student_management";
	private static final String USER = "root"; 
	private static final String PASSWORD = "Manjunath_9";

	@Override
	public void addStudent(Student student) {
		String sql = "INSERT INTO students (name, age, grade, course) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getGrade());
            pstmt.setString(4, student.getCourse());
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
		
	}

	@Override
	public List<Student> viewStudents() {
		List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("grade"),
                    rs.getString("course")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
        }
        return students;

	}

	@Override
	public void updateStudent(Student student) {
		 String sql = "UPDATE students SET name = ?, age = ?, grade = ?, course = ? WHERE id = ?";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, student.getName());
	            pstmt.setInt(2, student.getAge());
	            pstmt.setString(3, student.getGrade());
	            pstmt.setString(4, student.getCourse());
	            pstmt.setInt(5, student.getId());
	            pstmt.executeUpdate();
	            System.out.println("Student updated successfully!");
	        } catch (SQLException e) {
	            System.err.println("Error updating student: " + e.getMessage());
	        }
		
	}

	@Override
	public void deleteStudent(int id) {
		 String sql = "DELETE FROM students WHERE id = ?";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();
	            System.out.println("Student deleted successfully!");
	        } catch (SQLException e) {
	            System.err.println("Error deleting student: " + e.getMessage());
	        }
		
	}

	@Override
	public Student getStudentById(int id) {
		 String sql = "SELECT * FROM students WHERE id = ?";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return new Student(
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getInt("age"),
	                    rs.getString("grade"),
	                    rs.getString("course")
	                );
	            }
	        } catch (SQLException e) {
	            System.err.println("Error retrieving student: " + e.getMessage());
	        }
	        return null;
	    }
	
	
	

}
