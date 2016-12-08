package redmart.catalogue.com.webservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import redmart.catalogue.com.App;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */
public class WebServiceQueue {

    private Context mContext;
    private RequestQueue mRequestQueue;

    private static WebServiceQueue mInstance = new WebServiceQueue();

    public static synchronized WebServiceQueue getInstance() {
        return mInstance;
    }

    private WebServiceQueue() {
        mContext = App.getAppContext();
        mRequestQueue = getRequestQueue();
    }

    public <T> void addRequest(Request<T> request){
        getRequestQueue().add(request);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }




}
