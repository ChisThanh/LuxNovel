package project.luxnovel.Helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import project.luxnovel.R;

public class HelperShared
{
    public static void removeAnnotation(TextView annotation_view)
    {
        annotation_view.setText("");
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void toggleVisibility(EditText visibility_user)
    {
        visibility_user.setOnTouchListener((view, motion_event) ->
        {
            if (motion_event.getAction() == MotionEvent.ACTION_UP)
            {
                Drawable drawable_end = visibility_user.getCompoundDrawables()[2];

                if (drawable_end != null && motion_event.getRawX() >= (visibility_user.getRight() - drawable_end.getBounds().width()))
                {
                    int selection_start = visibility_user.getSelectionStart();
                    int selection_end = visibility_user.getSelectionEnd();

                    if (visibility_user.getTransformationMethod() instanceof PasswordTransformationMethod)
                    {
                        visibility_user.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        visibility_user.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.password, 0, R.drawable.visibility, 0);
                    }
                    else
                    {
                        visibility_user.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        visibility_user.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.password, 0, R.drawable.visibility_off, 0);
                    }

                    visibility_user.setSelection(selection_start, selection_end);

                    return true;
                }
            }

            return false;
        });


    }
}
