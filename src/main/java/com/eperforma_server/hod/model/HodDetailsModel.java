package com.eperforma_server.hod.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HodDetailsModel {
	public int ID;
	public String hodCollegeID;
	public String hodName;
	public String hodEmail;
	public String hodPassword;
	public String hodDepartment;
	public String hodMobileNumber;
		
}