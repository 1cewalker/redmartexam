package redmart.catalogue.com.webservice.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public class ProductDetail implements WSResponseData {

    public static final String PRODUCT = "product";
    public static final String ID = "id";
    public static final String PRICING = "pricing";
    public static final String PRICE = "price";
    public static final String IMAGE = "img";
    public static final String IMAGEURL = "name";
    public static final String PRODUCTNAME = "title";
    public static final String PRODUCTDESC = "desc";
    public static final String MEASURE = "measure";
    public static final String WEIGHT = "wt_or_vol";
    public static final String FILTERS = "filters";
    public static final String VENDOR = "mfr_name";


    public String mProductName;
    public String mProductId;
    public String mImagePath;
    public String mPrice;
    public String mProductDesc;
    public String mProductWeight;
    public String mProductVendor;

    @Override
    public void parse(JSONObject webServiceResponse) {
        try {
            JSONObject product = webServiceResponse.getJSONObject(PRODUCT);

            mProductId = product.getString(ID);
            mProductName = product.getString(PRODUCTNAME);
            mProductDesc = product.getString(PRODUCTDESC);
            mProductWeight = product.getJSONObject(MEASURE).getString(WEIGHT);
            mProductVendor = product.getJSONObject(FILTERS).getString(VENDOR);
            mPrice = product.getJSONObject(PRICING).getString(PRICE);
            mImagePath = product.getJSONObject(IMAGE).getString(IMAGEURL);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
