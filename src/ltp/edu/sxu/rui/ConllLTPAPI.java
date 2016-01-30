package ltp.edu.sxu.rui;

/*
 * This example shows how to use Java to build http connection and request
 * the ltp-cloud service for perform full-stack Chinese language analysis
 * and get results in specified formats
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ConllLTPAPI {
	 static String api_key = "U1H0S1Z1CkcUtrLouJvyHVNSOWkY9ycmAVahcduW";
     static String pattern = "srl";
     static String format  = "conll";//设定格式，“json”或是“xml”或是“conll”或是"plain";
//     String format  = args[0];
//     String text    = " 科学家最近的发现将有助于解释记忆存储这一最不为人所知的大脑活动。此次研究揭示了可能的存储手段，不过让人惊奇的是，‘朊毒体活动’竟然在其中发挥着作用。记忆就被存储在这一约有1万亿个神经细胞和突触的复杂网络中。研究表明，在哺乳动物的神经突触中，CPEB的朊毒体特征可能就是使突触和神经细胞存储长期记忆的机制，科学家计划对这一理论作进一步的研究。";
     
	public static ArrayList<String> LTPSrl(String candiSenten){
		StringBuilder builder = new StringBuilder();
		ArrayList<String> ltpWordsnew;
		String text;
		try {
			text = URLEncoder.encode(candiSenten, "utf-8");
			 URL url;
			try {
				url = new URL("http://ltpapi.voicecloud.cn/analysis/?"
				         + "api_key=" + api_key + "&"
				         + "text="    + text    + "&"
				         + "format="  + format  + "&"
				         + "pattern=" + pattern
//                     +"&"+"has_key="+false//去掉健
				         );
				URLConnection conn = url.openConnection();
				conn.connect();

				BufferedReader innet = new BufferedReader(new InputStreamReader(
				                       conn.getInputStream(),
				                       "utf-8"));
				String line;
		        while ((line = innet.readLine())!= null) {
		        	builder.append(line);
		            //System.out.println(line);           
		            Write(line,"out_conll.txt");
		        }
		        innet.close(); 
//		        new JSonParser();
//			    ltpWordsnew =JSonParser.JSonParserKey(builder.toString());
//				//System.out.println("LTP角色标注后的关键词:"+ltpWordsnew.toString());
//				return ltpWordsnew;
			} catch (MalformedURLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}


		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;

	    
	}
    public static void main(String[] args) throws IOException {
//    	String text    = " 科学家最近的发现将有助于解释记忆存储这一最不为人所知的大脑活动。此次研究揭示了可能的存储手段，不过让人惊奇的是，‘朊毒体活动’竟然在其中发挥着作用。记忆就被存储在这一约有1万亿个神经细胞和突触的复杂网络中。研究表明，在哺乳动物的神经突触中，CPEB的朊毒体特征可能就是使突触和神经细胞存储长期记忆的机制，科学家计划对这一理论作进一步的研究。";
//    	LTPSrl(text);
    }
//    	StringBuilder builder = new StringBuilder();
//        if (args.length < 1 || !(args[0].equals("xml") 
//                                || args[0].equals("json") 
//                                || args[0].equals("conll"))) {
//            System.out.println("Usage: java SimpleAPI [xml/json/conll]");
//            return;
//        }

       /* String api_key = "U1H0S1Z1CkcUtrLouJvyHVNSOWkY9ycmAVahcduW";
        String pattern = "srl";
        String format  = "json";//设定格式，“json”或是“xml”或是“conll”;
//        String format  = args[0];
        String text    = " 科学家最近的发现将有助于解释记忆存储这一最不为人所知的大脑活动。此次研究揭示了可能的存储手段，不过让人惊奇的是，‘朊毒体活动’竟然在其中发挥着作用。记忆就被存储在这一约有1万亿个神经细胞和突触的复杂网络中。研究表明，在哺乳动物的神经突触中，CPEB的朊毒体特征可能就是使突触和神经细胞存储长期记忆的机制，科学家计划对这一理论作进一步的研究。";
        text = URLEncoder.encode(text, "utf-8");

        URL url     = new URL("http://ltpapi.voicecloud.cn/analysis/?"
                              + "api_key=" + api_key + "&"
                              + "text="    + text    + "&"
                              + "format="  + format  + "&"
                              + "pattern=" + pattern
//                              +"&"+"has_key="+false//去掉健
                              );
        URLConnection conn = url.openConnection();
        conn.connect();

        BufferedReader innet = new BufferedReader(new InputStreamReader(
                                conn.getInputStream(),
                                "utf-8"));*/
//        String line;
//        while ((line = innet.readLine())!= null) {
//        	builder.append(line);
//            System.out.println(line);           
//            Write(line,"out_xml.txt");
//        }
//        innet.close(); 
//        new JSonParser();
//		ArrayList<String> ltpWordsnew =JSonParser.JSonParserKey(builder.toString());
//		System.out.println("LTP角色标注后的关键词"+ltpWordsnew.toString());
   
   
    public static void Write(String cc,String str)
	  {
		 String path = str;
		 try 
		 {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path,false));
			writer.write(cc+"\t");
			writer.newLine();
			writer.close();
//			System.out.println("写入成功");
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
	  }
}