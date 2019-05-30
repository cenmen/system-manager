package com.liang.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.Information;
import com.liang.system.mapper.CommonMapper;
import com.liang.system.service.IIformationService;


@Service
public class InformationServiceImpl  implements IIformationService{

	@Autowired
	private CommonMapper commonMapper;

	@Override
	public List<Information> getInformations() {
		String info_to = "SYS";
		return commonMapper.getAllInformationByInfoTo(info_to);
	}
	
	@Override
	public void updateInformationStatus(int info_id) {
		Information information = new Information();
		information.setInfo_id(info_id);
		information.setInfo_status(true);
		commonMapper.updateInformationStatus(information);
	}
}
