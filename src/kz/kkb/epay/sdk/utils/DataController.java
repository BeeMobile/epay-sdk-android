package kz.kkb.epay.sdk.utils;

import android.content.Context;
import android.util.Log;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * epay-sdk-android
 * Created by http://beemobile.kz on 3/29/15 5:26 PM.
 */
public class DataController {

    public AQuery aq;

    public DataController(Context context) {
        aq = new AQuery(context);
    }

    private void requestServer(Map<String, Object> params,
                               final EpayCallback success, final EpayCallback failure) {

        AjaxCallback<String> callback = new AjaxCallback<String>() {
            @Override
            public void callback(String url, String data, AjaxStatus status) {
                int responseCode = status.getCode();

                if (responseCode == 200) {
                    if (data != null) {
                        Log.e(EpayConstants.LOG_TAG, "data = " + data);

                        success.process(data);
                    }
                } else {
                    failure.process(null);
                }
            }
        };

        callback.url(EpayConstants.SERVER_URL);

        if (params == null) {
            params = new HashMap<String, Object>();
        }

        callback.params(params);
        callback.type(String.class);
        callback.timeout(EpayConstants.TIMEOUT_CONNECTION);
        callback.encoding("UTF-8");
        callback.method(com.androidquery.util.Constants.METHOD_POST).header("User-Agent", "android");

        aq.ajax(callback);
    }

    public void getOrderId(long id, final EpayCallback success, final EpayCallback failure) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("order_id", id);

        requestServer(params,
                new EpayCallback() {
                    @Override
                    public void process(Object o) {
                        success.process(o);
                    }
                }, new EpayCallback() {
                    @Override
                    public void process(Object o) {
                        failure.process(o);
                    }
                }
        );
    }
}
