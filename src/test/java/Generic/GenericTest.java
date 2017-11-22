package Generic;

import ab.Generic.Generic;
import org.junit.Test;

/**
 * @author 刘晨
 * @create 2017-11-17 15:50
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class GenericTest {

    @Test
    public void test01(){
        Generic generic01 = new Generic<Integer>(123456);
        Generic generic02 = new Generic<String>("456789");
    }

    @Test
    public void test02(){
        new Generic<String>("123").method("123","abc","fds");
    }

    @Test
    public void test03(){
        new Generic<Integer>(123).method01(new Generic<Integer>(123),new Generic<Integer>(653),new Generic<Integer>(2312));
    }
}
