package redmart.catalogue.com.webservice.model;

import org.json.JSONObject;

/**
 * Created by Nathaniel James Lim on 8/12/2016.
 */

public interface WSResponseData {

    public void parse(JSONObject webServiceResponse);

}
