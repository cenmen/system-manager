package com.liang.system.service;

import java.util.List;

import com.liang.system.beans.RentPlace;

public interface IRentPlaceService {
	
	List<RentPlace> getAllRentPlace();
	
	RentPlace getRentPlaceById(String rp_id);
	
	List<RentPlace> getRentPlaceByCondition(RentPlace RentPlace);
	
	List<RentPlace> getRentPlaceByPage(int pageNum);
	
	void addRentPlace(RentPlace rentPlace);
	
	void updateRentPlace(RentPlace rentPlace);
	
	void deleteRentPlace(String rp_id);
}
