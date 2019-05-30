package com.liang.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.RentManager;
import com.liang.system.beans.RentPlace;
import com.liang.system.mapper.CommonMapper;
import com.liang.system.mapper.RentPlaceMapper;
import com.liang.system.service.IRentPlaceService;
import com.liang.system.util.PageModel;


@Service
public class RentPlaceServiceImpl implements IRentPlaceService{
	@Autowired
	private RentPlaceMapper rentPlaceMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	@Override
	public List<RentPlace> getAllRentPlace() {
		return rentPlaceMapper.getRentPlaces();
	}
	
	@Override
	public void addRentPlace(RentPlace rentPlace) {
		String lastRp_id = commonMapper.getLastRp_idFromRP();
		String number = lastRp_id.substring(2);
		int i = Integer.parseInt(number);
		String s = "";
		s=String.valueOf("RP"+(i+1));
		rentPlace.setRp_id(s);
		rentPlaceMapper.addRentPlace(rentPlace);
	}
	
	@Override
	public RentPlace getRentPlaceById(String rp_id) {
		RentPlace rentPlace = new RentPlace();
		rentPlace.setRp_id(rp_id);
		return rentPlaceMapper.getRentPlaceById(rentPlace);
	}
	
	@Override
	public List<RentPlace> getRentPlaceByCondition(RentPlace rentPlace) {
		return rentPlaceMapper.getRentPlaceByCondition(rentPlace);
	}
	
	@Override
	public List<RentPlace> getRentPlaceByPage(int pageNum) {
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return rentPlaceMapper.getRentPlaceByPage(pageModel);
	}
	
	@Override
	public void updateRentPlace(RentPlace rentPlace) {
		rentPlaceMapper.updateRentPlace(rentPlace);
	}
	
	@Override
	public void deleteRentPlace(String rp_id) {
		RentPlace rentPlace = new RentPlace();
		rentPlace.setRp_id(rp_id);
		rentPlaceMapper.deleteRentPlace(rentPlace);
	}
}
