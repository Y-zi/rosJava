package com.boat.support.slam.entity.floors;



public class CleanDrvTest {


    private int rbLiftStatus =-1  ;  //滚扫模块升降   0：休息位 1：工作位
    private int rdLiftStatus  =-1   ;  //滚拖模块升降   0：休息位 1：工作位
    private int liftWipeWaterStatus   =-1   ;  //刮水模块升降   0：休息位 1：工作位
    private int brushPlateSpeed  =-1  ;  //边刷速度
    private int blowerSpeed    =-1    ;  //风机
    private int waterPumpStatus  =-1  ;  //水流量检测
    private int rollerBrushSpeed  =-1 ;  //滚扫电机旋转
    private int rollerDragSpeed =-1 ;  //滚拖电机旋转
    private int automaticClean  =-1 ;  //自清洁模式
    private int teeValveStatus  =-1   ;  //三通球阀状态
    private int solenoidStatus   =-1 ;  //电磁阀状态

    public int getRbLiftStatus() {
        return rbLiftStatus;
    }

    public void setRbLiftStatus(int rbLiftStatus) {
        this.rbLiftStatus = rbLiftStatus;
    }

    public int getRdLiftStatus() {
        return rdLiftStatus;
    }

    public void setRdLiftStatus(int rdLiftStatus) {
        this.rdLiftStatus = rdLiftStatus;
    }

    public int getLiftWipeWaterStatus() {
        return liftWipeWaterStatus;
    }

    public void setLiftWipeWaterStatus(int liftWipeWaterStatus) {
        this.liftWipeWaterStatus = liftWipeWaterStatus;
    }

    public int getBrushPlateSpeed() {
        return brushPlateSpeed;
    }

    public void setBrushPlateSpeed(int brushPlateSpeed) {
        this.brushPlateSpeed = brushPlateSpeed;
    }

    public int getBlowerSpeed() {
        return blowerSpeed;
    }

    public void setBlowerSpeed(int blowerSpeed) {
        this.blowerSpeed = blowerSpeed;
    }

    public int getWaterPumpStatus() {
        return waterPumpStatus;
    }

    public void setWaterPumpStatus(int waterPumpStatus) {
        this.waterPumpStatus = waterPumpStatus;
    }

    public int getRollerBrushSpeed() {
        return rollerBrushSpeed;
    }

    public void setRollerBrushSpeed(int rollerBrushSpeed) {
        this.rollerBrushSpeed = rollerBrushSpeed;
    }

    public int getRollerDragSpeed() {
        return rollerDragSpeed;
    }

    public void setRollerDragSpeed(int rollerDragSpeed) {
        this.rollerDragSpeed = rollerDragSpeed;
    }

    public int getAutomaticClean() {
        return automaticClean;
    }

    public void setAutomaticClean(int automaticClean) {
        this.automaticClean = automaticClean;
    }

    public int getTeeValveStatus() {
        return teeValveStatus;
    }

    public void setTeeValveStatus(int teeValveStatus) {
        this.teeValveStatus = teeValveStatus;
    }

    public int getSolenoidStatus() {
        return solenoidStatus;
    }

    public void setSolenoidStatus(int solenoidStatus) {
        this.solenoidStatus = solenoidStatus;
    }

}
