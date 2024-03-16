package com.consolemonkey.model;

public class Moderator extends User{
    private int privilege;

    public Moderator() {
    }

    public Moderator(int privilege) {
        this.privilege = privilege;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public WordPool createWordPool(String wordSet){
        WordPool wordPool = new WordPool(wordSet);
        return wordPool;
    }

    public void editWordPool(int id){

    }

    public void deleteWordPool(int id){

    }
}
