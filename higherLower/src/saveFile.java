import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class saveFile {
    private int highscore;
    private int totalGuesses;
    private double averageGuesses;
    private File f;
    public saveFile(File f) {this.f = f;}

    public int getHighscore() throws FileNotFoundException {
        String valueStr = getFieldValue("HIGHSCORE");
        
        try {
            int valueInt = Integer.parseInt(valueStr);
            return valueInt;

        } catch (Exception e) {
            System.out.println("Could not find/parse highscore. Returning 100.");
            return 100; }
    }

    public int getTotalGuesses() throws FileNotFoundException {
        String valueStr = getFieldValue("TOTAL_GUESSES");
        
        try {
            int valueInt = Integer.parseInt(valueStr);
            return valueInt;

        } catch (Exception e) {
            System.out.println("Could not find/parse total guesses. Returning 0.");
            return 100; }
    }



    public String getFieldValue(String targetKey) throws FileNotFoundException {
        Scanner fileScanner;
        fileScanner = new Scanner(f);

        while (fileScanner.hasNextLine()) {

            String line = fileScanner.nextLine();
                
            String[] pair = line.split(":");
            String key = pair[0];
            String value = pair [1];

            if (key.equalsIgnoreCase(targetKey)) {
                fileScanner.close();
                return value;
            }
        }
        fileScanner.close();
        System.out.println("Could not find target key:" + targetKey);
        return null;
        
    }
} // FJERN
   /* public String updateFieldValue(String targetKey, String newValue) throws FileNotFoundException {

        }
        fileScanner.close();
        System.out.println("Could not find target key:" + targetKey);
        return null;
        }
      }


}*/
