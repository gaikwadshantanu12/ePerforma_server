package com.eperforma_server.notices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficialNoticesModel {
	public int noticeID;
	public String noticeTitle;
	public String noticeMessage;
	public String noticeFileName;
	public String hodCollegeID;
	public String hodDepartment;
	
}
