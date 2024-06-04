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
public class MapList {

    private List<Floors> floors;
    private long defaultFloor;

    public MapList(){

    }

    public void setFloors(List<Floors> floors) {
         this.floors = floors;
     }
     public List<Floors> getFloors() {
         return floors;
     }

    public void setDefaultFloor(long defaultFloor) {
         this.defaultFloor = defaultFloor;
     }
     public long getDefaultFloor() {
         return defaultFloor;
     }

}