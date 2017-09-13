package org.svcetedu.www.csemobileapp.StudentRegistration;


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
import com.google.firebase.storage.FirebaseStorage;

import org.svcetedu.www.csemobileapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CloudComputing extends Fragment {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUser;
    private View  mMainView;
    private String mCurrent_user_id;
    private TextView postTitle;
    private TextView postDesc;
    private ImageView postImage;
    private FirebaseStorage mStorage;


    public CloudComputing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView= inflater.inflate(R.layout.fragment_cloud_computing, container, false);

       // mMainView= inflater.inflate(R.layout.studentfragment_circular, container, false);

        mBlogList = (RecyclerView) mMainView.findViewById(R.id.cloudComputing);
        mAuth = FirebaseAuth.getInstance();


        //Declaration of all modules
        postTitle=(TextView)mMainView.findViewById(R.id.notes_title);
        postDesc=(TextView)mMainView.findViewById(R.id.notes_desc);
        //postImage=(ImageView)mMainView.findViewById(R.id.post_image);


        //mCurrent_user_id = mAuth.getCurrentUser().getUid();

        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("CloudComputingNotes");
        mDatabaseUser.keepSynced(true);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getContext()));




        return mMainView;

    }


/*
    private void downloadThings() {


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://cse-mobile-app-85e20.appspot.com/");
        StorageReference  islandRef = storageRef.child("CloudFiles");

        File rootPath = new File(Environment.getExternalStorageDirectory(), "file_name");
        if(!rootPath.exists()) {
            rootPath.mkdirs();
        }

        final File localFile = new File(rootPath,"imageName.txt");

        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("firebase ",";local tem file created  created " +localFile.toString());
                //  updateDb(timestamp,localFile.toString(),position);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("firebase ",";local tem file not created  created " +exception.toString());
            }
        });



    }

    */

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<CloudComputingClass, CloudComputing.FriendsViewHolder> friendsRecyclerViewAdapter = new FirebaseRecyclerAdapter<CloudComputingClass, CloudComputing.FriendsViewHolder>(

                CloudComputingClass.class,
                R.layout.cloudcomputingrows,
                CloudComputing.FriendsViewHolder.class,
                mDatabaseUser


        ) {
            @Override
            protected void populateViewHolder(final CloudComputing.FriendsViewHolder friendsViewHolder, CloudComputingClass model, int i) {

                friendsViewHolder.setTitle(model.getName());
                friendsViewHolder.setDesc(model.getDesc());

                friendsViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        //downloadThings();

                    }
                });








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
            TextView post_title = (TextView) mView.findViewById(R.id.notes_title);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = (TextView) mView.findViewById(R.id.notes_desc);
            post_desc.setText(desc);
        }



    }

}
