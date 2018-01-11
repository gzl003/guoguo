package com.xuesen.modle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *  * Created by 智光 on 2018/1/11 16:13
 *  
 */
@Entity
public class BabyInfo  {
    @Id
    private Long id;
    public String name;
    public String birthday;
    public String sex;
    public String weight;//重量
    public String height;//身高
    @Generated(hash = 713797724)
    public BabyInfo(Long id, String name, String birthday, String sex,
            String weight, String height) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
    }
    @Generated(hash = 421042228)
    public BabyInfo() {
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
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getWeight() {
        return this.weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getHeight() {
        return this.height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
}
