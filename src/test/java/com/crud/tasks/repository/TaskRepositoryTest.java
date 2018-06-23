package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskRepositoryTest {

    @Autowired
    TaskRepository repository;

    @Test
    public void shouldSave() {

        //Given
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");

        //When
        Task testTask = repository.save(task1);

        //Then
        Assert.assertTrue(repository.exists(testTask.getId()));

        //CleanUp
       repository.delete(testTask.getId());
    }

    @Test
    public void shouldfindById(){

        //Given
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        Task task3 = new Task(3L, "Cooking", "Cook dinner");

        repository.save(task1);
        repository.save(task2);
        repository.save(task3);

        List<Task>list = repository.findAll();

        //When
        Optional<Task> testTask = repository.findById(list.get(1).getId());

        //Then
        Assert.assertEquals("Learning",testTask.get().getTitle());

        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void shouldfindAll(){

        //Given
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        Task task3 = new Task(3L, "Cooking", "Cook dinner");

        repository.save(task1);
        repository.save(task2);
        repository.save(task3);

        //When
        List<Task>testList = repository.findAll();

        //Then
        Assert.assertEquals(3,testList.size());

        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void shouldDeleteById(){

        //Given
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        Task task3 = new Task(3L, "Cooking", "Cook dinner");

        repository.save(task1);
        repository.save(task2);
        repository.save(task3);

        //When
        List<Task>testList1 = repository.findAll();
        Long id = testList1.get(2).getId();
        repository.deleteById(id);
        List<Task>testList2 = repository.findAll();

        //Then
        Assert.assertEquals(2,testList2.size());

        //CleanUp
        repository.deleteAll();


    }

}