package org.svcetedu.www.csemobileapp.StudentRegistration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.svcetedu.www.csemobileapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CNS extends Fragment {
    private View mMainView;
    private TextView cnsTitle;
    private TextView cnsDesc;
    private DatabaseReference cnsDatabase;
    //private FirebaseAuth mAuth;
    private RecyclerView CNSREC;





    public CNS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView= inflater.inflate(R.layout.fragment_cns, container, false);

        cnsTitle=(TextView)mMainView.findViewById(R.id.cnsnotesTitle);
        cnsDesc=(TextView)mMainView.findViewById(R.id.cnsnotes_desc);
        cnsDatabase= FirebaseDatabase.getInstance().getReference().child("CnsLecture");

        CNSREC=(RecyclerView)mMainView.findViewById(R.id.cnsRecyle);
        CNSREC.setHasFixedSize(true);
        CNSREC.setLayoutManager(new LinearLayoutManager(getContext()));



        return mMainView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<CNSClass, CNS.FriendsViewHolder> friendsRecyclerViewAdapter = new FirebaseRecyclerAdapter<CNSClass, CNS.FriendsViewHolder>(

                CNSClass.class,
                R.layout.cnsrow,
                CNS.FriendsViewHolder.class,
                cnsDatabase


        ) {


            @Override
            protected void populateViewHolder(final CNS.FriendsViewHolder friendsViewHolder, CNSClass model, int i) {

                friendsViewHolder.setTitle(model.getTitle());
                friendsViewHolder.setDesc(model.getDesc());



            }
        };

        CNSREC.setAdapter(friendsRecyclerViewAdapter);


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
