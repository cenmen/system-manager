package com.liang.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.BicycleDistributeSituation;
import com.liang.system.beans.Information;
import com.liang.system.beans.RentPlace;
import com.liang.system.mapper.BicycleDistributeSituationMapper;
import com.liang.system.mapper.CommonMapper;
import com.liang.system.service.IBicycleDistributeSituationService;
import com.liang.system.util.PageModel;

@Service
public class BicycleDistributeSituationServiceImpl implements IBicycleDistributeSituationService {
	@Autowired
	private BicycleDistributeSituationMapper bicycleDistributeSituationMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	@Override
	public List<BicycleDistributeSituation> getAllBicycleDistributeSituation() {
		return bicycleDistributeSituationMapper.getBicycleDistributeSituations();
	}
	
	@Override
	public List<BicycleDistributeSituation> getBicycleDistributeSituationByPage(int pageNum) {
		boolean isContain = false;
		List<String> addRp_id = new ArrayList<String>();
		//更新租借点当前车辆数量
		List<String> listFromRp = bicycleDistributeSituationMapper.getRentPlaces();
		List<String> listFromRbs = bicycleDistributeSituationMapper.getBicycleDistributeSituationsRpId();
		
		for(int i = 0; i < listFromRp.size(); i++) {
			isContain = listFromRbs.contains(listFromRp.get(i).toString());
			if (!isContain) {
				addRp_id.add(listFromRp.get(i).toString());
			}
		}
		System.out.println("addRp_id : " + addRp_id);
		for (String str : addRp_id) {
			addBicycleDistributeSituation(str);
		}
		for(int i = 0; i < listFromRp.size(); i++) {
			int number = bicycleDistributeSituationMapper.getRentA_BicycleStatusCount(listFromRp.get(i).toString());
			BicycleDistributeSituation bicycleDistributeSituation = new BicycleDistributeSituation();
			bicycleDistributeSituation.setBds_rp_id(listFromRp.get(i).toString());
			bicycleDistributeSituation.setBds_distribute_num(number);
			bicycleDistributeSituationMapper.updateBicycleDistributeSituationDistributeNum(bicycleDistributeSituation);
		}
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return bicycleDistributeSituationMapper.getBicycleDistributeSituationByPage(pageModel);
	}
	
	@Override
	public BicycleDistributeSituation getBicycleDistributeSituationById(String bds_id) {
		BicycleDistributeSituation bicycleDistributeSituation = new BicycleDistributeSituation();
		bicycleDistributeSituation.setBds_id(bds_id);
		return bicycleDistributeSituationMapper.getBicycleDistributeSituationById(bicycleDistributeSituation);
	}
	
	@Override
	public List<BicycleDistributeSituation> getBicycleDistributeSituationByCondition(String bds_id, String bds_rp_id) {
		BicycleDistributeSituation bicycleDistributeSituation = new BicycleDistributeSituation();
		bicycleDistributeSituation.setBds_id(bds_id);
		bicycleDistributeSituation.setBds_rp_id(bds_rp_id);
		return bicycleDistributeSituationMapper.getBicycleDistributeSituationByCondition(bicycleDistributeSituation);
	}
	
	@Override
	public void addBicycleDistributeSituation(String bds_rp_id) {
		BicycleDistributeSituation bicycleDistributeSituation = new BicycleDistributeSituation();
		String lastId = bicycleDistributeSituationMapper.getBicycleDistributeSituationsLastData().substring(3);
		int i = Integer.parseInt(lastId);
		String persentId = "BDS" + (i+1);
		bicycleDistributeSituation.setBds_id(persentId);
		bicycleDistributeSituation.setBds_rp_id(bds_rp_id);
		bicycleDistributeSituation.setBds_distribute_num(0);//新增时，当前数量默认值设置为0
		System.out.println("bicycleDistributeSituation : " + bicycleDistributeSituation);
		bicycleDistributeSituationMapper.addBicycleDistributeSituation(bicycleDistributeSituation);
	}
	
	@Override
	public void updateBicycleDistributeSituation(BicycleDistributeSituation bicycleDistributeSituation) {
		String dbs_dispatch_plan = bicycleDistributeSituation.getDbs_dispatch_plan();
		System.out.println("bicycleDistributeSituation : " + bicycleDistributeSituation);
		if (!dbs_dispatch_plan.isEmpty()) {
			Information information = new Information();
			information.setInfo_from("SYS");
			information.setInfo_to(bicycleDistributeSituation.getBds_rp_id());
			information.setInfo_type("车辆调度");
//			String str = "系统分配"+bicycleDistributeSituation.getDbs_intend_num()+"辆车到你的租借点（"+bicycleDistributeSituation.getBds_rp_id()+")";
			information.setInfo_content(dbs_dispatch_plan);
			System.out.println("information : " + information);
			commonMapper.addInformation(information);
		}
		bicycleDistributeSituationMapper.updateBicycleDistributeSituation(bicycleDistributeSituation);
	}
	
	@Override
	public void deleteBicycleDistributeSituation(String bds_id) {
		BicycleDistributeSituation bicycleDistributeSituation = new BicycleDistributeSituation();
		bicycleDistributeSituation.setBds_id(bds_id);
		bicycleDistributeSituationMapper.deleteBicycleDistributeSituation(bicycleDistributeSituation);
	}
}
