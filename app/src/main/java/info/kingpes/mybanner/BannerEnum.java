package info.kingpes.mybanner;

public enum BannerEnum {
    RED(R.string.string1, R.drawable.first, R.layout.my_banner_item),
    BLUE(R.string.string2,R.drawable.second, R.layout.my_banner_item),
    ORANGE(R.string.string3,R.drawable.third, R.layout.my_banner_item);

    private int mTitleResId;
    private int mImageResId;
    private int mLayoutResId;

    BannerEnum(int titleResId, int imageResId, int layoutResId) {
        mTitleResId = titleResId;
        mImageResId = imageResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
