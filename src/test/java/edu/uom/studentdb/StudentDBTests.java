package edu.uom.studentdb;

import edu.uom.DBConnection;
import edu.uom.Student;
import edu.uom.StudentDB;
import edu.uom.studentdb.stubs.StubDBCConnectionSuccess;
import junit.extensions.TestSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StudentDBTests {

    public final int DEFAULT_STUDENT_ID = 1;
    StudentDB studentDB;

    @Before
    public void setup(){
        studentDB = new StudentDB();
    }

    @After
    public void tearDown(){
        studentDB = null;
    }

    @Test
    public void testCountIncrementAfterAddingStudent(){
        // Setup
        Student student = new Student(DEFAULT_STUDENT_ID, null, null);
        // Cache the current size of database
        int size = studentDB.countStudents();
         // Excercise
        studentDB.addStudent(student);
        // Verify
        assertEquals(size+1, studentDB.countStudents());
    }

    @Test
    public void testAddingTheSameStudentTwice(){
        // Setup
        Student student = new Student(DEFAULT_STUDENT_ID, null, null);
        // Cache the current size of database
        int size = studentDB.countStudents();
        // Excercise
        studentDB.addStudent(student);
        studentDB.addStudent(student);
        // Verify
        assertEquals(size+1, studentDB.countStudents());
    }

    @Test
    public void removingOnlyStudents(){
        // Setup
        Student student = new Student(DEFAULT_STUDENT_ID, null, null);
        studentDB.addStudent(student);
        // Cache the current size of database
        int size = studentDB.countStudents();
        // Excercise
        studentDB.removeStudent(size);
        // Verify
        assertEquals(size-1, studentDB.countStudents());
    }

    @Test
    public void removingStudentWithUnexistingID(){
        // Setup
        Student student = new Student(DEFAULT_STUDENT_ID, null, null);
        studentDB.addStudent(student);
        // Cache the current size of database
        int size = studentDB.countStudents();
        // Excercise
        studentDB.removeStudent(size+1);
        // Verify
        assertEquals(size, studentDB.countStudents());
    }

    @Test
    public void testCommitWithSuccessfulDBConnection(){
        //DBConnection dbConnection = new StubDBCConnectionSuccess();
        //studentDB.addStudent(student);

    }
}
