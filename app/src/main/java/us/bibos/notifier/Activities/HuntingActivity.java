package us.bibos.notifier.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import us.bibos.notifier.R;

import static us.bibos.notifier.Utils.Constants.PAD_PACKAGE_NAME;

public class HuntingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hunting_center);
    }

    public void huntButtonClicked(View view) {
        Intent padIntent = getPackageManager().getLaunchIntentForPackage(PAD_PACKAGE_NAME);
        startActivity(padIntent);
    }
}
