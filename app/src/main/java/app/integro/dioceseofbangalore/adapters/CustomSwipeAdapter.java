package app.integro.dioceseofbangalore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import app.integro.dioceseofbangalore.R;
import app.integro.dioceseofbangalore.apis.ApiClients;
import app.integro.dioceseofbangalore.models.WordOfGod;

public class CustomSwipeAdapter extends PagerAdapter {
    private int size;
    private Context context;

    private List<WordOfGod> wordofgod;
    private String[] PageTitle = {"1st Reading", "Psalm", "Gospel"};
    private String[] PageTitle1 = {"1st Reading", "Psalm", "2nd Reading", "Gospel" };
    private String title = null;
    private String date;

    public CustomSwipeAdapter(Context context) {
        this.context = context;
        wordofgod = new ArrayList<>();
    }

    public CustomSwipeAdapter(Context context, List<WordOfGod> wordofgod,String date) {
        this.context = context;
        this.wordofgod = wordofgod;
        this.date=date;
    }

    public String getPageTitle(int position) {
        if (getCount() == 3)
            title = PageTitle[position];
        else if (getCount() == 4)
            title = PageTitle1[position];
        return title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getCount() {
        return wordofgod.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.gospel_layout, container, false);
        final WordOfGod word = wordofgod.get(position);
        TextView author =  view.findViewById(R.id.author);
        TextView gospelTitle =  view.findViewById(R.id.gospelTitle);
        final TextView wordOfGod =  view.findViewById(R.id.wordOfGod);
        Button button =  view.findViewById(R.id.share);

        author.setText(word.getVerse());
        gospelTitle.setText(word.getTitle());
        wordOfGod.setText(word.getReading());

        container.addView(view);

        final String uri = ApiClients.BASE_URL + "?date=" + word.getDate();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, date + " " + getPageTitle(position) + "\n\n" + "http://bangalorearchdiocesenews.org/bibleshare.php?id=" + word.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
