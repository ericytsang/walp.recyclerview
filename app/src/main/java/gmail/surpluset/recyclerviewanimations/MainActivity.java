package gmail.surpluset.recyclerviewanimations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

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

        // initialize view references
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        this.adapter = new Adapter();

        // configure recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
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
}
