package com.liang.system.beans;

import java.sql.Date;
import lombok.Data;

@Data
public class BicyclePurchaseBatch {
	private int bp_id_batch;
	private String bp_brand_batch;
	private String bp_model_batch;
	private Date bp_dateOfProduction_batch;
	private Date bp_dateOfPurchase_batch;
	private boolean bp_isallocated_batch;
	private String bp_mark_batch;
}
