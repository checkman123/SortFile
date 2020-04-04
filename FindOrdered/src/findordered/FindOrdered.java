package findordered;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Sanyapoom Sirijirakarn
 */
public class FindOrdered {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Please enter input file name: ");
        String filename = in.nextLine();
        
        ArrayList<String> readDigitOnly = new ArrayList<String>();
        ArrayList<String> readText = new ArrayList<String>();
        
        try 
        {
            reader = new BufferedReader(new FileReader(filename));
            
            String currLine = reader.readLine();
            
            while(currLine != null)
            {
                //seperate readed number and text
                if(isNumeric(currLine))
                {
                    readDigitOnly.add(currLine);
                }else
                {
                    readText.add(currLine);
                }
                currLine = reader.readLine();
            }
            
            //sort number small to largest
            Collections.sort(readDigitOnly, new Comparator<String> () {
                @Override
            public int compare(String o1, String o2) {
                return new BigDecimal(o1).compareTo(new BigDecimal(o2));
            }
            });
            
            //sort text
            Collections.sort(readText, new Comparator<String> () {
                @Override
            public int compare(String o1, String o2) {
                int smallest = 0;
                int c1, c2;
                int compare = 0;
                
                smallest = ((o1.length() > o2.length()) ? o2.length() : o1.length());
                
                for(int i = 0; i < smallest; i++)
                {
                    c1 = (int) o1.charAt(i);
                    c2 = (int) o2.charAt(i);
                    compare = c1 - c2;
                    
                    //if 0 then kept going else positive is c1 and negative is c2
                    if(compare > 0) 
                    {
                        return c1;
                    }
                    if(compare < 0)
                    {
                        return c2;
                    }
                }
                return 0;
            }
            
            });
            
            //combined array for output
            readDigitOnly.addAll(readText);
            
            System.out.println("Please enter output file name: ");
            String outFileName = in.nextLine();
            
            writer = new BufferedWriter(new FileWriter(outFileName));
            
            //write to Output
            for(String line : readDigitOnly)
            {
                writer.write(line);
                writer.newLine();
            }
        }catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            //Close files
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
                 
                if(writer != null)
                {
                    writer.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    //Check if the string is numeric 
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                    return false;
            }
        }

    return true;
    }
}
