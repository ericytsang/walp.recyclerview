package gmail.surpluset.recyclerviewanimations;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class StringViewHolder extends ViewHolder<String>
{
    // view references
    private final TextView textView;

    // constructors

    public StringViewHolder(ViewGroup parent)
    {
        super(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false));

        // initialize view references
        textView = (TextView) itemView.findViewById(android.R.id.text1);
    }

    // public interface

    @Override
    public void onSetModel(String s)
    {
        textView.setText(s);
    }
}
