package com.meta.facebook.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    //which service we want to test
    @InjectMocks
    private StudentService studentService;

    //Declare the dependencies

    @Mock
    StudentMapper studentMapper;
    @Mock
    StudentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_student(){
        //Given
        StudentDto dto = new StudentDto("Gandalf","Grey",
                "example.com",1);
        Student student = new Student(33,"example.com",
                "Grey" , "Gandalf");
        Student savedStudent = new Student(33,"example.com",
                "Grey" , "Gandalf");

        savedStudent.setId(1);

        //Mock the calls
        Mockito.when(studentMapper.toStudent(dto)).thenReturn(student);
        Mockito.when(repository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentResponse(savedStudent)).thenReturn(new StudentResponse("Gandalf","Grey","example.com"));


        //When
        StudentResponse response = studentService.saveStudent(dto);

        //Then
        assertEquals(dto.firstName() , response.firstName());
        assertEquals(dto.lastName() , response.lastName());
        assertEquals(dto.email() , response.email());

    }
}