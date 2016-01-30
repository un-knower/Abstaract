package inter;

import inter.CLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import study_test.Search;
import wordsimilarity.WordSimilarity;



public class CandidateAnswer {
	// 停用词词表
	public static final String negativeTable = "D:" + File.separator + "java"
			+ File.separator + "srcFile" + File.separator + "NegativeTable.txt";
	public static final String srcFile = "D:" + File.separator + "java" + File.separator
			+ "srcFile" + File.separator + "question.txt";
	 static String destFile="FinalAnswer.txt";
//	 static String destFile="D:\\java\\ans\\FinalAnswer.txt";
//	 static String similiarAns ="similiar.txt";
	 static String similiarAns ="similiarAns.txt";
	 public static void main(String[] args) {

	 
	/* new SearchContent("D:/java/infoFile",//content.txt
				"D:/java/destFile", "txt").startSearchContent();*/
		
	 new CandidateAnswer(srcFile,similiarAns,destFile);
	
	 }
   /**
    * 
    * @param srcFile   待分词文件
    * @param destFile  分词后存放文件   
    * @param number    选项基数
    */
	public CandidateAnswer(String srcFile, String simiAnswer,String destFile) {
//		public void CandidateAnswer(String srcFile, String destFile,int number) {
		try {
			// 读取原文件和停用词表
			BufferedReader srcFileBr = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(new File(srcFile))));
			BufferedReader simiAnswerBr = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(new File(simiAnswer))));
			BufferedReader negitiveFileBr = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							negativeTable))));
			BufferedWriter destFileBw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(
							destFile)), "GBK"));	
		
			// 用来存放停用词的集合
			ArrayList<String> negitiveArry = new ArrayList<String>();

			// 初如化停用词集
			String stopWord = null;
			while((stopWord=negitiveFileBr.readLine())!=null){
				negitiveArry.add(stopWord);
				
			}
			negitiveFileBr.close();
//			System.out.println("negitiveArry:"+negitiveArry.size());
//			for(int i=0;i<negitiveArry.size();i++){
//				System.out.println("negitiveArr:"+negitiveArry.get(i));
//			}
			int init_flag = CLibrary.Instance.NLPIR_Init("",1, "0");//0 GBK ；1 utf-8
			String nativeBytes = null;

			if (0 == init_flag) {
				nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
				System.err.println("初始化失败！fail reason is "+nativeBytes);
				return;
			}
			//候选答案关键词提取
			/*	String similiarWords = CLibrary.Instance.NLPIR_GetFileKeyWords(similiarAns1s, nMaxKeyLimit, false);
				String[] AnswerWords = similiarWords.split("#");
				System.out.println("候选答案关键词："+Arrays.toString(AnswerWords));*/
			
			/**
			 * 读取候选答案文件加入到数组中
			 */
		ArrayList<String> sentenList = new ArrayList<String>();
		
		Search brSen ;
		try {
			brSen= new Search(new FileReader(simiAnswer));
			String sentence=null;	
			while ((sentence = brSen.readpara()) != null){
				sentenList.add(sentence.trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		//simiAnswerBr.close();
		//测试后选句子列表
//	System.out.println("sentenList.size"+sentenList.size());
//		for (int i = 0; i < sentenList.size(); i++) {
//			System.out.println("sentenList"+sentenList.get(i));
//		}
//		
		
		/**
		 * 判断疑问词中
		 */
		//System.out.println("sen"+sentenList.size());
			for (int i = 0; i < sentenList.size(); i++) {
			//	System.out.println("sentenList.size"+sentenList.size()+sentenList.get(i));
				for(int index=0;index<negitiveArry.size();index++){
				//	System.out.println("negitiveArry:"+negitiveArry.size()+negitiveArry.get(index));
				if(sentenList.get(i).contains(negitiveArry.get(index))){
							
					sentenList.remove(i);
					i--;
					break;
				}
			}
			}
			/**
			 * 处理问题核心代码
			 */
			ArrayList<String> questionList = new ArrayList<String>();
			String question;	
			while((question=srcFileBr.readLine())!=null){
				questionList.add(question);
				
			}	
			srcFileBr.close();
			for(int i=0;i<questionList.size();i++){
				System.out.println("questionList:"+questionList.get(i));
			}
			/**
			 * 计算相似度代码
			 */
			// SemanticSimilarity similarity = SemanticSimilarity.getInstance();
			
			// 将过滤后的文本信息写入到指定文件中，将sentenList加入到答案集中
			//StringBuffer finalStr = new StringBuffer();
			//System.out.println("sen"+sentenList.size());
			
			
			System.out.print("[");
			for (int i = 0; i < sentenList.size(); i++){
				//finalStr.append(sentenList.get(i)).append("\n");
				String str = sentenList.get(i);
				System.out.print(str.substring(0, 2)+" ");
				str = str.substring(2);
				//System.out.println(str);
				destFileBw.write(str);
				//取消注释掉即换行
				//destFileBw.newLine();
			}
			System.out.print("]");

			
			
			
//			destFileBw.write(finalStr.toString());
//			destFileBw.write('\n');
//			destFileBw.newLine();
			// }
			CLibrary.Instance.NLPIR_Exit();
			// 关闭输入流
			destFileBw.close();
			
			
			//sysprintLn();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static int sentcecount;//句子个数
	private void sysprintLn() throws FileNotFoundException {
		
		System.out.println("筛选后的答案句:\n\n"+read(destFile)+"\n\n答案写在FinalAnswer.txt文件中");
		//System.out.println("最终筛选的答案句:\n"+read(destFile)+"\n\n答案写在FinalAnswer.txt文件中");
		
	}
	public static String read(String src) {
		StringBuffer res = new StringBuffer();
		String line = null;
		int count = 0;
		Search br;
		try {
		
		br = new Search(new FileReader(destFile));
		//while ((line = reader.readLine()) != null) {
			while ((line=br.readpara()) != null) {
			res.append(line);
//			res.append(line + "\r\n");
			count++;
		}
		
		//reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("共"+count+"行");
		sentcecount=count;
		return res.toString();
	}

}
