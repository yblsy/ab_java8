package ab.stream;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 勾股数应用
 *
 * @author 刘晨
 * @create 2017-10-17 15:01
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class PythagoreanTriple {

    @Test
    public void PythagoreanTripleTest01(){
        Stream stream = IntStream.rangeClosed(1,100).boxed()//Stream<Integer>
                .flatMap(a->IntStream.rangeClosed(a,100)
                            .filter(b->Math.sqrt(a * a + b * b) % 1D == 0)//按规则筛选a^2+b^2=c^2
                            .mapToObj(b->new int[]{a,b,(int)Math.sqrt(a * a + b * b)}));//组合成新的
        stream.forEach(a ->{
            int[] temp = (int[])a;
            System.out.println(temp[0]+":"+temp[1]+":"+temp[2]);
        });
    }
}
