package spring.mvc.session09.dao;

import java.util.List;
import java.util.Optional;

import spring.mvc.session09.entity.EducationData;
import spring.mvc.session09.entity.InterestData;
import spring.mvc.session09.entity.SexData;

public interface DataDao {
	List<EducationData> findAllEducationDatas();
	Optional<EducationData> getEducationDataById(Integer id);
	
	List<SexData> findAllSexDatas();
	Optional<SexData> getSexDataById(Integer id);
	
	List<InterestData> findAllInterestDatas();
	List<InterestData> findInterestDatasByIds(Integer... ids);
	
}
