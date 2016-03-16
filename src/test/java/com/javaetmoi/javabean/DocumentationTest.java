package com.javaetmoi.javabean;

import com.javaetmoi.javabean.domain.*;
import com.javaetmoi.javabean.generator.AlbumGenerator;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.Month;

public class DocumentationTest extends AbstractJavaBeanMarshallerTest {

    private AlbumGenerator artistGenerator = new AlbumGenerator();

    @After
    public void tearDown() {
        JavaBeanMarshaller.unregister(artistGenerator);
    }

    @Test
    public void readme() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Artist u2 = new Artist(1, "U2", ArtistType.GROUP);
        Album joshuaTree = new Album("The Joshua Tree", LocalDate.of(1987, Month.MARCH, 9), u2);
        u2.getAlbums().add(joshuaTree);
        executeTest(u2);
    }

    @Test
    public void customGenerator() {
        JavaBeanMarshaller.register(artistGenerator);
        Artist u2 = new Artist(1, "U2", ArtistType.GROUP);
        Album joshuaTree = new Album("The Joshua Tree", LocalDate.of(1987, Month.MARCH, 9), u2);
        u2.getAlbums().add(joshuaTree);
        executeTest(u2);
    }
}
