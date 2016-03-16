package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.javaetmoi.javabean.domain.Album;
import com.squareup.javapoet.MethodSpec;

/**
 * Sample of a custom {@link CodeGenerator}
 */
public class AlbumGenerator extends DefaultCodeGenerator<Album> {

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        Album album = getValue(param);
        Item releaseDate = param.getMarshaller().buildItem(album.getReleaseDate());
        String artistVarName = param.getMarshaller().getVariableName(album.getArtist());
        method.addStatement("$L.add(new $T(\"$L\", "+releaseDate.getPattern()+", $L))", param.getVarName(), param.getValueClass(), album.getName(), releaseDate.getVal(), artistVarName);
    }
}
