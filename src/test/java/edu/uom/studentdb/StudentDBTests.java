package edu.uom.studentdb;

import edu.uom.studentdb.spies.StudDBConnectionSuccessSpy;
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

    @Test
    public void testDirtyFlagSetToFalseAfterCreation() {
        //Verify
        assertFalse(studentDB.isDirty());
    }

    @Test
    public void testDirtyFlagSetToTrueAfterAddingAStudent() {
        //Exercise
        studentDB.addStudent(student);

        //Verify
        assertTrue(studentDB.isDirty);
    }

    @Test
    public void testDirtyFlagSetToFalseAfterCommitting() {
        //Setup
        studentDB.addStudent(student);

        //Exercise
        studentDB.commit(new StubDBConnectionSuccess());

        //Verify
        assertFalse(studentDB.isDirty);
    }



    @Test
    public void testDirtyFlagSetToTrueAfterRemovingAStudent() {
        //Setup
        studentDB.addStudent(student);
        studentDB.commit(new StubDBConnectionSuccess());

        //Exercise
        studentDB.removeStudent(student.id);

        //Verify
        assertTrue(studentDB.isDirty);
    }


    @Test
    public void testDbConnectionIsCalledWhenDBIsDirty() {

        //Setup
        StudDBConnectionSuccessSpy spyConnection = new StudDBConnectionSuccessSpy();
        int initialCount = spyConnection.count();
        studentDB.addStudent(student);  //This makes the DB dirty

        //Exercise
        studentDB.commit(spyConnection);

        //Verify
        int newCount = spyConnection.count();
        assertTrue( newCount > initialCount);
    }

    @Test
    public void testDbConnectionIsNotCalledWhenDBIsNotDirty() {

        //Setup
        StudDBConnectionSuccessSpy spyConnection = new StudDBConnectionSuccessSpy();
        studentDB.addStudent(student);  //This makes the DB dirty
        studentDB.commit(spyConnection); //This makes the DB not dirty
        int initialCount = spyConnection.count();

        //Exercise
        studentDB.commit(spyConnection);

        //Verify
        int newCount = spyConnection.count();
        assertEquals(initialCount, newCount);
    }
}
