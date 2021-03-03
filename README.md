# Minimisation for [oracle/graal#2928](https://github.com/oracle/graal/issues/2928)

`docker build -t graal2928 .`

`docker run --rm graal2928`

The issue is not reproducible when the native-image was built with v21.0.0.2. With v20.3.1.2 the application fails at runtime:
```
Exception in thread "main" com.oracle.svm.core.jdk.UnsupportedFeatureError: Invoke with MethodHandle argument could not be reduced to at most a single call or single field access. The method handle must be a compile time constant, e.g., be loaded from a `static final` field. Method that contains the method handle invocation: java.lang.invoke.LambdaForm$MH/584730620.invoke_MT(Object, Object)
	at com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:87)
	at scala.runtime.Statics.releaseFence(Statics.java:148)
	at scala.collection.immutable.$colon$colon.<init>(List.scala:623)
	at scala.collection.immutable.List.prependedAll(List.scala:153)
	at scala.collection.IterableOnceOps.toList(IterableOnce.scala:1258)
	at scala.collection.IterableOnceOps.toList$(IterableOnce.scala:1258)
	at scala.collection.AbstractIterable.toList(Iterable.scala:920)
	at cats.effect.internals.IOTracing$.buildFrame(IOTracing.scala:48)
	at cats.effect.internals.IOTracing$.buildCachedFrame(IOTracing.scala:39)
	at cats.effect.internals.IOTracing$.cached(IOTracing.scala:34)
	at cats.effect.IO.flatMap(IO.scala:133)
	at cats.effect.IOLowPriorityInstances$IOEffect.flatMap(IO.scala:886)
	at cats.effect.IOLowPriorityInstances$IOEffect.flatMap(IO.scala:863)
	at cats.FlatMap$Ops.flatMap(FlatMap.scala:229)
	at cats.FlatMap$Ops.flatMap$(FlatMap.scala:229)
	at cats.FlatMap$ToFlatMapOps$$anon$2.flatMap(FlatMap.scala:243)
	at io.odin.loggers.DefaultLogger.$anonfun$log$1(DefaultLogger.scala:23)
	at cats.Applicative.whenA(Applicative.scala:187)
	at cats.Applicative.whenA$(Applicative.scala:186)
	at cats.effect.IOLowPriorityInstances$IOEffect.whenA(IO.scala:863)
	at io.odin.loggers.DefaultLogger.log(DefaultLogger.scala:23)
	at io.odin.loggers.DefaultLogger.$anonfun$info$1(DefaultLogger.scala:98)
	at cats.Applicative.whenA(Applicative.scala:187)
	at cats.Applicative.whenA$(Applicative.scala:186)
	at cats.effect.IOLowPriorityInstances$IOEffect.whenA(IO.scala:863)
	at io.odin.loggers.DefaultLogger.info(DefaultLogger.scala:98)
	at app.Main$.run(Main.scala:8)
	at cats.effect.IOApp.$anonfun$main$3(IOApp.scala:68)
	at cats.effect.internals.IOAppPlatform$.mainFiber(IOAppPlatform.scala:40)
	at cats.effect.internals.IOAppPlatform$.main(IOAppPlatform.scala:25)
	at cats.effect.IOApp.main(IOApp.scala:68)
	at cats.effect.IOApp.main$(IOApp.scala:67)
	at app.Main$.main(Main.scala:6)
	at app.Main.main(Main.scala)
```

