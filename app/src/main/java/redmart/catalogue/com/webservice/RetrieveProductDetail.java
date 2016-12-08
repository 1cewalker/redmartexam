package redmart.catalogue.com.webservice;

import org.json.JSONObject;

import redmart.catalogue.com.webservice.model.ProductDetail;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public class RetrieveProductDetail extends WebServiceTask {

    private String mProductId;
    private static final String TASK = "products";

    public RetrieveProductDetail(String id, WebServiceListener callback){
        super(callback);

        mProductId = id;
    }

    @Override
    protected void setUrl() {
        mURL += TASK + "/" + mProductId;
    }

    @Override
    protected void onSuccess(JSONObject response) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.parse(response);

        mCallback.onSuccess(productDetail);

    }

    @Override
    protected void onError() {
        mCallback.onError();
    }
}
