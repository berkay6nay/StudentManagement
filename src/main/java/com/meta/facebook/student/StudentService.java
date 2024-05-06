package com.meta.facebook.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentMapper studentMapper;
    private final StudentRepository repository;

    public StudentService(StudentMapper studentMapper, StudentRepository repository) {
        this.studentMapper = studentMapper;
        this.repository = repository;
    }
public StudentResponse saveStudent(StudentDto dto ){
    var student = studentMapper.toStudent(dto);
    var savedStudent = repository.save(student);
    return studentMapper.toStudentResponse(savedStudent);
}

public List<StudentResponse> getAllStudents(){
    return repository.findAll()
            .stream()
            .map(studentMapper::toStudentResponse)
            .collect(Collectors.toList());
}

public StudentResponse getStudent(Integer id){
    Optional<Student> student = repository.findById(id);
    if(student.isPresent()){
        Student student_to_return = student.get();
         return studentMapper.toStudentResponse(student_to_return);

    }
    else{
        return null;
    }
    }

public List<StudentResponse> getStudentsByName(String name){
    return repository.findAllByFirstName(name)
            .stream()
            .map(studentMapper::toStudentResponse)
            .collect(Collectors.toList());
}

public void deleteStudent(Integer id){
        repository.deleteById(id);
}
}
