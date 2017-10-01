package com.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by gao on 2017/9/27.
 */

public class AppUtil {

    /**
     * 获取签名key
     *
     * @param context
     * @return
     */
    public static String getHashKey(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(signature.toByteArray());
                String currentSignature = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                DebugLog.d("gao", currentSignature);
                if (!TextUtils.isEmpty(currentSignature)) {
                    return currentSignature.trim();
                }
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 某應用是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            return context.getPackageManager().getApplicationInfo(packageName, 0) != null;
        } catch (Throwable e) {
            return false;
        }
    }

    /**
     * 获得当前Apk的版本号，如：1.0.0
     */
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "1.0";
        }
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }
}
