/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.Pair;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.Transition;

/**
 * @author Ben Fagin
 * @version 08-06-2012
 */
public abstract class AbstractGenerator {
	protected AbstractGenerator(GeneratorContext ctx) {
		this.ctx = ctx;
	}

	protected final GeneratorContext ctx;

	// ------- type creation strategies ------- //

	protected static final TypeCreationStrategy BUILDER_CLASS_STRATEGY = new BuilderClassTypeCreationStrategy();

	protected static final TypeCreationStrategy BUILDER_INTERFACE_STRATEGY = new TypeCreationStrategy() {
		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			String name = ctx.getGeneratedName("", "Builder", state);
			JDefinedClass _interface = ctx.getOrCreateInterface(state.getName(), name);

			if (_interface.typeParams().length == 0) {
				_interface.generify(Constants.RETURN_TYPE_NAME);
			}

			return _interface;
		}
	};

	protected static final class BUILDER_NARROWED_INTERFACE_STRATEGY {
		public static JClass createType(GeneratorContext ctx, StateClass state) {
			JClass type = BUILDER_INTERFACE_STRATEGY.createType(ctx, state);
			return type.narrow(type.typeParams()[0]);
		}
	};

	protected static final TypeCreationStrategy HELPER_INTERFACE_STRATEGY = new TypeCreationStrategy() {
		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			return ctx.getOrCreateInterface(state.getName(), state.getName()+"Helper");
		}
	};

	protected static final TypeCreationStrategy GENERATOR_CLASS_STRATEGY = new TypeCreationStrategy() {
		public JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			String name = state.getName()+"Generator";
			return ctx.getOrCreateClass(state.getName(), name);
		}
	};

	public static final TypeCreationStrategy WRAPPER_INTERFACE_STRATEGY = new TypeCreationStrategy() {
		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			String name = state.getName()+"Builder";
			JDefinedClass parent = ctx.getOrCreateInterface(state.getName(), name);
			JDefinedClass innerClass;

			try {
				innerClass = parent._interface("$");
			} catch (JClassAlreadyExistsException ex) {
				return ex.getExistingClass();
			}

			JDefinedClass builder = BUILDER_INTERFACE_STRATEGY.createType(ctx, state);
			JTypeVar tv = innerClass.generify(Constants.RETURN_TYPE_NAME);
			innerClass._extends(builder.narrow(tv));
			return innerClass;
		}
	};

	public static final TypeCreationStrategy NULL_STRATEGY = new TypeCreationStrategy() {
		public @Override JDefinedClass createType(GeneratorContext ctx, StateClass state) {
			return null;
		}
	};

	// -------------------------------- //

	protected String makeMethodKey(Transition transition) {
		StringBuilder sb = new StringBuilder();
		sb.append(transition.getOwner().getName()).append("_")
		  .append(makeMethodKey(transition.getMethodSignature()));

		return sb.toString();
	}

	public static String makeMethodKey(String methodSignature) {
		StringBuilder sb = new StringBuilder();
		MethodParser parser = new MethodParser(methodSignature);

		sb.append(parser.methodName).append("$");
		boolean first = true;

		for (Pair<String, String> param : parser.params) {
			if (!first) { sb.append("$"); }
			else { first = false; }

			sb.append(param.first).append("_").append(param.second);
		}

		return sb.toString();
	}


	protected JClass ref(Class clazz) {
		return ctx.model.ref(clazz);
	}

	protected JClass ref(String FQCN) {
		return ctx.model.ref(FQCN);
	}

	protected JType getType(String name) {
		JType clazz;

		dance: {
			try {
				Class c = Thread.currentThread().getContextClassLoader().loadClass(name);
				clazz = ref(c);
				break dance;
			} catch (ClassNotFoundException ex) {
				// nothing
			}

			// try java.lang package
			try {
				Class c = Thread.currentThread().getContextClassLoader().loadClass("java.lang."+name);
				clazz = ref(c);
				break dance;
			} catch (ClassNotFoundException ex) {
				// nothing
			}

			// try java.util package
			try {
				Class c = Thread.currentThread().getContextClassLoader().loadClass("java.util."+name);
				clazz = ref(c);
				break dance;
			} catch (ClassNotFoundException ex) {
				// nothing
			}

			// Maybe it's primitive, or just not visible. Let the code model decide.
			clazz = ref(name);
		}

		return clazz;
	}

	protected JMethod addMethod(JDefinedClass _class, JType returnType, int mods, Transition transition) {
		MethodParser parsed = new MethodParser(transition.getMethodSignature());
		JMethod m = _class.method(mods, returnType, parsed.methodName);

		// regular params
		for (Pair<String, String> entry : parsed.params) {
			JType clazz = getType(entry.first);
			m.param(clazz, entry.second);
		}

		// varargs
		if (parsed.varargType != null) {
			JType clazz = getType(parsed.varargType);
			m.varParam(clazz, parsed.varargName);
		}

		// documentation
		if (transition.info().getDocumentation() != null) {
			m.javadoc().append(transition.info().getDocumentation());
		}

		// deprecation
		if (transition.info().isDeprecated()) {
			m.annotate(Deprecated.class);
			JCommentPart doc = m.javadoc().addDeprecated();

			String docString = transition.info().getDeprecationReason();
			if (docString != null && !(docString = docString.trim()).isEmpty()) {
				doc.append(docString);
			}
		}

		return m;
	}
}
