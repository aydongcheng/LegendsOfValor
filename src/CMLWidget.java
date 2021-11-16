abstract class CMLWidget {
    protected int widgetWidth = 0;
    protected int widgetHeight = 0;

    private int rowStart = 0;
    private int colStart = 0;

    private int currentLinePosition = 0;

    public CMLWidget() {
    }
    
    public void setWidthHeight(int widgetWidth, int widgetHeight) {
        this.widgetWidth = widgetWidth;
        this.widgetHeight = widgetHeight;
    }

    public void setPosition(int rowStart, int colStart) {
        this.rowStart = rowStart;
        this.colStart = colStart;
    }

    public boolean isWithinRange(int rowCurrent, int colCurrent) {
        boolean cond1 = (rowCurrent >= rowStart) && (rowCurrent < rowStart + widgetHeight); 
        boolean cond2 = (colCurrent >= colStart) && (colCurrent < colStart + widgetWidth); 
        return cond1 && cond2;
    }

    public boolean isStartPoint(int colCurrent) {
        return colCurrent == colStart;
    }

    public String nextLineWithLoop() {
        String ret = toString().split("\n")[currentLinePosition];

        currentLinePosition += 1;
        if (currentLinePosition == widgetHeight) {
            currentLinePosition -= widgetHeight;
        }
        return ret;
    }

    public abstract String toString();
}
