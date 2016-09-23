package myapplication90.khloud.example.com.myapplication_demo2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements RAdapter.onClick{
    private boolean slide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slide = findViewById(R.id.movie_detail_fragment) != null;
    }


    @Override
    public void onclick(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);

        if (slide) {
            DetailFragmentActivity.Det detailActivityFragment = new DetailFragmentActivity.Det();
            detailActivityFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_fragment, detailActivityFragment)
                    .commit();
        } else {
            Intent i = new Intent(this, DetailFragmentActivity.class);
            i.putExtra("id", id);
            startActivity(i);
        }
    }
}