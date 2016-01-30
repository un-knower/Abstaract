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
	// 停用词词表
	public static final String stopWordTable = "D:" + File.separator + "java"
			+ File.separator + "srcFile" + File.separator + "StopWordTable.txt";

//	 public static void main(String[] args) {
//	
//	 // 源文件和目的文件
//	 String srcFile = "D:" + File.separator + "java" + File.separator
//	 + "srcFile" + File.separator + "stem.txt";
//	 String destFile = "D:" + File.separator + "java" + File.separator
//	 + "destFile" + File.separator + "目标文件.txt";
//	 new FileExcludeStopWord().fileExcludeStopWord(srcFile, destFile);
//	
//	 }
   /**
    * 
    * @param srcFile   待分词文件
    * @param destFile  分词后存放文件   
    * @param number    选项基数
    */
	public void fileExcludeStopWord(String srcFile, String destFile,int number) {
		try {
			// 读取原文件和停用词表
			BufferedReader srcFileBr = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(new File(srcFile))));
			
			BufferedReader StopWordFileBr = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(
							stopWordTable))));
			String contentProces="D:\\java\\article\\contentProces.txt";
//			BufferedReader contentProcessReader = new BufferedReader(
//					new InputStreamReader(new FileInputStream(new File(
//							contentProces))));//题干分词
File contentProcessReader = new File(contentProces);
			// 将去除停用词的文本信息存入输出文件
			BufferedWriter destFileBw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(
							destFile)), "GBK"));
			// 提取文章关键字保存路径
			String artiWords = "D:\\java\\article\\contentkey.txt";
			//String similiarAns1s="D:\\java\\ans\\similiarAns1.txt";
			BufferedWriter article = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(
							artiWords)), "utf-8"));
//			BufferedWriter similiarAns1 = new BufferedWriter(
//					new OutputStreamWriter(new FileOutputStream(new File(
//							similiarAns1s)), "utf-8"));
			// 用来存放停用词的集合
			Set<String> stopWordSet = new HashSet<String>();

			// 初如化停用词集
			String stopWord = null;
			while((stopWord=StopWordFileBr.readLine())!=null)
				stopWordSet.add(stopWord);

			int init_flag = CLibrary.Instance.NLPIR_Init("",1, "0");//0 GBK ；1 utf-8
			String nativeBytes = null;

			if (0 == init_flag) {
				nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
				System.err.println("初始化失败！fail reason is "+nativeBytes);
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
			System.out.println("问题为：" + paragraph);
			// 导入用户词典
			//int nCount = 0;
			//String usrdir = "userdict.txt";
			//byte[] usrdirb = usrdir.getBytes();
			// 第一个参数为用户字典路径，第二个参数为用户字典的编码类型(0:type
			// unknown;1:ASCII码;2:GB2312,GBK,GB10380;3:UTF-8;4:BIG5）
			//nCount = ICTCLAS.ICTCLAS_ImportUserDictFile(usrdirb, 2);
			// System.out.println("导入用户词个数："+nCount);
			// nCount = 0;
			
			//String newWords=CLibrary.Instance.NLPIR_GetFileNewWords(srcFile, nMaxKeyLimit, false);
			/**
			 * 解决字符编码问题
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
			 * 提取文章新词和关键词
			 */
			int nMaxKeyLimit=200;
			String newWords= CLibrary.Instance.NLPIR_GetFileNewWords(
					artiWords, 100,false);//提取新词
//			"D:\\java\\article\\content.txt", 10,false);
			//System.out.println("新词："+newWords);
			String[] newArtiWords = newWords.split("#");//新词数组
			System.out.println("文章新词:"+Arrays.toString(newArtiWords));
			String necissaryWords = CLibrary.Instance.NLPIR_GetFileKeyWords(artiWords, nMaxKeyLimit, false);
									
			//System.out.println("文章关键词："+necissaryWords);
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
			 *  对读入的文本进行分词
			 */
			byte[] spiltResult = CLibrary.Instance.
					NLPIR_ParagraphProcess(paragraph, 1).getBytes();//1词性标注
//			byte[] spiltResult = CLibrary.Instance.
//					NLPIR_ParagraphProcess(paragraph, 0).getBytes();
			/**
			 * 读读入的文章进行分词
			 */
			
			CLibrary.Instance.NLPIR_FileProcess(artiWords,contentProces, 1);
			
			//CLibrary.Instance.NLPIR_AddUserWord("酶解 ");
			//spiltResult = CLibrary.Instance.NLPIR_ParagraphProcess(paragraph, 0).getBytes();
			//System.out.println("分词结果为： " + new String(spiltResult));
			/*byte[] spiltResult = ICTCLAS.ICTCLAS_ParagraphProcess(
					paragraph.getBytes("GBK"), 2, 0);*/
			String spiltResultStr = new String(spiltResult, 0,
					spiltResult.length, "GBK");

			// 得到分词后的词汇数组，以便后续比较
			String[] resultArray = spiltResultStr.split(" ");//将题干分词
			//System.out.println("分词结果"+Arrays.toString(resultArray));
			System.out.println("\n将题干分词：");
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
//			StringBuffer resultArrayStr = new StringBuffer();
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
			
		
//			/**
//			 * 过滤停用词
//			 */
//			for (int i = 0; i < resultArray.length; i++) {//将题干中包含停用词此表，则滞空null
//				if (stopWordSet.contains(resultArray[i])) {
//					resultArray[i] = null;
//				}
//			}
//			System.out.println("过滤停用词后的分词："+Arrays.toString(resultArray));
			
			//System.out.println("resultArrayStr.length();"+resultArrayStr.length());
			/**
			 * * 测试方法过滤停用词后
			 */
			
			resultArray =new String[resultArrayStr.size()];
			
			for (int i = 0; i < resultArrayStr.size(); i++) {//将题干中包含停用词此表，则滞空null
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
			//System.out.println("过滤问题提取的实词："+resultArrayStr);
			
			//System.out.println(Arrays.toString(resultArray));
			
			 /**
			  * 输出问题关键字
			  */
			//String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(paragraph, 10, false);
			//resultArray = nativeByte.split("#");
			//System.out.println("系统问题关键字："+resultArray);			
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
			System.out.println("过滤停用词后的关键字为:\n"+necArry.toString());
			//System.out.println("系统提取关键字为");
			Set<String> questNesWords = new HashSet<String>();
//			ArrayList<String> questNesWords = new ArrayList<String>();
//			Search br =null;
//			br = new Search((new InputStreamReader(new FileInputStream("D:\\java\\infoFile\\content.txt"), "GBK")));
//			String s = null;
//			while ((s = br.readpara()) != null) {
//				//System.out.println(s);
//				for (int i = 0; i < necArry.size(); i++) {//将题干中包含停用词此表，则滞空null
//					if (s.contains(necArry.get(i))) {
//						questNesWords.add(necArry.get(i));
//						//System.out.println(s+"关键词： "+necArry.get(i));
//					}					
//				}
//			}
			//System.out.println("questNesWords"+questNesWords);
			/**
			 * 相似度匹配
			 */
			WordSimilarity.loadGlossary();
			//ArrayList<String> questNesWords = new ArrayList<String>();
		int count=0;
		for(int k=0;k<necArry.size();k++){
			for(int j=0;j<neciArtiWords.length;j++){		
				if(neciArtiWords[j]!=null && neciArtiWords[j].length()!=1&& necArry.get(k).matches(neciArtiWords[j])){
					questNesWords.add(neciArtiWords[j]);//System.out.println("字面匹配关键字："+questNesWords.toString());
					System.out.println();
					neciArtiWords[j]=null;}
					else if (neciArtiWords[j]!=null && necArry.get(k).length()!=1&&neciArtiWords[j].length()!=1&&WordSimilarity.simWord(
						necArry.get(k),neciArtiWords[j])>=0.90){
					questNesWords.add(neciArtiWords[j]);
					questNesWords.add(necArry.get(k));
					System.out.println("相似度关键字：["+necArry.get(k)+"] 匹配文章关键词：["+neciArtiWords[j]+"]");
					neciArtiWords[j]=null;
					
				}//else{questNesWords.add(necArry.get(k));}
//					questNesWords.add(necArry.get(k));
			}
		}
		
//		System.out.println("测试相似度："+WordSimilarity.simWord("母","母亲"));
		//System.out.println("测试相似度："+WordSimilarity.simWord("自己","我"));
		//System.out.println("necArry"+necArry.size()+" neciArtiWords"+neciArtiWords.length +" 总数"+count);
		
		System.out.println("系统关键字匹配后检索的关键字为:\n"+questNesWords.toString());
		
		
		StringBuffer finalStr = new StringBuffer();
		Iterator<String> it = questNesWords.iterator();
		while(it.hasNext()){
			
			finalStr = finalStr.append(it.next()).append(" ");
		}
		
		

			// 将过滤后的文本信息写入到指定文件中
			//destFileBw.write(questNesWords.toString());//写入文件destFile
			destFileBw.write(finalStr.toString());//写入文件destFile
			destFileBw.write('\n');
			destFileBw.newLine();
			// }
			CLibrary.Instance.NLPIR_Exit();
			// 关闭输入流
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
