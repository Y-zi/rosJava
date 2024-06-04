/**
  * Copyright 2022 json.cn 
  */
package com.boat.support.slam.entity.floors;



/**
 * Auto-generated: 2022-04-25 16:42:5
 *
 * @author json.cn (i@json.cn)
 *
 */
public class Lines {

    private double x;
    private double y;
    public Lines(){

    }


    public void setX(double x) {
         this.x = x;
     }
     public double getX() {
         return x;
     }

    public void setY(double y) {
         this.y = y;
     }
     public double getY() {
         return y;
     }

    @Override
    public String toString() {
        return "Lines{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}