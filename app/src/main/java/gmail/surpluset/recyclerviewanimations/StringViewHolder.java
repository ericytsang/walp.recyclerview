package gmail.surpluset.recyclerviewanimations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StringViewHolder extends ViewHolder<String>
{
    // instance data: view references
    private final TextView textView;

    // public interface: constructors

    public StringViewHolder(ViewGroup parent,final Listener listener)
    {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false));

        // initialize instance data: view references
        this.textView = (TextView) itemView.findViewById(R.id.textview);

        // set listeners on views
        getView().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onClick(getModel());
            }
        });
    }

    // protected interface: ViewHolder implementation

    /**
     * sets the view holder's model, and updates the instance's view to
     *   present the new model.
     *
     * @param s reference to the new model to present.
     */
    @Override
    public void onSetModel(String s)
    {
        textView.setText(s);
        textView.setBackgroundColor(s.hashCode());
    }

    // public interface: interfaces

    /**
     * defines callbacks for events potentially relevant to listeners.
     */
    public interface Listener
    {
        void onClick(String s);
    }
}
