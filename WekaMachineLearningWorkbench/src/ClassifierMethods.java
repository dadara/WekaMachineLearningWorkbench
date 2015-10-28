/* @author: Daniela Ramsauer daniela.ramsauer.univie.ac.at
 * program to evaluate classification algorithms
 * method used: 10-fold cross validation 
 * saves results of the classifiers in csv format: arff file used for testing, classifier names and results (TP,FP, Recall, Precision, F1, ROC)
 * saves classifier.model 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SGD;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.functions.VotedPerceptron;
import weka.classifiers.meta.Stacking;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.Instances;

public class ClassifierMethods {
	
	double truePositiveRate;
	double falsePositiveRate;
	double precision;
	double recall;
	double fMeasure;
	double roc;
	
	public ClassifierMethods(){
		super();
	}
	
	public String classify10folds1random(Instances tr, Attribute classNames, Classifier classifier, String classifierName){
//	public String classify10folds1random(Instances tr, Attribute classNames, BayesNet classifier, String classifierName){

		try {
			classifier.buildClassifier(tr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String fs = File.separator;
//		try {
//			weka.core.SerializationHelper.write(System.getProperty("user.dir")+fs+"results"+fs+"bayesNet.model", classifier);
//		} catch (Exception e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}	
		
		try {
		// serialize model
		 ObjectOutputStream oos = new ObjectOutputStream(
		                            new FileOutputStream(System.getProperty("user.dir")+fs+"results"+fs+"bayesNetJava.model"));
		 oos.writeObject(classifier);
		 oos.flush();
		 oos.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
//		System.out.println("dir: "+System.getProperty("user.dir")+fs+"results"+fs+"bayesNet.model");
		
/********************************read in previously saved model begin**********************************/
		Classifier cls=null;
		try {
//			cls = (Classifier) weka.core.SerializationHelper.read(System.getProperty("user.dir")+fs+"results"+fs+"W7BayesNet.model");
			cls = (Classifier) weka.core.SerializationHelper.read(System.getProperty("user.dir")+fs+"results"+fs+"bayesNetJava.model");
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
/********************************read in previously saved model end**********************************/
	
/********************************use previously saved model for classification begin**********************************/
		BufferedReader breader=null;
		breader = null;
		try {
			breader = new BufferedReader(new FileReader(System.getProperty("user.dir")+fs+"groundtruth"+fs+"groundtruth-happy.csvTest.arff"));		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Instances trainT=null;
		try {
			trainT = new Instances(breader);
			breader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trainT.setClassIndex(trainT.numAttributes()-1);

		int s1=0;  

		//perform your prediction
		double value=0;
		
		for(int i=0; i<trainT.size(); i++){
			try {
				value = cls.classifyInstance(trainT.instance(i));
				//get the name of the class value
				String prediction=trainT.classAttribute().value((int)value); 
//				int predInt = Integer.parseInt(prediction);
				
//				if(!prediction.equalsIgnoreCase(tr.classAttribute().value((int)value))){
					System.out.println("The predicted value of instance"+Integer.toString(i)+": "+prediction+
							" the original classification value"+Integer.toString(i)+": "+tr.classAttribute().value((int)value)); 
//				}
				
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		
		}		
/***************************************use previously saved model  for classification end*************************************/
		
/*********************************evaluate the classifier with 10-fold cross validation begin**********************************/		
		Evaluation ev =null;
		try {
			ev = new Evaluation(tr);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			ev.crossValidateModel(classifier,tr,10,new Random(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		truePositiveRate = ev.weightedTruePositiveRate();
		falsePositiveRate = ev.weightedFalseNegativeRate();
		precision = ev.weightedPrecision();
		recall = ev.weightedRecall();
		fMeasure = ev.weightedFMeasure();
		roc = ev.weightedAreaUnderROC();
		
		String classAvg = truePositiveRate+StartWeka.sepCSV+falsePositiveRate+StartWeka.sepCSV+precision+StartWeka.sepCSV+recall+StartWeka.sepCSV+fMeasure+StartWeka.sepCSV+roc;
		classifierName+=" 10folds-Random(1)";
		String evString = classifierName+StartWeka.sepCSV+classAvg+StartWeka.ls+StartWeka.ls;
/*********************************evaluate the classifier with 10-fold cross validation end**********************************/		
		
		return evString;	
	}

	
	{
///****************************** classifer.bayes********************************************/
//
//	
//	//**************************ten folds crossvalidation with Random(1)*************************************************************/ 
//	/**
//	 * evaluates the training data 'Instances tr' with BayesNet and 10-fold cross validation 
//	 * @param tr			training data
//	 * @param classNames	names of possible labels of the Instances	
//	 * @return				String contains the results of the classifier used for 'Instances tr'		
//	 */
//		public String bayesNetFct10folds1random(Instances tr, Attribute classNames){
//			BayesNet bN = new BayesNet();
//			try {
//				bN.buildClassifier(tr);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//				
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			
//			try {
//				ev.crossValidateModel(bN,tr,10,new Random(1));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			String fs = File.separator;
//			try {
//				weka.core.SerializationHelper.write(System.getProperty("user.dir")+fs+"result"+fs+"bayesNet.model", (Classifier)bN);
//			} catch (Exception e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}	
//			
//			truePositiveRate = (ev.truePositiveRate(0)+ev.truePositiveRate(1))/2;
//			falsePositiveRate = (ev.falsePositiveRate(0)+ev.falsePositiveRate(1))/2;
//			precision = (ev.precision(0)+ev.precision(1))/2;
//			recall = (ev.recall(0)+ev.recall(1))/2;
//			fMeasure = (ev.fMeasure(0)+ev.fMeasure(1))/2;
//			roc = (ev.areaUnderROC(0)+ev.areaUnderROC(1))/2;
//			
//			String classAvg = StartWeka.sepCSV+truePositiveRate+StartWeka.sepCSV+falsePositiveRate+StartWeka.sepCSV+precision+StartWeka.sepCSV+recall+StartWeka.sepCSV+fMeasure+StartWeka.sepCSV+roc;
//			String classifierName="BayesNet"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = classifierName+StartWeka.ls+classAvg+StartWeka.ls+StartWeka.ls;
//
////			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
////			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
////			String classifierName="BayesNet"+StartWeka.sepCSV+"10folds-Random(1)";
////			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		
//		/**
//		 * evaluates the training data 'Instances tr' with naiveBayes and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String naiveBayesFct10folds1random(Instances tr, Attribute classNames){
//			NaiveBayes nB = new NaiveBayes();
//			try {
//				nB.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			
//			try {
//				ev.crossValidateModel(nB,tr,10,new Random(1));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//				
//			truePositiveRate = (ev.truePositiveRate(0)+ev.truePositiveRate(1))/2;
//			falsePositiveRate = (ev.falsePositiveRate(0)+ev.falsePositiveRate(1))/2;
//			precision = (ev.precision(0)+ev.precision(1))/2;
//			recall = (ev.recall(0)+ev.recall(1))/2;
//			fMeasure = (ev.fMeasure(0)+ev.fMeasure(1))/2;
//			roc = (ev.areaUnderROC(0)+ev.areaUnderROC(1))/2;
//			
//			String classAvg = truePositiveRate+StartWeka.sepCSV+falsePositiveRate+StartWeka.sepCSV+precision+StartWeka.sepCSV+recall+StartWeka.sepCSV+fMeasure+StartWeka.sepCSV+roc;
//			String classifierName="NaiveBayes"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = classifierName+StartWeka.ls+classAvg+StartWeka.ls+StartWeka.ls;
//			
////			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
////			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);		
////			String classifierName="NaiveBayes"+StartWeka.sepCSV+"10folds-Random(1)";
////			String evString = StartWeka.ls+classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		
//		/**
//		 * evaluates the training data 'Instances tr' with naiveBayesMulti and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String naiveBayesMultiFct10folds1random(Instances tr, Attribute classNames){
//			NaiveBayesMultinomial nB = new NaiveBayesMultinomial();
//			try {
//				nB.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			
//			try {
//				ev.crossValidateModel(nB,tr,10,new Random(1));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//				
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			
//			String classifierName="NaiveBayesMultinomial"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = StartWeka.ls+classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		/**
//		 * evaluates the training data 'Instances tr' with naiveBayesMultiText and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String naiveBayesMultiTextFct10folds1random(Instances tr, Attribute classNames){
//			NaiveBayesMultinomialText nB = new NaiveBayesMultinomialText();
//			try {
//				nB.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			
//			try {
//				ev.crossValidateModel(nB,tr,10,new Random(1));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//				
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			
//			String classifierName="NaiveBayesMultinomialText"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = StartWeka.ls+classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		
///****************************** classifer.functions********************************************/
//		/**
//		 * evaluates the training data 'Instances tr' with SMO and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String smoFct10folds1random(Instances tr, Attribute classNames){
//			SMO smo = new SMO();
////			String[] smoOptions = new String[18];
////			smoOptions[0]="-C";//-C <double> The complexity constant C. (default 1)
////			smoOptions[1]="1.0";
////			smoOptions[2]="-L";//-L <double> The tolerance parameter. (default 1.0e-3)
////			smoOptions[3]="0.001";
////			smoOptions[4]="-P";//-P <double>The epsilon for round-off error. (default 1.0e-12)
////			smoOptions[5]="1.0E-12";
////			smoOptions[6]="-N";//-N Whether to 0=normalize/1=standardize/2=neither. (default 0=normalize)
////			smoOptions[7]="0";
////			smoOptions[8]="-V";//V <double> The number of folds for the internal cross-validation. (default -1, use training data)
////			smoOptions[9]="-1"; 
////			smoOptions[10]="-W";//-W <double> The random number seed. (default 1)
////			smoOptions[11]="1";
////			smoOptions[12]="-K";// -K <classname and parameters> The Kernel to use. (default: weka.classifiers.functions.supportVector.PolyKernel)
////			smoOptions[13]="weka.classifiers.functions.supportVector.PolyKernel";
////			smoOptions[14]="-C";//-C <num> The size of the cache (a prime number), 0 for full cache and -1 to turn it off. (default: 250007)
////			smoOptions[15]="250007";
////			smoOptions[16]="-E";//-E <num>  The Exponent to use.(default: 1.0)
////			smoOptions[17]="1.0";
////			smo.setOptions(smoOptions);
//			
//			try {
//				smo.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				ev.crossValidateModel(smo,tr,10,new Random(1));			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			classNames = tr.classAttribute();
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			String classifierName="SMO Default Values"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		/**
//		 * evaluates the training data 'Instances tr' with logisticRegression and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String logisticRegr10folds1random(Instances tr, Attribute classNames){
//			
//			Logistic lo = new Logistic();
//			try {
//				lo.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				ev.crossValidateModel(lo,tr,10,new Random(1));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//				
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			
//			String classifierName="LogisticRegression"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = StartWeka.ls+classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		
//		/**
//		 * evaluates the training data 'Instances tr' with simpleLogisticRegression and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String simpleLogisticRegr10folds1random(Instances tr, Attribute classNames){
//			
//			SimpleLogistic slo = new SimpleLogistic();
//			try {
//				slo.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				ev.crossValidateModel(slo,tr,10,new Random(1));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//				
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			
//			String classifierName="SimpleLogisticRegression"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = StartWeka.ls+classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		
//		/**
//		 * evaluates the training data 'Instances tr' with multiPerceptron and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String multiPercFct10folds1random(Instances tr, Attribute classNames){
//			MultilayerPerceptron mlp= new MultilayerPerceptron();
//			System.out.println("before multiPerc in Method");
//
//			try {
//				mlp.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("after buildClassifier in Method");
//
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				ev.crossValidateModel(mlp,tr,10,new Random(1));			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("after crossValidateModel in Method");
//
//			classNames = tr.classAttribute();
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			String classifierName="Logistic Default Values"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		/**
//		 * evaluates the training data 'Instances tr' with sgd and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String sgdFct10folds1random(Instances tr, Attribute classNames){
//			SGD sgd= new SGD();
//
//			try {
//				sgd.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				ev.crossValidateModel(sgd,tr,10,new Random(1));			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			classNames = tr.classAttribute();
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			String classifierName="Logistic Default Values"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		/**
//		 * evaluates the training data 'Instances tr' with votedPerceptron and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String votedPercFct10folds1random(Instances tr, Attribute classNames){
//			VotedPerceptron vp= new VotedPerceptron();
//
//			try {
//				vp.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				ev.crossValidateModel(vp,tr,10,new Random(1));			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			classNames = tr.classAttribute();
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			String classifierName="Logistic Default Values"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//		}
//		
///****************************** classifer.meta********************************************/
//		/**
//		 * evaluates the training data 'Instances tr' with j48 and 10-fold cross validation 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String j48Fct10folds1random(Instances tr, Attribute classNames){
//			J48 j48 = new J48();
//			String[] j48Options = new String[4];
//			j48Options[0]="-C";
//			j48Options[1]="0.25";
//			j48Options[2]="-M";
//			j48Options[3]="2";
//			try {
//				j48.setOptions(j48Options);
//			} catch (Exception e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//
//			try {
//				j48.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				ev.crossValidateModel(j48,tr,10,new Random(1));			
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			classNames = tr.classAttribute();
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			String classifierName="J48 Default values "+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//			
//
//		}
//		
//		/**
//		 * evaluates the training data 'Instances tr' with Stacking of J48, naiveBayes and RandomForest 
//		 * @param tr			training data
//		 * @param classNames	names of possible labels of the Instances	
//		 * @return				String contains the results of the classifier used for 'Instances tr'		
//		 */
//		public String stacking(Instances tr, Attribute classNames){
//			Stacking stacker = new Stacking();
//			stacker.setMetaClassifier(new J48());
//			Classifier[] classifiers = {
//					new J48(),
//					new NaiveBayes(),
//					new RandomForest()
//			};
//			stacker.setClassifiers(classifiers);
//			try {
//				stacker.buildClassifier(tr);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			Evaluation ev =null;
//			try {
//				ev = new Evaluation(tr);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//			try {
//				ev.crossValidateModel(stacker,tr,10,new Random(1));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//				
//			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
//			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
//			
//			String classifierName="StackingJ48NaiveBRandomF"+StartWeka.sepCSV+"10folds-Random(1)";
//			String evString = StartWeka.ls+classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
//			return evString;
//			
//		}
	}
}
