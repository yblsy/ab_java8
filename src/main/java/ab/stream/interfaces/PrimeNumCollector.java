package ab.stream.interfaces;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * @author 刘晨
 * @create 2017-11-16 15:39
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class PrimeNumCollector implements Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>>{

    /**
     * 创建累加器函数
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean,List<Integer>>(){{
            put(true,new ArrayList<>());
            put(false,new ArrayList<>());
        }};
    }

    /**
     * 如何去做处理每一个子流
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean,List<Integer>> maps,Integer condidate) -> {
            //用来判断该数是否是质数，如果是就加入到true的map，否则就加到为false的map
            maps.get(isPrime(maps.get(true),condidate,true)).add(condidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean,List<Integer>> map1,Map<Boolean,List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    /**
     * 是否是质数
     * @param primes    质数列表
     * @param candidate   候选数
     * @return
     */
    public static boolean isPrime(List<Integer> primes, int candidate){
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime(List<Integer> primes, int candidate, boolean isNew){
        //获取数的平方根
        int candidateRoot = (int)Math.sqrt((double) candidate);
        //只用不大于被测数平方个的质数去效验
        return takeWhile(primes, i-> i<= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
    }

    /**
     * 返回满足谓词的list的前缀
     * @param list
     * @param p
     * @param <A>
     * @return
     */
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for(A item : list){
            if(!p.test(item)){
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }
}
