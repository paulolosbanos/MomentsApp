package app.moments.com.momentsapp.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import app.moments.com.momentsapp.R;
import app.moments.com.momentsapp.Util.Key;
import app.moments.com.momentsapp.Util.Utils;
import app.moments.com.momentsapp.mock.ThreadMock;

/**
 * Created by yggdrasil on 2/26/16.
 */
public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.ThreadViewHolder>{

    private Context mContext;
    private JSONObject mThreads;

    String mThreadAgeFormatter;
    String mCommentCountFormatter;
    String mLikeCountFormatter;


    public ThreadAdapter(Context context,JSONObject threads) {
        this.mContext = context;
        this.mThreads = threads;
    }

    @Override
    public int getItemCount() {
        return mThreads.length();
    }

    @Override
    public ThreadViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.thread_card_item, viewGroup, false);
        return new ThreadViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(final ThreadViewHolder threadViewHolder, int i) {

        try {

            Resources res = mContext.getResources();

            final JSONObject thread = mThreads.getJSONObject(ThreadMock.orderedKeys[i]);

            threadViewHolder.mProfilePic.setImageDrawable(res.getDrawable(ThreadMock.getImageRes(thread.getString(Key.THREAD_STARTER_IMAGE))));

            Bitmap imgBit = Utils.drawableToBitmap(threadViewHolder.mProfilePic.getDrawable());
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, imgBit);
            dr.setCornerRadius(Math.max(imgBit.getWidth(), imgBit.getHeight()) / 2.0f);
            threadViewHolder.mProfilePic.setImageDrawable(dr);

            threadViewHolder.mThreadImagePost.setImageDrawable(res.getDrawable(ThreadMock.getImageRes(thread.getString(Key.THREAD_IMAGE_POST))));

            mThreadAgeFormatter = threadViewHolder.mThreadAge.getText().toString();
            mCommentCountFormatter = threadViewHolder.mCommentCount.getText().toString();
            mLikeCountFormatter = threadViewHolder.mLikeCount.getText().toString();

            threadViewHolder.mThreadStarter.setText(thread.getString(Key.THREAD_STARTER));
            threadViewHolder.mThreadTextPost.setText(thread.getString(Key.THREAD_TEXT_POST));

            threadViewHolder.mThreadAge.setText(String.format(mThreadAgeFormatter, thread.getString(Key.THREAD_AGE)));
            Log.e("qqq",thread.getString(Key.THREAD_AGE));
            threadViewHolder.mCommentCount.setText(String.format(mCommentCountFormatter, thread.getString(Key.THREAD_COMMENT_COUNT)));
            threadViewHolder.mLikeCount.setText(String.format(mLikeCountFormatter, thread.getString(Key.THREAD_LIKES)));

            CommentAdapter adapter = new CommentAdapter(mContext,ThreadMock.getCommentsForThread(ThreadMock.orderedKeys[i]));
            threadViewHolder.mListComment.setLayoutManager(new LinearLayoutManager(mContext));
            threadViewHolder.mListComment.setAdapter(adapter);

            threadViewHolder.mLikeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean pressed) {
                    try {
                        if (pressed) {
                            int count = Integer.parseInt(thread.getString(Key.THREAD_LIKES));
                            count++;
                            threadViewHolder.mLikeCount.setText(String.format(mLikeCountFormatter, count + ""));
                        } else {
                            threadViewHolder.mLikeCount.setText(String.format(mLikeCountFormatter, thread.getString(Key.THREAD_LIKES)));
                        }
                    } catch (JSONException e) {

                    }
                }
            });


        } catch (JSONException je) {

        }

    }

    class ThreadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mProfilePic;
        ImageView mThreadImagePost;

        TextView mThreadStarter;
        TextView mThreadAge;
        TextView mThreadTextPost;

        TextView mLikeCount;
        TextView mCommentCount;

        ImageButton mCommentButton;
        CheckBox mLikeButton;


        RecyclerView mListComment;
        View mCommentDivider;
        LinearLayout mViewMore;
        RelativeLayout mCommentArea;

        boolean visible = false;

        public ThreadViewHolder(View itemView,Context context) {
            super(itemView);

            mCommentButton = (ImageButton) itemView.findViewById(R.id.commentButton);
            mCommentButton.setOnClickListener(this);

            mLikeButton = (CheckBox) itemView.findViewById(R.id.likeButton);

            mProfilePic = (ImageView) itemView.findViewById(R.id.profilePic);
            mThreadImagePost = (ImageView) itemView.findViewById(R.id.threadImagePost);

            mThreadStarter = (TextView) itemView.findViewById(R.id.threadStarter);
            mThreadAge = (TextView) itemView.findViewById(R.id.threadAge);
            mThreadTextPost = (TextView) itemView.findViewById(R.id.threadTextPost);

            mLikeCount = (TextView) itemView.findViewById(R.id.likeCount);
            mCommentCount = (TextView) itemView.findViewById(R.id.commentCount);
            mCommentCount.setOnClickListener(this);

            mListComment = (RecyclerView) itemView.findViewById(R.id.list_comment);
            mCommentDivider = itemView.findViewById(R.id.commentDivider);
            mCommentArea = (RelativeLayout) itemView.findViewById(R.id.commentArea);
            mViewMore = (LinearLayout) itemView.findViewById(R.id.viewMore);


        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.commentCount:
                case R.id.commentButton:
                    if(visible) {
                        mListComment.setVisibility(View.GONE);
                        mCommentDivider.setVisibility(View.GONE);
                        mCommentArea.setVisibility(View.GONE);
                        mViewMore.setVisibility(View.GONE);
                        visible = false;
                    } else {
                        mListComment.setVisibility(View.VISIBLE);
                        mCommentDivider.setVisibility(View.VISIBLE);
                        mCommentArea.setVisibility(View.VISIBLE);
                        mViewMore.setVisibility(View.VISIBLE);
                        visible = true;
                    }
                    break;
            }
        }
    }


}
