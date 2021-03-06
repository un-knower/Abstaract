/*
 * Copyright (C) 2008 SKLSDE(State Key Laboratory of Software Development and Environment, Beihang University)., All Rights Reserved.
 */
package similarity.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

import wordsimilarity.WordSimilarity;


/**
 * DOCUMENT ME!
 *
 * @author Yingqiang Wu
 * @version 1.0
  */
public class WordSimilarityTests {
//	public class WordSimilarityTests extends TestCase {
    public void test_loadGlossary(){
        WordSimilarity.loadGlossary();
    }
    /**
     * test the method {@link WordSimilarity#disPrimitive(String, String)}.
     */
    public void test_disPrimitive(){
        int dis = WordSimilarity.disPrimitive("����", "��");
        System.out.println("���� and �� dis : "+ dis);
    }
    
    public void test_simPrimitive(){
        double simP = WordSimilarity.simPrimitive("����", "��");
        System.out.println("���� and �� sim : "+ simP);
    }
    public void test_simWord(){
        String word1 = "��";
        String word2 = "��";
        double sim = WordSimilarity.simWord(word2, word1);
        System.out.println(sim);
    }
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//        BufferedReader reader = new BufferedReader(new FileReader(
//                "dict/glossary.dat"));
//        Set<String> set = new HashSet<String>();
///*        String line = reader.readLine();
//        while (line != null) {
//            //System.out.println(line);
//            line = line.replaceAll("\\s+", " ");
//            String[] strs = line.split(" ");
//            for (int i = 0; i < strs.length; i++) {
//               // System.out.print(" " + strs[i]);
//            }
//            System.out.println();
//            set.add(strs[1]);
//            line = reader.readLine();
//        }*/
//        System.out.println(set.size());
//        for (String name : set) {
//            System.out.println(name);
//        }
         WordSimilarity.loadGlossary();
         double simval=WordSimilarity.simWord("��ϵ","��");
         System.out.println("����"+simval);
        
        
        WordSimilarityTests tests = new WordSimilarityTests();
        tests.test_disPrimitive();
        tests.test_simPrimitive();
        tests.test_simWord();
    }
    
}

