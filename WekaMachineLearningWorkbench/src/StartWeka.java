import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

















import weka.filters.Filter;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
//import weka.filters.supervised.attribute.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;

public class StartWeka {
	
	static String ls;
	static String spaces;
	static String sepCSV;

	public static void main(String[] args) {
		
		BufferedReader breader=null;
		String fs = File.separator;
		String envPath = System.getProperty("user.dir");
		String arffFile = "groundtruth.arff";
		String filePlace = envPath+fs+"groundtruthData"+fs+arffFile;

		try {
			breader = new BufferedReader(new FileReader(filePlace));		
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
		
//		String ls = System.getProperty("line.separator");
//		String spaces = "          ";
//		String sepCSV =",";
		ls = System.getProperty("line.separator");
		spaces = "          ";
		sepCSV =",";
		String filepath;
		if(args.length<1){
			filepath ="C:"+fs+"Users"+fs+"ramsauerd89cs"+fs+"workspaceGitPrecious"+fs;
		}else{
			filepath = args[0];
		}
		
		String header = "arff file is in: groundtruthData"+fs+arffFile+ls+ls+"class name"+sepCSV+"truePositiveRate"+sepCSV+"falsePositiveRate"+sepCSV+"Precision"+sepCSV+"Recall"+sepCSV+"fMeasure"+sepCSV+"ROC"+ls+ls;
		String classifierName="";
		String class1 = "";
		String class2 = "";
		String class3 = "";
		String class4 = "";
		String evalString = header;
		String testMethod=ls+"training data is used as test data "+ls;
		
		Evaluation eval=null;
		
		try {
			eval = new Evaluation(train);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Attribute classNames = train.classAttribute();
				
		int numFolds = 0;
		int randomSeed = 0;
		boolean isFold = true;
		boolean isTrainingTest = true;

		
//*******************************BAYES NET Default values all 160 attributes*******************************//
		
		evalString += bayesNetFct(eval, train, classNames, "TrainingIsTestData", !isFold, numFolds, randomSeed, isTrainingTest);

//*******************************NAIVE BAYES Default values all 160 attributes*******************************//
		
		evalString += naiveBayesFct(eval, train, classNames, "TrainingIsTestData", !isFold, numFolds, randomSeed, isTrainingTest);
		
//*******************************J48 Default values all 160 attributes*******************************//
		evalString += j48Fct(eval, train, classNames, "TrainingIsTestData", !isFold, numFolds, randomSeed, isTrainingTest);
			
//*******************************SMO Default values all 160 attributes*******************************//
		evalString += smoFct(eval, train, classNames, "TrainingIsTestData", !isFold, numFolds, randomSeed, isTrainingTest);


		
		System.out.println(evalString);
		
		File saveWekaResults = new File(envPath+fs+"results"+fs+"wekaEvalResults.csv");
		
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
			bw.write(evalString);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	public static String bayesNetFct(Evaluation ev, Instances tr, Attribute classNames, String testMethod, boolean crossvalidation, int folds, int randomSeed, boolean trainingIsTest){
		BayesNet bN = new BayesNet();
		try {
			bN.buildClassifier(tr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ev = new Evaluation(tr);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if(trainingIsTest){
				try {
					ev.evaluateModel(bN,tr);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		if(crossvalidation){
			try {
				ev.crossValidateModel(bN,tr,folds,new Random(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String class1 = classNames.value(0)+sepCSV+ev.truePositiveRate(0)+sepCSV+ev.falsePositiveRate(0)+sepCSV+ev.precision(0)+sepCSV+ev.recall(0)+sepCSV+ev.fMeasure(0)+sepCSV+ev.areaUnderROC(0);
		String class2 = classNames.value(1)+sepCSV+ev.truePositiveRate(1)+sepCSV+ev.falsePositiveRate(1)+sepCSV+ev.precision(1)+sepCSV+ev.recall(1)+sepCSV+ev.fMeasure(1)+sepCSV+ev.areaUnderROC(1);
		String class3 = classNames.value(2)+sepCSV+ev.truePositiveRate(2)+sepCSV+ev.falsePositiveRate(2)+sepCSV+ev.precision(2)+sepCSV+ev.recall(2)+sepCSV+ev.fMeasure(2)+sepCSV+ev.areaUnderROC(2);
		String class4 = classNames.value(3)+sepCSV+ev.truePositiveRate(3)+sepCSV+ev.falsePositiveRate(3)+sepCSV+ev.precision(3)+sepCSV+ev.recall(3)+sepCSV+ev.fMeasure(3)+sepCSV+ev.areaUnderROC(3);
		String classifierName="BayesNet Default values all 160 attributes"+testMethod;
		String evString = classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
		return evString;
	}
	
	public static String naiveBayesFct(Evaluation ev, Instances tr, Attribute classNames, String testMethod, boolean crossvalidation, int folds, int randomSeed, boolean trainingIsTest){
		NaiveBayes nB = new NaiveBayes();
		try {
			nB.buildClassifier(tr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		if(trainingIsTest){
			try {
				ev = new Evaluation(tr);
				ev.evaluateModel(nB,tr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(crossvalidation){
			try {
				ev.crossValidateModel(nB,tr,folds,new Random(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
//		Attribute classNames = train.classAttribute();
		String class1 = classNames.value(0)+sepCSV+ev.truePositiveRate(0)+sepCSV+ev.falsePositiveRate(0)+sepCSV+ev.precision(0)+sepCSV+ev.recall(0)+sepCSV+ev.fMeasure(0)+sepCSV+ev.areaUnderROC(0);
		String class2 = classNames.value(1)+sepCSV+ev.truePositiveRate(1)+sepCSV+ev.falsePositiveRate(1)+sepCSV+ev.precision(1)+sepCSV+ev.recall(1)+sepCSV+ev.fMeasure(1)+sepCSV+ev.areaUnderROC(1);
		String class3 = classNames.value(2)+sepCSV+ev.truePositiveRate(2)+sepCSV+ev.falsePositiveRate(2)+sepCSV+ev.precision(2)+sepCSV+ev.recall(2)+sepCSV+ev.fMeasure(2)+sepCSV+ev.areaUnderROC(2);
		String class4 = classNames.value(3)+sepCSV+ev.truePositiveRate(3)+sepCSV+ev.falsePositiveRate(3)+sepCSV+ev.precision(3)+sepCSV+ev.recall(3)+sepCSV+ev.fMeasure(3)+sepCSV+ev.areaUnderROC(3);
		
		String classifierName="NaiveBayes Default Values all 160 attributes"+testMethod;
		String evString = ls+classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
		return evString;
	}
	
	public static String j48Fct(Evaluation ev, Instances tr, Attribute classNames, String testMethod, boolean crossvalidation, int folds, int randomSeed, boolean trainingIsTest){
		J48 j48 = new J48();
		String[] j48Options = new String[4];
		j48Options[0]="-C";
		j48Options[1]="0.25";
		j48Options[2]="-M";
		j48Options[3]="2";
		try {
			j48.setOptions(j48Options);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			j48.buildClassifier(tr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		if(trainingIsTest){
			try {
				ev = new Evaluation(tr);
				ev.evaluateModel(j48,tr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(crossvalidation){
			try {
				ev.crossValidateModel(j48,tr,folds,new Random(1));			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		classNames = tr.classAttribute();
		String class1 = classNames.value(0)+sepCSV+ev.truePositiveRate(0)+sepCSV+ev.falsePositiveRate(0)+sepCSV+ev.precision(0)+sepCSV+ev.recall(0)+sepCSV+ev.fMeasure(0)+sepCSV+ev.areaUnderROC(0);
		String class2 = classNames.value(1)+sepCSV+ev.truePositiveRate(1)+sepCSV+ev.falsePositiveRate(1)+sepCSV+ev.precision(1)+sepCSV+ev.recall(1)+sepCSV+ev.fMeasure(1)+sepCSV+ev.areaUnderROC(1);
		String class3 = classNames.value(2)+sepCSV+ev.truePositiveRate(2)+sepCSV+ev.falsePositiveRate(2)+sepCSV+ev.precision(2)+sepCSV+ev.recall(2)+sepCSV+ev.fMeasure(2)+sepCSV+ev.areaUnderROC(2);
		String class4 = classNames.value(3)+sepCSV+ev.truePositiveRate(3)+sepCSV+ev.falsePositiveRate(3)+sepCSV+ev.precision(3)+sepCSV+ev.recall(3)+sepCSV+ev.fMeasure(3)+sepCSV+ev.areaUnderROC(3);
		String classifierName="J48 Default values all 160 attributes"+testMethod;
		String evString = classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
		return evString;
		

	}
		
	public static String smoFct(Evaluation ev, Instances tr, Attribute classNames, String testMethod, boolean crossvalidation, int folds, int randomSeed, boolean trainingIsTest){
		SMO smo = new SMO();
//		String[] smoOptions = new String[18];
//		smoOptions[0]="-C";//-C <double> The complexity constant C. (default 1)
//		smoOptions[1]="1.0";
//		smoOptions[2]="-L";//-L <double> The tolerance parameter. (default 1.0e-3)
//		smoOptions[3]="0.001";
//		smoOptions[4]="-P";//-P <double>The epsilon for round-off error. (default 1.0e-12)
//		smoOptions[5]="1.0E-12";
//		smoOptions[6]="-N";//-N Whether to 0=normalize/1=standardize/2=neither. (default 0=normalize)
//		smoOptions[7]="0";
//		smoOptions[8]="-V";//V <double> The number of folds for the internal cross-validation. (default -1, use training data)
//		smoOptions[9]="-1"; 
//		smoOptions[10]="-W";//-W <double> The random number seed. (default 1)
//		smoOptions[11]="1";
//		smoOptions[12]="-K";// -K <classname and parameters> The Kernel to use. (default: weka.classifiers.functions.supportVector.PolyKernel)
//		smoOptions[13]="weka.classifiers.functions.supportVector.PolyKernel";
//		smoOptions[14]="-C";//-C <num> The size of the cache (a prime number), 0 for full cache and -1 to turn it off. (default: 250007)
//		smoOptions[15]="250007";
//		smoOptions[16]="-E";//-E <num>  The Exponent to use.(default: 1.0)
//		smoOptions[17]="1.0";
//		smo.setOptions(smoOptions);
		
		try {
			smo.buildClassifier(tr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(trainingIsTest){
			try {
				ev = new Evaluation(tr);
				ev.evaluateModel(smo,tr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(crossvalidation){
			try {
				ev.crossValidateModel(smo,tr,folds,new Random(1));			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		classNames = tr.classAttribute();
		String class1 = classNames.value(0)+sepCSV+ev.truePositiveRate(0)+sepCSV+ev.falsePositiveRate(0)+sepCSV+ev.precision(0)+sepCSV+ev.recall(0)+sepCSV+ev.fMeasure(0)+sepCSV+ev.areaUnderROC(0);
		String class2 = classNames.value(1)+sepCSV+ev.truePositiveRate(1)+sepCSV+ev.falsePositiveRate(1)+sepCSV+ev.precision(1)+sepCSV+ev.recall(1)+sepCSV+ev.fMeasure(1)+sepCSV+ev.areaUnderROC(1);
		String class3 = classNames.value(2)+sepCSV+ev.truePositiveRate(2)+sepCSV+ev.falsePositiveRate(2)+sepCSV+ev.precision(2)+sepCSV+ev.recall(2)+sepCSV+ev.fMeasure(2)+sepCSV+ev.areaUnderROC(2);
		String class4 = classNames.value(3)+sepCSV+ev.truePositiveRate(3)+sepCSV+ev.falsePositiveRate(3)+sepCSV+ev.precision(3)+sepCSV+ev.recall(3)+sepCSV+ev.fMeasure(3)+sepCSV+ev.areaUnderROC(3);
		String classifierName="SMO Default Values all 160 attributes"+testMethod;
		String evString = classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
		return evString;
	}


}
