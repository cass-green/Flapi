
package unquietcode.tools.flapi.builder;


public interface DescriptorBuilder_setDescriptorName_setReturnType<_ReturnType >{


    MethodBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setReturnType<_ReturnType>> addMethod(String methodSignature);

    _ReturnType build();

    MethodBuilder_addBlockChain<BlockBuilder_addBlockChain<DescriptorBuilder_setDescriptorName_setReturnType<_ReturnType>>> startBlock(String blockName, String methodSignature);

    DescriptorBuilder_setReturnType<_ReturnType> setDescriptorName(String descriptorName);

    DescriptorBuilder_setDescriptorName<_ReturnType> setReturnType(Class returnType);

}
