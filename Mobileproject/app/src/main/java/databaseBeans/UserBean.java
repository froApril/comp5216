package databaseBeans;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class UserBean extends LitePalSupport {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
