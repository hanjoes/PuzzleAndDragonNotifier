package us.bibos.puzzleanddragonnotifier.IntegrationTest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import us.bibos.puzzleanddragonnotifier.HomeActivity;
import us.bibos.puzzleanddragonnotifier.R;

public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {
    public HomeActivityTest() {
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

    public void testPreconditions() throws Exception {
        assertNotNull("HomeActivity is null", homeActivity);
        assertNotNull("Update button is null", updateButton);
        assertNotNull("Save button is null", saveButton);
    }

    public void testUpdateButton_layout() {
        final ViewGroup.LayoutParams buttonLayout = updateButton.getLayoutParams();
        assertEquals(LayoutParams.MATCH_PARENT, buttonLayout.height);
        assertEquals(LayoutParams.WRAP_CONTENT, buttonLayout.width);
    }
}
