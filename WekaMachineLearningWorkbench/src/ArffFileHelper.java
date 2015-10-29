//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import semantic.LIWCDIcParser;
//
//public class ArffFileHelper {
//
//	
//	private static Logger LOG = Logger.getLogger(ModeAnalyserHelper.class);
//	
////	static final File folder = new File("all_files");
//	// static String CSV_FILE_NAME = "test.csv";
//	static String CSV_FILE_NAME = "test.arff";
//	static FileWriter writer;
//	static String FILE_DELIMITER = ",";
//	static String LINE_SEPRATOR = "\n";
//	static Field[] allFields;
////	static String dailyArffFileName = "_dailymode";
//	static String arffExtension = ".arff";
//
//	static String ARFF_TEMPLATE = "arff_template.txt";
//
//	static String fileContent ="";
//	
//	public static String MAIN_ARFF_FOLDER_PATH  = "/var/lib/openshift/5516a1905973cae84300014a/app-root/data/folder/ARFF_FOLDER/";
//	 
//	public static String LIWC_FILE_DIC = MAIN_ARFF_FOLDER_PATH + "LIWC2001_English.dic";
//	
//	
//	// define all variables
//	// default values
//	private static double defaultValueAbout = 1;
//	private static double defaultValueAbove = 1;
//	private static double defaultValueAfter = 1;
//	private static double defaultValueAgain = 1;
//	private static double defaultValueAgainst = 1;
//	private static double defaultValueAll = 1;
//	private static double defaultValueAlmost = 1;
//	private static double defaultValueAlready = 1;
//	private static double defaultValueAlways = 1;
//	private static double defaultValueAm = 1;
//	private static double defaultValueAnd = 1;
//	private static double defaultValueAngry = 1;
//	private static double defaultValueAnother = 1;
//	private static double defaultValueAny = 1;
//	private static double defaultValueAss = 1;
//	private static double defaultValueAt = 1;
//	private static double defaultValueAway = 1;
//	private static double defaultValueAwesome = 1;
//	private static double defaultValueBaby = 1;
//	private static double defaultValueBack = 1;
//	private static double defaultValueBad = 1;
//	private static double defaultValueBe = 1;
//	private static double defaultValueBecause = 1;
//	private static double defaultValueBeen = 1;
//	private static double defaultValueBefore = 1;
//	private static double defaultValueBetween = 1;
//	private static double defaultValueBirthday = 1;
//	private static double defaultValueBut = 1;
//	private static double defaultValueDo = 1;
//	private static double defaultValueDont = 1;
//	private static double defaultValueDown = 1;
//	private static double defaultValueFamily2 = 1;
//	private static double defaultValueFeel2 = 1;
//	private static double defaultValueFight = 1;
//	private static double defaultValueFinal = 1;
//	private static double defaultValueFind = 1;
//	private static double defaultValueFirst = 1;
//	private static double defaultValueFriend2 = 1;
//	private static double defaultValueFun = 1;
//	private static double defaultValueGet = 1;
//	private static double defaultValueGirl = 1;
//	private static double defaultValueGreat = 1;
//	private static double defaultValueHalloween = 1;
//	private static double defaultValueHappy = 1;
//	private static double defaultValueHate = 1;
//	private static double defaultValueHave = 1;
//	private static double defaultValueHe = 1;
//	private static double defaultValueHim = 1;
//	private static double defaultValueHelp = 1;
//	private static double defaultValueHer = 1;
//	private static double defaultValueHoliday = 1;
//	private static double defaultValueHome2 = 1;
//	private static double defaultValueHour = 1;
//	private static double defaultValueI2 = 1;
//	private static double defaultValueJob2 = 1;
//	private static double defaultValueIt = 1;
//	private static double defaultValueJust = 1;
//	private static double defaultValueKnow = 1;
//	private static double defaultValueLate = 1;
//	private static double defaultValueLike = 1;
//	private static double defaultValueLol = 1;
//	private static double defaultValueLost = 1;
//	private static double defaultValueLove = 1;
//	private static double defaultValueMe = 1;
//	private static double defaultValueMiss = 1;
//	private static double defaultValueMoney2 = 1;
//	private static double defaultValueMore = 1;
//	private static double defaultValueMuch = 1;
//	private static double defaultValueNeed = 1;
//	private static double defaultValueNew = 1;
//	private static double defaultValueNo = 1;
//	private static double defaultValueNot = 1;
//	private static double defaultValueOff = 1;
//	private static double defaultValueOnly = 1;
//	private static double defaultValueOutside = 1;
//	private static double defaultValueOver = 1;
//	private static double defaultValuePeople = 1;
//	private static double defaultValuePlease = 1;
//	private static double defaultValueReally = 1;
//	private static double defaultValueShe = 1;
//	private static double defaultValueShit = 1;
//	private static double defaultValueShould = 1;
//	private static double defaultValueSick = 1;
//	private static double defaultValueSleep2 = 1;
////	private static double defaultValueSo = 1;
//	private static double defaultValueSome = 1;
//	private static double defaultValueSomething = 1;
//	private static double defaultValueSometimes = 1;
//	private static double defaultValueSorry = 1;
//	private static double defaultValueStill = 1;
//	private static double defaultValueStop = 1;
//	private static double defaultValueStress = 1;
//	private static double defaultValueStupid = 1;
//	private static double defaultValueSuck = 1;
//	private static double defaultValueTake = 1;
//	private static double defaultValueThank = 1;
//	private static double defaultValueThem = 1;
//	private static double defaultValueThey2 = 1;
//	private static double defaultValueThink = 1;
//	private static double defaultValueThis = 1;
//	private static double defaultValueTime2 = 1;
//	private static double defaultValueTired = 1;
//	private static double defaultValueTo = 1;
//	private static double defaultValueToday = 1;
//	private static double defaultValueToo = 1;
//	private static double defaultValueUp = 1;
//	private static double defaultValueUs = 1;
//	private static double defaultValueWant = 1;
//	private static double defaultValueWatch = 1;
//	private static double defaultValueWe2 = 1;
//	private static double defaultValueWeek = 1;
//	private static double defaultValueWhat = 1;
//	private static double defaultValueWhen = 1;
//	private static double defaultValueWho = 1;
//	private static double defaultValueWhy = 1;
//	private static double defaultValueWill = 1;
//	private static double defaultValueWish = 1;
//	private static double defaultValueWith = 1;
//	private static double defaultValueWithin = 1;
//	private static double defaultValueWork2 = 1;
//	private static double defaultValueYear = 1;
//	private static double defaultValueYes = 1;
//	private static double defaultValueYou2 = 1;
//	
//	private String lastModeStatus;
//	private String createdTempFileName;
//	// end of variables definitions
//	
//	
//	public String addContentToArff(String userName, String text, Long lastServerId){
//		try {
//			readFileContent(MAIN_ARFF_FOLDER_PATH + ARFF_TEMPLATE, 0);
//			addNewContentToArff(text, userName, lastServerId);
//			
//			return createdTempFileName;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//			return "";
//		}
//		
//	}
//	
//	
//	
//	
//	
//	private  void readFileContent(String fileName, int counter)
//			throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader(fileName));
//		try {
//			StringBuilder sb = new StringBuilder();
//			String line = br.readLine();
//
//			while (line != null) {
//				sb.append(line);
//				sb.append(System.lineSeparator());
//				line = br.readLine();
//			}
//			String everything = sb.toString();
//
//			// writeToCsv(sb.toString(), counter);
//
//			fileContent = sb.toString();
////			System.out.println(everything);
//		} finally {
//			br.close();
//		}
//	}
//	
//	private  void addNewContentToArff(String text, String userName,  Long lastServerId){
//		
//		String currentDate = readDailyFile();
//		
//		
//		String directoryUserPath = MAIN_ARFF_FOLDER_PATH + userName + "/";
//		File directoryUser = new File(directoryUserPath);
//		if(!directoryUser.exists()){
//			directoryUser.mkdir();
//			
//		}
//		
//		try {
//
//			writeToCsv(text, userName, lastServerId);
//			    				    
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
//	}
//	
//	
//	private  void writeToCsv(String text, String userName,  Long lastServerId) throws IOException {
//
//		List<Field> privateFields = new ArrayList<>();
//	    allFields = LIWCDIcParser.class.getDeclaredFields();
//	    for (Field field : allFields) {
//	        if (Modifier.isPrivate(field.getModifiers())) {
//	            privateFields.add(field);
//	          
//	        }
//	    }
//
//
//	    LIWCDIcParser test = new LIWCDIcParser();
//	    
//		String newLine = "";
//		
//		File fi = new File(LIWC_FILE_DIC);
//				if(fi.exists()){
//					System.out.println("### " +  LIWC_FILE_DIC + " EXISTING");
//				}else{
//					System.out.println("### " + LIWC_FILE_DIC + " NOT EXISTING");
//				}
//				
//				
//		
//		 URL url = ArffFileHelper.class.getResource("ArffFileHelper.class");
//
//		    String className = url.getFile();
//		    String filePath = className.substring(0,className.indexOf("WEB-INF") + "WEB-INF".length());
//		    System.out.println("WEB-INF Path = " + filePath);
//		    		    		  				    
//		String dicPath = filePath + "/dic_files/LIWC2001_English.dic";
//		
//		System.out.println("#### DIC-Path = " + dicPath);
//		test.setSource(dicPath);
//		
//		dicPath = "/var/lib/openshift/5516a1905973cae84300014a/app-root/data/folder/ARFF_FOLDER/LIWC2001_English.dic";
//		LOG.info("#### DIC-Path 2 = " + dicPath);
//		File fileTest = new File(dicPath);
//		if(fileTest.exists()){
//			LOG.info("EXISTs DIC PATH FILE: " + dicPath  +" Exists");
//		}else{
//			LOG.info("NOT EXISTs DIC PATH FILE: " + dicPath  +" NOT Exists");
//		}
//		LOG.info("#### Text= " + text);
//
//		test.textParser(text);
//
//		for (Field f : allFields) {
//
//			if (f.getName().equalsIgnoreCase("swear")) {
//				newLine += String.valueOf(test.getSwear()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("selfReference")) {
//				newLine += String.valueOf(test.getSelfReference())
//						+ FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("affect")) {
//				newLine += String.valueOf(test.getAffect()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("posemo")) {
//				newLine += String.valueOf(test.getPosemo()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("negemo")) {
//				newLine += String.valueOf(test.getNegemo()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("anx")) {
//				newLine += String.valueOf(test.getAnx()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("anger")) {
//				newLine += String.valueOf(test.getAnger()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("sad")) {
//				newLine += String.valueOf(test.getSad()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("friend")) {
//				newLine += String.valueOf(test.getFriend()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("family")) {
//				newLine += String.valueOf(test.getFamily()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("human")) {
//				newLine += String.valueOf(test.getHuman()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("insight")) {
//				newLine += String.valueOf(test.getInsight()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("past")) {
//				newLine += String.valueOf(test.getPast()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("present")) {
//				newLine += String.valueOf(test.getPresent()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("future")) {
//				newLine += String.valueOf(test.getFuture()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("motion")) {
//				newLine += String.valueOf(test.getMotion()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("job")) {
//				newLine += String.valueOf(test.getJob()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("leisure")) {
//				newLine += String.valueOf(test.getLeisure()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("home")) {
//				newLine += String.valueOf(test.getHome()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("sports")) {
//				newLine += String.valueOf(test.getSports()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("music")) {
//				newLine += String.valueOf(test.getMusic()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("money")) {
//				newLine += String.valueOf(test.getMoney()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("relig")) {
//				newLine += String.valueOf(test.getRelig()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("death")) {
//				newLine += String.valueOf(test.getDeath()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("physcal")) {
//				newLine += String.valueOf(test.getPhyscal()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("body")) {
//				newLine += String.valueOf(test.getBody()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("sexual")) {
//				newLine += String.valueOf(test.getSexual()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("eating")) {
//				newLine += String.valueOf(test.getEating()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("sleep")) {
//				newLine += String.valueOf(test.getSleep()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("groom")) {
//				newLine += String.valueOf(test.getGroom()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("tentat")) {
//				newLine += String.valueOf(test.getTentat()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("certain")) {
//				newLine += String.valueOf(test.getCertain()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("social")) {
//				newLine += String.valueOf(test.getSocial()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("cause")) {
//				newLine += String.valueOf(test.getCause()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("time")) {
//				newLine += String.valueOf(test.getTime()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("space")) {
//				newLine += String.valueOf(test.getSpace()) + FILE_DELIMITER;
//
//			} else if (f.getName().equalsIgnoreCase("wc")) {
//				newLine += String.valueOf(test.getWC()) + FILE_DELIMITER;
//
//			}
//
//		}
//
//		
//		evaluateSentence(text);
//		
//		
//		// Add default values
//		String defaultValues = defaultValueAbout + FILE_DELIMITER;
//		defaultValues += defaultValueAbove + FILE_DELIMITER;
//		defaultValues += defaultValueAfter + FILE_DELIMITER;
//		defaultValues += defaultValueAgain + FILE_DELIMITER;
//		defaultValues += defaultValueAgainst + FILE_DELIMITER;
//		defaultValues += defaultValueAll + FILE_DELIMITER;
//		defaultValues += defaultValueAlmost + FILE_DELIMITER;
//		defaultValues += defaultValueAlready + FILE_DELIMITER;
//		defaultValues += defaultValueAlways + FILE_DELIMITER;
//		defaultValues += defaultValueAm + FILE_DELIMITER;
//		defaultValues += defaultValueAnd + FILE_DELIMITER;
//		defaultValues += defaultValueAngry + FILE_DELIMITER;
//		defaultValues += defaultValueAnother + FILE_DELIMITER;
//		defaultValues += defaultValueAny + FILE_DELIMITER;
//		defaultValues += defaultValueAss + FILE_DELIMITER;
//		defaultValues += defaultValueAt + FILE_DELIMITER;
//		defaultValues += defaultValueAway + FILE_DELIMITER;
//		defaultValues += defaultValueAwesome + FILE_DELIMITER;
//		defaultValues += defaultValueBaby + FILE_DELIMITER;
//		defaultValues += defaultValueBack + FILE_DELIMITER;
//		defaultValues += defaultValueBad + FILE_DELIMITER;
//		defaultValues += defaultValueBe + FILE_DELIMITER;
//		defaultValues += defaultValueBecause + FILE_DELIMITER;
//		defaultValues += defaultValueBeen + FILE_DELIMITER;
//		defaultValues += defaultValueBefore + FILE_DELIMITER;
//		defaultValues += defaultValueBetween + FILE_DELIMITER;
//		defaultValues += defaultValueBirthday + FILE_DELIMITER;
//		defaultValues += defaultValueBut + FILE_DELIMITER;
//		defaultValues += defaultValueDo + FILE_DELIMITER;
//		defaultValues += defaultValueDont + FILE_DELIMITER;
//		defaultValues += defaultValueDown + FILE_DELIMITER;
//		defaultValues += defaultValueFamily2 + FILE_DELIMITER;
//		defaultValues += defaultValueFeel2 + FILE_DELIMITER;
//		defaultValues += defaultValueFight + FILE_DELIMITER;
//		defaultValues += defaultValueFinal + FILE_DELIMITER;
//		defaultValues += defaultValueFind + FILE_DELIMITER;
//		defaultValues += defaultValueFirst + FILE_DELIMITER;
//		defaultValues += defaultValueFriend2 + FILE_DELIMITER;
//		defaultValues += defaultValueFun + FILE_DELIMITER;
//		defaultValues += defaultValueGet + FILE_DELIMITER;
//		defaultValues += defaultValueGirl + FILE_DELIMITER;
//		defaultValues += defaultValueGreat + FILE_DELIMITER;
//		defaultValues += defaultValueHalloween + FILE_DELIMITER;
//		defaultValues += defaultValueHappy + FILE_DELIMITER;
//		defaultValues += defaultValueHate + FILE_DELIMITER;
//		defaultValues += defaultValueHave + FILE_DELIMITER;
//		defaultValues += defaultValueHe + FILE_DELIMITER;
//		defaultValues += defaultValueHim + FILE_DELIMITER;
//		defaultValues += defaultValueHelp + FILE_DELIMITER;
//		defaultValues += defaultValueHer + FILE_DELIMITER;
//		defaultValues += defaultValueHoliday + FILE_DELIMITER;
//		defaultValues += defaultValueHome2 + FILE_DELIMITER;
//		defaultValues += defaultValueHour + FILE_DELIMITER;
//		defaultValues += defaultValueI2 + FILE_DELIMITER;
//		defaultValues += defaultValueJob2 + FILE_DELIMITER;
//		defaultValues += defaultValueIt + FILE_DELIMITER;
//		defaultValues += defaultValueJust + FILE_DELIMITER;
//		defaultValues += defaultValueKnow + FILE_DELIMITER;
//		defaultValues += defaultValueLate + FILE_DELIMITER;
//		defaultValues += defaultValueLike + FILE_DELIMITER;
//		defaultValues += defaultValueLol + FILE_DELIMITER;
//		defaultValues += defaultValueLost + FILE_DELIMITER;
//		defaultValues += defaultValueLove + FILE_DELIMITER;
//		defaultValues += defaultValueMe + FILE_DELIMITER;
//		defaultValues += defaultValueMiss + FILE_DELIMITER;
//		defaultValues += defaultValueMoney2 + FILE_DELIMITER;
//		defaultValues += defaultValueMore + FILE_DELIMITER;
//		defaultValues += defaultValueMuch + FILE_DELIMITER;
//		defaultValues += defaultValueNeed + FILE_DELIMITER;
//		defaultValues += defaultValueNew + FILE_DELIMITER;
//		defaultValues += defaultValueNo + FILE_DELIMITER;
//		defaultValues += defaultValueNot + FILE_DELIMITER;
//		defaultValues += defaultValueOff + FILE_DELIMITER;
//		defaultValues += defaultValueOnly + FILE_DELIMITER;
//		defaultValues += defaultValueOutside + FILE_DELIMITER;
//		defaultValues += defaultValueOver + FILE_DELIMITER;
//		defaultValues += defaultValuePeople + FILE_DELIMITER;
//		defaultValues += defaultValuePlease + FILE_DELIMITER;
//		defaultValues += defaultValueReally + FILE_DELIMITER;
//		defaultValues += defaultValueShe + FILE_DELIMITER;
//		defaultValues += defaultValueShit + FILE_DELIMITER;
//		defaultValues += defaultValueShould + FILE_DELIMITER;
//		defaultValues += defaultValueSick + FILE_DELIMITER;
//		defaultValues += defaultValueSleep2 + FILE_DELIMITER;
////		defaultValues += defaultValueSo + FILE_DELIMITER;
//		defaultValues += defaultValueSome + FILE_DELIMITER;
//		defaultValues += defaultValueSomething + FILE_DELIMITER;
//		defaultValues += defaultValueSometimes + FILE_DELIMITER;
//		defaultValues += defaultValueSorry + FILE_DELIMITER;
//		defaultValues += defaultValueStill + FILE_DELIMITER;
//		defaultValues += defaultValueStop + FILE_DELIMITER;
//		defaultValues += defaultValueStress + FILE_DELIMITER;
//		defaultValues += defaultValueStupid + FILE_DELIMITER;
//		defaultValues += defaultValueSuck + FILE_DELIMITER;
//		defaultValues += defaultValueTake + FILE_DELIMITER;
//		defaultValues += defaultValueThank + FILE_DELIMITER;
//		defaultValues += defaultValueThem + FILE_DELIMITER;
//		defaultValues += defaultValueThey2 + FILE_DELIMITER;
//		defaultValues += defaultValueThink + FILE_DELIMITER;
//		defaultValues += defaultValueThis + FILE_DELIMITER;
//		defaultValues += defaultValueTime2 + FILE_DELIMITER;
//		defaultValues += defaultValueTired + FILE_DELIMITER;
//		defaultValues += defaultValueTo + FILE_DELIMITER;
//		defaultValues += defaultValueToday + FILE_DELIMITER;
//		defaultValues += defaultValueToo + FILE_DELIMITER;
//		defaultValues += defaultValueUp + FILE_DELIMITER;
//		defaultValues += defaultValueUs + FILE_DELIMITER;
//		defaultValues += defaultValueWant + FILE_DELIMITER;
//		defaultValues += defaultValueWatch + FILE_DELIMITER;
//		defaultValues += defaultValueWe2 + FILE_DELIMITER;
//		defaultValues += defaultValueWeek + FILE_DELIMITER;
//		defaultValues += defaultValueWhat + FILE_DELIMITER;
//		defaultValues += defaultValueWhen + FILE_DELIMITER;
//		defaultValues += defaultValueWho + FILE_DELIMITER;
//		defaultValues += defaultValueWhy + FILE_DELIMITER;
//		defaultValues += defaultValueWill + FILE_DELIMITER;
//		defaultValues += defaultValueWish + FILE_DELIMITER;
//		defaultValues += defaultValueWith + FILE_DELIMITER;
//		defaultValues += defaultValueWithin + FILE_DELIMITER;
//		defaultValues += defaultValueWork2 + FILE_DELIMITER;
//		defaultValues += defaultValueYear + FILE_DELIMITER;
//		defaultValues += defaultValueYes + FILE_DELIMITER;
//		defaultValues += defaultValueYou2 + FILE_DELIMITER;
//
//		
//		// end of default values 
//		
//		
//		newLine += defaultValues;
//		newLine +="?";
//
//		
//		createTempFile(newLine, lastServerId, userName);
//	}
//	
//	private   void createTempFile(String evaluatedText, Long lastServerId, String userName){
//		
//		
//		String directoryUserTempPath = MAIN_ARFF_FOLDER_PATH + userName;
//		File directoryUserTemp = new File(directoryUserTempPath);
//		if(!directoryUserTemp.exists()){
//			directoryUserTemp.mkdir();
//			
//		}
//		
//		String directoryPath =  directoryUserTempPath + "/tmp/";
//		
//		File tempDirectory = new File(directoryPath);
//		if(!tempDirectory.exists()){
//			tempDirectory.mkdir();
//			
//		}
//		
//		String tmpFileName = directoryPath + "tmp_" + lastServerId + "_" + userName;
//		File f = new File(tmpFileName + arffExtension);
//		
//		
//		createdTempFileName = tmpFileName + arffExtension;
//		FileWriter writer;
//		try{
//			if(f.exists()){
//				writer = new FileWriter(f  , true);
//			}else{
//				f.createNewFile();
//				writer = new FileWriter(f  , true);
//				writer.append(fileContent);
//				writer.append(LINE_SEPRATOR);
//				
//				writer.append(evaluatedText);
//				
//				
//				 writer.flush();
//				    writer.close();
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	
//	}
//	
//	
//	private static void evaluateSentence(String sentence){
//		
//		// define all if statments
//		
//		
//		if(!sentence.toLowerCase().contains("about")){
//			defaultValueAbout = 0;
//		}
//		if(!sentence.toLowerCase().contains("above")){
//			defaultValueAbove = 0;
//		}
//		if(!sentence.toLowerCase().contains("after")){
//			defaultValueAfter = 0;
//		}
//		if(!sentence.toLowerCase().contains("again")){
//			defaultValueAgain = 0;
//		}
//		if(!sentence.toLowerCase().contains("against")){
//			defaultValueAgainst = 0;
//		}
//		if(!sentence.toLowerCase().contains("all")){
//			defaultValueAll = 0;
//		}
//		if(!sentence.toLowerCase().contains("almost")){
//			defaultValueAlmost = 0;
//		}
//		if(!sentence.toLowerCase().contains("already")){
//			defaultValueAlready = 0;
//		}
//		if(!sentence.toLowerCase().contains("always")){
//			defaultValueAlways = 0;
//		}
//		if(!sentence.toLowerCase().contains("am")){
//			defaultValueAm = 0;
//		}
//		if(!sentence.toLowerCase().contains("and")){
//			defaultValueAnd = 0;
//		}
//		if(!sentence.toLowerCase().contains("angry")){
//			defaultValueAngry = 0;
//		}
//		if(!sentence.toLowerCase().contains("another")){
//			defaultValueAnother = 0;
//		}
//		if(!sentence.toLowerCase().contains("any")){
//			defaultValueAny = 0;
//		}
//		if(!sentence.toLowerCase().contains("ass")){
//			defaultValueAss = 0;
//		}
//		if(!sentence.toLowerCase().contains("at")){
//			defaultValueAt = 0;
//		}
//		if(!sentence.toLowerCase().contains("away")){
//			defaultValueAway = 0;
//		}
//		if(!sentence.toLowerCase().contains("awesome")){
//			defaultValueAwesome = 0;
//		}
//		if(!sentence.toLowerCase().contains("baby")){
//			defaultValueBaby = 0;
//		}
//		if(!sentence.toLowerCase().contains("back")){
//			defaultValueBack = 0;
//		}
//		if(!sentence.toLowerCase().contains("bad")){
//			defaultValueBad = 0;
//		}
//		if(!sentence.toLowerCase().contains("be")){
//			defaultValueBe = 0;
//		}
//		if(!sentence.toLowerCase().contains("because")){
//			defaultValueBecause = 0;
//		}
//		if(!sentence.toLowerCase().contains("been")){
//			defaultValueBeen = 0;
//		}
//		if(!sentence.toLowerCase().contains("before")){
//			defaultValueBefore = 0;
//		}
//		if(!sentence.toLowerCase().contains("between")){
//			defaultValueBetween = 0;
//		}
//		if(!sentence.toLowerCase().contains("birthday")){
//			defaultValueBirthday = 0;
//		}
//		if(!sentence.toLowerCase().contains("but")){
//			defaultValueBut = 0;
//		}
//		if(!sentence.toLowerCase().contains("do")){
//			defaultValueDo = 0;
//		}
//		if(!sentence.toLowerCase().contains("dont")){
//			defaultValueDont = 0;
//		}
//		if(!sentence.toLowerCase().contains("down")){
//			defaultValueDown = 0;
//		}
//		if(!sentence.toLowerCase().contains("family")){
//			defaultValueFamily2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("feel")){
//			defaultValueFeel2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("fight")){
//			defaultValueFight = 0;
//		}
//		if(!sentence.toLowerCase().contains("final")){
//			defaultValueFinal = 0;
//		}
//		if(!sentence.toLowerCase().contains("find")){
//			defaultValueFind = 0;
//		}
//		if(!sentence.toLowerCase().contains("first")){
//			defaultValueFirst = 0;
//		}
//		if(!sentence.toLowerCase().contains("friend")){
//			defaultValueFriend2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("fun")){
//			defaultValueFun = 0;
//		}
//		if(!sentence.toLowerCase().contains("get")){
//			defaultValueGet = 0;
//		}
//		if(!sentence.toLowerCase().contains("girl")){
//			defaultValueGirl = 0;
//		}
//		if(!sentence.toLowerCase().contains("great")){
//			defaultValueGreat = 0;
//		}
//		if(!sentence.toLowerCase().contains("halloween")){
//			defaultValueHalloween = 0;
//		}
//		if(!sentence.toLowerCase().contains("happy")){
//			defaultValueHappy = 0;
//		}
//		if(!sentence.toLowerCase().contains("hate")){
//			defaultValueHate = 0;
//		}
//		if(!sentence.toLowerCase().contains("have")){
//			defaultValueHave = 0;
//		}
//		if(!sentence.toLowerCase().contains("he")){
//			defaultValueHe = 0;
//		}
//		if(!sentence.toLowerCase().contains("him")){
//			defaultValueHim = 0;
//		}
//		if(!sentence.toLowerCase().contains("help")){
//			defaultValueHelp = 0;
//		}
//		if(!sentence.toLowerCase().contains("her")){
//			defaultValueHer = 0;
//		}
//		if(!sentence.toLowerCase().contains("holiday")){
//			defaultValueHoliday = 0;
//		}
//		if(!sentence.toLowerCase().contains("home")){
//			defaultValueHome2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("hour")){
//			defaultValueHour = 0;
//		}
//		if(!sentence.toLowerCase().contains("i")){
//			defaultValueI2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("job")){
//			defaultValueJob2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("it")){
//			defaultValueIt = 0;
//		}
//		if(!sentence.toLowerCase().contains("just")){
//			defaultValueJust = 0;
//		}
//		if(!sentence.toLowerCase().contains("know")){
//			defaultValueKnow = 0;
//		}
//		if(!sentence.toLowerCase().contains("late")){
//			defaultValueLate = 0;
//		}
//		if(!sentence.toLowerCase().contains("like")){
//			defaultValueLike = 0;
//		}
//		if(!sentence.toLowerCase().contains("lol")){
//			defaultValueLol = 0;
//		}
//		if(!sentence.toLowerCase().contains("lost")){
//			defaultValueLost = 0;
//		}
//		if(!sentence.toLowerCase().contains("love")){
//			defaultValueLove = 0;
//		}
//		if(!sentence.toLowerCase().contains("me")){
//			defaultValueMe = 0;
//		}
//		if(!sentence.toLowerCase().contains("miss")){
//			defaultValueMiss = 0;
//		}
//		if(!sentence.toLowerCase().contains("money")){
//			defaultValueMoney2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("more")){
//			defaultValueMore = 0;
//		}
//		if(!sentence.toLowerCase().contains("much")){
//			defaultValueMuch = 0;
//		}
//		if(!sentence.toLowerCase().contains("need")){
//			defaultValueNeed = 0;
//		}
//		if(!sentence.toLowerCase().contains("new")){
//			defaultValueNew = 0;
//		}
//		if(!sentence.toLowerCase().contains("no")){
//			defaultValueNo = 0;
//		}
//		if(!sentence.toLowerCase().contains("not")){
//			defaultValueNot = 0;
//		}
//		if(!sentence.toLowerCase().contains("off")){
//			defaultValueOff = 0;
//		}
//		if(!sentence.toLowerCase().contains("only")){
//			defaultValueOnly = 0;
//		}
//		if(!sentence.toLowerCase().contains("outside")){
//			defaultValueOutside = 0;
//		}
//		if(!sentence.toLowerCase().contains("over")){
//			defaultValueOver = 0;
//		}
//		if(!sentence.toLowerCase().contains("people")){
//			defaultValuePeople = 0;
//		}
//		if(!sentence.toLowerCase().contains("please")){
//			defaultValuePlease = 0;
//		}
//		if(!sentence.toLowerCase().contains("really")){
//			defaultValueReally = 0;
//		}
//		if(!sentence.toLowerCase().contains("she")){
//			defaultValueShe = 0;
//		}
//		if(!sentence.toLowerCase().contains("shit")){
//			defaultValueShit = 0;
//		}
//		if(!sentence.toLowerCase().contains("should")){
//			defaultValueShould = 0;
//		}
//		if(!sentence.toLowerCase().contains("sick")){
//			defaultValueSick = 0;
//		}
//		if(!sentence.toLowerCase().contains("sleep")){
//			defaultValueSleep2 = 0;
//		}
////		if(!sentence.toLowerCase().contains("so")){
////			defaultValueSo = 0;
////		}
//		if(!sentence.toLowerCase().contains("some")){
//			defaultValueSome = 0;
//		}
//		if(!sentence.toLowerCase().contains("something")){
//			defaultValueSomething = 0;
//		}
//		if(!sentence.toLowerCase().contains("sometimes")){
//			defaultValueSometimes = 0;
//		}
//		if(!sentence.toLowerCase().contains("sorry")){
//			defaultValueSorry = 0;
//		}
//		if(!sentence.toLowerCase().contains("still")){
//			defaultValueStill = 0;
//		}
//		if(!sentence.toLowerCase().contains("stop")){
//			defaultValueStop = 0;
//		}
//		if(!sentence.toLowerCase().contains("stress")){
//			defaultValueStress = 0;
//		}
//		if(!sentence.toLowerCase().contains("stupid")){
//			defaultValueStupid = 0;
//		}
//		if(!sentence.toLowerCase().contains("suck")){
//			defaultValueSuck = 0;
//		}
//		if(!sentence.toLowerCase().contains("take")){
//			defaultValueTake = 0;
//		}
//		if(!sentence.toLowerCase().contains("thank")){
//			defaultValueThank = 0;
//		}
//		if(!sentence.toLowerCase().contains("them")){
//			defaultValueThem = 0;
//		}
//		if(!sentence.toLowerCase().contains("they")){
//			defaultValueThey2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("think")){
//			defaultValueThink = 0;
//		}
//		if(!sentence.toLowerCase().contains("this")){
//			defaultValueThis = 0;
//		}
//		if(!sentence.toLowerCase().contains("time")){
//			defaultValueTime2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("tired")){
//			defaultValueTired = 0;
//		}
//		if(!sentence.toLowerCase().contains("to")){
//			defaultValueTo = 0;
//		}
//		if(!sentence.toLowerCase().contains("today")){
//			defaultValueToday = 0;
//		}
//		if(!sentence.toLowerCase().contains("too")){
//			defaultValueToo = 0;
//		}
//		if(!sentence.toLowerCase().contains("up")){
//			defaultValueUp = 0;
//		}
//		if(!sentence.toLowerCase().contains("us")){
//			defaultValueUs = 0;
//		}
//		if(!sentence.toLowerCase().contains("want")){
//			defaultValueWant = 0;
//		}
//		if(!sentence.toLowerCase().contains("watch")){
//			defaultValueWatch = 0;
//		}
//		if(!sentence.toLowerCase().contains("we")){
//			defaultValueWe2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("week")){
//			defaultValueWeek = 0;
//		}
//		if(!sentence.toLowerCase().contains("what")){
//			defaultValueWhat = 0;
//		}
//		if(!sentence.toLowerCase().contains("when")){
//			defaultValueWhen = 0;
//		}
//		if(!sentence.toLowerCase().contains("who")){
//			defaultValueWho = 0;
//		}
//		if(!sentence.toLowerCase().contains("why")){
//			defaultValueWhy = 0;
//		}
//		if(!sentence.toLowerCase().contains("will")){
//			defaultValueWill = 0;
//		}
//		if(!sentence.toLowerCase().contains("wish")){
//			defaultValueWish = 0;
//		}
//		if(!sentence.toLowerCase().contains("with")){
//			defaultValueWith = 0;
//		}
//		if(!sentence.toLowerCase().contains("within")){
//			defaultValueWithin = 0;
//		}
//		if(!sentence.toLowerCase().contains("work")){
//			defaultValueWork2 = 0;
//		}
//		if(!sentence.toLowerCase().contains("year")){
//			defaultValueYear = 0;
//		}
//		if(!sentence.toLowerCase().contains("yes")){
//			defaultValueYes = 0;
//		}
//		if(!sentence.toLowerCase().contains("you")){
//			defaultValueYou2 = 0;
//		}
//		
//	}
//	
//	
//	public static String readDailyFile() {
//		SimpleDateFormat fm = new SimpleDateFormat("yyyMMdd");
//
//		return fm.format(new Date());
//	}
//	
//}
