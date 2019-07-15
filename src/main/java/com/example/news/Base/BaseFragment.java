package com.example.news.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Mohamed Nabil Mohamed on 5/18/2019.
 * m.nabil.fci2015@gmail.com
 */
public class BaseFragment extends Fragment {

    public BaseActivity activity;
    AlertDialog alertDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= ((BaseActivity) context);
    }

    public AlertDialog showConfirmationDialog(int title, int message, int posText,
                                              AlertDialog.OnClickListener posAction, boolean isCancelable){
     return activity.showConfirmationDialog(title,message,posText,posAction,isCancelable);
    }
    public AlertDialog showMessage(int title,int message,int posText){
        return activity.showMessage(title,message,posText);
    }
    public AlertDialog showConfirmationDialog(String title,String message,String posText,
                                              AlertDialog.OnClickListener posAction,boolean isCancelable){
        return
                activity.showConfirmationDialog(title,message,posText,posAction,isCancelable);

    }
    public AlertDialog showMessage(String title,String message,String posText){
        return activity.showMessage(title,message,posText);
    }

    ProgressDialog progressDialog;
    public ProgressDialog showProgressBar(int message){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }
    public void hideProgressDialog(){
        if(progressDialog!=null)
            progressDialog.dismiss();
    }


}
