/**
  * Copyright 2023 json.cn 
  */
package com.boat.support.slam.entity.floors;


import java.util.List;

/**
 * Auto-generated: 2023-03-08 13:16:44
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class GlobalPlans {

    private List<AddPoints> addPoints;
    private List<Lines> lines;
    private String globalplanName;
    private int orderNum;
    private long globalplanId;
    private int dir;

    public void setAddPoints(List<AddPoints> addPoints) {
         this.addPoints = addPoints;
     }
     public List<AddPoints> getAddPoints() {
         return addPoints;
     }

    public void setLines(List<Lines> lines) {
         this.lines = lines;
     }
     public List<Lines> getLines() {
         return lines;
     }

    public void setGlobalplanName(String globalplanName) {
         this.globalplanName = globalplanName;
     }
     public String getGlobalplanName() {
         return globalplanName;
     }

    public void setOrderNum(int orderNum) {
         this.orderNum = orderNum;
     }
     public int getOrderNum() {
         return orderNum;
     }

    public void setGlobalplanId(long globalplanId) {
         this.globalplanId = globalplanId;
     }
     public long getGlobalplanId() {
         return globalplanId;
     }

    public void setDir(int dir) {
         this.dir = dir;
     }
     public int getDir() {
         return dir;
     }

}