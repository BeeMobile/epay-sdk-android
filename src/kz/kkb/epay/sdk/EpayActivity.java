package kz.kkb.epay.sdk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import kz.kkb.epay.sdk.fragment.EpayFragment;
import kz.kkb.epay.sdk.utils.EpayCallback;
import kz.kkb.epay.sdk.utils.EpayConstants;
import kz.kkb.epay.sdk.utils.EpayLanguage;

/**
 * epay-sdk-android
 * Created by http://beemobile.kz on 3/29/15 6:38 PM.
 */
public class EpayActivity extends FragmentActivity {

    private EpayFragment epayFragment;

    private boolean testMode;

    private String order;
    private String postLink;
    private String template;

    private EpayLanguage language;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_epay);

        if (getIntent() != null) {
            testMode = getIntent().getBooleanExtra(EpayConstants.EXTRA_TEST_MODE, false);
            order = getIntent().getStringExtra(EpayConstants.EXTRA_SIGNED_ORDER_BASE_64);
            postLink = getIntent().getStringExtra(EpayConstants.EXTRA_POST_LINK);
            template = getIntent().getStringExtra(EpayConstants.EXTRA_TEMPLATE);

            language = (EpayLanguage) getIntent().getSerializableExtra(EpayConstants.EXTRA_LANGUAGE);
        }

        if (findViewById(R.id.content) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            epayFragment = EpayFragment.newInstance();

            epayFragment.useTestMode(testMode);

            epayFragment.setSignedOrderBase64(order);
            epayFragment.setPostLink(postLink);
            epayFragment.setLanguage(language);
            epayFragment.setTemplate(template);

            epayFragment.setSuccessCallback(
                    new EpayCallback() {
                        @Override
                        public void process(Object o) {
                            setResult(EpayConstants.EPAY_PAY_SUCCESS);

                            finish();
                        }
                    }
            );

            epayFragment.setFailureCallback(
                    new EpayCallback() {
                        @Override
                        public void process(Object o) {
                            setResult(EpayConstants.EPAY_PAY_FAILURE);

                            finish();
                        }
                    }
            );

            replaceFragment(epayFragment, false);
        }
    }

    /**
     * Replace current fragment with another fragment
     * @param f - new fragment
     * @param addToBackStack - if true current fragment will be in stack
     */
    public void replaceFragment(Fragment f, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.replace(R.id.content, f);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

}
