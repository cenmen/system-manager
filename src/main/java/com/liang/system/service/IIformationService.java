package com.liang.system.service;

import java.util.List;

import com.liang.system.beans.Information;


public interface IIformationService {
	
	List<Information> getInformations();
	
	void updateInformationStatus(int info_id);
}
