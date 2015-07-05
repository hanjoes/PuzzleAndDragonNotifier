package us.bibos.puzzleanddragonnotifier.DBContract;

import android.provider.BaseColumns;

public final class UserInfoContract {
    public UserInfoContract() {}

    public static abstract class UserInfo implements BaseColumns {
        public static final String TABLE_NAME = "user_info";
        public static final String COLUMN_NAME_PND_ID = "user_pnd_id";
        public static final String COLUMN_NAME_NAME = "user_name";
        public static final String COLUMN_NAME_DATE = "update_date";
    }
}
