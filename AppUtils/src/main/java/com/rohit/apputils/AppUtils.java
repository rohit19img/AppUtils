package com.rohit.apputils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class AppUtils {

    public static void hideKeyboard(Context context){
        ((InputMethodManager)context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void showKeyboard(Context context){
        ((InputMethodManager)context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    public static ProgressDialog getProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.MyTheme);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setMessage("");
//        progressDialog.show();
//        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static boolean isValidEmail(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();

    }

    public static void customToast(Context context, String message){
        LayoutInflater inflater1 =((Activity)context).getLayoutInflater();
        View layout = inflater1.inflate(R.layout.custom_toast,
                (ViewGroup) ((Activity)context).findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 170);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

/*
    public static void showSuccess(Context context, String toast_text){
        new Flashbar.Builder((Activity) context)
                .gravity(Flashbar.Gravity.TOP)
                .duration(5000)
                .icon(R.drawable.logo)
                .showIcon()
                .message(toast_text)
                .enableSwipeToDismiss()
                .dismissOnTapOutside()
                .backgroundDrawable(R.drawable.success_bg)
                .titleColorRes(R.color.white)
                .messageColorRes(R.color.white)
                .enterAnimation(FlashAnim.with(context)
                        .animateBar()
                        .duration(750)
                        .alpha()
                        .overshoot())
                .exitAnimation(FlashAnim.with(context)
                        .animateBar()
                        .duration(400)
                        .accelerateDecelerate())
                .build()
                .show();
    }

    public static void showError(Context context, String toast_text){
        new Flashbar.Builder((Activity) context)
                .gravity(Flashbar.Gravity.TOP)
                .duration(5000)
                .message(toast_text)
                .icon(R.drawable.logo)
                .showIcon()
                .enableSwipeToDismiss()
                .dismissOnTapOutside()
                .backgroundDrawable(R.drawable.error_bg)
                .titleColorRes(R.color.white)
                .messageColorRes(R.color.white)
                .enterAnimation(FlashAnim.with(context)
                        .animateBar()
                        .duration(750)
                        .alpha()
                        .overshoot())
                .exitAnimation(FlashAnim.with(context)
                        .animateBar()
                        .duration(400)
                        .accelerateDecelerate())
                .build()
                .show();
    }

    public static void showError_withAction(Context context, String toast_text, Flashbar.OnActionTapListener listner){

        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Flashbar.Builder((Activity) context)
                        .gravity(Flashbar.Gravity.TOP)
                        .duration(5000)
                        .message(toast_text)
                        .icon(R.drawable.logo)
                        .showIcon()
                        .enableSwipeToDismiss()
                        .dismissOnTapOutside()
                        .backgroundDrawable(R.drawable.error_bg)
                        .titleColorRes(R.color.white)
                        .messageColorRes(R.color.white)
//                .positiveActionTextSizeInSp(12)
                        .positiveActionTextAppearance(R.style.positiveText)
                        .positiveActionText("Retry")
                        .positiveActionTapListener(listner)
                        .enterAnimation(FlashAnim.with(context)
                                .animateBar()
                                .duration(750)
                                .alpha()
                                .overshoot())
                        .exitAnimation(FlashAnim.with(context)
                                .animateBar()
                                .duration(400)
                                .accelerateDecelerate())
                        .build()
                        .show();
            }
        });
    }

    public static void NoInternet(Context context, Flashbar.OnActionTapListener listner){

        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Flashbar.Builder((Activity) context)
                        .gravity(Flashbar.Gravity.TOP)
                        .duration(5000)
                        .message("Internet connection lost")
                        .icon(R.drawable.logo)
                        .showIcon()
                        .enableSwipeToDismiss()
                        .dismissOnTapOutside()
                        .backgroundDrawable(R.drawable.error_bg)
                        .titleColorRes(R.color.white)
                        .messageColorRes(R.color.white)
//                .positiveActionTextSizeInSp(12)
                        .positiveActionTextAppearance(R.style.positiveText)
                        .positiveActionText("Retry")
                        .positiveActionTapListener(listner)
                        .enterAnimation(FlashAnim.with(context)
                                .animateBar()
                                .duration(750)
                                .alpha()
                                .overshoot())
                        .exitAnimation(FlashAnim.with(context)
                                .animateBar()
                                .duration(400)
                                .accelerateDecelerate())
                        .build()
                        .show();
            }
        });
    }
*/

    public static void setCountDownTimer(Context context,String match_date, TextView tv) {
        String sDate = "2017-09-08 10:05:00";
        String eDate = "2017-09-10 12:05:00";
        Date startDate = null, endDate = null;

        Calendar c = Calendar.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            c.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Kolkata")));
        }
        int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);
        int mYear1 = c.get(Calendar.YEAR);
        int mMonth1 = c.get(Calendar.MONTH);
        int mDay1 = c.get(Calendar.DAY_OF_MONTH);

        sDate = mYear1 + "-" + (mMonth1 + 1) + "-" + mDay1 + "T" + hour + ":" + minute + ":" + sec;
        eDate = match_date;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        try {
            startDate = dateFormat.parse(sDate);
            endDate = dateFormat.parse(eDate);
        } catch (ParseException e) {
            sDate = mYear1 + "-" + (mMonth1 + 1) + "-" + mDay1 + " " + hour + ":" + minute + ":" + sec;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            try {
                startDate = dateFormat.parse(sDate);
                endDate = dateFormat.parse(eDate);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            e.printStackTrace();
        }

        final long diffInMs = endDate.getTime() - startDate.getTime();

        final long hours1 = 1 * 60 * 60 * 1000;
        final long hours4 = 24 * 60 * 60 * 1000;
        final long hours48 = 48 * 60 * 60 * 1000;

        CountDownTimer cT = new CountDownTimer(diffInMs, 1000) {

            public void onTick(long millisUntilFinished) {
                if (diffInMs < hours1) {
                    tv.setText(String.format(String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) + "m : "
                            + String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + "s"));
                } else if (diffInMs < hours4) {
                    tv.setText(String.format(String.format("%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + "h : "
                            + String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + "m"));
                } else if (diffInMs < hours48) {
                    tv.setText(String.format(String.format("%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + "h"));

                } else {
                    tv.setText(String.format(String.format("%02d", TimeUnit.MILLISECONDS.toDays(millisUntilFinished)) + " days"));
                }
            }

            public void onFinish() {
                tv.setText("Time Over");
                if (context.getClass().getSimpleName().equals("ChallengesActivity")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setMessage("Match time over, Do check upcoming matches");
                    dialog.setTitle("Time Over");
                    dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ((Activity) context).finish();
                        }
                    });
                    dialog.setCancelable(false);
                    dialog.show();

                } else if (context.getClass().getSimpleName().equals("HomeActivity")){

                } else {
                    ((Activity) context).finish();
                }
            }
        };
        cT.start();
    }

    public static void setCountDownTimer_nonFinish(Context context,String match_date, TextView tv) {
        String sDate = "2017-09-08 10:05:00";
        String eDate = "2017-09-10 12:05:00";
        Date startDate = null, endDate = null;

        Calendar c = Calendar.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            c.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Kolkata")));
        }
        int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);
        int mYear1 = c.get(Calendar.YEAR);
        int mMonth1 = c.get(Calendar.MONTH);
        int mDay1 = c.get(Calendar.DAY_OF_MONTH);

        sDate = mYear1 + "-" + (mMonth1 + 1) + "-" + mDay1 + "T" + hour + ":" + minute + ":" + sec;
        eDate = match_date;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        try {
            startDate = dateFormat.parse(sDate);
            endDate = dateFormat.parse(eDate);
        } catch (ParseException e) {
            sDate = mYear1 + "-" + (mMonth1 + 1) + "-" + mDay1 + " " + hour + ":" + minute + ":" + sec;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            try {
                startDate = dateFormat.parse(sDate);
                endDate = dateFormat.parse(eDate);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            e.printStackTrace();
        }

        final long diffInMs = endDate.getTime() - startDate.getTime();

        final long hours1 = 1 * 60 * 60 * 1000;
        final long hours4 = 24 * 60 * 60 * 1000;
        final long hours48 = 48 * 60 * 60 * 1000;

        CountDownTimer cT = new CountDownTimer(diffInMs, 1000) {

            public void onTick(long millisUntilFinished) {
                if (diffInMs < hours1) {
                    tv.setText(String.format(String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) + "m : "
                            + String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + "s"));
                } else if (diffInMs < hours4) {
                    tv.setText(String.format(String.format("%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + "h : "
                            + String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + "m"));
                } else if (diffInMs < hours48) {
                    tv.setText(String.format(String.format("%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + "h"));

                } else {
                    tv.setText(String.format(String.format("%02d", TimeUnit.MILLISECONDS.toDays(millisUntilFinished)) + " days"));
                }
            }

            public void onFinish() {
                tv.setText("Live");
            }
        };
        cT.start();
    }


    public static boolean isValidPassword(String password){

        return  (password.length() > 3);

//        return Pattern.compile("^(?=.*?[A-Za-z])(?=.*?[0-9]).{8,}$")
//                .matcher(password).matches();
    }

    public static boolean isValidNumber(String number){
        return Pattern.compile("(0/91)?[6-9][0-9]{9}")
                .matcher(number).matches();
    }


    public static boolean isValidPincode(String number){
        return Pattern.compile("^[1-9]\\d{5}$")
                .matcher(number).matches();
    }

    public static boolean isValidName(String name){
        return Pattern.compile("([a-zA-Z]{3,30}\\s*)+")
                .matcher(name).matches();
    }

    public static boolean isValidTeamName(String name){
        return Pattern.compile("([a-zA-Z0-9_-]{3,30}\\s*)+")
                .matcher(name).matches();
    }

    public static boolean isValidPAN(String panNumber){
        return Pattern.compile("[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}")
                .matcher(panNumber).matches();
    }

    public static boolean isValidIFSC(String ifscCode){
        return Pattern.compile("[A-Za-z]{4}[0]{1}[A-Za-z0-9]{6}")
                .matcher(ifscCode).matches();
    }

    public static boolean isValidUPI(String ifscCode){
        return Pattern.compile("[a-zA-Z0-9]{2,256}@[a-zA-Z][a-zA-Z]{2,64}")
                .matcher(ifscCode).matches();
    }

    public static boolean isValidAadhaarNumber(String number){
        return Pattern.compile("^[0-9]{4}[ -]?[0-9]{4}[ -]?[0-9]{4}$")
                .matcher(number).matches();
    }

    public static boolean isValidAccountNumber(String number){
        return Pattern.compile("^[0-9]{6,18}$")
                .matcher(number).matches();
    }

    public static boolean isValidUrl(String urlString){
        try {
            URL url = new URL(urlString);
            return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
        } catch (MalformedURLException ignored) {

        }
        return false;
    }

    public static void showLog(String TAG,String message){
        Log.e(TAG, "DeveloperLog: "+message );
    }

    public static void showRetryOption( Context context, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Something went wrong...");
        builder.setMessage("Please retry....");
        builder.setNegativeButton("Retry", listener);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public static String StringifyNumber(String value){
        double amt = Double.parseDouble(value);
        String returnValue = "";
        if(amt > 10000000)
            returnValue = (String.format("%.1f", (amt/10000000)))+"Cr";
        else if(amt > 100000)
            returnValue = (String.format("%.1f", (amt/100000)))+"L";
        else if(amt > 1000)
            returnValue = (String.format("%.1f", (amt/1000)))+"K";
        else
            returnValue = String.valueOf(amt);

        return returnValue;
    }

    public static void setDate_text(String time,TextView tv){
        LocalDate dateToDo = null; //for example 2019-08-31
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat date_ = new SimpleDateFormat("dd MMMM, yyyy");

        String date = "";
        try {
            date_ = new SimpleDateFormat("yyyy-MM-dd");
            date = date_.format(dateFormat.parse(time));
        } catch (ParseException e) {
            date_ = new SimpleDateFormat("dd MMMM, yyyy");
            throw new RuntimeException(e);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateToDo = LocalDate.parse(date);

            LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
            LocalDate tomorrow = today.plusDays(1);

            if(dateToDo.equals(today)){
                tv.setText("Today");
            } else if (dateToDo.equals(tomorrow)){
                tv.setText("Tomorrow");
            } else {
                try {
                    tv.setText(date_.format(dateFormat.parse(time)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            try {
                tv.setText(date_.format(dateFormat.parse(time)));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void setTime_text(String time,TextView tv){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat time_ = new SimpleDateFormat("hh:mm a");

        try {
            tv.setText(time_.format(dateFormat.parse(time)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setDate_Time_text(String time,TextView tv){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat time_ = new SimpleDateFormat("dd MMM, yyyy hh:mm a");

        try {
            tv.setText(time_.format(dateFormat.parse(time)));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void AddUnderline(TextView link){
        String txt = link.getText().toString();
        SpannableString mSpannableString = new SpannableString(txt);
        mSpannableString.setSpan(new UnderlineSpan(), 0, mSpannableString.length(), 0);
        link.setText(mSpannableString);
    }

    public static String ProcessOverNumber(String number){
        if(number.equals("1"))
            return "1st";
        else if(number.equals("1"))
            return "2nd";
        else if(number.equals("1"))
            return "3rd";
        else
            return number+"th";

    }
}