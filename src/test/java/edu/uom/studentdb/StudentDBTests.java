package edu.uom.studentdb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentDBTests {

    public final int DEFAULT_STUDENT_ID = 1;

    StudentDB studentDB;
    Student student;

    @Before
    public void setup(){
        studentDB = new StudentDB();
        student = new Student(DEFAULT_STUDENT_ID, null, null);
    }

    @After
    public void tearDown(){
        studentDB = null;
    }

    @Test
    public void testCountIncrementAfterAddingStudent(){
        // Current size of database
        int size = studentDB.countStudents();

        // Exercise
        studentDB.addStudent(student);

        // Verify
        assertEquals(size+1, studentDB.countStudents());
    }

    @Test
    public void testAddingTheSameStudentTwice(){
        // Current size of database
        int size = studentDB.countStudents();

        // Exercise
        studentDB.addStudent(student);
        studentDB.addStudent(student);

        // Verify
        assertEquals(size+1, studentDB.countStudents());
    }

    @Test
    public void testRemovingExistingStudent(){
        // Setup database
        studentDB.addStudent(student);

        // Size of database
        int size = studentDB.countStudents();;

        // Exercise
        studentDB.removeStudent(student.id);

        // Verify
        assertEquals(size-1, studentDB.countStudents());
    }

    @Test
    public void testRemovingUnExistingStudent(){
        // Setup database
        studentDB.addStudent(student);

        // Size of database
        int size = studentDB.countStudents();;

        // Exercise
        studentDB.removeStudent(student.id+1);

        // Verify
        assertEquals(size, studentDB.countStudents());
    }

}
