package com.example;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class QuickReaderView extends TextView {
    private static final long MINUTE = 60000;
    private long delay = 1000; // 60 words per second by default

    public QuickReaderView(Context context) {
        super(context);
    }

    public QuickReaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuickReaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // in words per second
    public QuickReaderView setDelay(long wordsPerSecond) {
        this.delay = MINUTE / wordsPerSecond;
        return this;
    }

    public void showText(String text) {
        final String words[] = text.split(" ");
        final Activity activity = (Activity)getContext();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(final String word : words) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setText(word);
                        }
                    });
                    sleep(delay);
                }
            }
        }).start();
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {

        }
    }
}