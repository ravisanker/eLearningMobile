package com.example.hopefoundation.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    private static final String TAG = JSONUtil.class.getSimpleName();

    public static String getString(JSONObject json, String key, String def) {
        try {
            if (json.isNull(key)) {
                return def;
            }
            if (json.has(key)) {
                return json.getString(key);
            } else {
                return def;
            }
        } catch (JSONException e) {

            return def;
        }
    }

    public static String getString(JSONObject json, String key) {
        try {
            if (json != null && json.isNull(key)) {
                return null;
            }
            if (json.has(key)) {
                return json.getString(key);
            } else {
                throw new IllegalArgumentException(key + " is missing");
            }
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            return null;
        }

    }

    public static long getLong(JSONObject json, String key) {
        try {
            if (json.has(key)) {
                return json.getLong(key);
            } else {
                throw new IllegalArgumentException(key + " is missing");
            }
        } catch (JSONException e) {
            return 0;
        }
    }

    public static long getLong(JSONObject json, String key, long defValue) {
        try {
            if (json.has(key)) {
                return json.getLong(key);
            } else {
                return defValue;
            }
        } catch (JSONException e) {
            return defValue;
        }
    }

    public static boolean getBoolean(JSONObject json, String key, boolean defValue) {
        try {
            if (json.has(key)) {
                return json.getBoolean(key);
            } else {
                return defValue;
            }
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            // TODO this doesn't seem right; 0 is a valid UNIX timestamp
            return defValue;
        } catch (NullPointerException npe) {
            return defValue;
        }
    }

    public static boolean getBoolean(JSONObject json, String key) {
        try {
            if (json.has(key)) {
                return json.getBoolean(key);
            } else {
                throw new IllegalArgumentException(key + " is missing");
            }
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            // TODO this doesn't seem right; 0 is a valid UNIX timestamp
            return false;
        }
    }

    public static int getInt(JSONObject json, String key) {
        try {
            if (json.has(key)) {
                return json.getInt(key);
            } else {
                throw new IllegalArgumentException(key + " is null");
            }
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            // TODO this doesn't seem right; 0 is a valid UNIX timestamp
            return 0;
        }
    }

    public static int getInt(JSONObject json, String key, int defaultValue) {
        try {
            if (json.has(key)) {
                return json.getInt(key);
            } else {
                return defaultValue;
            }
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public static double getDouble(JSONObject json, String key) {
        try {
            if (json.has(key)) {
                return json.getDouble(key);
            } else {
                throw new IllegalArgumentException(key + " is null");
            }
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            return -1;
        }
    }

    public static JSONArray getJSONArray(JSONObject json, String key) {
        try {
            if (json.has(key)) {
                return json.getJSONArray(key);
            } else {
                throw new IllegalArgumentException(key + " is null");
            }
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            return null;
        }
    }

    public static JSONArray getJSONArray(JSONObject json, String key, JSONArray defValue) {
        try {
            if (json.has(key)) {
                return json.getJSONArray(key);
            } else {
                return defValue;
            }
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            return defValue;
        }
    }

    public static JSONObject getJSONObject(JSONObject json, String key) {
        try {
            if (json.has(key)) {
                return json.getJSONObject(key);
            } else {
                throw new IllegalArgumentException(key + " is null");
            }
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            // TODO this doesn't seem right; 0 is a valid UNIX timestamp
            return null;
        }
    }

    public static double getDouble(JSONArray json, int index) {
        try {
            return json.getDouble(index);
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            return 0;
        }
    }

    public static JSONObject getJSONObject(String key) {
        try {
            JSONObject j = new JSONObject(key);
            return j;
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            // TODO Auto-generated catch block
            return null;
        }
    }

    public static JSONArray getJSONArray(String key) {
        try {
            JSONArray j = new JSONArray(key);
            return j;
        } catch (JSONException e) {
            // CrittericismUtils.logHandledException(e);
            // TODO Auto-generated catch block
            return null;
        }
    }

    public static List<Float> getFloatArrayList(JSONArray jArray) {
        List<Float> floatarray = new ArrayList<Float>();
        for (int i = 0; i < jArray.length(); i++) {
            try {
                floatarray.add(Float.parseFloat(jArray.get(i).toString()));
            } catch (NumberFormatException e) {
            } catch (JSONException e) {
            }
        }
        return floatarray;
    }

    public static float[] getFloatArray(JSONArray jArray) {
        float[] array = new float[jArray.length()];
        for (int i = 0; i < jArray.length(); i++) {
            try {
                array[i] = Float.parseFloat(jArray.get(i).toString());
            } catch (NumberFormatException e) {
            } catch (JSONException e) {
            }
        }
        return array;
    }

    public static List<String> getStringArrayList(JSONArray jArray) {
        List<String> stringArray = new ArrayList<String>();
        for (int i = 0; i < jArray.length(); i++) {
            try {
                stringArray.add(jArray.get(i).toString());
            } catch (NumberFormatException e) {
            } catch (JSONException e) {
            }
        }
        return stringArray;
    }

    public static String[] getStringArray(JSONArray jArray) {
        String[] array = new String[jArray.length()];
        for (int i = 0; i < jArray.length(); i++) {
            try {
                array[i] = jArray.get(i).toString();
            } catch (NumberFormatException e) {
            } catch (JSONException e) {
            }
        }
        return array;
    }

    public static JSONArray concatArray(JSONArray... arrs)
            throws JSONException {
        JSONArray result = new JSONArray();
        for (JSONArray arr : arrs) {
            for (int i = 0; i < arr.length(); i++) {
                result.put(arr.get(i));
            }
        }
        return result;
    }


    public static JSONArray concatArray(ArrayList<JSONArray> arrs)
            throws JSONException {
        JSONArray result = new JSONArray();
        for (JSONArray arr : arrs) {
            for (int i = 0; i < arr.length(); i++) {
                result.put(arr.get(i));
            }
        }
        return result;
    }
}
