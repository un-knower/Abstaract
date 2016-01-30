package study_test;
import java.io.IOException;
import java.io.Reader;


public class Search {
	private Reader fr;
	  private int lineNumber = 0; // ��¼��ǰ������

	public Search(Reader fr) {
		this.fr = fr;
	}

	/**
	 * ͨ��read()����һ��һ���ַ��Ķ�ȡ����������žͷ��ض�ȡ��һ���ַ���
	 */
	public String readpara() {
		
		int num = 0;
		StringBuffer sb = new StringBuffer();
		try {
			while ((num = fr.read()) != -1) {
					if (num == '��'||num == '��'||num == '!'||num == '��'||num == '?') {
					//if(num!='\n'){
					lineNumber++;
					//System.out.println("linenumber"+lineNumber);
				//	return ""+ sb.append(lineNumber).append((char) num);
					return sb.append((char) num).toString();
					//}// ���ض�ȡ��һ���ַ���
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

		// ��ֹ�ı�ĩβû�ӻس����з����Զ�ʧ�ı�����
		if (sb.length() > 0) {
			lineNumber++;
			return sb.toString();
		}
		return null;
	}
	/*
	 * ͨ��read()����һ��һ���ַ��Ķ�ȡ���������س����оͷ��ض�ȡ��һ���ַ���
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
//					lineNumber++; // ��ȡһ�䣬�кż�1
//					sb.append((char) num).toString(); // ���ض�ȡ��һ���ַ���
//					//return sb.append((char) num).toString(); // ���ض�ȡ��һ���ַ���
////					return "" + sb.append((char) num); // ���ض�ȡ��һ���ַ���
//					System.out.print(sb.toString());
//					//return sb.toString();
//				} else {
//					sb.append((char) num);
//				}
			}
			String ss = sb.toString();
		    s =ss.substring(ss.lastIndexOf(ss.trim()));			//String s = ss.substring(0, ss.lastIndexOf(ss.trim()));
			//sb.toString().trim();
			//System.out.println("����"+s);
			//return s;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		// ��ֹ�ı�ĩβû�ӻس����з����Զ�ʧ�ı�����
		if (sb.length() > 0) {
			lineNumber++;
			return s;
		//	return sb.toString();
		}
		return null;
	}

	// �ر�����������
	public void close() {
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���õ�ǰ������
	public void setLineNumber(int lineNumber) {
		lineNumber = lineNumber;
	}

	// ��ȡ��ǰ������
	public  String getLineNumber() {
		
		return lineNumber<10?"0"+lineNumber:""+lineNumber;
	}

}