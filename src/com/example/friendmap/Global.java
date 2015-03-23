package com.example.friendmap;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Global {
	private static String strIpMain = "119.29.93.212:8080";
	//private static String strIpMain = "192.168.1.112:8080";
	/** 服务器IP */
	public static String getIpMain() {
		// TODO Auto-generated method stub
		return strIpMain;
	}

	/** 获得版本名 */
	public static String getVersionName() {
		return getPackageInfo().versionName;
	}
	/** 获得版本号 */
	public static int getVersionCode(){
		return getPackageInfo().versionCode;
	}

	/** 获得包信息 */
	public static PackageInfo getPackageInfo() {
		PackageInfo packageInfo=null;
		try {
			PackageManager manager = FMApplication.getInstance().getPackageManager();
			packageInfo = manager.getPackageInfo(FMApplication.getInstance().getPackageName(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packageInfo;
	}

}
