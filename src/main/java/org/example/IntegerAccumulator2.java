package org.example;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntegerAccumulator2 {
    private int init;

    public IntegerAccumulator2(int init) {
        this.init = init;
    }

    public int add(int i){
        this.init += i;
        return this.init;
    }

    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulator2 accumulator=new IntegerAccumulator2(0);
        IntStream.range(0,3).forEach(i->new Thread(()->{
            int inc=1;
            while (true){
                int oldValue;
                int result;
                synchronized (IntegerAccumulator2.class){
                    oldValue = accumulator.getValue();
                    result=accumulator.add(inc);
                }
                System.out.println(oldValue+"+"+inc+"="+result);
                if(inc+oldValue != result){
                    System.out.println("ERROR:"+oldValue+"+"+inc+"="+result);
                }
                inc++;
                slowly();
            }
        }).start());
    }

    private static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
