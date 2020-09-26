package com.zf.imports;

import com.zf.bean.Blue;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhengfan
 * @create 2020-09-26 下午8:23
 *
 * 自定义逻辑返回要导入的组件
 */

public class MyImportSelector  implements ImportSelector {
    /**
     *
     * @param importingClassMetadata 当前标注@Import注解的类的所有注解信息
     * @return 就是要导入到容器的全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"com.zf.bean.Blue","com.zf.bean.Yellow"};
    }
}
