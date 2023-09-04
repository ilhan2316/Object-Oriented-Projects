public enum Color {
    MAGENTA(0xFF00FF, "\u001B[35m"), 
    ORANGE(0xFFA500, "\u001B[33m"), 
    PINK(0xFFC0CB, "\u001B[95m"), 
    CYAN(0x00FFFF, "\u001B[36m"); 
    
    private final int rgb; 
    private final String ansiColorCode; 
    
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
