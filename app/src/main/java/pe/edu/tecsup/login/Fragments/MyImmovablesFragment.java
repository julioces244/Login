package pe.edu.tecsup.login.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pe.edu.tecsup.login.InmueblesListActivity;
import pe.edu.tecsup.login.MapsActivity;
import pe.edu.tecsup.login.R;


public class MyImmovablesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_myimmovables, container, false);


        CardView cardView1 = (CardView) view.findViewById(R.id.card1);
      //  CardView cardView2 = (CardView) view.findViewById(R.id.card2);
        //Button button1 = (Button) view.findViewById(R.id.button3);

        cardView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Crea el nuevo fragmento y la transacción.
                Intent editprofile = new Intent(MyImmovablesFragment.this.getActivity(), InmueblesListActivity.class);
                startActivity(editprofile);

            }
        });

       // Button button2 = (Button) view.findViewById(R.id.button4);



        return view;
    }
}
