package gmail.surpluset.recyclerviewanimations;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<StringViewHolder>
{
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private Listener adapterListener;
    private final List<String> items;
    private final StringViewHolderListener stringViewHolderListener;

    // public interface: constructors

    public Adapter()
    {
        this.adapterListener = null;
        this.items = new ArrayList<>();
        this.stringViewHolderListener = new StringViewHolderListener();

        setHasStableIds(true);
    }

    // public interface: Adapter implementation

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        switch(viewType)
        {
        case 0:
            return new StringViewHolder(parent,stringViewHolderListener);
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
        return items.get(position).hashCode(); // can be improved because 2 different strings could have the same hashcode...
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    // public interface: interfaces & server methods

    /**
     * defines callbacks for events potentially relevant to listeners.
     */
    public interface Listener
    {
        void onListItemClick(String s);
    }

    /**
     * sets the adapter's listener. the listener's methods will be invoked by
     *   the adapter when relevant events regarding this adapter occurs.
     *
     * @param listener the adapter's observer.
     */
    public void setListener(Listener listener)
    {
        this.adapterListener = listener;
    }

    /**
     * adds a random list item to the adapter's data source, then notifies
     *   display adapters.
     */
    public void addRandomListItem()
    {
        int rand1 = (int) (Math.random()*LOREM_IPSUM.length());
        int rand2 = (int) (Math.random()*LOREM_IPSUM.length());
        String gibberish = LOREM_IPSUM.substring(Math.min(rand1,rand2),Math.max(rand1,rand2));
        items.add((int) Math.round(Math.random()*items.size()),gibberish);
        notifyDataSetChanged();
    }

    /**
     * removes a random list item from the adapter's data source, then notifies
     *   display adapters.
     */
    public void removeRandomListItem()
    {
        if(items.size() == 0) return;

        int index = (int) (Math.random()*items.size());
        items.remove(index);
        notifyDataSetChanged();
    }

    /**
     * randomly reorders the adapter's data source, then notifies display
     *   adapters.
     */
    public void shuffleListItems()
    {
        Collections.shuffle(items);
        notifyDataSetChanged();
    }

    // private interface

    private class StringViewHolderListener implements StringViewHolder.Listener
    {
        @Override
        public void onClick(final String s)
        {
            if(adapterListener != null) adapterListener.onListItemClick(s);
        }
    }
}
