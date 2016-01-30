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
	// TODO �Զ����ɵĹ��캯�����
	JSONArray QuesTypeArry;
	try {
		QuesTypeArry = new JSONArray(sentence);
		Set<String> set = new HashSet<String>();
		for (int j = 0; j < QuesTypeArry.length(); j++) {		
			JSONArray QuesTypeArry2 = QuesTypeArry.getJSONArray(j);	
//			System.out.println("������:"+QuesTypeArry2.length());
			
			for(int k = 0; k < QuesTypeArry2.length(); k++){
				JSONArray QuesTypeArry3 = QuesTypeArry2.getJSONArray(k);
//				System.out.println("������:"+QuesTypeArry3.length());
					for(int o=0;o<QuesTypeArry3.length();o++){//����ÿ������
						JSONObject jsonObject=QuesTypeArry3.getJSONObject(o);//ÿ������
						JSONArray argArray = jsonObject.getJSONArray("arg");
						//System.out.println("argArray.length():"+argArray.length());
						if(argArray.length()!=0){
						for(int argIn=0;argIn<argArray.length();argIn++){
							JSONObject argObj=argArray.getJSONObject(argIn);
							String type = argObj.getString("type");
							if(type.equals("A0")||type.equals("A1")){
								int beg = Integer.parseInt(argObj.getString("beg"));//����beg�ɱ���
								int end =Integer.parseInt(argObj.getString("end")) ;
								for(int idN=beg;idN<=end ;idN++){//��������
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
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
//	System.out.println("������:"+QuesTypeArry.length());
	return list;
	

}
//public static void main(String[] args) throws JSONException, ParseException {
//String string = "��ѧ������ķ��ֽ������ڽ��ͼ���洢��һ�Ϊ����֪�Ĵ��Ի���˴��о���ʾ�˿��ܵĴ洢�ֶΣ��������˾�����ǣ����ö�������Ȼ�����з��������á�����ͱ��洢����һԼ��1���ڸ���ϸ����ͻ���ĸ��������С��о��������ڲ��鶯�����ͻ���У�CPEB���ö����������ܾ���ʹͻ������ϸ���洢���ڼ���Ļ��ƣ���ѧ�Ҽƻ�����һ��������һ�����о���";
////String string = "��ѧ������ķ��ֽ������ڽ��ͼ���洢��һ�Ϊ����֪�Ĵ��Ի���˴��о���ʾ�˿��ܵĴ洢�ֶΣ��������˾�����ǣ����ö�������Ȼ�����з��������á�����ͱ��洢����һԼ��1���ڸ���ϸ����ͻ���ĸ��������С��о��������ڲ��鶯�����ͻ���У�CPEB���ö����������ܾ���ʹͻ������ϸ���洢���ڼ���Ļ��ƣ���ѧ�Ҽƻ�����һ��������һ�����о���";
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
//	System.out.println("������:"+QuesTypeArry.length());
	Set<String> set = new HashSet<String>();
	for (int j = 0; j < QuesTypeArry.length(); j++) {		
		JSONArray QuesTypeArry2 = QuesTypeArry.getJSONArray(j);	
//		System.out.println("������:"+QuesTypeArry2.length());
		
		for(int k = 0; k < QuesTypeArry2.length(); k++){
			JSONArray QuesTypeArry3 = QuesTypeArry2.getJSONArray(k);
//			System.out.println("������:"+QuesTypeArry3.length());
				for(int o=0;o<QuesTypeArry3.length();o++){//����ÿ������
					JSONObject jsonObject=QuesTypeArry3.getJSONObject(o);//ÿ������
					JSONArray argArray = jsonObject.getJSONArray("arg");
					//System.out.println("argArray.length():"+argArray.length());
					if(argArray.length()!=0){
					for(int argIn=0;argIn<argArray.length();argIn++){
						JSONObject argObj=argArray.getJSONObject(argIn);
						String type = argObj.getString("type");
						if(type.equals("A0")||type.equals("A1")){
							int beg = Integer.parseInt(argObj.getString("beg"));//����beg�ɱ���
							int end =Integer.parseInt(argObj.getString("end")) ;
							for(int idN=beg;idN<=end ;idN++){//��������
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