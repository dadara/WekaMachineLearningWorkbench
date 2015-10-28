/** @author: Daniela Ramsauer daniela.ramsauer.univie.ac.at
 * Mood Classification for Social Media messages (Twitter Tweets, Facebook Posts,...) 
 * programm to test different text classification algorithms on test data sets
 * arg[0]-arg[n]: name of arff test file
 * program to evaluate classification algorithms
 * method used 10-fold cross validation 
 * saves results of the classifiers in csv format: arff file used for testing, classifier names and results (TP,FP, Recall, Precision, F1, ROC)
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import weka.filters.Filter;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
//import weka.filters.supervised.attribute.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SGD;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.functions.VotedPerceptron;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;

public class StartWeka {
	
	public static String ls;		// contains line separator symbol for csv files
	public static String spaces;	// contains space symbol
	public static String sepCSV;	// contains the separator symbol for single data elements in csv files 
	static String fs;				// contains file separator
	static String envPath;			// the environment path
	static String arffFile;			// name of the labelled arff file taken as input for classifier
	static String filePlace;		// path to the labelled groundtruth arff file taken as input for classifier
	static String evalString;		// results of the different classifiers to be saved in csv
	static ArrayList<String> arffFileNames;		// names of the labelled groundtruth arff files with file endings
	static ArrayList<String> arffFilePaths;		// path + names: whole path to labelled groundtruth arff file
	static Instances train;			// weka instances taken from labelled groundtruth arff file

	public static void main(String[] args) {
		
		ls = System.getProperty("line.separator");
		spaces = "          ";
		sepCSV =",";
		fs = File.separator;
		
		arffFileNames = new ArrayList<String>(); 
		arffFileNames.add("groundtruth-happy.csv.arff");
		arffFileNames.add("groundtruth-sad.csv.arff");
		arffFileNames.add("groundtruth-stress.csv.arff");	
		
		envPath = System.getProperty("user.dir");
		filePlace = envPath+fs+"groundtruth"+fs;
		ArrayList<String> arffFilePaths = new ArrayList<String>();
		for(int i = 0; i<arffFileNames.size(); i++){
//			System.out.println(arffFileNames.get(i));
			arffFilePaths.add(filePlace+arffFileNames.get(i));
//			System.out.println(arffFilePaths.get(i));
		}
			
		ClassifierMethods cm = new ClassifierMethods();
/********************************* groundtruth-happy.csv.arff ***********************************/		
		train = readInArffFile(arffFilePaths.get(0));
		ClassifierRunnable crHappy = new ClassifierRunnable(arffFileNames,arffFilePaths,train,0);
/********************************* groundtruth-sad.csv.arff ***********************************/
//		train = readInArffFile(arffFilePaths.get(1));
//		ClassifierRunnable crSad = new ClassifierRunnable(arffFileNames,arffFilePaths,train,1);
/********************************* groundtruth-stress.csv.arff ************************************/
//		train = readInArffFile(arffFilePaths.get(2));
//		ClassifierRunnable crStress = new ClassifierRunnable(arffFileNames,arffFilePaths,train,2);

	}
	/**
	 * reads in arff file
	 * @param 	arffFilePlace	String which contains the whole path to the arff file
	 * @return	weka Instances which contains all the labelled instances of the arff file
	 */
	public static Instances readInArffFile(String arffFilePlace){
		
		BufferedReader breader=null;
		breader = null;
		try {
			breader = new BufferedReader(new FileReader(arffFilePlace));		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Instances train=null;
		try {
			train = new Instances(breader);
			train.setClassIndex(train.numAttributes()-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			breader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return train;
	}
}
