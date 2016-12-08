package redmart.catalogue.com.catalogue;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import redmart.catalogue.com.R;
import redmart.catalogue.com.webservice.RetrieveProductDetail;
import redmart.catalogue.com.webservice.WebServiceListener;
import redmart.catalogue.com.webservice.model.ProductDetail;
import redmart.catalogue.com.webservice.model.WSResponseData;

import static redmart.catalogue.com.GlobalVariable.CURRENCYSYMBOL;

public class CatalogueDetail extends AppCompatActivity implements WebServiceListener {

    public static final String PRODUCT_ID="PRODUCT_ID";
    private static final String IMAGEBASEURL = "http://media.redmart.com/newmedia/200p";

    @BindView(R.id.ivProductImage)
    ImageView m_imProductImage;
    @BindView(R.id.tvProductName)
    TextView m_tvProductName;
    @BindView(R.id.tvProductDesc)
    TextView m_tvProductDesc;
    @BindView(R.id.tvVendorName)
    TextView m_tvProductVendor;
    @BindView(R.id.tvProductPrice)
    TextView m_tvProductPrice;
    @BindView(R.id.tvProductWeight)
    TextView m_tvProductWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        new RetrieveProductDetail(getIntent().getStringExtra(PRODUCT_ID),this).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // go to previous screen when app icon in action bar is clicked
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSuccess(WSResponseData data) {
        ProductDetail productDetail = (ProductDetail) data;

        getSupportActionBar().setTitle(productDetail.mProductName);
        m_tvProductName.setText(productDetail.mProductName);
        m_tvProductDesc.setText(productDetail.mProductDesc);
        m_tvProductPrice.setText(CURRENCYSYMBOL + productDetail.mPrice);
        m_tvProductWeight.setText(productDetail.mProductWeight);
        m_tvProductVendor.setText(productDetail.mProductVendor);

        Picasso.with(this)
                .load(IMAGEBASEURL +productDetail.mImagePath)
                .into(m_imProductImage);
    }

    @Override
    public void onError() {

        finish();
    }
}
