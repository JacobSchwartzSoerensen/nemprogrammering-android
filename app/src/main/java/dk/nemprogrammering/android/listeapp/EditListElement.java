package dk.nemprogrammering.android.listeapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditListElement extends AppCompatActivity {

    private boolean isNewElement = true;
    private int uid;
    private ListViewModel viewModel;
    private ListElementEntity element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_element);

        this.viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        Intent intent = getIntent();
        this.isNewElement = intent.getBooleanExtra(List.EXTRA_EDIT_ELEMENT_IS_NEW, true);
        if (!this.isNewElement)
        {
            this.uid = intent.getIntExtra(List.EXTRA_EDIT_ELEMENT_UID, -1);
            if (this.uid < 0)
            {
                finish();
            }
            else
            {
                fetchElementFromDatabase();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.edit_list_element_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.edit_list_element_menu_delete:
                this.deleteElement();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteElement()
    {
        if (!this.isNewElement)
        {
            this.viewModel.deleteListElements(this.element);
        }

        this.finish();
    }

    private void fetchElementFromDatabase()
    {
        this.viewModel.getListElementById(this.uid).observe(this, entity -> {
            this.element = entity;

            if (this.element == null)
            {
                finish();
                return;
            }

            EditText header = findViewById(R.id.edit_element_header);
            EditText desc = findViewById(R.id.edit_element_desc);

            header.setText(this.element.header);
            desc.setText(this.element.desc);

            this.viewModel.getListElementById(this.uid).removeObservers(this);
        });
    }

    public void onSave(View v)
    {
        EditText editHeader = findViewById(R.id.edit_element_header);
        EditText editDesc = findViewById(R.id.edit_element_desc);

        if (this.isNewElement)
        {
            ListElementEntity element = new ListElementEntity();

            element.header = editHeader.getText().toString();
            element.desc = editDesc.getText().toString();

            element.img = "";

            this.viewModel.insertListElements(element);
        }
        else
        {
            this.element.header = editHeader.getText().toString();
            this.element.desc = editDesc.getText().toString();

            this.viewModel.updateListElements(element);
        }
        finish();
    }

    public void onCancel(View v)
    {
        finish();
    }
}
