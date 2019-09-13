package com.nisac.sprintjavatodos.repos;

import com.nisac.sprintjavatodos.models.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ToDoRepository extends CrudRepository<Todo, Long>
{

    @Query(value = "SELECT * FROM todo WHERE todos = :userid", nativeQuery = true)
    List<Todo> getAllByID(long userid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM todo WHERE userid = :userid", nativeQuery = true)
    void deleteAllByUserId(long userid);

}
