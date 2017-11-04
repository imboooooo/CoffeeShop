package id.co.androidkejar.project1;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.androidkejar.project1.library.Mail;
import id.co.androidkejar.project1.models.ItemModel;

public class ShopActivity extends AppCompatActivity {
    private Button mBtnMinus, mBtnPlus, mBtnReset, mBtnOrder;
    private EditText mFirstName;
    private Spinner mMenu;
    private RadioButton mFlavorChoocolate, mFlavorMocha;
    private RadioGroup mFlavorGroup;
    private TextView mQuantity, mTextPrice;
    private int vQuantity, vPrice = 0;
    private Mail mail;
    ArrayList<ItemModel> itemModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initEvent();

    }

    /**
     * this for init content view
     *
     * @author Imammagribi <imammagribi@gmail.com>
     */
    protected void initView() {
        Intent intent = getIntent();
        mBtnMinus = (Button) findViewById(R.id.mBtnMinus);
        mBtnPlus = (Button) findViewById(R.id.mBtnPlus);
        mBtnReset = (Button) findViewById(R.id.mBtnReset);
        mBtnOrder = (Button) findViewById(R.id.mBtnOrder);
        mFirstName = (EditText) findViewById(R.id.mFirstName);
        mFlavorChoocolate = (RadioButton) findViewById(R.id.mFlavorChoocolate);
        mFlavorMocha = (RadioButton) findViewById(R.id.mFlavorMocha);
        mFlavorGroup = (RadioGroup) findViewById(R.id.mFlavorGroup);
        mFlavorGroup.check(mFlavorChoocolate.getId());
        mQuantity = (TextView) findViewById(R.id.mQuantity);
        mTextPrice = (TextView) findViewById(R.id.mTextPrice);
        mMenu = (Spinner) findViewById(R.id.mMenu);
        setQuantity(0);
        List<String> categories = new ArrayList<String>();
        categories.add("Choice Flavor");
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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMenu.setAdapter(dataAdapter);
        for (ItemModel value : itemModel) {
            categories.add(value.getName());
        }
        int sFlavorPosition = intent.getIntExtra("sFlavorPosition",-1);
        if (sFlavorPosition != -1) {
            mMenu.setSelection(sFlavorPosition+1);
        }

        mail = new Mail();
    }

    public void initEvent() {
        mBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vQuantity += 1;
                setQuantity(vQuantity);

            }
        });

        mBtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((vQuantity <= 0) == false) {
                    vQuantity -= 1;
                    setQuantity(vQuantity);
                }

            }
        });
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vQuantity = 0;
                vPrice = 0;
                setQuantity(vQuantity);
                mFlavorGroup.check(mFlavorChoocolate.getId());
                mMenu.setSelection(0);
                mFirstName.setText("");

            }
        });
        mBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] to = {"imammagribi@gmail.com"};
                String text = String.format("Nama Saya %s Saya memesan %s sebanyak %s seharga %d%s", mFirstName.getText(), mMenu.getSelectedItem(), mQuantity.getText(), vPrice, "$");

                try {
                    startActivity(Intent.createChooser(mail.sendMail(to, text), "Send Send..."));
                    finish();
                } catch (Exception e) {
                    Toast.makeText(ShopActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });
        mMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(ShopActivity.this, item, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mFlavorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.mFlavorChoocolate:
                        Toast.makeText(ShopActivity.this, "Chocolate flavor", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mFlavorMocha:
                        Toast.makeText(ShopActivity.this, "Moccha flavor", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }

    /**
     * this for set quantity in textbox price
     *
     * @param vQuantity
     * @author Imammagribi <imammagribi@gmail.com>
     */
    protected void setQuantity(int vQuantity) {
        mQuantity.setText(String.valueOf(vQuantity));
        vPrice = 5 * vQuantity;
        mTextPrice.setText(String.format("Price : %d$", vPrice));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
