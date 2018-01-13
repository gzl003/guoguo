package com.xuesen.modle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/1/13.
 */
@Entity
public class ActionCount {

    @Id(autoincrement = true)//只增长
    private Long id;//主键
    private String name;
    private String startime;
    private String endtime;
    private String description;
    private String date;//2018/1/12
    @Generated(hash = 508797926)
    public ActionCount(Long id, String name, String startime, String endtime,
            String description, String date) {
        this.id = id;
        this.name = name;
        this.startime = startime;
        this.endtime = endtime;
        this.description = description;
        this.date = date;
    }
    @Generated(hash = 667733589)
    public ActionCount() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStartime() {
        return this.startime;
    }
    public void setStartime(String startime) {
        this.startime = startime;
    }
    public String getEndtime() {
        return this.endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
