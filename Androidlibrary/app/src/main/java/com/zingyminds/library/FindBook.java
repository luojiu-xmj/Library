package com.zingyminds.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.zingyminds.library.manager.bookDaoManager;
import com.zingyminds.library.model.bookmodel;
import com.zingyminds.library.R;

import java.util.ArrayList;
import java.util.List;

public class FindBook extends Fragment{
    private Button findButton;
    private EditText searchView;
    private ListView searchlist;
    String searchname;
    private bookadpter bookadpter;
    private ListView listView;
    private bookDaoManager mDBManager;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = inflater.inflate(R.layout.activity_find, container, false);
        findButton=(Button)root.findViewById(R.id.find_book);
        searchView=(EditText) root.findViewById(R.id.search_name);
        searchlist=(ListView)root.findViewById(R.id.find_view);
        return root;
        }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b1=(Button)getActivity().findViewById(R.id.find_book);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchname = searchView.getText().toString();
                switch (view.getId()) {
                    case R.id.find_book:
                        if (searchname.equals("")) {
                            Toast.makeText(getActivity(), "查询失败，名字不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            listView = (ListView) root.findViewById(R.id.find_view);
                            mDBManager = new bookDaoManager(listView.getContext());
                            List<bookmodel> model = mDBManager.queryByName(searchname);
                            if (model.size() != 0) {
                                bookadpter = new bookadpter(listView.getContext(), model);
                                listView.setAdapter(bookadpter);
                                Toast.makeText(getActivity(), "查询成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "没有该图书", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

