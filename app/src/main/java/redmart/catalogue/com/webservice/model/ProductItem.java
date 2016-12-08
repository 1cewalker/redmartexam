package redmart.catalogue.com.webservice.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public class ProductItem {

    public static final String ID = "id";
    public static final String PRICING = "pricing";
    public static final String PRICE = "price";
    public static final String IMAGE = "img";
    public static final String IMAGEURL = "name";
    public static final String PRODUCTNAME = "title";

    public String mProductName;
    public String mProductId;
    public String mImagePath;
    public String mPrice;

    public ProductItem(JSONObject item){
        try {
            mProductId = item.getString(ID);
            mProductName = item.getString(PRODUCTNAME);
            mPrice = item.getJSONObject(PRICING).getString(PRICE);
            mImagePath = item.getJSONObject(IMAGE).getString(IMAGEURL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
