package redmart.catalogue.com.webservice;

import redmart.catalogue.com.webservice.model.WSResponseData;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public interface WebServiceListener {

    public void onSuccess(WSResponseData data);
    public void onError();

}
