package ab.stream;

import ab.stream.interfaces.PrimeNumCollector;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 刘晨
 * @create 2017-11-16 15:38
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class PrimeNumber_01 {

    @Test
    public void test01(){
        Map<Boolean,List<Integer>> maps = IntStream.rangeClosed(2,1000).boxed().collect(new PrimeNumCollector());
        maps.keySet().forEach(key -> {
            System.out.println(key+":"+maps.get(key).stream().map(integer -> ""+integer).collect(Collectors.joining(",")));
        });
    }
}
