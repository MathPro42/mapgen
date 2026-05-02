package mapgen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyRandomTest {

    @Test
    void testSameSeedProducesSameSequence() {
        MyRandom rand1 = new MyRandom(42);
        MyRandom rand2 = new MyRandom(42);

        // Verify that identical seeds produce the exact same pseudo-random sequence
        assertEquals(
            rand1.next(0, 100),
            rand2.next(0, 100),
            "First random numbers should match"
        );
        assertEquals(
            rand1.next(10, 50),
            rand2.next(10, 50),
            "Second random numbers should match"
        );
        assertEquals(
            rand1.next(-10, 10),
            rand2.next(-10, 10),
            "Third random numbers should match"
        );

        assertEquals(
            3,
            rand1.getCounter(),
            "Counter should increment correctly"
        );
    }
}
