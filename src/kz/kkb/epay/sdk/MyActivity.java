package kz.kkb.epay.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import kz.kkb.epay.sdk.utils.DataController;
import kz.kkb.epay.sdk.utils.EpayCallback;
import kz.kkb.epay.sdk.utils.EpayConstants;
import kz.kkb.epay.sdk.utils.EpayLanguage;

public class MyActivity extends FragmentActivity {
    private Button payButton;

    private DataController dc;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);

        dc = new DataController(this);

        payButton = (Button) findViewById(R.id.btn_pay);

        payButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        payButton.setEnabled(false);

                        dc.getOrderId(System.currentTimeMillis(),
                                new EpayCallback() {
                                    @Override
                                    public void process(Object o) {
                                        String order = (String) o;
                                        String postLink = "http://your_post_link";
                                        String template = "your_template";

                                        Intent intent = new Intent(MyActivity.this, EpayActivity.class);
                                        intent.putExtra(EpayConstants.EXTRA_TEST_MODE, true);
                                        intent.putExtra(EpayConstants.EXTRA_SIGNED_ORDER_BASE_64, order);
                                        intent.putExtra(EpayConstants.EXTRA_POST_LINK, postLink);
                                        intent.putExtra(EpayConstants.EXTRA_LANGUAGE, EpayLanguage.RUSSIAN);
                                        intent.putExtra(EpayConstants.EXTRA_TEMPLATE, template);

                                        startActivityForResult(intent, EpayConstants.EPAY_PAY_REQUEST);

                                        payButton.setEnabled(true);
                                    }
                                },
                                new EpayCallback() {
                                    @Override
                                    public void process(Object o) {
                                        payButton.setEnabled(true);
                                    }
                                }
                        );
                    }
                }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == EpayConstants.EPAY_PAY_REQUEST) {
            // Make sure the request was successful
            if (resultCode == EpayConstants.EPAY_PAY_SUCCESS) {
                Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
            } else if (resultCode == EpayConstants.EPAY_PAY_FAILURE) {
                Toast.makeText(this, "failure", Toast.LENGTH_LONG).show();
            }
        }
    }
}
