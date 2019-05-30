package com.liang.system.service;

import java.util.List;


import com.liang.system.beans.BicycleQuality;

public interface IBicycleQualityService {
	
	List<BicycleQuality> getAllBicycleQuality();
	
	BicycleQuality getBicycleQualityById(int bq_id);
	
	List<BicycleQuality> getBicycleQualityByCondition(BicycleQuality bicycleQuality);
	
	List<BicycleQuality> getBicycleQualityByPage(int pageNum);
	
	boolean addBicycleQuality(BicycleQuality bicycleQuality);
	
	void updateBicycleQuality(BicycleQuality bicycleQuality);
	
	void deleteBicycleQuality(int bq_id);
}
