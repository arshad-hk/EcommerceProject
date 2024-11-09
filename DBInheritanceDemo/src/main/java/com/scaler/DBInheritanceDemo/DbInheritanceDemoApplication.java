package com.scaler.DBInheritanceDemo;

import com.scaler.DBInheritanceDemo.joined.service.InitServiceForJoined;
import com.scaler.DBInheritanceDemo.mappedSuperClass.service.InitServiceForMSC;
import com.scaler.DBInheritanceDemo.perClass.service.InitServiceForPerClass;
import com.scaler.DBInheritanceDemo.singleTable.service.InitServiceForSingleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbInheritanceDemoApplication implements CommandLineRunner {
	@Autowired
	InitServiceForJoined initServiceForJoined;
	InitServiceForPerClass initServiceForPerClass;
	InitServiceForSingleTable initServiceForSingleTable;
	InitServiceForMSC initServiceForMSC;

	/* newer spring version does not require to explicitly write Autowired for
	 constructor injection*/
	DbInheritanceDemoApplication(InitServiceForMSC initServiceForMSC,
								 InitServiceForSingleTable initServiceForSingleTable,
								 InitServiceForPerClass initServiceForPerClass){
		this.initServiceForMSC = initServiceForMSC;
		this.initServiceForSingleTable = initServiceForSingleTable;
		this.initServiceForPerClass = initServiceForPerClass;
	}

	public static void main(String[] args) {
		SpringApplication.run(DbInheritanceDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initServiceForJoined.initialise();
		initServiceForPerClass.initialise();
		initServiceForMSC.initialise();
		initServiceForSingleTable.initialise();
	}
}
