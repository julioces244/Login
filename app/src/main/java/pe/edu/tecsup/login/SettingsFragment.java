package pe.edu.tecsup.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_settings, container, false);




        Button button1 = (Button) view.findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
                Intent editprofile = new Intent(SettingsFragment.this.getActivity(), InmueblesListActivity.class);
                startActivity(editprofile);

            }
        });

        Button button2 = (Button) view.findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
                Intent editprofile2 = new Intent(SettingsFragment.this.getActivity(), MapsActivity.class);
                startActivity(editprofile2);

            }
        });


        return view;
    }
}
