package spring.mvc.session09.dao;

import java.util.List;

import spring.mvc.session09.entity.EducationData;
import spring.mvc.session09.entity.InterestData;
import spring.mvc.session09.entity.SexData;

public interface DataDao {
	List<EducationData> findAllEducationDatas();
	List<InterestData> InterestData();
	List<SexData> findAllSexDatas();
}
