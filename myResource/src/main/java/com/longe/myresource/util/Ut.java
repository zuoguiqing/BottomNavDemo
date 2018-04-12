package com.longe.myresource.util;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.longe.myresource.R;

import java.io.File;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

/**
 * Ut
 * 通用工具类
 *
 * @author ShiXiang
 * @version 1.0, 2016/10/31
 */
public class Ut {
    /*** dialog ***************************************************/
    public static AlertDialog getOriginalDialog(Context context, @Nullable Boolean canCancelOut, @Nullable Integer style) {
        AlertDialog dialog;
        if (style == null) {
            dialog = new AlertDialog.Builder(context).create();
        } else {
            dialog = new AlertDialog.Builder(context).create();
        }
        if (canCancelOut != null) {
            dialog.setCanceledOnTouchOutside(canCancelOut);
        } else {
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();

        return dialog;
    }

    public static AlertDialog getCustomDialogTransparent(Context context, int resId, @Nullable Boolean canCancelOut) {
        AlertDialog dialog = getOriginalDialog(context, canCancelOut, R.style.TransparentDialog);

        dialog.getWindow().setContentView(resId);

        return dialog;
    }

    public static AlertDialog getCustomDialog(Context context, int resId, @Nullable Boolean canCancelOut) {
        AlertDialog dialog = getOriginalDialog(context, canCancelOut, null);

        dialog.getWindow().setContentView(resId);

        return dialog;
    }

    public static AlertDialog getCustomDialog2(Context context, int resId, @Nullable Boolean canCancelOut) {
        AlertDialog dialog = getOriginalDialog(context, canCancelOut, null);

        dialog.getWindow().setContentView(resId);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        return dialog;
    }

    public static AlertDialog getCustomDialog(Context context, View view, @Nullable Boolean canCancelOut) {
        AlertDialog dialog = getOriginalDialog(context, canCancelOut, null);

        dialog.getWindow().setContentView(view);

        return dialog;
    }

    public static View getCustomView(Context context, int resId, @Nullable ViewGroup parent) {
        View view;
        if (parent == null) {
            view = LayoutInflater.from(context).inflate(resId, null, false);
        } else {
            view = LayoutInflater.from(context).inflate(resId, parent, false);
        }

        return view;
    }

    /**
     * @author ShiXiang
     * @version 1.0, 19/01/2018
     */
    public static AlertDialog getTextDialog(Context context, String content) {
        final AlertDialog dialog = getCustomDialogTransparent(context, R.layout.my_dialog_text, true);

        TextView tv = (TextView) dialog.findViewById(R.id.dl_content);
        tv.setText(content);
        TextView cancel = (TextView) dialog.findViewById(R.id.dl_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public static AlertDialog getTextDialog(Context context, String title, String content) {
        AlertDialog dialog = getTextDialog(context, content);

        TextView tv = (TextView) dialog.findViewById(R.id.dl_title);
        tv.setVisibility(View.VISIBLE);
        tv.setText(title);

        return dialog;
    }

    public static AlertDialog getConfirmDialog(Context context, String content) {
        final AlertDialog dialog = getCustomDialogTransparent(context, R.layout.my_dialog_confirm, true);

        TextView tv = (TextView) dialog.findViewById(R.id.dl_content);
        tv.setText(content);

        TextView cancel = (TextView) dialog.findViewById(R.id.dl_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public static AlertDialog getConfirmDialog(Context context, String title, String content) {
        AlertDialog dialog = getConfirmDialog(context, content);

        TextView tv = (TextView) dialog.findViewById(R.id.dl_title);
        tv.setVisibility(View.VISIBLE);
        tv.setText(title);

        return dialog;
    }

    //底部dialog
    public static Dialog getBottomDialog(Context context, int resId) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(resId, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(size.x);

        // 获取自定义Dialog布局中的控件
        // TODO: 19/12/2017

        // 定义Dialog布局和参数
        Dialog dialog = new Dialog(context, R.style.SheetDialog);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return dialog;
    }

    //进度dialog
    public static ProgressDialog getProgressDialog(Context context, String setTitle, String setMessage) {
        ProgressDialog dialog = new ProgressDialog(context, R.style.TransparentDialog);

        // 设置进度条风格，风格为圆形，旋转的
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // 设置ProgressDialog 标题
        dialog.setTitle(setTitle);
        // 设置ProgressDialog 提示信息
        dialog.setMessage(setMessage);
        // 设置ProgressDialog 的进度条是否不明确
        dialog.setIndeterminate(false);
        // 设置ProgressDialog 是否可以按退回按键取消
        dialog.setCancelable(true);
        // 设置ProgressDialog 显示位置
        dialog.getWindow().setGravity(Gravity.CENTER);

        return dialog;
    }

    /***  ***************************************************/
    public static String MD5(String pwd) {
        //用于加密的字符
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = pwd.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int length = md.length;
            char str[] = new char[length * 2];

            int k = 0;
            for (int i = 0; i < length; i++) {
                byte mdByte = md[i];
                str[k++] = md5String[mdByte >>> 4 & 0xF];
                str[k++] = md5String[mdByte & 0xF];
            }

            // 返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    //验证邮箱格式
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /***  ***************************************************/
    public static SharedPreferences getSharedPreferences(Context context, String name) {
        return context.getSharedPreferences(name, MODE_PRIVATE);
    }

    public static String s(Object object) {
        if (object == null) {
            return "";
        } else {
            return object + "";
        }
    }

    public static String s(Object object, String def) {
        if (object == null) {
            return def;
        } else {
            return object + "";
        }
    }

    /**
     * 酒店预订弹窗———内容
     */
    public static String getHotelBookText(Object object) {
        if (object == null || object.toString().trim().isEmpty()) {
            return "——";
        } else {
            return object + "";
        }
    }

    /*** log ***************************************************/
    private static final boolean isTest = true;
    private static final String TAG = "====";

    public static String getTag(Class c) {
        return c.getSimpleName();
    }

    public static void p(Object msg) {
        if (!isTest)
            return;

        System.out.println(TAG + msg);
    }

    public static void i(@Nullable String tag, Object msg) {
        if (!isTest)
            return;

        if (tag == null) {
            Log.i(TAG, msg + "");
        } else {
            Log.i(TAG + tag, msg + "");
        }

    }

    public static void d(@Nullable String tag, Object msg) {
        if (!isTest)
            return;

        if (tag == null) {
            Log.d(TAG, msg + "");
        } else {
            Log.d(TAG + tag, msg + "");
        }

    }

    public static void e(@Nullable String tag, Object msg) {
        if (!isTest)
            return;

        if (tag == null) {
            Log.e(TAG, msg + "");
        } else {
            Log.e(TAG + tag, msg + "");
        }

    }

    public static void w(@Nullable String tag, Object msg) {
        if (!isTest)
            return;

        if (tag == null) {
            Log.w(TAG, msg + "");
        } else {
            Log.w(TAG + tag, msg + "");
        }

    }

    public static void v(@Nullable String tag, Object msg) {
        if (!isTest)
            return;

        if (tag == null) {
            Log.v(TAG, msg + "");
        } else {
            Log.v(tag, msg + "");
        }

    }

    /**
     * 时间工具
     * "yyyy.MM.dd"
     * "yyyy-MM-dd HH:mm:ss"
     *
     * @author ShiXiang
     * @version 1.0, 2016/11/21
     */
    private static final String TIME_ALL_TYPE = "yyyy-MM-dd HH:mm:ss";

    private static String getDefaultFormat(@Nullable String format) {
        String iFormat;
        if (format == null) {
            iFormat = TIME_ALL_TYPE;
        } else {
            iFormat = format;
        }
        return iFormat;
    }

    public static String formatTime(long time, @Nullable String format) {
        SimpleDateFormat sdf = getFormat(format);

        if (time <= 0) {
            return "0000-00-00 00:00";
        }
        String strTime = sdf.format(time);
        return strTime;
    }

    @NonNull
    public static SimpleDateFormat getFormat(@Nullable String format) {
        String iFormat = getDefaultFormat(format);
        SimpleDateFormat sdf = new SimpleDateFormat(iFormat, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf;
    }

    public static String formatTime(Date date, @Nullable String format) {
        SimpleDateFormat sdf = getFormat(format);

        if (date.getTime() <= 0) {
            return "0000-00-00 00:00";
        }
        String strTime = sdf.format(date);
        return strTime;
    }

    public static long formatTime(String time, @Nullable String format) {
        SimpleDateFormat sdf = getFormat(format);

        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getCurrentTime(@Nullable String format) {
        String iFormat = getDefaultFormat(format);

        Date date = new Date(System.currentTimeMillis());
        return Ut.formatTime(date, iFormat);
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getCurrentTimeAll() {
        Date date = new Date(System.currentTimeMillis());
        return Ut.formatTime(date, null);
    }

    public static String calculateHourMinute(long startTime, long endTime) {
        if (startTime != 0 && endTime != 0) {
            long t = (endTime - startTime);

            SimpleDateFormat sdf = new SimpleDateFormat("HH小时mm分钟", Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            String s = sdf.format(t);

            return s;
        } else {
            return "00小时00分钟";
        }
    }

    /*** 响应工具 ***************************************************/
    public static boolean isSuccess(String json) {
        Ut.i("http reply", json);

        boolean ret = false;

        ComBean bean = new Gson().fromJson(json, ComBean.class);
        if (bean.getCode() == 200) {
            ret = true;
        }

        return ret;
    }

    public static String getReply(String json) {
        String ret = "";

        ComBean bean = new Gson().fromJson(json, ComBean.class);
        String msg = bean.getMsg();
        if (msg != null)
            ret = msg;

        return ret;
    }

    public class ComBean {
        private String message;
        private int code;

        public String getMsg() {
            return message;
        }

        public void setMsg(String msg) {
            this.message = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    /*** 功能 ***************************************************/
    public static boolean isUIThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    //震动milliseconds毫秒
    public static Vibrator vibrate(Context context, long milliseconds) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);

        return vib;
    }

    //以pattern[]方式震动
    public static Vibrator vibrate(Context context, long[] pattern, int repeat) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, repeat);

        return vib;
    }

    //取消震动
    public static void virateCancle(Vibrator vib) {
        vib.cancel();
    }

    public static void playNotifacation(Context context) {
        //用于获取手机默认铃声的Uri
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        MediaPlayer player = getVoicePlayer(context, alert, true);
        player.start();
    }

    //开始播放
    public static MediaPlayer getVoicePlayer(Context context, Uri uri, boolean loop) {
        MediaPlayer player = new MediaPlayer();

        try {
            player.setDataSource(context, uri);
            player.setAudioStreamType(AudioManager.STREAM_RING);//告诉mediaPlayer播放的是铃声流
            player.setLooping(loop);
            player.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

        return player;
    }

    //停止播放
    public static void stopPlay(MediaPlayer player) {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
                player.release();
            }
        }
    }

    //停止播放
    public static void startPlay(MediaPlayer player) {
        if (player != null) {
            if (player.isPlaying()) {
                return;
            }
            player.start();
        }
    }

    /*** 储存 ***************************************************/
    public static String getAppFile(Context ctx, @Nullable String type) {
        File file = ContextCompat.getExternalFilesDirs(ctx, type)[0];
        return file.getAbsolutePath();
    }

    public static String getExternalRoot() {
        File file = Environment.getExternalStorageDirectory();
        return file.getAbsolutePath();
    }

    /***  ***************************************************/
    public static void skip2Home(Context context) {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(home);
    }

    /*** 排序 ***************************************************/

    /**
     * view是否屏幕中
     */
    //获取view是否显示中
    public static Boolean checkIsVisible(Context context, View view) {
        // 如果已经加载了，判断广告view是否显示出来，然后曝光
        int screenWidth = getScreenMetrics(context).x;
        int screenHeight = getScreenMetrics(context).y;
        Rect rect = new Rect(0, 0, screenWidth, screenHeight);
        int[] location = new int[2];
        view.getLocationInWindow(location);
        if (view.getLocalVisibleRect(rect)) {
            return true;
        } else {
            //view已不在屏幕可见区域;
            return false;
        }
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    public static String getNum(int m) {
        String num = "";
        if (m < 10) {
            num = "0" + m;
        } else {
            num = m + "";
        }
        return num;
    }

    /**
     * 获取今天明天日期
     */
    public static DateStringBean getDateString() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //几月几日
        String startDate = month + "月" + day + "日";
        String endDate = month + "月" + (day + 1) + "日";
        //yyyy-MM-dd
        String startDateNum = year + "-" + getNum(month) + "-" + getNum(day);
        String endDateNum = year + "-" + getNum(month) + "-" + getNum(day + 1);
        return new DateStringBean(startDate, endDate, startDateNum, endDateNum);
    }

    /**
     * <pre>
     * 根据指定的日期字符串获取星期几
     * </pre>
     *
     * @param strDate 指定的日期字符串(yyyy-MM-dd 或 yyyy/MM/dd)
     * @return week
     * 星期几(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY)
     */
    public static String getWeekByDateStr(String strDate) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String toDay = simpleDateFormat.format(Calendar.getInstance().getTime());
        if (toDay.equals(strDate)) {
            return "今日";
        } else {
            Ut.e("getWeekByDateStr", strDate);
            int year = Integer.parseInt(strDate.substring(0, 4));
            int month = Integer.parseInt(strDate.substring(5, 7));
            int day = Integer.parseInt(strDate.substring(8, 10));

            Calendar c = Calendar.getInstance();

            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month - 1);
            c.set(Calendar.DAY_OF_MONTH, day);

            String week = "";
            int weekIndex = c.get(Calendar.DAY_OF_WEEK);

            switch (weekIndex) {
                case 1:
                    week = "周日";
                    break;
                case 2:
                    week = "周一";
                    break;
                case 3:
                    week = "周二";
                    break;
                case 4:
                    week = "周三";
                    break;
                case 5:
                    week = "周四";
                    break;
                case 6:
                    week = "周五";
                    break;
                case 7:
                    week = "周日";
                    break;
            }
            return week;
        }
    }

    /*** 打电话 ***************************************************/
    public static void call(final Context context, final String phoneNum) {
        if (phoneNum == null)
            return;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            MyToast.showShort("拨打电话功能被禁止，前往设置开启权限");
            return;
        }

        AlertDialog dialog = getConfirmDialog(context, "拨打电话：" + phoneNum);
        TextView confirm = (TextView) dialog.findViewById(R.id.dl_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                context.startActivity(intent);
            }
        });

    }

    public static void call(final Context context, final String phoneNum, String prefix) {
        if (phoneNum == null)
            return;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            MyToast.showShort( "拨打电话功能被禁止，前往设置开启权限");
            return;
        }

        AlertDialog dialog = getConfirmDialog(context, prefix + "：" + phoneNum);
        TextView confirm = (TextView) dialog.findViewById(R.id.dl_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                context.startActivity(intent);
            }
        });

    }

    /**
     * 拆分图片URL
     */
    public static List<String> getImageUrl(Object url) {
        String s = "";
        if (url != null) {
            s = url + "";
        }
        List<String> list = new ArrayList<>();
        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }

        if (list.size() == 0) {
            list.add("");
        }

        return list;
    }


}
