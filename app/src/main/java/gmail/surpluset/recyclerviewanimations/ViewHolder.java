package gmail.surpluset.recyclerviewanimations;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ViewHolder<Model> extends RecyclerView.ViewHolder
{
    // instance data

    private Model model;

    // constructor

    public ViewHolder(View itemView)
    {
        super(itemView);
    }

    // public interface

    public abstract void onSetModel(Model model);

    public final void setModel(Model model)
    {
        this.model = model;
        onSetModel(model);
    }

    public final Model getModel()
    {
        return model;
    }
}
