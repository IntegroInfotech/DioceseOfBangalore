package app.integro.dioceseofbangalore.customTablayout;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class CustomTabLayout extends TabLayout {
    Context context;

    public CustomTabLayout(Context context) {
        super(context);
        this.context = context;
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        this.removeAllTabs();
        Typeface typeface;
        try {
            typeface = Typeface.createFromAsset(getContext().getAssets(), "font/slabo27px_regular.ttf");
        } catch (Exception e) {
            typeface =null;
        }
        ViewGroup slidingTabStrip = (ViewGroup) getChildAt(0);
        PagerAdapter adapter = viewPager.getAdapter();
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            Tab tab = this.newTab();
            this.addTab(tab.setText(adapter.getPageTitle(i)));
            AppCompatTextView view = (AppCompatTextView) ((ViewGroup) slidingTabStrip.getChildAt(i)).getChildAt(1);
            if(typeface!= null) {
                view.setTypeface(typeface, Typeface.BOLD);
            }
        }
    }
}