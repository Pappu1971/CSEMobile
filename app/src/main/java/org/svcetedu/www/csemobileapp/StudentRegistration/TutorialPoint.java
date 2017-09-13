package org.svcetedu.www.csemobileapp.StudentRegistration;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.svcetedu.www.csemobileapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialPoint extends Fragment {
    ProgressBar pb_per;
    WebView mWebView;
    private View mMain;
    public TutorialPoint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMain= inflater.inflate(R.layout.fragment_tutorial_point, container, false);


        pb_per = (ProgressBar) mMain.findViewById(R.id.progressBar_book1);
        mWebView = (WebView) mMain.findViewById(R.id.tuWeb); //This is the id you gave for webview

        //------------------------------------- to over ride keyboard error ------------(1)
        mWebView.setWebViewClient(new myWebClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        //----------------------------------------------------------------------------


        mWebView.getSettings().setSupportZoom(false);       //Zoom Control on web (You don't need this
        //if ROM supports Multi-Touch
        mWebView.getSettings().setBuiltInZoomControls(false); //Enable Multitouch if supported by ROM
        mWebView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(false);


        // Load URL
        mWebView.loadUrl("https://www.tutorialspoint.com/");

        return mMain;
    }
    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            pb_per.setVisibility(View.VISIBLE);
            //  multi_per.setVisibility(ProgressBar.GONE);

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            pb_per.setVisibility(View.GONE);
            //  multi_per.setVisibility(ProgressBar.VISIBLE);

        }
    }



}
