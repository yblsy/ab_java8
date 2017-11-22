package ab.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 刘晨
 * @create 2017-10-18 11:04
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class CreateStream {

    /**
     * Stream.of
     */
    @Test
    public void cs01() {
        Stream<String> stringStream = Stream.of("aqwe", "asd", "zxc", "rty", "cvb");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

    }

    /**
     * Arrays.stream
     */
    @Test
    public void cs02() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        IntStream intStream = Arrays.stream(numbers);
        System.out.println(intStream.sum());
    }

    /**
     * 文件流
     */
    @Test
    public void cs03() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get(" data. txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 无限流迭代
     */
    @Test
    public void cs04() {
        Stream.iterate(0,n->n+2)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * 0,1,1,2,3,5,8..
     */
    @Test
    public void cs05() {
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.print(t[0] + " "));
    }

    /**
     * Stream.generate()
     */
    @Test
    public void cs06() {
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }

    /**
     * Stream.generate()使用generate来创建0，1，1，2，3，5，8
     * 编写规则：记录第一位数值和第二位数值，计算出第三位数值，之后，将第一位数值舍去，将第二位变为第一位，第三位变为第二位，将第三位塞入Stream
     */
    @Test
    public void cs07() {
        IntSupplier intSupplier = new IntSupplier() {
            private int current = -1;
            private int next = 1;
            @Override
            public int getAsInt() {
                int newNext = current + next;
                int temp = current;
                current = next;
                next = newNext;
                return newNext;
            }
        };

        int[] i = IntStream.generate(intSupplier).limit(10).toArray();
        List list = Arrays.asList(i);
    }
}