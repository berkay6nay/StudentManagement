package com.meta.facebook.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

   private StudentMapper mapper;

   @BeforeEach
   void setUp(){
       mapper = new StudentMapper();
   }

   @Test
   public void shouldMapStudentDtoToStudentClass(){
        StudentDto studentDto = new StudentDto("John" ,
                "Doe" ,
                "example@gmail.com",1);
        Student student = mapper.toStudent(studentDto);

        assertEquals(studentDto.firstName() , student.getFirstName());
        assertEquals(studentDto.lastName() , student.getLastName());
        assertEquals(studentDto.email() , student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId() , student.getSchool().getId());

   }

   @Test
   public void should_throw_null_pointer_exception_when_dto_is_null(){
         var exp = assertThrows(NullPointerException.class , () -> mapper.toStudent(null));
         assertEquals("Student Dto shall not be Null" , exp.getMessage() );
   }

   @Test
    public void shouldMapStudentToStudentDto(){

       //Given
       Student student = new Student(33,"example.com" , "Baggins" ,
               "Frodo");
       //When
       StudentResponse studentDto = mapper.toStudentResponse(student);

       //Then
       assertEquals(studentDto.firstName() , student.getFirstName());
       assertEquals(studentDto.lastName() , student.getLastName());
       assertEquals(studentDto.email() , student.getEmail());

   }

}