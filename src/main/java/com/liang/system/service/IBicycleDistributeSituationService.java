package com.liang.system.service;

import java.util.List;

import com.liang.system.beans.BicycleDistributeSituation;

public interface IBicycleDistributeSituationService {
	
	List<BicycleDistributeSituation> getAllBicycleDistributeSituation();
	
	List<BicycleDistributeSituation> getBicycleDistributeSituationByPage(int pageNum);
	
	BicycleDistributeSituation getBicycleDistributeSituationById(String bds_id);
	
	List<BicycleDistributeSituation> getBicycleDistributeSituationByCondition(String bds_id, String bds_rp_id);
	
	void addBicycleDistributeSituation(String bds_rp_id);
	
	void updateBicycleDistributeSituation(BicycleDistributeSituation bicycleDistributeSituation);
	
	void deleteBicycleDistributeSituation(String bds_id);
}
