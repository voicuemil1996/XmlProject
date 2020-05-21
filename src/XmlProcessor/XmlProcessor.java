
package pss_technical_exercise;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlProcessor {

    
    public static void main(String[] args) {
        
        final File folder = new File("E:\\input");
        List<String> result = new ArrayList<>();
        
        search(".*\\.xml", folder, result);

        for(String filePath : result){

            XmlCreator a = new XmlCreator(new File(filePath));
            a.XML_create();            
        }

    }
    
    //method use for seaching the valid xml files
    public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {
            

            if (f.isFile()) {
                
                if (f.getName().matches(pattern)){
                    
                    //checking if the xml founded file respects the name pattern
                    if (Character.isDigit(f.getName().charAt(f.getName().length() - 5)) 
                        && Character.isDigit(f.getName().charAt(f.getName().length() - 6))){
                        
                        result.add(f.getAbsolutePath());
                    }else{
                        System.out.println(f.getName() + " does not respect the name pattern !!!");
                    }      
                }
            }

        }
    }
    
}
