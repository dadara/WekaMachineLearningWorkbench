/* @author: Daniela Ramsauer daniela.ramsauer.univie.ac.at
 * Mood Classification for Social Media messages (Twitter Tweets, Facebook Posts,...) 
 * programm to test different text classification algorithms on test data sets
 * arg[0]: name of arff test file
 * arg[1]: path to arff test file
 * classification algorithm tested: BayesNet, NaiveBayes, J48, SMO
 * method used 10-fold cross validation 
 * saves csv files with test data: arff file used for testing, classifier names and results (TP,FP, Recall, Precision, F1, ROC)
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
	
	public static String ls;
	public static String spaces;
	public static String sepCSV;
	static String fs;
	static String envPath;
	static String arffFile;
	static String filePlace;
//	static String arffFilePlace;
	static String evalString;
	static ArrayList<String> arffFileNames;
	static ArrayList<String> arffFilePaths;
	static Instances train;

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
		filePlace = envPath+fs+"groundtruthData"+fs;
		ArrayList<String> arffFilePaths = new ArrayList<String>();
		for(int i = 0; i<arffFileNames.size(); i++){
//			System.out.println(arffFileNames.get(i));
			arffFilePaths.add(filePlace+arffFileNames.get(i));
//			System.out.println(arffFilePaths.get(i));
		}
		
		
		ClassifierMethods cm = new ClassifierMethods();
		
/********************************* groundtruth-happy.csv.arff ***********************************/		
		train = readInArffFile(arffFilePaths.get(0));
//		evalString = executeClassifiers(cm, train, arffFileNames.get(0));		
//		saveFile(evalString,"HappyBinaryResults", "BayesFunction-excMultiP-J48-");
		ClassifierRunnable crHappy = new ClassifierRunnable(arffFileNames,arffFilePaths,train,0);
/********************************* groundtruth-sad.csv.arff ***********************************/
		train = readInArffFile(arffFilePaths.get(1));
//		evalString = executeClassifiers(cm, train, arffFileNames.get(1));	
//		saveFile(evalString,"SadBinaryResults", "4BayesFunction-excMultiP-J48-");
		ClassifierRunnable crSad = new ClassifierRunnable(arffFileNames,arffFilePaths,train,1);
/********************************* groundtruth-stress.csv.arff ************************************/
		train = readInArffFile(arffFilePaths.get(2));
//		evalString = executeClassifiers(cm, train, arffFileNames.get(2));	
//		saveFile(evalString,"StressBinaryResults", "4BayesFunction-excMultiP-J48-");	
		ClassifierRunnable crStress = new ClassifierRunnable(arffFileNames,arffFilePaths,train,2);

	}
	
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
	
	public static String executeClassifiers(ClassifierMethods cm, Instances train,  String arffFile){
		
		Attribute classNames = train.classAttribute();
//		evalString = "";
		String header = "arff file is in: groundtruthData"+fs+arffFile+ls+ls+"classifier"+sepCSV+"truePositiveRate"+sepCSV+"falsePositiveRate"+sepCSV+"Precision"+sepCSV+"Recall"+sepCSV+"fMeasure"+sepCSV+"ROC"+ls+ls;
		evalString = header;
//		evalString += cm.bayesNetFct10folds1random(train, classNames);
//		evalString += cm.naiveBayesFct10folds1random(train, classNames);
//		evalString += cm.naiveBayesMultiFct10folds1random(train, classNames);
//		evalString += cm.naiveBayesMultiTextFct10folds1random(train, classNames);
//		
//		evalString += cm.smoFct10folds1random(train, classNames);
//		evalString += cm.logisticRegr10folds1random(train, classNames);
//		evalString += cm.simpleLogisticRegr10folds1random(train, classNames);
//		DOESN'T WORK never finishes, stucks before buildClassifier 
//		System.out.println("before multiPerc");
//		evalString += cm.multiPercFct10folds1random(train, classNames);		
//		evalString += cm.sgdFct10folds1random(train, classNames);
//		evalString += cm.votedPercFct10folds1random(train, classNames);
//		
//		evalString += cm.j48Fct10folds1random(train, classNames);	
//		evalString += cm.stacking(train, classNames);
		
		Classifier classifier = new BayesNet();
		evalString += cm.classify10folds1random(train, classNames, classifier, "BayesNet")+ls;
		classifier = new NaiveBayes();
		evalString += cm.classify10folds1random(train, classNames, classifier, "NaiveBayes")+ls;
		classifier = new NaiveBayesMultinomial();
		evalString += cm.classify10folds1random(train, classNames, classifier, "NaiveBayesMultinomial")+ls;
		classifier = new Logistic();
		evalString += cm.classify10folds1random(train, classNames, classifier, "Logistic")+ls;
		classifier = new SMO();
		evalString += cm.classify10folds1random(train, classNames, classifier, "SMO")+ls;
		classifier = new SimpleLogistic();
		evalString += cm.classify10folds1random(train, classNames, classifier, "SimpleLogistic")+ls;
		classifier = new SGD();
		evalString += cm.classify10folds1random(train, classNames, classifier, "SGD")+ls;
		classifier = new VotedPerceptron();
		evalString += cm.classify10folds1random(train, classNames, classifier, "VotedPerceptron")+ls;
		classifier = new J48();
		evalString += cm.classify10folds1random(train, classNames, classifier, "J48")+ls;
		
		System.out.println(evalString);
		return evalString; 
	}
	
	public static void saveFile(String resultString, String arffFileName, String classifiersUsed){
		File createFolder = new File(envPath+fs+"results"+fs);
		if(!createFolder.isDirectory()){
			createFolder.mkdirs();
		}
		
//		File saveWekaResults = new File(envPath+fs+"results"+fs+"bayesFunctionExcMultiP-J48-"+arffFileName+".csv");
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
	
	
	
	{	
//***********************************classification use training set as test set or to use ten-folds cross validation*********/
//	
//	public static String bayesNetFct(Evaluation ev, Instances tr, Attribute classNames, String testMethod, boolean crossvalidation, int folds, int randomSeed, boolean trainingIsTest){
//		BayesNet bN = new BayesNet();
//		try {
//			bN.buildClassifier(tr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			ev = new Evaluation(tr);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		if(trainingIsTest){
//				try {
//					ev.evaluateModel(bN,tr);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//		}
//		
//		if(crossvalidation){
//			try {
//				ev.crossValidateModel(bN,tr,folds,new Random(randomSeed));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		String class1 = classNames.value(0)+sepCSV+ev.truePositiveRate(0)+sepCSV+ev.falsePositiveRate(0)+sepCSV+ev.precision(0)+sepCSV+ev.recall(0)+sepCSV+ev.fMeasure(0)+sepCSV+ev.areaUnderROC(0);
//		String class2 = classNames.value(1)+sepCSV+ev.truePositiveRate(1)+sepCSV+ev.falsePositiveRate(1)+sepCSV+ev.precision(1)+sepCSV+ev.recall(1)+sepCSV+ev.fMeasure(1)+sepCSV+ev.areaUnderROC(1);
//		String class3 = classNames.value(2)+sepCSV+ev.truePositiveRate(2)+sepCSV+ev.falsePositiveRate(2)+sepCSV+ev.precision(2)+sepCSV+ev.recall(2)+sepCSV+ev.fMeasure(2)+sepCSV+ev.areaUnderROC(2);
//		String class4 = classNames.value(3)+sepCSV+ev.truePositiveRate(3)+sepCSV+ev.falsePositiveRate(3)+sepCSV+ev.precision(3)+sepCSV+ev.recall(3)+sepCSV+ev.fMeasure(3)+sepCSV+ev.areaUnderROC(3);
//		String classifierName="BayesNet Default values all 160 attributes"+testMethod;
//		String evString = classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
//		return evString;
//	}
//	
//	
//	
//	
//	
//	public static String naiveBayesFct(Evaluation ev, Instances tr, Attribute classNames, String testMethod, boolean crossvalidation, int folds, int randomSeed, boolean trainingIsTest){
//		NaiveBayes nB = new NaiveBayes();
//		try {
//			nB.buildClassifier(tr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//
//		if(trainingIsTest){
//			try {
//				ev = new Evaluation(tr);
//				ev.evaluateModel(nB,tr);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if(crossvalidation){
//			try {
//				ev.crossValidateModel(nB,tr,folds,new Random(1));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//			
////		Attribute classNames = train.classAttribute();
//		String class1 = classNames.value(0)+sepCSV+ev.truePositiveRate(0)+sepCSV+ev.falsePositiveRate(0)+sepCSV+ev.precision(0)+sepCSV+ev.recall(0)+sepCSV+ev.fMeasure(0)+sepCSV+ev.areaUnderROC(0);
//		String class2 = classNames.value(1)+sepCSV+ev.truePositiveRate(1)+sepCSV+ev.falsePositiveRate(1)+sepCSV+ev.precision(1)+sepCSV+ev.recall(1)+sepCSV+ev.fMeasure(1)+sepCSV+ev.areaUnderROC(1);
//		String class3 = classNames.value(2)+sepCSV+ev.truePositiveRate(2)+sepCSV+ev.falsePositiveRate(2)+sepCSV+ev.precision(2)+sepCSV+ev.recall(2)+sepCSV+ev.fMeasure(2)+sepCSV+ev.areaUnderROC(2);
//		String class4 = classNames.value(3)+sepCSV+ev.truePositiveRate(3)+sepCSV+ev.falsePositiveRate(3)+sepCSV+ev.precision(3)+sepCSV+ev.recall(3)+sepCSV+ev.fMeasure(3)+sepCSV+ev.areaUnderROC(3);
//		
//		String classifierName="NaiveBayes Default Values all 160 attributes"+testMethod;
//		String evString = ls+classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
//		return evString;
//	}
//	
//	public static String j48Fct(Evaluation ev, Instances tr, Attribute classNames, String testMethod, boolean crossvalidation, int folds, int randomSeed, boolean trainingIsTest){
//		J48 j48 = new J48();
//		String[] j48Options = new String[4];
//		j48Options[0]="-C";
//		j48Options[1]="0.25";
//		j48Options[2]="-M";
//		j48Options[3]="2";
//		try {
//			j48.setOptions(j48Options);
//		} catch (Exception e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		try {
//			j48.buildClassifier(tr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//		
//		if(trainingIsTest){
//			try {
//				ev = new Evaluation(tr);
//				ev.evaluateModel(j48,tr);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if(crossvalidation){
//			try {
//				ev.crossValidateModel(j48,tr,folds,new Random(1));			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		classNames = tr.classAttribute();
//		String class1 = classNames.value(0)+sepCSV+ev.truePositiveRate(0)+sepCSV+ev.falsePositiveRate(0)+sepCSV+ev.precision(0)+sepCSV+ev.recall(0)+sepCSV+ev.fMeasure(0)+sepCSV+ev.areaUnderROC(0);
//		String class2 = classNames.value(1)+sepCSV+ev.truePositiveRate(1)+sepCSV+ev.falsePositiveRate(1)+sepCSV+ev.precision(1)+sepCSV+ev.recall(1)+sepCSV+ev.fMeasure(1)+sepCSV+ev.areaUnderROC(1);
//		String class3 = classNames.value(2)+sepCSV+ev.truePositiveRate(2)+sepCSV+ev.falsePositiveRate(2)+sepCSV+ev.precision(2)+sepCSV+ev.recall(2)+sepCSV+ev.fMeasure(2)+sepCSV+ev.areaUnderROC(2);
//		String class4 = classNames.value(3)+sepCSV+ev.truePositiveRate(3)+sepCSV+ev.falsePositiveRate(3)+sepCSV+ev.precision(3)+sepCSV+ev.recall(3)+sepCSV+ev.fMeasure(3)+sepCSV+ev.areaUnderROC(3);
//		String classifierName="J48 Default values all 160 attributes"+testMethod;
//		String evString = classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
//		return evString;
//		
//
//	}
//		
//	public static String smoFct(Evaluation ev, Instances tr, Attribute classNames, String testMethod, boolean crossvalidation, int folds, int randomSeed, boolean trainingIsTest){
//		SMO smo = new SMO();
////		String[] smoOptions = new String[18];
////		smoOptions[0]="-C";//-C <double> The complexity constant C. (default 1)
////		smoOptions[1]="1.0";
////		smoOptions[2]="-L";//-L <double> The tolerance parameter. (default 1.0e-3)
////		smoOptions[3]="0.001";
////		smoOptions[4]="-P";//-P <double>The epsilon for round-off error. (default 1.0e-12)
////		smoOptions[5]="1.0E-12";
////		smoOptions[6]="-N";//-N Whether to 0=normalize/1=standardize/2=neither. (default 0=normalize)
////		smoOptions[7]="0";
////		smoOptions[8]="-V";//V <double> The number of folds for the internal cross-validation. (default -1, use training data)
////		smoOptions[9]="-1"; 
////		smoOptions[10]="-W";//-W <double> The random number seed. (default 1)
////		smoOptions[11]="1";
////		smoOptions[12]="-K";// -K <classname and parameters> The Kernel to use. (default: weka.classifiers.functions.supportVector.PolyKernel)
////		smoOptions[13]="weka.classifiers.functions.supportVector.PolyKernel";
////		smoOptions[14]="-C";//-C <num> The size of the cache (a prime number), 0 for full cache and -1 to turn it off. (default: 250007)
////		smoOptions[15]="250007";
////		smoOptions[16]="-E";//-E <num>  The Exponent to use.(default: 1.0)
////		smoOptions[17]="1.0";
////		smo.setOptions(smoOptions);
//		
//		try {
//			smo.buildClassifier(tr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		if(trainingIsTest){
//			try {
//				ev = new Evaluation(tr);
//				ev.evaluateModel(smo,tr);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if(crossvalidation){
//			try {
//				ev.crossValidateModel(smo,tr,folds,new Random(1));			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		classNames = tr.classAttribute();
//		String class1 = classNames.value(0)+sepCSV+ev.truePositiveRate(0)+sepCSV+ev.falsePositiveRate(0)+sepCSV+ev.precision(0)+sepCSV+ev.recall(0)+sepCSV+ev.fMeasure(0)+sepCSV+ev.areaUnderROC(0);
//		String class2 = classNames.value(1)+sepCSV+ev.truePositiveRate(1)+sepCSV+ev.falsePositiveRate(1)+sepCSV+ev.precision(1)+sepCSV+ev.recall(1)+sepCSV+ev.fMeasure(1)+sepCSV+ev.areaUnderROC(1);
//		String class3 = classNames.value(2)+sepCSV+ev.truePositiveRate(2)+sepCSV+ev.falsePositiveRate(2)+sepCSV+ev.precision(2)+sepCSV+ev.recall(2)+sepCSV+ev.fMeasure(2)+sepCSV+ev.areaUnderROC(2);
//		String class4 = classNames.value(3)+sepCSV+ev.truePositiveRate(3)+sepCSV+ev.falsePositiveRate(3)+sepCSV+ev.precision(3)+sepCSV+ev.recall(3)+sepCSV+ev.fMeasure(3)+sepCSV+ev.areaUnderROC(3);
//		String classifierName="SMO Default Values all 160 attributes"+testMethod;
//		String evString = classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
//		return evString;
//	}
	}

}
