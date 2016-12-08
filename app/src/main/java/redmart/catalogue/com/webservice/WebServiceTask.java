package redmart.catalogue.com.webservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public abstract class WebServiceTask {

    private static final String URLBASE = "https://api.redmart.com/v1.5.6/catalog/";
    private Context mContext;
    protected String mURL;
    protected WebServiceListener mCallback;

    protected WebServiceTask(WebServiceListener callback){
        mURL = URLBASE;
        mCallback = callback;
    }

    public void start(){

        setUrl();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, mURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onError();

                    }
                });

        WebServiceQueue.getInstance().addRequest(jsObjRequest);
    }

    protected abstract void setUrl();
    protected abstract void onSuccess(JSONObject response);
    protected abstract void onError();
}