package shoponline.models;


import javax.persistence.*;

@Entity
@Table(name = "uzer")
public class Uzer {

    public enum Role{
        USER,
        ADMIN
    }

    long id;
    String username;
    String password;
    Role role;

    public Uzer(){

    }

    public Uzer(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role= role;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
