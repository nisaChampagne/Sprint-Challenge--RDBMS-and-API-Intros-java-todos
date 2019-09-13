package com.nisac.sprintjavatodos.services;

import com.nisac.sprintjavatodos.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserServiceImpl implements UserDetailsService, UserService
{
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserByName(String name) {
        return null;
    }

    @Override
    public User findUserById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user, long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
