package com.example.bookshelf;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowCompat; // Thêm import WindowCompat

import com.example.bookshelf.api.ApiClient;
import com.example.bookshelf.api.ApiService;
import com.example.bookshelf.api.models.BookAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// (Loại bỏ import BottomNavigationView vì không dùng)

public class PreviewActivity extends AppCompatActivity {
    WebView wbPreview;
    ImageButton btn_back;
    private final ApiService api = ApiClient.getClient().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Thiết lập Edge-to-Edge
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.preview_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        Intent intent = getIntent();
        String bookId = intent.getStringExtra("bookId");

        wbPreview = findViewById(R.id.wbPreview);
        WebSettings webSettings = wbPreview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        setUpWebView();
        loadWebViewPreview(bookId);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadWebViewPreview(String bookId) {
        Call<BookAPI> call = api.getBook(bookId);
        call.enqueue(new Callback<BookAPI>() {
            @Override
            public void onResponse(Call<BookAPI> call, Response<BookAPI> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BookAPI bookAPI = response.body();
                    String link = bookAPI.getBookInfo().getPreviewLink();

                    link = link.replace("http://", "https://");
                    wbPreview.loadUrl(link);
                }
                else {
                    Log.d("Preview Activity", "Load web view error");
                }
            }

            @Override
            public void onFailure(Call<BookAPI> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setUpWebView() {
        WebSettings webSettings = wbPreview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setLoadsImagesAutomatically(true);
        wbPreview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wbPreview.setInitialScale(1);

        webSettings.setUserAgentString(
                "Mozilla/5.0 (iPhone; CPU iPhone OS 17_0 like Mac OS X) " +
                        "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Mobile/15E148 Safari/604.1"
        );

        wbPreview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e("WebView", "Lỗi tải trang: " + error.getDescription());
            }
        });

        wbPreview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e("WebView-JS", consoleMessage.message() + " -- From line "
                        + consoleMessage.lineNumber() + " of "
                        + consoleMessage.sourceId());
                return true;
            }
        });

        wbPreview.setWebChromeClient(new WebChromeClient());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }
}
