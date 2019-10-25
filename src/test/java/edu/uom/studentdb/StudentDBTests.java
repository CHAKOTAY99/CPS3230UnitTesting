package edu.uom.studentdb;

import edu.uom.studentdb.stubs.StubDBConnectionFailure;
import edu.uom.studentdb.stubs.StubDBConnectionSuccess;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

    @Test
    public void testCommitWithSuccessfulDBConnection(){
        // Setup
        DBConnection dbConnection = new StubDBConnectionSuccess();
        studentDB.addStudent(student);

        // Exercise
        boolean result = studentDB.commit(dbConnection);

        // Verify
        assertTrue(result);
    }

    @Test
    public void testCommitWithFailedDBConnection(){
        // Setup
        DBConnection dbConnection = new StubDBConnectionFailure();
        studentDB.addStudent(student);

        // Exercise
        boolean result = studentDB.commit(dbConnection);

        // Verify
        assertFalse(result);
    }
}
