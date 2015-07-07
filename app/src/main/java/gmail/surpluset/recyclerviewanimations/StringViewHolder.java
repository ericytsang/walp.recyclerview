package gmail.surpluset.recyclerviewanimations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class StringViewHolder extends ViewHolder<String>
{
    // instance data: general
    private final Listener listener;

    // instance data: view references
    private final TextView textView;

    // constructors

    public StringViewHolder(ViewGroup parent,Listener listener)
    {
        super(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item,parent,false));

        // initialize instance data: general
        this.listener = listener;

        // initialize instance data: view references
        this.textView = (TextView) itemView.findViewById(android.R.id.text1);

        // set listeners on views
        getView().setOnClickListener(new ViewListener());
    }

    // ViewHolder<>

    @Override
    public void onSetModel(String s)
    {
        textView.setText(s);
    }

    // public interface

    public interface Listener
    {
        void onStringSelected(String s);
    }

    // private interface

    private class ViewListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            listener.onStringSelected(getModel());
        }
    }
}
