package redmart.catalogue.com.catalogue;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import redmart.catalogue.com.R;
import redmart.catalogue.com.webservice.model.ProductItem;

import static redmart.catalogue.com.GlobalVariable.CURRENCYSYMBOL;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public class CatalogueAdapter extends ArrayAdapter<ProductItem>{

    private Context mContext;
    private static final String IMAGEBASEURL = "http://media.redmart.com/newmedia/200p";

    public CatalogueAdapter(Context context) {
        super(context, R.layout.product_row);
        mContext = context;

    }

    public void addData(List<ProductItem> productList){

        this.addAll(productList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.product_row, null);

        if(getCount()>0){
            ProductItem item = this.getItem(position);

            ImageView productImage = (ImageView)v.findViewById(R.id.ivProductImage);
            TextView productName = (TextView) v.findViewById(R.id.tvProductName);
            TextView productPrice = (TextView) v.findViewById(R.id.tvProductPrice);

            Picasso.with(mContext)
                    .load(IMAGEBASEURL +item.mImagePath)
                    .resize(100, 100)
                    .centerCrop()
                    .into(productImage);
            productName.setText(item.mProductName);
            productPrice.setText(CURRENCYSYMBOL + item.mPrice);

        }

        return v;
    }

}
