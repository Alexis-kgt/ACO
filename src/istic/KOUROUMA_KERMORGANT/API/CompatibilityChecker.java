package istic.KOUROUMA_KERMORGANT.API;

import java.util.Collection;

public interface CompatibilityChecker {
	
	Collection<PartType> getIncompatibilities(PartType pt);
	Collection<PartType> getRequirements(PartType pt);
	String toString();
}