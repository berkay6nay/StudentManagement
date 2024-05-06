package com.meta.facebook.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    private final SchoolService schoolService;

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto dto
    )
    {
        return schoolService.saveSchool(dto);
    }

    @GetMapping("/schools")
    public List<SchoolDto> getSchools(){
        return schoolService.getSchools();
    }

    @GetMapping("/schools/{school-id}")
    public SchoolDto getSchool(@PathVariable("school-id") Integer id){
        return schoolService.getSchoolById(id);
    }
}
