package com.jash.first;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private RecyclerView recycle;
    private TreeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Node<String> node = new Node<>(null);
        for (int i = 0; i < 5; i++) {
            Node<String> t_1 = new Node<>(String.format("level %d, %d", 1, i));
            node.add(t_1);
            for (int j = 0; j < 5; j++) {
                Node<String> t_2 = new Node<>(String.format("level %d, %d", 2, j));
                t_1.add(t_2);
                for (int k = 0; k < 5; k++) {
                    Node<String> t_3 = new Node<>(String.format("level %d, %d", 3, k));
                    t_2.add(t_3);
                }
            }
        }
        node.setIsOpen(true);
        recycle = ((RecyclerView) findViewById(R.id.recycle));
        recycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeAdapter(this, node);
        recycle.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int position =recycle.getChildAdapterPosition(v);
        Node<String> node = adapter.getTree(position);
        boolean open = node.isOpen();
        int count = node.getCount();
        node.setIsOpen(!open);
        if (open ^ node.isOpen()){
            if (node.isOpen()){
                adapter.notifyItemRangeInserted(position + 1, node.getCount() - 1);
            } else {
                adapter.notifyItemRangeRemoved(position + 1, count - 1);
            }

        }
    }
}
