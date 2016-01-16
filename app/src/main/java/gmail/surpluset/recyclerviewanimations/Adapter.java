package gmail.surpluset.recyclerviewanimations;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<StringViewHolder>
{
    /**
     * long string to generate gibberish from for list items.
     */
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, c" +
            "onsectetur adipiscing elit, sed do eiusmod tempor incididunt ut" +
            " labore et dolore magna aliqua. Ut enim ad minim veniam, quis n" +
            "ostrud exercitation ullamco laboris nisi ut aliquip ex ea commo" +
            "do consequat. Duis aute irure dolor in reprehenderit in volupta" +
            "te velit esse cillum dolore eu fugiat nulla pariatur. Excepteur" +
            " sint occaecat cupidatat non proident, sunt in culpa qui offici" +
            "a deserunt mollit anim id est laborum.";

    /**
     * reference to this adapter's listener. the listener's methods are invoked
     *   when events occur relevant to this adapter.
     */
    private Listener adapterListener;

    /**
     * adapter's underlying data source to be displayed by the recyclerview.
     */
    private final List<String> items;

    /**
     * listener to pass to viewholders managed by this adapter.
     */
    private final StringViewHolderListener stringViewHolderListener;

    public Adapter(Listener adapterListener)
    {
        this.adapterListener = adapterListener;
        this.items = new ArrayList<>();
        this.stringViewHolderListener = new StringViewHolderListener();

        // important: enables animations
        setHasStableIds(true);
    }

    /**
     * called when the recycler view needs to create a new view holder of the
     *   given type to represent an item.
     *
     * @param parent the ViewGroup into which the new View will be added after
     *   it is bound to an adapter position.
     * @param viewType the view type of the new View.
     *
     * @return the view type of the new View
     */
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

    /**
     * called when a view holder is being bound (or re-bound) to an element from
     *   the adapter's underlying data source.
     *
     * @param holder reference to the view holder being bound to an element.
     * @param position position of the element in the adapter.
     */
    @Override
    public void onBindViewHolder(StringViewHolder holder,int position)
    {
        holder.setModel(items.get(position));
    }

    /**
     * should return a unique number for each element in the adapter's data
     *   source. number should be from or created from only the element (don't
     *   just return the position or the animations won't work correctly).
     *
     * this number is used by the recyclerview to determine if a particular
     *   element has changed positions in the list, so it can properly animate
     *   the change on the GUI.
     *
     * @param position position of the element in the data source to get the ID
     *   of.
     *
     * @return id of the element at the given position.
     */
    @Override
    public long getItemId(int position)
    {
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

    /**
     * defines callbacks for events that are potentially relevant to listeners.
     */
    public interface Listener
    {
        void onListItemClick(String s);
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

    private class StringViewHolderListener implements StringViewHolder.Listener
    {
        @Override
        public void onClick(final String s)
        {
            adapterListener.onListItemClick(s);
        }
    }
}
