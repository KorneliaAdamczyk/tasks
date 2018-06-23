package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import com.google.common.base.Verify;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService service;

    @Mock
    TaskRepository repository;

    @Test
    public void shouldGetAllTasks() {

        //Given
        List<Task> list = new ArrayList<>();
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        Task task3 = new Task(3L, "Cooking", "Cook dinner");
        list.add(task1);
        list.add(task2);
        list.add(task3);

       when(repository.findAll()).thenReturn(list);

        //When
        List<Task> testList = service.getAllTasks();

        //Then
        Assert.assertEquals(3, testList.size());
    }
    @Test
    public void shouldGetTask() {

        //Given
        List<Task> list = new ArrayList<>();
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        Task task3 = new Task(3L, "Cooking", "Cook dinner");
        list.add(task1);
        list.add(task2);
        list.add(task3);

        when(repository.findById(2L)).thenReturn(Optional.ofNullable(task2));

        //When
        Optional<Task> testTask = service.getTask(2L);

        //Then
        Assert.assertEquals("Learning", testTask.get().getTitle());

    }

    @Test
    public void shouldSaveTask(){

        //Given
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");

        when(repository.save(task1)).thenReturn(task1);

        //When
        Task testTask = service.saveTask(task1);

        //Then
        Assert.assertEquals("Shopping", testTask.getTitle());
    }

    @Test
    public void shouldDeleteTask(){

        //Given
        List<Task> list = new ArrayList<>();
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        Task task3 = new Task(3L, "Cooking", "Cook dinner");
        list.add(task1);
        list.add(task2);
        list.add(task3);

        //When
       service.deleteTask(list.get(0).getId());

        //Then
        Mockito.verify(repository,Mockito.times(1)).deleteById(list.get(0).getId());
    }
}
