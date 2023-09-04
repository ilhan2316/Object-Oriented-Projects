public enum Color {
    MAGENTA(0xFF00FF, "\u001B[35m"), // RGB for Magenta
    ORANGE(0xFFA500, "\u001B[33m"), // RGB for Orange
    PINK(0xFFC0CB, "\u001B[95m"), // RGB for Pink
    CYAN(0x00FFFF, "\u001B[36m"); // RGB for Cyan
    
    private final int rgb; // Red-Green-Blue integer
    private final String ansiColorCode; // ANSI escape code for color
    
    private Color(int rgb, String ansiColorCode) {
        this.rgb = rgb;
        this.ansiColorCode = ansiColorCode;
    }
    
    public int getRgb() {
        return rgb;
    }
    
    public String getAnsiColorCode() {
        return ansiColorCode;
    }
    
    @Override
    public String toString() {
        return ansiColorCode + this.name() + "(Ox" + String.format("%06X", rgb) + ")\u001B[0m";
    }
}
