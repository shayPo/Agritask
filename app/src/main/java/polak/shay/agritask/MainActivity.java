package polak.shay.agritask;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import polak.shay.agritask.model.DataItem;

public class MainActivity extends AppCompatActivity implements Observer {

    private ListAdapter mListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        RecyclerView list = findViewById(R.id.display_list);
        list.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mListAdapter = new ListAdapter();
        list.setAdapter(mListAdapter);
        addData();
    }

    private void addData()
    {
        ArrayList<DataItem> list = new ArrayList<>();
        for(int i = 0; i<20 ; i++)
        {
            list.add(new DataItem(R.drawable.image));
        }
        mListAdapter.addData(list);
    }

    @Override
    public void update(Observable o, Object arg) {
        addData();
    }
}
