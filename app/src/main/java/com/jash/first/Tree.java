package com.jash.first;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jash on 15-3-21.
 */
public class Tree<T> {
    private T t;
    private boolean isOpen = false;
    private List<Tree<T>> trees = new ArrayList<>();
    private int level = -1;
    public Tree(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen && trees != null && !trees.isEmpty();
    }

    public void add(Tree tree){
        tree.level = level + 1;
        trees.add(tree);
    }

    public Tree<T> getItem(int position){
        if (level != -1){
            position--;
        }
        if (position < 0){
            return this;
        }
        for (Tree tree:trees){
            int count = tree.getCount();
            if (position < count){
                if (count == 1){
                    return tree;
                } else {
                    return tree.getItem(position);
                }
            }
            position -= count;
        }
        return null;
    }
    public int getCount(){
        if (!isOpen){
            return 1;
        }
        int count = 1;
        if (level == -1){
            count = 0;
        }
        for (Tree tree:trees){
            count += tree.getCount();
        }
        return count;
    }

    public int getLevel() {
        return level;
    }
}
