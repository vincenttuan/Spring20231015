package spring.mvc.session09.dao;

import java.util.Arrays;
import java.util.List;

import spring.mvc.session09.entity.EducationData;
import spring.mvc.session09.entity.SexData;
import spring.mvc.session09.entity.InterestData;

public class DataDaoImplInMemory implements DataDao {

	@Override
	public List<EducationData> findAllEducationDatas() {
		// 教育程度: 1:小學, 2:國中, 3:高中, 4:大學, 5:研究所
		List<EducationData> educationDatas = Arrays.asList(
				new EducationData(1, "小學"),
				new EducationData(2, "國中"),
				new EducationData(3, "高中"),
				new EducationData(4, "大學"),
				new EducationData(5, "研究所"));
		return educationDatas;
	}

	@Override
	public List<InterestData> InterestData() {
		// // 興趣: 1:爬山, 2:看書, 3:打球, 4:飛控, 5:手遊
		List<InterestData> interestDatas = Arrays.asList(
				new InterestData(1, "爬山"),
				new InterestData(2, "看書"),
				new InterestData(3, "打球"),
				new InterestData(4, "飛控"),
				new InterestData(5, "手遊"));
		return interestDatas;
	}

	@Override
	public List<SexData> findAllSexDatas() {
		// 性別: 1:男生, 2:女生
		List<SexData> sexDatas = Arrays.asList(
				new SexData(1, "男生"), 
				new SexData(2, "女生"));
		return sexDatas;
	}
	
}
