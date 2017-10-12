package org.svcetedu.www.csemobileapp.Faculty;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.svcetedu.www.csemobileapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacultyCircularDisplay extends Fragment {
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUser;
    private View  mMainView;
    private String mCurrent_user_id;
    private TextView postTitle;
    private TextView postDesc;
    private ImageView postImage;

    public FacultyCircularDisplay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView= inflater.inflate(R.layout.fragment_faculty_circular_display, container, false);

        mBlogList = (RecyclerView) mMainView.findViewById(R.id.blog_list);
        mAuth = FirebaseAuth.getInstance();


        //Declaration of all modules
        postTitle=(TextView)mMainView.findViewById(R.id.post_title);
        postDesc=(TextView)mMainView.findViewById(R.id.post_desc);
        postImage=(ImageView)mMainView.findViewById(R.id.post_image);

        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("CircularPost");
        mDatabaseUser.keepSynced(true);


        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getContext()));
        return mMainView;



    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, FacultyCircularDisplay.FriendsViewHolder> friendsRecyclerViewAdapter = new FirebaseRecyclerAdapter<Blog, FacultyCircularDisplay.FriendsViewHolder>(

                Blog.class,
                R.layout.blog_row,
                FacultyCircularDisplay.FriendsViewHolder.class,
                mDatabaseUser


        ) {
            @Override
            protected void populateViewHolder(final FacultyCircularDisplay.FriendsViewHolder friendsViewHolder, Blog model, int i) {


                friendsViewHolder.setTitle(model.getTitle());
                friendsViewHolder.setDesc(model.getDesc());
                friendsViewHolder.setImage(model.getImage(),getContext());







            }
        };

        mBlogList.setAdapter(friendsRecyclerViewAdapter);


    }


    public static class FriendsViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public FriendsViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }


        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(String image ,Context ctx)
        {
            ImageView post_image=(ImageView)mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }


    }

}
