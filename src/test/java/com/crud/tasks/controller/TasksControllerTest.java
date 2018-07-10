package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TasksController.class)
public class TasksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TaskMapper taskMapper;

    @MockBean
    DbService service;

    @Test
    public void shouldGetTasks()throws Exception{

        //Given
        List<TaskDto> listDto = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(1L, "Shopping", "Buy new shoes");
        TaskDto taskDto2 = new TaskDto(2L, "Learning", "Read about Spring");
        TaskDto taskDto3 = new TaskDto(3L, "Cooking", "Cook dinner");
        listDto.add(taskDto1);
        listDto.add(taskDto2);
        listDto.add(taskDto3);

        List<Task> list = new ArrayList<>();
        Task task1 = new Task(1L, "Shopping", "Buy new shoes");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        Task task3 = new Task(3L, "Cooking", "Cook dinner");
        list.add(task1);
        list.add(task2);
        list.add(task3);

        when(service.getAllTasks()).thenReturn(list);
        when(taskMapper.mapToTaskDtoList(list)).thenReturn(listDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].title",is("Shopping")))
                .andExpect(jsonPath("$[1].title",is("Learning")))
                .andExpect(jsonPath("$[2].title",is("Cooking")));

    }

    @Test
    public void shouldGetTask()throws Exception{

        //Given
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        TaskDto taskDto2 = new TaskDto(2L, "Learning", "Read about Spring");

        when(service.getTask(2L)).thenReturn(java.util.Optional.ofNullable(task2));
        when(taskMapper.mapToTaskDto(task2)).thenReturn(taskDto2);

        //When & Then
        mockMvc.perform(get("/v1/tasks/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(2)))
               .andExpect(jsonPath("$.title",is("Learning")))
               .andExpect(jsonPath("$.content",is("Read about Spring")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {

        //When & Then

        mockMvc.perform(delete("/v1/tasks/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(service,Mockito.times(1)).deleteTask(2L);
    }

    @Test
    public void shouldCreateTask() throws Exception{

    //Given
        TaskDto createdTask = new TaskDto(4L,"Learning","Read about tests");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdTask);

        //When & Then
        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

       Mockito.verify(service,Mockito.times(1)).saveTask(any(Task.class));

    }

    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        TaskDto taskDto2 = new TaskDto(2L, "Learning", "Read about Spring");
        Task task2 = new Task(2L, "Learning", "Read about Spring");
        TaskDto updatedTaskDto = new TaskDto(2L,"Learning","Read about tests");
        Task updateTask = new Task(2L,"Learning","Read about tests");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(updateTask);
        when(service.saveTask(updateTask)).thenReturn(updateTask);
        when(taskMapper.mapToTaskDto(updateTask)).thenReturn(updatedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTaskDto);

        //When & Then
       mockMvc.perform(put("/v1/tasks")
               .contentType(MediaType.APPLICATION_JSON)
               .characterEncoding("UTF-8")
               .content(jsonContent))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id",is(2)))
               .andExpect(jsonPath("$.title",is("Learning")))
                .andExpect(jsonPath("$.content", is("Read about tests")));
    }



}