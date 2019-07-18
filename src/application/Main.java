package application;

import io.github.henriquesabino.training.TrainingSet;
import services.MnistReader;

import java.io.File;
import java.io.IOException;

public class Main {
    
    private static TrainingSet trainingSet = null;
    
    public static void main(String[] args) {
        
        loadTrainingSet();
    }
    
    private static void loadTrainingSet() {
        
        System.out.println("Loading training data...");
        
        File trainImages = new File("mnist data set/train-images.idx3-ubyte");
        File trainLabels = new File("mnist data set/train-labels.idx1-ubyte");
        
        try {
            double[][] imageSet = MnistReader.readFile(trainImages);
            double[][] labelSet = MnistReader.readFile(trainLabels);
            
            trainingSet = new TrainingSet(imageSet, labelSet);
            
            System.out.println("Successfully loaded training data!");
            System.out.println("Training set size: " + trainingSet.getSize());
            
        } catch (IOException e) {
            System.err.println("Failed loaded training data!");
            e.printStackTrace();
        }
    }
    
    //temporary method, it will be replaced with a Processing sketch
    private static void loadTestingSet() {
    
    }
}
