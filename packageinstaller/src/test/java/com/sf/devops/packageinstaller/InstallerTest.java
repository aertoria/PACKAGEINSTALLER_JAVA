/**
 * 
 */
package com.sf.devops.packageinstaller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ethan
 *
 */
public class InstallerTest {
	private Installer installer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Map<String,ArrayList<String>> cnfg_cache=new HashMap<String,ArrayList<String>>();
        
        ArrayList<String> arrayP1=new ArrayList<String>(Arrays.asList("P2","P3"));
        cnfg_cache.put("P1",arrayP1);
        ArrayList<String> arrayP2=new ArrayList<String>(Arrays.asList("P3","P4","P5"));
        cnfg_cache.put("P2", arrayP2);
        ArrayList<String> arrayP4=new ArrayList<String>();
        cnfg_cache.put("P4",arrayP4);
        
        installer=new Installer(cnfg_cache); 
	}

	/*
	 * For makeDependency method
	 */
	@Test
	public void test_makeDependency() {
		installer.makeDependency("P1", null);
		assertTrue("inserting null dependency about an existing package should not inserting anything",installer.get_cnfg_cache().get("P1").size()==2);
		
		installer.makeDependency("P5", null);
		assertTrue("inserting null dependency about an new package should inserting new package",installer.get_cnfg_cache().get("P5").size()==0);
		
		installer.makeDependency("P6", "P1");
		installer.makeDependency("P6", "P2");
		assertTrue("inserting [P1 P2] dependency about an new package should inserting new package",
				installer.get_cnfg_cache().get("P6").size()==2);
		assertTrue("inserting [P1 P2] dependency about an new package should contains P1 P2 as its dependencies",
				installer.get_cnfg_cache().get("P6").contains("P1")&&installer.get_cnfg_cache().get("P6").contains("P2"));
			
		installer.makeDependency("P1", "P4");
		installer.makeDependency("P1", "P5");
		assertTrue("inserting [P4 P5] dependency about an existing package P1 should editing the exiting dependencies",
				installer.get_cnfg_cache().get("P1").size()==4
				&& installer.get_cnfg_cache().get("P1").contains("P4")
				&& installer.get_cnfg_cache().get("P1").contains("P5")
				&& installer.get_cnfg_cache().get("P1").contains("P2")
				&& installer.get_cnfg_cache().get("P1").contains("P3"));	
	}
}
