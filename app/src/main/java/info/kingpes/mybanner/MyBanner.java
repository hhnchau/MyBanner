package info.kingpes.mybanner;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyBanner extends FrameLayout {
    private ViewPager viewpager;
    private MyBannerAdapter adapter;
    private int currentPage = 0;

    public MyBanner(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_banner, this, true);

        viewpager = view.findViewById(R.id.viewpager);
        adapter = new MyBannerAdapter();
        viewpager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewpager, true);

        changeDotIndicator(tabLayout);

        adapter.setOnClickListener(new MyBannerAdapter.OnClickListener() {
            @Override
            public void onListener() {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                int current = viewpager.getCurrentItem();
                if (current == adapter.getCount() - 1 || currentPage == 0) {
                    int previousState = currentPage;
                    currentPage = i;
                    if (previousState == 1 && currentPage == 0) {
                        viewpager.setCurrentItem(current == 0 ? adapter.getCount() - 1 : 0);
                    }
                }
            }
        });

        startAutoScroll();
    }

    private void changeDotIndicator(TabLayout tabLayout) {
        ViewGroup.LayoutParams params = tabLayout.getLayoutParams();
        params.height = 10;
        params.width = 1000;
        tabLayout.setLayoutParams(params);
    }

    private void startAutoScroll() {
        Timer timer = new Timer();
        final Handler handler = new Handler();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage == adapter.getCount()) {
                            currentPage = 0;
                        }
                        viewpager.setCurrentItem(currentPage, true);
                        currentPage += 1;
                    }
                });
            }
        }, 500, 3 * 1000);
    }
}
