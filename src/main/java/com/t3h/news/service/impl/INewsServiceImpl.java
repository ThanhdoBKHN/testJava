package com.t3h.news.service.impl;

import com.t3h.news.dao.INewsDao;
import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.InsertNewsRequest;
import com.t3h.news.service.INewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class INewsServiceImpl implements INewsService {

    private INewsDao iNewsDao;

    public INewsServiceImpl(INewsDao iNewsDao) {
        this.iNewsDao = iNewsDao;
    }

    public List<NewsModel> getList(){
        return iNewsDao.getList();
    }

    @Override
    public NewsModel insert(InsertNewsRequest insertNewsRequest) {
        int newsId = iNewsDao.insert(insertNewsRequest);

        NewsModel newsModelResponse = null;
        for (NewsModel newsModel:this.getList()
             ) {
            if (newsModel.getId() == newsId){
                newsModelResponse = newsModel;
            }
        }
        return newsModelResponse;
    }

    @Override
    public List<NewsModel> findByProperties(int numberAccess, int censor) {
        return iNewsDao.findByProperties(numberAccess,censor);
    }

    @Override
    public NewsModel update(int id,InsertNewsRequest insertNewsRequest) {
        iNewsDao.updateById(id, insertNewsRequest);
        NewsModel newsModelResponse = null;
        for (NewsModel newsModel:this.getList()
        ) {
            if (newsModel.getId() == id){
                newsModelResponse = newsModel;
            }
        }
        return newsModelResponse;
    }

    @Override
    public List<NewsModel> delete(int id) {
        iNewsDao.delete(id);
        return iNewsDao.getList();
    }
}