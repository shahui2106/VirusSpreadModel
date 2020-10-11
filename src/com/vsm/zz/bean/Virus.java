package com.vsm.zz.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Zhu
 * @createtime 2020/9/29-14:43
 */
@Accessors(chain = true)
@Data
public class Virus {
    //病毒的类型
    String type = "无";
    //病毒潜伏期时长(单位：天)
    String lurkingTime;
    int lurkingStarTime;
    int lurkingEndTime;
    //病毒潜伏期传播率
    int lurkingRate;
    //病毒发病期时长(单位：天)
    String illTime;
    int illStartTime;
    int illEndTime;
    //病毒发病传播率
    int illRate;

    @Override
    public String toString() {
        return "Virus{" +
                "type='" + type + '\'' +
                ", lurkingTime='" + lurkingTime + '\'' +
                ", lurkingStarTime=" + lurkingStarTime +
                ", lurkingEndTime=" + lurkingEndTime +
                ", lurkingRate=" + lurkingRate +
                ", illTime='" + illTime + '\'' +
                ", illStartTime=" + illStartTime +
                ", illEndTime=" + illEndTime +
                ", illRate=" + illRate +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLurkingTime() {
        if (lurkingStarTime == lurkingEndTime)
            return String.valueOf(lurkingStarTime);
        return lurkingStarTime + " - " + lurkingEndTime;
    }


    public void setLurkingStarTime(int lurkingStarTime) {
        this.lurkingStarTime = lurkingStarTime;
    }

    public void setLurkingEndTime(int lurkingEndTime) {
        this.lurkingEndTime = lurkingEndTime;
    }

    public void setIllStartTime(int illStartTime) {
        this.illStartTime = illStartTime;
    }

    public void setIllEndTime(int illEndTime) {
        this.illEndTime = illEndTime;
    }

    public int getLurkingRate() {
        return lurkingRate;
    }

    public void setLurkingRate(int lurkingRate) {
        this.lurkingRate = lurkingRate;
    }

    public String getIllTime() {
        if (illEndTime == illStartTime)
            return String.valueOf(illStartTime);
        else
            return illStartTime + " - " + illEndTime;
    }

    public int getIllRate() {
        return illRate;
    }

    public void setIllRate(int illRate) {
        this.illRate = illRate;
    }
}
