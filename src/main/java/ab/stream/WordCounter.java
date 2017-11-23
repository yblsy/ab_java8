package ab.stream;

/**
 * @author 刘晨
 * @create 2017-11-23 13:57
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class WordCounter {
    private final long counter;//计数器
    private final boolean lastSpec;//上一个是否是空格

    public WordCounter(long counter,boolean lastSpec){
        System.out.println("counter:"+counter+",lastSpec:"+lastSpec);
        this.counter = counter;
        this.lastSpec = lastSpec;
    }

    /**
     * 构建一个累加器
     */
    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){
            //如果当前值是空格且上一个字符也是空格，则返回本身，其本身lastSpec = false;
            return lastSpec ? this : new WordCounter(counter,true);
        }else{
            //如果上一个字符是空格且当前字符不是空格，说明当前是一个单词
            //如果上一个字符不是空格，且当前字符也不是一个空格，则返回本身，说明此时在一个单词之内
            return lastSpec ? new WordCounter(counter + 1,false) : this;
        }
    }

    /**
     * 合并器
     * @param wordCounter
     * @return
     */
    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpec);
    }

    public long getCounter(){
        return counter;
    }
}
