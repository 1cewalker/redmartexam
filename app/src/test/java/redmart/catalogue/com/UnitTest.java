package redmart.catalogue.com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import redmart.catalogue.com.webservice.RetrieveProductList;
import redmart.catalogue.com.webservice.WebServiceListener;
import redmart.catalogue.com.webservice.WebServiceTask;
import redmart.catalogue.com.webservice.model.ProductList;
import redmart.catalogue.com.webservice.model.WSResponseData;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class UnitTest{

    ProductList mProductList;

    @Test
    public void checkRetrieveProductList() throws Exception {



        WebServiceTask task = new RetrieveProductList(20, new WebServiceListener() {
            @Override
            public void onSuccess(WSResponseData data) {
                mProductList = (ProductList)data;
            }

            @Override
            public void onError() {

            }
        });
        task.start();

        assertEquals(20,mProductList.mProductList.size());

    }
}