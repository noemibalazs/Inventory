package com.example.android.inventory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventory.data.BookContract.BookEntry;
import com.example.android.inventory.data.BookDbHelper;

public class MainActivity extends AppCompatActivity {

    private BookDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fb = (FloatingActionButton) findViewById( R.id.fab);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, EditorActivity.class);
                startActivity(intent);

            }
        });

        mDbHelper = new BookDbHelper(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDataBase();
    }


    private void displayDataBase(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String [] projection = {BookEntry.BOOK_COLUMN_ID,
                BookEntry.BOOK_COLUMN_TITLE,
                BookEntry.BOOK_COLUMN_AUTHOR,
                BookEntry.BOOK_COLUMN_PRICE,
                BookEntry.BOOK_COLUMN_QUANTITY,
                BookEntry.BOOK_COLUMN_SUPPLIER_NAME,
                BookEntry.BOOK_COLUMN_SUPPLIER_EMAIL,
                BookEntry.BOOK_COLUMN_SUPPLIER_PHONE_NUMBER};

        Cursor cursor = db.query(BookEntry.TABLE_NAME, projection, null, null,
                null, null, null );

        TextView textView = (TextView)findViewById(R.id.text_view);

        try {
            textView.setText("The book table contains " + cursor.getCount() + " books.\n\n");

            textView.append(BookEntry.BOOK_COLUMN_ID + " - " +
                            BookEntry.BOOK_COLUMN_TITLE + " - " +
                            BookEntry.BOOK_COLUMN_AUTHOR + " - " +
                            BookEntry.BOOK_COLUMN_PRICE + " - " +
                            BookEntry.BOOK_COLUMN_QUANTITY + " - " +
                            BookEntry.BOOK_COLUMN_SUPPLIER_NAME + " - " +
                            BookEntry.BOOK_COLUMN_SUPPLIER_EMAIL + " - " +
                            BookEntry.BOOK_COLUMN_SUPPLIER_PHONE_NUMBER + "\n");

            int indexBookId = cursor.getColumnIndex(BookEntry.BOOK_COLUMN_ID);
            int indexBookTitle = cursor.getColumnIndex(BookEntry.BOOK_COLUMN_TITLE);
            int indexBookAuthor = cursor.getColumnIndex(BookEntry.BOOK_COLUMN_AUTHOR);
            int indexBookPrice = cursor.getColumnIndex(BookEntry.BOOK_COLUMN_PRICE);
            int indexBookQuantity = cursor.getColumnIndex(BookEntry.BOOK_COLUMN_QUANTITY);
            int indexBookSupName = cursor.getColumnIndex(BookEntry.BOOK_COLUMN_SUPPLIER_NAME);
            int indexBookSupEmail = cursor.getColumnIndex(BookEntry.BOOK_COLUMN_SUPPLIER_EMAIL);
            int indexBookSupPhone = cursor.getColumnIndex(BookEntry.BOOK_COLUMN_SUPPLIER_PHONE_NUMBER);

            while (cursor.moveToNext()){
                int id = cursor.getInt(indexBookId);
                String productName = cursor.getString(indexBookTitle);
                String productAuthor = cursor.getString(indexBookAuthor);
                int productPrice = cursor.getInt(indexBookPrice);
                int productQuantity = cursor.getInt(indexBookQuantity);
                String productSupplier = cursor.getString(indexBookSupName);
                String productSupEmail = cursor.getString(indexBookSupEmail);
                String productSupPhone = cursor.getString(indexBookSupPhone);

                textView.append("\n" + id + " - " + productName + " - " + productAuthor + " - " + productPrice
                + " - " + productQuantity  + " - " +  productSupplier + " - " + productSupEmail + " - " + productSupPhone);
            }

        }
        finally {
            cursor.close();
        }
    }

    private void insertBook(){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BookEntry.BOOK_COLUMN_TITLE, "Inferno");
        values.put(BookEntry.BOOK_COLUMN_AUTHOR, "Dan Brown");
        values.put(BookEntry.BOOK_COLUMN_PRICE, 12);
        values.put(BookEntry.BOOK_COLUMN_QUANTITY, 9);
        values.put(BookEntry.BOOK_COLUMN_SUPPLIER_NAME, "Amazon");
        values.put(BookEntry.BOOK_COLUMN_SUPPLIER_EMAIL, "support@amazon.com");
        values.put(BookEntry.BOOK_COLUMN_SUPPLIER_PHONE_NUMBER, "121");

        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);
        if (newRowId == -1){
            Toast.makeText(this, "Error create book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "New row Id "+ newRowId, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_item){
            insertBook();
            displayDataBase();
        }
        return super.onOptionsItemSelected(item);
    }
}
