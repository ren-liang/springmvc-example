package com.dcits.springmvc.dao;

import java.util.List;

import com.dcits.springmvc.model.Book;

public interface IBookDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    
    List<Book> selectByProperties(Book book);
}