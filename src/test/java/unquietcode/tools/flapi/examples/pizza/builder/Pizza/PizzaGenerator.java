
package unquietcode.tools.flapi.examples.pizza.builder.Pizza;

import unquietcode.tools.flapi.examples.pizza.builder.Pizza.PizzaBuilder.$;

import javax.annotation.Generated;


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
public class PizzaGenerator {
    @SuppressWarnings("unchecked")
    public static $<Void> makePizza(PizzaHelper helper) {
        if (helper == null) {
            throw new IllegalArgumentException("Helper cannot be null.");
        }
         
        return (($) new ImplPizzaBuilder_addCheese_addSauce_addTopping$3(helper, null));
    }
}
