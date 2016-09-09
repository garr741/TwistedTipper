package tylorgarrett.xyz.twistedtipper.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import tylorgarrett.xyz.twistedtipper.R;
import tylorgarrett.xyz.twistedtipper.models.Critique;

public class CritiqueAdapter extends RecyclerView.Adapter<CritiqueAdapter.ViewHolder> {

    private List<Critique> critiques;
    private Context context;
    private OnCritiqueClickedListener listener;

    public CritiqueAdapter(Context context, List<Critique> critiques, OnCritiqueClickedListener listener){
        this.context = context;
        this.critiques = critiques;
        this.listener = listener;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.critique_card, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Critique critique = critiques.get(position);
        holder.critiqueText.setText(critique.getName());
        holder.cardView.setCardBackgroundColor(getColorFromValue(critique.getValue()));
    }

    @Override public int getItemCount() {
        return critiques.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.critique_text) TextView critiqueText;
        @BindView(R.id.card_view) CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this);
        }

        @Override public void onClick(View view) {
            if (listener != null) {
                Critique critique = critiques.get(getAdapterPosition());
                listener.onCritiqueClicked(critique);
            }
        }
    }

    private int getColorFromValue(Critique.Value value){
        Resources resources = context.getResources();
        switch (value){
            case RED:
                return resources.getColor(R.color.red);
            case GREEN:
                return resources.getColor(R.color.green);
            case YELLOW:
                return resources.getColor(R.color.yellow);
            default:
                return resources.getColor(R.color.grey);
        }
    }

    public interface OnCritiqueClickedListener {
        void onCritiqueClicked(Critique critique);
    }
}
