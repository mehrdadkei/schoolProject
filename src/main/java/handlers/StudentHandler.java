package handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static handlers.DbHandler.*;
import static utilities.InputOutput.getInput;
import static utilities.InputOutput.print;

import connection.ConnectionManager;
import students.Student;

public class StudentHandler {

    public static void registration() throws SQLException, ClassNotFoundException {

        if (InquiryCapacity()<1) {
            print(" capacity is full");
        }
        Student student = new Student();
        print("please enter the first name");
        student.setFirstName(getInput());
        print("please enter the last name");
        student.setLastName(getInput());
        print("please enter the address");
        student.setAddress(getInput());
        print("please enter the national code");
        student.setNationalCode(Integer.parseInt(getInput()));
        print("please enter the score");
        student.setScore(Integer.parseInt(getInput()));
        print("please enter the student number");
        student.setStudentNo(Integer.parseInt(getInput()));
        LocalDate date = LocalDate.now();
        student.setYear(date.getYear());
        student.setDeleted(false);
        insertToDb(student);
        print("the student registered");
    }

    public static Integer InquiryCapacity() throws SQLException, ClassNotFoundException {
        LocalDate date = LocalDate.now();
        Integer capacity = yearCapacity();
        Integer count = 0;
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String command = "SELECT count(*) FROM public.students where is_deleted = false and year=" + date.getYear() + ";";
        ResultSet resultSet = statement.executeQuery(command);
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        statement.close();
        connection.close();
        Integer inquiryCapacity = capacity - count;
        print("capacity =" + inquiryCapacity);
return inquiryCapacity;
    }


    public static void EditStudentInformation() throws SQLException, ClassNotFoundException {
        Student student= new Student();
        loadByNationalCode(student);
        if (student == null){
            print("There is no any student with this national code ");
        }else {
            print("edit the fields if not press the enter for next field");
            print(" first name = " + student.getFirstName());
            String firstName = getInput();
            if (!(firstName.equals(""))){
                student.setFirstName(firstName);
            }
            print(" last name = " + student.getLastName());
            String lastName = getInput();
            if (!(lastName.equals(""))){
                student.setLastName(lastName);
            }
            print(" address = " + student.getAddress());
            String address = getInput();
            if (!(address.equals(""))){
                student.setAddress(address);
            }
            print(" score = " + student.getScore());
            String score = getInput();
            if (!(score.equals(""))){
                student.setScore(Integer.valueOf(score));
            }
            print(" national code = " + student.getNationalCode());
            String nationalCodee = getInput();
            if (!(nationalCodee.equals(""))){
                student.setNationalCode(Integer.valueOf(nationalCodee));
            }
            print(" year = " + student.getYear());
            String year = getInput();
            if (!(year.equals(""))){
                student.setYear(Integer.valueOf(year));
            }
            print(" student number = " + student.getStudentNo());
            String studentNo = getInput();
            if (!(studentNo.equals(""))){
                student.setStudentNo(Integer.valueOf(studentNo));
            }

            print(student.toString());

        }
        updateDb(student);
        print("it is edited successfully");

    }



    public static void CancelRegistration() throws SQLException, ClassNotFoundException {
        Student student=new Student();
        loadByNationalCode(student);
        delete(student);


    }

    public static void InquiryStudentInformation() throws SQLException, ClassNotFoundException {
      Student student=new Student();
        loadByNationalCode(student);
        print(student.toString());

    }







}
