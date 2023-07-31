package org.zero.jdbcex.service;


import lombok.extern.log4j.Log4j2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zero.jdbcex.dto.TodoDTO;

import java.time.LocalDate;

class TodoServiceTests {
    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void testRegister()throws Exception {

        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title22")
                .dueDate(LocalDate.now())
                .build();



        todoService.register(todoDTO);
    }

    @Test
    void listAll() {
    }

    @Test
    void get() {
    }

    @Test
    void remove() {
    }

    @Test
    void modify() {
    }
}