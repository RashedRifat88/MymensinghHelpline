package com.egsystembd.MymensinghHelpline.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedData {

    public static final String IS_USER_LOGGED_IN = "is_user_logged_in";
    public static final String IS_USER_REGISTERED = "is_user_registered";
    public static final String DEVICE_ID = "device_id";
    public static final String TOKEN_OTP = "token_otp";
    public static final String TOKEN = "token";
    public static final String PASSWORD = "password";
    public static final String DOCTOR_ID = "doctor_id";
    public static final String NOTIFICATION_COUNT = "notification_count";
    public static final String USER_NAME = "user_name";
    public static final String BMDC_NO = "bmdc_no";
    public static final String PROFILE_IMAGE_URL = "profile_image_url";
    public static final String FATHER_NAME = "father_name";
    public static final String MOTHER_NAME = "mother_name";
    public static final String MOBILE = "mobile";
    public static final String EMAIL = "email";
    public static final String FB_ID = "fb_id";
    public static final String NID = "nid";
    public static final String PASSPORT = "passport";
    public static final String JOB_DESCRIPTION = "job_description";
    public static final String PRESENT_ADDRESS = "present_address";

    public static final String SKIPPED_QUES_ID_STORED = "skipped_ques_id_stored";

    public static final String NIGHT_NODE_ENABLED = "night_node_enabled";
    public static final String ADDS_DISABLED = "adds_disabled";

    public static final String COURSE_NAME = "course_name";
    public static final String EXAM_NAME = "exam_name";
    public static final String EXAM_DURATION = "exam_duration";
    public static final String MCQ_NUMBER = "mcq_number";
    public static final String MCQ_MARK = "mcq_mark";
    public static final String MCQ_NEGATIVE_MARK = "mcq_negative_mark";
    public static final String SBA_NUMBER = "sba_number";
    public static final String SBA_MARK = "sba_mark";
    public static final String SBA_NEGATIVE_MARK = "sba_negative_mark";


    public static void saveTOKEN(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(TOKEN, value);
        editor.commit();
    }

    public static String getTOKEN(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(TOKEN, null);
    }
    ///


    public static void saveTOKEN_OTP(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(TOKEN_OTP, value);
        editor.commit();
    }

    public static String getTOKEN_OTP(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(TOKEN_OTP, null);
    }
    ///

    ///
    public static void saveIS_USER_LOGGED_IN(Context context, boolean value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean(IS_USER_LOGGED_IN, value);
        editor.commit();
    }

    public static Boolean getIS_USER_LOGGED_IN(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false);
    }
    ///
    public static void saveIS_USER_REGISTERED(Context context, boolean value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean(IS_USER_REGISTERED, value);
        editor.commit();
    }

    public static Boolean getIS_USER_REGISTERED(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(IS_USER_REGISTERED, false);
    }

    ///
    public static void saveNIGHT_NODE_ENABLED(Context context, boolean value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean(NIGHT_NODE_ENABLED, value);
        editor.commit();
    }

    public static Boolean getNIGHT_NODE_ENABLED(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(NIGHT_NODE_ENABLED, false);
    }

    public static void saveADDS_DISABLED(Context context, boolean value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean(ADDS_DISABLED, value);
        editor.commit();
    }

    public static Boolean getADDS_DISABLED(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(ADDS_DISABLED, false);
    }

    ///

    public static void saveFATHER_NAME(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(FATHER_NAME, value);
        editor.commit();
    }

    public static String getFATHER_NAME(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(FATHER_NAME, null);
    }

    //
    public static void saveMOTHER_NAME(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(MOTHER_NAME, value);
        editor.commit();
    }

    public static String getMOTHER_NAME(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MOTHER_NAME, null);
    }

    //
    public static void saveMOBILE(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(MOBILE, value);
        editor.commit();
    }

    public static String getMOBILE(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MOBILE, null);
    }

    //
    public static void saveEMAIL(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(EMAIL, value);
        editor.commit();
    }

    public static String getEMAIL(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(EMAIL, null);
    }

    //
    public static void saveFB_ID(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(FB_ID, value);
        editor.commit();
    }

    public static String getFB_ID(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(FB_ID, null);
    }

    //
    public static void saveNID(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(NID, value);
        editor.commit();
    }

    public static String getNID(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(NID, null);
    }

    //
    public static void savePASSPORT(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(PASSPORT, value);
        editor.commit();
    }

    public static String getPASSPORT(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PASSPORT, null);
    }

    //
    public static void saveJOB_DESCRIPTION(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(JOB_DESCRIPTION, value);
        editor.commit();
    }

    public static String getJOB_DESCRIPTION(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(JOB_DESCRIPTION, null);
    }
    //

    public static void savePRESENT_ADDRESS(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(PRESENT_ADDRESS, value);
        editor.commit();
    }

    public static String getPRESENT_ADDRESS(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PRESENT_ADDRESS, null);
    }
    //


    ///


    public static void saveCOURSE_NAME(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(COURSE_NAME, value);
        editor.commit();
    }

    public static String getCOURSE_NAME(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(COURSE_NAME, null);
    }


    public static void saveEXAM_NAME(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(EXAM_NAME, value);
        editor.commit();
    }

    public static String getEXAM_NAME(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(EXAM_NAME, null);
    }


    public static void saveEXAM_DURATION(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(EXAM_DURATION, value);
        editor.commit();
    }

    public static String getEXAM_DURATION(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(EXAM_DURATION, null);
    }


    public static void saveMCQ_NUMBER(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(MCQ_NUMBER, value);
        editor.commit();
    }

    public static String getMCQ_NUMBER(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MCQ_NUMBER, null);
    }

    public static void saveMCQ_MARK(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(MCQ_MARK, value);
        editor.commit();
    }

    public static String getMCQ_MARK(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MCQ_MARK, null);
    }

    public static void saveMCQ_NEGATIVE_MARK(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(MCQ_NEGATIVE_MARK, value);
        editor.commit();
    }

    public static String getMCQ_NEGATIVE_MARK(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MCQ_NEGATIVE_MARK, null);
    }

    public static void saveSBA_NUMBER(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(SBA_NUMBER, value);
        editor.commit();
    }

    public static String getSBA_NUMBER(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SBA_NUMBER, null);
    }

    public static void saveSBA_MARK(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(SBA_MARK, value);
        editor.commit();
    }

    public static String getSBA_MARK(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SBA_MARK, null);
    }

    public static void saveSBA_NEGATIVE_MARK(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(SBA_NEGATIVE_MARK, value);
        editor.commit();
    }

    public static String getSBA_NEGATIVE_MARK(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SBA_NEGATIVE_MARK, null);
    }

    ///


    public static void saveDOCTOR_ID(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(DOCTOR_ID, value);
        editor.commit();
    }

    public static String getDOCTOR_ID(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(DOCTOR_ID, null);
    }
    ///


    public static void saveNOTIFICATION_COUNT(Context context, int value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt(NOTIFICATION_COUNT, value);
        editor.commit();
    }

    public static int getNOTIFICATION_COUNT(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(NOTIFICATION_COUNT, 0);
    }


    public static void savePASSWORD(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(PASSWORD, value);
        editor.commit();
    }

    public static String getPASSWORD(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PASSWORD, null);
    }


    public static void saveUSER_NAME(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(USER_NAME, value);
        editor.commit();
    }

    public static String getUSER_NAME(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USER_NAME, null);
    }


    public static void savePROFILE_IMAGE_URL(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(PROFILE_IMAGE_URL, value);
        editor.commit();
    }

    public static String getPROFILE_IMAGE_URL(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PROFILE_IMAGE_URL, null);
    }


    public static void saveBMDC_NO(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(BMDC_NO, value);
        editor.commit();
    }

    public static String getBMDC_NO(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(BMDC_NO, null);
    }


    public static void saveSKIPPED_QUES_ID_STORED(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(SKIPPED_QUES_ID_STORED, value);
        editor.commit();
    }

    public static String getSKIPPED_QUES_ID_STORED(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SKIPPED_QUES_ID_STORED, "0");
    }


}
