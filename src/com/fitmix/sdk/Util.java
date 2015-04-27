package com.fitmix.sdk;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Util {
	private static final String sPhotoFile = "photo.jpg";
	private final static String TAG = "fitmix";

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static Bitmap getPhotoFile() {
		Bitmap bm = BitmapFactory.decodeFile(getPhotoFilename());
		if (MyConfig.isLogOut())
			Log.d(TAG, "photo:" + bm);
		return bm;
	}

	public static String getPhotoFilename() {
		return MyConfig.getDataPath() + sPhotoFile;
	}

	public static boolean saveImage(Bitmap photo, String spath) {
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(spath, false));
			photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
