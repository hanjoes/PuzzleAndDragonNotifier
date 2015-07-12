package us.bibos.puzzleanddragonnotifier.UITests;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import us.bibos.puzzleanddragonnotifier.HomeActivity;
import us.bibos.puzzleanddragonnotifier.MockContext;
import us.bibos.puzzleanddragonnotifier.R;

public class HomeActivityInitializationTest extends ActivityUnitTestCase<HomeActivity> {
    private static final long WAIT_TIME_IN_SECONDS = 2;
    private RenamingDelegatingContext mockContext;
    private static final String MOCK_PREFIX = "mock.";
    private CountDownLatch signal;

    public HomeActivityInitializationTest() {
        super(HomeActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mockContext = new MockContext(getInstrumentation().getTargetContext(), MOCK_PREFIX);
        signal = new CountDownLatch(1);
    }

    @MediumTest
    public void testHomeActivityInitialization() throws InterruptedException {
        setActivityContext(mockContext);

        startActivity(new Intent(), null, null);

        final HomeActivity activity = getActivity();

        Button saveButton = (Button) activity.findViewById(R.id.info_save_button);
        Button updateButton = (Button) activity.findViewById(R.id.info_update_button);

        // TODO: not a good practice here
        signal.await(WAIT_TIME_IN_SECONDS, TimeUnit.SECONDS);

        assertEquals(true, saveButton.isEnabled());
        assertEquals(false, updateButton.isEnabled());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
