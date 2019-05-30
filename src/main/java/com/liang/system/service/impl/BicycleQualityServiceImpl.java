package com.liang.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.BicycleQuality;
import com.liang.system.beans.Information;
import com.liang.system.beans.RentA_BicycleStatus;
import com.liang.system.mapper.BicycleQualityMapper;
import com.liang.system.mapper.CommonMapper;
import com.liang.system.service.IBicycleQualityService;
import com.liang.system.util.PageModel;

@Service
public class BicycleQualityServiceImpl implements IBicycleQualityService {
	@Autowired
	private BicycleQualityMapper bicycleQualityMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	@Override
	public List<BicycleQuality> getAllBicycleQuality() {
		return bicycleQualityMapper.getBicycleQualitys();
	}
	
	@Override
	public boolean addBicycleQuality(BicycleQuality bicycleQuality) {
		boolean isRp_id = false;
		boolean isBp_id = false;
		List<String> listRp_id = commonMapper.getAllRp_idFromRP();
		List<String> listBp_id = commonMapper.getAllBp_idFromBP();
		String rp_id = bicycleQuality.getBq_rp_id();
		String bp_id = bicycleQuality.getBq_bp_id();
		for (String str : listRp_id) {
			if (str.equals(rp_id)) {
				isRp_id = true;
			}
		}
		for (String str : listBp_id) {
			if (str.equals(bp_id)) {
				isBp_id = true;
			}
		}
		if (isRp_id && isBp_id) {
			bicycleQualityMapper.addBicycleQuality(bicycleQuality);
		}
		return isRp_id && isBp_id;
	}
	
	@Override
	public BicycleQuality getBicycleQualityById(int bq_id) {
		BicycleQuality bicycleQuality = new BicycleQuality();
		bicycleQuality.setBq_id(bq_id);
		return bicycleQualityMapper.getBicycleQualityById(bicycleQuality);
	}
	
	@Override
	public List<BicycleQuality> getBicycleQualityByCondition(BicycleQuality bicycleQuality) {
		return bicycleQualityMapper.getBicycleQualityByCondition(bicycleQuality);
	}
	
	@Override
	public List<BicycleQuality> getBicycleQualityByPage(int pageNum) {
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return bicycleQualityMapper.getBicycleQualityByPage(pageModel);
	}
	
	@Override
	public void updateBicycleQuality(BicycleQuality bicycleQuality) {
		boolean isLose = "丢失".equals(bicycleQuality.getBq_trouble());
		if (bicycleQuality.isBq_ishandle() && bicycleQuality.isBq_isremand() && !isLose) {
			RentA_BicycleStatus rentA_BicycleStatus = new RentA_BicycleStatus();
			rentA_BicycleStatus.setRbs_bicycle_quality_status("正常");
			rentA_BicycleStatus.setRbs_bp_id(bicycleQuality.getBq_bp_id());
			bicycleQualityMapper.updateRentA_BicycleStatusToNormal(rentA_BicycleStatus);
			//增加消息
			Information information = new Information();
			information.setInfo_from("SYS");
			information.setInfo_to(bicycleQuality.getBq_rp_id());
			information.setInfo_type("车辆质量");
			String str = "系统已处理并将车辆（" + bicycleQuality.getBq_bp_id() + "）送回租借点（" + bicycleQuality.getBq_rp_id() + "）可正常使用";
			information.setInfo_content(str);
			System.out.println("information : " + information);
			commonMapper.addInformation(information);
		}
		if (bicycleQuality.isBq_ishandle() && bicycleQuality.isBq_isremand() && isLose) {
			RentA_BicycleStatus rentA_BicycleStatus = new RentA_BicycleStatus();
			rentA_BicycleStatus.setRbs_bicycle_quality_status("丢失");
			rentA_BicycleStatus.setRbs_bp_id(bicycleQuality.getBq_bp_id());
			bicycleQualityMapper.updateRentA_BicycleStatusToNormal(rentA_BicycleStatus);
			//增加消息
			Information information = new Information();
			information.setInfo_from("SYS");
			information.setInfo_to(bicycleQuality.getBq_rp_id());
			information.setInfo_type("车辆质量");
			String str = "系统已登记丢失车辆（" + bicycleQuality.getBq_bp_id() + "）请租借点（" + bicycleQuality.getBq_rp_id() + "）及时修改车辆状态";
			information.setInfo_content(str);
			System.out.println("information isLose : " + information);
			commonMapper.addInformation(information);
		}
		bicycleQualityMapper.updateBicycleQuality(bicycleQuality);
	}
	
	@Override
	public void deleteBicycleQuality(int bq_id) {
		BicycleQuality bicycleQuality = new BicycleQuality();
		bicycleQuality.setBq_id(bq_id);
		bicycleQualityMapper.deleteBicycleQuality(bicycleQuality);
	}
}
