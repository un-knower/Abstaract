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
	// ͣ�ôʴʱ�
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
			// �������ͣ�ôʵļ���
			Set<String> stopWordSet = new HashSet<String>();

			// ���绯ͣ�ôʼ�
			String stopWord = null;
			while((stopWord=StopWordFileBr.readLine())!=null)
				stopWordSet.add(stopWord);
			if (stopWordSet.contains(word))
				flag=true;
			
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			try {
				StopWordFileBr.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return flag;
		
		
	} 
	 public static void main(String[] args) {
	
	 // Դ�ļ���Ŀ���ļ�
		 String destFile = "D:" + File.separator + "java" + File.separator
					+ "destFile" + File.separator + "����ؼ���.txt";
			String srcFile = "D:" + File.separator + "java" + File.separator
					+ "srcFile" + File.separator + "question.txt";
			//new KeyWord(srcFile, srcFile, 1);
			File file=new File("D:\\java\\�߿���\\2005.txt");
			String sentence= "�����Ƿ���ͬ����������Ϸ����ϵ����˵����Ϊʲô��";
			BufferedReader bufferedReader;StringBuilder builder = null;
			try {
				bufferedReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));
				String s;builder=new StringBuilder();
				while((s=bufferedReader.readLine())!=null){
					builder.append(s);
				}
			} catch (FileNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			//System.out.println("����ؼ���"+KeyWords(sentence,builder.toString()));
	
	 }
   /**
    * 
    * @param sentence   ���ִ��ļ�
    * @param destFile  �ִʺ����ļ�   
    * @param number    ѡ�����
    */
	static ArrayList<String> KeyWords(String sentence,String passage) {
		//String[] keySen=null;
		ArrayList<String> questWords =null;
		try {
			// ��ȡԭ�ļ���ͣ�ôʱ�

			
			/*BufferedReader StopWordFileBr = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							stopWordTable))));
	
			// �������ͣ�ôʵļ���
			Set<String> stopWordSet = new HashSet<String>();

			// ���绯ͣ�ôʼ�
			String stopWord = null;
			while((stopWord=StopWordFileBr.readLine())!=null)
				stopWordSet.add(stopWord);*/

			int init_flag = CLibrary.Instance.NLPIR_Init("",1, "0");//0 GBK ��1 utf-8
			String nativeBytes = null;

			if (0 == init_flag) {
				nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
				System.err.println("��ʼ��ʧ�ܣ�fail reason is "+nativeBytes);
				return null;
			}


			/**
			 * ��ȡ�����´ʺ͹ؼ���
			 */
			//�����´�
			int nMaxKeyLimit=200;
			String newWords= CLibrary.Instance.NLPIR_GetNewWords(
					passage, 100,false);//��ȡ�´�
			String[] newArtiWords = newWords.split("#");//�´�����
			System.out.println("�����´�:"+Arrays.toString(newArtiWords));
			
			//���µĹؼ���
			String necissaryWords = CLibrary.Instance.NLPIR_GetKeyWords(passage, nMaxKeyLimit, false);
			String[] neciArtiWords = necissaryWords.split("#");
			System.out.println("���¹ؼ���:"+Arrays.toString(neciArtiWords));
			
			//��ѡ�𰸹ؼ�����ȡ
			/*String similiarWords = CLibrary.Instance.NLPIR_GetFileKeyWords(similiarAns1s, nMaxKeyLimit, false);
			String[] AnswerWords = similiarWords.split("#");
			System.out.println("��ѡ�𰸹ؼ��ʣ�"+Arrays.toString(AnswerWords));
			
			for(int i=0;i<AnswerWords.length;i++){if (stopWordSet.contains(AnswerWords[i])) {
//				if (stopWordSet.contains(resultArrayStr.get(i))) {
				AnswerWords[i]=null;
			}}*/
			/**
			 * �����´ʵ��û��ʵ䣬�ִ�ʱʹ��
			 */
			for(String s:newArtiWords)
			  CLibrary.Instance.NLPIR_AddUserWord(s);

			/**
			 *  �Ծ��ӽ��зִ�
			 */
			byte[] spiltResult = CLibrary.Instance.
					NLPIR_ParagraphProcess(sentence, 1).getBytes();//1���Ա�ע
			String spiltResultStr = new String(spiltResult, 0,
					spiltResult.length, "gbk");
			// �õ��ִʺ�Ĵʻ����飬�Ա�����Ƚ�
			String[] resultArray = spiltResultStr.split(" ");
			//System.out.println("�ִʽ��"+Arrays.toString(resultArray));
			System.out.println("\n������ִʣ�");
			for (int i = 0; i < resultArray.length; i++) {
				System.out.print("  "+resultArray[i]);
				}
			
			/**
			 * ȥ���ظ��ַ���
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
			//System.out.println("ƥ���ϵĹؼ���:");
			for (int i = 0; i < resultArray.length; i++) {
				matcher=pattern.matcher(resultArray[i]);
				if(matcher.find()){
					// System.out.println("ƥ���ϵ��ַ�"+matcher.group());
					
					resultArrayStr.add(resultArray[i]);
					// System.out.print(" "+resultArray[i]);
				}//else {resultArray[i]="";}
			}
			
			System.out.println("\n������ȡ��ʵ�ʣ�\n"+resultArrayStr);
			
			/**
			 * * ���Է�������ͣ�ô�
			 */
			
			//resultArray =new String[resultArrayStr.size()];
			
			for (int i = 0; i < resultArrayStr.size(); i++) {//������а���ͣ�ôʴ˱������Ϳ�null
				if (resultArrayStr.get(i)== null||isContains(resultArrayStr.get(i).replaceAll("/[a-z]{1,}", ""))) {
//					if (resultArrayStr.get(i)== null||stopWordSet.contains(resultArrayStr.get(i).replaceAll("/[a-z]{1,}", ""))) {
						resultArrayStr.remove(i);i--;
				}
							
			}
			System.out.println("����ͣ�ô���ȡ��ʵ�ʣ�"+resultArrayStr);
			ArrayList<String> resultArrayWord=new ArrayList<String>();
			for(String string:resultArrayStr){
				string=string.replaceAll("/[a-z]{1,}", "");
				resultArrayWord.add(string);
			}
			System.out.println("ȥ������ʵ��:"+resultArrayWord);
			
			 /**
			  * �������ؼ���
			  */
			
			questWords = new ArrayList<String>();
			/**
			 * ���ƶ�ƥ��
			 */
			WordSimilarity.loadGlossary();
			
		int count=0;
		for(int k=0;k<resultArrayWord.size();k++){
			questWords.add(resultArrayWord.get(k));
			for(int j=0;j<neciArtiWords.length;j++){		
//				if(neciArtiWords[j]!=null && neciArtiWords[j].length()!=1 &&
//						resultArrayWord.get(k).matches(neciArtiWords[j])){
//					questWords.add(neciArtiWords[j]);//System.out.println("����ƥ��ؼ��֣�"+questNesWords.toString());
//					//System.out.println("����ƥ��"+questNesWords);
//					neciArtiWords[j]=null;}
//					else 
						if (neciArtiWords[j]!=null &&!resultArrayWord.get(k).equals(neciArtiWords[j])&&
							resultArrayWord.get(k).length()!=1&&neciArtiWords[j].length()!=1&&
							WordSimilarity.simWord(
							resultArrayWord.get(k),neciArtiWords[j])>=0.90){
					questWords.add(neciArtiWords[j]);
					//questNesWords.add(resultArrayWord.get(k));
					System.out.println("���ƶȹؼ��֣�["+resultArrayWord.get(k)+"] ƥ�����¹ؼ��ʣ�["+neciArtiWords[j]+"]");
					//System.out.println("����"+WordSimilarity.simWord("��˾","�г�"));
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