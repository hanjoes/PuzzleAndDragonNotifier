package us.bibos.notifier.UITests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import us.bibos.notifier.Activities.HomeActivity;
import us.bibos.notifier.R;

public class HomeActivityLayoutTest extends ActivityInstrumentationTestCase2<HomeActivity> {
    public HomeActivityLayoutTest() {
        super(HomeActivity.class);
    }

    HomeActivity homeActivity;
    Button updateButton;
    Button saveButton;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        homeActivity = getActivity();
        updateButton = (Button) homeActivity.findViewById(R.id.info_update_button);
        saveButton = (Button) homeActivity.findViewById(R.id.info_save_button);
    }

    @MediumTest
    public void testPreconditions() throws Exception {
        assertNotNull("HomeActivity is null", homeActivity);
        assertNotNull("Update button is null", updateButton);
        assertNotNull("Save button is null", saveButton);
    }

    @MediumTest
    public void testUpdateButton_layout() {
        final ViewGroup.LayoutParams buttonLayout = updateButton.getLayoutParams();
        assertEquals(LayoutParams.WRAP_CONTENT, buttonLayout.height);
        assertEquals(LayoutParams.MATCH_PARENT, buttonLayout.width);
    }

    @MediumTest
    public void testSaveButton_layout() {
        final ViewGroup.LayoutParams buttonLayout = saveButton.getLayoutParams();
        assertEquals(LayoutParams.WRAP_CONTENT, buttonLayout.height);
        assertEquals(LayoutParams.MATCH_PARENT, buttonLayout.width);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
