package com.example.android.inventory.data;

import android.graphics.Bitmap;
import android.provider.BaseColumns;

import com.example.android.inventory.R;

/**
 * Created by Noemi on 1/2/2018.
 */

public final class BookContract {

    private BookContract(){}

    public static final class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "books";

        public static final String BOOK_COLUMN_ID = BaseColumns._ID;

        public static final String BOOK_COLUMN_TITLE = "Product";
        public static final String BOOK_COLUMN_AUTHOR = "Author";
        public static final String BOOK_COLUMN_PRICE = "Price";
        public static final String BOOK_COLUMN_QUANTITY = "Quantity";
        public static final String BOOK_COLUMN_SUPPLIER_NAME = "Supplier_Name";
        public static final String BOOK_COLUMN_SUPPLIER_EMAIL = "Supplier_Email";
        public static final String BOOK_COLUMN_SUPPLIER_PHONE_NUMBER = "Supplier_Phone_Number";

    }
}
