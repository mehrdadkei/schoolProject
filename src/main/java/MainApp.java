
import java.sql.SQLException;

import static handlers.StudentHandler.*;
import static utilities.InputOutput.getInput;
import static utilities.InputOutput.print;


public class MainApp {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        while (true) {
            showMenu();

            Integer menu = Integer.parseInt(getInput());
            switch (menu) {
                case 1:
                    registration();
                    break;
                case 2:
                    InquiryCapacity();
                    break;
                case 3:
                    EditStudentInformation();
                    break;
                case 4:
                    CancelRegistration();
                    break;
                case 5:
                    InquiryStudentInformation();
                    break;
                case 0:
                    print("goodbye");
                    return;
            }
        }
    }
    private static void showMenu() {
        print("please select from the menu");
        print("1- registration");
        print("2- Inquiry capacity");
        print("3- Edit student information");
        print("4- Cancel registration ");
        print("5- Inquiry student information ");
        print("0- Exit");
    }




}
