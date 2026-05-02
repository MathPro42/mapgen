package mapgen;

import java.util.Random;

public class MyRandom {

    private final Random random;
    private final int seed;
    private int counter;

    public MyRandom(int seed) {
        this.seed = seed;
        this.random = new Random(seed);
        this.counter = 0;
    }

    public MyRandom(Random random, int counter) {
        this.seed = 0;
        this.random = random;
        this.counter = counter;
    }

    public int getSeed() {
        return seed;
    }

    public int getCounter() {
        return counter;
    }

    public Random getRandom() {
        return random;
    }

    public int next(int min, int max) {
        counter++;
        return random.nextInt(max - min) + min;
    }
}
