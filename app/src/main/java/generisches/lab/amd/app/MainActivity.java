package generisches.lab.amd.app;

import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import generisches.lab.amd.R;
import generisches.lab.amd.adapter.RecyclerAdapter;
import generisches.lab.amd.model.Landscape;

public class    MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolBar();
        setupDrawer();
        setupRecyclerView();
    }

    private void setupToolBar(){
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("Home Page");
        mToolbar.setSubtitle("Folks!");

//        mToolbar.setLogo(R.drawable.ic_weekend_white_24dp);
//        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            mToolbar.setElevation(10f);
//        }else{ }

//        mToolbar.inflateMenu(R.menu.menu_main);
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()){
            case R.id.discard:
                msg = "Delete";
                break;

            case R.id.search:
                msg = "Search";
                break;

            case R.id.settings:
                msg = "Settings";
                break;

            case R.id.edit:
                msg = "Edit";
                break;

            case R.id.exit:
                msg = "Exit";
                break;
        }
        toast(msg);

        return super.onOptionsItemSelected(item);
    }
    private void setupDrawer(){
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.nav_draw_frag);
        DrawerLayout lDrawerLayout = findViewById(R.id.drawer_layout);
        drawerFragment.setupDrawer(R.id.nav_draw_frag, lDrawerLayout, mToolbar);
    }


    private void setupRecyclerView() {
        RecyclerView lRecyclerView = findViewById(R.id.recyclerView);
        RecyclerAdapter lAdapter = new RecyclerAdapter(this, Landscape.getData());
        lRecyclerView.setAdapter(lAdapter);

        LinearLayoutManager lLinearLayoutManagerVertical = new LinearLayoutManager(this);
        lLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(lLinearLayoutManagerVertical);

        lRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void toast(String msg) {
        Toast.makeText(this, msg + " Clicked", Toast.LENGTH_SHORT).show();
    }
}
