package com.scaler.DBInheritanceDemo.mappedSuperClass.service;

import com.scaler.DBInheritanceDemo.mappedSuperClass.Instructor;
import com.scaler.DBInheritanceDemo.mappedSuperClass.Student;
import com.scaler.DBInheritanceDemo.mappedSuperClass.repository.MSCInstructorRepository;
import com.scaler.DBInheritanceDemo.mappedSuperClass.repository.MSCScalerUserRepository;
import com.scaler.DBInheritanceDemo.mappedSuperClass.repository.MSCStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitServiceForMSC {
    private MSCScalerUserRepository MSCScalerUserRepository;
    private MSCStudentRepository MSCStudentRepository;
    private MSCInstructorRepository MSCInstructorRepository;
    public InitServiceForMSC(@Autowired MSCScalerUserRepository MSCScalerUserRepository,
                             @Autowired MSCStudentRepository MSCStudentRepository,
                             @Autowired MSCInstructorRepository MSCInstructorRepository){
        this.MSCScalerUserRepository = MSCScalerUserRepository;
        this.MSCInstructorRepository = MSCInstructorRepository;
        this.MSCStudentRepository = MSCStudentRepository;
    }

    public void initialise(){
        Student st1 = new Student();
        st1.setName("Alice-Mapped");
        st1.setEmail("alice-mapped@scaler.com");
        st1.setPsp(88.0);
        st1.setBatchName("Apr21");
        MSCStudentRepository.save(st1);

        Instructor instructor = new Instructor();
        instructor.setName("Bob-Mapped");
        instructor.setEmail("bob-mapped@scaler.com");
        instructor.setInstructorRating(3.5);
        MSCInstructorRepository.save(instructor);

    }
}
