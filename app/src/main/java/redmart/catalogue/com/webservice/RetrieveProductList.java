package redmart.catalogue.com.webservice;

import android.util.Log;

import org.json.JSONObject;

import redmart.catalogue.com.webservice.model.ProductList;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public class RetrieveProductList extends WebServiceTask {

    private int mPageSize = 20;
    private int mPage;
    private static final String TASK = "search";
    private static final String PAGESIZE = "pageSize";
    private static final String PAGE = "page";

    public RetrieveProductList(int Page, WebServiceListener callback){
        super(callback);
        mPage = Page;
    }

    @Override
    public void setUrl() {
        mURL += TASK + "?" + PAGE + "=" + mPage + "&" + PAGESIZE + "=" + mPageSize;
    }

    @Override
    public void onSuccess(JSONObject response) {
        ProductList productList = new ProductList();
        productList.parse(response);

        mCallback.onSuccess(productList);
    }

    @Override
    public void onError() {
        mCallback.onError();
    }
}
