//TestLine.java
public class TestLine {
    public static void main(String[] args) {
        Line line1 = new Line(0, 0, 3, 4, Color.MAGENTA);
        Line line2 = new Line(1, 2, 6, 8, Color.ORANGE);
        Line line3 = new Line(-2, -1, 2, 5, Color.PINK);
        Line line4 = new Line(3, 3, 7, 3, Color.CYAN);

        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
    }
}



