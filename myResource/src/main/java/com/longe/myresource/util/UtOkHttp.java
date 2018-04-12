package com.longe.myresource.util;

/**
 * 网络框架okhttp
 *
 * @author ShiXiang
 * @version 1.0, 1/4/2017
 */
public class UtOkHttp {
//    private static OkHttpClient httpClient = new OkHttpClient();
//
//    public static Call post(Request request) {
//        return httpClient.newCall(request);
//
//    }
//
//    public static Call postData(String mainUrl, HashMap<String, String> params) {
//
//        FormBody.Builder builder = new FormBody.Builder();
//
//        Set<String> keys = params.keySet();
//        for (String key : keys) {
//            builder.add(key, params.get(key));
//        }
//
//        RequestBody requestBody = builder.build();
//
////        MyTest.prt("getCookie",LoginActivity.sp.getString(Constants.MB_SESSION_ID, null));
//        Request request = new Request.Builder()
////                .header("cookie", LoginActivity.sp.getString(Constants.MB_SESSION_ID, null))
//                .url(mainUrl)
//                .post(requestBody)
//                .build();
//
//        return httpClient.newCall(request);
//
////        httpClient.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                handler.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        Toast.makeText(context, "网络错误!", Toast.LENGTH_SHORT).show();
////
////                        okHttpFail.onOKHttpFail();
////                    }
////                });
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                String json = response.body().string();
////
////                ret = json;
////                MyTest.prt("json", json);
////
////
////                okHttpSuccess.onOKHttpSuccess(response);
////            }
////        });
////
////        return ret;
//    }
//
//
//    public static Call getData(String mainUrl, String httpArg) {
//        String httpUrl = mainUrl + "?" + httpArg;
//        Request request = new Request.Builder()
////                .header("cookie", LoginActivity.sp.getString(Constants.MB_SESSION_ID, null))
//                .url(httpUrl)
//                .get()
//                .build();
//
//
//        return httpClient.newCall(request);
////        call.enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                handler.post(new Runnable() {
////                    @Override
////                    public void run() {
////                        Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
////                    }
////                });
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                final String json = response.body().string();
////
////                ret = json;
////
////            }
////        });
//
//
////        return ret;
//    }
//
////    public interface OKHttpFail {
////        void onOKHttpFail();
////    }
////
////    public interface OKHttpSuccess {
////        void onOKHttpSuccess(Response response);
////    }
//
//    ////上传图片
//    public static void commit(final Context context) {
//
//        final AlertDialog dialog = new AlertDialog.Builder(this).create();
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//        dialog.getWindow().setContentView(R.layout.layout_commit_dialog);
//
//        MultipartBody.Builder builder = new MultipartBody.Builder();
//        builder.setType(MultipartBody.FORM)
//                .addFormDataPart("member_id", LoginActivity.sp.getString(Constants.MB_ID, null))
//                .addFormDataPart("content", mContent);
//
//        for (int i = 0; i < photoPath.size(); i++) {
//            String pathname = photoPath.get(i);
////            MyTest.prt("getPath", pathname);
//
//            //获取文件名
//            String[] strs = pathname.split(File.separator);
//            String fileName = strs[strs.length - 1];
////            MyTest.prt("filename", fileName);
//
//            //获取文件的类型
//            FileNameMap fileNameMap = URLConnection.getFileNameMap();
//            String contentTypeFor = fileNameMap.getContentTypeFor(pathname);
//            if (null == contentTypeFor || "".equals(contentTypeFor)) {
//                contentTypeFor = "application/octet-stream";
//            }
//
//            //文件类型对应的"媒体信息"
//            MediaType mediaType = MediaType.parse(contentTypeFor);
////            MyTest.prt("gettype", mediaType);
//
////            File file = new File(pathname);
////            MyTest.prt("file", file);
//            builder.addFormDataPart("Images[" + i + "].file", fileName, RequestBody.create(mediaType, new File(pathname)));
//        }
//
//        RequestBody requestBody = builder.build();
//        Request request = new Request.Builder()
////                .url("http://120.76.217.21:8080/wubing/shuoshuo/insert.jhtml")
//                .url(Constants.URL_SS_RELEASE)
//                .post(requestBody)
//                .build();
//
//        Call call = OKHttpUtils.post(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        dialog.dismiss();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json = response.body().string();
////                MyTest.prt("releas", json);
//
//                String substring = json.substring(0, 1);
////                MyTest.prt("detailJson", substring);
//                if (!substring.equals("{")) {
//                    dialog.dismiss();
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            ShowToast.showShort(ReleaseActivity.this, "发布失败");
//                        }
//                    });
//                    return;
//                }
//
//                final SuccessBean successBean = new Gson().fromJson(json, SuccessBean.class);
//                final SuccessBean.BodyBean body = successBean.getBody();
//
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (body.getType().equals("success")) {
//                            ShowToast.showShort(context, body.getContent());
//                            dialog.dismiss();
//                            finish();
//                        }
//                    }
//                });
//            }
//
//        });
//    }

}
