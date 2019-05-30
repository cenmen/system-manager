package com.liang.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.system.beans.RentPlace;
import com.liang.system.util.PageModel;

public interface RentPlaceMapper {
	@Select("select * from rent_place")
	List<RentPlace> getRentPlaces();
	
	@Select("select * from rent_place where rp_id = #{rp_id}")
	RentPlace getRentPlaceById(RentPlace rentPlace);
	
	@Select("<script>"
            + "select * from `rent_place`"
            + "<where>"
            + "<if test=\"rp_id != null and rp_id != '' \">AND rp_id = #{rp_id}</if>"
            + "<if test=\"rp_name != null and rp_name != '' \">AND rp_name = #{rp_name}</if>"
            + "</where>"
            + "</script>")
	List<RentPlace> getRentPlaceByCondition(RentPlace rentPlace);
	
	@Select("select * from rent_place limit #{start}, #{pageSize}")
	List<RentPlace> getRentPlaceByPage(PageModel pageModel);
	
	@Insert("insert into rent_place values(#{rp_id}, #{rp_name}, #{rp_place}, #{rp_establish_time}, #{rp_mark})")
	void addRentPlace(RentPlace rentPlace);
	
	@Update("update rent_place set rp_name = #{rp_name}, rp_place = #{rp_place}, rp_establish_time = #{rp_establish_time}, rp_mark = #{rp_mark} where rp_id = #{rp_id}")
	void updateRentPlace(RentPlace rentPlace);
	
	@Delete("delete from rent_place where rp_id = #{rp_id}")
	void deleteRentPlace(RentPlace rentPlace);
}
