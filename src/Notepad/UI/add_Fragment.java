package Notepad.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_Fragment extends Fragment{


	private EditText mEditText;
	private Button mButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_add, container, false);
		mEditText = (EditText) view.findViewById(R.id.txtTitle_t);
		mButton = (Button) view.findViewById(R.id.btnSave_t);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Äãµã»÷ÁËÎÒ", 1000).show();
			}
		});
		return view;
	}
	
}
