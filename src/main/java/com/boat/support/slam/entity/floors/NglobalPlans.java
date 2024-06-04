package com.boat.support.slam.entity.floors;



import java.util.List;

public class NglobalPlans {
    private List<NPoint> nPoints;
    private List<NLine> nLines;

    public List<NPoint> getnPoints() {
        return nPoints;
    }

    public void setnPoints(List<NPoint> nPoints) {
        this.nPoints = nPoints;
    }

    public List<NLine> getnLines() {
        return nLines;
    }

    public void setnLines(List<NLine> nLines) {
        this.nLines = nLines;
    }

}
