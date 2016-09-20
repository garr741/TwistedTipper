package tylorgarrett.xyz.twistedtipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.lzyzsd.circleprogress.DonutProgress;
import java.util.LinkedList;
import java.util.List;
import tylorgarrett.xyz.twistedtipper.adapters.CritiqueAdapter;
import tylorgarrett.xyz.twistedtipper.models.Critique;

public class MainActivity extends AppCompatActivity implements CritiqueAdapter.OnCritiqueClickedListener{

    @BindView(R.id.donut_progress) DonutProgress donutProgress;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    List<Critique> critiqueList;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        critiqueList = new LinkedList<>();
        mockCritiqueData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CritiqueAdapter(this, critiqueList, this));
    }

    private void mockCritiqueData(){
        critiqueList.add(new Critique("Server got my order wrong!", Critique.Value.RED));
        critiqueList.add(new Critique("Waiting too long for refill", Critique.Value.YELLOW));
        critiqueList.add(new Critique("Server was prompt and on time!", Critique.Value.GREEN));
    }

    @Override public void onCritiqueClicked(Critique critique) {
        Toast.makeText(MainActivity.this, critique.getName(), Toast.LENGTH_SHORT).show();
    }
}
