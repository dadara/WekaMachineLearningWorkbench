/* @author: Daniela Ramsauer daniela.ramsauer.univie.ac.at
 * Thread to enable that multiple classifiers are working on different classifications at the same time
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SGD;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.functions.VotedPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;


public class ClassifierRunnable implements Runnable{

	String evalString;
	String ls;
	String spaces;
	String sepCSV;
	String fs;
	String envPath;
	Thread mythread ;
	ArrayList<String> arffFileNames;
	ArrayList<String> arffFilePaths;
	String filePlace;
	Instances instances;
	int actClass;
   /**
    * 
    * @param arfffilenames	arrayList that contains names of the arff File
    * @param arfffilepaths	arrayList that contain paths to groundtruth arffFiles
    * @param inst			labelled training Instances 
    * @param aClass			int defines the class (=label) which is used for binary classification
    */
	ClassifierRunnable(ArrayList<String> arfffilenames, ArrayList<String> arfffilepaths, Instances inst, int aClass)
	{ 
	   this.arffFileNames = arfffilenames; 
	   this.arffFilePaths = arfffilepaths;
	   this.instances = inst;
	   this.actClass = aClass;
	   mythread = new Thread(this, "RunnableThread");
	   System.out.println("thread created" + mythread);
	   mythread.start();
	}
   
	public void run()
	{
	   ClassifierMethods cm = new ClassifierMethods();
	   
	   ls = System.getProperty("line.separator");
	   spaces = "          ";
	   sepCSV =",";
	   fs = File.separator;
	   envPath = System.getProperty("user.dir");
 
	   System.out.println("in THREAD");
       String evalString = executeClassifiers(cm, instances, arffFileNames.get(actClass));	
	   System.out.println("beforeSave");
	   String arffName = arffFileNames.get(actClass);
       saveFile(evalString, arffName.substring(0,  arffName.indexOf("."))+"Results", "BayesFunction-excMultiP-J48-");
     
       System.out.println("mythread run is over" );
   }
   
//	/**
//	 * reads in arff file
//	 * @param 	arffFilePlace	String which contains the whole path to the arff file
//	 * @return	weka Instances which contains all the labelled instances of the arff file
//	 */
//   public Instances readInArffFile(String arffFilePlace){
//		
//		BufferedReader breader=null;
//		breader = null;
//		try {
//			breader = new BufferedReader(new FileReader(arffFilePlace));		
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Instances train=null;
//		try {
//			train = new Instances(breader);
//			train.setClassIndex(train.numAttributes()-1);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			breader.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return train;
//	}
	
   /**
	 * train and evaluate classifiers with 10-fold cross-validation
	 * @param cm 		Instance of ClassifierMethods
	 * @param train		training Instances as input for classifiers
	 * @param arffFile	name of the arff File without file ending
	 * @return			String, which contains all results of used classifiers
	 */
	public String executeClassifiers(ClassifierMethods cm, Instances train,  String arffFile){
		
		Attribute classNames = train.classAttribute();

		String header = "arff file is in: groundtruthData"+fs+arffFile+ls+ls+"classifier"+sepCSV+"truePositiveRate"+sepCSV+"falsePositiveRate"+sepCSV+"Precision"+sepCSV+"Recall"+sepCSV+"fMeasure"+sepCSV+"ROC"+ls+ls;
		evalString = header;

		Classifier classifier = new BayesNet();
//		BayesNet classifier = new BayesNet();
		evalString += cm.classify10folds1random(train, classNames, classifier, "BayesNet")+ls;
//		classifier = new NaiveBayes();
//		evalString += cm.classify10folds1random(train, classNames, classifier, "NaiveBayes")+ls;
//		classifier = new NaiveBayesMultinomial();
//		evalString += cm.classify10folds1random(train, classNames, classifier, "NaiveBayesMultinomial")+ls;
//		classifier = new Logistic();
//		evalString += cm.classify10folds1random(train, classNames, classifier, "Logistic")+ls;
//		classifier = new SMO();
//		evalString += cm.classify10folds1random(train, classNames, classifier, "SMO")+ls;
//		classifier = new SimpleLogistic();
//		evalString += cm.classify10folds1random(train, classNames, classifier, "SimpleLogistic")+ls;
//		classifier = new SGD();
//		evalString += cm.classify10folds1random(train, classNames, classifier, "SGD")+ls;
//		classifier = new VotedPerceptron();
//		evalString += cm.classify10folds1random(train, classNames, classifier, "VotedPerceptron")+ls;
//		classifier = new J48();
//		evalString += cm.classify10folds1random(train, classNames, classifier, "J48")+ls;
		
//		System.out.println(evalString);
		return evalString; 
	}
   
	/**
	 * saves a file with classifier results in csv format
	 * @param resultString 		String which contains the results (truePositiveRate,
	 * 							falsePositiveRate,Precision,Recall,fMeasure,ROC) of the classifier  
	 * @param arffFileName		String which contains the name of the labeled groundtruth arff file, which 
	 * 							was taken as input to evaluate the classifier 
	 * @param classifiersUsed 	String which names the classifier used for evaluation  
	 */
	public void saveFile(String resultString, String arffFileName, String classifiersUsed){
		File createFolder = new File(envPath+fs+"results"+fs);
		if(!createFolder.isDirectory()){
			createFolder.mkdirs();
		}
		
		File saveWekaResults = new File(envPath+fs+"results"+fs+classifiersUsed+arffFileName+".csv");

		if (!saveWekaResults.exists()) {
			try {
				saveWekaResults.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FileWriter fw =null;
		BufferedWriter bw =null;
		try {
			fw = new FileWriter(saveWekaResults.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(resultString);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
