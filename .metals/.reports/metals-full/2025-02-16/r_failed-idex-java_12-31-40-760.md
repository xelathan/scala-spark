error id: jar:file://<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/arrow/arrow-vector/12.0.1/arrow-vector-12.0.1-sources.jar!/codegen/templates/BaseWriter.java
file://<WORKSPACE>/jar:file:<HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/arrow/arrow-vector/12.0.1/arrow-vector-12.0.1-sources.jar!/codegen/templates/BaseWriter.java
### java.lang.Exception: Unexpected symbol '#' at word pos: '35' Line: '    <#list  vv.types as type><#list type.minor as minor>'

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

<@pp.dropOutputFile />
<@pp.changeOutputFile name="/org/apache/arrow/vector/complex/writer/BaseWriter.java" />


<#include "/@includes/license.ftl" />

package org.apache.arrow.vector.complex.writer;

<#include "/@includes/vv_imports.ftl" />

/*
 * File generated from ${.template_name} using FreeMarker.
 */
@SuppressWarnings("unused")
public interface BaseWriter extends AutoCloseable, Positionable {
  int getValueCapacity();
  void writeNull();

  public interface StructWriter extends BaseWriter {

    Field getField();

    /**
     * Whether this writer is a struct writer and is empty (has no children).
     *
     * <p>
     *   Intended only for use in determining whether to add dummy vector to
     *   avoid empty (zero-column) schema, as in JsonReader.
     * </p>
     * @return whether the struct is empty
     */
    boolean isEmptyStruct();

    <#list vv.types as type><#list type.minor as minor>
    <#assign lowerName = minor.class?uncap_first />
    <#if lowerName == "int" ><#assign lowerName = "integer" /></#if>
    <#assign upperName = minor.class?upper_case />
    <#assign capName = minor.class?cap_first />
    <#if minor.typeParams?? >
    ${capName}Writer ${lowerName}(String name<#list minor.typeParams as typeParam>, ${typeParam.type} ${typeParam.name}</#list>);
    </#if>
    ${capName}Writer ${lowerName}(String name);
    </#list></#list>

    void copyReaderToField(String name, FieldReader reader);
    StructWriter struct(String name);
    ListWriter list(String name);
    MapWriter map(String name);
    MapWriter map(String name, boolean keysSorted);
    void start();
    void end();
  }

  public interface ListWriter extends BaseWriter {
    void startList();
    void endList();
    StructWriter struct();
    ListWriter list();
    MapWriter map();
    MapWriter map(boolean keysSorted);
    void copyReader(FieldReader reader);

    <#list vv.types as type><#list type.minor as minor>
    <#assign lowerName = minor.class?uncap_first />
    <#if lowerName == "int" ><#assign lowerName = "integer" /></#if>
    <#assign upperName = minor.class?upper_case />
    <#assign capName = minor.class?cap_first />
    ${capName}Writer ${lowerName}();
    </#list></#list>
  }

  public interface MapWriter extends ListWriter {
    void startMap();
    void endMap();

    void startEntry();
    void endEntry();

    MapWriter key();
    MapWriter value();
  }

  public interface ScalarWriter extends
  <#list vv.types as type><#list type.minor as minor><#assign name = minor.class?cap_first /> ${name}Writer, </#list></#list> BaseWriter {}

  public interface ComplexWriter {
    void allocate();
    void clear();
    void copyReader(FieldReader reader);
    StructWriter rootAsStruct();
    ListWriter rootAsList();

    void setPosition(int index);
    void setValueCount(int count);
    void reset();
  }

  public interface StructOrListWriter {
    void start();
    void end();
    StructOrListWriter struct(String name);
    StructOrListWriter listoftstruct(String name);
    StructOrListWriter list(String name);
    boolean isStructWriter();
    boolean isListWriter();
    VarCharWriter varChar(String name);
    IntWriter integer(String name);
    BigIntWriter bigInt(String name);
    Float4Writer float4(String name);
    Float8Writer float8(String name);
    BitWriter bit(String name);
    VarBinaryWriter binary(String name);
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