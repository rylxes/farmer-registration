package com.register.farmerregistration.util;



import com.register.farmerregistration.local.entities.User;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by sherriff on 28/03/2017.
 */

@Slf4j
public class UserSession {
    static String userID;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserSession.userName = userName;
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        UserSession.userType = userType;
    }

    static String userName;
    static String userType;
//    static String userLocationID;
//    static int employeeRandomUserID;
//    static String userDB;
    static User CurrentAuth;
    static String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    static String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    static boolean isEmployee = false;
    static boolean isFirstTime = false;

    public static boolean isIsFirstTime() {
        return isFirstTime;
    }

    public static void setIsFirstTime(boolean isFirstTime) {
        UserSession.isFirstTime = isFirstTime;
    }



//    public static String getUserLocationID() {
//        return userLocationID;
//    }
//
//    public static void setUserLocationID(String userLocationID) {
//        UserSession.userLocationID = userLocationID;
//    }

    public static String getToday() {
        return today;
    }

    public static void setToday(String today) {
        UserSession.today = today;
    }

    public static String getTodayDate() {
        return todayDate;
    }

    public static void setTodayDate(String todayDate) {
        UserSession.todayDate = todayDate;
    }

    public static User getCurrentAuth() {
        return CurrentAuth;
    }

    public static void setCurrentAuth(User currentAuth) {
        CurrentAuth = currentAuth;
    }



    public static String getLoyaltyID() {
        return loyaltyID;
    }

    public static void setLoyaltyID(String loyaltyID) {
        UserSession.loyaltyID = loyaltyID;
    }

    static String loyaltyID;


    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        UserSession.userID = userID;
    }




}
