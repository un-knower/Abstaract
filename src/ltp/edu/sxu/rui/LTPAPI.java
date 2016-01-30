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

public class LTPAPI {
	 static String api_key = "U1H0S1Z1CkcUtrLouJvyHVNSOWkY9ycmAVahcduW";
     static String pattern = "srl";
     static String format  = "json";//�趨��ʽ����json�����ǡ�xml�����ǡ�conll��;
//     String format  = args[0];
//     String text    = " ��ѧ������ķ��ֽ������ڽ��ͼ���洢��һ�Ϊ����֪�Ĵ��Ի���˴��о���ʾ�˿��ܵĴ洢�ֶΣ��������˾�����ǣ����ö�������Ȼ�����з��������á�����ͱ��洢����һԼ��1���ڸ���ϸ����ͻ���ĸ��������С��о��������ڲ��鶯�����ͻ���У�CPEB���ö����������ܾ���ʹͻ������ϸ���洢���ڼ���Ļ��ƣ���ѧ�Ҽƻ�����һ��������һ�����о���";
     
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
//                     +"&"+"has_key="+false//ȥ����
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
		            Write(line,"out_json.txt");
		        }
		        innet.close(); 
		        new JSonParser();
			    ltpWordsnew =JSonParser.JSonParserKey(builder.toString());
				//System.out.println("LTP��ɫ��ע��Ĺؼ���:"+ltpWordsnew.toString());
				return ltpWordsnew;
			} catch (MalformedURLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}


		} catch (UnsupportedEncodingException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;

	    
	}
    public static void main(String[] args) throws IOException {
//    	String text    = " ��ѧ������ķ��ֽ������ڽ��ͼ���洢��һ�Ϊ����֪�Ĵ��Ի���˴��о���ʾ�˿��ܵĴ洢�ֶΣ��������˾�����ǣ����ö�������Ȼ�����з��������á�����ͱ��洢����һԼ��1���ڸ���ϸ����ͻ���ĸ��������С��о��������ڲ��鶯�����ͻ���У�CPEB���ö����������ܾ���ʹͻ������ϸ���洢���ڼ���Ļ��ƣ���ѧ�Ҽƻ�����һ��������һ�����о���";
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
        String format  = "json";//�趨��ʽ����json�����ǡ�xml�����ǡ�conll��;
//        String format  = args[0];
        String text    = " ��ѧ������ķ��ֽ������ڽ��ͼ���洢��һ�Ϊ����֪�Ĵ��Ի���˴��о���ʾ�˿��ܵĴ洢�ֶΣ��������˾�����ǣ����ö�������Ȼ�����з��������á�����ͱ��洢����һԼ��1���ڸ���ϸ����ͻ���ĸ��������С��о��������ڲ��鶯�����ͻ���У�CPEB���ö����������ܾ���ʹͻ������ϸ���洢���ڼ���Ļ��ƣ���ѧ�Ҽƻ�����һ��������һ�����о���";
        text = URLEncoder.encode(text, "utf-8");

        URL url     = new URL("http://ltpapi.voicecloud.cn/analysis/?"
                              + "api_key=" + api_key + "&"
                              + "text="    + text    + "&"
                              + "format="  + format  + "&"
                              + "pattern=" + pattern
//                              +"&"+"has_key="+false//ȥ����
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
//		System.out.println("LTP��ɫ��ע��Ĺؼ���"+ltpWordsnew.toString());
   
   
    public static void Write(String cc,String str)
	  {
		 String path = str;
		 try 
		 {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
			writer.write(cc+"\t");
			writer.newLine();
			writer.close();
//			System.out.println("д��ɹ�");
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
	  }
}