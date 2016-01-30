package similarity.test;



import ruc.irm.similarity.sentence.morphology.SemanticSimilarity;
import wordsimilarity.WordSimilarity;

public class SemanticSimilarityTest {
	
public static void main(String[] args) {

	
    //WordSimilarity.loadGlossary();
//    double simval=WordSimilarity.simWord("美国","中国");
//    System.out.println("美"+simval);
    String s1="核心能力既是一个公司的福音，却也可能是诅咒,为什么";
    String s2="核心能力既是一个公司的福音，却也可能是诅咒";
//    String s1="优越的资源对于一个公司、一个国家来说的确是一种福音，但同时也可能是一种诅咒";
//    String s2="文章认为核心能力既是一个公司的福音，却也可能是诅咒";
 	  SemanticSimilarity similarity = SemanticSimilarity.getInstance();
     //double sim = similarity.getSimilarity(s1, s2);
         System.out.println("sim ==> " + similarity.getSimilarity(s1, s2));
         
             
}


}
