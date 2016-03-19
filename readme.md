# JavaBean Marshaller #

The **JavaBeanMarshaller utility class** is responsible for **serializing an object (graph) to it's Java code**.
The objects from the graph have to follow the **JavaBean convention**.
And by Java code, we talk calling getters and setters of all the object graph.

The main purpose of this tools is to help developer to create realistic data sets or stubs for their unit tests.
Instead of using XML or JSON serialization, dataset are generated into the Java language. This format is more appropriate for performance and refactoring purpose.

## Supports ##

* Primitive types and their wrapper classes
* Enumeration
* Collection and Map
* Arrays (1 and 2 dimensions)
* java.util.Date, java.sql.Date, Calendar, XMLGregorianCalendar
* Java 8 Date & Time API (JSR-310)
* JodaTime
* Unidirectional and bidirectionnal relationships
* Cyclic graph


## Example ##

Let's imagine an in memory artist instance with a single album.
![UML class diagramm](https://raw.githubusercontent.com/arey/javabean-marshaller/master/src/test/java/com/javaetmoi/javabean/domain/artist.png "UML class diagramm")

Call the ```generateJavaCode``` method:

```
JavaBeanMarshaller.generateJavaCode(artist);
```

At the working directory, the java class _ArtistFactory.java_ is generated:

```
import com.javaetmoi.javabean.domain.Album;
import com.javaetmoi.javabean.domain.Artist;
import com.javaetmoi.javabean.domain.ArtistType;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class ArtistFactory {
  public static Artist newArtist() {
    Artist artist1 = new Artist();
    ArrayList<Album> albums1 = new ArrayList<>();
    Album album1 = new Album();
    album1.setArtist(artist1);
    album1.setReleaseDate(LocalDate.of(1987, Month.MARCH, 9));
    album1.setName("The Joshua Tree");
    albums1.add(album1);
    artist1.setAlbums(albums1);
    artist1.setName("U2");
    artist1.setId(1L);
    artist1.setType(ArtistType.GROUP);
    return artist1;
  }
}
```

## Pre-requisites ##

* Java 7 or above
* Your JavaBeans should have of a no-arg constructors and getter/setter

## Quick Start ##

Download the jar though Maven:

```xml
<dependency>
  <groupId>com.javaetmoi.util</groupId>
  <artifactId>javaetmoi-javabean-marshaller</artifactId>
  <version>1.0.0</version>
</dependency>
```

JavaBean Marshaller artefacts are available from [Maven Central](http://repo1.maven.org/maven2/com/javaetmoi/util/javaetmoi-javabean-marshaller/)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.javaetmoi.util/javaetmoi-javabean-marshaller/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.javaetmoi.util/javaetmoi-javabean-marshaller)

In your application code, select a point o add the ```generateJavaCode``` method call:
```
JavaBeanMarshaller.generateJavaCode(myBean);
```

Run your application and play the scenario.


To support a new custome type, you have to implement the ```CodeGenerator``` interface then call the ```JavaBeanMarshaller::addCodeGenerator``` method.


## Getting Help ##

This readme.md file contains the main documentation.
There are also unit tests available to look at.


## Contributing to JavaBean Marshaller ##

Github is for social coding platform: if you want to write code, we encourage contributions through pull requests from [forks of this repository](https://help.github.com/articles/fork-a-repo/). If you want to contribute code this way, please reference a GitHub ticket as well covering the specific issue you are addressing.
Before submitting your pull request, don't forget to write a corresponding unit test.


### Development environment installation ###

Download the code with git:

``git clone git://github.com/arey/javabean-marshaller.git``

Compile the code with maven:

``mvn clean install``

If you're using an IDE that supports Maven-based projects (InteliJ Idea, Netbeans or m2Eclipse), you can import the project directly from its POM. 
Otherwise, generate IDE metadata with the related IDE maven plugin:

``mvn eclipse:clean eclipse:eclipse``

## Release Note ##

<table>
  <tr>
    <th>Version</th><th>Release date</th><th>Features</th>
  </tr>
  <tr>
    <td>1.0.0</td><td>2016-03-19</td><td>Initial release</td>
  </tr>
</table>


## Credits ##

* [Javapoet](https://github.com/square/javapoet) from Square and [Apache Common BeanUtils](https://commons.apache.org/proper/commons-beanutils/)
* Uses [Maven](http://maven.apache.org/) as a build tool
* Uses [Travis CI](http://www.travis-ci.org) for continuous integration builds whenever code is pushed into GitHub


## Build Status ##

* Travis: [![Build
Status](https://travis-ci.org/arey/javabean-marshaller.png?branch=master)](https://travis-ci.org/arey/javabean-marshaller)
* Coverage [![Coverage Status](https://coveralls.io/repos/github/arey/javabean-marshaller/badge.svg?branch=master)](https://coveralls.io/github/arey/javabean-marshaller?branch=master)

