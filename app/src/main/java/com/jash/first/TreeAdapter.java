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
    private Node<String> node;
    private View.OnClickListener listener;
    public TreeAdapter(Context context, Node<String> node) {
        this.context = context;
        this.node = node;
        listener = (View.OnClickListener) context;
    }
    public Node<String> getTree(int position){
        return node.getItem(position);
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
        ((TextView) viewHolder.itemView).setText(node.getItem(position).getT());
    }

    @Override
    public int getItemViewType(int position) {
        return node.getItem(position).getLevel();
    }

    @Override
    public int getItemCount() {
        return node.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
