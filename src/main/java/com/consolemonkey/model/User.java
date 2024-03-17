package model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 8352014546353030121L;

    private String id;
    private String password;

    public User(String id) {
        this.id = id;
    }

    private User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*@Override
    public String toString() {
        return STR."User{id='\{id}\{'\''}, password='\{password}\{'\''}\{'}'}";
    }*/
}
