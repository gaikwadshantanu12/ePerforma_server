package com.eperforma_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
@EntityScan(basePackages = {"com.eperforma_server.student.entity", "com.eperforma_server.teacher.entity", "com.eperforma_server.hod.entity", "com.eperforma_server.notices.entity", "com.eperforma_server.classes.entity", "com.eperforma_server.subjects.entity", "com.eperforma_server.lecture_attendance.entity", "com.eperforma_server.student_documents.entity", "com.eperforma_server.batch_allotment.entity", "com.eperforma_server.messages.entity"})
public class EperformaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EperformaServerApplication.class, args);
	}

}
