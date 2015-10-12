package gmail.surpluset.recyclerviewanimations;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<StringViewHolder>
{
    /**
     * long string to generate gibberish from for the list items.
     */
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    /**
     * reference to this adapter's listener. the listener's methods are invoked
     *   when events occur relevant to this adapter.
     */
    private Listener adapterListener;

    /**
     * reference to the adapter's underlying data source.
     */
    private final List<String> items;

    /**
     * listener to pass to viewholders managed by this adapter.
     */
    private final StringViewHolderListener stringViewHolderListener;

    // public interface: constructors

    public Adapter()
    {
        this.adapterListener = null;
        this.items = new ArrayList<>();
        this.stringViewHolderListener = new StringViewHolderListener();

        // important: the following line of code lets listening views know that
        // they can depend on the values provided by the getItemId method for
        // different list items, so they may implement animations of an item
        // moving from one position to another by looking at their id
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

    /**
     * should return a unique number for each element in the adapter's data
     *   source.
     *
     * @param position position of the element in the data source to get the ID
     *   of.
     *
     * @return id of the element at the specified position.
     */
    @Override
    public long getItemId(int position)
    {
        // IMPORTANT: the code below can be improved because 2 different strings
        // could have the same hashcode...although the recyclerview won't
        // exception if multiple elements return the same IDs.
        return items.get(position).hashCode();
    }

    /**
     * returns the number of elements in the adapter's underlying data source.
     *
     * @return the number of elements in the adapter's underlying data source.
     */
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
