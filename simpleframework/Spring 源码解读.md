# spring 源码解读
## 框架中的设计模式
### 开闭原则
> 一个软件实体，应该对扩展开放，对修改关闭。  
> 应该通过扩展来实现变化，而不是通过修改已有的代码来实现变化
### 简单工厂模式
#### 优缺点
+ 优点：可以对创建的对象进行“加工”，对客户端隐藏相关细节。
+ 缺点：因创建逻辑复杂或创建对象过多而造成代码臃肿
+ 缺点：新增、删除子类均会违反开闭原则
### 工厂方法模式
> 定义一个用于创建对象的接口，让子类决定实例化哪一个类。
+ 对类的是实例化延迟的其子类
+ 优点
    + 遵循开闭原则
    + 对客户端隐藏对象的创建细节
    + 遵循单一职责
+ 缺点
    + 添加子类的时候”拖家带口”
    + 只支持同一类产品的创建
### 抽象工厂模式
> 提供一个创建一系列相关或相互依赖对象的接口  
> 解决了工厂模式只支持生产一种产品的弊端

+ 新增一个产品族，只需要增加一个新的具体工厂，不需要修改代码
```
工厂方法模式：每个抽象产品派生多个具体产品类，每个抽象工厂类派生多个具体工厂类，每个具体工厂类负责一个具体产品的实例创建
抽象工厂模式：每个抽象产品派生多个具体产品类，每个抽象工厂派生多个具体工厂类，每个具体工厂负责一系列具体产品的实例创建
```
## 反射
> 允许程序在运行时来进行自我检查并且对内部的成员进行操作
```
反射主要是指程序可以访问，检测和修改它本身状态或行为的一种能力，并能根据自身行为的状态和结果，调整或修改应用所描述行为的状态和相关的语义
```
### 反射机制的作用
+ 在运行时判断任意一个对象所属的类
+ 在运行时获取类的对象
+ 在运行时访问java对象的属性，方法，构造方法等
### java.lang.reflect类库里面主要的类
+ Field：表示类中的成员变量
+ Method：表示类中的方法
+ Constructor：表示类的构造方法
+ Array：该类提供了动态创建数组和访问数组元素的静态方法
### 反射依赖的Class
> 用来表示运行时类型信息的对应类
+ 每个类都有唯一一个与之相对应的Class对象
+ Class类为类类型，而Class对象为类类型对象
+ Class类的特点
    + Class类也是类的一种，class则是关键字
    + Class类只有一个私有的构造函数，只有JVM能够创建Class类的实例
    + JVM中只有唯一一个和类相对应的Class对象来描述其类型信息
```
    /*
     * Private constructor. Only the Java Virtual Machine creates Class objects.
     * This constructor is not used and prevents the default constructor being
     * generated.
     */
    private Class(ClassLoader loader) {
        // Initialize final field for classLoader.  The initialization value of non-null
        // prevents future JIT optimizations from assuming this final field is null.
        classLoader = loader;
    }
```
#### 获取Class对象的三种方式
> 在运行期间，一个类，只有一个与之相对应的Class对象产生
+ Object—>getClass0
+ 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
+ 通过Class类的静态方法：forName（String className）（常用）
#### 通过反射获取 属性 方法和构造方法
> 调用私有对象：className.setAccessible(true)
```
通过Class对象可以获取某个类中的：构造方法；获取构造方法：
1）.批量的方法：
public ConstructorI getConstructors()：所有“公有的“构造方法
public Constructorl getDeclaredConstructors()：获取所有的构造方法（包括私有、受保护、默认、公有）
2）.获取单个的方法，并调用：
public Constructor getConstructor（Class..parameterTypes）：获取单个的“公有的”构造方法：
public Constructor getDeclaredConstructor（Class..parameterTypes）：获取”某个构造方法“可以是私有的，

调用构造方法：
Constructor-->newInstance（Object..initargs）

获取成员变量并调用：
1.批量的
    1）Field[] getFields()：获取所有的“公有字段”
    2）Field[] getDeclaredFields()：获取所有字段，包括：私有、受保护、默认、公有；
2.获取单个的：
    1）public Field getField（String fieldName）.获取某个“公有的“字段；
    2）public Field getDeclaredField（String fieldName）：获取某个字段（可以是私有的）设置字段的值：

Field-->public void set（Object obj，Object value）：
    参数说明：
        1.obj：要设置的字段所在的对象；
        2.value：要为字段设置的值；

获取成员方法并调用：
1.批量的：
    public Methogll getMethods0：获取所有“公有方法”；（包含了父类的方法也包含Object类）
    public Methodll getDeclaredMethods0：获取所有的成员方法，包括私有的（不包括继承的）
2.获取单个的：
    public Method getMethod（String name，Class<？.…parameterTypes）：
        参数：
            name：方法名；
            Class..…：形参的Class类型对象
    public Method getDeclaredMethod（String name，Class<？>.…parameterTypes）
调用方法：
    Method-->public Object invoke（Object obj，Object..args）：参数说明：
    obj：要调用方法的对象；args：调用方式时所传递的实参；
```
#### 反射的获取源
+ 用xml来保存类相关的信息以供反射调用
```aidl
<xml version="1.0"encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<bean id="welcomeService"class="com.imooc.service.impl.WelcomeServicelmpl"/>
</beans>
```
+ 用注解来保存类相关的信息以供反射调用

#### 注解
> 提供一种为程序元素设置元数据的方法
+ 元数据是添加到程序元素如方法、字段、类和包上的额外信息
+ 注解是一种分散式的元数据设置方式，XML是集中式的设置方式
+ 注解不能直接干扰程序代码的运行
+ 注解的功能
    + 作为特定的标记，用于告诉编译器一些信息
    + 编译时动态处理，如动态生成代码
    + 运行时动态处理，作为额外信息的载体，如获取注解信息
+ 注解的分类
    + 标准注解：Override、Deprecated、SuppressWarnings
    + 元注解：@Retention、@Target、@Inherited、@Documented
        >  用于修饰注解的注解，通常用在注解的定义上
        + @Target：注解的作用目标
        + @Retention：注解的生命周期
        + @Documented：注解是否应当被包含在JavaDoc文档中
        + @Inherited：是否允许子类继承该注解 11：43
    + 自定义注解
##### 注解的工作原理
+ 通过键值对的形式为注解属性赋值
+ 编译器检查注解的使用范围，将注解信息写入元素属性表
+ 运行时JVM将RUNTIME的所有注解属性取出并最终存入map里
+ 创建AnnotationInvocationHandler实例并传入前面的map
+ JVM使用JDK动态代理为注解生成代理类，并初始化处理器
+ 调用invoke方法，通过传入方法名返回注解对应的属性值
+ 使用注解标记需要工厂管理的实例，并依据注解属性做精细控制

## 控制反转 Ioc Inversion of Control
+ 依托一个类似工厂的IoC容器
+ 将对象的创建、依赖关系的管理以及生命周期交由loC容器管理
+ 降低系统在实现上的复杂性和耦合度，易于扩展，满足开闭原则
+ Spring Core 最核心的部分
+ DI
    + DI之前: 上层建筑依赖下层建筑 `依赖`
    + DI之后: 把底层类作为参数传递给上层类,实现上层对下层的`控制` `注入`
    ![Alt text](https://s1.ax1x.com/2020/07/22/UbcBHU.png)
    + 依赖注入的方式
        + Setter
        + Interface
        + Constructor
        + Annotation
    ![Alt_text](https://s1.ax1x.com/2020/07/22/Ubgp5Q.png)
+ IOC容器的优势
    + 避免在各处使用new来创建类，并且可以做到统一维护
    + 创建实例的时候不需要了解其中的细节
    + 反射+工厂模式的合体，满足开闭原则