package ab.stream;/**
 * Created by liuchen on 2017/11/23.
 */

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author 刘晨
 * @create 2017-11-23 14:47
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class WordCountSpliterator implements Spliterator<Character>{
    private final String string;
    private int currentChar = 0;

    public WordCountSpliterator(String string){
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if(currentSize < 10){
            return null;
        }

        for(int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++){
            //这段是为了寻找下一个空格
            if(Character.isWhitespace(string.charAt(splitPos))){
                Spliterator<Character> spliterator = new WordCountSpliterator(string.substring(currentChar,splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    /**
     * 总长度和当前遍历位置的差？
     * @return
     */
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
