package ab.stream;

import ab.stream.interfaces.PrimeNumCollector;
import ab.stream.interfaces.ToListCollector;
import org.junit.Test;

//import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * @author 刘晨
 * @create 2017-10-19 10:15
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class DishTest_3 {

    @Test
    public void test01(){
        System.out.println(Dish.getMenu().stream().collect(counting()));
        System.out.println(Dish.getMenu().stream().count());
    }

    @Test
    public void test02(){
        IntSummaryStatistics iss = Dish.getMenu().stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("avg:"+iss.getAverage()+" count:"+iss.getCount()+" max:"+iss.getMax()+" min:"+iss.getMin()+" sum:"+iss.getSum());
    }

    @Test
    public void test03(){
        System.out.println(Dish.getMenu().stream().map(Dish::getName).collect(joining()));
        System.out.println(Dish.getMenu().stream().map(Dish::getName).collect(joining(",")));
    }

    @Test
    public void test04(){
        System.out.println(Dish.getMenu().stream().collect(reducing(0,Dish::getCalories,(i,j)->i+j)));
        System.out.println(Dish.getMenu().stream().collect(reducing((d1,d2)->d1.getCalories()>d2.getCalories()?d1:d2)));
    }

    /**
     * 分区函数
     */
    @Test
    public void test05(){
        //是否是蔬菜
        Map<Boolean,List<Dish>> maps = Dish.getMenu().stream().collect(partitioningBy(Dish::isVegetarion));
        //是否是蔬菜，且每组按照类型分类
        Map<Boolean,Map<Type,List<Dish>>> mapDishs = Dish.getMenu().stream().collect(partitioningBy(Dish::isVegetarion,groupingBy(Dish::getType)));
        //是否蔬菜，且每组最大卡路里的菜品
        Map<Boolean,Dish> mostCaloricDish = Dish.getMenu().stream().collect(partitioningBy(Dish::isVegetarion,collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }

    /**
     * P127
     * 使用partitioningBy
     * 将数字分为质数或者非质数
     */
    @Test
    public void test06(){
        List numbers = IntStream.rangeClosed(2,100).mapToObj(num -> (Object)num).collect(toList());
        //这个其实就是拆箱的效果
        Map<Boolean,List<Integer>> integers = IntStream.rangeClosed(2,100).mapToObj(num -> (Object)num).map(obj -> Integer.valueOf(""+obj)).collect(partitioningBy(number -> isPrime(Integer.parseInt(""+number))));
        //可以直接使用boxed
        Map<Boolean,List<Integer>> integers01 = IntStream.rangeClosed(2,100).boxed().collect(partitioningBy(number -> isPrime(Integer.parseInt(""+number))));
        System.out.println(integers.get(true).stream().map(integer -> ""+integer).collect(joining(",")));
        System.out.println("-----------");
        System.out.println(integers.get(false).stream().map(integer -> ""+integer).collect(joining(",")));
    }

    /**
     * test06的完全替代
     */
    @Test
    public void test08(){
        Map<Boolean,List<Integer>> maps = IntStream.rangeClosed(2,1000).boxed().collect(new PrimeNumCollector());
        maps.keySet().forEach(key -> {
            System.out.println(key+":"+maps.get(key).stream().map(integer -> ""+integer).collect(Collectors.joining(",")));
        });
    }

    /**
     * 是否是质数
     * @param number
     * @return
     */
    public static boolean isPrime(int number){
        return IntStream.range(2,number).noneMatch(num -> number % num == 0);
    }

    /**
     * 调用自己定义的collector
     */
    @Test
    public void test07(){
        List<Dish> dishs = Dish.getMenu().stream().collect(new ToListCollector<Dish>());
        Collectors.toList();
        dishs.forEach(System.out::println);
    }

    @Test
    public void test09(){
        Map<Type,List<Dish>> maps = Dish.getMenu().stream().collect(groupingBy(Dish::getType));

        Map<Type,BigDecimal> maps01 = Dish.getMenu().stream().collect(groupingBy(Dish::getType,collectingAndThen(summingInt(Dish::getCalories),BigDecimal::new)));

        Map<String[],List<Dish>> maps02 = Dish.getMenu().stream().collect(groupingBy(dish -> new String[]{dish.getType().toString(),dish.getName()}));

        Map<String[],BigDecimal> maps03 = Dish.getMenu().stream().collect(groupingBy(dish -> new String[]{dish.getType().toString(),dish.getName()},collectingAndThen(summingInt(Dish::getCalories),BigDecimal::new)));
    }

    @Test
    public void test10(){
        System.out.println(DishTest_3.rabits(0,1,12,0));
    }

    /**
     *
     * @param last 上上一个
     * @param prev 上一个
     * @param month 几个月
     * @return
     */
    public static int rabits(int last,int prev,int month,int index){
        if(month == index)
            return last + prev;
        else
            return rabits(prev,last + prev,month,++index);
    }
}
