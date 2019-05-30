package com.liang.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.liang.system.beans.RentA_BicycleManager;
import com.liang.system.util.PageModel;

public interface RentA_BicycleManagerMapper {
	@Select("select * from rent_a_bicycle_manager")
	List<RentA_BicycleManager> getRentA_BicycleManagers();
	
	@Select("select * from rent_a_bicycle_manager where rbm_id = #{rbm_id}")
	RentA_BicycleManager getRentA_BicycleManagerById(RentA_BicycleManager rentA_BicycleManager);
	
	@Select("<script>"
            + "select * from `rent_a_bicycle_manager`"
            + "<where>"
            + "<if test=\"rbm_bp_id != null and rbm_bp_id != '' \">AND rbm_bp_id = #{rbm_bp_id}</if>"
            + "<if test=\"rbm_hirer_name != null and rbm_hirer_name != '' \">AND rbm_hirer_name = #{rbm_hirer_name}</if>"
            + "<if test=\"rbm_hirer_contact != null and rbm_hirer_contact != '' \">AND rbm_hirer_contact = #{rbm_hirer_contact}</if>"
            + "</where>"
            + "</script>")
	List<RentA_BicycleManager> getRentA_BicycleManagerByCondition(RentA_BicycleManager rentA_BicycleManager);
	
	@Select("select * from rent_a_bicycle_manager limit #{start}, #{pageSize}")
	List<RentA_BicycleManager> getRentA_BicycleManagerByPage(PageModel pageModel);
	
}
