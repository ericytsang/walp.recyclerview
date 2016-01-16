package gmail.surpluset.recyclerviewanimations;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * a view holder is used by a recycler view and its recycler view adapter to
 *   manage a view layout used to present an element of a specific type.
 *
 * @param <Model>
 */
public abstract class ViewHolder<Model> extends RecyclerView.ViewHolder
{
    private Model model;

    public ViewHolder(View itemView)
    {
        super(itemView);
    }

    /**
     * returns the application context.
     *
     * @return the application context.
     */
    public final Context getContext()
    {
        return itemView.getContext();
    }

    /**
     * sets the view holder's model, and updates the instance's view to
     * present the new model.
     *
     * @param model reference to the new model to present.
     */
    protected abstract void onSetModel(Model model);

    /**
     * sets the view holder's model, and updates the view holder's view to
     * present the new model.
     *
     * @param model reference to the new model to present.
     */
    public final void setModel(Model model)
    {
        this.model = model;
        onSetModel(model);
    }

    /**
     * returns the model that is currently being presented by the instance.
     *
     * @return the model that is currently being presented by the instance.
     */
    public final Model getModel()
    {
        return model;
    }

    /**
     * returns the view holder's view that it is backing.
     *
     * @return the view holder's view that it is backing.
     */
    public final View getView()
    {
        return itemView;
    }
}
