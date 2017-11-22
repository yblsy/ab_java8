package ab.Generic;

import java.util.Arrays;

/**
 * @author 刘晨
 * @create 2017-11-17 15:47
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class Generic<T> {

    private T t;

    public Generic(T t){
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public <T> String showKeyName(Generic<T> contaioner){
        return contaioner.getT().toString();
    }

    //这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    //同时这也印证了泛型通配符章节所描述的，?是一种类型实参，可以看做为Number等所有类的父类
    public void showKeyValue2(Generic<?> obj){
        System.out.println("泛型测试"+" key value is " + obj.getT());
    }

    public static <T> void method(T... args){
        for(T t : args){
            System.out.println(t);
        }
    }

    public void method01(Generic<? extends Number>... args){
        for(Generic t : args){
            System.out.println(t.getT());
        }
    }

    public static <T extends Number> void method(T... args){
        for(T t : args){
            System.out.println(t);
        }
    }
}
