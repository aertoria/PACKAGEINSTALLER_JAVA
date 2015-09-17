package com.sf.devops.packageinstaller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*
 *Insaller calss 
 *coded this for SF task
 */

public class Installer{
	private Map<String,ArrayList<String>> cnfg_cache;
	public Map<String,ArrayList<String>> get_cnfg_cache(){
		return this.cnfg_cache;
	}
	
	
	
	public Installer(Map<String,ArrayList<String>> cnfg_cache){
		this.cnfg_cache=cnfg_cache;
	}
	
	//MakeDependency(component1, component2) // makes component1 dependent on component2
	public void makeDependency(String component1,String component2){
		if (component2 == null){
			if (cnfg_cache.get(component1) == null){
				ArrayList<String> emptylist = new ArrayList<String>();
				cnfg_cache.put(component1, emptylist);
			}
		}else{
			if (cnfg_cache.get(component1)==null){
				ArrayList<String> newlist = new ArrayList<String>(Arrays.asList(component2));
				cnfg_cache.put(component1,newlist);
			}else{
				cnfg_cache.get(component1).add(component2);
			}
		}	
	}
	

}
