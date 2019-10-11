package edu.uom;

import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    List<Student> db;

    public StudentDB(){
        db = new ArrayList<Student>();
    }

    public void addStudent(Student s){
        if(getStudentById(s.id) == null){
        db.add(s);
        }
    }

    public void removeStudent(int id){
        db.remove(getStudentById(id));
    }

    public Student getStudentById(int id){
        for(Student s : db){
            if(s.id == id){
                return s;
            }
        }
        return null;
    }

    public int countStudents(){
        return db.size();
    }
}