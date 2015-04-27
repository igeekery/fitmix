package com.fitmix.sdk;

import java.io.File;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.util.Log;

public class MyConfig {
	private static boolean bLogOut = false;
	private static boolean bDemoMode = false;
	private static int iPasswordLengthMin = 6;
	private static int iUsernameLengthMin = 2;
	private static String mUsername;
	private static String mPassword;
	private static String mEmailAddress;
	private static int mSex;
	private static int mAge;
	private static int mHeight;
	private static int mWeight;
	private static int mUnit;
	private static boolean mLocation;
	private static boolean mNotification;
	private static boolean mTrail;

	private static boolean bLoginSuccess = false;
	private static String mDefaultUsername;
	private static String mDefaultPassword;
	private static String mDefaultEmailAddress;
	private static int mDefaultSex;
	private static int mDefaultAge;
	private static int mDefaultWeight;
	private static int mDefaultHeight;
	private static int mDefaultUnit;
	private static boolean mDefaultLocation;
	private static boolean mDefaultNotification;
	private static boolean mDefaultTrail;
	private static String mDataPath;
	public static final int SEX_MAL = 0;
	public static final int SEX_FEMAL = 1;

	private final static String sConfigFile = "config";
	private final static String TAG = "fitmix";
	
	private static String sMyRoot;
	private static final String sProductDir = "fitmix";	

	public MyConfig() {

	}

	public static void init(Context context) {
		if (context == null)
			return;

		bLogOut = context.getResources().getBoolean(R.bool.isLogout);
		bDemoMode = context.getResources().getBoolean(R.bool.isDemoMode);
		iPasswordLengthMin = context.getResources().getInteger(
				R.integer.password_length_min);
		iUsernameLengthMin = context.getResources().getInteger(
				R.integer.username_length_min);
		mDefaultUsername = context.getResources().getString(
				R.string.default_username);
		mDefaultPassword = context.getResources().getString(
				R.string.default_password);
		mDefaultEmailAddress = context.getResources().getString(
				R.string.default_email);
		mDefaultSex = context.getResources().getInteger(R.integer.default_sex);
		mDefaultAge = context.getResources().getInteger(R.integer.default_age);
		mDefaultWeight = context.getResources().getInteger(
				R.integer.default_weight);
		mDefaultHeight = context.getResources().getInteger(
				R.integer.default_height);
		mDefaultUnit = context.getResources()
				.getInteger(R.integer.default_unit);

		mDefaultLocation = context.getResources().getBoolean(
				R.bool.default_location);
		mDefaultNotification = context.getResources().getBoolean(
				R.bool.default_notification);
		mDefaultTrail = context.getResources().getBoolean(R.bool.default_trail);		
	}

	public static void loadConfig(Context context) {
		SharedPreferences sp = context.getSharedPreferences(sConfigFile,
				Context.MODE_PRIVATE);
		mUsername = sp.getString("username", mDefaultUsername);
		mPassword = sp.getString("password", mDefaultPassword);
		mEmailAddress = sp.getString("email", mDefaultEmailAddress);
		mSex = sp.getInt("sex", mDefaultSex);
		mAge = sp.getInt("age", mDefaultAge);
		mHeight = sp.getInt("height", mDefaultHeight);
		mWeight = sp.getInt("weight", mDefaultWeight);
		mUnit = sp.getInt("unit", mDefaultUnit);
		mNotification = sp.getBoolean("notification", mDefaultNotification);
		mLocation = sp.getBoolean("location", mDefaultLocation);
		mTrail = sp.getBoolean("trail", mDefaultTrail);

		if (!bLogOut)
			return;
		Log.d(TAG, "username:" + mUsername);
		Log.d(TAG, "password:" + mPassword);
		Log.d(TAG, "email:" + mEmailAddress);
		Log.d(TAG, "sex:" + mSex);
		Log.d(TAG, "age:" + mAge);
		Log.d(TAG, "height:" + mHeight);
		Log.d(TAG, "weight:" + mWeight);
		Log.d(TAG, "unit:" + mUnit);
		Log.d(TAG, "notification:" + mNotification);
		Log.d(TAG, "location:" + mLocation);
		Log.d(TAG, "trail:" + mTrail);

	}

	public static void saveConfig(Context context) {
		SharedPreferences sp = context.getSharedPreferences(sConfigFile,
				Context.MODE_PRIVATE);

		Editor editor = sp.edit();
		editor.putString("username", mUsername);
		editor.putString("password", mPassword);
		editor.putString("email", mEmailAddress);
		editor.putInt("sex", mSex);
		editor.putInt("age", mAge);
		editor.putInt("height", mHeight);
		editor.putInt("weight", mWeight);
		editor.putInt("unit", mUnit);
		editor.putBoolean("notification", mNotification);
		editor.putBoolean("location", mLocation);
		editor.putBoolean("trail", mTrail);
		editor.commit();

		if (!bLogOut)
			return;
		Log.d(TAG, "username:" + mUsername);
		Log.d(TAG, "password:" + mPassword);
		Log.d(TAG, "email:" + mEmailAddress);
		Log.d(TAG, "sex:" + mSex);
		Log.d(TAG, "age:" + mAge);
		Log.d(TAG, "height:" + mHeight);
		Log.d(TAG, "weight:" + mWeight);
		Log.d(TAG, "unit:" + mUnit);
		Log.d(TAG, "notification:" + mNotification);
		Log.d(TAG, "location:" + mLocation);
		Log.d(TAG, "trail:" + mTrail);

	}

	public static boolean isLogOut() {
		return bLogOut;
	}

	public static boolean isDemoMode() {
		return bDemoMode;
	}

	public static int getPasswordLengthMin() {
		return iPasswordLengthMin;
	}

	public static int getUsernameLengthMin() {
		return iUsernameLengthMin;
	}

	public static String getDefaultUsername() {
		return mDefaultUsername;
	}

	public static String getDefaultPassword() {
		return mDefaultPassword;
	}

	public static String getDefaultEmailAddress() {
		return mDefaultEmailAddress;
	}

	public static int getDefaultSex() {
		return mDefaultSex;
	}

	public static int getDefaultAge() {
		return mDefaultAge;
	}

	public static int getDefaultWeight() {
		return mDefaultWeight;
	}

	public static int getDefaultHeight() {
		return mDefaultHeight;
	}

	public static int getDefaultUnit() {
		return mDefaultUnit;
	}

	public static boolean getDefaultLocation() {
		return mDefaultLocation;
	}

	public static boolean getDefaultNotification() {
		return mDefaultNotification;
	}

	public static boolean getDefaultTrail() {
		return mDefaultTrail;
	}

	public static void setLoginSuccess(boolean bSuccess) {
		bLoginSuccess = bSuccess;
	}

	public static boolean getLoginSuccess() {
		return bLoginSuccess;
	}

	public static String getUsername() {
		return mUsername;
	}

	public static void setUsername(String sUsername) {
		mUsername = sUsername;
	}

	public static String getPassword() {
		return mPassword;
	}

	public static void setPassword(String sPassword) {
		mPassword = sPassword;
	}

	public static String getEmailAddress() {
		return mEmailAddress;
	}

	public static void setEmailAddress(String sEmailAddress) {
		mEmailAddress = sEmailAddress;
	}

	public static int getSex() {
		return mSex;
	}

	public static void setSex(int sex) {
		mSex = sex;
	}

	public static int getHeight() {
		return mHeight;
	}

	public static void setHeight(int height) {
		mHeight = height;
	}

	public static int getWeight() {
		return mWeight;
	}

	public static void setWeight(int weight) {
		mWeight = weight;
	}

	public static int getAge() {
		return mAge;
	}

	public static void setAge(int age) {
		mAge = age;
	}

	public static int getUnit() {
		return mUnit;
	}

	public static void setUnit(int unit) {
		mUnit = unit;
	}

	public static boolean getNotification() {
		return mNotification;
	}

	public static void setNotification(boolean notification) {
		mNotification = notification;
	}

	public static boolean getLocation() {
		return mLocation;
	}

	public static void setLocation(boolean location) {
		mLocation = location;
	}

	public static boolean getTrail() {
		return mTrail;
	}

	public static void setTrail(boolean trail) {
		mTrail = trail;
	}


	public static String getDataPath() {
		if (mDataPath == null) {
			sMyRoot = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			if (sMyRoot == null)
				return null;
			return sMyRoot + File.separator + sProductDir + File.separator;
		}
		return mDataPath;
	}

	public static void setDataPath(String sDataPath) {
		mDataPath = sDataPath;
	}

	
}
