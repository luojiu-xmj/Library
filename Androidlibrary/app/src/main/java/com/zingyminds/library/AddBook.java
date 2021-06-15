package com.zingyminds.library;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.zingyminds.library.manager.bookDaoManager;
import com.zingyminds.library.R;

public class AddBook extends Fragment{
    private EditText nameEdt , authorEdt , pressEdt,priceEdt,pageEdt,scoreEdt,ISBNEdt,DateEdt
            ,contentEdt,linkEdt;

    private bookDaoManager mDBManager;
    private String namestr;
    private String authorstr;
    private String pressstr;
    private String pricestr;
    private String scorestr;
    private String datestr;
    private String ISBNstr;
    private String pagestr;
    private String contentstr;
    private String linkstr;
    private Intent intent;
    private String username;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                                ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = inflater.inflate(R.layout.activity_add, container, false);
        mDBManager = new bookDaoManager(this.getContext());
        nameEdt=root.findViewById(R.id.name_edt);
        authorEdt= root.findViewById(R.id.author_edt);
        pressEdt=root.findViewById(R.id.press_edt);
        priceEdt=root.findViewById(R.id.price_edt);
        pageEdt=root.findViewById(R.id.page_edt);
        scoreEdt=root.findViewById(R.id.score_edt);
        ISBNEdt=root.findViewById(R.id.ISBN_edt);
        DateEdt=root.findViewById(R.id.date_edt);
        contentEdt=root.findViewById(R.id.content_edt);
        linkEdt=root.findViewById(R.id.link_edt);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button)getActivity().findViewById(R.id.add_book);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namestr = nameEdt.getText().toString();
                authorstr = authorEdt.getText().toString();
                pressstr = pressEdt.getText().toString();
                pricestr = priceEdt.getText().toString();
                scorestr = scoreEdt.getText().toString();
                datestr = DateEdt.getText().toString();
                ISBNstr = ISBNEdt.getText().toString();
                pagestr = pageEdt.getText().toString();
                contentstr = contentEdt.getText().toString();
                linkstr = linkEdt.getText().toString();

                switch (view.getId()) {
                    case R.id.add_book:
                        mDBManager.insert(namestr, authorstr, pressstr, pagestr, pricestr, scorestr,
                                datestr, ISBNstr, linkstr, contentstr);
                        Toast.makeText(getActivity(), "添加书籍成功", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}