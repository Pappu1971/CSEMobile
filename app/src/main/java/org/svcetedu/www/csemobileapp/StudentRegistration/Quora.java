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
public class Quora extends Fragment {

    private  View mMain;
    private WebView mWeb;
    private ProgressBar mProgressbar;


    public Quora() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMain= inflater.inflate(R.layout.fragment_quora, container, false);

        mProgressbar = (ProgressBar) mMain.findViewById(R.id.quoraProgressbar);
        mWeb = (WebView) mMain.findViewById(R.id.quoraWebView); //This is the id you gave for webview

        //------------------------------------- to over ride keyboard error ------------(1)
        mWeb.setWebViewClient(new myWebClient());
        mWeb.getSettings().setJavaScriptEnabled(true);
        //----------------------------------------------------------------------------

        mWeb.getSettings().getCacheMode();
        mWeb.getSettings().getAllowFileAccess();
        mWeb.getSettings().getAllowContentAccess();
        mWeb.getSettings().getSaveFormData();

        mWeb.getSettings().setSupportZoom(false);       //Zoom Control on web (You don't need this
        //if ROM supports Multi-Touch
        mWeb.getSettings().setBuiltInZoomControls(false); //Enable Multitouch if supported by ROM
        mWeb.setBackgroundColor(Color.parseColor("#FFFFFF"));
        mWeb.getSettings().setUseWideViewPort(true);
        mWeb.getSettings().setLoadWithOverviewMode(false);


        // Load URL
        mWeb.loadUrl("https://m.quora.com/");

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
            mProgressbar.setVisibility(View.VISIBLE);
            //  multi_per.setVisibility(ProgressBar.GONE);

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            mProgressbar.setVisibility(View.GONE);
            //  multi_per.setVisibility(ProgressBar.VISIBLE);

        }
    }



}
