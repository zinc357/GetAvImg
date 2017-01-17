package com.zinc3.getavimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String B_URL = "http://www.bilibili.com/video/";
    private EditText editText;
    private TextView imgSrcTextView;
    private ImageView imageView;
    private String imgSrc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                editText = (EditText) findViewById(R.id.editText);
                String avNum = editText.getText().toString();
                Document document = null;
                try {
                    document = Jsoup.connect(B_URL + avNum).get();
                    L.e(document.html());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final Element element = document.select("[class=cover_image]").first();
                // 在UI线程中刷新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            imgSrcTextView = (TextView) findViewById(R.id.imgSrc);
                            imgSrc = element.attr("src");
                            imgSrcTextView.setText(imgSrc);
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity.this,"视频不存在，或该视频只有会员可以享用",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }).start();

    }
}
