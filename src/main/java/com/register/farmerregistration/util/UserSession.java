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
    static String customerID;
    static String userBranchId;
    static String userLocationID;
    static int employeeRandomUserID;
    static String userDB;
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



    public static String getUserLocationID() {
        return userLocationID;
    }

    public static void setUserLocationID(String userLocationID) {
        UserSession.userLocationID = userLocationID;
    }

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

    public static String getUserDB() {
        if (userDB == null) {
            return "ikooba_db";
        }
        return userDB;
    }

    public static void setUserDB(String userDB) {
        UserSession.userDB = userDB;
    }

    public static String getLoyaltyID() {
        return loyaltyID;
    }

    public static void setLoyaltyID(String loyaltyID) {
        UserSession.loyaltyID = loyaltyID;
    }

    static String loyaltyID;

    public static String getUserBranchId() {
        return userBranchId;
    }

    public static void setUserBranchId(String userBranchId) {
        UserSession.userBranchId = userBranchId;
    }


    public static String getCustomerID() {
        return customerID;
    }

    public static void setCustomerID(String customerID) {
        UserSession.customerID = customerID;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        UserSession.userID = userID;
    }

    public static int getEmployeeRandomUserID() {
        return employeeRandomUserID;
    }

    public static void setEmployeeRandomUserID(int employeeRandomUserID) {
        UserSession.employeeRandomUserID = employeeRandomUserID;
    }
}
