package net.nueca.imonggo.interfaces;

import net.nueca.imonggo.enums.Modules;

public interface OnHttpRequestor {
	/**
	 * 
	 * What to before performing the request.
	 * 
	 * @param module
	 */
	public void preExecuteOperation(int module);
	
	/**
	 * 
	 * What should be the behavior when the request has finished.
	 * 
	 * @param module
	 * @param result
	 * @param hasResponse
	 */
	public void postExecuteOperation(int module, String result, boolean hasResponse);
}
