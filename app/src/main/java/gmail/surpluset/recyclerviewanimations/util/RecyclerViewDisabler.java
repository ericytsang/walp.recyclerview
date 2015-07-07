package gmail.surpluset.recyclerviewanimations.util;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

public class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener
{
    public static final RecyclerViewDisabler INSTANCE = new RecyclerViewDisabler();

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv,MotionEvent e)
    {
        return true;
    }

    @Override
    public void onTouchEvent(RecyclerView rv,MotionEvent e)
    {
        // do nothing
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept)
    {
        // do nothing
    }
}