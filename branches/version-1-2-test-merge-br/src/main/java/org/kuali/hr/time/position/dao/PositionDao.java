package org.kuali.hr.time.position.dao;

import org.kuali.hr.time.position.Position;

public interface PositionDao {
	public Position getPosition(String hrPositionId);
	public Position getPositionByPositionNumber(String hrPositionNbr);
}
