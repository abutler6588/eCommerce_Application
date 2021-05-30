package com.example.demo;

import com.example.demo.model.persistence.User;

import java.lang.reflect.Field;

public class TestUtils {

    public static void injectObjects(Object target, String filedName, Object toInject){

        boolean wasPrivate = false;

        try {
        Field f = target.getClass().getDeclaredField(filedName);

        if(!f.isAccessible()){
            f.setAccessible(true);
            wasPrivate = true;
            }
            f.set(target, toInject);
            if(wasPrivate){
                f.setAccessible(false);
            }
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static User createUser(long id, String username, String password) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}
