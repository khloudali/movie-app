package myapplication90.khloud.example.com.myapplication_demo2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Talier  extends RecyclerView.Adapter<Talier.viewHolder> {

    ArrayList<Movi> movieInfo;
    Context context;
    LayoutInflater layoutInflater;

    public Talier(Context context, ArrayList<Movi> movieInfo) {
        this.context = context;
        this.movieInfo = movieInfo;
        layoutInflater = layoutInflater.from(context);


    }

    @Override
    public Talier.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new viewHolder(layoutInflater.inflate(R.layout.row_tailer, parent, false));

    }



    @Override
    public void onBindViewHolder(Talier.viewHolder holder, int position) {

        holder.trailername.setText(movieInfo.get(position).getTrailername());

    }

    @Override
    public int getItemCount() {
        return movieInfo.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView trailername;

        public viewHolder(final View itemView) {
            super(itemView);
            trailername = (TextView) itemView.findViewById(R.id.trailer_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://www.youtube.com/watch?v=" + movieInfo.get(getPosition()).getTrailerkey()));
                    context.startActivity(i);
                }
            });

        }
    }

}

