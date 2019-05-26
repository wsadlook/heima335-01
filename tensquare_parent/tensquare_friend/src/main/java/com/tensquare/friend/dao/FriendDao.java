package com.tensquare.friend.dao;


import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {

    @Query(value = "SELECT * FROM tb_friend WHERE userid = ? AND friendid = ?",nativeQuery = true)
    Friend findIsFriend(String userid ,String friendid);


    @Query(value = "update tb_friend set islike = ? where userid = ? and friendid = ?",nativeQuery = true)
    @Modifying
    void updateByIslike(String s, String userid, String friendid);


    @Query(value = "delete from tb_friend where userid = ?  and friendid = ?",nativeQuery = true)
    @Modifying
    void deleteUserid(String userid,String friendid);
}
