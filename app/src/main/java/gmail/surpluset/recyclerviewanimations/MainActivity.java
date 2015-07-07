package gmail.surpluset.recyclerviewanimations;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import gmail.surpluset.recyclerviewanimations.util.DividerItemDecoration;
import gmail.surpluset.recyclerviewanimations.util.RecyclerViewUtils;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private Adapter adapter;

    // AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize instance data
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        this.adapter = new Adapter();

        // configure recycler view
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        // configure adapter
        adapter.setListener(new StringViewHolderListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

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

    // private interface

    private class StringViewHolderListener implements StringViewHolder.Listener
    {
        @Override
        public void onStringSelected(final String s)
        {
            // disable all touches to the recycler view
            RecyclerViewUtils.disableInputs(recyclerView);

            // delay the action associated with the click, because we want to
            // wait for the click animation to complete first
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    new AlertDialog.Builder(MainActivity.this).setMessage(s).create().show();
                    RecyclerViewUtils.enableInputs(recyclerView);
                }
            },100);
        }
    }
}
