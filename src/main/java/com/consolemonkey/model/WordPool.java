package com.consolemonkey.model;

import java.io.Serializable;

public class WordPool implements Serializable {

    private static final long serialVersionUID = -5484357873815606425L;
    private int level;
    private String wordSet;
    private User createdby;

    public WordPool(String wordSet){
        this.wordSet= wordSet;
    }

   /* @Override
    public String toString() {
        return STR."WordPool{level=\{level}, wordSet='\{wordSet}\{'\''}, createdby=\{createdby}\{'}'}";
    }*/
}
