error id: jar:file://<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/arrow/arrow-vector/12.0.1/arrow-vector-12.0.1-sources.jar!/codegen/templates/ComplexCopier.java
file://<WORKSPACE>/jar:file:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/arrow/arrow-vector/12.0.1/arrow-vector-12.0.1-sources.jar!/codegen/templates/ComplexCopier.java
### java.lang.Exception: Unexpected symbol '#' at word pos: '35' Line: '  <#list  vv.types as type><#list type.minor as minor><#assign name = minor.class?cap_first />'

Java indexer failed with and exception.
```Java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.arrow.vector.complex.impl.UnionMapReader;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.types.Types;

<@pp.dropOutputFile />
<@pp.changeOutputFile name="/org/apache/arrow/vector/complex/impl/ComplexCopier.java" />


<#include "/@includes/license.ftl" />

package org.apache.arrow.vector.complex.impl;

<#include "/@includes/vv_imports.ftl" />

/*
 * This class is generated using freemarker and the ${.template_name} template.
 */
@SuppressWarnings("unused")
public class ComplexCopier {

  /**
   * Do a deep copy of the value in input into output
   * @param input field to read from
   * @param output field to write to
   */
  public static void copy(FieldReader input, FieldWriter output) {
    writeValue(input, output);
  }

  private static void writeValue(FieldReader reader, FieldWriter writer) {
    final MinorType mt = reader.getMinorType();

      switch (mt) {

      case LIST:
      case LARGELIST:
      case FIXED_SIZE_LIST:
        if (reader.isSet()) {
          writer.startList();
          while (reader.next()) {
            FieldReader childReader = reader.reader();
            FieldWriter childWriter = getListWriterForReader(childReader, writer);
            if (childReader.isSet()) {
              writeValue(childReader, childWriter);
            } else {
              childWriter.writeNull();
            }
          }
          writer.endList();
        } else {
          writer.writeNull();
        }
        break;
      case MAP:
        if (reader.isSet()) {
          UnionMapReader mapReader = (UnionMapReader) reader;
          writer.startMap();
          while (mapReader.next()) {
            FieldReader structReader = reader.reader();
            if (structReader.isSet()) {
              writer.startEntry();
              writeValue(mapReader.key(), getMapWriterForReader(mapReader.key(), writer.key()));
              writeValue(mapReader.value(), getMapWriterForReader(mapReader.value(), writer.value()));
              writer.endEntry();
            } else {
              writer.writeNull();
            }
          }
          writer.endMap();
        } else {
          writer.writeNull();
        }
        break;
      case STRUCT:
        if (reader.isSet()) {
          writer.start();
          for(String name : reader){
            FieldReader childReader = reader.reader(name);
            if (childReader.getMinorType() != Types.MinorType.NULL) {
              FieldWriter childWriter = getStructWriterForReader(childReader, writer, name);
              if (childReader.isSet()) {
                writeValue(childReader, childWriter);
              } else {
                childWriter.writeNull();
              }
            }
          }
          writer.end();
        } else {
          writer.writeNull();
        }
        break;
  <#list vv.types as type><#list type.minor as minor><#assign name = minor.class?cap_first />
  <#assign fields = minor.fields!type.fields />
  <#assign uncappedName = name?uncap_first/>

  <#if !minor.typeParams?? || minor.class?starts_with("Decimal") >

      case ${name?upper_case}:
        if (reader.isSet()) {
          Nullable${name}Holder ${uncappedName}Holder = new Nullable${name}Holder();
          reader.read(${uncappedName}Holder);
          if (${uncappedName}Holder.isSet == 1) {
            writer.write${name}(<#list fields as field>${uncappedName}Holder.${field.name}<#if field_has_next>, </#if></#list><#if minor.class?starts_with("Decimal")>, new ArrowType.Decimal(${uncappedName}Holder.precision, ${uncappedName}Holder.scale, ${name}Holder.WIDTH * 8)</#if>);
          }
        } else {
          writer.writeNull();
        }
        break;

  </#if>
  </#list></#list>
      }
 }

  private static FieldWriter getStructWriterForReader(FieldReader reader, StructWriter writer, String name) {
    switch (reader.getMinorType()) {
    <#list vv.types as type><#list type.minor as minor><#assign name = minor.class?cap_first />
    <#assign fields = minor.fields!type.fields />
    <#assign uncappedName = name?uncap_first/>
    <#if !minor.typeParams??>
    case ${name?upper_case}:
      return (FieldWriter) writer.<#if name == "Int">integer<#else>${uncappedName}</#if>(name);
    </#if>
    <#if minor.class?starts_with("Decimal")>
    case ${name?upper_case}:
      if (reader.getField().getType() instanceof ArrowType.Decimal) {
        ArrowType.Decimal type = (ArrowType.Decimal) reader.getField().getType();
        return (FieldWriter) writer.${uncappedName}(name, type.getScale(), type.getPrecision());
      } else {
        return (FieldWriter) writer.${uncappedName}(name);
      }
    </#if>
    
    </#list></#list>
    case STRUCT:
      return (FieldWriter) writer.struct(name);
    case FIXED_SIZE_LIST:
    case LIST:
      return (FieldWriter) writer.list(name);
    case MAP:
      return (FieldWriter) writer.map(name);
    default:
      throw new UnsupportedOperationException(reader.getMinorType().toString());
    }
  }

  private static FieldWriter getListWriterForReader(FieldReader reader, ListWriter writer) {
    switch (reader.getMinorType()) {
    <#list vv.types as type><#list type.minor as minor><#assign name = minor.class?cap_first />
    <#assign fields = minor.fields!type.fields />
    <#assign uncappedName = name?uncap_first/>
    <#if !minor.typeParams?? || minor.class?starts_with("Decimal") >
    case ${name?upper_case}:
    return (FieldWriter) writer.<#if name == "Int">integer<#else>${uncappedName}</#if>();
    </#if>
    </#list></#list>
    case STRUCT:
      return (FieldWriter) writer.struct();
    case FIXED_SIZE_LIST:
    case LIST:
    case MAP:
    case NULL:
      return (FieldWriter) writer.list();
    default:
      throw new UnsupportedOperationException(reader.getMinorType().toString());
    }
  }

  private static FieldWriter getMapWriterForReader(FieldReader reader, MapWriter writer) {
    switch (reader.getMinorType()) {
    <#list vv.types as type><#list type.minor as minor><#assign name = minor.class?cap_first />
    <#assign fields = minor.fields!type.fields />
    <#assign uncappedName = name?uncap_first/>
    <#if !minor.typeParams?? || minor.class?starts_with("Decimal") >
      case ${name?upper_case}:
      return (FieldWriter) writer.<#if name == "Int">integer<#else>${uncappedName}</#if>();
    </#if>
    </#list></#list>
      case STRUCT:
        return (FieldWriter) writer.struct();
      case FIXED_SIZE_LIST:
      case LIST:
      case NULL:
        return (FieldWriter) writer.list();
      case MAP:
        return (FieldWriter) writer.map(false);
      default:
        throw new UnsupportedOperationException(reader.getMinorType().toString());
    }
  }
}

```


#### Error stacktrace:

```
scala.meta.internal.mtags.JavaToplevelMtags.unexpectedCharacter(JavaToplevelMtags.scala:352)
	scala.meta.internal.mtags.JavaToplevelMtags.parseToken$1(JavaToplevelMtags.scala:253)
	scala.meta.internal.mtags.JavaToplevelMtags.fetchToken(JavaToplevelMtags.scala:262)
	scala.meta.internal.mtags.JavaToplevelMtags.loop(JavaToplevelMtags.scala:73)
	scala.meta.internal.mtags.JavaToplevelMtags.indexRoot(JavaToplevelMtags.scala:42)
	scala.meta.internal.mtags.MtagsIndexer.index(MtagsIndexer.scala:21)
	scala.meta.internal.mtags.MtagsIndexer.index$(MtagsIndexer.scala:20)
	scala.meta.internal.mtags.JavaToplevelMtags.index(JavaToplevelMtags.scala:18)
	scala.meta.internal.mtags.Mtags.indexWithOverrides(Mtags.scala:74)
	scala.meta.internal.mtags.SymbolIndexBucket.indexSource(SymbolIndexBucket.scala:129)
	scala.meta.internal.mtags.SymbolIndexBucket.addSourceFile(SymbolIndexBucket.scala:108)
	scala.meta.internal.mtags.SymbolIndexBucket.$anonfun$addSourceJar$2(SymbolIndexBucket.scala:74)
	scala.collection.immutable.List.flatMap(List.scala:294)
	scala.meta.internal.mtags.SymbolIndexBucket.$anonfun$addSourceJar$1(SymbolIndexBucket.scala:70)
	scala.meta.internal.io.PlatformFileIO$.withJarFileSystem(PlatformFileIO.scala:77)
	scala.meta.internal.io.FileIO$.withJarFileSystem(FileIO.scala:33)
	scala.meta.internal.mtags.SymbolIndexBucket.addSourceJar(SymbolIndexBucket.scala:68)
	scala.meta.internal.mtags.OnDemandSymbolIndex.$anonfun$addSourceJar$2(OnDemandSymbolIndex.scala:85)
	scala.meta.internal.mtags.OnDemandSymbolIndex.tryRun(OnDemandSymbolIndex.scala:131)
	scala.meta.internal.mtags.OnDemandSymbolIndex.addSourceJar(OnDemandSymbolIndex.scala:84)
	scala.meta.internal.metals.Indexer.indexJar(Indexer.scala:561)
	scala.meta.internal.metals.Indexer.addSourceJarSymbols(Indexer.scala:555)
	scala.meta.internal.metals.Indexer.$anonfun$indexDependencySources$5(Indexer.scala:381)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:619)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:617)
	scala.collection.AbstractIterable.foreach(Iterable.scala:935)
	scala.collection.IterableOps$WithFilter.foreach(Iterable.scala:905)
	scala.meta.internal.metals.Indexer.$anonfun$indexDependencySources$1(Indexer.scala:372)
	scala.meta.internal.metals.Indexer.$anonfun$indexDependencySources$1$adapted(Indexer.scala:371)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:619)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:617)
	scala.collection.AbstractIterable.foreach(Iterable.scala:935)
	scala.meta.internal.metals.Indexer.indexDependencySources(Indexer.scala:371)
	scala.meta.internal.metals.Indexer.$anonfun$indexWorkspace$19(Indexer.scala:192)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.meta.internal.metals.TimerProvider.timedThunk(TimerProvider.scala:25)
	scala.meta.internal.metals.Indexer.$anonfun$indexWorkspace$18(Indexer.scala:185)
	scala.meta.internal.metals.Indexer.$anonfun$indexWorkspace$18$adapted(Indexer.scala:181)
	scala.collection.immutable.List.foreach(List.scala:334)
	scala.meta.internal.metals.Indexer.indexWorkspace(Indexer.scala:181)
	scala.meta.internal.metals.Indexer.$anonfun$profiledIndexWorkspace$2(Indexer.scala:57)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.meta.internal.metals.TimerProvider.timedThunk(TimerProvider.scala:25)
	scala.meta.internal.metals.Indexer.$anonfun$profiledIndexWorkspace$1(Indexer.scala:57)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:687)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:467)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	java.base/java.lang.Thread.run(Thread.java:840)
```
#### Short summary: 

Java indexer failed with and exception.