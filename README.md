# java-decompiler
java反编译


### 简要说明
 + 1、该库主要是封装了procyon相关的反编译库，这个库可以处理Java1.8的代码。
 + 2、该库补充了procyon没有处理InputStream及byte[]的情况。
 


### 使用方法

详细可参考test.com.farmer.x.DecompilerTest
```java
String customClassFile = classPath + "CompilerAndJarTools.class";
String decompileJava = DecompilerUtils.decompile(customClassFile);
System.out.printf("\r\n----- %s 反编译结果： -----\r\n\r\n", customClassFile);
System.out.println(decompileJava);
```
test.com.farmer.x.ConvertMain可以将整个jar文件包下的.class文件全部反编为.java文件
```xml
1.将jar包解压非C盘的路径下（对加密的.class文件无法正常的编译）
2.修改changePath下文件的路径，修改main方法下的路径(详情参考对应方法的备注)

```
