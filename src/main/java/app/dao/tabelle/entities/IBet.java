package app.dao.tabelle.entities;

import app.dao.tipologiche.entities.BetHouse;
import app.dao.tipologiche.entities.TimeType;

public interface IBet {
	void setBetHouse(BetHouse betHouseString);
	void setTimeType(TimeType timeTypeString);
}
