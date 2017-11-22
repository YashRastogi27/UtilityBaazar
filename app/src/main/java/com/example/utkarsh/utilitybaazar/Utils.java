package com.example.utkarsh.utilitybaazar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Yash on 22-11-2017.
 */

public class Utils {
    public static void switchFragment(int id, Fragment fragment, FragmentActivity activity){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}
