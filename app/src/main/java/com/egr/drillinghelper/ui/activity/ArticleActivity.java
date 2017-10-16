package com.egr.drillinghelper.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebView;

import com.egr.drillinghelper.R;
import com.egr.drillinghelper.bean.response.Article;
import com.egr.drillinghelper.common.MyConstants;
import com.egr.drillinghelper.contract.ArticleContract;
import com.egr.drillinghelper.presenter.ArticlePresenterImpl;
import com.egr.drillinghelper.ui.base.BaseMVPActivity;
import com.egr.drillinghelper.ui.widgets.DialogHelper;
import com.egr.drillinghelper.utils.StringUtils;
import com.egr.drillinghelper.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import cc.cloudist.acplibrary.ACProgressFlower;

import static com.egr.drillinghelper.common.MyConstants.PATH;

/**
 * author lzd
 * date 2017/9/29 20:55
 * 类描述：文章详情
 */

public class ArticleActivity extends BaseMVPActivity<ArticleContract.View,
        ArticlePresenterImpl> implements ArticleContract.View {
    @BindView(R.id.wv)
    WebView webView;
    private ACProgressFlower mDialog;

    @Override
    public int returnLayoutID() {
        return R.layout.activity_article;
    }

    @Override
    public void TODO(Bundle savedInstanceState) {
        setupActionBar(R.string.explain, true);
        setActionbarBackground(R.color.white);

        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        mDialog = DialogHelper.openiOSPbDialog(this, getString(R.string.waiting));
        String id = getIntent().getStringExtra(KEY_INTENT);
        String catalogId = getIntent().getStringExtra("catalogId");

        if (!mDialog.isShowing())
            mDialog.show();
        presenter.getArticle(catalogId, id);
    }

    @Override
    public ArticlePresenterImpl createPresenter() {
        return new ArticlePresenterImpl();
    }

    @Override
    public void getArticleSuccess(Article article) {
        mDialog.dismiss();

        if (article != null) {
            setActionBarTitle(article.getTitle());
            String content = article.getContent();
            if (article.isCache()) {
                content = StringUtils.updateHtmlTag(content, "img", "src");
            }
            webView.loadDataWithBaseURL("", content, "text/html", "utf-8", "");
        }

    }

    @Override
    public void getArticleFail(String msg) {
        mDialog.dismiss();
        ToastUtils.show(this, msg);
    }
}
