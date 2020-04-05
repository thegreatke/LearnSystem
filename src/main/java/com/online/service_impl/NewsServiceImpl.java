package com.online.service_impl;

import com.online.mapper.NewsMapper;
import com.online.model.News;
import com.online.service.NewsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");


    @Override
    public String publishNews(String title, Date date, String content, String sender, String tag) {

        try {

            String strName = dateFormat.format(date);
            Date newDate = dateFormat.parse(strName);
            News news = new News(title, newDate, content, sender, tag);
            newsMapper.pulishNews(news);
        } catch (Exception e) {
            return "publish failed!";
        }
        return "publish successfully!";
    }


    @Override
    public String deleteNews(int id) {
        try {
            newsMapper.deleteOneNews(id);
        } catch (Exception e) {
            return "delete failed!";
        }
        return "delete successfully!";
    }

    @Override
    public JSONObject findAllTagNews(String tag) {
        return null;
    }

    @Override
    public JSONObject findAllNews() {
        List<News> allNews = newsMapper.findAllNews();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (News news : allNews) {
            jsonObject = new JSONObject();
            jsonObject.put("title", news.getTitle());
            jsonObject.put("id", news.getId());
            jsonObject.put("date", dateFormat.format(news.getDate()));  //格式化util.date -> str
            jsonObject.put("content", news.getContent());
            jsonObject.put("sender", news.getSender());
            jsonObject.put("tag", news.getTag());
            jsonArray.add(jsonObject);
        }
        jsonObject = new JSONObject();
        jsonObject.put("result", jsonArray);
        return jsonObject;
    }

    @Override
    public String findTitleById(int id) {
        return newsMapper.findTitleById(id);
    }
}

























