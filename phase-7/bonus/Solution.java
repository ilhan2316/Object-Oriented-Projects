public class Solution implements Comparable<Solution> {
    public Solution(String name, String word, int x, int y, Direction direction) {
        this.name = name;
        this.word = word;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("In %s: %s found at (%d,%d,%s)", name, word, x, y, direction);
    }

    @Override
    public int compareTo(Solution other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.word.compareTo(other.word);
    }

    private String name;
    private String word;
    private int x;
    private int y;
    private Direction direction;
}

