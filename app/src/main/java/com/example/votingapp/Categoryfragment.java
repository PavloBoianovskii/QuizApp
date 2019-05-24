package com.example.votingapp;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.content.ClipData.Item;

import com.example.votingapp.Common.Common;
import com.example.votingapp.Interface.ItemClickListener;
import com.example.votingapp.Model.Category;
import com.example.votingapp.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Categoryfragment extends Fragment {
    View myFragment;

    RecyclerView listCategory;
    //List<Category> categories = new ArrayList();
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference categories;

    public static Categoryfragment newInstance(){
    Categoryfragment categoryfragment = new Categoryfragment();
    return categoryfragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        database=FirebaseDatabase.getInstance();
        categories=database.getReference("Category");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_categoryfragment,container,false);

        listCategory= (RecyclerView)myFragment.findViewById(R.id.listCategory);
        listCategory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);

        //retrieveCategories();
        //setCategories();
        loadCategories();
        return myFragment;
    }
    private void loadCategories() {
        adapter=new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder,@NonNull final Category model,int position) {
                viewHolder.category_name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(viewHolder.category_image);
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(getActivity(),String.format("%d|%s",adapter.getRef(position).getKey(),model.getName()),Toast.LENGTH_SHORT).show();
                        Intent startGame = new Intent(getActivity(),Start.class);
                        Common.categoryId = adapter.getRef(position).getKey();
                        startActivity(startGame);
                    }
                });

            }
        };
        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }
 /*   private void setCategories() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Category");
        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(query,Category.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {

            @Override
            public int getItemCount() {
                return categories.size();
            }
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder viewHolder, int position, @NonNull final Category model) {
                viewHolder.category_name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(viewHolder.category_image);
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(),String.format("%d|%s",adapter.getRef(position).getKey(),model.getName()),Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.category_layout, viewGroup, false);

                return new CategoryViewHolder(view);

            }
        };

    }

    private void retrieveCategories() {
        categories.clear();
        DatabaseReference db = FirebaseDatabase.getInstance()
                .getReference()
                .child("Category");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot categorySnapShot:dataSnapshot.getChildren())
                {
                    Category category = categorySnapShot.getValue(Category.class);
                    categories.add(category);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onStart() {
        if(adapter != null)
            adapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {
        if(adapter != null)
            adapter.stopListening();
        super.onStop();
    }
    */
    /*  FirebaseRecyclerOptions<Category> options =
            new FirebaseRecyclerOptions.Builder<Category>()
                    .setQuery(categories, Category.class)
                    .build();

    private void loadCategories() {
        adapter=new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder viewHolder, int position, @NonNull final Category model) {
                viewHolder.category_name.setText(model.getName());
                Picasso.get().load(model.getImage()).into(viewHolder.category_image);
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getActivity(),String.format("%d|%s",adapter.getRef(position).getKey(),model.getName()),Toast.LENGTH_SHORT).show();
                    }
                });

            }
            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.category_layout, viewGroup, false);

                return new CategoryViewHolder(view);
            }

        };
        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }
*/

}
