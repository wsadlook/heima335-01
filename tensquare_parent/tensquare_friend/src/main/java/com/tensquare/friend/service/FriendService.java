package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Service
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Transactional
    public boolean like(String userid, String friendid) {
        //判断是否已经添加为好友
        Friend friend = friendDao.findIsFriend(userid, friendid);
        if(friend != null){
            //是好友，不能重复提交，返回false
            return false;
        }

        //不是好友，可以添加
        Friend friend1 = new Friend();
        friend1.setUserid(userid);
        friend1.setFriendid(friendid);
        friend1.setIslike("0");
        friendDao.save(friend1);

        //判断对方是否已经添加你为好友
        //如果对方已添加的话，需要修改双方的islike 状态为1
        Friend isFriend = friendDao.findIsFriend(friendid, userid);
        if(isFriend != null){
            friendDao.updateByIslike("1",userid,friendid);
            friendDao.updateByIslike("1",friendid,userid );
        }

        return true;
    }
    @Transactional
    public void addNoFriend(String userid, String friendid) {
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
    @Transactional
    public void delete(String userid, String friendid) {
        friendDao.deleteUserid(userid,friendid);
        Friend isFriend = friendDao.findIsFriend(friendid, userid);
        if(isFriend != null){
            friendDao.updateByIslike("0",friendid,userid);
        }
    }
}
