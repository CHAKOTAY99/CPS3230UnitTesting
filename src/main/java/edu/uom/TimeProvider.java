package edu.uom;

public interface TimeProvider {
    public final static int Morning = 0;
    public final static int Afternoon = 1;
    public final static int Evening = 2;

    public int getTimeSegment();
}
