package com.liang.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.system.beans.RentA_BicycleStatus;
import com.liang.system.beans.BicyclePurchase;
import com.liang.system.util.PageModel;


public interface BicyclePurchaseMapper {
	
	@Select("select * from bicycle_purchase")
	List<BicyclePurchase> getBicyclePurchases();
	
	@Select("select * from bicycle_purchase where bp_id = #{bp_id}")
	BicyclePurchase getBicyclePurchaseById(BicyclePurchase bicyclePurchase);
	
	@Select("select count(*) from bicycle_purchase")
	int getBicyclePurchaseCount();
	
	@Select("select * from bicycle_purchase order by bp_id DESC limit 1")
	BicyclePurchase getBicyclePurchaseLastData();
	
	@Select("<script>"
            + "select * from `bicycle_purchase`"
            + "<where>"
            + "<if test=\"bp_id != null and bp_id != '' \">AND bp_id = #{bp_id}</if>"
            + "<if test=\"bp_brand != null and bp_brand != '' \">AND bp_brand = #{bp_brand}</if>"
            + "<if test=\"bp_model != null and bp_model != '' \">AND bp_model = #{bp_model}</if>"
            + "<if test=\"bp_isallocated != null\">AND bp_isallocated = #{bp_isallocated}</if>"
            + "</where>"
            + "</script>")
	List<BicyclePurchase> getBicyclePurchaseByCondition(BicyclePurchase bicyclePurchase);
	
	@Select("select * from bicycle_purchase limit #{start}, #{pageSize}")
	List<BicyclePurchase> getBicyclePurchaseByPage(PageModel pageModel);
	
	@Insert("insert into bicycle_purchase values(#{bp_id}, #{bp_brand}, #{bp_model}, #{bp_dateOfProduction}, #{bp_dateOfPurchase}, #{bp_isallocated}, #{bp_mark})")
	void addBicyclePurchase(BicyclePurchase bicyclePurchase);
	
	@Update("update bicycle_purchase set bp_brand = #{bp_brand}, bp_model = #{bp_model}, bp_dateOfProduction = #{bp_dateOfProduction}, bp_dateOfPurchase = #{bp_dateOfPurchase} , bp_isallocated = #{bp_isallocated} where bp_id = #{bp_id}")
	void updateBicyclePurchase(BicyclePurchase bicyclePurchase);
	
	//批量更新车辆分配状态
	@Update("update bicycle_purchase set bp_isallocated = #{bp_isallocated} where bp_id = #{bp_id}")
	void updateDistributeBicyclePurchase(BicyclePurchase bicyclePurchase);
	
	//根据bp_id查询车辆分配状态
	@Select("select bp_isallocated from bicycle_purchase where bp_id = #{bp_id}")
	boolean[] getBicyclePurchaseIsallocatedById(String bp_id);
	
	//根据新车辆分配更新到租借点车辆状态表
	@Insert("insert into rent_a_bicycle_status values(#{rbs_rp_id}, #{rbs_bp_id}, #{rbs_bicycle_hire_status}, #{rbs_bicycle_quality_status})")
	void addRentA_BicycleStatusFromBp(RentA_BicycleStatus rentA_BicycleStatus);
	
	@Delete("delete from bicycle_purchase where bp_id = #{bp_id}")
	void deleteBicyclePurchase(BicyclePurchase bicyclePurchase);
}
