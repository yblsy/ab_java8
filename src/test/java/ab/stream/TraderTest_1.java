package ab.stream;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TraderTest_1 {

    //private final static Logger logger = LoggerFactory.getLogger(TraderTest_1.class);
    /**
     *  找出2011年发生的所有交易并且按照交易额来排序
     */
    @Test
    public void test1(){
        Transaction.getTransaction().stream().filter(transaction -> transaction.getYear() == 2011).
                sorted(Comparator.comparing(Transaction::getValue).reversed()).
                forEach(transaction -> System.out.println(transaction.getTrader().getName() + ":" + transaction.getYear() + ":" + transaction.getValue()));
    }

    /**
     *  交易员都在哪些不同的城市呆过
     */
    @Test
    public void test2(){
        Transaction.getTransaction().stream().map(transaction -> transaction.getTrader().getCity()).
                distinct().
                forEach(System.out::println);
    }

    /**
     *  查找所有剑桥的操作员，按照姓名排序
     */
    @Test
    public void test3(){
        Transaction.getTransaction().stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(trader -> System.out.println(trader.getName() + ":" + trader.getCity()));
    }

    /**
     *  返回所有交易员的姓名字符串，按字母排序
     */
    @Test
    public void test4(){
        String strName = Transaction.getTransaction().stream().map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(a,b)->a+":"+b);
        System.out.println(strName);
    }

    /**
     *  有没有交易员是在米兰工作的
     */
    @Test
    public void test5(){
        boolean isMilan = Transaction.getTransaction().stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(isMilan);
    }

    /**
     *  打印生活在剑桥的所有交易员的交易数据
     */
    @Test
    public void test6(){
        Transaction.getTransaction().stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .forEach(transaction -> System.out.println(transaction.getTrader().getName() + ":" + transaction.getYear() + ":" + transaction.getValue()));
    }

    /**
     *  所有交易中，最高的交易额是多少
     */
    @Test
    public void test7(){
        System.out.println(Transaction.getTransaction().stream()
                .max(Comparator.comparing(Transaction::getValue)).get().toStringTransaction());
    }

    /**
     *  所有交易中，最高的交易额是多少
     */
    @Test
    public void test8(){
        System.out.println(Transaction.getTransaction().stream()
                .min(Comparator.comparing(Transaction::getValue)).get().toStringTransaction());
    }

    /**
     * 把所有交易按照年份分组
     */
    @Test
    public void test9(){
        Map<String,List<Transaction>> map = Transaction.getTransaction().stream().collect(Collectors.groupingBy(transaction->transaction.getTrader().getCity()));
//        for(Integer year : map.keySet()){
//            System.out.println(year+":"+map.get(year).stream()
//                    .map(transaction -> transaction.getTrader().getName()+"|"+transaction.getTrader().getCity()+"|"+transaction.getValue())
//                    .reduce("",(a,b)->a+"_"+b));
//        }

        for(String city : map.keySet()){
            System.out.println(city+":"+map.get(city).stream()
                    .map(transaction -> transaction.getTrader().getName()+"|"+transaction.getTrader().getCity()+"|"+transaction.getValue())
                    .reduce("",(a,b)->a+"_"+b));
        }
    }
}
