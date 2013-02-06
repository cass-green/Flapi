
package unquietcode.tools.flapi.builder.Method;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.BlockChain.BlockChainHelper;
import unquietcode.tools.flapi.builder.Documentation.DocumentationHelper;
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
public interface MethodHelper {


    /**
     * Add a BlockChain, which is a block which must be passed through
     * before the current method returns.
     * 
     */
    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    /**
     * expect the method [0, inf) times
     * 
     */
    void any();

    /**
     * expect the method [X, inf) times
     * 
     */
    void atLeast(int num);

    /**
     * expect the method [0, X] times
     * 
     */
    void atMost(int num);

    /**
     * expect the method [0, X] times, and assign a group number
     * 
     */
    void atMost(int num, int group);

    /**
     * expect the method [atLeast, atMost] times
     * 
     */
    void between(int atLeast, int atMost);

    /**
     * expect the method [X, X] times
     * 
     */
    void exactly(int num);

    /**
     * mark the method as terminal, exiting the block when called
     * 
     */
    void last();

    /**
     * mark the method as terminal, returning an object of the given type when called
     * 
     */
    void last(Class returnType);

    /**
     * Marks this method with a Deprecated annotation.
     * Also adds a note to the Javadocs.
     * 
     */
    void markAsDeprecated(String reason);

    /**
     * Add javadoc style documentation to the method.
     * 
     */
    void withDocumentation(ObjectWrapper<DocumentationHelper> _helper1);

    /**
     * Add javadoc style documentation to the method.
     * 
     */
    void withDocumentation(String documentation);

}