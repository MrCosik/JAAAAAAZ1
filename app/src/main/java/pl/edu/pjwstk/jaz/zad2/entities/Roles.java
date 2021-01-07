package pl.edu.pjwstk.jaz.zad2.entities;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_roles;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "title")
    private String role;

    public Roles() {}
//    static {
//        new Roles("admin");
//        new Roles("user");
//
//    }

    public Roles(String role) {
        this.role = role;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public UserEntity getUserEntity() {
//        return userEntity;
//    }
//
//    public void setUserEntity(UserEntity userEntity) {
//        this.userEntity = userEntity;
//    }
}
