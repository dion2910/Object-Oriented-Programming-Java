package CamApp;
import java.io.*;


public class CsvEditMethods 
{

    //CsvFilePaths applicationCsvFilePaths = new CsvFilePaths();

    //targetupdate is column name
    public void updateCamp (String fileToUpdate, String targetUpdate, String update, String campID)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file

            // Find the desired column index
            String[] firstRowData = currentLine.split(",");
            int targetCol = -1; // Initialize to -1 for not found
            int numCol = firstRowData.length;
            while (numCol > 0) 
            {
                numCol--;
                if (firstRowData[numCol].equals(targetUpdate)) 
                {
                    targetCol = numCol; // Update target column index
                    break;
                }
            }

            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated

                // Find the desired row
                boolean rowModified = false;
                if (data[0].equals(campID)) //data[0] is campID column
                {
                    // Modify the desired columns or values when correct creator is requesting to edit
                    data[targetCol] = update;
                    rowModified = true;
                }

                bw.newLine(); // Move to the next line in the new file
                bw.write(rowModified ? String.join(",", data) : currentLine);// Write the modified or original row to the new file
            }
            bw.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updatePassword (String fileToUpdate, String targetUpdate, String update,String userID)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file

            // Find the desired column index
            String[] firstRowData = currentLine.split(",");
            int targetCol = -1; // Initialize to -1 for not found
            int numCol = firstRowData.length;
            while (numCol > 0) 
            {
                numCol--;
                if (firstRowData[numCol].equals(targetUpdate)) 
                {
                    targetCol = numCol; // Update target column index
                    break;
                }
            }

            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated

                // Find the desired row
                boolean rowModified = false;
                if (data[1].equals(userID)) //data[0] is campID column
                {
                    // Modify the desired columns or values when correct creator is requesting to edit
                    data[targetCol] = update;
                    rowModified = true;
                }

                bw.newLine(); // Move to the next line in the new file
                bw.write(rowModified ? String.join(",", data) : currentLine);// Write the modified or original row to the new file
            }
            bw.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteCamp(String fileToUpdate, String campID)//find row and skip it
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file
            bw.newLine();


            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated
                if (!data[0].equals(campID)) //data[0] is campID column
                {
                    bw.write(currentLine);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteAllEnquiries(String fileToUpdate, String campID)//find row and skip it
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file
            bw.newLine();


            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated
                if (!data[0].equals(campID)) //data[0] is campID column
                {
                    bw.write(currentLine);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllSuggestion(String fileToUpdate, String campID)//find row and skip it
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file
            bw.newLine();


            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated
                if (!data[0].equals(campID)) //data[0] is campID column
                {
                    bw.write(currentLine);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAttendeeList (String fileToUpdate)
    {
        File f = new File (fileToUpdate);

        if (f.delete())
            System.out.println("Attendee List Deleted");
        else
        System.out.println("Attendee List Deletion Failed");
    }

    public void deleteCommitteeList (String fileToUpdate)
    {
        File f = new File (fileToUpdate);

        if (f.delete())
            System.out.println("Committee List Deleted");
        else
        System.out.println("Committee List Deletion Failed");
    }

    public void deleteMyEnquiry(String fileToUpdate, String campID, String userID)//find row and skip it
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file
            bw.newLine();


            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated
                if (!data[0].equals(campID) && !data[1].equals(userID)) //data[0] is campID column
                {
                    bw.write(currentLine);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteMySuggestion(String fileToUpdate, String campID, String userID)//find row and skip it
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file
            bw.newLine();


            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated
                if (!data[0].equals(campID) && !data[1].equals(userID)) //data[0] is campID column
                {
                    bw.write(currentLine);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdrawFromCamp(String fileToUpdate, String campID, String userID)//find row and skip it
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file
            bw.newLine();


            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated
                if (!data[1].equals(userID)) //data[1] is userID
                {
                    bw.write(currentLine);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate );
            File tempFile = new File(fileToUpdate  + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replyEnquiry (String fileToUpdate, String targetUpdate, String update, String userID, String campID)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file

            // Find the desired column index
            String[] firstRowData = currentLine.split(",");
            int targetCol = -1; // Initialize to -1 for not found
            int numCol = firstRowData.length;

            while (numCol > 0) 
            {
                numCol--;
                if (firstRowData[numCol].equals(targetUpdate)) 
                {
                    targetCol = numCol; // Update target column index
                    break;
                }
            }

            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated

                // Find the desired row
                boolean rowModified = false;
                if (data[0].equals(campID) && data[1].equals(userID)) //data[0] is campID column
                {
                    // Modify the desired columns or values when correct creator is requesting to edit
                    data[targetCol] = update;
                    rowModified = true;
                }

                bw.newLine(); // Move to the next line in the new file
                bw.write(rowModified ? String.join(",", data) : currentLine); // Write the modified or original row to the new file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editEnquiry (String fileToUpdate, String targetUpdate, String update, String userID, String campID)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file

            // Find the desired column index
            String[] firstRowData = currentLine.split(",");
            int targetCol = -1; // Initialize to -1 for not found
            int numCol = firstRowData.length;

            while (numCol > 0) 
            {
                numCol--;
                if (firstRowData[numCol].equals(targetUpdate)) 
                {
                    targetCol = numCol; // Update target column index
                    break;
                }
            }

            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated

                // Find the desired row
                boolean rowModified = false;
                if (data[0].equals(campID) && data[1].equals(userID)) //data[0] is campID column
                {
                    // Modify the desired columns or values when correct creator is requesting to edit
                    data[targetCol] = update;
                    rowModified = true;
                }

                bw.newLine(); // Move to the next line in the new file
                bw.write(rowModified ? String.join(",", data) : currentLine); // Write the modified or original row to the new file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void editSuggestion (String fileToUpdate, String targetUpdate, String update, String userID, String campID)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file

            // Find the desired column index
            String[] firstRowData = currentLine.split(",");
            int targetCol = -1; // Initialize to -1 for not found
            int numCol = firstRowData.length;

            while (numCol > 0) 
            {
                numCol--;
                if (firstRowData[numCol].equals(targetUpdate)) 
                {
                    targetCol = numCol; // Update target column index
                    break;
                }
            }

            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated

                // Find the desired row
                boolean rowModified = false;
                if (data[0].equals(campID) && data[1].equals(userID)) //data[0] is campID column
                {
                    // Modify the desired columns or values when correct creator is requesting to edit
                    data[targetCol] = update;
                    rowModified = true;
                }

                bw.newLine(); // Move to the next line in the new file
                bw.write(rowModified ? String.join(",", data) : currentLine); // Write the modified or original row to the new file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void approveSuggestion (String fileToUpdate, String targetUpdate, String userID, String campID)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file

            // Find the desired column index
            String[] firstRowData = currentLine.split(",");
            int targetCol = -1; // Initialize to -1 for not found
            int numCol = firstRowData.length;

            while (numCol > 0) 
            {
                numCol--;
                if (firstRowData[numCol].equals(targetUpdate)) 
                {
                    targetCol = numCol; // Update target column index
                    break;
                }
            }

            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated

                // Find the desired row
                boolean rowModified = false;
                if (data[0].equals(campID) && data[1].equals(userID)) //data[0] is campID column
                {
                    // Modify the desired columns or values when correct creator is requesting to edit
                    data[targetCol] = "TRUE";
                    rowModified = true;
                }

                bw.newLine(); // Move to the next line in the new file
                bw.write(rowModified ? String.join(",", data) : currentLine); // Write the modified or original row to the new file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addPoints (String fileToUpdate, String targetUpdate, String update, String userID)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToUpdate));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileToUpdate + ".tmp"))) 
        {

            String currentLine = br.readLine(); // Read the header
            bw.write(currentLine); // Write the header to the new file

            // Find the desired column index
            String[] firstRowData = currentLine.split(",");
            int targetCol = -1; // Initialize to -1 for not found
            int numCol = firstRowData.length;
            while (numCol > 0) 
            {
                numCol--;
                if (firstRowData[numCol].equals(targetUpdate)) 
                {
                    targetCol = numCol; // Update target column index
                    break;
                }
            }

            while ((currentLine = br.readLine()) != null) // Read rows loop
            { 
                String[] data = currentLine.split(","); // Assuming CSV data is comma-separated

                // Find the desired row
                boolean rowModified = false;
                if (data[1].equals(userID)) //data[1] is userID column
                {
                    // Modify the desired columns or values when correct creator is requesting to edit
                    data[targetCol] = update;
                    rowModified = true;
                }

                bw.newLine(); // Move to the next line in the new file
                bw.write(rowModified ? String.join(",", data) : currentLine);// Write the modified or original row to the new file
            }
            bw.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace the original file with the temporary file containing the modifications
        try {
            File originalFile = new File(fileToUpdate);
            File tempFile = new File(fileToUpdate + ".tmp");

            // Delete the original file if exists and rename the temp file to the original file name
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                //System.out.println("CSV file has been updated successfully.");
            } 
            else {
                System.out.println("Failed to update CSV file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
