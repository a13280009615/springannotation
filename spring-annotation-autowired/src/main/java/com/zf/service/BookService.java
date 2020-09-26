package com.zf.service;

import com.zf.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author zhengfan
 * @create 2020-09-26 22:33
 */
@Service
public class BookService {

  //  @Qualifier("bookDao")
    //@Resource(name = "bookDao2")

   @Inject
    private BookDao bookDao;

    public  void  print(){
        System.out.println(bookDao);
    }
}
