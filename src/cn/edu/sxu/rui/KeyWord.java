package cn.edu.sxu.rui;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import study_test.Search;
import wordsimilarity.WordSimilarity;



public class KeyWord {
	// 停用词词表
	public static final String stopWordTable = "StopWordTable.txt";
//	public static final String stopWordTable = "D:" + File.separator + "java"
//			+ File.separator + "srcFile" + File.separator + "StopWordTable.txt";
	public static boolean isContains(String word){
		boolean flag =false;
		BufferedReader StopWordFileBr = null;
		try {
			StopWordFileBr = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							stopWordTable))));
			// 用来存放停用词的集合
			Set<String> stopWordSet = new HashSet<String>();

			// 初如化停用词集
			String stopWord = null;
			while((stopWord=StopWordFileBr.readLine())!=null)
				stopWordSet.add(stopWord);
			if (stopWordSet.contains(word))
				flag=true;
			
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			try {
				StopWordFileBr.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return flag;
		
		
	} 
	 public static void main(String[] args) {
	
	 // 源文件和目的文件
		 String destFile = "D:" + File.separator + "java" + File.separator
					+ "destFile" + File.separator + "问题关键字.txt";
			String srcFile = "D:" + File.separator + "java" + File.separator
					+ "srcFile" + File.separator + "question.txt";
			//new KeyWord(srcFile, srcFile, 1);
			File file=new File("D:\\java\\高考题\\2005.txt");
			String sentence= "作者是否赞同“世界三大戏剧体系”的说法，为什么？";
			BufferedReader bufferedReader;StringBuilder builder = null;
			try {
				bufferedReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));
				String s;builder=new StringBuilder();
				while((s=bufferedReader.readLine())!=null){
					builder.append(s);
				}
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			//System.out.println("问题关键词"+KeyWords(sentence,builder.toString()));
	
	 }
   /**
    * 
    * @param sentence   待分词文件
    * @param destFile  分词后存放文件   
    * @param number    选项基数
    */
	static ArrayList<String> KeyWords(String sentence,String passage) {
		//String[] keySen=null;
		ArrayList<String> questWords =null;
		try {
			// 读取原文件和停用词表

			
			/*BufferedReader StopWordFileBr = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							stopWordTable))));
	
			// 用来存放停用词的集合
			Set<String> stopWordSet = new HashSet<String>();

			// 初如化停用词集
			String stopWord = null;
			while((stopWord=StopWordFileBr.readLine())!=null)
				stopWordSet.add(stopWord);*/

			int init_flag = CLibrary.Instance.NLPIR_Init("",1, "0");//0 GBK ；1 utf-8
			String nativeBytes = null;

			if (0 == init_flag) {
				nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
				System.err.println("初始化失败！fail reason is "+nativeBytes);
				return null;
			}


			/**
			 * 提取文章新词和关键词
			 */
			//文章新词
			int nMaxKeyLimit=200;
			String newWords= CLibrary.Instance.NLPIR_GetNewWords(
					passage, 100,false);//提取新词
			String[] newArtiWords = newWords.split("#");//新词数组
			System.out.println("文章新词:"+Arrays.toString(newArtiWords));
			
			//文章的关键词
			String necissaryWords = CLibrary.Instance.NLPIR_GetKeyWords(passage, nMaxKeyLimit, false);
			String[] neciArtiWords = necissaryWords.split("#");
			System.out.println("文章关键词:"+Arrays.toString(neciArtiWords));
			
			//候选答案关键词提取
			/*String similiarWords = CLibrary.Instance.NLPIR_GetFileKeyWords(similiarAns1s, nMaxKeyLimit, false);
			String[] AnswerWords = similiarWords.split("#");
			System.out.println("候选答案关键词："+Arrays.toString(AnswerWords));
			
			for(int i=0;i<AnswerWords.length;i++){if (stopWordSet.contains(AnswerWords[i])) {
//				if (stopWordSet.contains(resultArrayStr.get(i))) {
				AnswerWords[i]=null;
			}}*/
			/**
			 * 添加新词到用户词典，分词时使用
			 */
			for(String s:newArtiWords)
			  CLibrary.Instance.NLPIR_AddUserWord(s);

			/**
			 *  对句子进行分词
			 */
			byte[] spiltResult = CLibrary.Instance.
					NLPIR_ParagraphProcess(sentence, 1).getBytes();//1词性标注
			String spiltResultStr = new String(spiltResult, 0,
					spiltResult.length, "gbk");
			// 得到分词后的词汇数组，以便后续比较
			String[] resultArray = spiltResultStr.split(" ");
			//System.out.println("分词结果"+Arrays.toString(resultArray));
			System.out.println("\n将问题分词：");
			for (int i = 0; i < resultArray.length; i++) {
				System.out.print("  "+resultArray[i]);
				}
			
			/**
			 * 去掉重复字符串
			 */
			for(int i=0;i< resultArray.length;i++)
			{
				for(int j=i+1;j<resultArray.length;j++){
					if(String.valueOf(resultArray[i]).equals(String.valueOf(resultArray[j])))
						resultArray[j] = "";
				}
			}
			ArrayList<String> resultArrayStr=new ArrayList<String>();
			String regex = "/n$|/v|a";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher ;
			//System.out.println("匹配上的关键词:");
			for (int i = 0; i < resultArray.length; i++) {
				matcher=pattern.matcher(resultArray[i]);
				if(matcher.find()){
					// System.out.println("匹配上的字符"+matcher.group());
					
					resultArrayStr.add(resultArray[i]);
					// System.out.print(" "+resultArray[i]);
				}//else {resultArray[i]="";}
			}
			
			System.out.println("\n问题提取的实词：\n"+resultArrayStr);
			
			/**
			 * * 测试方法过滤停用词
			 */
			
			//resultArray =new String[resultArrayStr.size()];
			
			for (int i = 0; i < resultArrayStr.size(); i++) {//将题干中包含停用词此表，则滞空null
				if (resultArrayStr.get(i)== null||isContains(resultArrayStr.get(i).replaceAll("/[a-z]{1,}", ""))) {
//					if (resultArrayStr.get(i)== null||stopWordSet.contains(resultArrayStr.get(i).replaceAll("/[a-z]{1,}", ""))) {
						resultArrayStr.remove(i);i--;
				}
							
			}
			System.out.println("过滤停用词提取的实词："+resultArrayStr);
			ArrayList<String> resultArrayWord=new ArrayList<String>();
			for(String string:resultArrayStr){
				string=string.replaceAll("/[a-z]{1,}", "");
				resultArrayWord.add(string);
			}
			System.out.println("去掉词性实词:"+resultArrayWord);
			
			 /**
			  * 输出问题关键字
			  */
			
			questWords = new ArrayList<String>();
			/**
			 * 相似度匹配
			 */
			WordSimilarity.loadGlossary();
			
		int count=0;
		for(int k=0;k<resultArrayWord.size();k++){
			questWords.add(resultArrayWord.get(k));
			for(int j=0;j<neciArtiWords.length;j++){		
//				if(neciArtiWords[j]!=null && neciArtiWords[j].length()!=1 &&
//						resultArrayWord.get(k).matches(neciArtiWords[j])){
//					questWords.add(neciArtiWords[j]);//System.out.println("字面匹配关键字："+questNesWords.toString());
//					//System.out.println("字面匹配"+questNesWords);
//					neciArtiWords[j]=null;}
//					else 
						if (neciArtiWords[j]!=null &&!resultArrayWord.get(k).equals(neciArtiWords[j])&&
							resultArrayWord.get(k).length()!=1&&neciArtiWords[j].length()!=1&&
							WordSimilarity.simWord(
							resultArrayWord.get(k),neciArtiWords[j])>=0.90){
					questWords.add(neciArtiWords[j]);
					//questNesWords.add(resultArrayWord.get(k));
					System.out.println("相似度关键字：["+resultArrayWord.get(k)+"] 匹配文章关键词：["+neciArtiWords[j]+"]");
					//System.out.println("测试"+WordSimilarity.simWord("公司","市场"));
					neciArtiWords[j]=null;
					
				}
			}
		}
		
		
		

			CLibrary.Instance.NLPIR_Exit();	
			//StopWordFileBr.close();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questWords;
	}
	
}
