package com.zingyminds.library;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zingyminds.library.manager.bookDaoManager;
import com.zingyminds.library.model.bookmodel;
import com.zingyminds.library.R;

public class look_bookDetail extends AppCompatActivity {
    private bookDaoManager mDbManager;
    private TextView mEtInfo;
    private bookmodel mbooksModel;
    private TextView nameEdt;
    private TextView authorEdt;
    private TextView pressEdt;
    private TextView priceEdt;
    private TextView pageEdt;
    private TextView scoreEdt;
    private TextView isbnEdt;
    private TextView dateEdt;
    private TextView linkEdt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        mDbManager = new bookDaoManager(this);
        mbooksModel = getIntent().getParcelableExtra("bookmodel");
        mEtInfo = findViewById(R.id.look_content_edt);
        nameEdt = findViewById(R.id.look_name_edt);
        authorEdt = findViewById(R.id.look_author_edt);
        pressEdt = findViewById(R.id.look_press_edt);
        priceEdt = findViewById(R.id.look_price_edt);
        pageEdt = findViewById(R.id.look_page_edt);
        scoreEdt = findViewById(R.id.look_score_edt);
        isbnEdt = findViewById(R.id.look_ISBN_edt);
        dateEdt = findViewById(R.id.look_date_edt);
        linkEdt = findViewById(R.id.look_link_edt);
        mEtInfo.setText(mbooksModel.getContent_description());
        nameEdt.setText(mbooksModel.getName());
        authorEdt.setText(mbooksModel.getAuthor());
        pressEdt.setText(mbooksModel.getPress());
        priceEdt.setText(mbooksModel.getPrice());
        pageEdt.setText(mbooksModel.getPage());
        scoreEdt.setText(mbooksModel.getScore());
        isbnEdt.setText(mbooksModel.getISBN());
        dateEdt.setText(mbooksModel.getDate());
        linkEdt.setText(mbooksModel.getLink());

    }
}
