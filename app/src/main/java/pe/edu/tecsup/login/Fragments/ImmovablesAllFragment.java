package pe.edu.tecsup.login.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.special.ResideMenu.ResideMenu;

import pe.edu.tecsup.login.ImmovablesAllListActivity;
import pe.edu.tecsup.login.InmueblesListActivity;
import pe.edu.tecsup.login.LoginActivity;
import pe.edu.tecsup.login.MapsActivity2;
import pe.edu.tecsup.login.MapsAllActivity;
import pe.edu.tecsup.login.R;


public class ImmovablesAllFragment extends Fragment {
    private View parentView;
    private ResideMenu resideMenu;
    private CardView cardViewall;
    private CardView cardViewmaps;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_immovables_all, container, false);

        cardViewall = parentView.findViewById(R.id.allimmovables);
        cardViewmaps = parentView.findViewById(R.id.allimmovablesmaps);

        cardViewall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
                Intent immovables = new Intent(ImmovablesAllFragment.this.getActivity(), ImmovablesAllListActivity.class);
                startActivity(immovables);

            }
        });

        cardViewmaps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
                Intent immovables = new Intent(ImmovablesAllFragment.this.getActivity(), MapsActivity2.class);
                startActivity(immovables);

            }
        });

        //setUpViews();
        return parentView;
    }


}
