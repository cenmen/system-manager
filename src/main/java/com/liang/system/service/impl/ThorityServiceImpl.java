package com.liang.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.Thority;
import com.liang.system.mapper.ThorityMapper;
import com.liang.system.service.IThorityService;

@Service
public class ThorityServiceImpl implements IThorityService{
	
	@Autowired
	private ThorityMapper thorityMapper;
	
	@Override
	public Thority getThor(int id) {
		Thority thority = new Thority();
		thority.setId(id);
		return thorityMapper.getThor(thority);
	}
}
