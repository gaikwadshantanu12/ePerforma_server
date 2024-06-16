package com.eperforma_server.notices.repository;

import org.springframework.stereotype.Repository;
import com.eperforma_server.notices.entity.OfficialNoticesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface OfficialNoticesRepository extends JpaRepository<OfficialNoticesEntity, Integer>{
	public boolean deleteByNoticeID(int noticeID);

	public OfficialNoticesEntity findByNoticeID(int noticeID);
	
	public List<OfficialNoticesEntity> findByHodCollegeID(String hodCollegeID);

	public List<OfficialNoticesEntity> findByHodDepartment(String hodDepartment);

	public List<OfficialNoticesEntity> findByNoticeIDAndHodCollegeID(int noticeID, String hodCollegeID);

	@Query(value = "SELECT * from official_notices WHERE hod_department = :department", nativeQuery = true)
	List<Object[]> getNoticesByDepartment(@Param("department") String department);
}
