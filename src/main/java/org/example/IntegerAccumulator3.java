package org.example;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public final class IntegerAccumulator3 {
    private final int init;

    public IntegerAccumulator3(int init) {
        this.init = init;
    }

    public IntegerAccumulator3(IntegerAccumulator3 accumulator,int init){
        this.init=accumulator.getValue()+init;
    }

    public IntegerAccumulator3 add(int i){
        return new IntegerAccumulator3(this,i);
    }

    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulator3 accumulator=new IntegerAccumulator3(0);
        IntStream.range(0,3).forEach(i->new Thread(()->{
            int inc=0;
            while (true){
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc).getValue();
                System.out.println(oldValue+" + "+inc+"="+result);
                if(inc+oldValue != result){
                    System.err.println("error:"+oldValue+"+"+inc+"="+result);
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
