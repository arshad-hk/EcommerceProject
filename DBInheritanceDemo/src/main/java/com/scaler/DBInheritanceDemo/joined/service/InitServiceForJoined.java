package com.scaler.DBInheritanceDemo.joined.service;

import com.scaler.DBInheritanceDemo.joined.Instructor;
import com.scaler.DBInheritanceDemo.joined.Student;
import com.scaler.DBInheritanceDemo.joined.repository.InstructorRepository;
import com.scaler.DBInheritanceDemo.joined.repository.ScalerUserRepository;
import com.scaler.DBInheritanceDemo.joined.repository.StudentRepository;
import com.scaler.DBInheritanceDemo.joined.ScalerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitServiceForJoined {
    private ScalerUserRepository scalerUserRepository;
    private StudentRepository studentRepository;
    private InstructorRepository instructorRepository;
    public InitServiceForJoined(@Autowired ScalerUserRepository scalerUserRepository,
                                @Autowired StudentRepository studentRepository,
                                @Autowired InstructorRepository instructorRepository){
        this.scalerUserRepository = scalerUserRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    public void initialise(){
        Student st1 = new Student();
        st1.setName("Alice-Joined");
        st1.setEmail("alice-joined@scaler.com");
        st1.setPsp(90.0);
        st1.setBatchName("Jun22");
        studentRepository.save(st1);

        Instructor instructor = new Instructor();
        instructor.setName("Bob-Joined");
        instructor.setEmail("bob-joined@scaler.com");
        instructor.setInstructorRating(4.5);
        instructorRepository.save(instructor);

        ScalerUser scalerUser = new ScalerUser();
        scalerUser.setEmail("charlie-joined@scaler.com");
        scalerUser.setName("Charlie-Joined");
        scalerUserRepository.save(scalerUser);
    }
}
