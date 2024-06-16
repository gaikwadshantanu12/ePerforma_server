package com.eperforma_server.notices.entity;

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
@Table(name = "official_notices")
public class OfficialNoticesEntity {
	
	@Column(name = "notice_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int noticeID;
	
	@Column(name = "notice_title")
	public String noticeTitle;
	
	@Column(name = "notice_message")
	public String noticeMessage;
	
	@Column(name = "notice_file_name")
	public String noticeFileName;
	
	@Column(name = "hod_college_id")
	public String hodCollegeID;

	@Column(name = "hod_department")
	public String hodDepartment;

}
