package com.zf.service;

import com.zf.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {

//    @Qualifier("bookDao")
//    @Autowired
    @Resource
   private BookDao bookDao;


    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
