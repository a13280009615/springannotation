package com.zf.typefilter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author zhengfan
 * @create 2020-09-26 下午7:25
 *  实现 TypeFilter接口 自己指定规则
 */
public class MyTypeFilter  implements TypeFilter {

    /**
     *
     * @param metadataReader 读取当前扫描类的信息
     * @param metadataReaderFactory 可以获取到其他任何类的信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前扫描的类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类的资源信息(类的路径..)
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        // 主要扫描的类名 包含 er 就注册到容器中
       if (className.contains("er")){
           return  true;
       }
        return false;
    }
}
