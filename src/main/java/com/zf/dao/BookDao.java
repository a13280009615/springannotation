package com.zf.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

    private String level  = "1";

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BookDao(String level) {
        this.level = level;
    }

    public BookDao() {
    }


    @Override
    public String toString() {
        return "BookDao{" +
                "level='" + level + '\'' +
                '}';
    }
}
