package com.xuesen.modle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *  * Created by 智光 on 2018/1/11 16:09
 *  事件
 * http://www.cnblogs.com/whoislcj/p/5651396.html
 */
@Entity
public class Action {
    @Id(autoincrement = true)//只增长
    private Long id;//主键
    private String name;
    private long startime;
    private long endtime;
    private String description;
    private String date;//2018/1/12

    @Generated(hash = 496338150)
    public Action(Long id, String name, long startime, long endtime,
                  String description, String date) {
        this.id = id;
        this.name = name;
        this.startime = startime;
        this.endtime = endtime;
        this.description = description;
        this.date = date;
    }

    @Generated(hash = 2056262033)
    public Action() {
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

    public long getStartime() {
        return this.startime;
    }

    public void setStartime(long startime) {
        this.startime = startime;
    }

    public long getEndtime() {
        return this.endtime;
    }

    public void setEndtime(long endtime) {
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
