package us.bibos.notifier.FunctionalTests;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.EditText;

import us.bibos.notifier.Activities.HomeActivity;
import us.bibos.notifier.MockContext;
import us.bibos.notifier.R;

public class UserRegistrationTest extends ActivityUnitTestCase<HomeActivity> {

    private static final String MOCK_CONTEXT = "mock.";
    public static final String TEST_PND_ID = "9810934012";
    private static final String TEST_PND_NAME = "hanjoes";
    private MockContext mockContext;

    public UserRegistrationTest(Class<HomeActivity> activityClass) {
        super(activityClass);
        mockContext = new MockContext(getInstrumentation().getTargetContext(),
                MOCK_CONTEXT);
    }

    @MediumTest
    public void testUserRegistration() {
        setActivityContext(mockContext);

        startActivity(new Intent(), null, null);

        final HomeActivity activity = getActivity();

        EditText idEditor = (EditText) activity.findViewById(R.id.id_editor);
        idEditor.setText(TEST_PND_ID);
        EditText nameEditor = (EditText) activity.findViewById(R.id.name_editor);
        nameEditor.setText(TEST_PND_NAME);
        Button saveButton = (Button) activity.findViewById(R.id.info_save_button);
        saveButton.performClick();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
