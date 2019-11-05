package polak.shay.agritask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import polak.shay.agritask.model.DataItem;

public class ListAdapter extends Adapter<ListAdapter.Holder> {
    private List<DataItem> mData;
    private int mUpdateIndex = 18;
    private Observable mDataIndexInfo = new Observable();


    public void addObservers(Observer observer) {
        mDataIndexInfo.addObserver(observer);
    }

    public void addData(List<DataItem> data) {
        if (data != null) {
            if (mData == null) {
                mData = new ArrayList<>(data);
            } else {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (position == mUpdateIndex) {
            mUpdateIndex+=20;
            mDataIndexInfo.hasChanged();
            mDataIndexInfo.notifyObservers();
        }
        holder.setup(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (mData != null) {
            size = mData.size();
        }
        return size;
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView mText;
        private ImageView mImage;
        private String mMaxText = "000000";

        Holder(View view) {
            super(view);
            init(view);
        }

        void init(View view) {
            mImage = view.findViewById(R.id.image);
            mText = view.findViewById(R.id.text);
        }

        void setup(DataItem data, int index) {
            mImage.setImageResource(data.getImage());
            StringBuilder text = new StringBuilder();
            int maxSize = 6;
            maxSize = maxSize - text.length();
            text.append(mMaxText.subSequence(0, maxSize));
            text.append(index);
            mText.setText(text.toString());

            int numTest = index / 2;
            numTest = numTest * 2;
            if (index == numTest) {
                mText.setBackgroundResource(R.color.highlight);
            }
        }
    }
}
