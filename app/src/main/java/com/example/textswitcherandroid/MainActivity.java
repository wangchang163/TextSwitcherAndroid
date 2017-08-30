package com.example.textswitcherandroid;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextSwitcher sw, sw1, sw2, sw3;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw = (TextSwitcher) findViewById(R.id.tv_sw);
        sw.setFactory(mFactory);
        sw1 = (TextSwitcher) findViewById(R.id.tv_sw1);
        sw1.setFactory(mFactory);
        sw2 = (TextSwitcher) findViewById(R.id.tv_sw2);
        sw2.setFactory(mFactory);
        sw3 = (TextSwitcher) findViewById(R.id.tv_sw3);
        sw3.setFactory(mFactory);
        Animation in = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_right);
        Animation in1 = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_top);
        Animation out1 = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_bottom);
        Animation in2 = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_right);
        Animation out2 = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_left);
        Animation in3 = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_bottom);
        Animation out3 = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_top);
        sw.setInAnimation(in);
        sw.setOutAnimation(out);
        sw1.setInAnimation(in1);
        sw1.setOutAnimation(out1);
        sw2.setInAnimation(in2);
        sw2.setOutAnimation(out2);
        sw3.setInAnimation(in3);
        sw3.setOutAnimation(out3);
    }

    private ViewSwitcher.ViewFactory mFactory = new ViewSwitcher.ViewFactory() {

        @Override
        public View makeView() {
            // Create a new TextView
            TextView t = new TextView(MainActivity.this);
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            t.setTextAppearance(MainActivity.this, R.style.Text_Medium_Style);
            t.setPadding(20, 20, 20, 20);
            return t;
        }
    };

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("《秋风辞》");
        list.add("秋风起兮白云飞");
        list.add("草木黄落兮雁南归");
        list.add("兰有秀兮菊有芳");
        list.add("怀佳人兮不能忘");
        list.add("泛楼船兮济汾河");
        list.add("横中流兮扬素波");
        list.add("箫鼓鸣兮发棹歌");
        list.add("欢乐极兮哀情多");
        list.add("少壮几时兮奈老何");
        list.add("完！");
        return list;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int position = msg.what;
            sw.setText(getData().get(position));
            sw1.setText(getData().get(position));
            sw2.setText(getData().get(position));
            sw3.setText(getData().get(position));
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(mCount);
            mCount++;
            if (mCount >= getData().size()) {
                mCount = 0;
            }
            handler.postDelayed(runnable, 2000);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

}
