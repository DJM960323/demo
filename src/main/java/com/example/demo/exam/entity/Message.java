package com.example.demo.exam.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

public class Message {
    /**
     * 留言表id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 留言的时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 1代表可见，0代表不可见
     */
    private Boolean visiable;

    /**
     * 获取留言表id
     *
     * @return id - 留言表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置留言表id
     *
     * @param id 留言表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取留言内容
     *
     * @return content - 留言内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置留言内容
     *
     * @param content 留言内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取留言的时间
     *
     * @return createTime - 留言的时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置留言的时间
     *
     * @param createtime 留言的时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取1代表可见，0代表不可见
     *
     * @return visiable - 1代表可见，0代表不可见
     */
    public Boolean getVisiable() {
        return visiable;
    }

    /**
     * 设置1代表可见，0代表不可见
     *
     * @param visiable 1代表可见，0代表不可见
     */
    public void setVisiable(Boolean visiable) {
        this.visiable = visiable;
    }
}