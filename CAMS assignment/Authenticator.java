package CamApp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Authenticator {

    Authenticator() {

    }

    public User authenticate(String studentFilePath, String staffFilePath) {

        String data[];
        String currentLine;
        ArrayList<String> UserDetails = new ArrayList<String>();
        int colIndex;
        String userType = "";
        String thisUserID = null;
        String thisUserPassword = null;

        Boolean validInput = false;
        do{
            //try read from student file
            try
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter UserID:");
                thisUserID = sc.nextLine();

                System.out.println("Enter Password:");
                thisUserPassword = sc.nextLine();


                FileReader fr = new FileReader(studentFilePath);
                BufferedReader br = new BufferedReader(fr);

                //while not the end of csv file
                while( (currentLine = br.readLine()) != null) {

                    //data is the current line
                    data = currentLine.split(",");

                    //if data column index is the identified student, add row of data to studentDetails
                    if (data[1].equalsIgnoreCase(thisUserID) && data[2].equals(thisUserPassword)) {

                        userType = "student";

                        colIndex = 0;
                        int dataLength = data.length;
                        while ( dataLength != 0) {

                            UserDetails.add(data[colIndex]);
                            colIndex++;
                            dataLength--;

                        }
                        validInput = true;

                    }

                }
                br.close();
            }
            catch (Exception exception)
            {
                System.out.println(exception);
            }

            try
            {
                FileReader fr = new FileReader(staffFilePath);
                BufferedReader br = new BufferedReader(fr);

                //while not the end of csv file
                while( (currentLine = br.readLine()) != null) {

                    //data is the current line
                    data = currentLine.split(",");

                    //if data column index is the identified student, add row of data to studentDetails
                    if (data[1].equalsIgnoreCase(thisUserID) && data[2].equals(thisUserPassword)) {

                        colIndex = 0;
                        int dataLength = data.length;
                        while ( dataLength != 0) {

                            userType = "staff";

                            UserDetails.add(data[colIndex]);
                            colIndex++;
                            dataLength--;

                        }
                        validInput = true;

                    }

                }
                br.close();
            }
            catch (Exception exception)
            {
                System.out.println(exception);
            }
            if (!validInput) {
                System.out.println("Invalid UserID or Password, please try again.");
            }
            
        }while(!validInput);


        User thisUser = new User(UserDetails.get(0), UserDetails.get(1), UserDetails.get(2), UserDetails.get(3), userType);

        return thisUser;
    }

}
