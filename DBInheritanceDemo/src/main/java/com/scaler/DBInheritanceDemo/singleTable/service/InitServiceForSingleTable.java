package com.scaler.DBInheritanceDemo.singleTable.service;

import com.scaler.DBInheritanceDemo.singleTable.Instructor;
import com.scaler.DBInheritanceDemo.singleTable.ScalerUser;
import com.scaler.DBInheritanceDemo.singleTable.Student;
import com.scaler.DBInheritanceDemo.singleTable.repository.SingleTableInstructorRepository;
import com.scaler.DBInheritanceDemo.singleTable.repository.SingleTableScalerUserRepository;
import com.scaler.DBInheritanceDemo.singleTable.repository.SingleTableStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitServiceForSingleTable {
    private SingleTableScalerUserRepository singleTableScalerUserRepository;
    private SingleTableStudentRepository singleTableStudentRepository;
    private SingleTableInstructorRepository singleTableInstructorRepository;
    public InitServiceForSingleTable(@Autowired SingleTableScalerUserRepository singleTableScalerUserRepository,
                                     @Autowired SingleTableStudentRepository singleTableStudentRepository,
                                     @Autowired SingleTableInstructorRepository singleTableInstructorRepository){
        this.singleTableScalerUserRepository = singleTableScalerUserRepository;
        this.singleTableInstructorRepository = singleTableInstructorRepository;
        this.singleTableStudentRepository = singleTableStudentRepository;
    }

    public void initialise(){
        Student st1 = new Student();
        st1.setName("Alice-SingleTable");
        st1.setEmail("alice-singletable@scaler.com");
        st1.setPsp(94.0);
        st1.setBatchName("Oct22");
        singleTableStudentRepository.save(st1);

        Instructor instructor = new Instructor();
        instructor.setName("Bob-SingleTable");
        instructor.setEmail("bob-singletable@scaler.com");
        instructor.setInstructorRating(4.1);
        singleTableInstructorRepository.save(instructor);

        ScalerUser scalerUser = new ScalerUser();
        scalerUser.setEmail("charlie-singletable@scaler.com");
        scalerUser.setName("Charlie-SingleTable");
        singleTableScalerUserRepository.save(scalerUser);

    }
}
