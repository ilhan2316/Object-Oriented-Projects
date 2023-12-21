public enum Color {
    MAGENTA(0xFF00FF), // RGB for Magenta
    ORANGE(0xFFA500), // RGB for Orange
    PINK(0xFFC0CB), // RGB for Pink
    CYAN(0x00FFFF); // RGB for Cyan
    
    private final int rgb; 
    
    private Color(int rgb) {
        this.rgb = rgb;
    }
    
    public int getRgb() {
        return rgb;
    }
    
    @Override
    public String toString() {
        return this.name() + "(Ox" + String.format("%06X", rgb) + ")";
    }
}

