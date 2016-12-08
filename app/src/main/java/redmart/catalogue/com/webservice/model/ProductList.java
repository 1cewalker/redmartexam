package redmart.catalogue.com.webservice.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public class ProductList implements WSResponseData {

    public static final String PRODUCTS = "products";


    public ArrayList<ProductItem> mProductList;

    @Override
    public void parse(JSONObject webServiceResponse) {

        mProductList = new ArrayList<>();

        JSONArray productList;

        try {
            productList = webServiceResponse.getJSONArray(PRODUCTS);
            for(int i=0; i<productList.length(); i++){
                mProductList.add(new ProductItem(productList.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
