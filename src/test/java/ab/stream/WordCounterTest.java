package ab.stream;/**
 * Created by liuchen on 2017/11/23.
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author 刘晨
 * @create 2017-11-23 14:05
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class WordCounterTest {

    static String words = "sdfsd sdfsdfsdf qw qweqwxz   dfgfdg trtr sdkfls;d terdf";

    public static long countWords(Stream<Character> characterStream) {
        WordCounter wordCounter = characterStream.reduce(new WordCounter(0, true),
                (WordCounter wc, Character c) -> {
                    return wc.accumulate(c);
                },
                (WordCounter wc1, WordCounter wc2) -> {
                    return wc1.combine(wc2);
                });

        return wordCounter.getCounter();
    }

    @Test
    public void test01(){
        Stream<Character> stream = IntStream.range(0,words.length()).mapToObj(i -> words.charAt(i));
//        System.out.println("Found " + countWords(stream) + " words");
        System.out.println("Found " + countWords(stream.parallel()) + " words");
    }

    @Test
    public void test02(){
        Spliterator<Character> spliterator = new WordCountSpliterator(words);
        Stream<Character> stream = StreamSupport.stream(spliterator,true);
        System.out.println("Found " + countWords(stream) + " words");
    }
}
