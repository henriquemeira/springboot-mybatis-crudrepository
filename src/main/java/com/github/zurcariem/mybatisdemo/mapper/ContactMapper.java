package com.github.zurcariem.mybatisdemo.mapper;

import com.github.zurcariem.mybatisdemo.model.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Mybatis Mapper interface with additional SQL query.
 */
@Mapper
public interface ContactMapper {

    @Select("SELECT * FROM contact WHERE fullname = #{fullname}")
    Contact findByName(String fullname);
    
    @Select("select 1 from contact where fullname = #{fullname}")
    public boolean existsByFullname(String fullname);

    @Select("select count(id) from contact")
    public long count();
    
}
