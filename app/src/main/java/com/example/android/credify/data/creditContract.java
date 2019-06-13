package com.example.android.credify.data;

import android.provider.BaseColumns;

public final class creditContract {
    private creditContract() {
    }

    ;

    public static final class CreditEntry implements BaseColumns {

        /**
         * Name of database table for users
         */
        public final static String TABLE_NAME = "user";


        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_USER_NAME = "name";
        public final static String COLUMN_USER_EMAIL = "email";
        public final static String COLUMN_USER_CREDIT = "amount";

        public final static String TABLE2_NAME = "transactions";
        public final static String COLUMN_SENDER = "sender";
        public final static String COLUMN_RECEIVER = "receiver";
        public final static String COLUMN_AMOUNT = "amount";

    }

}
