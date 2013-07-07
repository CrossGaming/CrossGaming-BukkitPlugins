/* **************************************************************************
 * @ Copyright 2004 by Brian Blank   										*
 * **************************************************************************
 * Module:	$Source: /cvsroot/webtranslator/source/src/com/javanetworkframework/patterns/threads/Barrier.java,v $
 * **************************************************************************
 * Java Web Translator Project												*
 * http://sourceforge.net/projects/webtranslator/							*
 * **************************************************************************
 * CVS INFORMATION															*
 * Current revision $Revision: 1.2 $
 * On branch $Name: A0-2 $
 * Latest change by $Author: xyombie $ on $Date: 2004/09/18 00:44:18 $
 * **************************************************************************
 * Modification History:													*
 * VERSION    DATE	 AUTHOR	DESCRIPTION OF CHANGE	    				    *
 * -------	-------- ------	------------------------------------------------*
 *  V1.00	09/17/04  BRB	Initial Version.								*
 * **************************************************************************
 */
package com.javanetworkframework.patterns.threads;

import java.util.logging.Logger;

/** Implements a Barrier where there are n spawned threads
 *  and one main thread waiting for all the spawned processes to complete.
 * 
 * @author  Brian Blank
 * @version 1.0
 */
public class Barrier {

	/* -------------------- STATIC SECTION --------------- */

	private static final Logger logger = 
		Logger.getLogger(Barrier.class.getName());

	/* -------------------- INSTANCE SECTION --------------- */

	/** Number of objects being waited on */
	private int counter;
	
	/** Constructor for Barrier
	 * 
	 * @param n Number of objects to wait on
	 */
	public Barrier(int n) {
		logger.entering(this.getClass().getName(), "Barrier", new Integer(n));
		
		counter = n;
		
		logger.exiting(this.getClass().getName(), "Barrier");
	}
	
	/** Wait for objects to complete */
	public synchronized void barrierWait() {
		logger.entering(this.getClass().getName(), "barrierWait");
		
		while(counter > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		logger.exiting(this.getClass().getName(), "barrierWait");
	}
	
	/** Object just completed */
	public synchronized void barrierPost() {
		logger.entering(this.getClass().getName(), "barrierPost");

		counter--;
		if(counter == 0) {
			notifyAll();
		}
		
		logger.exiting(this.getClass().getName(), "barrierPost");
	}
}
