package com.meta.facebook.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository repository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository repository, SchoolMapper schoolMapper) {
        this.repository = repository;
        this.schoolMapper = schoolMapper;
    }
    public SchoolDto saveSchool(SchoolDto dto){
        School school = schoolMapper.toSchool(dto);
        repository.save(school);
        return dto;
    }

    public List<SchoolDto> getSchools(){
        return repository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }

    public SchoolDto getSchoolById(Integer id){
        Optional<School> school = repository.findById(id);
        if(school.isPresent()){
            School active_school = school.get();
            return schoolMapper.toSchoolDto(active_school);
        }
        else{
            return null;
        }
    }
}
