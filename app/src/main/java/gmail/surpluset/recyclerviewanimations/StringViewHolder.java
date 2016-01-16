package gmail.surpluset.recyclerviewanimations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * implementation of a {@link ViewHolder} for elements of type {@link String}.
 */
public class StringViewHolder extends ViewHolder<String>
{
    /**
     * a {@link TextView} of the view layout managed by this {@link ViewHolder}.
     *   used to display the {@link String}.
     */
    private final TextView textView;

    /**
     * inflates the view managed by this view holder, and configures it.
     *
     * @param parent
     * @param listener listener that is notified when this view holder's view is
     *   clicked.
     */
    public StringViewHolder(ViewGroup parent,final Listener listener)
    {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false));

        // initialize instance data
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
    }

    /**
     * defines callbacks for events potentially relevant to listeners.
     */
    public interface Listener
    {
        void onClick(String s);
    }
}
