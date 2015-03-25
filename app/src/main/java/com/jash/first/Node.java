package com.jash.first;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jash on 15-3-21.
 */
public class Node<T> {
    private T t;
    private boolean isOpen = false;
    private List<Node<T>> nodes = new ArrayList<>();
    private int level = -1;
    public Node(T t) {
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
        this.isOpen = isOpen && nodes != null && !nodes.isEmpty();
    }

    public void add(Node node){
        node.level = level + 1;
        nodes.add(node);
    }

    public Node<T> getItem(int position){
        if (level != -1){
            position--;
        }
        if (position < 0){
            return this;
        }
        for (Node node : nodes){
            int count = node.getCount();
            if (position < count){
                if (count == 1){
                    return node;
                } else {
                    return node.getItem(position);
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
        for (Node node : nodes){
            count += node.getCount();
        }
        return count;
    }

    public int getLevel() {
        return level;
    }
}
