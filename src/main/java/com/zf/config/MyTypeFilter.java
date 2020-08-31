package com.zf.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter  implements TypeFilter {
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

//        获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
//    获取当前类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

//       获取当前类的资源(类的路径)
        Resource resource = metadataReader.getResource();

//        通过类信息 获取扫描所有的类名
        String className = classMetadata.getClassName();
//        System.out.println("------>"+className);
        if (className.contains("er")){
            return  true;
        }
        return false;
    }
}
