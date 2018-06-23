package com.crud.tasks.com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperSuiteTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void shouldMapToTask(){

        //Given
        TaskDto taskDto = new TaskDto(1L, "Shopping", "Buy new shoes");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals("Shopping", task.getTitle());
        Assert.assertEquals("Buy new shoes", task.getContent());
    }

    @Test
    public void shouldMapToTaskDto(){

        //Given
        Task task = new Task(1L, "Shopping", "Buy new shoes");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals("Shopping", taskDto.getTitle());
        Assert.assertEquals("Buy new shoes", taskDto.getContent());
    }

    @Test
    public void shouldMapToTaskDtoList(){

        //Given
        List<Task> list = new ArrayList<>();
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        Task task3 = new Task(3L, "Cooking", "Cook dinner");
        list.add(task1);
        list.add(task2);
        list.add(task3);

        //When
        List<TaskDto> testList = taskMapper.mapToTaskDtoList(list);

        //Then
        Assert.assertEquals(3, testList.size());
        Assert.assertEquals("Shopping", testList.get(0).getTitle());
    }

}
