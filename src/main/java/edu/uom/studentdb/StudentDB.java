package edu.uom.studentdb;

import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    List<Student> database;
    boolean isDirty = false;

    public StudentDB(){
        database = new ArrayList<Student>();
    }

    public  int countStudents(){
        return database.size();
    }

    public void addStudent(Student s){
        if(getStudentById(s.id) == null){
            database.add(s);
            setDirty(true);
        }
    }

    public void removeStudent(int id){
        database.remove(getStudentById(id));
        setDirty(true);
    }

    public Student getStudentById(int id){
        for(Student s : database){
            if(s.id == id){
                return s;
            }
        }
        return null;
    }

    public boolean isDirty(){
        return isDirty;
    }

    public void setDirty(boolean dirty){
        this.isDirty = dirty;
    }

    public boolean commit(DBConnection dbConnection){
        if(!isDirty){
            return true;
        }
        boolean result = true;

        for (Student s : database){
            if(dbConnection.commitStudent(s) != 0){
                result = false;
            }
        }

        setDirty(false);

        return result;
    }
}