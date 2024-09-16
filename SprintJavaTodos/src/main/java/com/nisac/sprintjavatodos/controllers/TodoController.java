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


    ////localhost:5005/todos/todoid/{todoid}
    @PutMapping(value = "/todoid/{todoid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@PathVariable long todoid, @RequestBody Todo todo)
    {
        return new ResponseEntity<>(todoService.update(todo, todoid), HttpStatus.OK);
    }

}
