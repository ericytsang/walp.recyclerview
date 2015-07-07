package gmail.surpluset.recyclerviewanimations.util;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

public class RecyclerViewUtils
{
    private static final RecyclerViewDisabler RECYCLER_VIEW_DISABLER = new RecyclerViewDisabler();

    // public interface

    public static void disableInputs(RecyclerView recyclerView)
    {
        recyclerView.addOnItemTouchListener(RECYCLER_VIEW_DISABLER);
    }

    public static void enableInputs(RecyclerView recyclerView)
    {
        recyclerView.removeOnItemTouchListener(RECYCLER_VIEW_DISABLER);
    }

    // RecyclerView.OnItemTouchListener

    private static class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener
    {
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
}