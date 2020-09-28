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
 *    利用 AspectJAutoProxyRegistrar 自定义给容器中注册bean
 *
 *   if (!registry.containsBeanDefinition("org.springframework.aop.config.internalAutoProxyCreator"))
 *    给容器中定义了一个bean的id为internalAutoProxyCreator =>这个bean为AnnotationAwareAspectJAutoProxyCreator
 *
 * 这个组件 继承关系 =>代表继承
 *     AnnotationAwareAspectJAutoProxyCreator
 *        ->AspectJAwareAdvisorAutoProxyCreator
 *            ->AbstractAdvisorAutoProxyCreator
 *               ->AbstractAutoProxyCreator  extends ProxyProcessorSupport implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *
 *
 *   流程
 *     1创建ioc容器
 *     2注册配置类  调用refresh() 刷新容器
 *       register(annotatedClasses);
 * 		 refresh();
 *     3 注册bean 的后置处理器  拦截bean 的创建  registerBeanPostProcessors(beanFactory);
 *        1先获取ioc 容器中已经定义了的需要创建对象所有的后置处理器
 *        2给容器中填加了一些别的后置处理器  beanFactory.addBeanPostProcessor()
 *        在容器中做了一些 PriorityOrdered 和 Ordered 接口的后置处理器的处理
 *        3优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *        4再来注册实现了Ordered接口的BeanPostProcessor
 *        5最后注册没有实现优先级接口的BeanPostProcessor
 *        6先去ioc中拿这个组件 如果ioc容器中没有这个组件 就创建BeanPostProcessor对象并保存在容器中
 *           1创建bean的实例
 *           2给属性赋值 populateBean
 *           3初始化bean  initializeBean
 *                  1 invokeAwareMethods(); 处理Aware接口的方法回调
 *                  2 应用后置处理器 applyBeanPostProcessorsBeforeInitialization
 *                  3 invokeInitMethods 执行自定义初始化方法
 *                  4 指定后置处理器的 applyBeanPostProcessorsAfterInitialization
 *           4BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功
 *           并且创建了aspectJAdvisorsBuilder
 *           this.aspectJAdvisorsBuilder =
 *  * 				new BeanFactoryAspectJAdvisorsBuilderAdapter(beanFactory, this.aspectJAdvisorFactory);
 *        7 把BeanPostProcessor.addBeanPostProcessor(postProcessor)
 *          beanFactory.addBeanPostProcessor(postProcessor);
 *
 *
 * =====以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=======
 *  AnnotationAwareAspectJAutoProxyCreator =>  InstantiationAwareBeanPostProcessor
 *
 *     4 finishBeanFactoryInitialization(beanFactory);完成bean工厂初始化工作创建剩下的单实例bean
 *        1 遍历获取容器中所有的bean 一次创建对象getBean(beanName)
 *          getBean()  -> doGetBean()  ->getSingleton()
 *        2 创建bean
 *          1 先从缓存中获取当前的bean,如果能获取到 说明bean之前是被创建过的直接使用 否则再创建
 *          只要创建好的bean都会被缓存起来
 *          2 createBean() 创建bean 【 AnnotationAwareAspectJAutoProxyCreator会在任何对象之前先尝试返回bean的实例】
 *           【BeanPostProcessor 是bean 对象创建完成完成初始化前后调用的】
 *           【InstantiationAwareBeanPostProcessor是在创建bean实例之前先尝试用后置处理器返回对象】
 *
 *            1 Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
 *              希望后置处理器在此能返回一个代理对象如果能返回就使用代理对象如果不能就继续创建bean 第二步
 *
 *                后置处理器先尝试返回对象
 *               bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                 如果这个后置处理器是InstantiationAwareBeanPostProcessor 类型的执行
 *                  postProcessBeforeInstantiation()方法
 * 					if (bean != null) {
 * 						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 * 						}
 *
 *            2  doCreateBean  doCreateBean(beanName, mbdToUse, args);  这才是真正的去创建一个bean实例 和 3.6流程一样
 *
 *
 *  AnnotationAwareAspectJAutoProxyCreator  的作用就是
 *  每一个bean创建之前调用 postProcessBeforeInstantiation 方法
 *   1 判断当前的bean是否在advisedBeans中(advisedBeans 保存了所有要增强的bean)
 *   if (this.advisedBeans.containsKey(cacheKey))
 *   2 判断当前bean是否是基础类型的  【实现了一下接口的类】
 *   Advice||Pointcut ||Advisor ||AopInfrastructureBean||Aspect的注解
 *   3 判断是否需要跳过
 *      1  获取候选的增强器(切面里的通知方法)
 *        InstantiationModelAwarePointcutAdvisor: expression [pointCut()]; advice method [public void com.zf.aop.LogAspects.logStart(org.aspectj.lang.JoinPoint)]; perClauseKind=SINGLETON
 *       判断每一个增强器是否是 AspectJPointcutAdvisor类型的 是返回true
 *
 *          永远返回false
 *  2 创建对象
 *  postProcessAfterInitialization
 *    1 wrapIfNecessary(bean, beanName, cacheKey); 如果需要的情况下包装
 *      1 获取候选的所有增强器(通知方法)  specificInterceptors
 *      2 获取到能在当前bean使用的增强器
 *      3 给增强器排序
 *    2 保存当前bean的advisedBeans中
 *    3 如果这个bean是增强的 穿件了一个当前bean的proxy代理对象
 *       获取所有的增前器
 *       保存到ProxyFactory中
 *       创建代理代理自动决定 【通过 jdk 或者cglib】
 *    4  wrapIfNecessary 给容器中返回了使用cglib增强了的代理对象
 *    5  以后容器中获取到这个组件的代理对象 执行目标方法的时候 代理对象就会执行通知方法
 *
 *  目标方法的执行
 *   容器中保存了组件的代理对象(cglib增强后的对象) 这个对象里面保存了详细信息(比如增强器,目标对象...)
 *    1 CglibAopProxy.intercept();拦截目标方法的执行
 *    2 根据 proxyFactory获取目标方法的拦截器链
 *    List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *        1 创建一个list保存所有的拦截器 List<Object> interceptorList
 *        默认是config.getAdvisors().length
 *       2 遍历所有的增强器 将其转为Interceptor
 *       3 判断将增强器转为 List<MethodInterceptor>
 *           如果是MethodInterceptor直接添加到list中
 *           如果没有 用AdvisorAdapter将增强器转为Interceptor 添加到list 中
 *           返回转换完成的MethodInterceptor的数组
 *    3 如果没有拦截器链直接执行目标方法
 *       拦截器链:每一个通知方法又被包装为方法拦截器 利用MethodInterceptor机制
 *    4 如果有拦截器链 把需要执行的目标对象 目标方法 拦截器链等信息传入 创建一个new CglibMethodInvocation 并执行proceed方法
 *    new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 *
 *    5 拦截器链的触发过程
 *     1 如果没有拦截器直接执行目标方法 或者拦截器的索引和拦截器数组长度-1 大小一样 执行目标方法
 *     2 链式获取没一个拦截器，拦截器执行 invoke方法 每一个拦截器等待下一个拦截器执行完成返回以后
 *     再来执行；
 *      拦截器链的机制 保证了通知方法与目标方法的执行顺序
 *
 *   aop原理总结
 *   1  @EnableAspectJAutoProxy 开启 aop功能
 *   2 这个注解会给容器中 注册一个组件 AnnotationAwareAspectJAutoProxyCreator
 *     这是一个后置处理器
 *   3 容器的创建流程
 *       1 registerBeanPostProcessors(beanFactory); 注册所有的后置处理器
 *       2 finishBeanFactoryInitialization(beanFactory); 初始化剩下的单实例bean
 *          1  创建业务逻辑组件和切面组件
 *          2  后置处理器会拦截组件的创建过程
 *          3  在组件创建完成之后会判断这个组件是否需要增强
 *              是：将切面的通知方法 封装成增强器 给业务逻辑bean创建一个代理对象默认是(cglib)
 *    4 指定目标方法
 *          1 代理对象执行目标方法 使用CglibProxy.intercept() 进行拦截方法
 *             得到目标方法的拦截器链(增强器保证成拦截器)
 *          2 利用拦截器的链式机制 依次进入每个拦截器进行执行
 *          3 效果
 *             正常  前置通知-->目标方法-->后置通知 --->返回通知
 *             异常  前置通知-->目标方法-->后置通知 --->异常通知
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
