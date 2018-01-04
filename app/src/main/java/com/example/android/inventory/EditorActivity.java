package com.example.android.inventory;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventory.data.BookContract.BookEntry;
import com.example.android.inventory.data.BookDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mBookName;
    private EditText mBookAuthor;
    private EditText mBookPrice;
    private EditText mBookQuantity;
    private EditText mSupName;
    private EditText mSupEmail;
    private EditText mSupPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mBookName =  findViewById(R.id.book_name);
        mBookAuthor =  findViewById(R.id.book_author);
        mBookPrice =  findViewById(R.id.book_price);
        mBookQuantity = findViewById(R.id.book_quantity);
        mSupName =  findViewById(R.id.sup_name);
        mSupEmail =  findViewById(R.id.sup_email);
        mSupPhone =  findViewById(R.id.sup_phone);
    }


    private void insertBook(){

        BookDbHelper mDbHelper = new BookDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String nameBook = mBookName.getText().toString().trim();
        String authorBook = mBookAuthor.getText().toString().trim();
        String priceBook = mBookPrice.getText().toString().trim();
        String quantityBook = mBookQuantity.getText().toString().trim();
        String nameSup = mSupName.getText().toString().trim();
        String emailSup = mSupEmail.getText().toString().trim();
        String phoneSup = mSupPhone.getText().toString().trim();
        int price = Integer.parseInt(priceBook);
        int quantity = Integer.parseInt(quantityBook);

        values.put(BookEntry.BOOK_COLUMN_TITLE, nameBook);
        values.put(BookEntry.BOOK_COLUMN_AUTHOR, authorBook);
        values.put(BookEntry.BOOK_COLUMN_PRICE, price);
        values.put(BookEntry.BOOK_COLUMN_QUANTITY, quantity);
        values.put(BookEntry.BOOK_COLUMN_SUPPLIER_NAME, nameSup);
        values.put(BookEntry.BOOK_COLUMN_SUPPLIER_EMAIL, emailSup);
        values.put(BookEntry.BOOK_COLUMN_SUPPLIER_PHONE_NUMBER, phoneSup);

        long newRowId =  db.insert(BookEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "New row id" + newRowId, Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.editor_save:
                insertBook();
                finish();
                return true;
            case R.id.editor_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
