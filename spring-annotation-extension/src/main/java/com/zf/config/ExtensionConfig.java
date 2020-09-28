package com.zf.config;

import com.zf.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengfan
 * @create 2020-09-28 23:29
 *  spring 其他的扩展原理
 *  BeanPostProcessor  bean后置处理器  bean创建对象初始化前后进行拦截工作的
 *  BeanFactoryPostProcessor bean工厂的后置处理器
 *  在内部bean工厂初始化之后。所有bean定义都已加载，但没有bean还没有被创建
 *
 * 1. BeanFactoryPostProcessor 原理
 *  1 ioc容器初始化
 *  2 invokeBeanFactoryPostProcessors(beanFactory);
 *     如何找到所有的BeanFactoryPostProcessor并执行他们的方法
 *       1直接在bean工厂中中找到所有的BeanFactoryPostProcessor的组件并执行他们的方法
 *       2 在初始化创建其他组件前面执行
 * 2. BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *     postProcessBeanDefinitionRegistry()
 *     在所有的bean的定义信息将要被加载 但是bean实例还未创建的时候
 *    优先于BeanFactoryPostProcessor执行 利用BeanDefinitionRegistryPostProcessor给容器中
 *    在额外添加一些组件
 *    原理
 *      ioc容器准备创建 invokeBeanFactoryPostProcessors(beanFactory);
 *       1 先从容器中根据类型获取到BeanDefinitionRegistryPostProcessor
 *       2 invokeBeanDefinitionRegistryPostProcessors()方法中依次触发
 *          postProcessor.postProcessBeanDefinitionRegistry(registry);
 *       3 invokeBeanFactoryPostProcessors();
 *          postProcessor.postProcessBeanFactory(beanFactory);
 *       4 再来从容器中获取 BeanFactoryPostProcessor 然后依次触发
 *          postProcessor.postProcessBeanFactory(registry);
 *
 * 3 ApplicationListener 监听容器中发布的事件  事件驱动模型开发
 * ApplicationListener<E extends ApplicationEvent> extends EventListener
 *
 *  自定义事件发布 步骤
 *    1 写一个监听器 监听某个事件 必须是 ApplicationEvent及其子类
 *     可以使用@EventListener
 *        原理
 *          使用了 EventListenerMethodProcessor处理器来解析方法上注解
 *           EventListenerMethodProcessor implements SmartInitializingSingleton
 *        流程：
 *           1 ioc 容器创建 refresh()
 *           2 初始化剩下的单实例的bean finishBeanFactoryInitialization(beanFactory);
 *              1 先创建所有的单实例bean
 *              2 单实例bean创建完成 判断是否是 instanceof SmartInitializingSingleton
 *              如果是就进行回调
 *    2 加入到容器中
 *    3 只要容器中相关事件发布 就能监听到这个事件
 *        ContextRefreshedEvent 容器刷新完成 所有bean都完全创建 会发布这个事件
 *        ContextClosedEvent    容器关闭会发布这个事件
 *    4 事件发布
 *        ioc.publishEvent
 *
 *  原理：
 *     1 ioc 容器创建  refresh()方法
 *     2 finishRefresh(); 容器刷新完成 会发布 ContextRefreshedEvent事件
 *     也可以自己发布事件
 *    容器 关闭 publishEvent(new ContextClosedEvent(this)) 事件
 *     事件发布流程
 *     3 publishEvent(new ContextRefreshedEvent(this));
 *           1 获取事件的派发器getApplicationEventMulticaster()
 *           2 派发事件 multicastEvent
 *           3 获取到所有的ApplicationListener
 *              for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *               1 如果有Executor 可以支持使用Executor进行异步派发
 *               2 否则同步的方式执行 invokeListener(listener, event);
 *               拿到 listener 回调onApplicationEvent(event);
 *
 *   事件派发器流程
 *       1容器创建 调用refresh();
 *       2 初始化 initApplicationEventMulticaster();
 *          1 先去容器中去找有没有id==“applicationEventMulticaster” 如果有就从ioc容器中拿
 *          2 如果没有就创建一个new SimpleApplicationEventMulticaster(beanFactory);
 *            注册到ioc容器中 我们就可以在其他组件要派发事件的时候自动注入applicationEventMulticaster
 *            就可以了
 *    容器中有哪些监听器
 *     1 容器创建 调用refresh();
 *     2 注册所有的监听器 registerListeners();
 *     3 String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 * 		for (String listenerBeanName : listenerBeanNames) {
 * 			getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *                }
 *        按照类型从ioc容器中拿这些组件 并且将这些组件添加到了applicationEventMulticaster组件中
 */
@ComponentScan("com.zf")
@Configuration
public class ExtensionConfig {


    @Bean
   public Blue blue(){
       return  new Blue();
   }
}
