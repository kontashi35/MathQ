package com.blogspot.techtibet.mathq;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by WS7 on 3/24/2018.
 */

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context){
        this.context=context;

    }
    //array of image
    public int[] slide_image={
       R.drawable.genius,
            R.drawable.dont,
            R.drawable.solve,
    };
    public String[] slide_heading={
         "Genius?",
            "Dumb?",
            "Let's Decide!"
    };
    public String[] slide_desc={
      "Are you a Genius?",
            "I don't think You are",
            "See who you are"
    };
    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slidelayout,container,false);
        CircleImageView mSlideImg=(CircleImageView)view.findViewById(R.id.slide_img);
        TextView mSlideHead=(TextView)view.findViewById(R.id.slide_head);
        TextView mSlideDesc=(TextView)view.findViewById(R.id.slide_desc);
        mSlideImg.setImageResource(slide_image[position]);
        mSlideHead.setText(slide_heading[position]);
        mSlideDesc.setText(slide_desc[position]);

        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
container.removeView((RelativeLayout)object);
    }
}
