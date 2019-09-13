package com.nisac.sprintjavatodos.controllers;

import com.nisac.sprintjavatodos.models.Todo;
import com.nisac.sprintjavatodos.models.User;
import com.nisac.sprintjavatodos.services.TodoService;
import com.nisac.sprintjavatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/todos")
public class TodoController
{

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

////localhost:5005/todos/users/mine     GET
   @GetMapping(value = "/users/mine", produces = {"application/json"})
    public ResponseEntity<?> getMyTodos()
   {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       User currentUser = userService
               .findUserByName(((org.springframework.security.core.userdetails.User)authentication
                       .getPrincipal()).getUsername());
       return new ResponseEntity<>(todoService.findAll(currentUser.getUserid()), HttpStatus.OK);
   }

////localhost:5005/todos/users/todo/{userid}
    @PostMapping(value = "/users/todo/{userid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> postNewTodo(@PathVariable long userid, @RequestBody Todo todo){
        todo.setUser(userService.findUserById(userid));
        return new ResponseEntity<>(todoService.save(todo), HttpStatus.OK);
    }

    ////localhost:5005/todos/todoid/{todoid}
    @PutMapping(value = "/todoid/{todoid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@PathVariable long todoid, @RequestBody Todo todo){
        return new ResponseEntity<>(todoService.update(todo, todoid), HttpStatus.OK);
    }

}
