
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;

public interface BlockChainHelper {


    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void startBlock(String methodSignature, String blockName, ObjectWrapper<BlockHelper> _helper1);

    void addBlockReference(String blockName);

}
