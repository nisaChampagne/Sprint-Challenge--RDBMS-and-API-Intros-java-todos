package com.nisac.sprintjavatodos.services;

import com.nisac.sprintjavatodos.models.Todo;

import java.util.ArrayList;

public interface TodoService
{
    ArrayList<Todo> findAll(long id);

    Todo findTodoById(long id);

    void delete(long id);

    Todo save(Todo todo);

    Todo update(Todo todo, long id);

}
