package dodger.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Wu
 */
public class Utils {
    
    public static String loadFileAsString(String path){
        //  Add characters to String
        StringBuilder builder = new StringBuilder();
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            
            //  Until end of file
            while ((line = br.readLine()) != null){
                builder.append(line + "\n");
            }
            
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }
    
    public static void outputStringAsFile(String path, String score){
        //  Outputs score into file
        try {
            FileWriter fw = new FileWriter(path);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(score);
            pw.close(); // close file for writing
        } catch (IOException e) {
        }
    }
    
    //  Parse String as integer
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}
