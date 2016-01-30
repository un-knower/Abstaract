package study_test;
import java.io.IOException;
import java.io.Reader;


public class Search {
	private Reader fr;
	  private int lineNumber = 0; // 记录当前的行数

	public Search(Reader fr) {
		this.fr = fr;
	}

	/**
	 * 通过read()函数一个一个字符的读取，当遇到句号就返回读取的一行字符串
	 */
	public String readpara() {
		
		int num = 0;
		StringBuffer sb = new StringBuffer();
		try {
			while ((num = fr.read()) != -1) {
					if (num == '。'||num == '？'||num == '!'||num == '！'||num == '?') {
					//if(num!='\n'){
					lineNumber++;
					//System.out.println("linenumber"+lineNumber);
				//	return ""+ sb.append(lineNumber).append((char) num);
					return sb.append((char) num).toString();
					//}// 返回读取的一行字符串
//					else{
//						lineNumber++;
//						char c;
//						c='\n';
//						num=lineNumber;
//						return "\n" + lineNumber;
//					}
				} else {
					sb.append((char) num);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		// 防止文本末尾没加回车换行符，以丢失文本内容
		if (sb.length() > 0) {
			lineNumber++;
			return sb.toString();
		}
		return null;
	}
	/*
	 * 通过read()函数一个一个字符的读取，当遇到回车换行就返回读取的一行字符串
	 */
	public String readLine() {
		int num = 0;
		String s;
		StringBuffer sb = new StringBuffer();
		try {
			while ((num = fr.read()) != -1) {
//				if(( String.valueOf((char)num)).trim().equals("")){
//                    continue;
//                }
                 
				sb.append((char) num);
//				if (num == '\r') {
//					lineNumber++; // 读取一句，行号加1
//					sb.append((char) num).toString(); // 返回读取的一行字符串
//					//return sb.append((char) num).toString(); // 返回读取的一行字符串
////					return "" + sb.append((char) num); // 返回读取的一行字符串
//					System.out.print(sb.toString());
//					//return sb.toString();
//				} else {
//					sb.append((char) num);
//				}
			}
			String ss = sb.toString();
		    s =ss.substring(ss.lastIndexOf(ss.trim()));			//String s = ss.substring(0, ss.lastIndexOf(ss.trim()));
			//sb.toString().trim();
			//System.out.println("问题"+s);
			//return s;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		// 防止文本末尾没加回车换行符，以丢失文本内容
		if (sb.length() > 0) {
			lineNumber++;
			return s;
		//	return sb.toString();
		}
		return null;
	}

	// 关闭输入流对象
	public void close() {
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 设置当前的行数
	public void setLineNumber(int lineNumber) {
		lineNumber = lineNumber;
	}

	// 获取当前的行数
	public  String getLineNumber() {
		
		return lineNumber<10?"0"+lineNumber:""+lineNumber;
	}

}
