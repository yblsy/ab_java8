package ab.branch;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author 刘晨
 * @create 2017-11-20 22:37
 * To change this template use File | Settings | Editor | File and Code Templates.
 * 使用RecursiveTask来进行分支合并,举个例子
 * 实现用这个框架为一组数字来进行求和
 * 非阻塞？
 **/
public class RecursiveTest extends RecursiveTask<Long>{

    /**
     * numbers,要求和的数组
     * start,end 子任务处理的数组的起始和终止位置
     */
    private final long[] numbers;
    private final int start;
    private final int end;

    /**
     * 当数组大小达到该值的时候，不再分解
     */
    public static final long THRESHOLD = 10_000;

    public RecursiveTest(long[] numbers){
        this(numbers,0,numbers.length);
    }

    private RecursiveTest(long[] numbers,int start,int end){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * 当子任务不再可分时计算结果的简单方法
     * @return
     */
    private long computeSequentially(){
        long sum = 0;
        for(int i = start;i < end;i++){
            sum += numbers[i];
        }
        return sum;
    }

    /**
     * 该方法定义了将任务拆分成子任务的逻辑，以及无法再拆分货不方便再拆分时，生成单个子任务结果的逻辑
     * @return
     */
    @Override
    protected Long compute() {
        //得到该数组的长度
        int length = end - start;
        //如果大小小于或等于阈值，顺序计算结果
        if(length <= THRESHOLD){
            return this.computeSequentially();
        }
        //创建子任务为数组的前一半求和
        RecursiveTest left = new RecursiveTest(numbers,start,start + length / 2);
        //fork方法是利用另一个线程来进行来异步执行创建的新的子任务
        left.fork();
        //创建一个任务为数组的后一般求和
        RecursiveTest right = new RecursiveTest(numbers,start + length / 2,end);

        /**
         * right.compute() 同步执行第二个子任务，有可能允许进一步递归划分
         * left.join()读取第一个子任务的结果，如果尚未完成就等待
         */
        return right.compute() + left.join();
    }

    public static long forkJoinSUm(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new RecursiveTest(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
