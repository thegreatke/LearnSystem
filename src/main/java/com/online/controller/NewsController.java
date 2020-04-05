package com.online.controller;


import com.online.model.Teacher;
import com.online.service.NewsService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@ResponseBody
public class NewsController {


    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/publishNews", method = RequestMethod.POST)

    public String publishNews(@RequestParam("title") String title,
//                            @RequestParam("id") int id,
                              @RequestParam("date") Date date,
                              @RequestParam("content") String content,
                              @RequestParam("sender") String sender,
                              @RequestParam("tag") String tag
    ) {
        return newsService.publishNews(title, date, content, sender, tag);
    }


    @RequestMapping(value = "/findAllNews", method = RequestMethod.GET)
    public JSONObject findAllNews() {
        return newsService.findAllNews();
    }


    @RequestMapping(value = "/deleteOneNew", method = RequestMethod.POST)
    public String findAllNews(@RequestParam("id") int id) {
        String title = newsService.findTitleById(id);
        if (title == null) return "id is not exist, failed!";
        else return newsService.deleteNews(id);
    }


    @RequestMapping(value = "/getClassTime", method = RequestMethod.GET)
    public String getClassTime() {
        if (Teacher.getClassTime() != null) return Teacher.getClassTime();
        return "老师还未设置时间，请稍后查询";
    }

    @RequestMapping(value = "/setClassTime", method = RequestMethod.POST)
    public String setClassTime(@RequestParam("classtime") String time) {
        Teacher.setClassTime(time);
        return "修改成功";
    }

}
