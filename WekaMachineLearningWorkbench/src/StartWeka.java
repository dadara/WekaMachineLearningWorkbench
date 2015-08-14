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
		
		

		
//*******************************BAYES NET Default values all 160 attributes*******************************//
			BayesNet bN = new BayesNet();
			try {
				bN.buildClassifier(train);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				eval = new Evaluation(train);
//				eval.crossValidateModel(bN,train,10,new Random(1));
				eval.evaluateModel(bN,train);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Attribute classNames = train.classAttribute();
//			header = "class name"+sepCSV+"truePositiveRate"+sepCSV+"falsePositiveRate"+sepCSV+"Precision"+sepCSV+"Recall"+sepCSV+"fMeasure"+sepCSV+"ROC"+ls;
			
//			classNames = train.classAttribute();
			class1 = classNames.value(0)+sepCSV+eval.truePositiveRate(0)+sepCSV+eval.falsePositiveRate(0)+sepCSV+eval.precision(0)+sepCSV+eval.recall(0)+sepCSV+eval.fMeasure(0)+sepCSV+eval.areaUnderROC(0);
			class2 = classNames.value(1)+sepCSV+eval.truePositiveRate(1)+sepCSV+eval.falsePositiveRate(1)+sepCSV+eval.precision(1)+sepCSV+eval.recall(1)+sepCSV+eval.fMeasure(1)+sepCSV+eval.areaUnderROC(1);
			class3 = classNames.value(2)+sepCSV+eval.truePositiveRate(2)+sepCSV+eval.falsePositiveRate(2)+sepCSV+eval.precision(2)+sepCSV+eval.recall(2)+sepCSV+eval.fMeasure(2)+sepCSV+eval.areaUnderROC(2);
			class4 = classNames.value(3)+sepCSV+eval.truePositiveRate(3)+sepCSV+eval.falsePositiveRate(3)+sepCSV+eval.precision(3)+sepCSV+eval.recall(3)+sepCSV+eval.fMeasure(3)+sepCSV+eval.areaUnderROC(3);
			classifierName="BayesNet Default values all 160 attributes"+testMethod;
			evalString += classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
		
//		evalString = bayesNetFct(eval, train, classNames, "TrainingIsTestData", false, 0, 0, true);

//*******************************NAIVE BAYES Default values all 160 attributes*******************************//
		
		NaiveBayes nB = new NaiveBayes();
		try {
			nB.buildClassifier(train);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			eval = new Evaluation(train);
//			eval.crossValidateModel(nB,train,10,new Random(1));
			eval.evaluateModel(nB,train);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
//		Attribute classNames = train.classAttribute();
		class1 = classNames.value(0)+sepCSV+eval.truePositiveRate(0)+sepCSV+eval.falsePositiveRate(0)+sepCSV+eval.precision(0)+sepCSV+eval.recall(0)+sepCSV+eval.fMeasure(0)+sepCSV+eval.areaUnderROC(0);
		class2 = classNames.value(1)+sepCSV+eval.truePositiveRate(1)+sepCSV+eval.falsePositiveRate(1)+sepCSV+eval.precision(1)+sepCSV+eval.recall(1)+sepCSV+eval.fMeasure(1)+sepCSV+eval.areaUnderROC(1);
		class3 = classNames.value(2)+sepCSV+eval.truePositiveRate(2)+sepCSV+eval.falsePositiveRate(2)+sepCSV+eval.precision(2)+sepCSV+eval.recall(2)+sepCSV+eval.fMeasure(2)+sepCSV+eval.areaUnderROC(2);
		class4 = classNames.value(3)+sepCSV+eval.truePositiveRate(3)+sepCSV+eval.falsePositiveRate(3)+sepCSV+eval.precision(3)+sepCSV+eval.recall(3)+sepCSV+eval.fMeasure(3)+sepCSV+eval.areaUnderROC(3);
		
		classifierName="NaiveBayes Default Values all 160 attributes"+testMethod;
		evalString += ls+classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
		
		
//*******************************J48 Default values all 160 attributes*******************************//
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
			j48.buildClassifier(train);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			eval = new Evaluation(train);
//			eval.crossValidateModel(j48,train,10,new Random(1));
			eval.evaluateModel(j48,train);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		classNames = train.classAttribute();
		class1 = classNames.value(0)+sepCSV+eval.truePositiveRate(0)+sepCSV+eval.falsePositiveRate(0)+sepCSV+eval.precision(0)+sepCSV+eval.recall(0)+sepCSV+eval.fMeasure(0)+sepCSV+eval.areaUnderROC(0);
		class2 = classNames.value(1)+sepCSV+eval.truePositiveRate(1)+sepCSV+eval.falsePositiveRate(1)+sepCSV+eval.precision(1)+sepCSV+eval.recall(1)+sepCSV+eval.fMeasure(1)+sepCSV+eval.areaUnderROC(1);
		class3 = classNames.value(2)+sepCSV+eval.truePositiveRate(2)+sepCSV+eval.falsePositiveRate(2)+sepCSV+eval.precision(2)+sepCSV+eval.recall(2)+sepCSV+eval.fMeasure(2)+sepCSV+eval.areaUnderROC(2);
		class4 = classNames.value(3)+sepCSV+eval.truePositiveRate(3)+sepCSV+eval.falsePositiveRate(3)+sepCSV+eval.precision(3)+sepCSV+eval.recall(3)+sepCSV+eval.fMeasure(3)+sepCSV+eval.areaUnderROC(3);
		classifierName="J48 Default values all 160 attributes"+testMethod;
		evalString += classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
		
		//*******************************J48 Default values AttributeSelection 33 attributes*******************************//
				
		
//		AttributeSelection attsel = new AttributeSelection();  // package weka.filters.supervised.attribute!
//		InfoGainAttributeEval infoGainEval = new InfoGainAttributeEval();
//		Ranker ranker = new Ranker();
//		ranker.setNumToSelect(-1);
//		ranker.setThreshold(0);
//	
//		attsel.setEvaluator(infoGainEval);
//		attsel.setSearch(ranker);
//		try {
//			attsel.SelectAttributes(train);
//		} catch (Exception e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		// obtain the attribute indices that were selected
//		int[] indices = null;
//		try {
//			indices = attsel.selectedAttributes();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println(Utils.arrayToString(indices));
//		for(int i=0; i<indices.length; i++){
//			System.out.println(train.attribute(indices[i]));
//		}
		
//		AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
//		InfoGainAttributeEval infoGainEval = new InfoGainAttributeEval();
//		
//		Ranker ranker = new Ranker();
//		ranker.setNumToSelect(-1);
//		ranker.setThreshold(0);
//		
//		j48 = new J48();
//		try {
//			j48.setOptions(j48Options);
//		} catch (Exception e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		classifier.setEvaluator(infoGainEval);
//		classifier.setSearch(ranker);
//		classifier.setClassifier(j48);
//
//		try {
//			classifier.buildClassifier(train);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			eval = new Evaluation(train);
//			eval.evaluateModel(classifier,train);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		classNames = train.classAttribute();
//		class1 = classNames.value(0)+sepCSV+eval.truePositiveRate(0)+sepCSV+eval.falsePositiveRate(0)+sepCSV+eval.precision(0)+sepCSV+eval.recall(0)+sepCSV+eval.fMeasure(0);
//		class2 = classNames.value(1)+sepCSV+eval.truePositiveRate(1)+sepCSV+eval.falsePositiveRate(1)+sepCSV+eval.precision(1)+sepCSV+eval.recall(1)+sepCSV+eval.fMeasure(1);
//		class3 = classNames.value(2)+sepCSV+eval.truePositiveRate(2)+sepCSV+eval.falsePositiveRate(2)+sepCSV+eval.precision(2)+sepCSV+eval.recall(2)+sepCSV+eval.fMeasure(2);
//		class4 = classNames.value(3)+sepCSV+eval.truePositiveRate(3)+sepCSV+eval.falsePositiveRate(3)+sepCSV+eval.precision(3)+sepCSV+eval.recall(3)+sepCSV+eval.fMeasure(3);
//		classifierName="J48 Default values AttributeSelection 33 attributes";
//		evalString += classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;

			
		//*******************************SMO Default values all 160 attributes*******************************//

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
			smo.buildClassifier(train);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			eval = new Evaluation(train);
//			eval.crossValidateModel(smo,train,10,new Random(1));
			eval.evaluateModel(smo,train);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		classNames = train.classAttribute();
		class1 = classNames.value(0)+sepCSV+eval.truePositiveRate(0)+sepCSV+eval.falsePositiveRate(0)+sepCSV+eval.precision(0)+sepCSV+eval.recall(0)+sepCSV+eval.fMeasure(0)+sepCSV+eval.areaUnderROC(0);
		class2 = classNames.value(1)+sepCSV+eval.truePositiveRate(1)+sepCSV+eval.falsePositiveRate(1)+sepCSV+eval.precision(1)+sepCSV+eval.recall(1)+sepCSV+eval.fMeasure(1)+sepCSV+eval.areaUnderROC(1);
		class3 = classNames.value(2)+sepCSV+eval.truePositiveRate(2)+sepCSV+eval.falsePositiveRate(2)+sepCSV+eval.precision(2)+sepCSV+eval.recall(2)+sepCSV+eval.fMeasure(2)+sepCSV+eval.areaUnderROC(2);
		class4 = classNames.value(3)+sepCSV+eval.truePositiveRate(3)+sepCSV+eval.falsePositiveRate(3)+sepCSV+eval.precision(3)+sepCSV+eval.recall(3)+sepCSV+eval.fMeasure(3)+sepCSV+eval.areaUnderROC(3);
		classifierName="SMO Default Values all 160 attributes"+testMethod;
		evalString += classifierName+ls+class1+ls+class2+ls+class3+ls+class4+ls+ls;
		
		
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
		}else{
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

}
