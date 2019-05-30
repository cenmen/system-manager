package com.liang.system.mapper;

import org.apache.ibatis.annotations.Select;

import com.liang.system.beans.Thority;

public interface ThorityMapper {
	@Select("select * from thority where id = #{id}")
	Thority getThor(Thority thority);
}
