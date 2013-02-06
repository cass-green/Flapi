
package unquietcode.tools.flapi.builder.BlockChain;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Block.BlockHelper;
import unquietcode.tools.flapi.support.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 04, 2013 10:10:17 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 04, 2013 10:10:17 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockChainHelper {


    /**
     * add an additional link to the chain, occurring before this one
     * 
     */
    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    /**
     * add a reference to an existing block
     * 
     */
    void addBlockReference(String blockName);

    /**
     * create a new anonymous block (which cannot be referenced from anywhere)
     * 
     */
    void startBlock(ObjectWrapper<BlockHelper> _helper1);

    /**
     * create a new block
     * 
     */
    void startBlock(String blockName, ObjectWrapper<BlockHelper> _helper1);

}