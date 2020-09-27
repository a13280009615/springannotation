package com.zf.config;

import com.zf.aop.LogAspects;
import com.zf.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhengfan
 * @create 2020-09-27 0:42
 *
 *  指在程序运行期间将某段代码切入到指定方法指定位置进行运行的编码方式
 *
 *  定义一个业务逻辑类 MathCalculator 在业务逻辑运行的时候 将日志进行打印
 *  定义一个日志切面类 LogAspects 切面类的方法需要动态感知
 *    通知方法
 *      前置通知
 *      后置通知  无论方法正常结束还是异常结束都调用
 *      返回通知
 *      异常通知
 *      环绕通知  joinPoint.procced()
 *
 *   给切面类目标方法标注何时何地运行
 *
 *   1将切面类和业务逻辑类(目标方法所在的类) 都加入到 容器中
 *
 *   2告诉spring 哪个类是切面类 给切面类加一个注解 @Aspect
 *
 *   3开启基于注解的切面功能  @EnableAspectJAutoProxy
 *
 *   注意:在使用JoinPoint 作为参数的 时候 一定要把他放在 第一个参数 不然就会报错
 *
 *
 *     spring aop 原理  @EnableAspectJAutoProxy 这个注解
 *
 *    导入了
 *     @Import(AspectJAutoProxyRegistrar.class)
 *    利用 AspectJAutoProxyRegistrar 自定义个容器中注册bean
 *
 *   if (registry.containsBeanDefinition("org.springframework.aop.config.internalAutoProxyCreator"))
 *  internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *
 *     AnnotationAwareAspectJAutoProxyCreator
 *        ->AspectJAwareAdvisorAutoProxyCreator
 *            ->AbstractAdvisorAutoProxyCreator
 *               ->AbstractAutoProxyCreator  extends ProxyProcessorSupport implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *
 *   给容器中注册了一个 自动代理创建器
 *   父类中 实现了SmartInstantiationAwareBeanPostProcessor, 自动装配了 bean工厂BeanFactoryAware
 *
 *   流程
 *     1创建ioc容器
 *     2注册配置类  调用refresh() 刷新容器
 *     3 注册bean 的后置处理器  拦截bean 的创建
 *        1先获取ioc 容器中已经定义了需要创建对象所有的后置处理器
 *        2给容器中加了一些别的后置处理器
 *        3优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *        4再来注册实现了Ordered接口的BeanPostProcessor
 *        5注册没有实现优先级接口的BeanPostProcessor
 *        6先去ioc中拿这个组件 如果ioc容器中没有这个组件 就创建BeanPostProcessor对象并保存在容器中
 *           1创建bean的实例
 *           2给属性赋值 populateBean
 *           3初始化bean  initializeBean
 *                  1 invokeAwareMethods(); Aware接口的方法回调
 *                  2 应用后置处理器 applyBeanPostProcessorsBeforeInitialization
 *                  3 invokeInitMethods 执行自定义初始化方法
 *                  4 指定后置处理器的 applyBeanPostProcessorsAfterInitialization
 *           4BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功
 *           this.aspectJAdvisorsBuilder =
 *  * 				new BeanFactoryAspectJAdvisorsBuilderAdapter(beanFactory, this.aspectJAdvisorFactory);
 *        7 把BeanPostProcessor.addBeanPostProcessor(postProcessor)
 *
 *
 * ==============以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=======
 *  AnnotationAwareAspectJAutoProxyCreator =>  InstantiationAwareBeanPostProcessor
 *
 *     4 finishBeanFactoryInitialization(beanFactory);完成bean工厂初始化工作创建剩下的单实例bean
 *        1 遍历获取容器中所有的bean 一次创建对象getBean(beanName)
 *          getBean()  -> doGetBean()  ->getSingleton()
 *        2 创建bean
 *          1 先从缓存中获取当前的bean,如果能获取到 说明bean之前是被创建过的 直接使用 否则在创建
 *          只要创建好的bean都会被缓存起来
 *          2 createBean() 创建bean  AnnotationAwareAspectJAutoProxyCreator会在任何对象之前先尝试返回bean 的实例
 *           BeanPostProcessor 是bean 对象创建完成完成初始化前后调用的
 *           InstantiationAwareBeanPostProcessor是在创建bean实例之前先尝试用后置处理器返回对象
 *
 *            1 Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
 *              希望后置处理器在此能返回一个代理对象如果能返回就使用代理对象如果不能就继续
 *               bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                 如果这个后置处理器是InstantiationAwareBeanPostProcessor 类型的
 *                 执行postProcessBeforeInstantiation()方法
 * 					if (bean != null) {
 * 						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 * 						}
 * 					后置处理器先尝试返回对象
 *            2  doCreateBean  这才是真正的去创建一个bean实例 和 3.6流程一样
 *
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    @Bean
    public MathCalculator mathCalculator(){
        return  new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return  new LogAspects();
    }
}
