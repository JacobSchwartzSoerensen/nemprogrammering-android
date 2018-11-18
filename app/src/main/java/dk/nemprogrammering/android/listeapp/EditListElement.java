package dk.nemprogrammering.android.listeapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditListElement extends AppCompatActivity {

    private boolean isNewElement = true;
    private ListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_element);

        Intent intent = getIntent();
        this.isNewElement = intent.getBooleanExtra(List.EXTRA_EDIT_ELEMENT_IS_NEW, true);

        this.viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
    }

    public void onSave(View v)
    {
        if (this.isNewElement)
        {
            ListElementEntity element = new ListElementEntity();

            EditText editHeader = findViewById(R.id.edit_element_header);
            element.header = editHeader.getText().toString();

            EditText editDesc = findViewById(R.id.edit_element_desc);
            element.desc = editDesc.getText().toString();

            element.img = "";

            this.viewModel.insertListElements(element);
        }
        else
        {
            // TODO
        }
        finish();
    }

    public void onCancel(View v)
    {
        finish();
    }
}
