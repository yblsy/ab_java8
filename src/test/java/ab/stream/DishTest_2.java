package ab.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DishTest_2 {

    /**
     * reduce，元素求和
     */
    @Test
    public void Dish_1(){
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Integer total = integerList.stream().reduce(0,(a,b)->a+b);
        System.out.println(total);
        total = integerList.stream().reduce(0,Integer::max);
        System.out.println(total);
    }

    /**
     * reduce，测试5.3，返回流中一共有多少个菜品
     */
    @Test
    public void Dish_2(){
        Integer i = Dish.getMenu().stream().map(a -> 1).reduce(0,Integer::sum);
        System.out.println(i);
    }

    /**
     * 原始类型流特化
     * 所有菜品的卡路里
     */
    @Test
    public void Dish_3(){
        System.out.println(Dish.getMenu().stream().mapToInt(Dish::getCalories).sum());
    }

    /**
     * 特化流转换为非特化流
     */
    @Test
    public void Dish_4(){
        IntStream intStream = Dish.getMenu().stream().mapToInt(Dish::getCalories);
        Stream<Integer> integerStream = intStream.boxed();
    }

    /**
     * OptionalInt
     */
    @Test
    public void Dish_5(){
        OptionalInt optionalInt = Dish.getMenu().stream().mapToInt(Dish::getCalories).max();
        optionalInt.orElse(1);//如果流为空流，则给一个默认值
        System.out.println(optionalInt.getAsInt());
    }

    /**
     * range和rangeClosed应用
     */
    @Test
    public void Dish_6(){
        IntStream.range(1,100).forEach(i -> System.out.println(i));
        System.out.println("-----------------------");
        IntStream.rangeClosed(1,100).forEach(i -> System.out.println(i));
    }
}
