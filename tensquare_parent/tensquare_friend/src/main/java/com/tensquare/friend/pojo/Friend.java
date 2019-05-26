package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)//一个PoJo中有两个主键，此注解  联合主键
public class Friend implements Serializable {

    @Id
    private String userid;
    @Id
    private String friendid;
    //是否相互喜欢，0代表单向喜欢，1代表相互喜欢
    private String islike;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userid='" + userid + '\'' +
                ", friendid='" + friendid + '\'' +
                ", islike='" + islike + '\'' +
                '}';
    }
}
