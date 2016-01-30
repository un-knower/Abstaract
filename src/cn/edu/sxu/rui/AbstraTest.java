package cn.edu.sxu.rui;

import inter.CLibrary;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextPane;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;

import ruc.irm.similarity.sentence.morphology.SemanticSimilarity;

import org.junit.Test;

import ruc.irm.similarity.sentence.morphology.SemanticSimilarity;

import java.awt.Font;

import ltp.edu.sxu.rui.ConllLTPAPI;
import ltp.edu.sxu.rui.LTPAPI;

public class AbstraTest implements ActionListener{
	
	private JFrame frame;
	private JButton button_Importtext = new JButton("\u5BFC\u5165\u6587\u672C");//�����ļ�
	private JButton button_ImportQuestion = new JButton("\u5BFC\u5165\u95EE\u9898");//��������
	private JButton button_ExportAnswer = new JButton("\u5BFC\u51FA\u7B54\u6848");//������
	private JTextArea textArea_Answer = new JTextArea(4, 60);
	private JTextArea textArea = new JTextArea();
	private JTextArea textArea_Question = new JTextArea();
	
	static String s=null;
	final static int BUFFER_SIZE = 0x900000;// ��������СΪ9M
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AbstraTest window = new AbstraTest();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	/**
	 * Create the application.
	 */
	public AbstraTest() {
		initialize();
		key_word();
		
	}
	String passage=null;
	static String question = null;
	int words_count=400;
	String key="";
	static ArrayList<Integer> everyCount;//ÿ�仰�ĳ���
	static String[] sentence;
	/**
	 * Initialize the contents of the frame.
	 */
	
	public void key_word(){
		int init_flag = CLibrary.Instance.NLPIR_Init("",1, "0");//0 GBK ��1 utf-8
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("��ʼ��ʧ�ܣ�fail reason is "+nativeBytes);
			return;
		}
		
	}
	@Test
	private void initialize() {
		
		frame = new JFrame();
		frame.setForeground(Color.CYAN);
		frame.setBackground(Color.CYAN);
		frame.setTitle("\u9AD8\u8003\u9605\u8BFB\u7406\u89E3\u81EA\u52A8\u95EE\u7B54\u7CFB\u7EDF");
		frame.setBounds(100, 100, 785, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ItemListener Item_Listen=null;
		ButtonGroup group=new ButtonGroup();
		//int words_count =0;
		
//		Item_Listen = new ItemListener() {
//			
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO �Զ����ɵķ������
//				 if (e.getSource() == radioButton_100) {
//					 words_count=200;
//			        }else if (
//			        		e.getSource() == radioButton_200) {
//					 words_count=300;
//			        }else if (
//			        		e.getSource() == radioButton_300){
//			        	words_count=400;
//			        }
//			}
//		};
		
		JLabel lblNewLabel = new JLabel("\u7B54\u6848");
		lblNewLabel.setBounds(397, 289, 61, 15);
		frame.getContentPane().add(lblNewLabel);
		
//		 final JTextArea textArea = new JTextArea();
		 textArea.setLineWrap(true);
		 textArea.setWrapStyleWord(true);
		// passage=textArea.getText();
		 textArea.setSize(437, 203);
		 
		textArea.setLocation(1, 1);
		textArea.setColumns(8);
		textArea.setBounds(1, 30, 375, 374);
		frame.getContentPane().add(textArea);
		final JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 26, 394, 405);
		frame.getContentPane().add(scrollPane);
		
		JButton button_confirm = new JButton("\u81EA\u52A8\u4F5C\u7B54");
		button_confirm.setFont(new Font("����", Font.PLAIN, 12));
		button_confirm.setBounds(436, 149, 91, 23);
		frame.getContentPane().add(button_confirm);
		
				
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u9605\u8BFB\u6750\u6599");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(7, 4, 113, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel label_words = new JLabel("\u4E2A\u5B57");
		label_words.setHorizontalAlignment(SwingConstants.CENTER);
		label_words.setBounds(426, 435, 61, 23);
		frame.getContentPane().add(label_words);
		button_Importtext.setFont(new Font("����", Font.PLAIN, 12));
		
//		JButton button_Importtext = new JButton("\u5BFC\u5165\u6587\u672C");
		button_Importtext.setBounds(291, 1, 84, 23);
		button_Importtext.setRequestFocusEnabled(false); 
		button_Importtext.addActionListener(this);
		
		frame.getContentPane().add(button_Importtext);
//		

		textArea_Question.setWrapStyleWord(true);
		textArea_Question.setLineWrap(true);
		textArea_Question.setColumns(8);
		textArea_Question.setBounds(416, 27, 353, 113);
		frame.getContentPane().add(textArea_Question);
		JScrollPane scrollPane_1 = new JScrollPane(textArea_Question);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(426, 26, 333, 113);
		frame.getContentPane().add(scrollPane_1);
		
				

		textArea_Answer.setBounds(436, 182, 323, 249);
		frame.getContentPane().add(textArea_Answer);
		textArea_Answer.setWrapStyleWord(true);
		textArea_Answer.setLineWrap(true);
		JScrollPane scrollPane_Answer = new JScrollPane(textArea_Answer);
		scrollPane_Answer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_Answer.setBounds(428, 181, 331, 250);
		frame.getContentPane().add(scrollPane_Answer);
		/**
		 * ��հ�ť
		 */
		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.setFont(new Font("����", Font.PLAIN, 12));
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textArea_Question.setText("");
				textArea_Answer.setText("");
				key_word();
				//System.out.println("\n********************");
			}
		});
		button_clear.setBounds(683, 435, 76, 23);
		frame.getContentPane().add(button_clear);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u95EE\u9898");
		label.setFont(new Font("����", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(428, 4, 113, 23);
		frame.getContentPane().add(label);
		
		
	
		button_ImportQuestion.setFont(new Font("����", Font.PLAIN, 12));
		button_ImportQuestion.setBounds(668, 1, 91, 23);
		
		
		button_ImportQuestion.addActionListener(this);
		frame.getContentPane().add(button_ImportQuestion);
		button_ExportAnswer.setFont(new Font("����", Font.PLAIN, 12));
		
		
	
		button_ExportAnswer.setBounds(668, 149, 91, 23);
		button_ExportAnswer.addActionListener(this);
		frame.getContentPane().add(button_ExportAnswer);
		
		JButton btnNewButton = new JButton("\u7B54\u6848\u5BF9\u6BD4");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame main=new JFrame();

				main.setTitle("��");

				main.setLocation(300,200);

				main.setVisible(true);
			}
		});
		btnNewButton.setBounds(554, 149, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		textArea.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent arg0) {
				// TODO �Զ����ɵķ������
			//	text_AllWords.setText((getCharCount(textArea.getText()))+"����");
				//count_passage.setText((getCharCount(textArea.getText()))+"");
			}
		});
		
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(passage);
				passage=textArea.getText();
				question=textArea_Question.getText();
				textArea.setText(getIndexPassage(passage));
				/*if(passage==null){
					 
					 JOptionPane.showMessageDialog(
							null, "����Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					
				}*/
			//	int passageLength=getsentenCount(passage);
				//if(passage.length()<words_count)
				/**
				 * ����ؼ���
				 */
				//ϵͳ����
//				key= CLibrary.Instance.NLPIR_GetKeyWords(question,50, false);
//				String[] key_arr=key.split("#");
				//���˷���
			    int ltpflag =0;ArrayList<String> lTPWordList=null;
			    
			   
				ArrayList<String> key_arr=null;
			  /**
			   * ���ε���
			   */
				int[] counts={0};
				ArrayList<Integer> sentenc_index =new ArrayList<>();
				ArrayList<Integer> sentenc_key =new ArrayList<>();
				StringBuilder abstractSent=null; int k=0;
				for(int cishu=0;cishu<2;cishu++){
				 if(cishu==1) {
					 //conll��ʽ
					 ConllLTPAPI.LTPSrl(abstractSent.toString().trim());
					 //json��ʽ
					 lTPWordList = LTPAPI.LTPSrl(abstractSent.toString().trim());
					lTPWordList.removeAll(key_arr);
					ltpflag = 1;
					 key_arr=lTPWordList;
					 System.err.println("\nLTP��ɫ��ע��Ĺؼ���:"+lTPWordList.toString());
					counts=new int[sentence.length-1];
						for(int i=0;i<sentence.length-1;i++){
						int count=0;//System.out.println(sentence[i].trim());
				//	if(sentence[i]!=null)
						for(int j=0;j<lTPWordList.size();j++){
							//System.out.println(key_arr[j]);
							if(sentence[i].contains(lTPWordList.get(j))){
								count++;
//								 System.out.println(sentence[i]+"\nlTPWordList.get(j)"+lTPWordList.get(j));
							}
							
						}
						counts[i]=count;//�ؼ��ʸ���			
						
						}
//						System.out.println("����Ϊ�����ԣ�"+sentenc_index.toString());
						int j_flag=0;
						//for(k=0;k<4;k++){
						int count=0;
						while(true){
							int a=0;
							int max=0;
							for(int i=0;i<counts.length;i++){					
								
								if(max<counts[i])
											{max=counts[i];a=i;//counts[i]=0;	
										}
								}
								if(!sentenc_index.contains(a+1)){
								count++;	
								sentenc_key.add(counts[a]);sentenc_index.add(a+1);j_flag++;
								System.out.println("LTP�����Ӿ��Ӻţ�"+(a+1)+" �ؼ��ָ�����"+max);}
								counts[a]=0;
								if(count==3)//�������ӵľ��Ӹ���
									break;
					}
						}
				   // }
				 else if(cishu==0) {
					 int a=0;
					boolean flag=true;int j_flag=0;//����м���0
					 key_arr=KeyWord.KeyWords(question,passage);
					System.out.println("����ؼ��ʣ�"+key_arr.toString());
					counts=getWordCount(key_arr);
					while(flag&&j_flag!=counts.length){
						//int max=0;
					for(k=0;k<4;k++){
						int max=0;
						for(int i=0;i<counts.length;i++){					
							
							if(max<counts[i])
										{max=counts[i];a=i;//counts[i]=0;	
									}
							}
							System.out.println("���Ӻţ�"+(a+1)+" �ؼ��ָ�����"+max);
							sentenc_key.add(counts[a]);counts[a]=0;sentenc_index.add(a+1);j_flag++;
							
						}
					
					
					while(k==4&&flag!=false){				
						int value=sentenc_key.get(k-1);
//						System.out.println("value"+value);
							int max=0;
							for(int i=0;i<counts.length;i++){
								if(max<counts[i])
							{max=counts[i];a=i;}}
//							System.out.println("max"+max);
							//int value=sentenc_key.get(k-1);
							//int max=0;
													
							if(max==value){
								System.out.println("���Ӻţ�"+(a+1)+" �ؼ��ָ�����"+max);
								sentenc_key.add(counts[a]);counts[a]=0;sentenc_index.add(a+1);j_flag++;
							}else{flag=false;break;}
//				
						}
							//break;
						
					}
				 }
				
				
				
				
				
			/*	int[] counts=new int[sentence.length-1];
				for(int i=0;i<sentence.length-1;i++){
					int count=0;//System.out.println(sentence[i].trim());
					for(int j=0;j<key_arr.size();j++){
						//System.out.println(key_arr[j]);
						if(sentence[i].contains(key_arr.get(j))){
							count++;//System.out.println("count"+count);
						}
						
					}
					counts[i]=count;//�ؼ��ʸ���
					
				}*/
				//System.out.println("�����ܾ���:"+passageLength);
//				System.out.println("�����ܾ���:"+passageLength+"�趨����"+words_count);
				//System.out.println("ÿ��ؼ��ִ�Ƶ"+Arrays.toString(counts));
				//System.out.println("ÿ�仰���ȣ�"+ everyCount.toString());//ÿ�仰����
				
//				ArrayList<Integer> sentenc_index =new ArrayList<>();
//				ArrayList<Integer> sentenc_key =new ArrayList<>();
				
				
					
				
				
				/**
				 * ���ƴ𰸾䳤��
				 */
			/*while(flag&&j_flag!=counts.length){
				int max=0;
				for(int i=0;i<counts.length;i++){					
					
					if(max<counts[i])
								{max=counts[i];a=i;//counts[i]=0;	
							}
					}System.out.println("���Ӻţ�"+(a+1)+"�ؼ��ָ�����"+max);
			
			int remain__words_count = words_count;
				//if(everyCount.get(a)<remain__words_count&&max!=0){
			
			if(everyCount.size()==0){break;}else
				if(everyCount.get(a)<remain__words_count){
//					if(everyCount.get(a)<remain__words_count&&counts[a]!=0){
					//System.out.println("counts[a]"+counts[a]);
					sentenc_key.add(counts[a]);
					counts[a]=0;j_flag++;
					sentenc_index.add(a+1);//�±��1��ʼ
					remain__words_count-=everyCount.get(a);
					//System.out.println("ʣ����ӳ���"+remain__words_count);
					flag = true;
				}else{
					flag=false;
					}
									
		}*/
			
				/*System.out.println("�𰸾��Ӻţ����ؼ���Ƶ�Ӵ�С����"+sentenc_index.toString()+
						"\nÿ��ؼ��ʸ�����"+sentenc_key.toString());*/
				abstractSent = new StringBuilder();
				Collections.sort(sentenc_index, new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO �Զ����ɵķ������
						return o1.compareTo(o2);//��С��������
//						return o2.compareTo(o1);//�Ӵ�С����
					}
				});
				
				 
				System.out.println("�𰸾��Ӻţ�����"+sentenc_index.toString());
				boolean flags=true;
				for(int n=0;n<sentenc_index.size()-1;n++){
					///System.out.println(sentenc_index.get(n));
					if(-sentenc_index.get(n)+sentenc_index.get(n+1)==2){
						flags=false;
						sentenc_index.add(sentenc_index.get(n)+1);
						//n++;
					}
					String s1=sentence[sentenc_index.get(n)-1];
					if(s1.startsWith("��")){
						sentenc_index.add(sentenc_index.get(n)-1);
					}
					if(n==sentenc_index.size()-2&&sentence[sentenc_index.get(n+1)-1].startsWith("��")){
						sentenc_index.add(sentenc_index.get(n+1)-1);
					}
				}
				if(!flags){
				Collections.sort(sentenc_index,new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO �Զ����ɵķ������
						return o1.compareTo(o2);
					}
				});}
				for(int n=0;n<sentenc_index.size()-1;n++){
					//System.out.println(sentenc_index.get(n));
					if(-sentenc_index.get(n)+sentenc_index.get(n+1)==0){
						//flags=false;
						sentenc_index.remove(n);
						//n++;
					}
					
				}
				System.out.println("��չ��ѡ��Ϊ��"+sentenc_index.toString());
				/**
				 * ɾ�����ʴ�
				 */
			if(cishu==1){
				new QuestionData();
				String[] negative= new String[QuestionData.POSITIVE_SEMEMES.length];
				negative=QuestionData.POSITIVE_SEMEMES;
				for(int n=0;n<sentenc_index.size();n++){
					//System.out.println(sentenc_index.get(n));
					String s1=sentence[sentenc_index.get(n)-1];
					for(int i=0;i<negative.length;i++){
						//System.out.println(negative[i]);
						if(s1.contains(negative[i])){
							//System.out.println(s1);
//							System.out.println(sentence[sentenc_index.get(n)-1]);
							//sentence[sentenc_index.get(n)-1]=null;
							
							sentenc_index.remove(sentenc_index.get(n));
							
							
							
						}
					}
								
					
				}
				
				System.out.println("ɾ�������ʴʺ��ѡ��Ϊ��"+sentenc_index.toString());
			}
//				System.out.println("���⣺"+question);
				String s2=question;int length=0;String nagative=null;
				
				/*
				for(int i=0;i<negative.length;i++){
					
					
					if(s2.contains(negative[i])){
						
						if(length<negative[i].length()){
							length=negative[i].length();nagative=negative[i];
						}
						
						
					}
				}s2=s2.replace(nagative, " ");
				
				System.out.println("�������⣺"+s2);*/
			
				
				for(int i=0;i<sentenc_index.size();i++){
					
						String s1=sentence[sentenc_index.get(i)-1];
				       
					abstractSent.append(s1.trim()+"��");
					s=s1;
					
					
				}
			}	
				
				/**
				 * ���ƶȼ���
				 */
				boolean flag=true;
				for(int i=0;i<sentenc_index.size();i++){
					/**
					 * ɾ���������ѡ�����ƶȺܸߵľ���
					 */
					String s1=sentence[sentenc_index.get(i)-1];	     
							
				//��ϣ�𡰶��е�һ�ֻ滭״̬����������״̬��
					SemanticSimilarity similarity = SemanticSimilarity.getInstance();
				double similarityValue = similarity.getSimilarity(s1.trim(), question);
				System.out.println("��"+sentenc_index.get(i)+"��:����="+similarityValue);
//				System.out.println("��"+sentenc_index.get(i)+"�䣺"+s1.trim()+"\n���ƶ�:"+similarityValue);
				//abstractSent.append(sentence[sentenc_index.get(i)-1]+"��");
				if(similarityValue>0.7){
					/**
					 * ���ƶȴ���ֵ
					 */
					 flag=false;
					System.out.println("��"+sentenc_index.get(i)+"�����ƶȴ����趨ֵ,ɾ��");
					sentenc_index.remove(i);
					
				}
			}
				if(!flag) 
					System.out.println("ɾ�������������ƶȴ�ĺ�ѡ���ֺ�"+sentenc_index.toString());
				
				flag=true;
				for(int i=0;i<sentenc_index.size()-1;i++){
					/**
					 * ɾ����ѡ������ƶȸ߾��ӳ��ȴ�ľ��ӡ�
					 */
					for(int j=i+1;j<sentenc_index.size();j++){
						String s1=sentence[sentenc_index.get(i)-1];	 
						String s2=sentence[sentenc_index.get(j)-1];
						SemanticSimilarity similarity = SemanticSimilarity.getInstance();
						double similarityValue = similarity.getSimilarity(s1.trim(), s2.trim());
						System.out.println("��"+sentenc_index.get(i)+"�䣺"+"��"+sentenc_index.get(j)+"��="+similarityValue);
//						System.out.println("��"+sentenc_index.get(i)+"�䣺"+s1.trim()+"\n"+"��"+sentenc_index.get(j)+"�䣺"+s2.trim()+"\n���ƶ�"+similarityValue);
						
						if(similarityValue>0.7){
							int n=0;
							flag=false;
							int s1_length = everyCount.get(sentenc_index.get(i)-1);
							int s2_length = everyCount.get(sentenc_index.get(j)-1);
							n=(s1_length>=s2_length?i:j);
//							if(s1_length>s2_length){
//								//System.out.println("��"+sentenc_index.get(i)+"�䳤�Ƚϳ���ɾ��");
////								     
////								System.out.println(s1_length);  
////								System.out.println(s2_length);  
//								n=i;
//								//sentenc_index.remove(i);
//							}else{
//								//System.out.println("��"+sentenc_index.get(j)+"�䳤�Ƚϳ���ɾ��");
//							     n=j;
//	//							System.out.println(s1_length);  
//	//							System.out.println(s2_length);  
//							//	sentenc_index.remove(j);
//							}
							System.out.println("��"+sentenc_index.get(i)+"���"+"��"+sentenc_index.get(j)+"�����ƶȴ����趨ֵ��ɾ�����ӽϳ��ĵ�"+sentenc_index.get(n)+ "��");
							System.out.println("��"+sentenc_index.get(i)+"�䳤��:"+s1_length);  
							System.out.println("��"+sentenc_index.get(j)+"�䳤��:"+s2_length);  
							sentenc_index.remove(n);
							
						}
					}
				}
				if(!flag) 
					System.out.println("ɾ�����������ƶȽϴ��Ĵ𰸾��Ӻ�"+sentenc_index.toString());
				
				//test();
			
				//textArea_result.setText("����������"+getCharCount(passage)+"�����ܾ���"+passageLength+"�趨����"+words_count+"\n"+abstractSent.toString());
						
						
						//System.out.println(lTPWordList.toString());
			
		/*				
						textArea_Answer.setText(abstractSent.toString().trim());
//				System.out.println("ÿ�仰���ȣ�"+ everyCount.toString());
						textArea_Answer.addCaretListener(new CaretListener() {
							
							@Override
							public void caretUpdate(CaretEvent arg0) {
								// TODO �Զ����ɵķ������
							//	text_AllWords.setText((getCharCount(textArea.getText()))+"����");
								label_words.setText((getCharCount(textArea_Answer.getText()))+"����");
							}
						});*/
				
				CLibrary.Instance.NLPIR_Exit();
//				+"�趨����"+words_count+"\n"+passage);
//				textArea_result.setText(passage.length()+"�趨����"+words_count+"\n"+passage);
			
				
				
				/**
				 * ������𰸿�
				 */
				abstractSent = new StringBuilder();
				for(int i=0;i<sentenc_index.size();i++){
					
					String s1=sentence[sentenc_index.get(i)-1];
			       
				abstractSent.append(s1.trim()+"��");				
				
				
			}	
			textArea_Answer.setText(abstractSent.toString().trim());
			textArea_Answer.addCaretListener(new CaretListener() {
				
				@Override
				public void caretUpdate(CaretEvent arg0) {
					// TODO �Զ����ɵķ������
				//	text_AllWords.setText((getCharCount(textArea.getText()))+"����");
					label_words.setText((getCharCount(textArea_Answer.getText()))+"����");
				}
			});
		}
	});		
					
	}
	public static int[] getWordCount(ArrayList<String> list){
		
		int[] counts=new int[sentence.length-1];
			for(int i=0;i<sentence.length-1;i++){
			int count=0;//System.out.println(sentence[i].trim());
	//	if(sentence[i]!=null)
			for(int j=0;j<list.size();j++){
				//System.out.println(key_arr[j]);
				if(sentence[i].contains(list.get(j))){
					count++;//System.out.println("count"+count);
				}
				
			}
			counts[i]=count;//�ؼ��ʸ���
		
			
		}
		
		return counts;
		
	}
	public static int getCharCount(String s){//ͳ���ַ�������
		int charCount=0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)!=' '&&s.charAt(i)!='\n'){
				charCount++;
			}
		}
		return charCount;
	}

	 
	public static int getsentenCount(String s){//ͳ�������ܾ�����
		//int lengthCount=1;
		 int lengthCount=1;
		 everyCount=new ArrayList<>();
		sentence=s.split("[����!��?]");
 		
		for(int i=0;i<sentence.length;i++){
			int sentenCount=getCharCount(sentence[i]+"��");
			everyCount.add(sentenCount);
			//lengthCount++;
			//System.out.println("sentence"+i+sentence[i]);
		}
		lengthCount=sentence.length;
		//System.out.println(lengthCount);
		return lengthCount;
	}
	public static String getIndexPassage(String s){//���ؾ��ӱ������
		StringBuffer buffer = new StringBuffer();
		int sentenceCount = getsentenCount(s);
		//System.out.println(sentenceCount);
		for(int i=0;i<sentenceCount;i++){
			if(i==0){
				 buffer.append("   "+(i+1)+sentence[i].trim()+"��");
			}else
			if(sentence[i].contains("\n"))
			  buffer.append("\r\n   "+(i+1)+sentence[i].trim()+"��");
			else{
				 buffer.append((i+1)+sentence[i].trim()+"��");
			}
		}
		
		return buffer.toString();
		
	}
	public static String getdesPassage(String s,int wordCount){//����ָ����������
		

		
		return s;
		
	}
	String importFile(String str){
		String sall="";
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(str);//��ǩ
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"txt files", "txt");
		chooser.setFileFilter(filter);
//		if(chooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION ){
//		      //����������,�������Ի���,����ѡ��Ҫ�ϴ����ļ�,���ѡ����,�Ͱ�ѡ����ļ��ľ���·����ӡ����,���˾���·��,ͨ��JTextField��settext�������ý�ȥ��,�Ǹ���ûд
//		      System.out.println(chooser.getSelectedFile().getAbsolutePath());
//			}
		int i = chooser.showOpenDialog(this.frame);
		if (i == JFileChooser.APPROVE_OPTION) {
			String s="";
//			String sall="";
			s=chooser.getSelectedFile().getPath();
			int BUFFER_SIZE1 = 0x900000;
			File file =new File (s);
	        MappedByteBuffer inputBuffer = null;
			try {
				inputBuffer = new RandomAccessFile(file, "r").
				getChannel().map(FileChannel.MapMode.READ_ONLY,0,file.length());
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			byte[] dst = new byte[BUFFER_SIZE1];// ÿ�ζ���9M������   
			for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE1) {
				if (inputBuffer.capacity() - offset >= BUFFER_SIZE1) {
					for (int ii = 0; ii < BUFFER_SIZE1; ii++)  
						dst[ii] = inputBuffer.get(offset + ii);
					} 
				else {
						for (int ij = 0; ij < inputBuffer.capacity() - offset; ij++)
							dst[ij] = inputBuffer.get(offset + ij);
						}
				int length = (inputBuffer.capacity() % BUFFER_SIZE1 == 0) ? BUFFER_SIZE1:inputBuffer.capacity() % BUFFER_SIZE;
				sall=new String(dst, 0, length);
			} 
			//return sall;
			//textArea.setText(sall);
		}
		if (i == JFileChooser.CANCEL_OPTION)
			return null;
		return sall;
	 };
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if (e.getSource() == button_Importtext) {
			textArea.setText(importFile("�����ļ�"));
	    }else if(e.getSource()==button_ImportQuestion){
			//importFile("��������");
			textArea_Question.setText(importFile("��������"));
		}else if(e.getSource()==button_ExportAnswer){//�����𰸼���
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("������");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"txt files", "txt");
			chooser.setFileFilter(filter);
			int i = chooser.showSaveDialog(this.frame);
			if (i == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile(); //�õ�ѡ����ļ�
				try {
					FileOutputStream out = new FileOutputStream(f);  //�õ��ļ������
					out.write(textArea_Answer.getText().getBytes()); //д���ļ�
					out.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
			
	
	}
}