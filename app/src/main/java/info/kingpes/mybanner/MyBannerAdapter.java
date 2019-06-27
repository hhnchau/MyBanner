package info.kingpes.mybanner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBannerAdapter extends PagerAdapter {

    public MyBannerAdapter() {
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BannerEnum bannerEnum = BannerEnum.values()[position];
        View v = LayoutInflater.from(container.getContext()).inflate(bannerEnum.getLayoutResId(), container, false);

        TextView txt = v.findViewById(R.id.txt);
        txt.setText(container.getContext().getString(bannerEnum.getTitleResId()));

        ImageView img = v.findViewById(R.id.img);
        img.setImageResource(bannerEnum.getImageResId());

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) onClickListener.onListener();
            }
        });

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return BannerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private OnClickListener onClickListener;


    public interface OnClickListener {
        void onListener();
    }
}
