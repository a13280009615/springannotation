package com.zf.controller;

import com.zf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author zhengfan
 * @create 2020-09-26 22:34
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
}
