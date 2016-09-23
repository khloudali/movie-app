package myapplication90.khloud.example.com.myapplication_demo2;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by khloud on 8/11/2016.
 */
public class DetailFragmentActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
            //NavUtils.navigateUpFromSameTask(this);
            Intent i =new Intent(this,MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //getActionBar().setTitle();
        getSupportActionBar().setTitle("Detailes");
        Bundle bundle = new Bundle();
        bundle.putLong("id", getIntent().getExtras().getLong("id"));
        Det detailActivityFragment = new Det();
        detailActivityFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_detail_fragment, detailActivityFragment)
                .commit();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






    }

    public static class Det extends Fragment {

        Long id;
        String title;
        String poster_path;
        RequestQueue requestQueue;
        Movi data;
        ArrayList<Movi> review_data, Trailer_data;
        ImageView panner, poster;
        Button star;
        TextView overview, release_date, vote_average;
        RecyclerView Review_list, Trailer_list;
        Favorite favorite;

        Bundle bundle = null;


        public Det() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestQueue = Volley.newRequestQueue(getActivity());

            bundle = getArguments();
            id = bundle.getLong("id");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootview = inflater.inflate(R.layout.detail_fragment, container, false);

            panner = (ImageView) rootview.findViewById(R.id.moviepanner);
            poster = (ImageView) rootview.findViewById(R.id.poster2);
            overview = (TextView) rootview.findViewById(R.id.summary);
            release_date = (TextView) rootview.findViewById(R.id.release_date);
            vote_average = (TextView) rootview.findViewById(R.id.vote_average);
            star = (Button) rootview.findViewById(R.id.star);
            favorite = new Favorite(getActivity());
            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (favorite.retrivebyID(id)) {

                        Toast.makeText(getActivity(), "This Film is Already in Favorite ", Toast.LENGTH_LONG).show();
                    } else {
                        favorite.insert(id, poster_path, title);

                    }
                }
            });

            Trailer_list = (RecyclerView) rootview.findViewById(R.id.recycler_Trailer);
            Trailer_list.setLayoutManager(new LinearLayoutManager(getActivity()));

              Review_list = (RecyclerView) rootview.findViewById(R.id.recycler_Review);
             Review_list.setLayoutManager(new LinearLayoutManager(getActivity()));


            return rootview;
        }



        @Override
        public void onResume() {
            super.onResume();

            SendjsonRequest("http://api.themoviedb.org/3/movie/" + id + "?api_key=aa43a2b8bd6218e82a15eef7a5f63fab");
         sendjsonRequestReview("http://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=aa43a2b8bd6218e82a15eef7a5f63fab");
            sendjsonRequestTrailer("http://api.themoviedb.org/3/movie/" + id + "/videos?api_key=aa43a2b8bd6218e82a15eef7a5f63fab");

        }

        public void SendjsonRequest(String Url) {

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {

                    data = Getdata(jsonObject);

                    if (data != null) {
                        Picasso.with(getActivity()).load(Uri.parse("http://image.tmdb.org/t/p/w185" + data.getBackimage_path()))
                                .into(panner);
                        Picasso.with(getActivity()).load(Uri.parse("http://image.tmdb.org/t/p/w185" + data.getPoster_path()))
                                .into(poster);
                        overview.setText(data.getOverview());
                        release_date.setText(data.getRelease_date());
                        vote_average.setText(data.getVote_average() + "");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }
            }
            );
            requestQueue.add(request);
        }

        public Movi Getdata(JSONObject jsonObject) {

            Movi mydata = null;

            if (jsonObject == null || jsonObject.length() == 0) {
                return null;
            } else {
                try {
                    title = jsonObject.getString("title");
                    poster_path = jsonObject.getString("poster_path");
                    String backdrop_path = jsonObject.getString("backdrop_path");
                    String release_date = jsonObject.getString("release_date");
                    double vote_average = jsonObject.getDouble("vote_average");
                    String overview = jsonObject.getString("overview");
                    mydata = new Movi(poster_path, overview, release_date, title, backdrop_path, vote_average);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return mydata;
        }

     public void sendjsonRequestReview(String Url) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                review_data = getDataReview(jsonObject);

                if (review_data != null) {
                    Review_list.setAdapter(new Review(getActivity(), review_data));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        }
        );

        requestQueue.add(request);
    }

        public ArrayList<Movi> getDataReview(JSONObject jsonObject) {

            ArrayList<Movi> mydata = new ArrayList<>();

            if (jsonObject == null || jsonObject.length() == 0) {
                return null;
            } else {
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject row = results.getJSONObject(i);

                        String author = row.getString("author");
                        String content = row.getString("content");
                        mydata.add(new Movi(author, content));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return mydata;
        }

        public void sendjsonRequestTrailer(String Url) {

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {

                    Trailer_data = getDataTrailer(jsonObject);
                    if (Trailer_data != null) {
                        Trailer_list.setAdapter(new Talier(getActivity(), Trailer_data));
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }
            }
            );

            requestQueue.add(request);
        }

        public ArrayList<Movi> getDataTrailer(JSONObject jsonObject) {

            ArrayList<Movi> mydata = new ArrayList<>();
            if (jsonObject == null || jsonObject.length() == 0) {
                return null;
            } else {
                try {
                    JSONArray results = jsonObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject row = results.getJSONObject(i);
                        Movi index = new Movi();
                        String trailer_name = row.getString("name");
                        String trailer_key = row.getString("key");

                        index.setTrailername(trailer_name);
                        index.setTrailerkey(trailer_key);
                        mydata.add(index);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return mydata;
        }
    }

}