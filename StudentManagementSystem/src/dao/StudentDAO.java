package dao;

import model.Student;
import java.util.List;

public interface StudentDAO {
	
	 	void addStudent(Student student);
	    List<Student> viewStudents();
	    void updateStudent(Student student);
	    void deleteStudent(int id);
	    Student getStudentById(int id);
	

}
