package com.lucas.eazybankboot.bank.repository.jpa_interface;

import java.util.List;

import com.lucas.eazybankboot.bank.model.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

	@Query(value = "from notice_details n where CURRENT_DATE BETWEEN n.noticBegDt AND n.noticEndDt")
	List<Notice> findAllActiveNotices();
}
