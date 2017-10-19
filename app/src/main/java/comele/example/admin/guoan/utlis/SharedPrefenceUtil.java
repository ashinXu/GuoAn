package comele.example.admin.guoan.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import comele.example.admin.guoan.UserApplication;


public class SharedPrefenceUtil {
    public static final String IS_WIFI_DOWNLOAD_PROMPT = "is_wifi_download_prompt";
    public static final String MSG_TIME_STAMP = "msg_time_stamp";
    public static final String PRODUCT_TIME_STAMP = "product_time_stamp";
    public static final String VERSION_TIME_STAMP = "version_time_stamp";
    public static final String USERNAME_ADDITION = "addition_";

    public synchronized static String readConfig(Context context, String key,
                                                 String defaultValue) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        return sharedPreferences.getString(key, defaultValue);
    }

    public synchronized static void writeConfig(Context context, String key,
                                                String value) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public synchronized static Boolean read(Context context, String key,
                                            Boolean defaultValue) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public synchronized static void write(Context context, String key,
                                          Boolean value) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public synchronized static int read(Context context, String key,
                                        int defaultValue) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public synchronized static void write(Context context, String key, int value) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public synchronized static void remove(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.commit();
    }

    private static SharedPreferences getSharedprefence(Context context) {
        if (context != null) {
            return context.getSharedPreferences(
                    ConstUtil.SHAREDPREFENCE_FILE,
                    Context.MODE_PRIVATE);
        } else {
            return UserApplication.getApplication().getSharedPreferences(
                    ConstUtil.SHAREDPREFENCE_FILE,
                    Context.MODE_PRIVATE);
        }


    }

    public synchronized static Set<String> readStringSet(Context context, String key,
                                                         Set<String> defaultValue) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    public synchronized static void writeStringSet(Context context, String key,
                                                   Set<String> saveSet) {
        SharedPreferences sharedPreferences = getSharedprefence(context);
        Editor edit = sharedPreferences.edit();
        edit.putStringSet(key, saveSet);
        edit.commit();
    }

    public static void writeMap(Context context, String key, Map<String, Object> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Iterator<Map.Entry<String, Object>> iterator = datas.entrySet().iterator();

            JSONObject object = new JSONObject();

            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry;
                entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mJsonArray.put(object);
        }

        SharedPreferences sp = getSharedprefence(context);
        Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }

    public static Map<String, Object> getMap(Context context, String key) {
        SharedPreferences sp = getSharedprefence(context);
        Map<String, Object> itemMap = null;
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                itemMap = new HashMap<String, Object>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return itemMap;
    }
}
