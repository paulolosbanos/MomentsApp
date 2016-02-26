package app.moments.com.momentsapp.mock;

import org.json.JSONObject;

import app.moments.com.momentsapp.R;
import app.moments.com.momentsapp.Util.Key;

/**
 * Created by yggdrasil on 2/26/16.
 */
public class ThreadMock {

    public static String[] orderedKeys = {"threadA","threadB"};
    public static String[] orderedCommentKeys = {"comment1","comment2"};

    public static JSONObject getThreads() {

        JSONObject threads = new JSONObject();

        JSONObject thread1 = new JSONObject();
        JSONObject thread2 = new JSONObject();

        try {

            thread1.put(Key.THREAD_STARTER,"David Mark");
            thread1.put(Key.THREAD_STARTER_IMAGE,"DM_IMG");
            thread1.put(Key.THREAD_AGE,"5");
            thread1.put(Key.THREAD_IMAGE_POST,"DM_IMG_POST");
            thread1.put(Key.THREAD_TEXT_POST,"Having breakfast now. Good morning everyone!");
            thread1.put(Key.THREAD_LIKES,"9");
            thread1.put(Key.THREAD_COMMENT_COUNT,"10");

            thread2.put(Key.THREAD_STARTER,"Mary Jane");
            thread2.put(Key.THREAD_STARTER_IMAGE,"MJ_IMG");
            thread2.put(Key.THREAD_AGE,"30");
            thread2.put(Key.THREAD_IMAGE_POST,"MJ_IMG_POST");
            thread2.put(Key.THREAD_TEXT_POST,"Shopping!");
            thread2.put(Key.THREAD_LIKES,"19");
            thread2.put(Key.THREAD_COMMENT_COUNT,"30");

            threads.put("threadA",thread1);
            threads.put("threadB",thread2);

        } catch (Exception e) {

        }

        return threads;
    }

    public static JSONObject getCommentsForThread(String thread) {
        JSONObject obj = new JSONObject();
        switch (thread) {
            case "threadA":
                try {
                    JSONObject comment1 = new JSONObject();
                    JSONObject comment2 = new JSONObject();

                    comment1.put(Key.COMMENT_POSTER_PICTURE,"ED_IMG");
                    comment1.put(Key.COMMENT_POSTER_NAME,"Edward");
                    comment1.put(Key.COMMENT_TEXT_POST,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eget turpis eu massa viverra ullamcorper. Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

                    comment2.put(Key.COMMENT_POSTER_PICTURE,"MM_IMG");
                    comment2.put(Key.COMMENT_POSTER_NAME,"Ming Ming");
                    comment2.put(Key.COMMENT_TEXT_POST,"Nice!");

                    obj.put("comment1",comment1);
                    obj.put("comment2",comment2);
                } catch (Exception e) {

                }
                break;
            case "threadB":
                try {
                    JSONObject comment1 = new JSONObject();
                    JSONObject comment2 = new JSONObject();

                    comment1.put(Key.COMMENT_POSTER_PICTURE,"ED_IMG");
                    comment1.put(Key.COMMENT_POSTER_NAME,"Edward");
                    comment1.put(Key.COMMENT_TEXT_POST,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eget turpis eu massa viverra ullamcorper. Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

                    comment2.put(Key.COMMENT_POSTER_PICTURE,"MM_IMG");
                    comment2.put(Key.COMMENT_POSTER_NAME,"Ming Ming");
                    comment2.put(Key.COMMENT_TEXT_POST,"Nice!");

                    obj.put("comment1",comment1);
                    obj.put("comment2",comment2);
                } catch (Exception e) {

                }

                break;
        }
        return obj;
    }

    public static int getImageRes(String key) {
        switch (key) {
            case "DM_IMG":
                return R.drawable.img1;
            case "MJ_IMG":
                return R.drawable.img2;
            case "ED_IMG":
                return R.drawable.img3;
            case "MM_IMG":
                return R.drawable.img4;
            case "DM_IMG_POST":
                return R.drawable.img_post_pic1;
            case "MJ_IMG_POST":
                return R.drawable.img_post_pic2;
        }

        return -1;
    }


}
