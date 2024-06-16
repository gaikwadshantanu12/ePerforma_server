package com.eperforma_server.hod.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hod_info")
public class HodDetailsEntity {
	
	@Column(name = "hod_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ID;
	
	@Column(name = "hod_college_id")
	public String hodCollegeID;
	
	@Column(name = "hod_name")
	public String hodName;
	
	@Column(name = "hod_email")
	public String hodEmail;
	
	@Column(name = "hod_password")
	public String hodPassword;
	
	@Column(name = "hod_department")
	public String hodDepartment;
	
	@Column(name = "hod_mobile")
	public String hodMobileNumber;
}