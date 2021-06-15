package com.zingyminds.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.zingyminds.library.manager.bookDaoManager;
import com.zingyminds.library.model.bookmodel;
import com.zingyminds.library.R;

import java.util.List;

public class MyAdd extends Fragment {
    private bookDaoManager mDBManager;
    private bookAdapterAlter bookadpter;
    private ListView listView;
    private String username;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = inflater.inflate(R.layout.my_add, container, false);
        mDBManager = new bookDaoManager(this.getContext());
        List<bookmodel> dataSource = mDBManager.querybooks();
        listView = root.findViewById(R.id.my_add_list_view);
        bookadpter = new bookAdapterAlter(MyAdd.this.getContext(), dataSource);
        listView.setAdapter(bookadpter);
        return root;
    }

}
