package com.meta.facebook.student;

import com.meta.facebook.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto){
        var student = new Student();
        if(dto == null){
            throw new NullPointerException("Student Dto shall not be Null");
        }
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResponse toStudentResponse(Student student){
        return new StudentResponse(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

}
