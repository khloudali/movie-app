package myapplication90.khloud.example.com.myapplication_demo2;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RAdapter extends RecyclerView.Adapter<RAdapter.viewHolder> {

    ArrayList<Movi> movieInfos;
    Context context;
    LayoutInflater layoutInflater;
    onClick onClick;

    public RAdapter(Context context, ArrayList<Movi> movieInfos) {
        this.context = context;
        this.movieInfos = movieInfos;
        layoutInflater = layoutInflater.from(context);
        onClick = (onClick) context;

    }

    @Override
    public RAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new viewHolder(layoutInflater.inflate(R.layout.rowmovie, parent, false));

    }



    @Override
    public void onBindViewHolder(RAdapter.viewHolder holder, int position) {
        Picasso.with(context).load(Uri.parse("http://image.tmdb.org/t/p/w185" + movieInfos.get(position).getPoster_path()))
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return movieInfos.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public viewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.poster1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onClick.onclick(movieInfos.get(getPosition()).getId());

                }
            });
        }
    }

    public interface onClick {
        void onclick(long id);
    }
}
