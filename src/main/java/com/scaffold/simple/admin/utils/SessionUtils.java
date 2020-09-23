package com.scaffold.simple.admin.utils;


import com.scaffold.simple.admin.application.dto.user.UserDto;

import java.util.Map;

/**
 * Session
 */
public class SessionUtils {
    private static ThreadLocal<Session> userThreadLocal = new ThreadLocal<>();

    public static void setUser(UserDto user) {
        Session session =new Session();
        session.setUserDto(user);
        userThreadLocal.set(session);
    }

    public static void setUser(UserDto user,String groupId){
        Session session =new Session();
        session.setUserDto(user);
        userThreadLocal.set(session);
    }

    public static UserDto getUser() {
        return userThreadLocal.get().getUserDto();
    }

    public static String getUserId() {
        Session session = userThreadLocal.get();
        return session != null ? session.getUserDto().getUserId() : null;
    }

    public static void removeUser() {
        userThreadLocal.remove();
    }


    public static void setGroupId(String groupId){
        Session session =userThreadLocal.get();
        userThreadLocal.set(session);
    }

    public static void setRoleMap(Map<String, Boolean> map) {
        Session session =userThreadLocal.get();
        session.setRoleMap(map);
        userThreadLocal.set(session);
    }

    public static Map getRoleMap(){
        return userThreadLocal.get().getRoleMap();
    }
}
