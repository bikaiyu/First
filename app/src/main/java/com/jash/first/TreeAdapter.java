package com.jash.first;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jash on 15-3-21.
 */
public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.ViewHolder> {
    private Context context;
    private Tree<String> tree;
    private View.OnClickListener listener;
    public TreeAdapter(Context context, Tree<String> tree) {
        this.context = context;
        this.tree = tree;
        listener = (View.OnClickListener) context;
    }
    public Tree<String> getTree(int position){
        return tree.getItem(position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        TextView textView = new TextView(context);
        textView.setTextSize(30);
        switch (viewType){
            case 0:
                textView.setPadding(0, 0, 0, 0);
                break;
            case 1:
                textView.setPadding(50, 0, 0, 0);
                break;
            case 2:
                textView.setPadding(100, 0, 0, 0);
                break;
        }
        textView.setOnClickListener(listener);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.itemView).setText(tree.getItem(position).getT());
    }

    @Override
    public int getItemViewType(int position) {
        return tree.getItem(position).getLevel();
    }

    @Override
    public int getItemCount() {
        return tree.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
