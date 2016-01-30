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
	// ͣ�ôʴʱ�
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
    * @param srcFile   ���ִ��ļ�
    * @param destFile  �ִʺ����ļ�   
    * @param number    ѡ�����
    */
	public CandidateAnswer(String srcFile, String simiAnswer,String destFile) {
//		public void CandidateAnswer(String srcFile, String destFile,int number) {
		try {
			// ��ȡԭ�ļ���ͣ�ôʱ�
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
		
			// �������ͣ�ôʵļ���
			ArrayList<String> negitiveArry = new ArrayList<String>();

			// ���绯ͣ�ôʼ�
			String stopWord = null;
			while((stopWord=negitiveFileBr.readLine())!=null){
				negitiveArry.add(stopWord);
				
			}
			negitiveFileBr.close();
//			System.out.println("negitiveArry:"+negitiveArry.size());
//			for(int i=0;i<negitiveArry.size();i++){
//				System.out.println("negitiveArr:"+negitiveArry.get(i));
//			}
			int init_flag = CLibrary.Instance.NLPIR_Init("",1, "0");//0 GBK ��1 utf-8
			String nativeBytes = null;

			if (0 == init_flag) {
				nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
				System.err.println("��ʼ��ʧ�ܣ�fail reason is "+nativeBytes);
				return;
			}
			//��ѡ�𰸹ؼ�����ȡ
			/*	String similiarWords = CLibrary.Instance.NLPIR_GetFileKeyWords(similiarAns1s, nMaxKeyLimit, false);
				String[] AnswerWords = similiarWords.split("#");
				System.out.println("��ѡ�𰸹ؼ��ʣ�"+Arrays.toString(AnswerWords));*/
			
			/**
			 * ��ȡ��ѡ���ļ����뵽������
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
		//���Ժ�ѡ�����б�
//	System.out.println("sentenList.size"+sentenList.size());
//		for (int i = 0; i < sentenList.size(); i++) {
//			System.out.println("sentenList"+sentenList.get(i));
//		}
//		
		
		/**
		 * �ж����ʴ���
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
			 * ����������Ĵ���
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
			 * �������ƶȴ���
			 */
			// SemanticSimilarity similarity = SemanticSimilarity.getInstance();
			
			// �����˺���ı���Ϣд�뵽ָ���ļ��У���sentenList���뵽�𰸼���
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
				//ȡ��ע�͵�������
				//destFileBw.newLine();
			}
			System.out.print("]");

			
			
			
//			destFileBw.write(finalStr.toString());
//			destFileBw.write('\n');
//			destFileBw.newLine();
			// }
			CLibrary.Instance.NLPIR_Exit();
			// �ر�������
			destFileBw.close();
			
			
			//sysprintLn();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static int sentcecount;//���Ӹ���
	private void sysprintLn() throws FileNotFoundException {
		
		System.out.println("ɸѡ��Ĵ𰸾�:\n\n"+read(destFile)+"\n\n��д��FinalAnswer.txt�ļ���");
		//System.out.println("����ɸѡ�Ĵ𰸾�:\n"+read(destFile)+"\n\n��д��FinalAnswer.txt�ļ���");
		
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
		//System.out.println("��"+count+"��");
		sentcecount=count;
		return res.toString();
	}

}