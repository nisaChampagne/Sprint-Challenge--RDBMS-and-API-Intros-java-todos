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
        User u1 = new User("Nisa", "password!", users);
        u1.getTodos().add(new Todo("Finish java-orders", new Date(), u1));
        u1.getTodos().add(new Todo("Drink all the coffee", new Date(), u1));
        u1.getTodos().add(new Todo("Feed the animals", new Date(), u1));
        u1.getTodos().add(new Todo("Complete the sprint challenge", new Date(), u1));
        userrepos.save(u1);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        User u2 = new User("admin", "password", admins);
        u2.getTodos().add(new Todo("Walk the dog", new Date(), u2));
        u2.getTodos().add(new Todo("provide feedback to my instructor", new Date(), u2));
        u2.getTodos().add(new Todo("Cry just a little", new Date(), u2));
        userrepos.save(u2);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("Test", "password", users);
        u3.getTodos().add(new Todo("Pass week 2 Java Sprint", new Date(), u3));
        u3.getTodos().add(new Todo("Don't get burnt out", new Date(), u3));
        userrepos.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Test2", "password", users);
        u4,getTodos().add(new Todo("Breathe", new Date(), u4)
        userrepos.save(u4);
    }
}
