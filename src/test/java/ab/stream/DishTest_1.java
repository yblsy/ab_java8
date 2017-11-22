package ab.stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DishTest_1 {

    /**
     * 第一个例子
     * 初步使用流的操作
     */
    @Test
    public void testDish_1() {
        List<Dish> dishList = Dish.getMenu();
        List<String> dishName = dishList.stream().filter(dish -> dish.getCalories() > 300).map(Dish::getName).limit(3).collect(Collectors.toList());
        System.out.println(dishName);
    }

    /**
     * 此处同一个流使用两次的情况会报错
     * java.lang.IllegalStateException: stream has already been operated upon or closed
     */
    @Test
    public void testDish_2() {
        List<Dish> dishList = Dish.getMenu();
        Stream<String> s = dishList.stream().map(Dish::getName);
        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }

    /**
     * 直观性的查看每个中间操作的详细过程
     */
    @Test
    public void testDish_3() {
        List<String> temp = Dish.getMenu().stream().filter(dish -> {
            System.out.println("name:" + dish.getName());
            return dish.getCalories() > 300;
        }).sorted((dish1, dish2) -> {
            System.out.println("dish1:" + dish1.getName() + "   dish2:" + dish2.getName());
            return BigDecimal.valueOf(dish1.getCalories()).compareTo(BigDecimal.valueOf(dish2.getCalories()));
        }).map(dish -> {
            System.out.println("map:" + dish.getName());
            return dish.getName();
        }).limit(3).collect(Collectors.toList());
        System.out.println(temp);
    }

    /**
     * 用谓词进行筛选，其中filter里面接受的是lambda里面的谓词方法，返回boolean类型
     */
    @Test
    public void testDish_4() {
        Dish.getMenu().stream().filter(dish -> dish.isVegetarion()).forEach(dish -> System.out.println(dish.getName()));
        Dish.getMenu().stream().filter(dish -> dish.isVegetarion()).forEach(System.out::println);
    }

    /**
     * 去重？
     */
    @Test
    public void testDish_5() {
        List<Integer> integers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        integers.stream().distinct().forEach(System.out::println);
    }

    /**
     * 跳过元素
     */
    @Test
    public void testDish_6() {
        Dish.getMenu().stream().limit(4).skip(2).forEach(dish -> System.out.println(dish.getName()));
    }

    /**
     * 映射map
     */
    @Test
    public void testDish_7() {
        //此处的map。相当于传入一个String之后生成了一个Stream<String>
        List<String> dishNames = Dish.getMenu().stream().map(Dish::getName).collect(Collectors.toList());
        dishNames.forEach(System.out::println);
    }

    /**
     * 对于一张单词表，如果返回一张列表，来列出其中各不相同的字符？
     * 错误例子****
     */
    @Test
    public void testDish_8() {
        List<String> words = Arrays.asList("Hello", "World");
        words.stream().map(str -> str.split(""))//这一步本意是想要将每组词变成String型，可是split的返回值是[]数组，所以此处的Stream是Stream<String[]>
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 对于一张单词表，如果返回一张列表，来列出其中各不相同的字符？
     * 正确例子****
     */
    @Test
    public void testDish_9() {
        List<String> words = Arrays.asList("Hello", "World");
        words.stream().map(str -> str.split(""))//这一步本意是想要将每组词变成String型，可是split的返回值是[]数组，所以此处的Stream是Stream<String[]>
                .flatMap(strings -> {//即Arrays::stream
                    return Arrays.stream(strings);
                })
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 给定一个数字列表，返回每个数的平方构成的列表
     */
    @Test
    public void testDish_10() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integers.stream().map(integer -> integer * integer).forEach(System.out::println);
    }

    /**
     * 给定两组列表，返回所有数对
     */
    @Test
    public void testDish_11() {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(4, 5);

        number1.stream().flatMap(n1 -> {
            return number2.stream().map(n2 -> {
                return new Integer[]{n1, n2};
            });
        }).forEach(number -> System.out.println(number[0] + ":" + number[1]));
    }

    /**
     * 给定两组列表，返回所有数对,只返回能被三整除的数对
     */
    @Test
    public void testDish_12() {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(4, 5);

        number1.stream().flatMap(n1 -> number2.stream()
                    .filter(n3 -> (n1 + n3) % 3 == 0)
                    .map(n2 -> new Integer[]{n1, n2}))
                .forEach(number -> System.out.println(number[0] + ":" + number[1]));
    }

    /**
     * 使用anyMatch检查谓词是否至少匹配一个元素
     */
    @Test
    public void testDish_13(){
        if(Dish.getMenu().stream().anyMatch(Dish::isVegetarion)){
            System.out.println("The menu has true for vegetarion!");
        }
    }

    /**
     * 使用allMatch来匹配是否所有元素
     */
    @Test
    public void testDish_14(){
        boolean noFilter = Dish.getMenu().stream().allMatch(Dish::isVegetarion);
        System.out.println("noFilter : "+noFilter);
        boolean filter = Dish.getMenu().stream().filter(Dish::isVegetarion).allMatch(Dish::isVegetarion);
        System.out.println("Filter : "+filter);
    }

    /**
     * 使用noneMatch来得到与allMatch相反的结果
     * 如果流中有元素与给定的谓词相匹配，则返回false，否则返回true
     */
    @Test
    public void testDish_15(){
        boolean noFilter = Dish.getMenu().stream().allMatch(Dish::isVegetarion);//是否全部都是true
        System.out.println("noFilter : "+noFilter);
        boolean noNoneFilter = Dish.getMenu().stream().noneMatch(Dish::isVegetarion);//确保流中没有任何元素是true
        System.out.println("noNoneFilter : "+noNoneFilter);
        boolean filter = Dish.getMenu().stream().filter(Dish::isVegetarion).allMatch(Dish::isVegetarion);
        System.out.println("Filter : "+filter);
        boolean nonefilter = Dish.getMenu().stream().filter(Dish::isVegetarion).noneMatch(Dish::isVegetarion);
        System.out.println("nonefilter : "+nonefilter);
        nonefilter = Dish.getMenu().stream().filter(dish -> !dish.isVegetarion()).noneMatch(Dish::isVegetarion);
        System.out.println("nonefilter : "+nonefilter);
    }

    /**
     * 使用findAny来返回一个随机值
     */
    @Test
    public void testDish_16(){
        Optional<Dish> optional = Dish.getMenu().stream().filter(Dish::isVegetarion).findAny();
        optional.ifPresent(dish -> System.out.println(dish.getName()));
    }
}
