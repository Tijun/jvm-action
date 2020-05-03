#### 深入Java虚拟机实战

主要使用简单易懂的方法模拟各类jvm运行时可能发生的问题，并给出调优策略,主要参考《深入理解Java虚拟机》第三版

说明，新建的项目为maven项目，所有命令在src/main/java目录下执行，先用javac编译，然后使用java命令+参数进行运行调试

- 模拟OOM异常

  - 堆内存溢出（Java heap space）

    - 代码

    ```java
    public class HeapOOM {
    
        private static List<char[]> chars = new ArrayList<char[]>();
        public static void main(String[] args) {
            for (;;){
                chars.add(new char[1024]);
            }
        }
    }
    ```

    

    - 命令及参数，在java目录下执行

      ```shell
      ~/IdeaProjects/jvm-action/src/main/java » javac oom/HeapOOM.java                               tijun@TijundeMacBook-Pro
      ------------------------------------------------------------
      ~/IdeaProjects/jvm-action/src/main/java » java -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./oom.dump oom.HeapOOM
      java.lang.OutOfMemoryError: Java heap space
      Dumping heap to ./oom.dump ...
      Heap dump file created [20561484 bytes in 0.104 secs]
      Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
              at oom.HeapOOM.main(HeapOOM.java:11)
      
      ```

    - 分析：

      - 在main线程中抛出了oom异常，并指出了异常抛出的位置，`Java heap space`则说明他是堆发生了oom，找到HeapOOM的11行，发现是不停的往list中添加char数组，直到堆内存耗尽后，不能再申请到可用的内存时抛出了oom。

        ```java
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
                at oom.HeapOOM.main(HeapOOM.java:11)
        ```

      - oom.dump文件如何分析？

        - 使用https://www.eclipse.org/mat/downloads.php Eclipse memory Analyzer工具进行分析

- 模拟死循环

- 模拟fullGC

- 模拟死锁

  

