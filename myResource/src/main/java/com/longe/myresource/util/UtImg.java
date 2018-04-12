package com.longe.myresource.util;

/**
 * UtImg
 * picasso 工具类
 *
 * @author ShiXiang
 * @version 1.0, 2016/10/31
 */
public class UtImg {

    //文件存储路径
//    public static final File PHOTO_SAVE_DIR = new File(Environment.getExternalStorageDirectory(),
//            Weld.appContext.getPackageName() + File.separator + "img");
//    public static final String PHOTO_LAST_URI = "photo_last_uri";
//
//    public static void savePhoto(Bitmap bitmap) {
//        if (!PHOTO_SAVE_DIR.exists())
//            PHOTO_SAVE_DIR.mkdirs();
//
//        String fileName = "portrait.png";
//        Weld.editor.putString(PHOTO_LAST_URI, PHOTO_SAVE_DIR.toURI().toString() + fileName);
//        Weld.editor.apply();
//
//        File file = new File(PHOTO_SAVE_DIR, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 10, fos);
//            fos.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void savePhoto(int width, int height) {
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
//
//        if (!PHOTO_SAVE_DIR.exists())
//            PHOTO_SAVE_DIR.mkdirs();
//
//        String fileName = "portrait.png";
//        Weld.editor.putString(PHOTO_LAST_URI, PHOTO_SAVE_DIR.toURI().toString() + fileName);
//        Weld.editor.apply();
//
//        File file = new File(PHOTO_SAVE_DIR, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Bitmap convertViewToBitmap(View view) {
//        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//        view.buildDrawingCache();
//        Bitmap bitmap = view.getDrawingCache();
//
//        return bitmap;
//    }


}
