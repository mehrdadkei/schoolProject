package handlers;

import connection.ConnectionManager;
import students.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static utilities.InputOutput.getInput;
import static utilities.InputOutput.print;


public class DbHandler {

    protected static void insertToDb(Student student) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String command = "INSERT INTO public.students(" +
                "first_name, last_name, address, score, is_deleted, national_code, student_no, year)" +
                "VALUES ('" + student.getFirstName() + "'," + "'" + student.getLastName() + "',"
                + "'" + student.getAddress() + "'," + student.getScore() + "," + false + ","
                + student.getNationalCode() + "," + student.getStudentNo() + "," + student.getYear() + ");";
        statement.execute(command);
        connection.commit();
        statement.close();
        connection.close();
    }




    protected static void updateDb(Student student) throws SQLException, ClassNotFoundException {
    Connection connection = ConnectionManager.getConnection();
    Statement statement = connection.createStatement();
    connection.setAutoCommit(false);
    String command = "UPDATE public.students SET first_name=" +"'"+student.getFirstName()+
            "',"+ " last_name= "+"'"+student.getLastName()+"',"
            + " address= "+"'"+student.getAddress()+"',"+
            " score= "+student.getScore()+","+" is_deleted= "+student.getDeleted()+","+
            " national_code= "+student.getNationalCode()+","+" student_no= "+student.getStudentNo()+","+
            " year = "+student.getYear()+ "WHERE national_code=" + student.getNationalCode();
    statement.executeUpdate(command);
    connection.commit();
    statement.close();
    connection.close();


}

    protected static Student loadByNationalCode(Student student) throws ClassNotFoundException, SQLException {
        print("please enter the student national code");
        Integer nationalCode = Integer.parseInt(getInput());
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String command = "SELECT * FROM public.students where national_code = " + nationalCode + ";";
        ResultSet resultSet = statement.executeQuery(command);
        if (resultSet.next()) {
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            student.setAddress(resultSet.getString("address"));
            student.setNationalCode(resultSet.getInt("national_code"));
            student.setStudentNo(resultSet.getInt("student_no"));
            student.setYear(resultSet.getInt("year"));
            student.setId(resultSet.getInt("id"));
            student.setScore(resultSet.getInt("score"));
            student.setDeleted(resultSet.getBoolean("is_deleted"));
        }
        statement.close();
        connection.close();
        return student;
    }

    protected static void delete(Student student) throws ClassNotFoundException, SQLException {
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String command = "UPDATE public.students SET is_deleted=true WHERE national_code="+student.getNationalCode()+";";
        statement.executeUpdate(command);
        connection.commit();
        statement.close();
        connection.close();
    }

    protected static Integer yearCapacity() throws SQLException, ClassNotFoundException {
        LocalDate date = LocalDate.now();
        Integer count = 0;
        Connection connection = ConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        String command = "SELECT cnt FROM public.capacity where year =" + date.getYear() + ";";
        ResultSet resultSet = statement.executeQuery(command);
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        statement.close();
        connection.close();
        return count;

    }
}
