package JavaThreading;

public class ThreadBasicTest {
    public static void main(String[] args) {
        int val = 1;
        SafeSequence a = new SafeSequence(val);
        SafeSequence b = new SafeSequence(val);
        b.pecati();
    }
}
