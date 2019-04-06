package com.example.boysandgirlsclubevents.Calendar.DailyView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boysandgirlsclubevents.Calendar.Event;
import com.example.boysandgirlsclubevents.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarDailyFragment extends Fragment
{
    private static final String TAG = "CalendarDailyFragment";
    private static final String DATE_KEY = "date";
    private static final String MONTH_KEY = "month";
    private static final String DAY_KEY = "day";

    private int mDate;
    private String mDay;
    private List<Event> mEvents;

    private TextView mDateText;
    private TextView mDayText;
    private RecyclerView mEventsRecyclerView;

    public static CalendarDailyFragment newInstance(int date, String month, String dayOfWeek)
    {
        Bundle args = new Bundle();
        args.putInt(DATE_KEY, date);
        args.putString(MONTH_KEY, month);
        args.putString(DAY_KEY, dayOfWeek);

        CalendarDailyFragment fragment = new CalendarDailyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null)
        {
            mDate = args.getInt(DATE_KEY);
            mDay = args.getString(DAY_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.calendar_daily_view, container, false);
        setUpDate(view);
        setUpEventsRecyclerView(view);
        return view;
    }

    private void setUpDate(View view)
    {
        mDateText = view.findViewById(R.id.tv_date_daily);
        mDayText = view.findViewById(R.id.tv_day_daily);
        mDateText.setText(String.valueOf(mDate));
        mDayText.setText(String.valueOf(mDay));
    }

    private void setUpEventsRecyclerView(View view)
    {
        mEvents = new ArrayList<>();
        //String title, String age, ClubLocation clubLocation, Calendar startTime, int duration
        Event event1 = new Event("Basketball", "8-14", Event.ClubLocation.AnnStreet, Calendar.getInstance(), 60, R.drawable.football);
        Event event2 = new Event("Leadership", "5-16", Event.ClubLocation.Columbia, Calendar.getInstance(), 180, R.drawable.torch);
        Event event3 = new Event("Study Help", "8-13", Event.ClubLocation.WaterStreet, Calendar.getInstance(), 440, R.drawable.homework);
        mEvents.add(event1);
        mEvents.add(event2);
        mEvents.add(event3);

        mEventsRecyclerView = view.findViewById(R.id.rv_events_daily);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mEventsRecyclerView.setLayoutManager(layoutManager);
        DailyViewAdapter dailyViewAdapter = new DailyViewAdapter(mEvents);
        mEventsRecyclerView.setAdapter(dailyViewAdapter);
    }
}
