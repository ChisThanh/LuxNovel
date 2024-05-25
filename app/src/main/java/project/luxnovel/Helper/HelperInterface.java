package project.luxnovel.Helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Activity.ActivityAccount;
import project.luxnovel.Activity.ActivityHome;
import project.luxnovel.Activity.ActivityLibrary;
import project.luxnovel.Activity.ActivityLogin;
import project.luxnovel.Activity.ActivityRead;
import project.luxnovel.Activity.ActivitySearch;
import project.luxnovel.Activity.ActivitySettings;
import project.luxnovel.Activity.ActivityWrite;
import project.luxnovel.R;

public class HelperInterface
{
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

    public static void linkDrawer(Activity activity, DrawerLayout drawer_layout, Toolbar toolbar, NavigationView navigation_view)
    {
        toolbar.setNavigationOnClickListener(view -> drawer_layout.openDrawer(GravityCompat.START));

        navigation_view.setNavigationItemSelectedListener(menu_item ->
        {
            int id = menu_item.getItemId();

            if(id == R.id.iMenu_mDrawer_Home)
            {
                drawer_layout.closeDrawer(GravityCompat.START);
                activity.startActivity(new Intent(activity, ActivityHome.class));
            }
            else if(id == R.id.iMenu_mDrawer_Library)
            {
                drawer_layout.closeDrawer(GravityCompat.START);
                activity.startActivity(new Intent(activity, ActivityLibrary.class));
            }
            else if(id == R.id.iMenu_mMenu_Search)
            {
                drawer_layout.closeDrawer(GravityCompat.START);
                activity.startActivity(new Intent(activity, ActivitySearch.class));
            }
            else if(id == R.id.iMenu_mDrawer_Read)
            {
                drawer_layout.closeDrawer(GravityCompat.START);
                activity.startActivity(new Intent(activity, ActivityRead.class));
            }
            else if(id == R.id.iMenu_mDrawer_Write)
            {
                drawer_layout.closeDrawer(GravityCompat.START);
                activity.startActivity(new Intent(activity, ActivityWrite.class));
            }
            else if(id == R.id.iMenu_mDrawer_Account)
            {
                drawer_layout.closeDrawer(GravityCompat.START);
                activity.startActivity(new Intent(activity, ActivityAccount.class));
            }
            else if(id == R.id.iMenu_mDrawer_Settings)
            {
                drawer_layout.closeDrawer(GravityCompat.START);
                activity.startActivity(new Intent(activity, ActivitySettings.class));
            }
            else if(id == R.id.iMenu_mDrawer_Logout)
            {
                drawer_layout.closeDrawer(GravityCompat.START);
                activity.startActivity(new Intent(activity, ActivityLogin.class));
            }

            return true;
        });
    }
}
