package similarity.test;

import ruc.irm.similarity.word.CharBasedSimilarity;
import junit.framework.TestCase;

public class CharBasedSimilarityTest extends TestCase {
    public void test() {
        CharBasedSimilarity sim = new CharBasedSimilarity();
        String s1 = "颜色";
        String s2 = "绿色";
        System.out.println(sim.getSimilarity(s1, s2));
       // assertTrue(sim.getSimilarity(s1, s2) > 0);
    }
}
