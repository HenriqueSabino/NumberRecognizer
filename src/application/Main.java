package application;

import application.screens.Screen;
import io.github.henriquesabino.training.TrainingSet;
import processing.core.PApplet;
import processing.core.PVector;
import services.MnistReader;

import java.io.File;
import java.io.IOException;

public class Main extends PApplet {
    
    private static TrainingSet trainingSet = null;
    private Screen leftScreen, rightScreen, drawingScreen, textOut;
    
    public static void main(String[] args) {
        
        loadTrainingSet();
        PApplet.main("application.Main");
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
    
    @Override
    public void settings() {
        size(560, 560);
        
        leftScreen = new Screen(new PVector(0, 0), new PVector(width / 2, height));
        rightScreen = new Screen(new PVector(width / 2 + 1, 0), new PVector(width, height));
        
        drawingScreen = new Screen(new PVector(0, 0), new PVector(28, 28), leftScreen);
        textOut = new Screen(new PVector(0, 0), new PVector(28, 28), rightScreen);
    }
}
