package com.meta.facebook.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }



    @GetMapping("/hello")
    public String sayHello(){
        return "Hello from the first controller";
    }

    @PostMapping("/students")
        public StudentResponse saveStudent(
                @Valid @RequestBody StudentDto dto
                ){
        return this.studentService.saveStudent(dto);

        }

        @GetMapping("/students")
        public List<StudentResponse> getAllStudents(){
            return studentService.getAllStudents();
        }

    @GetMapping("/students/{student-id}")
    public StudentResponse getStudent(@PathVariable("student-id") Integer id){
        return studentService.getStudent(id);
    }

    @GetMapping("/students/search/{first-name}")
    public List<StudentResponse> findStudentsByName(@PathVariable("first-name") String name){
        return studentService.getStudentsByName(name);

    }
    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("student-id") Integer id){
        studentService.deleteStudent(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp){
        var errors = new HashMap<String , String>();
        exp.getBindingResult().getAllErrors()
                .forEach(
                        error -> {
                            var fieldName = ((FieldError) error).getField();
                            var errorMessage = error.getDefaultMessage();
                            errors.put(fieldName,errorMessage);
                        }
                );
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }


    }




