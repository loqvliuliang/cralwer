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
                        .orderBy("createTime",false)
        );
        setTags(blogs);
        return blogs;
    }


    public  void setTags(List<Blogs> blogs){
        if(!CollectionUtils.isEmpty(blogs)){
            for(Blogs blog : blogs){
                //String转List
                String tag = blog.getTag();
                String[] strings = tag.split(",");
                blog.setTags(Arrays.asList(strings));
            }
        }
    }

    public Boolean updateBlog(Blogs blogs){
        Integer integer = blogMapper.updateById(blogs);
        return integer!=0;
    }


    public Boolean insertBlog(Blogs blogs){
        //将list tags转tag标签。
        String tags = blogs.getTags().toString();
        blogs.setViewTimes(10);//观看次数10次
        blogs.setTag(tags.substring(1,tags.length()-1));
        blogs.setCreatedBy(1L);
        blogs.setMarkdownContent("<pre>"+blogs.getMarkdownContent()+"</pre>");//方便前台显示
        Integer insert = blogMapper.insert(blogs);
        return insert!=0;
    }


    public Blogs getBlogById(Long id){
        Blogs blogs = blogMapper.selectById(id);
        setTags(Arrays.asList(blogs));
        //更新观看次数
        blogs.setViewTimes(blogs.getViewTimes()+1);
        blogMapper.updateById(blogs);
        return blogs;
    }

}
