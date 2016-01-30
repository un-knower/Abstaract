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
	private JButton button_Importtext = new JButton("\u5BFC\u5165\u6587\u672C");//导入文件
	private JButton button_ImportQuestion = new JButton("\u5BFC\u5165\u95EE\u9898");//导入问题
	private JButton button_ExportAnswer = new JButton("\u5BFC\u51FA\u7B54\u6848");//导出答案
	private JTextArea textArea_Answer = new JTextArea(4, 60);
	private JTextArea textArea = new JTextArea();
	private JTextArea textArea_Question = new JTextArea();
	
	static String s=null;
	final static int BUFFER_SIZE = 0x900000;// 缓冲区大小为9M
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
	static ArrayList<Integer> everyCount;//每句话的长度
	static String[] sentence;
	/**
	 * Initialize the contents of the frame.
	 */
	
	public void key_word(){
		int init_flag = CLibrary.Instance.NLPIR_Init("",1, "0");//0 GBK ；1 utf-8
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
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
//				// TODO 自动生成的方法存根
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
		button_confirm.setFont(new Font("宋体", Font.PLAIN, 12));
		button_confirm.setBounds(436, 149, 91, 23);
		frame.getContentPane().add(button_confirm);
		
				
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u9605\u8BFB\u6750\u6599");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(7, 4, 113, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel label_words = new JLabel("\u4E2A\u5B57");
		label_words.setHorizontalAlignment(SwingConstants.CENTER);
		label_words.setBounds(426, 435, 61, 23);
		frame.getContentPane().add(label_words);
		button_Importtext.setFont(new Font("宋体", Font.PLAIN, 12));
		
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
		 * 清空按钮
		 */
		JButton button_clear = new JButton("\u6E05\u7A7A");
		button_clear.setFont(new Font("宋体", Font.PLAIN, 12));
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
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(428, 4, 113, 23);
		frame.getContentPane().add(label);
		
		
	
		button_ImportQuestion.setFont(new Font("宋体", Font.PLAIN, 12));
		button_ImportQuestion.setBounds(668, 1, 91, 23);
		
		
		button_ImportQuestion.addActionListener(this);
		frame.getContentPane().add(button_ImportQuestion);
		button_ExportAnswer.setFont(new Font("宋体", Font.PLAIN, 12));
		
		
	
		button_ExportAnswer.setBounds(668, 149, 91, 23);
		button_ExportAnswer.addActionListener(this);
		frame.getContentPane().add(button_ExportAnswer);
		
		JButton btnNewButton = new JButton("\u7B54\u6848\u5BF9\u6BD4");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame main=new JFrame();

				main.setTitle("答案");

				main.setLocation(300,200);

				main.setVisible(true);
			}
		});
		btnNewButton.setBounds(554, 149, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		textArea.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent arg0) {
				// TODO 自动生成的方法存根
			//	text_AllWords.setText((getCharCount(textArea.getText()))+"个字");
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
							null, "文章为空", "警告", JOptionPane.ERROR_MESSAGE);
					
				}*/
			//	int passageLength=getsentenCount(passage);
				//if(passage.length()<words_count)
				/**
				 * 问题关键词
				 */
				//系统方法
//				key= CLibrary.Instance.NLPIR_GetKeyWords(question,50, false);
//				String[] key_arr=key.split("#");
				//过滤方法
			    int ltpflag =0;ArrayList<String> lTPWordList=null;
			    
			   
				ArrayList<String> key_arr=null;
			  /**
			   * 两次迭代
			   */
				int[] counts={0};
				ArrayList<Integer> sentenc_index =new ArrayList<>();
				ArrayList<Integer> sentenc_key =new ArrayList<>();
				StringBuilder abstractSent=null; int k=0;
				for(int cishu=0;cishu<2;cishu++){
				 if(cishu==1) {
					 //conll格式
					 ConllLTPAPI.LTPSrl(abstractSent.toString().trim());
					 //json格式
					 lTPWordList = LTPAPI.LTPSrl(abstractSent.toString().trim());
					lTPWordList.removeAll(key_arr);
					ltpflag = 1;
					 key_arr=lTPWordList;
					 System.err.println("\nLTP角色标注后的关键词:"+lTPWordList.toString());
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
						counts[i]=count;//关键词个数			
						
						}
//						System.out.println("次数为二测试："+sentenc_index.toString());
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
								System.out.println("LTP新添加句子号："+(a+1)+" 关键字个数："+max);}
								counts[a]=0;
								if(count==3)//控制增加的句子个数
									break;
					}
						}
				   // }
				 else if(cishu==0) {
					 int a=0;
					boolean flag=true;int j_flag=0;//标记有几个0
					 key_arr=KeyWord.KeyWords(question,passage);
					System.out.println("问题关键词："+key_arr.toString());
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
							System.out.println("句子号："+(a+1)+" 关键字个数："+max);
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
								System.out.println("句子号："+(a+1)+" 关键字个数："+max);
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
					counts[i]=count;//关键词个数
					
				}*/
				//System.out.println("文章总句数:"+passageLength);
//				System.out.println("文章总句数:"+passageLength+"设定长度"+words_count);
				//System.out.println("每句关键字词频"+Arrays.toString(counts));
				//System.out.println("每句话长度："+ everyCount.toString());//每句话长度
				
//				ArrayList<Integer> sentenc_index =new ArrayList<>();
//				ArrayList<Integer> sentenc_key =new ArrayList<>();
				
				
					
				
				
				/**
				 * 控制答案句长度
				 */
			/*while(flag&&j_flag!=counts.length){
				int max=0;
				for(int i=0;i<counts.length;i++){					
					
					if(max<counts[i])
								{max=counts[i];a=i;//counts[i]=0;	
							}
					}System.out.println("句子号："+(a+1)+"关键字个数："+max);
			
			int remain__words_count = words_count;
				//if(everyCount.get(a)<remain__words_count&&max!=0){
			
			if(everyCount.size()==0){break;}else
				if(everyCount.get(a)<remain__words_count){
//					if(everyCount.get(a)<remain__words_count&&counts[a]!=0){
					//System.out.println("counts[a]"+counts[a]);
					sentenc_key.add(counts[a]);
					counts[a]=0;j_flag++;
					sentenc_index.add(a+1);//下标从1开始
					remain__words_count-=everyCount.get(a);
					//System.out.println("剩余句子长度"+remain__words_count);
					flag = true;
				}else{
					flag=false;
					}
									
		}*/
			
				/*System.out.println("答案句子号（按关键词频从大到小）："+sentenc_index.toString()+
						"\n每句关键词个数："+sentenc_key.toString());*/
				abstractSent = new StringBuilder();
				Collections.sort(sentenc_index, new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO 自动生成的方法存根
						return o1.compareTo(o2);//从小到大排序
//						return o2.compareTo(o1);//从大到小排序
					}
				});
				
				 
				System.out.println("答案句子号（排序）"+sentenc_index.toString());
				boolean flags=true;
				for(int n=0;n<sentenc_index.size()-1;n++){
					///System.out.println(sentenc_index.get(n));
					if(-sentenc_index.get(n)+sentenc_index.get(n+1)==2){
						flags=false;
						sentenc_index.add(sentenc_index.get(n)+1);
						//n++;
					}
					String s1=sentence[sentenc_index.get(n)-1];
					if(s1.startsWith("这")){
						sentenc_index.add(sentenc_index.get(n)-1);
					}
					if(n==sentenc_index.size()-2&&sentence[sentenc_index.get(n+1)-1].startsWith("这")){
						sentenc_index.add(sentenc_index.get(n+1)-1);
					}
				}
				if(!flags){
				Collections.sort(sentenc_index,new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO 自动生成的方法存根
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
				System.out.println("扩展候选句为："+sentenc_index.toString());
				/**
				 * 删除疑问词
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
				
				System.out.println("删除完疑问词后候选句为："+sentenc_index.toString());
			}
//				System.out.println("问题："+question);
				String s2=question;int length=0;String nagative=null;
				
				/*
				for(int i=0;i<negative.length;i++){
					
					
					if(s2.contains(negative[i])){
						
						if(length<negative[i].length()){
							length=negative[i].length();nagative=negative[i];
						}
						
						
					}
				}s2=s2.replace(nagative, " ");
				
				System.out.println("化简问题："+s2);*/
			
				
				for(int i=0;i<sentenc_index.size();i++){
					
						String s1=sentence[sentenc_index.get(i)-1];
				       
					abstractSent.append(s1.trim()+"。");
					s=s1;
					
					
				}
			}	
				
				/**
				 * 相似度计算
				 */
				boolean flag=true;
				for(int i=0;i<sentenc_index.size();i++){
					/**
					 * 删除问题与候选句相似度很高的句子
					 */
					String s1=sentence[sentenc_index.get(i)-1];	     
							
				//普希金“独有的一种绘画状态”是怎样的状态？
					SemanticSimilarity similarity = SemanticSimilarity.getInstance();
				double similarityValue = similarity.getSimilarity(s1.trim(), question);
				System.out.println("第"+sentenc_index.get(i)+"句:问题="+similarityValue);
//				System.out.println("第"+sentenc_index.get(i)+"句："+s1.trim()+"\n相似度:"+similarityValue);
				//abstractSent.append(sentence[sentenc_index.get(i)-1]+"。");
				if(similarityValue>0.7){
					/**
					 * 相似度大于值
					 */
					 flag=false;
					System.out.println("第"+sentenc_index.get(i)+"句相似度大于设定值,删除");
					sentenc_index.remove(i);
					
				}
			}
				if(!flag) 
					System.out.println("删除完与问题相似度大的候选句字号"+sentenc_index.toString());
				
				flag=true;
				for(int i=0;i<sentenc_index.size()-1;i++){
					/**
					 * 删除候选句答案相似度高句子长度大的句子、
					 */
					for(int j=i+1;j<sentenc_index.size();j++){
						String s1=sentence[sentenc_index.get(i)-1];	 
						String s2=sentence[sentenc_index.get(j)-1];
						SemanticSimilarity similarity = SemanticSimilarity.getInstance();
						double similarityValue = similarity.getSimilarity(s1.trim(), s2.trim());
						System.out.println("第"+sentenc_index.get(i)+"句："+"第"+sentenc_index.get(j)+"句="+similarityValue);
//						System.out.println("第"+sentenc_index.get(i)+"句："+s1.trim()+"\n"+"第"+sentenc_index.get(j)+"句："+s2.trim()+"\n相似度"+similarityValue);
						
						if(similarityValue>0.7){
							int n=0;
							flag=false;
							int s1_length = everyCount.get(sentenc_index.get(i)-1);
							int s2_length = everyCount.get(sentenc_index.get(j)-1);
							n=(s1_length>=s2_length?i:j);
//							if(s1_length>s2_length){
//								//System.out.println("第"+sentenc_index.get(i)+"句长度较长，删除");
////								     
////								System.out.println(s1_length);  
////								System.out.println(s2_length);  
//								n=i;
//								//sentenc_index.remove(i);
//							}else{
//								//System.out.println("第"+sentenc_index.get(j)+"句长度较长，删除");
//							     n=j;
//	//							System.out.println(s1_length);  
//	//							System.out.println(s2_length);  
//							//	sentenc_index.remove(j);
//							}
							System.out.println("第"+sentenc_index.get(i)+"句和"+"第"+sentenc_index.get(j)+"句相似度大于设定值，删除句子较长的第"+sentenc_index.get(n)+ "句");
							System.out.println("第"+sentenc_index.get(i)+"句长度:"+s1_length);  
							System.out.println("第"+sentenc_index.get(j)+"句长度:"+s2_length);  
							sentenc_index.remove(n);
							
						}
					}
				}
				if(!flag) 
					System.out.println("删除完相邻相似度较大后的答案句子号"+sentenc_index.toString());
				
				//test();
			
				//textArea_result.setText("文章总字数"+getCharCount(passage)+"文章总句数"+passageLength+"设定长度"+words_count+"\n"+abstractSent.toString());
						
						
						//System.out.println(lTPWordList.toString());
			
		/*				
						textArea_Answer.setText(abstractSent.toString().trim());
//				System.out.println("每句话长度："+ everyCount.toString());
						textArea_Answer.addCaretListener(new CaretListener() {
							
							@Override
							public void caretUpdate(CaretEvent arg0) {
								// TODO 自动生成的方法存根
							//	text_AllWords.setText((getCharCount(textArea.getText()))+"个字");
								label_words.setText((getCharCount(textArea_Answer.getText()))+"个字");
							}
						});*/
				
				CLibrary.Instance.NLPIR_Exit();
//				+"设定长度"+words_count+"\n"+passage);
//				textArea_result.setText(passage.length()+"设定长度"+words_count+"\n"+passage);
			
				
				
				/**
				 * 输出到答案框
				 */
				abstractSent = new StringBuilder();
				for(int i=0;i<sentenc_index.size();i++){
					
					String s1=sentence[sentenc_index.get(i)-1];
			       
				abstractSent.append(s1.trim()+"。");				
				
				
			}	
			textArea_Answer.setText(abstractSent.toString().trim());
			textArea_Answer.addCaretListener(new CaretListener() {
				
				@Override
				public void caretUpdate(CaretEvent arg0) {
					// TODO 自动生成的方法存根
				//	text_AllWords.setText((getCharCount(textArea.getText()))+"个字");
					label_words.setText((getCharCount(textArea_Answer.getText()))+"个字");
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
			counts[i]=count;//关键词个数
		
			
		}
		
		return counts;
		
	}
	public static int getCharCount(String s){//统计字符总字数
		int charCount=0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)!=' '&&s.charAt(i)!='\n'){
				charCount++;
			}
		}
		return charCount;
	}

	 
	public static int getsentenCount(String s){//统计文章总句子数
		//int lengthCount=1;
		 int lengthCount=1;
		 everyCount=new ArrayList<>();
		sentence=s.split("[。？!！?]");
 		
		for(int i=0;i<sentence.length;i++){
			int sentenCount=getCharCount(sentence[i]+"。");
			everyCount.add(sentenCount);
			//lengthCount++;
			//System.out.println("sentence"+i+sentence[i]);
		}
		lengthCount=sentence.length;
		//System.out.println(lengthCount);
		return lengthCount;
	}
	public static String getIndexPassage(String s){//返回句子标号文章
		StringBuffer buffer = new StringBuffer();
		int sentenceCount = getsentenCount(s);
		//System.out.println(sentenceCount);
		for(int i=0;i<sentenceCount;i++){
			if(i==0){
				 buffer.append("   "+(i+1)+sentence[i].trim()+"。");
			}else
			if(sentence[i].contains("\n"))
			  buffer.append("\r\n   "+(i+1)+sentence[i].trim()+"。");
			else{
				 buffer.append((i+1)+sentence[i].trim()+"。");
			}
		}
		
		return buffer.toString();
		
	}
	public static String getdesPassage(String s,int wordCount){//返回指定字数文章
		

		
		return s;
		
	}
	String importFile(String str){
		String sall="";
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(str);//标签
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"txt files", "txt");
		chooser.setFileFilter(filter);
//		if(chooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION ){
//		      //解释下这里,弹出个对话框,可以选择要上传的文件,如果选择了,就把选择的文件的绝对路径打印出来,有了绝对路径,通过JTextField的settext就能设置进去了,那个我没写
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
			byte[] dst = new byte[BUFFER_SIZE1];// 每次读出9M的内容   
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
		// TODO 自动生成的方法存根
		if (e.getSource() == button_Importtext) {
			textArea.setText(importFile("导入文件"));
	    }else if(e.getSource()==button_ImportQuestion){
			//importFile("导入问题");
			textArea_Question.setText(importFile("导入问题"));
		}else if(e.getSource()==button_ExportAnswer){//导出答案监听
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("导出答案");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"txt files", "txt");
			chooser.setFileFilter(filter);
			int i = chooser.showSaveDialog(this.frame);
			if (i == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile(); //得到选择的文件
				try {
					FileOutputStream out = new FileOutputStream(f);  //得到文件输出流
					out.write(textArea_Answer.getText().getBytes()); //写出文件
					out.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
			
	
	}
}
