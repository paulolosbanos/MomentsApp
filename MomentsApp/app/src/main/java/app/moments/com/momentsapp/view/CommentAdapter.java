package app.moments.com.momentsapp.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import app.moments.com.momentsapp.R;
import app.moments.com.momentsapp.Util.Key;
import app.moments.com.momentsapp.Util.Utils;
import app.moments.com.momentsapp.mock.ThreadMock;

/**
 * Created by yggdrasil on 2/26/16.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    private Context mContext;
    private JSONObject mComments;

    public CommentAdapter(Context context,JSONObject comments) {
        this.mContext = context;
        this.mComments = comments;
    }

    @Override
    public int getItemCount() {
        return mComments.length();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item, viewGroup, false);
        return new CommentViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder commentViewHolder, int i) {
        try {

            JSONObject comments = mComments.getJSONObject(ThreadMock.orderedCommentKeys[i]);

            Resources res = mContext.getResources();

            commentViewHolder.mProfilePic.setImageDrawable(res.getDrawable(ThreadMock.getImageRes(comments.getString(Key.COMMENT_POSTER_PICTURE))));

            Bitmap imgBit = Utils.drawableToBitmap(commentViewHolder.mProfilePic.getDrawable());
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, imgBit);
            dr.setCornerRadius(Math.max(imgBit.getWidth(), imgBit.getHeight()) / 2.0f);
            commentViewHolder.mProfilePic.setImageDrawable(dr);

            commentViewHolder.commenterName.setText(comments.getString(Key.COMMENT_POSTER_NAME));
            commentViewHolder.commenterText.setText(comments.getString(Key.COMMENT_TEXT_POST));

        } catch (JSONException e) {

        }
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        ImageView mProfilePic;
        TextView commenterName;
        TextView commenterText;

        public CommentViewHolder(View itemView,Context context) {
            super(itemView);

            mProfilePic = (ImageView) itemView.findViewById(R.id.profilePic);
            commenterName = (TextView) itemView.findViewById(R.id.commenterName);
            commenterText = (TextView) itemView.findViewById(R.id.commenterText);
        }
    }
}
