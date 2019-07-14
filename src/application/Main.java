package application;

import services.MnistReader;

import java.io.File;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {
        
        File trainImages = new File("mnist data set/t10k-images.idx3-ubyte");
        
        try {
            double[][] set = MnistReader.readFile(trainImages);
            
            System.out.println(set.length);
            System.out.println(set[0].length);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
