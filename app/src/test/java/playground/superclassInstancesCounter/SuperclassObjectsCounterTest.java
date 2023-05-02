package playground.superclassInstancesCounter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class A {
    private static int ID = 1;
    public final int id;

    public A() {
        this.id = ID++;
    }
}

class B extends A {
    public B() {
        assert true != false;
    }
}

class C extends B {
}

class D extends A {
}

public final class SuperclassObjectsCounterTest {
    @Test
    void subclassesGetIdBasedOnSuperclassInstances() {
        A a1 = new A();
        B b1 = new B();
        A a2 = new A();
        B b2 = new B();
        A a3 = new A();
        B b3 = new B();
        C c1 = new C();
        D d1 = new D();
        A a4 = new A();

        assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                new int[] { a1.id, b1.id, a2.id, b2.id, a3.id, b3.id, c1.id, d1.id, a4.id });
    }
}
