package com.liang.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.system.beans.RentPlace;
import com.liang.system.service.IRentPlaceService;


@RestController
@RequestMapping("/RentPlaceCon")
public class RentPlaceController {
	@Autowired
	private IRentPlaceService rentPlaceService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	@RequestMapping(path="/getAllRentPlace.do", produces="application/json;charset=utf-8")
	public List<RentPlace> getAllRentPlace() {
		List<RentPlace> rentPlaceList = rentPlaceService.getAllRentPlace();
		System.out.println(rentPlaceList);
		return rentPlaceList;
	}
	
	@RequestMapping(path="/getRentPlaceById.do")
	public RentPlace getRentPlaceById(String rp_id) {
		return rentPlaceService.getRentPlaceById(rp_id);
	}
	
	@RequestMapping(path="/getRentPlaceByCondition.do", produces="application/json;charset=utf-8")
	public List<RentPlace> getRentPlaceByCondition(RentPlace rentPlace) {
		List<RentPlace> RentPlaceList = rentPlaceService.getRentPlaceByCondition(rentPlace);
		System.out.println("in getByCondition_bds"+RentPlaceList);
		return RentPlaceList;
	}
	
	@RequestMapping(path="/getRentPlaceByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getRentPlaceByPageCache")
	public List<RentPlace> getRentPlaceByPage(int pageNum) {
		List<RentPlace> rentPlaceList = rentPlaceService.getRentPlaceByPage(pageNum);
		System.out.println(pageNum+","+rentPlaceList);
		return rentPlaceList;
	}
	
	@RequestMapping(path="/addRentPlace.do")
	public void addRentPlace(RentPlace rentPlace) {
		System.out.println(rentPlace);
		rentPlaceService.addRentPlace(rentPlace);
	}
	
	@RequestMapping(path="/updateRentPlace.do")
	public void updateRentPlace(RentPlace rentPlace) {
		rentPlaceService.updateRentPlace(rentPlace);
	}
	
	@RequestMapping(path="/deleteRentPlace.do")
	public void deleteRentPlace(String rp_id) {
		rentPlaceService.deleteRentPlace(rp_id);
	}
}
