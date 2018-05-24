package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.Blogs;
import com.example.demo.mapper.BlogMapper;
import com.example.demo.utils.CurrentUtil;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class BlogService extends BaseService<BlogMapper,Blogs> {

    private final BlogMapper blogMapper;

    public BlogService(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }


    public List<Blogs> getBlogsByInput(){
        List<Blogs> blogs = blogMapper.selectList(
                new EntityWrapper<Blogs>()
                        .eq("createdBy", CurrentUtil.getCurrentUserID())
        );
        if(!CollectionUtils.isEmpty(blogs)){
            for(Blogs blog : blogs){
                //String转List
                String tag = blog.getTag();
                String[] strings = tag.split("-");
                blog.setTags(Arrays.asList(strings));
            }
        }
        return blogs;
    }



    public Boolean updateBlog(Blogs blogs){
        Integer integer = blogMapper.updateById(blogs);
        return integer!=null;
    }

}
