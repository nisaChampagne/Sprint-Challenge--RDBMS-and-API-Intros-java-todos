package com.nisac.sprintjavatodos;


import com.nisac.sprintjavatodos.models.Role;
import com.nisac.sprintjavatodos.models.Todo;
import com.nisac.sprintjavatodos.models.User;
import com.nisac.sprintjavatodos.models.UserRoles;
import com.nisac.sprintjavatodos.repos.RoleRepository;
import com.nisac.sprintjavatodos.repos.ToDoRepository;
import com.nisac.sprintjavatodos.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Transactional
@Component
public class SeedData implements CommandLineRunner {
    RoleRepository rolerepos;
    UserRepository userrepos;
    ToDoRepository todorepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, ToDoRepository todorepos) {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.todorepos = todorepos;
    }

    @Override
    public void run(String[] args) throws Exception {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        rolerepos.save(r1);
        rolerepos.save(r2);

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u1 = new User("nisa", "password", users);
        u1.getTodos().add(new Todo("Finish java-orders-swagger", new Date(), u1));
        u1.getTodos().add(new Todo("Feed the turtles", new Date(), u1));
        u1.getTodos().add(new Todo("Complete the sprint challenge", new Date(), u1));


        userrepos.save(u1);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        User u2 = new User("admin", "password", admins);
        u2.getTodos().add(new Todo("Walk the dogs", new Date(), u2));
        u2.getTodos().add(new Todo("provide feedback to my instructor", new Date(), u2));
        userrepos.save(u2);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("Test", "password", users);
        userrepos.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Test2", "password", users);
        userrepos.save(u4);
    }
}