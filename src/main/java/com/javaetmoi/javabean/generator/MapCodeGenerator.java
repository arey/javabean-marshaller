package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.javaetmoi.javabean.util.GenericsHelper;
import com.squareup.javapoet.MethodSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

public class MapCodeGenerator extends DefaultCodeGenerator<Map> {

    private final static Logger LOG = LoggerFactory.getLogger(MapCodeGenerator.class);

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        Map<?, ?> map = (Map<?, ?>) param.getValue();
        if (map.isEmpty()) {
            return;
        }
        if (param.getPropertyDescriptor() == null) {
            LOG.warn("propertyDescriptor is null for {}", param.getSetterName());
        }
        String mapName = param.getMarshaller().getOrGenerateVariableName(map, param);
        Type[] parameterizedTypes = GenericsHelper.findParameterizedTypes(param.getPropertyDescriptor());
        if ((parameterizedTypes != null) && (parameterizedTypes.length == 2)) {
            Type keyType = parameterizedTypes[0];
            Type valType = parameterizedTypes[1];
            method.addStatement("$T<$T, $T> $L = new $T<>()", param.getValueClass(), keyType, valType, mapName, param.getValueClass());
        } else {
            method.addStatement("$T $L = new $T()", param.getValueClass(), mapName, param.getValueClass());
        }

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Item keyItem = param.getMarshaller().buildItem(entry.getKey());
            Item valItem = param.getMarshaller().buildItem(entry.getValue());
            method.addStatement("$L.put(" + keyItem.getPattern() + ", " + valItem.getPattern() + ")", mapName, keyItem.getVal(), valItem.getVal());
        }
        method.addStatement("$L.$L($L)", param.getVarName(), param.getSetterName(), mapName);
    }


}
