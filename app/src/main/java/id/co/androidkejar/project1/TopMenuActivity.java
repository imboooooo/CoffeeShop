package id.co.androidkejar.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import id.co.androidkejar.project1.ShopActivity;
import id.co.androidkejar.project1.adapters.ItemAdapter;
import id.co.androidkejar.project1.models.ItemModel;

public class TopMenuActivity extends AppCompatActivity {
    private Intent i = null;
    private ListView listview;
    private ArrayList<ItemModel> itemModel;
    private static ItemAdapter itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView() {
        listview = (ListView) findViewById(R.id.mListView);
        itemModel=getItemsModel();
        itemsAdapter = new ItemAdapter(itemModel, getApplicationContext());
        listview.setAdapter(itemsAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView c = (TextView) view.findViewById(R.id.mTitle);
//                String title = c.getText().toString();
                i = new Intent(getApplicationContext(), ShopActivity.class);
                i.putExtra("sFlavorPosition",position);
                startActivityForResult(i, 0);
            }
        });
    }

    public ArrayList<ItemModel> getItemsModel(){
        itemModel = new ArrayList<>();
        itemModel.add(new ItemModel("Mochachino", getResources().getDrawable(R.drawable.ic_mochachino)));
        itemModel.add(new ItemModel("Frappuccino", getResources().getDrawable(R.drawable.ic_frappuccino)));
        itemModel.add(new ItemModel("Robusta", getResources().getDrawable(R.drawable.ic_robusta)));
        itemModel.add(new ItemModel("Kopi Lampung", getResources().getDrawable(R.drawable.ic_arabika)));
        itemModel.add(new ItemModel("Arabica", getResources().getDrawable(R.drawable.ic_arabika)));
        itemModel.add(new ItemModel("Kopi Luak", getResources().getDrawable(R.drawable.ic_kopi_luak)));
        itemModel.add(new ItemModel("Cortado", getResources().getDrawable(R.drawable.ic_cortado)));
        itemModel.add(new ItemModel("Flat White", getResources().getDrawable(R.drawable.ic_cortado)));
        itemModel.add(new ItemModel("Cafe Cubano", getResources().getDrawable(R.drawable.ic_cortado)));
        itemModel.add(new ItemModel("Expresso", getResources().getDrawable(R.drawable.ic_expresso)));
        return itemModel;
    }
}
