package com.boat.support.slam.entity.floors;



import java.util.List;


public class CleanAreaEnty {
    String type;
    String name;
    long cleanAreaId;
    List<Lines> points;
    float radius;
    boolean closed;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCleanAreaId() {
        return cleanAreaId;
    }

    public void setCleanAreaId(long cleanAreaId) {
        this.cleanAreaId = cleanAreaId;
    }

    public List<Lines> getPoints() {
        return points;
    }

    public void setPoints(List<Lines> points) {
        this.points = points;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

}
