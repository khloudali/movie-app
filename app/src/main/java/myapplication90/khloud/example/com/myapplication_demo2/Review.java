package myapplication90.khloud.example.com.myapplication_demo2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class Review extends RecyclerView.Adapter<Review.viewHolder> {

    ArrayList<Movi> movieInfo;
    Context context;
    LayoutInflater layoutInflater;

    public Review(Context context, ArrayList<Movi> movieInfo) {
        this.context = context;
        this.movieInfo = movieInfo;
        layoutInflater = layoutInflater.from(context);


    }

    @Override
    public Review.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new viewHolder(layoutInflater.inflate(R.layout.row_review, parent, false));

    }



    @Override
    public void onBindViewHolder(Review .viewHolder holder, int position) {

        holder.author.setText(movieInfo.get(position).getAuthor());
        holder.content.setText(movieInfo.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return movieInfo.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView content;

        public viewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}

