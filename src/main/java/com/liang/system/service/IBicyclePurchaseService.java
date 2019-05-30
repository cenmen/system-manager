package com.liang.system.service;

import java.util.List;

import com.liang.system.beans.BicyclePurchase;

public interface IBicyclePurchaseService {
	
	List<BicyclePurchase> getAllBicyclePurchase();
	
	BicyclePurchase getBicyclePurchaseById(String bp_id);
	
	int getBicyclePurchaseCount();
	
	BicyclePurchase getBicyclePurchaseLastData();
	
	List<BicyclePurchase> getBicyclePurchaseByCondition(BicyclePurchase bicyclePurchase);
	
	List<BicyclePurchase> getBicyclePurchaseByPage(int pageNum);
	
	void addBicyclePurchase(BicyclePurchase bicyclePurchase);
	
	void updateBicyclePurchase(BicyclePurchase bicyclePurchase);
	
	boolean updateDistributeBicyclePurchase(String[] bp_id, String rbs_rp_id);
	
	void deleteBicyclePurchase(String bp_id);
}
