package org.svcetedu.www.csemobileapp.StudentRegistration;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.svcetedu.www.csemobileapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Syllabus extends Fragment {
        private View mMainView;
    ProgressBar progressBar;
    WebView webView;
    final static String myBlogAddr = "http://svcetedu.org/wp/cse-syllabus/";
    String myUrl;

    public Syllabus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView=inflater.inflate(R.layout.fragment_syllabus, container, false);

        progressBar=(ProgressBar)mMainView.findViewById(R.id.progressbar);
        webView=(WebView)mMainView.findViewById(R.id.webview2);

        if(savedInstanceState!=null)
        {
            webView.restoreState(savedInstanceState);

        }
        else
        {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(false);
            webView.getSettings().setLoadWithOverviewMode(false);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setBackgroundColor(Color.WHITE);
            webView.setWebViewClient(new ourViewClient());
            webView.setWebChromeClient(new WebChromeClient()
                                       {
                                           public void onProgressChanged(WebView view,int progress)
                                           {
                                               progressBar.setProgress(progress);
                                               if(progress<100 && progressBar.getVisibility()==ProgressBar.GONE)
                                               {
                                                   progressBar.setVisibility(ProgressBar.VISIBLE);
                                               }
                                               if (progress==100)
                                               {
                                                   progressBar.setVisibility(ProgressBar.GONE);
                                               }

                                           }
                                       }

            );


        }
        if(myUrl == null){
            myUrl = myBlogAddr;
        }
        webView.loadUrl(myUrl);


        return mMainView;
    }

    public class ourViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView v, String url) {
            if(url.contains("http://svcetedu.org/wp/cse-syllabus/"))
            {
                v.loadUrl(url);
                CookieManager.getInstance().setAcceptCookie(true);

            }
            else
            {
                Uri uri= Uri.parse(url);
                startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW,uri),"Choose browser"));
            }
            return  true;
        }


        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view,url);
        }
    }


    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);





    }

}
