package com.zingyminds.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.zingyminds.library.manager.bookDaoManager;
import com.zingyminds.library.model.bookmodel;
import com.zingyminds.library.R;

import java.util.List;


public class queryactivity extends Fragment {
    private bookDaoManager mDBManager;
    private bookadpter bookadpter;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.activity_query, container, false);
        mDBManager = new bookDaoManager(this.getContext());
        List<bookmodel> dataSource = mDBManager.querybooks();
        listView = (ListView) root.findViewById(R.id.list_view);
        bookadpter = new bookadpter(queryactivity.this.getContext(), dataSource);
        listView.setAdapter(bookadpter);
        return root;
    }
}
