package unquietcode.tools.flapi.builder;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public interface MethodHelper {
	void once();
	void any();
	void last();
	void atLeast(int num);
	void atMost(int num);
	void between(int atLeast, int atMost);

	List<Object> addBlockChain();
}
