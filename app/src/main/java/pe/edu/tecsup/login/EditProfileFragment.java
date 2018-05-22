package pe.edu.tecsup.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;





public class EditProfileFragment extends Fragment {

    private EditText nombre, apellido, correo, telefono, genero;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View returnview= inflater.inflate(R.layout.fragment_edit_profile, container, false);




        return returnview;
    }
}
