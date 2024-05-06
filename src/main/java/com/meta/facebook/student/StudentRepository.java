package com.meta.facebook.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student , Integer> {

    List<Student> findAllByFirstName(String p);

}
