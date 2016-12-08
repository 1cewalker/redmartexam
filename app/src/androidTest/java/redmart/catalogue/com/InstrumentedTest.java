package redmart.catalogue.com;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import redmart.catalogue.com.webservice.RetrieveProductDetail;
import redmart.catalogue.com.webservice.RetrieveProductList;
import redmart.catalogue.com.webservice.WebServiceListener;
import redmart.catalogue.com.webservice.WebServiceTask;
import redmart.catalogue.com.webservice.model.ProductDetail;
import redmart.catalogue.com.webservice.model.ProductList;
import redmart.catalogue.com.webservice.model.WSResponseData;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    ProductList mProductList;
    ProductDetail mProductDetail;

    @Test
    public void checkRetrieveProductList() throws Exception {

        WebServiceTask task = new RetrieveProductList(0, new WebServiceListener() {
            @Override
            public void onSuccess(WSResponseData data) {
                mProductList = (ProductList)data;
            }

            @Override
            public void onError() {

            }
        });
        task.start();

        Thread.sleep(6000);

        assertEquals(20,mProductList.mProductList.size());
        assertEquals("Australian Broccoli",mProductList.mProductList.get(0).mProductName);
        assertEquals("1.95",mProductList.mProductList.get(0).mPrice);

    }

    @Test
    public void checkRetrieveProductDetail() throws Exception {

        WebServiceTask task = new RetrieveProductDetail("33973", new WebServiceListener() {
            @Override
            public void onSuccess(WSResponseData data) {
                mProductDetail = (ProductDetail)data;
            }

            @Override
            public void onError() {

            }
        });
        task.start();

        Thread.sleep(6000);

        assertEquals("/i/m/Broccoli_1439946314048.JPG",mProductDetail.mImagePath);
        assertEquals("1.95",mProductDetail.mPrice);
        assertEquals("33973",mProductDetail.mProductId);
        assertEquals("300 g",mProductDetail.mProductWeight);
        assertEquals("Australian Broccoli",mProductDetail.mProductName);

    }
}
