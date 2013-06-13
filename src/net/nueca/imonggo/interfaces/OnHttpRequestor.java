package net.nueca.imonggo.interfaces;

import net.nueca.imonggo.enums.Modules;

public interface OnHttpRequestor {
	public void preExecuteOperation(Modules module);
	public void postExecuteOperation(Modules module, String result, boolean hasResponse);
}
