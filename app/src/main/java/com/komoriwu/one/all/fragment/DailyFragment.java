package com.komoriwu.one.all.fragment;

import com.komoriwu.one.all.fragment.mvp.DailyPresenter;
import com.komoriwu.one.all.listener.OnItemVideoClickListener;
import com.komoriwu.one.model.bean.FindBean;
import com.komoriwu.one.utils.Constants;
import com.komoriwu.one.utils.Utils;

import java.util.HashMap;

/**
 * Created by KomoriWu
 * on 2017-12-29.
 */

public class DailyFragment extends CommonBaseFragment<DailyPresenter> implements OnItemVideoClickListener {
    private String mDate;
    private String mNum;

    @Override
    protected void setInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void refreshData(FindBean findBean) {
        super.refreshData(findBean);
        if (isLoadMore) {
            mDate = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[0];
            mNum = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[1].split(
                    "=")[1];
        }
    }

    @Override
    public void showMoreDate(FindBean findBean) {
        super.showMoreDate(findBean);
        if (isLoadMore) {
            mDate = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[0];
            mNum = Utils.formatUrl(findBean.getNextPageUrl()).split("&")[1].split(
                    "=")[1];
        }
    }

    @Override
    public void onLoadList() {
        presenter.loadList();
    }

    @Override
    public void onLoadMoreList() {
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put(Constants.DATE, mDate);
        stringHashMap.put(Constants.NUM, mNum);
        presenter.loadMoreList(stringHashMap);
    }
}
