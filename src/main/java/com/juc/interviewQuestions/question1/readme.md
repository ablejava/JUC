问题：
实现一个容器，提供两个方法add, size, 写两个线程：  
线程1. 添加10个元素到容器中  
线程2. 实时监控元素个数，当个数到5个时，线程2给提示并结束  
分别采用Lock, CountDownLatch, LockSupport, Semaphore