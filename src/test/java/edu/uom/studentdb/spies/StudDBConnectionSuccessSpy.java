package edu.uom.studentdb.spies;

import edu.uom.studentdb.DBConnection;
import edu.uom.studentdb.Student;
import edu.uom.studentdb.stubs.StubDBConnectionSuccess;

public class StudDBConnectionSuccessSpy implements DBConnection {

    int counter = 0;


    public int commitStudent(Student student) {
        counter++;
        return 0;
    }

    public int count() {
        return counter;
    }

}
