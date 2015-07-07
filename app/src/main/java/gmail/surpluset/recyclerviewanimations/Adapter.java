package gmail.surpluset.recyclerviewanimations;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Adapter extends RecyclerView.Adapter<StringViewHolder>
{
    private StringViewHolder.Listener listener;
    private final List<String> items;
    private final ListItemListener listItemListener;

    // constructors

    public Adapter()
    {
        this.listener = null;
        this.items = new ArrayList<>();
        this.listItemListener = new ListItemListener();

        setHasStableIds(true);
    }

    // Adapter<>

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        switch(viewType)
        {
        case 0:
            return new StringViewHolder(parent,listItemListener);
        default:
            throw new RuntimeException("unknown viewType");
        }
    }

    @Override
    public void onBindViewHolder(StringViewHolder holder,int position)
    {
        holder.setModel(items.get(position));
    }

    @Override
    public long getItemId(int position)
    {
        return items.get(position).hashCode(); // can be improved
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    // public interface

    public void setListener(StringViewHolder.Listener listener)
    {
        this.listener = listener;
    }

    public void addRandomListItem()
    {
        String giberish = UUID.randomUUID().toString();
        items.add((int) Math.round(Math.random()*items.size()),giberish);
        notifyDataSetChanged();
    }

    public void removeRandomListItem()
    {
        if(items.size() == 0) return;

        int index = (int) (Math.random()*items.size());
        items.remove(index);
        notifyDataSetChanged();
    }

    public void shuffleListItems()
    {
        Collections.shuffle(items);
        notifyDataSetChanged();
    }

    // private interface

    private class ListItemListener implements StringViewHolder.Listener
    {
        @Override
        public void onStringSelected(final String s)
        {
            if(listener != null) listener.onStringSelected(s);
        }
    }
}
