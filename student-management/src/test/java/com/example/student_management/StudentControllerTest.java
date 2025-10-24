package com.example.student_management;

import com.example.student_management.controller.StudentController;
import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentControllerTest {

    @Mock
    private StudentService service;

    @InjectMocks
    private StudentController controller;

    @Test
    void testSaveStudent() {
        Student s = new Student();
        s.setId(1);
        s.setNom("Mido");
        when(service.save(any(Student.class))).thenReturn(s);

        ResponseEntity<Student> response = controller.save(s);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Mido", response.getBody().getNom());
    }

    @Test
    void testFindAllStudents() {
        when(service.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));
        ResponseEntity<List<Student>> response = controller.findAll();
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCountStudents() {
        when(service.countStudents()).thenReturn(10L);
        ResponseEntity<Long> response = controller.count();
        assertEquals(10L, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFindByYear() {
        when(service.findNbrStudentByYear()).thenReturn(Arrays.asList());
        ResponseEntity<Collection<Object[]>> response = controller.byYear();
        assertEquals(0, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
