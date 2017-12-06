package com.admin.insurance.model;
	import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lintas.admin.common.model.Timestampable;
	@Entity
	@Table(name="insurance_policy_temp")
	public class InsurancePolicyTemp extends Timestampable implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Column(name = "transaction_key", unique = true, nullable = false)
		private String transactionKey;
		@Column(name = "insurance_policydata" , columnDefinition = "LONGBLOB")
		private byte[] insurancePolicyData;
		
		 
		public String getTransactionKey() {
			return transactionKey;
		}
		public byte[] getInsurancePolicyData() {
			return insurancePolicyData;
		}
		 
		public void setTransactionKey(String transactionKey) {
			this.transactionKey = transactionKey;
		}
		public void setInsurancePolicyData(byte[] insurancePolicyData) {
			this.insurancePolicyData = insurancePolicyData;
		}
		
	}

 
