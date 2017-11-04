package id.co.androidkejar.project1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.androidkejar.project1.models.ItemModel;
import id.co.androidkejar.project1.R;

/**
 * Created by imammagribi on 5/7/17.
 */

public class ItemAdapter extends ArrayAdapter<ItemModel> implements View.OnClickListener {

    private ArrayList<ItemModel> dataSet;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView imageView;
        TextView mTitle;
    }


    public ItemAdapter(ArrayList<ItemModel> data, Context context) {
        super(context, R.layout.item_coffee, data);
        this.dataSet = data;
        this.mContext = context;

    }


    @Override
    public void onClick(View v) {
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ItemModel itemModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_coffee, parent, false);
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.mTitle);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.mImage);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.mTitle.setText(itemModel.getName());
        viewHolder.imageView.setImageDrawable(itemModel.getImage());
        return result;
    }


}
