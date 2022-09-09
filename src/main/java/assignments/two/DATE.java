package assignments.two;

public enum DATE {

    DAY(1, 31), MONTH(1, 12);

    private final int first;
    private final int last;

    DATE(int first, int last) {
        this.last = last;
        this.first = first;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }
}
