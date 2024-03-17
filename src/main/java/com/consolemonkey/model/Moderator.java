package model;

import java.io.Serializable;

public class Moderator extends User implements Serializable {

    private static final long serialVersionUID = 7756504260140411246L;
    private int privilege;

    public Moderator(String id, int privilege) {
        super(id);
        this.privilege = privilege;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public WordPool createWordPool(String wordSet) {
        WordPool wordPool = new WordPool(wordSet);
        return wordPool;
    }

    public void editWordPool(int id) {

    }

    public void deleteWordPool(int id) {

    }

    @Override
    public String toString() {
        return STR."Moderator{privilege=\{privilege}\{'}'}";
    }
}
