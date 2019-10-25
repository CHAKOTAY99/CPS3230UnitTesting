package edu.uom.studentdb.stubs;

import edu.uom.studentdb.DBConnection;
import edu.uom.studentdb.Student;

public class StubDBConnectionSuccess implements DBConnection {
    public int commitStudent(Student student) {
        return 0;
    }
}
