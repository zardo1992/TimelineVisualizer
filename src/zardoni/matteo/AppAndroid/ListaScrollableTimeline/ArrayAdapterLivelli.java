package zardoni.matteo.AppAndroid.ListaScrollableTimeline;

import java.util.List;
import zardoni.matteo.timeline.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/* 
 	Con questa classe viene definita genericamente la riga della lista
 	che verrà presentata all'utente nella MainActivity 
 */

@SuppressLint({ "ViewHolder", "InflateParams" })
public class ArrayAdapterLivelli extends ArrayAdapter<TwoStringFields>
{
	private boolean[] checkBoxes;
    public ArrayAdapterLivelli(Context context, int textViewResourceId,
          	List<TwoStringFields> list, boolean[] c) 
    {
        super(context, textViewResourceId, list);
        checkBoxes = c;
    }
    
	@Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
        LayoutInflater inflater = (LayoutInflater) getContext()
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.rigalista, null);
        CheckBox cb = (CheckBox)convertView.findViewById(R.id.titoloRiga);
        TextView descrizione = (TextView)convertView.findViewById(R.id.textViewList);
        TwoStringFields c = getItem(position);
        cb.setText(c.getTitle());
        descrizione.setText(c.getDescription());
        
        if(checkBoxes[position])
        	cb.setChecked(true);
        else
        	cb.setChecked(false);
        return convertView;
    }

}
