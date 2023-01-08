package id.ac.e020320043.challenge1_materialme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

import id.ac.e020320043.challenge_materialme.R;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Sport> mSportsData;
    private SportsAdapter mAdapter;
    private static final String BUNDLE_KEY = "Sports_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Satria - Challenge 1 MaterialMe");
        setContentView(R.layout.activity_main);


        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSportsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new SportsAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();

        ItemTouchHelper helper = new ItemTouchHelper(new
     ItemTouchHelper.SimpleCallback(
             ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                     ItemTouchHelper.DOWN | ItemTouchHelper.UP,
             ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

         @Override
         public boolean onMove(@NonNull RecyclerView recyclerView,
                               @NonNull RecyclerView.ViewHolder viewHolder,
                               @NonNull RecyclerView.ViewHolder target) {

             int from = viewHolder.getAdapterPosition();
             int to = target.getAdapterPosition();

             Collections.swap(mSportsData, from, to);
             mAdapter.notifyItemMoved(from, to);
             return true;
         }

         @Override
         public void onSwiped(RecyclerView.ViewHolder viewHolder,
                              int direction) {
             mSportsData.remove(viewHolder.getAdapterPosition());
             mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

         }
     });
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);
        TypedArray sportsImageResources =
                getResources().obtainTypedArray(R.array.sports_images);
        String[] sportsDetails = getResources()
                .getStringArray(R.array.sports_details);

        // Clear the existing data (to avoid duplication).
        mSportsData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<sportsList.length;i++){
            mSportsData.add(new Sport(sportsList[i],sportsInfo[i],
                    sportsImageResources.getResourceId(i,0),  sportsDetails[i]));
        }

        sportsImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }
    public void resetSports(View view) {
        initializeData();
    }

}