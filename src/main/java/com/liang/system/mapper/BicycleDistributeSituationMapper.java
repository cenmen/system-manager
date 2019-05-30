package com.liang.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.system.beans.BicycleDistributeSituation;
import com.liang.system.beans.BicyclePurchase;
import com.liang.system.beans.RentA_BicycleManager;
import com.liang.system.beans.RentPlace;
import com.liang.system.util.PageModel;

public interface BicycleDistributeSituationMapper {
	@Select("select * from bicycle_distribute_situation")
	List<BicycleDistributeSituation> getBicycleDistributeSituations();
	
	@Select("select * from bicycle_distribute_situation limit #{start}, #{pageSize}")
	List<BicycleDistributeSituation> getBicycleDistributeSituationByPage(PageModel pageModel);
	
	@Select("select * from bicycle_distribute_situation where bds_id = #{bds_id}")
	BicycleDistributeSituation getBicycleDistributeSituationById(BicycleDistributeSituation bicycleDistributeSituation);
	
	@Select("select * from bicycle_distribute_situation where bds_id = #{bds_id} or bds_rp_id = #{bds_rp_id}")
	List<BicycleDistributeSituation> getBicycleDistributeSituationByCondition(BicycleDistributeSituation bicycleDistributeSituation);
	
	@Insert("insert into bicycle_distribute_situation values(#{bds_id}, #{bds_rp_id}, #{bds_distribute_num}, #{dbs_intend_num}, #{dbs_dispatch_plan}, #{dbs_dispatch_finish})")
	void addBicycleDistributeSituation(BicycleDistributeSituation bicycleDistributeSituation);
	
	@Update("update bicycle_distribute_situation set bds_rp_id = #{bds_rp_id}, bds_distribute_num = #{bds_distribute_num}, dbs_intend_num = #{dbs_intend_num}, dbs_dispatch_plan = #{dbs_dispatch_plan}, dbs_dispatch_finish = #{dbs_dispatch_finish} where bds_id = #{bds_id}")
	void updateBicycleDistributeSituation(BicycleDistributeSituation bicycleDistributeSituation);
	
	@Delete("delete from bicycle_distribute_situation where bds_id = #{bds_id}")
	void deleteBicycleDistributeSituation(BicycleDistributeSituation bicycleDistributeSituation);
	
	//查询租借点ID
	@Select("select rp_id from rent_place")
	List<String>  getRentPlaces();
	
	//查询对应租借点ID的车辆条数
	@Select("select count(rbs_rp_id) from rent_a_bicycle_status where rbs_rp_id = #{rbs_rp_id}")
	int getRentA_BicycleStatusCount(String rbm_rp_id);
	
	//根据租借点ID修改当前车辆数量
	@Update("update bicycle_distribute_situation set  bds_distribute_num = #{bds_distribute_num} where bds_rp_id = #{bds_rp_id}")
	void updateBicycleDistributeSituationDistributeNum(BicycleDistributeSituation bicycleDistributeSituation);
	
	//获取最后一条记录的ID
	@Select("select bds_id from bicycle_distribute_situation order by bds_id DESC limit 1")
	String getBicycleDistributeSituationsLastData();
	
	//查询车辆分配表已有的租借点ID
	@Select("select bds_rp_id from bicycle_distribute_situation")
	List<String>  getBicycleDistributeSituationsRpId();
}
