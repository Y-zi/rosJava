package com.boat.support.slam.entity.floors;



public class NLine {

    private long id ;
    private long startNid ;
    private long endNid ;
    private int type;
    private int direction;
    private float weight;
    private float speed;


    public NLine(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartNid() {
        return startNid;
    }

    public void setStartNid(long startNid) {
        this.startNid = startNid;
    }

    public long getEndNid() {
        return endNid;
    }

    public void setEndNid(long endNid) {
        this.endNid = endNid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
