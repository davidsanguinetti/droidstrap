package org.droidstrap.fragments;

import org.droidstrap.view.Activity_Commuter;
import org.droidstrap.view.MainActivity;
import org.droidstrap.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_MenuList extends ListFragment {

	MenuListAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new MenuListAdapter(getActivity());
		
		adapter.add(new MenuItem("Procurar", R.drawable.abs__ic_search, MainActivity.class));
/*		adapter.add(new MenuItem("Recolhas", R.drawable.icon_onde_dar, Activity_e20_ondedar.class));
		adapter.add(new MenuItem("Reservas", R.drawable.icon_reservas, Activity_e40_reservas.class));
		adapter.add(new MenuItem("Partilhar", R.drawable.icon_partilhar, Activity_e17.class));
		adapter.add(new MenuItem("Perfil", R.drawable.icon_perfil, Activity_e16_Medalhas.class));
		adapter.add(new MenuItem("Opções", R.drawable.icon_opcoes, Activity_notSettings.class));
		adapter.add(new MenuItem("Notificações", R.drawable.icon_notificacoes, Activity_NotList.class));*/
		
		setListAdapter(adapter);
	}

	private class MenuItem {
		public String tag;
		public int iconRes;
		public Class<?> newActivity;
		public MenuItem(String tag, int iconRes, Class<?> activity) {
			this.tag = tag; 
			this.iconRes = iconRes;
			this.newActivity = activity;
		}
	}

	public class MenuListAdapter extends ArrayAdapter<MenuItem> {

		public MenuListAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.mrow_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.mrow_text);
			title.setText(getItem(position).tag);

			return convertView;
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Class<?> clas = adapter.getItem(position).newActivity;
		
		Activity_Commuter ac = (Activity_Commuter) this.getActivity();
		
		Intent it = new Intent(getActivity(), clas);
		ac.changeActivity(it);
//		startActivity(it);
//		getActivity().finish();
	}
	
	
}