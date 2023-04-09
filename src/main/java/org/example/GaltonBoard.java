package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class GaltonBoard {

    private final int numBins;

    private final int numThread;



    private final double startValue;
    private  final AtomicIntegerArray result;
    private final ArrayList<Thread> threadList;
    public GaltonBoard(int numBins, int numThread) {
        this.numBins = numBins;
        this.numThread = numThread;
        this.startValue = (double) (numBins + 1) /2;
        this.result = new AtomicIntegerArray(numBins);
        this.threadList = new ArrayList<>();
    }

    public void run() {
        for (int i = 0; i < numThread; i++) {
            Thread thread = new Thread(new Ball(startValue));
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

public int sum() {
    int sum=0;
    for (int i = 0; i < result.length(); i++) {
        sum+=result.get(i);
    }
    return sum;
}

@Override
public String toString() {
    StringBuilder output = new StringBuilder();
    int columnSize=7;
    for (int i = 0; i < numBins; i++) {
        String iAsString = Integer.toString(i);

        output.append(iAsString);
        output.append(" ".repeat(columnSize - iAsString.length()));
        output.append(Integer.toString(result.get(i)));
        output.append("\n");
    }
    int sum = sum();
    String infos=String.format("Number of requested thread: %d\nSum of Bin values: %d\n",numThread,sum);

    if(numThread==sum) infos+="Nice work! Both of them are equal";

    output.append(infos);

    return output.toString();
}


    class Ball implements Runnable {
        private double value;

        public Ball(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        @Override
        public void run() {
            Random rand = new Random();
            float float_random;

            for (int i = 0; i < numBins-1; i++) {
                float_random = rand.nextFloat();
                if(float_random>0.5) this.setValue(this.getValue()+0.5);
                else this.setValue(this.getValue()-0.5);
            }
            int intValue = (int) this.getValue();
            result.incrementAndGet(intValue-1);
        }
    }
}