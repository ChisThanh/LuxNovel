package project.luxnovel.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class HelperNonScroll extends ListView
{
    public HelperNonScroll(Context context)
    {
        super(context);
    }

    public HelperNonScroll(Context context, AttributeSet attribute)
    {
        super(context, attribute);
    }

    public HelperNonScroll(Context context, AttributeSet attribute, int style)
    {
        super(context, attribute, style);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        if (canScroll()) getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(event);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (canScroll()) getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }

    private boolean canScroll()
    {
        return getChildAt(0) != null && (getFirstVisiblePosition() > 0 || getChildAt(0).getTop() < getPaddingTop());
    }
}
