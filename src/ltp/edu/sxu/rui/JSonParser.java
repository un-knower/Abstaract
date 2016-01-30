package ltp.edu.sxu.rui;

import java.io.*;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.*;

import cn.edu.sxu.rui.KeyWord;
public class JSonParser {
public static ArrayList<String> JSonParserKey(String sentence) {
	ArrayList<String> list =new ArrayList<String>();
	// TODO 自动生成的构造函数存根
	JSONArray QuesTypeArry;
	try {
		QuesTypeArry = new JSONArray(sentence);
		Set<String> set = new HashSet<String>();
		for (int j = 0; j < QuesTypeArry.length(); j++) {		
			JSONArray QuesTypeArry2 = QuesTypeArry.getJSONArray(j);	
//			System.out.println("句子数:"+QuesTypeArry2.length());
			
			for(int k = 0; k < QuesTypeArry2.length(); k++){
				JSONArray QuesTypeArry3 = QuesTypeArry2.getJSONArray(k);
//				System.out.println("单词数:"+QuesTypeArry3.length());
					for(int o=0;o<QuesTypeArry3.length();o++){//处理每个对象
						JSONObject jsonObject=QuesTypeArry3.getJSONObject(o);//每个对象
						JSONArray argArray = jsonObject.getJSONArray("arg");
						//System.out.println("argArray.length():"+argArray.length());
						if(argArray.length()!=0){
						for(int argIn=0;argIn<argArray.length();argIn++){
							JSONObject argObj=argArray.getJSONObject(argIn);
							String type = argObj.getString("type");
							if(type.equals("A0")||type.equals("A1")){
								int beg = Integer.parseInt(argObj.getString("beg"));//解析beg成变量
								int end =Integer.parseInt(argObj.getString("end")) ;
								for(int idN=beg;idN<=end ;idN++){//添加名词
									//for(int o2=0;o2<QuesTypeArry3.length();o2++){
									String pos=	QuesTypeArry3.getJSONObject(idN).getString("pos");
									if(pos.equals("n")||pos.equals("v"))	{
//										if(pos.equals("n")||pos.equals("v")||pos.equals("a"))	{
										String cont = QuesTypeArry3.getJSONObject(idN).getString("cont");
										set.add(cont);
									}								
								}				
							
							}
						}
					}					
				}

			}
		}
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			new KeyWord();
			if(KeyWord.isContains(string))
				{iterator.remove();}
			
		}
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			list.add(string);
			//System.out.println(string);
			
			
		}
	} catch (JSONException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
//	System.out.println("段落数:"+QuesTypeArry.length());
	return list;
	

}
//public static void main(String[] args) throws JSONException, ParseException {
//String string = "科学家最近的发现将有助于解释记忆存储这一最不为人所知的大脑活动。此次研究揭示了可能的存储手段，不过让人惊奇的是，‘朊毒体活动’竟然在其中发挥着作用。记忆就被存储在这一约有1万亿个神经细胞和突触的复杂网络中。研究表明，在哺乳动物的神经突触中，CPEB的朊毒体特征可能就是使突触和神经细胞存储长期记忆的机制，科学家计划对这一理论作进一步的研究。";
////String string = "科学家最近的发现将有助于解释记忆存储这一最不为人所知的大脑活动。此次研究揭示了可能的存储手段，不过让人惊奇的是，‘朊毒体活动’竟然在其中发挥着作用。记忆就被存储在这一约有1万亿个神经细胞和突触的复杂网络中。研究表明，在哺乳动物的神经突触中，CPEB的朊毒体特征可能就是使突触和神经细胞存储长期记忆的机制，科学家计划对这一理论作进一步的研究。";
//System.out.println(JSonParserKey(string).toString());}
/*StringBuffer stringBuffer = new StringBuffer();
String line = null ;
int i=0;
try {
	StringBuffer buffer = new StringBuffer();
	BufferedReader br = new BufferedReader(new InputStreamReader(ResourceHelper
        .getResourceInputStream("json.txt"),"gbk")); 
//BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream("F:\\java\\question.json")));
	int buf=-1;
	while( (line = br.readLine())!= null ){
	buffer.append(line);
	}	
    System.out.println(buffer);
	
	JSONArray QuesTypeArry = new JSONArray(buffer.toString());
//	System.out.println("段落数:"+QuesTypeArry.length());
	Set<String> set = new HashSet<String>();
	for (int j = 0; j < QuesTypeArry.length(); j++) {		
		JSONArray QuesTypeArry2 = QuesTypeArry.getJSONArray(j);	
//		System.out.println("句子数:"+QuesTypeArry2.length());
		
		for(int k = 0; k < QuesTypeArry2.length(); k++){
			JSONArray QuesTypeArry3 = QuesTypeArry2.getJSONArray(k);
//			System.out.println("单词数:"+QuesTypeArry3.length());
				for(int o=0;o<QuesTypeArry3.length();o++){//处理每个对象
					JSONObject jsonObject=QuesTypeArry3.getJSONObject(o);//每个对象
					JSONArray argArray = jsonObject.getJSONArray("arg");
					//System.out.println("argArray.length():"+argArray.length());
					if(argArray.length()!=0){
					for(int argIn=0;argIn<argArray.length();argIn++){
						JSONObject argObj=argArray.getJSONObject(argIn);
						String type = argObj.getString("type");
						if(type.equals("A0")||type.equals("A1")){
							int beg = Integer.parseInt(argObj.getString("beg"));//解析beg成变量
							int end =Integer.parseInt(argObj.getString("end")) ;
							for(int idN=beg;idN<=end ;idN++){//添加名词
								//for(int o2=0;o2<QuesTypeArry3.length();o2++){
								String pos=	QuesTypeArry3.getJSONObject(idN).getString("pos");
								if(pos.equals("n")||pos.equals("v")||pos.equals("a"))	{
									String cont = QuesTypeArry3.getJSONObject(idN).getString("cont");
									set.add(cont);
								}								
							}				
						
						}
					}
				}					
			}

		}
	}
	for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
		String string = iterator.next();
		new KeyWord();
		if(KeyWord.isContains(string))
			set.remove(string);
		System.out.println(string);
		
	}

	
	br.close();
} catch (FileNotFoundException e) {
       e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
 }*/



public static boolean write(String cont, File dist) {
	//System.out.println(cont);
	try {
        //OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(dist),"UTF-8"); 
       // System.out.println(dist);
		BufferedWriter writer = new BufferedWriter(new FileWriter(dist,true),5*1024*1024);
        writer.write(cont);
        //writer.flush();
        writer.newLine();
        writer.close();
		return true;
	} catch (IOException e) {
		e.printStackTrace();
	return false;
	}
}

}

class ResourceHelper {
	  /**
	   * @param resourceName
	   * @return 
	   * @return 
	   */
	  static BufferedInputStream getResourceInputStream(String resourceName) {
	    try {
	      return new BufferedInputStream(new FileInputStream(resourceName));
	    } catch (FileNotFoundException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    return null;
	  }
	}
