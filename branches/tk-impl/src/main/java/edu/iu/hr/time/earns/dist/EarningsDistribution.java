package edu.iu.hr.time.earns.dist;

import java.math.BigDecimal;

public class EarningsDistribution {
	private String earnCode;
	private String earnCode2;
	private BigDecimal distPct;
	private BigDecimal distPct2;
	
	public String getEarnCode() {
		return earnCode;
	}
	public void setEarnCode(String earnCode) {
		this.earnCode = earnCode;
	}
	public String getEarnCode2() {
		return earnCode2;
	}
	public void setEarnCode2(String earnCode2) {
		this.earnCode2 = earnCode2;
	}
	public BigDecimal getDistPct() {
		return distPct;
	}
	public void setDistPct(BigDecimal distPct) {
		this.distPct = distPct;
	}
	public BigDecimal getDistPct2() {
		return distPct2;
	}
	public void setDistPct2(BigDecimal distPct2) {
		this.distPct2 = distPct2;
	}

}
