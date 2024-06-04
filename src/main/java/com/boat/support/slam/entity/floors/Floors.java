/**
  * Copyright 2022 json.cn 
  */
package com.boat.support.slam.entity.floors;



import java.util.List;

/**
 * Auto-generated: 2022-04-25 16:48:29
 *
 * @author json.cn (i@json.cn)
 *
 */
public class Floors {

    private String floorName;
    private long floorId;
    private long defaultmap;
    private List<Maps> maps;

    public Floors() {

    }

    @Override
    public String toString() {
        return "Floors{" +
                "floorName='" + floorName + '\'' +
                ", floorId=" + floorId +
                ", defaultmap=" + defaultmap +
                ", maps=" + maps +
                '}';
    }


    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }

    public long getFloorId() {
        return floorId;
    }


    public void setDefaultmap(long defaultmap) {
        this.defaultmap = defaultmap;
    }

    public long getDefaultmap() {
        return defaultmap;
    }

    public void setMaps(List<Maps> maps) {
        this.maps = maps;
    }

    public List<Maps> getMaps() {
        return maps;
    }
}