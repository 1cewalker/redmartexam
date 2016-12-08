package redmart.catalogue.com.catalogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import redmart.catalogue.com.webservice.RetrieveProductList;
import redmart.catalogue.com.webservice.WebServiceListener;
import redmart.catalogue.com.webservice.WebServiceTask;
import redmart.catalogue.com.webservice.model.ProductList;
import redmart.catalogue.com.webservice.model.WSResponseData;

import static redmart.catalogue.com.catalogue.CatalogueDetail.PRODUCT_ID;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public class CatalogueList extends ListFragment implements WebServiceListener,AbsListView.OnScrollListener {

    private CatalogueAdapter mAdapter;
    private int mPage = 0;
    private int mOffset = 5;

    private boolean lock = false; //prevent requesting twice

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new CatalogueAdapter(getActivity());
        setListAdapter(mAdapter);
        setRetainInstance(true);
        getListView().setOnScrollListener(this);

        retrieveProductListFromServer();

    }

    private void retrieveProductListFromServer(){
        WebServiceTask task = new RetrieveProductList(mPage++,this);
        task.start();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(),CatalogueDetail.class);
        intent.putExtra(PRODUCT_ID,mAdapter.getItem(position).mProductId);
        startActivity(intent);
    }

    @Override
    public void onSuccess(WSResponseData data) {

        ProductList productList = (ProductList)data;
        mAdapter.addData(productList.mProductList);

        lock = false;
    }

    @Override
    public void onError() {
        lock = false;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        int position = firstVisibleItem+visibleItemCount;
        int limit = totalItemCount - mOffset;

        // Check if bottom has been reached
        if (position >= limit && totalItemCount > 0 && !lock) {
            Log.d("onScroll","bottom reached");
            lock = true;
            retrieveProductListFromServer();
//            if (mListener != null ) {
//                mListener.onBottomReached();
//            }
        }
    }
}
