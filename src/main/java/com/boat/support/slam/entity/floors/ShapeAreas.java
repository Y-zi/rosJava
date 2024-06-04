/**
 * Copyright 2022 json.cn
 */
package com.boat.support.slam.entity.floors;



import java.util.List;

/**
 * Auto-generated: 2022-04-28 15:55:37
 *
 * @author json.cn (i@json.cn)
 *
 */
public class ShapeAreas {

    private long shapeId;
    private int orderNum;
    private List<Lines> lines;
    private boolean closed;
    private Circle circle;
    private String type;
    private Ellipse ellipse;


    public void setShapeId(int shapeId) {
        this.shapeId = shapeId;
    }
    public long getShapeId() {
        return shapeId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setLines(List<Lines> lines) {
        this.lines = lines;
    }
    public List<Lines> getLines() {
        return lines;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    public boolean getClosed() {
        return closed;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
    public Circle getCircle() {
        return circle;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setEllipse(Ellipse ellipse) {
        this.ellipse = ellipse;
    }
    public Ellipse getEllipse() {
        return ellipse;
    }


}