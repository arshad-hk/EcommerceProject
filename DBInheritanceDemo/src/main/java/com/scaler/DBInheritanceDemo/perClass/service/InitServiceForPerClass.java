package com.scaler.DBInheritanceDemo.perClass.service;

import com.scaler.DBInheritanceDemo.perClass.Instructor;
import com.scaler.DBInheritanceDemo.perClass.ScalerUser;
import com.scaler.DBInheritanceDemo.perClass.Student;
import com.scaler.DBInheritanceDemo.perClass.repository.PerClassInstructorRepository;
import com.scaler.DBInheritanceDemo.perClass.repository.PerClassScalerUserRepository;
import com.scaler.DBInheritanceDemo.perClass.repository.PerClassStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitServiceForPerClass {
    private PerClassScalerUserRepository perClassScalerUserRepository;
    private PerClassStudentRepository perClassStudentRepository;
    private PerClassInstructorRepository perClassInstructorRepository;
    public InitServiceForPerClass(@Autowired PerClassScalerUserRepository perClassScalerUserRepository,
                                  @Autowired PerClassStudentRepository perClassStudentRepository,
                                  @Autowired PerClassInstructorRepository perClassInstructorRepository){
        this.perClassScalerUserRepository = perClassScalerUserRepository;
        this.perClassInstructorRepository = perClassInstructorRepository;
        this.perClassStudentRepository = perClassStudentRepository;
    }

    public void initialise(){
        Student st1 = new Student();
        st1.setName("Alice-PerClass");
        st1.setEmail("alice-perclass@scaler.com");
        st1.setPsp(98.0);
        st1.setBatchName("Jul23");
        perClassStudentRepository.save(st1);

        Instructor instructor = new Instructor();
        instructor.setName("Bob-PerClass");
        instructor.setEmail("bob-perclass@scaler.com");
        instructor.setInstructorRating(4.2);
        perClassInstructorRepository.save(instructor);

        ScalerUser scalerUser = new ScalerUser();
        scalerUser.setEmail("charlie-perclass@scaler.com");
        scalerUser.setName("Charlie-PerClass");
        perClassScalerUserRepository.save(scalerUser);

    }
}
