package gmail.surpluset.recyclerviewanimations;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
    // change to false to see a different layout manager in action! :)
    public static final boolean DEMO_LIST_LAYOUT_MANAGER = true;

    /**
     * recycler view displays view holders in a particular way.
     */
    private RecyclerView recyclerView;

    /**
     * adapter for the recycler view that adapts an arbitrary data source into
     *   a form that the recycler view can use, so the data may be displayed.
     */
    private Adapter adapter;

    // public interface: AppCompatActivity

    /**
     * invoked by android OS when the activity is created.
     *
     * initializes the activity's instance data, and configures them...
     *
     * @param savedInstanceState the activity's saved instance state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize instance data
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        this.adapter = new Adapter();

        // configure recycler view
        recyclerView.setAdapter(adapter);
        if(DEMO_LIST_LAYOUT_MANAGER)
        {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else
        {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }

        // configure adapter
        adapter.setListener(new AdapterListener());
    }

    /**
     * populates the application toolbar menu with menu items.
     *
     * @param menu the options menu that is being populated with menu items.
     *
     * @return true to create a toolbar; false otherwise.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    /**
     * invoked by android OS when menu item in application toolbar menu is
     *   clicked.
     *
     * this method determines which menu item was clicked, and passes flow of
     *   control to the appropriate code.
     *
     * @param item the menu item what was clicked.
     *
     * @return true if the click event was handled; false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
        case R.id.action_add:
            adapter.addRandomListItem();
            return true;
        case R.id.action_remove:
            adapter.removeRandomListItem();
            return true;
        case R.id.action_shuffle:
            adapter.shuffleListItems();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    // private interface: support methods

    /**
     * implementation of listener for recycler view's adapter
     */
    private class AdapterListener implements Adapter.Listener
    {
        /**
         * invoked by adapter when list item in the recycler view is clicked;
         *   displays an alert dialog.
         *
         * @param s string of the clicked list item.
         */
        @Override
        public void onListItemClick(String s)
        {
            // show a dialog displaying the clicked list item
            new AlertDialog.Builder(MainActivity.this).setMessage(s).create().show();
        }
    }
}
