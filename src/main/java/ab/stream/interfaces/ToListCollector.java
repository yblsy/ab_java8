package ab.stream.interfaces;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author 刘晨
 * @create 2017-11-15 15:11
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 该方法创建了一个结果容器
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        System.out.println("supplier()");
        return () -> Lists.newArrayList();
    }

    /**
     * 将结果循环累加结果容器中
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        System.out.println("accumulator()");
        return (list,t) -> list.add(t);
    }

    /**
     * 并行处理时告知stream要如何处理所有的最小单位
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        System.out.println("combiner()");
        return (list1,list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 完成最终转换
     * Function.identity() :返回一个执行了apply()方法之后只会返回输入参数的函数对象
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        System.out.println("finisher()");
        return Function.identity();
    }

    /**
     * UNORDERED：归约结果不受流中项目的遍历和累计顺序的影响。
     * CONCURRENT：accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。如果收集器没有标为UNORDERED，拿它仅在用于无序数据源时才可以并行归约。
     * IDENTITY_FINISH：表明完成其方法返回的函数是一个恒等函数，可以跳过，表明是否需要进一步转换。
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics()");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }
}
