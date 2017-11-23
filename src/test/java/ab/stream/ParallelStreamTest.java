package ab.stream;

import org.junit.Test;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author 刘晨
 * @create 2017-11-19 14:58
 * To change this template use File | Settings | Editor | File and Code Templates.
 * 并行流
 **/
public class ParallelStreamTest {

    /**
     * 返回从1到指定数字的和
     * @return
     */
    public static long sequentialSumJava8(long n){
        return Stream.iterate(1L,i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L,Long :: sum);
    }

    /**
     * 返回从1到指定数字的和
     * @return
     */
    public static long sequentialSumJava7(long n){
        long result = 0;
        for(long i = 1L;i <= n; i++){
            result += i;
        }
        return result;
    }

    /**
     * 这个方法接受一个函数和一个long作为参数。它会对传给方法的long应用函数10次，来记录每次执行的时间，并返回最短的依次执行时间。
     * @param adder
     * @param n
     * @return
     */
    public static long measureSumPref(Function<Long,Long> adder, long n){
        long fastest = Long.MAX_VALUE;
        for(int i = 0;i < 10;i++){
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: "+sum);
            if(duration < fastest) fastest = duration;
        }
        return fastest;
    }

    @Test
    public void test01(){
        //System.out.println("java8 sum done in:" + measureSumPref(ParallelStreamTest::sequentialSumJava8, 10_000_000) + " msecs");
        System.out.println("java8 sum done in:" + measureSumPref(ParallelStreamTest::sequentialSumJava7, 10_000_000) + " msecs");
    }

    @Test
    public void test02(){
        System.out.println("sideEffectParallelSum sum done in:" + sideEffectParallelSum(10_000_000) + " msecs");
    }

    public long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    class Accumulator{
        public long total = 0;
        public void add(long value){
            total += value;
        }
    }
}
