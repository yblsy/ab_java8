package branch;

import ab.branch.RecursiveTest;
import ab.stream.ParallelStreamTest;
import org.junit.Test;

/**
 * @author 刘晨
 * @create 2017-11-22 14:23
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class TaskTest {

    @Test
    public void test01(){
        System.out.println("ForkJoin sum done in " + ParallelStreamTest.measureSumPref(RecursiveTest::forkJoinSUm,10_000_000) + " msecs ");
    }

    public int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSPace = true;
        for(char c : s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSPace = true;
            }else{
                if(lastSPace){
                    counter++;
                }else{
                    lastSPace = false;
                }
            }
        }
        return counter;
    }

    /**
     * 通过流去实现
     */
}
