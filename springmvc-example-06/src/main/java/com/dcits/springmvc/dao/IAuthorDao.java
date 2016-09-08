package com.dcits.springmvc.dao;

import java.util.List;

import com.dcits.springmvc.model.Author;

public interface IAuthorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Author record);

    int insertSelective(Author record);

    Author selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);
    
    List<Author> selectByProperties(Author author);
}