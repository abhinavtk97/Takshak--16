package in.ac.mace.in;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhinav on 3/24/2016.
 */
public class DataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public DataAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Data object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHolder dataHolder;
        if (row ==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.activity_listview,parent,false);
            dataHolder =new DataHolder();
            dataHolder.tx_name=(TextView)row.findViewById(R.id.t_name);
            dataHolder.tx_mail=(TextView)row.findViewById(R.id.t_mail);

            dataHolder.tx_college=(TextView)row.findViewById(R.id.t_college);
            row.setTag(dataHolder);
        }
        else {
            dataHolder=(DataHolder)row.getTag();
        }
        Data data =(Data)getItem(position);
        dataHolder.tx_name.setText(data.getName().toString());
        dataHolder.tx_mail.setText(data.getMail().toString());

        dataHolder.tx_college.setText(data.getCollege().toString());

        return row;
    }
    static class DataHolder{
        TextView tx_name,tx_mail,tx_phone,tx_college;
    }
}
