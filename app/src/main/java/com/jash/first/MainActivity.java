package com.jash.first;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private RecyclerView recycle;
    private TreeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tree<String> tree = new Tree<>(null);
        for (int i = 0; i < 5; i++) {
            Tree<String> t_1 = new Tree<>(String.format("level %d, %d", 1, i));
            tree.add(t_1);
            for (int j = 0; j < 5; j++) {
                Tree<String> t_2 = new Tree<>(String.format("level %d, %d", 2, j));
                t_1.add(t_2);
                for (int k = 0; k < 5; k++) {
                    Tree<String> t_3 = new Tree<>(String.format("level %d, %d", 3, k));
                    t_2.add(t_3);
                }
            }
        }
        tree.setIsOpen(true);
        recycle = ((RecyclerView) findViewById(R.id.recycle));
        recycle.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeAdapter(this, tree);
        recycle.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int position =recycle.getChildAdapterPosition(v);
        Tree<String> tree = adapter.getTree(position);
        boolean open = tree.isOpen();
        int count = tree.getCount();
        tree.setIsOpen(!open);
        if (open ^ tree.isOpen()){
            if (tree.isOpen()){
                adapter.notifyItemRangeInserted(position + 1, tree.getCount() - 1);
            } else {
                adapter.notifyItemRangeRemoved(position + 1, count - 1);
            }

        }
    }
}
