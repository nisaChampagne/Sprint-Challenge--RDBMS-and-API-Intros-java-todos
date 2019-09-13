package com.nisac.sprintjavatodos.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="userroles")
public class UserRoles extends Auditable implements Serializable
{

//    USERROLES
//
//    roleid foreign key to role
//    userid foreign key to user


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userRoles")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne
    @JoinColumn(name ="roleid")
    @JsonIgnoreProperties("userRoles")
    private Role role;

    public UserRoles()
    {

    }


    public UserRoles(User user, Role role)
    {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
