package www.ds;

public class Test {
    public static void main(String[] args) {
        Sequence sequence = new SequenceArrayImpl(2);
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);
        sequence.add(null);
        sequence.remove(2);
        sequence.add(4);
        System.out.println(sequence.get(2));
    }
}
