# ARouterDemo
ARouter 技术探索：使用方法、实现原理、编程规范、架构设计、设计模式、编程思想。

目前只支持Activity和Fragment的路由。

根目录下的doc目录存有Xmind 图片，后续更新完善。

简单记录如下：

Postcard 的创建属于 builder 模式：

com.alibaba.android.arouter.launcher.ARouter#build(android.net.Uri)
public Postcard build(Uri url)

ARouter 总管navigation 属于外观设计模式：

异常提示：Could not，must not：
throw new IllegalArgumentException("path must not be null.");

用hasInit代替inited：
if (!hasInit) {
    throw new InitException("ARouterCore::Init::Invoke init(context) first!");
} else {}
