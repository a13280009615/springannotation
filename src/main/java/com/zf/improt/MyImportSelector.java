package com.zf.improt;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑返回要导入的组件
public class MyImportSelector  implements ImportSelector {
    /**
     * AnnotationMetadata 当前标注@Import 注解的类的所有注解信息
     * @param importingClassMetadata
     * @return 返回的就是要导入到IOC 容器的组件全类名
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"com.zf.bean.Blue","com.zf.bean.Yellow"};
    }
}
