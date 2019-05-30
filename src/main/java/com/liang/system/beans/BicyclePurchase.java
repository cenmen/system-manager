package com.liang.system.beans;

import java.sql.Date;
import lombok.Data;

@Data
public class BicyclePurchase {
	private String bp_id;
	private String bp_brand;
	private String bp_model;
	private Date bp_dateOfProduction;
	private Date bp_dateOfPurchase;
	private boolean bp_isallocated;
	private String bp_mark;
}
