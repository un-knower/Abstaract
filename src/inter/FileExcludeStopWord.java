package inter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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



public class FileExcludeStopWord {
	// ͣ�ôʴʱ�
	public static final String stopWordTable = "D:" + File.separator + "java"
			+ File.separator + "srcFile" + File.separator + "StopWordTable.txt";

//	 public static void main(String[] args) {
//	
//	 // Դ�ļ���Ŀ���ļ�
//	 String srcFile = "D:" + File.separator + "java" + File.separator
//	 + "srcFile" + File.separator + "stem.txt";
//	 String destFile = "D:" + File.separator + "java" + File.separator
//	 + "destFile" + File.separator + "Ŀ���ļ�.txt";
//	 new FileExcludeStopWord().fileExcludeStopWord(srcFile, destFile);
//	
//	 }
   /**
    * 
    * @param srcFile   ���ִ��ļ�
    * @param destFile  �ִʺ����ļ�   
    * @param number    ѡ�����
    */
	public void fileExcludeStopWord(String srcFile, String destFile,int number) {
		try {
			// ��ȡԭ�ļ���ͣ�ôʱ�
			BufferedReader srcFileBr = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(new File(srcFile))));
			
			BufferedReader StopWordFileBr = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							stopWordTable))));
			String contentProces="D:\\java\\article\\contentProces.txt";
//			BufferedReader contentProcessReader = new BufferedReader(
//					new InputStreamReader(new FileInputStream(new File(
//							contentProces))));//��ɷִ�
File contentProcessReader = new File(contentProces);
			// ��ȥ��ͣ�ôʵ��ı���Ϣ��������ļ�
			BufferedWriter destFileBw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(
							destFile)), "GBK"));
			// ��ȡ���¹ؼ��ֱ���·��
			String artiWords = "D:\\java\\article\\contentkey.txt";
			//String similiarAns1s="D:\\java\\ans\\similiarAns1.txt";
			BufferedWriter article = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(
							artiWords)), "utf-8"));
//			BufferedWriter similiarAns1 = new BufferedWriter(
//					new OutputStreamWriter(new FileOutputStream(new File(
//							similiarAns1s)), "utf-8"));
			// �������ͣ�ôʵļ���
			Set<String> stopWordSet = new HashSet<String>();

			// ���绯ͣ�ôʼ�
			String stopWord = null;
			while((stopWord=StopWordFileBr.readLine())!=null)
				stopWordSet.add(stopWord);

			int init_flag = CLibrary.Instance.NLPIR_Init("",1, "0");//0 GBK ��1 utf-8
			String nativeBytes = null;

			if (0 == init_flag) {
				nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
				System.err.println("��ʼ��ʧ�ܣ�fail reason is "+nativeBytes);
				return;
			}
			String paragraph = null;
			String paragraph1=null;
			StringBuilder builder = new StringBuilder();
			 for (; (paragraph1 = srcFileBr.readLine()) != null;) {
		//	for(int j=0;j<=(number-1)/4;j++){
		//		paragraph1=stemFileBr.readLine();
				 builder.append(paragraph1+"\n");
			}
			for (int k = 0; k < number; k++){
				//paragraph = paragraph1+srcFileBr.readLine();
//				paragraph = srcFileBr.readLine();
				paragraph=builder.toString();
				}
			System.out.println("����Ϊ��" + paragraph);
			// �����û��ʵ�
			//int nCount = 0;
			//String usrdir = "userdict.txt";
			//byte[] usrdirb = usrdir.getBytes();
			// ��һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û��ֵ�ı�������(0:type
			// unknown;1:ASCII��;2:GB2312,GBK,GB10380;3:UTF-8;4:BIG5��
			//nCount = ICTCLAS.ICTCLAS_ImportUserDictFile(usrdirb, 2);
			// System.out.println("�����û��ʸ�����"+nCount);
			// nCount = 0;
			
			//String newWords=CLibrary.Instance.NLPIR_GetFileNewWords(srcFile, nMaxKeyLimit, false);
			/**
			 * ����ַ���������
			 */
	
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream
							("D:\\java\\infoFile\\content.txt"),"GB2312"));
			String line;
			while ((line = in.readLine()) != null) {
				//System.out.println(line);
				article.write(line+"\n");
				article.newLine();
			}
			in.close();
			article.close();
//			
//			 in = new BufferedReader(
//					new InputStreamReader(new FileInputStream
//							("similiarAns.txt"),"GB2312"));
//			
//			while ((line = in.readLine()) != null) {
//				//System.out.println(line);
//				similiarAns1.write(line+"\n");
//				similiarAns1.newLine();
//			}
//			in.close();
//			article.close();
//			similiarAns1.close();
			/**
			 * ��ȡ�����´ʺ͹ؼ���
			 */
			int nMaxKeyLimit=200;
			String newWords= CLibrary.Instance.NLPIR_GetFileNewWords(
					artiWords, 100,false);//��ȡ�´�
//			"D:\\java\\article\\content.txt", 10,false);
			//System.out.println("�´ʣ�"+newWords);
			String[] newArtiWords = newWords.split("#");//�´�����
			System.out.println("�����´�:"+Arrays.toString(newArtiWords));
			String necissaryWords = CLibrary.Instance.NLPIR_GetFileKeyWords(artiWords, nMaxKeyLimit, false);
									
			//System.out.println("���¹ؼ��ʣ�"+necissaryWords);
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
			 *  �Զ�����ı����зִ�
			 */
			byte[] spiltResult = CLibrary.Instance.
					NLPIR_ParagraphProcess(paragraph, 1).getBytes();//1���Ա�ע
//			byte[] spiltResult = CLibrary.Instance.
//					NLPIR_ParagraphProcess(paragraph, 0).getBytes();
			/**
			 * ����������½��зִ�
			 */
			
			CLibrary.Instance.NLPIR_FileProcess(artiWords,contentProces, 1);
			
			//CLibrary.Instance.NLPIR_AddUserWord("ø�� ");
			//spiltResult = CLibrary.Instance.NLPIR_ParagraphProcess(paragraph, 0).getBytes();
			//System.out.println("�ִʽ��Ϊ�� " + new String(spiltResult));
			/*byte[] spiltResult = ICTCLAS.ICTCLAS_ParagraphProcess(
					paragraph.getBytes("GBK"), 2, 0);*/
			String spiltResultStr = new String(spiltResult, 0,
					spiltResult.length, "GBK");

			// �õ��ִʺ�Ĵʻ����飬�Ա�����Ƚ�
			String[] resultArray = spiltResultStr.split(" ");//����ɷִ�
			//System.out.println("�ִʽ��"+Arrays.toString(resultArray));
			System.out.println("\n����ɷִʣ�");
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
//			StringBuffer resultArrayStr = new StringBuffer();
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
			
		
//			/**
//			 * ����ͣ�ô�
//			 */
//			for (int i = 0; i < resultArray.length; i++) {//������а���ͣ�ôʴ˱������Ϳ�null
//				if (stopWordSet.contains(resultArray[i])) {
//					resultArray[i] = null;
//				}
//			}
//			System.out.println("����ͣ�ôʺ�ķִʣ�"+Arrays.toString(resultArray));
			
			//System.out.println("resultArrayStr.length();"+resultArrayStr.length());
			/**
			 * * ���Է�������ͣ�ôʺ�
			 */
			
			resultArray =new String[resultArrayStr.size()];
			
			for (int i = 0; i < resultArrayStr.size(); i++) {//������а���ͣ�ôʴ˱������Ϳ�null
				//System.out.println(i);
				resultArray[i]=resultArrayStr.get(i).
						//replace("/v", "").replace("/n", "").replace("/a", "");
				replaceAll("/[a-z]{1,}", "");
				//System.out.println(resultArray[i]);
				if (stopWordSet.contains(resultArray[i])) {
//					if (stopWordSet.contains(resultArrayStr.get(i))) {
					resultArrayStr.remove(i);i--;
				}
				//System.out.println(i);
				
			}
			//System.out.println("����������ȡ��ʵ�ʣ�"+resultArrayStr);
			
			//System.out.println(Arrays.toString(resultArray));
			
			 /**
			  * �������ؼ���
			  */
			//String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(paragraph, 10, false);
			//resultArray = nativeByte.split("#");
			//System.out.println("ϵͳ����ؼ��֣�"+resultArray);			
			ArrayList<String> necArry = new ArrayList<String>();						
			for (int i = 0; i < resultArray.length; i++) {						
			if (resultArray[i] != null) {
//				if (resultArray[i] != null&&AnswerWords[j]!=null) {

					necArry.add(resultArray[i]);
					//necArry.add(AnswerWords[j]);
					
				}
			
			}
			/*System.out.println(Arrays.toString(AnswerWords));
			for(int i=0;i<AnswerWords.length;i++){
				if (AnswerWords[i] != null) {
					
					necArry.add(AnswerWords[i]);
					
					
				}
			}*/
			System.out.println("����ͣ�ôʺ�Ĺؼ���Ϊ:\n"+necArry.toString());
			//System.out.println("ϵͳ��ȡ�ؼ���Ϊ");
			Set<String> questNesWords = new HashSet<String>();
//			ArrayList<String> questNesWords = new ArrayList<String>();
//			Search br =null;
//			br = new Search((new InputStreamReader(new FileInputStream("D:\\java\\infoFile\\content.txt"), "GBK")));
//			String s = null;
//			while ((s = br.readpara()) != null) {
//				//System.out.println(s);
//				for (int i = 0; i < necArry.size(); i++) {//������а���ͣ�ôʴ˱������Ϳ�null
//					if (s.contains(necArry.get(i))) {
//						questNesWords.add(necArry.get(i));
//						//System.out.println(s+"�ؼ��ʣ� "+necArry.get(i));
//					}					
//				}
//			}
			//System.out.println("questNesWords"+questNesWords);
			/**
			 * ���ƶ�ƥ��
			 */
			WordSimilarity.loadGlossary();
			//ArrayList<String> questNesWords = new ArrayList<String>();
		int count=0;
		for(int k=0;k<necArry.size();k++){
			for(int j=0;j<neciArtiWords.length;j++){		
				if(neciArtiWords[j]!=null && neciArtiWords[j].length()!=1&& necArry.get(k).matches(neciArtiWords[j])){
					questNesWords.add(neciArtiWords[j]);//System.out.println("����ƥ��ؼ��֣�"+questNesWords.toString());
					System.out.println();
					neciArtiWords[j]=null;}
					else if (neciArtiWords[j]!=null && necArry.get(k).length()!=1&&neciArtiWords[j].length()!=1&&WordSimilarity.simWord(
						necArry.get(k),neciArtiWords[j])>=0.90){
					questNesWords.add(neciArtiWords[j]);
					questNesWords.add(necArry.get(k));
					System.out.println("���ƶȹؼ��֣�["+necArry.get(k)+"] ƥ�����¹ؼ��ʣ�["+neciArtiWords[j]+"]");
					neciArtiWords[j]=null;
					
				}//else{questNesWords.add(necArry.get(k));}
//					questNesWords.add(necArry.get(k));
			}
		}
		
//		System.out.println("�������ƶȣ�"+WordSimilarity.simWord("ĸ","ĸ��"));
		//System.out.println("�������ƶȣ�"+WordSimilarity.simWord("�Լ�","��"));
		//System.out.println("necArry"+necArry.size()+" neciArtiWords"+neciArtiWords.length +" ����"+count);
		
		System.out.println("ϵͳ�ؼ���ƥ�������Ĺؼ���Ϊ:\n"+questNesWords.toString());
		
		
		StringBuffer finalStr = new StringBuffer();
		Iterator<String> it = questNesWords.iterator();
		while(it.hasNext()){
			
			finalStr = finalStr.append(it.next()).append(" ");
		}
		
		

			// �����˺���ı���Ϣд�뵽ָ���ļ���
			//destFileBw.write(questNesWords.toString());//д���ļ�destFile
			destFileBw.write(finalStr.toString());//д���ļ�destFile
			destFileBw.write('\n');
			destFileBw.newLine();
			// }
			CLibrary.Instance.NLPIR_Exit();
			// �ر�������
			destFileBw.close();
			
			StopWordFileBr.close();
			srcFileBr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}