package istic.KOUROUMA_KERMORGANT.Impl;

import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import istic.KOUROUMA_KERMORGANT.API.CompatibilityManager;
import istic.KOUROUMA_KERMORGANT.API.PartType;

public class CompatibilityManagerImpl implements CompatibilityManager {

	private Map <PartType, Collection<PartType>> mIncomp = new HashMap<PartType, Collection<PartType>>();
	private Map <PartType, Collection<PartType>> mRequire = new HashMap<PartType, Collection<PartType>>();
	
	private boolean ifExistInIncomp(PartType pt1,PartType pt2) {
		boolean result=false;
		if(pt1!=null && pt2!=null) {
			if(mIncomp.containsKey(pt1)) {
				Collection<PartType> Incomppt1 =mIncomp.get(pt1);
				if(Incomppt1.contains(pt2)) {
					result=true;
				}
			}
		}
		return result;
	}
	private boolean ifExistInRequirements(PartType pt1,PartType pt2) {
		boolean result=false;
		if(pt1!=null && pt2!=null) {
			if(mRequire.containsKey(pt1)) {
				Collection<PartType> requirePt1 =mRequire.get(pt1);
				if(requirePt1.contains(pt2)) {
					result=true;
				}
			}
			else if(mRequire.containsKey(pt2)) {
				Collection<PartType> requirePt2 =mRequire.get(pt1);
				if(requirePt2.contains(pt1)) {
					result=true;
				}
			}
		}
		return result;
	}
	
	public void addIncompatibilities(PartType pt1,PartType pt2) {
		// instance of class need be done to run this code
		if(pt1!=null && pt2!=null) {
			if(!ifExistInRequirements(pt1, pt2)) {
				if(mIncomp.containsKey(pt1)) {
					mIncomp.get(pt1).add(pt2);
					if(mIncomp.containsKey(pt2)) {
						mIncomp.get(pt2).add(pt1);
					}
					else {
						Collection<PartType> tmp2= new HashSet<>();
						tmp2.add(pt1);
						mIncomp.put(pt2, tmp2);
					}
				}
				else {
					Collection<PartType> tmp= new HashSet<>();
					tmp.add(pt2);
					mIncomp.put(pt1, tmp);
					if(mIncomp.containsKey(pt2)) {
						mIncomp.get(pt2).add(pt1);
					}
					else {
						Collection<PartType> tmp2= new HashSet<>();
						tmp2.add(pt1);
						mIncomp.put(pt2, tmp2);
					}
				}
			}
			else {
				Logger.getGlobal().log(Level.INFO, "exist in Requirement"+pt1.toString()+"-->"+pt2.toString()+"\n");
			}
		}
	}
	public void removeIncompatibilities(PartType pt1,PartType pt2) {
		Map<PartType,Collection<PartType>> m=mIncomp;
		if(pt1!=null && pt2!=null) {
			if (mIncomp.containsKey(pt1)) {
				Collection<PartType> requirept1 =mIncomp.get(pt1);
				if (requirept1.contains(pt2)) {
					requirept1.remove(pt2);
					if(requirept1.size()==0) {
						mIncomp.remove(pt1);
					}
					Collection<PartType> requirept2 =mIncomp.get(pt2);
					requirept2.remove(pt1);
					if(requirept2.size()==0) {
						mIncomp.remove(pt2);
					}
				}
			}
		}
	}
	public void addRequirements(PartType pt1,PartType pt2) {
		// instance of class need be done to run this code
		// pt1 require pt2
		if(pt1!=null && pt2!=null) {
			if(!ifExistInIncomp(pt1, pt2)) {
				if(mRequire.containsKey(pt1)) {
					Collection<PartType> requirept1 =mRequire.get(pt1);
					requirept1.add(pt2);
					// transitivity
					for(Entry<PartType, Collection<PartType>> entry : mRequire.entrySet()) {
						PartType pt3 = entry.getKey();
						Collection<PartType> listpt3 = entry.getValue();
						// pt1 require pt2 and pt3 require pt1 => pt3 require pt2
					    if(listpt3.contains(pt1)) {
					    	listpt3.add(pt2);
					    }
					    // pt1 require pt2 and pt2 require listpt3 => pt1 require listpt3
					    else if(pt3==pt2) {
					    	requirept1.addAll(listpt3);
					    }
					}
				}
				else {
					Collection<PartType> tmp= new HashSet<PartType>();
					tmp.add(pt2);
					mRequire.put(pt1, tmp);
					// transitivity
					for(Entry<PartType, Collection<PartType>> entry : mRequire.entrySet()) {
						PartType pt3 = entry.getKey();
						Collection<PartType> listpt3 = entry.getValue();
						// pt1 require pt2 and pt3 require pt1 => pt3 require pt2
					    if(listpt3.contains(pt1) && pt2!=pt3) {
					    	listpt3.add(pt2);
					    }
					    // pt1 require pt2 and pt2 require listpt3 => pt1 require listpt3
					    else if(pt3==pt2) {
					    	for (PartType e: listpt3) {
					    		if(e!=pt1) {
							    	tmp.add(e);
					    		}
					    	}
					    }
					}
				}
			}
			else {
				Logger.getGlobal().log(Level.INFO, "exist in Requirement"+pt1.toString()+"-->"+pt2.toString()+"\n");
			}
		}
	}
	public void removeRequirements(PartType pt1,PartType pt2) {
		if(pt1!=null && pt2!=null) {
			if (mRequire.containsKey(pt1)) {
				Collection<PartType> requirept1 =mRequire.get(pt1);
				if (requirept1.contains(pt2)) {
					requirept1.remove(pt2);
					if(requirept1.size()==0) {
						mRequire.remove(pt1);
					}
					for(Entry<PartType, Collection<PartType>> entry : mRequire.entrySet()) {
						PartType pt3 = entry.getKey();
						Collection<PartType> listpt3 = entry.getValue();
						// pt1 require pt2 and pt3 require pt1 => pt3 require pt2
					    if(listpt3.contains(pt1)) {
					    	listpt3.remove(pt2);
					    	if(listpt3.size()==0) {
								mRequire.remove(pt3);
							}
					    }
					}
				}
			}
		}
	}
	@Override
	public Collection<PartType> getIncompatibilities(PartType pt) {
		// TODO Auto-generated method stub
		Collection<PartType> result=new HashSet<PartType>();
		if(pt!=null) {
			result = mIncomp.get(pt);
			if(result!=null) {
				return new HashSet<PartType>(result);
			}
		}
		return new HashSet<PartType>();
	}

	@Override
	public Collection<PartType> getRequirements(PartType pt) {
		Collection<PartType> result=new HashSet<PartType>();
		if(pt!=null) {
			result = mRequire.get(pt);
			if(result!=null) {
				return new HashSet<PartType>(result);
			}
		}
		return new HashSet<PartType>();
	}
	@Override
	public String afficheRequirements() {
		// TODO Auto-generated method stub
		String result="---------------Requirements-----------------\n(";
		for(Entry<PartType, Collection<PartType>> entry : mRequire.entrySet()) {
			PartType pt = entry.getKey();
			Collection<PartType> list = entry.getValue();
			result=result+pt.toString()+"--> {";
			for (PartType e: list) {
				result =result+e.toString()+",";
			}
			result=result+"}\n";
		}
		result=result+")---------------Requirements-----------------\n";
		return result;
	}
	@Override
	public String afficheIncompatibilities() {
		// TODO Auto-generated method stub
		String result="---------------incompatibilies-----------------\n(";
		for(Entry<PartType, Collection<PartType>> entry : mIncomp.entrySet()) {
			PartType pt = entry.getKey();
			Collection<PartType> list = entry.getValue();
			result=result+pt.toString()+"--> {";
			for (PartType e: list) {
				result =result+e.toString()+",";
			}
			result=result+"}\n";
		}
		result=result+")---------------incompatibilies-----------------\n";
		return result;
	}
}