package istic.KOUROUMA_KERMORGANT.API;

public interface CompatibilityManager extends CompatibilityChecker{

	String afficheRequirements();
	String afficheIncompatibilities();
	void addIncompatibilities(PartType pt1, PartType pt2);
	void removeIncompatibilities(PartType pt1, PartType pt2);
	void addRequirements(PartType pt1, PartType pt2);
	void removeRequirements(PartType pt1, PartType pt2);
}
