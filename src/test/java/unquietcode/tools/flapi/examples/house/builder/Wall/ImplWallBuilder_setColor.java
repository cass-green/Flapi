
package unquietcode.tools.flapi.examples.house.builder.Wall;

import unquietcode.tools.flapi.support.BuilderImplementation;

import javax.annotation.Generated;
import java.awt.*;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on April 19, 2013 18:33:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 19, 2013 18:33:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplWallBuilder_setColor
    implements WallBuilder_setColor<Object> , BuilderImplementation
{
    private final WallHelper _helper;
    private final Object _returnValue;

    public ImplWallBuilder_setColor(WallHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        if (_returnValue instanceof BuilderImplementation) {
            return ((BuilderImplementation) _returnValue);
        } else {
            return null;
        }
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public Object setColor(Color color) {
        _checkInvocations();
        _helper.setColor(color);
         
        return _returnValue;
    }
}