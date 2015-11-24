package com.example.base;

/**
 * Created by nadeem on 10-11-2015.
 */
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.others.LruBitmapCache;
import com.example.others.OkHttpStack;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;

/**
 * Global application singleton instance.
 */
public class App extends Application
{
    // Singleton application sInstance
    private static App sInstance;

    // Volley request queue
    private RequestQueue mRequestQueue;

    // Volley image cache
    private LruBitmapCache mLruBitmapCache;

    // Volley image loader
    private ImageLoader mImageLoader;

    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "sessionid";

    private SharedPreferences _preferences;

    @Override
    public void onCreate()
    {
        super.onCreate();

        sInstance = this;
        _preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    /**
     * @return the application singleton instance
     */
    public static App getInstance()
    {
        return sInstance;
    }

    /**
     * Returns a Volley request queue for creating network requests
     *
     * @return {@link com.android.volley.RequestQueue}
     */
    public RequestQueue getVolleyRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack(new OkHttpClient()));
        }
        return mRequestQueue;
    }

    /**
     * Adds a request to the Volley request queue
     *
     * @param request to be added to the Volley requests queue
     */
    private static void addRequest(@NonNull final Request<?> request)
    {
        getInstance().getVolleyRequestQueue().add(request);
    }

    /**
     * Adds a request to the Volley request queue with a given tag
     *
     * @param request is the request to be added
     * @param tag tag identifying the request
     */
    public static void addRequest(@NonNull final Request<?> request, @NonNull final String tag)
    {
        request.setTag(tag);
        addRequest(request);
    }

    /**
     * Cancels all the request in the Volley queue for a given tag
     *
     * @param tag associated with the Volley requests to be cancelled
     */
    public static void cancelAllRequests(@NonNull final String tag)
    {
        if (getInstance().getVolleyRequestQueue() != null)
        {
            getInstance().getVolleyRequestQueue().cancelAll(tag);
        }
    }

    /**
     * Returns an image loader instance to be used with Volley.
     *
     * @return {@link com.android.volley.toolbox.ImageLoader}
     */
    public ImageLoader getVolleyImageLoader()
    {
        if (mImageLoader == null)
        {
            mImageLoader = new ImageLoader
                    (
                            getVolleyRequestQueue(),
                            App.getInstance().getVolleyImageCache()
                    );
        }

        return mImageLoader;
    }

    /**
     * Returns a bitmap cache to use with volley.
     *
     * @return {@link LruBitmapCache}
     */
    private LruBitmapCache getVolleyImageCache()
    {
        if (mLruBitmapCache == null)
        {
            mLruBitmapCache = new LruBitmapCache(sInstance);
        }
        return mLruBitmapCache;
    }

    public final void checkSessionCookie(Map<String, String> headers) {
        if (headers.containsKey(SET_COOKIE_KEY)
                && headers.get(SET_COOKIE_KEY).startsWith(SESSION_COOKIE)) {
            String cookie = headers.get(SET_COOKIE_KEY);
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                SharedPreferences.Editor prefEditor = _preferences.edit();
                prefEditor.putString(SESSION_COOKIE, cookie);
                prefEditor.commit();
            }
        }
    }

    /**
     * Adds session cookie to headers if exists.
     * @param headers
     */
    public final void addSessionCookie(Map<String, String> headers) {
        String sessionId = _preferences.getString(SESSION_COOKIE, "");
        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(SESSION_COOKIE);
            builder.append("=");
            builder.append(sessionId);
            if (headers.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(headers.get(COOKIE_KEY));
            }
            headers.put(COOKIE_KEY, builder.toString());
        }
    }


}
