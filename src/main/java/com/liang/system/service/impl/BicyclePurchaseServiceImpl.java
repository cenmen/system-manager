package com.liang.system.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.BicyclePurchase;
import com.liang.system.beans.Information;
import com.liang.system.beans.RentA_BicycleStatus;
import com.liang.system.mapper.BicyclePurchaseMapper;
import com.liang.system.mapper.CommonMapper;
import com.liang.system.service.IBicyclePurchaseService;
import com.liang.system.util.PageModel;

@Service
public class BicyclePurchaseServiceImpl implements IBicyclePurchaseService {
	@Autowired
	private BicyclePurchaseMapper bicyclePurchaseMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	@Override
	public List<BicyclePurchase> getAllBicyclePurchase() {
		return bicyclePurchaseMapper.getBicyclePurchases();
	}
	
	@Override
	public void addBicyclePurchase(BicyclePurchase bicyclePurchase) {
		bicyclePurchaseMapper.addBicyclePurchase(bicyclePurchase);
	}
	
	@Override
	public BicyclePurchase getBicyclePurchaseById(String bp_id) {
		BicyclePurchase bicyclePurchase = new BicyclePurchase();
		bicyclePurchase.setBp_id(bp_id);
		return bicyclePurchaseMapper.getBicyclePurchaseById(bicyclePurchase);
	}
	
	@Override
	public int getBicyclePurchaseCount() {
		return bicyclePurchaseMapper.getBicyclePurchaseCount();
	}
	
	@Override
	public BicyclePurchase getBicyclePurchaseLastData() {
		return bicyclePurchaseMapper.getBicyclePurchaseLastData();
	}
	
	@Override
	public List<BicyclePurchase> getBicyclePurchaseByCondition(BicyclePurchase bicyclePurchase) {
		return bicyclePurchaseMapper.getBicyclePurchaseByCondition(bicyclePurchase);
	}
	
	@Override
	public List<BicyclePurchase> getBicyclePurchaseByPage(int pageNum) {
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return bicyclePurchaseMapper.getBicyclePurchaseByPage(pageModel);
	}
	
	@Override
	public void updateBicyclePurchase(BicyclePurchase bicyclePurchase) {
		bicyclePurchaseMapper.updateBicyclePurchase(bicyclePurchase);
	}
	
	@Override
	public void deleteBicyclePurchase(String bp_id) {
		BicyclePurchase bicyclePurchase = new BicyclePurchase();
		bicyclePurchase.setBp_id(bp_id);
		bicyclePurchaseMapper.deleteBicyclePurchase(bicyclePurchase);
	}
	
	@Override
	public boolean updateDistributeBicyclePurchase(String[] bp_id, String rbs_rp_id) {
		boolean isPass = false;
		//判断所选择的车辆ID的分配状态是否已分配，如果有则isPass为true
		for (int i = 0; i < bp_id.length; i++) {
			boolean[] isallocated = bicyclePurchaseMapper.getBicyclePurchaseIsallocatedById(bp_id[i]);
			System.out.println("isallocated : " + Arrays.toString(isallocated));
			if (isallocated[0]) {
				isPass = true;
			}
        }
		//未分配的车辆，修改车辆采购表的分配状态和增加租借点车辆状态表的车辆
		if (!isPass && bp_id.length != 0){
			for (int i = 0; i < bp_id.length; i++) {
				BicyclePurchase bicyclePurchase = new BicyclePurchase();
				bicyclePurchase.setBp_id(bp_id[i]);
				bicyclePurchase.setBp_isallocated(true);
				bicyclePurchaseMapper.updateDistributeBicyclePurchase(bicyclePurchase);
				
				RentA_BicycleStatus rentA_BicycleStatus = new RentA_BicycleStatus();
				rentA_BicycleStatus.setRbs_rp_id(rbs_rp_id);
				rentA_BicycleStatus.setRbs_bp_id(bp_id[i]);
				rentA_BicycleStatus.setRbs_bicycle_hire_status(false);
				rentA_BicycleStatus.setRbs_bicycle_quality_status("正常");
				bicyclePurchaseMapper.addRentA_BicycleStatusFromBp(rentA_BicycleStatus);
	        }
			//增加消息
			Information information = new Information();
			information.setInfo_from("SYS");
			information.setInfo_to(rbs_rp_id);
			information.setInfo_type("车辆分配");
			String str = "系统分配"+bp_id.length+"辆车到你的租借点（"+rbs_rp_id+")";
			information.setInfo_content(str);
			System.out.println("information : " + information);
			commonMapper.addInformation(information);
			return true;
        } else {
        	System.out.println("所选记录中存在已分配的车辆状态");
        	return false;
        }
	}
}
