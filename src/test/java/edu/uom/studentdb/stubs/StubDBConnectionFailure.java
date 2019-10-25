package edu.uom.studentdb.stubs;

import edu.uom.studentdb.DBConnection;
import edu.uom.studentdb.Student;

public class StubDBConnectionFailure implements DBConnection {
    public int commitStudent(Student student) {
        return -1;
    }
}
