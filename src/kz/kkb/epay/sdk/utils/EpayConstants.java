package kz.kkb.epay.sdk.utils;

/**
 * epay-sdk-android
 * Created by http://beemobile.kz on 3/29/15 4:47 PM.
 */
public class EpayConstants {

    public static final int TIMEOUT_CONNECTION = 90000;
    public static final int EPAY_PAY_REQUEST = 1;
    public static final int EPAY_PAY_SUCCESS = 1;
    public static final int EPAY_PAY_FAILURE = 2;

    public static final String LOG_TAG = "EPAY_SDK";

    public static final String SERVER_URL = "https://3dsecure.kkb.kz/jsp/client/signorderb64.jsp";

    public static final String EPAY_POST_URL = "https://epay.kkb.kz/jsp/process/logon.jsp";
    public static final String EPAY_TEST_POST_URL = "https://3dsecure.kkb.kz/jsp/process/logon.jsp";
    public static final String EPAY_BACK_LINK = "http://localhost/success";
    public static final String EPAY_FAILURE_BACK_LINK = "http://localhost/failure";

    public static final String EXTRA_TEST_MODE = "test_mode";
    public static final String EXTRA_SIGNED_ORDER_BASE_64 = "order";
    public static final String EXTRA_POST_LINK = "post_link";
    public static final String EXTRA_LANGUAGE = "language";
    public static final String EXTRA_TEMPLATE = "template";
}
