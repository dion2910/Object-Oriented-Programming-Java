package CamApp;

public class BooleanParser {
    public static boolean parseCustomBoolean(String strValue) {
        // Convert to lowercase for case-insensitive comparison
        String lowerCaseValue = strValue.toLowerCase();

        // Check if the string represents 'true'
        if (lowerCaseValue.equals("y")) {
            return true;
        }

        // Check if the string represents 'false'
        if (lowerCaseValue.equals("n")) {
            return false;
        }

        // Default to false for any other case
        return false;
    }
}
