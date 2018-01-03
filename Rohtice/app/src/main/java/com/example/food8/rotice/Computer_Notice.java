package com.example.food8.rotice;

/**
 * Created by food8 on 2017-12-29.
 */
//컴퓨터 공학과 공지사항 리스트 객체
public class Computer_Notice {
    private String Title = "";   //제목
    private String Content_URL = ""; //해당 URL

    //생성자 인자값 : 제목, URL
    public Computer_Notice(String Title, String Content_URL){
        this.Title = Title;
        this.Content_URL = Content_URL;
    }
    //setter 메소드
    public void setTitle(String Title){
        this.Title = Title;
    }
    public void setContent_URL(String Content_URL){
        this.Content_URL = Content_URL;
    }

    //getter 메소드
    public String getTitle(){
        return this.Title;
    }
    public String getContent_URL(){
        return this.Content_URL;
    }

}
