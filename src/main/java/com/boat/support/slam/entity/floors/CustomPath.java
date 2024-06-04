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
public class CustomPath {

    private long pathId;
    private List<Lines> lines;
    private boolean closed;
    private int state;
    private String name;

    public long getPathId() {
        return pathId;
    }

    public void setPathId(long pathId) {
        this.pathId = pathId;
    }

    public List<Lines> getLines() {
        return lines;
    }

    public void setLines(List<Lines> lines) {
        this.lines = lines;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}