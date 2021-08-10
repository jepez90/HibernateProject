/*
 * defina the entity User
 */
package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Jerson Perez
 */
@Entity
@Table(name = "user ")
public class User implements Serializable {

    public User() {
    }

    public User(String nick, String name, String password) {
        this.nick = nick;
        this.name = name;
        this.password = password;
        this.isAdmin = 0;
        this.lastPassChange = LocalDateTime.now();
        this.oldPassword = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.setOldPassword(getPassword());
        this.setLastPassChange(LocalDateTime.now());
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public LocalDateTime getLastPassChange() {
        return lastPassChange;
    }

    public void setLastPassChange(LocalDateTime lastPassChange) {
        this.lastPassChange = lastPassChange;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nick='" + nick + "', name='" + name + "', password='" + password + "', isAdmin=" + isAdmin + ", lastPassChange=" + lastPassChange + ", oldPassword='" + oldPassword + "'}";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.nick);
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nick, other.nick);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nick")
    private String nick;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private int isAdmin;

    @Column(name = "last_pw_change")
    private LocalDateTime lastPassChange;

    @Column(name = "old_password")
    private String oldPassword;

}
