import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;


public class ClassifierMethods {
	
	public ClassifierMethods(){
		super();
	}
	
	//**************************ten folds crossvalidation with Random(1)*************************************************************/ 
		public String bayesNetFct10folds1random(Instances tr, Attribute classNames){
			BayesNet bN = new BayesNet();
			try {
				bN.buildClassifier(tr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Evaluation ev =null;
			try {
				ev = new Evaluation(tr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			try {
				ev.crossValidateModel(bN,tr,10,new Random(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
			String classifierName="BayesNet"+StartWeka.sepCSV+"10folds-Random(1)"+StartWeka.arffFile;
			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
			return evString;
		}
		
		public String naiveBayesFct10folds1random(Instances tr, Attribute classNames){
			NaiveBayes nB = new NaiveBayes();
			try {
				nB.buildClassifier(tr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Evaluation ev =null;
			try {
				ev = new Evaluation(tr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			try {
				ev.crossValidateModel(nB,tr,10,new Random(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
				
//			Attribute classNames = train.classAttribute();
			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
			
			String classifierName="NaiveBayes"+StartWeka.sepCSV+"10folds-Random(1)"+StartWeka.arffFile;
			String evString = StartWeka.ls+classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
			return evString;
		}
		
		public String j48Fct10folds1random(Instances tr, Attribute classNames){
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
			Evaluation ev =null;
			try {
				ev = new Evaluation(tr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				ev.crossValidateModel(j48,tr,10,new Random(1));			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			classNames = tr.classAttribute();
			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
			String classifierName="J48 Default values "+StartWeka.sepCSV+"10folds-Random(1)"+StartWeka.arffFile;
			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
			return evString;
			

		}
			
		public String smoFct10folds1random(Instances tr, Attribute classNames){
			SMO smo = new SMO();
//			String[] smoOptions = new String[18];
//			smoOptions[0]="-C";//-C <double> The complexity constant C. (default 1)
//			smoOptions[1]="1.0";
//			smoOptions[2]="-L";//-L <double> The tolerance parameter. (default 1.0e-3)
//			smoOptions[3]="0.001";
//			smoOptions[4]="-P";//-P <double>The epsilon for round-off error. (default 1.0e-12)
//			smoOptions[5]="1.0E-12";
//			smoOptions[6]="-N";//-N Whether to 0=normalize/1=standardize/2=neither. (default 0=normalize)
//			smoOptions[7]="0";
//			smoOptions[8]="-V";//V <double> The number of folds for the internal cross-validation. (default -1, use training data)
//			smoOptions[9]="-1"; 
//			smoOptions[10]="-W";//-W <double> The random number seed. (default 1)
//			smoOptions[11]="1";
//			smoOptions[12]="-K";// -K <classname and parameters> The Kernel to use. (default: weka.classifiers.functions.supportVector.PolyKernel)
//			smoOptions[13]="weka.classifiers.functions.supportVector.PolyKernel";
//			smoOptions[14]="-C";//-C <num> The size of the cache (a prime number), 0 for full cache and -1 to turn it off. (default: 250007)
//			smoOptions[15]="250007";
//			smoOptions[16]="-E";//-E <num>  The Exponent to use.(default: 1.0)
//			smoOptions[17]="1.0";
//			smo.setOptions(smoOptions);
			
			try {
				smo.buildClassifier(tr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Evaluation ev =null;
			try {
				ev = new Evaluation(tr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				ev.crossValidateModel(smo,tr,10,new Random(1));			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			classNames = tr.classAttribute();
			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
			String classifierName="SMO Default Values"+StartWeka.sepCSV+"10folds-Random(1)"+StartWeka.arffFile;
			String evString = classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
			return evString;
		}	
		
		public String logisticRegr10folds1random(Instances tr, Attribute classNames){
			
			Logistic lo = new Logistic();
			try {
				lo.buildClassifier(tr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Evaluation ev =null;
			try {
				ev = new Evaluation(tr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				ev.crossValidateModel(lo,tr,10,new Random(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
				
//			Attribute classNames = train.classAttribute();
			String class1 = classNames.value(0)+StartWeka.sepCSV+ev.truePositiveRate(0)+StartWeka.sepCSV+ev.falsePositiveRate(0)+StartWeka.sepCSV+ev.precision(0)+StartWeka.sepCSV+ev.recall(0)+StartWeka.sepCSV+ev.fMeasure(0)+StartWeka.sepCSV+ev.areaUnderROC(0);
			String class2 = classNames.value(1)+StartWeka.sepCSV+ev.truePositiveRate(1)+StartWeka.sepCSV+ev.falsePositiveRate(1)+StartWeka.sepCSV+ev.precision(1)+StartWeka.sepCSV+ev.recall(1)+StartWeka.sepCSV+ev.fMeasure(1)+StartWeka.sepCSV+ev.areaUnderROC(1);
			
			String classifierName="LogisticRegression"+StartWeka.sepCSV+"10folds-Random(1)"+StartWeka.arffFile;
			String evString = StartWeka.ls+classifierName+StartWeka.ls+class1+StartWeka.ls+class2+StartWeka.ls+StartWeka.ls;
			return evString;
		}

}
