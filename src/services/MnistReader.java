package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

public class MnistReader {
    
    public static double[][] readFile(File file) throws IOException {
        
        FileInputStream scanner = new FileInputStream(file);
        
        //checking the magic number
        int magicNumber = scanner.read() << 24 | scanner.read() << 16 |
                scanner.read() << 8 | scanner.read();
        
        
        if (magicNumber == 2051) {
            int imagesNum = scanner.read() << 24 | scanner.read() << 16 |
                    scanner.read() << 8 | scanner.read();
            
            int numRows = scanner.read() << 24 | scanner.read() << 16 |
                    scanner.read() << 8 | scanner.read();
            
            int numCols = scanner.read() << 24 | scanner.read() << 16 |
                    scanner.read() << 8 | scanner.read();
            
            double[][] set = new double[imagesNum][numRows * numCols];
            
            for (int i = 0; i < set.length; i++) {
                for (int j = 0; j < set[i].length; j++) {
                    //mapping the values to be between 0 and 1
                    set[i][j] = map(scanner.read(), 0, 255, 0, 1);
                }
            }
            
            return set;
        } else if (magicNumber == 2049) {
            int labelsNum = scanner.read() << 24 | scanner.read() << 16 |
                    scanner.read() << 8 | scanner.read();
            
            double[][] set = new double[labelsNum][1];
            
            for (int i = 0; i < set.length; i++) {
                for (int j = 0; j < set[i].length; j++) {
                    //mapping the values to be between 0 and 1
                    set[i][j] = scanner.read();
                }
            }
            
            return set;
            
        } else {
            throw new InvalidParameterException("The file specified is not an IDX file");
        }
    }
    
    private static double map(double x, double min1, double max1, double min2, double max2) {
        // x - min1       r - min1
        //----------- = -------------
        //max1 - min1     max2 - min2
        
        return (x - min1) * (max2 - min2) / (max1 - min1) + min1;
    }
}
