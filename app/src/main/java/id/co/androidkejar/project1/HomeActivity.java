package id.co.androidkejar.project1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClickOrderCoffee(View view) {
        Intent i = new Intent(this, ShopActivity.class);
        startActivityForResult(i, 0);
    }
    
    public void onClickTopMenu(View view) {
        Intent i = new Intent(this, TopMenuActivity.class);
        startActivityForResult(i, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i = null;
        switch (item.getItemId()) {
            case R.id.menuOrder:
                i = new Intent(this, ShopActivity.class);
                startActivityForResult(i, 0);
                break;
            case R.id.menuAbout:
                i = new Intent(this, AboutActivity.class);
                startActivityForResult(i, 0);
                break;
            case R.id.menuTopMenu:
                i = new Intent(this, TopMenuActivity.class);
                startActivityForResult(i, 0);
                break;
            case R.id.menuQuit:
                quitMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void quitMenu() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage(R.string.msg_box_quit);
        alertBuilder.setPositiveButton(R.string.msg_box_quit_positif_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertBuilder.setNegativeButton(R.string.msg_box_quit_negative_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        quitMenu();
//        super.onBackPressed();
    }
}
