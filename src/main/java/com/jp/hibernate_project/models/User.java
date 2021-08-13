/*
 * defina the entity User
 */
package com.jp.hibernate_project.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author Jerson Perez
 */
@Table(name = "user")
@Entity
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByIsAdmin", query = "SELECT u FROM User u WHERE u.isAdmin = :isAdmin"),
    @NamedQuery(name = "User.findByByDoc", query = "SELECT u FROM User u WHERE u.document = :document"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByNick", query = "SELECT u FROM User u WHERE u.nick = :nick")})
public class User implements Serializable {

    public User() {
    }

    public User(String nick, String name, String password) {
        this.nick = nick;
        this.name = name;
        this.password = password;
        this.isAdmin = 0;
        this.passChangedAt = new Date();
        this.oldPassword = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Date getPassChangedAt() {
        return passChangedAt;
    }

    public void setPassChangedAt(Date passChangedAt) {
        this.passChangedAt = passChangedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.setOldPassword(getPassword());
        this.setPassChangedAt(new Date());
        this.password = password;
    }

    public static final String encodePass(String pass) {
        return DigestUtils.sha256Hex(pass);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nick='" + nick + "', name='" + name + "', password='" + password + "', isAdmin=" + isAdmin + ", lastPassChange=" + passChangedAt + ", oldPassword='" + oldPassword + "'}";
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.nick, other.nick);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name="nick", nullable = false, length = 64, unique = true)
    private String nick;

    @Basic(optional = false)
    @Column(name="name", nullable = false, length = 128)
    private String name;

    @Basic(optional = false)
    @Column(name="document", nullable = false, length = 20, unique = true)
    private String document;

    @Basic(optional = false)
    @Column(name = "is_admin", nullable = false)
    @ColumnDefault("0")
    private int isAdmin;

    @Basic(optional = false)
    @Column(name="password", nullable = false, length = 64)
    private String password;

    @Column(name = "old_password", length = 64)
    private String oldPassword;

    @Basic(optional = false)
    @Column(name = "pass_changed_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private Date passChangedAt;

    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private Date createdAt = new Date();

}
