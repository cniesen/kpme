package edu.iu.uis.hr.tk.pool.web;

import java.util.List;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;

public class TKDataPoolForm extends AbstractStrutsActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -212495334055967127L;

	private int freeConnections;
	private int usedConnections;
	private int poolSize;
	private int minConnections;
	private int maxConnections;
	public void prepare() {
		// TODO Auto-generated method stub

	}

	public List getAccessAuthorizations() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getFreeConnections() {
		return freeConnections;
	}

	public void setFreeConnections(int freeConnections) {
		this.freeConnections = freeConnections;
	}

	public int getUsedConnections() {
		return usedConnections;
	}

	public void setUsedConnections(int usedConnections) {
		this.usedConnections = usedConnections;
	}

	
	public int getMinConnections() {
		return minConnections;
	}

	public void setMinConnections(int minConnections) {
		this.minConnections = minConnections;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}


}
