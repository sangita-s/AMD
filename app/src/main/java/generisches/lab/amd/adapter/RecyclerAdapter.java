package generisches.lab.amd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import generisches.lab.amd.R;
import generisches.lab.amd.model.Landscape;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private static final int NON_PRIME_ROW = 1;
    private static final int PRIME_ROW = 0;
    List<Landscape> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context context, List<Landscape> data){
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View lView = mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(lView);
        return holder;

//        switch (viewType){
//            case PRIME_ROW:
//                ViewGroup primeRow = (ViewGroup) mInflater.inflate(R.layout.list_item_prime, parent,false);
//                MyViewHolder_PRIME holderPrime = new MyViewHolder_PRIME(primeRow);
//                return holderPrime;
//            case NON_PRIME_ROW:
//                ViewGroup nonprimeRow = (ViewGroup)mInflater.inflate(R.layout.list_item_not_prime, parent,false);
//                MyViewHolder_PRIME holderNonPrime = new MyViewHolder_PRIME(nonprimeRow);
//                return holderNonPrime;
//            default:
//                ViewGroup defaultRow = (ViewGroup)mInflater.inflate(R.layout.list_item_not_prime, parent,false);
//                MyViewHolder_PRIME holderDefault = new MyViewHolder_PRIME(defaultRow);
//                return holderDefault;
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder" + position);

        Landscape crtObj = mData.get(position);
        holder.setData(crtObj, position);
        holder.setListeners();

//        switch (holder.getItemViewType()){
//            case PRIME_ROW:
//                MyViewHolder_PRIME holder_prime = (MyViewHolder_PRIME) holder;
//                holder_prime.setData(crtObj);
//                break;
//            case NON_PRIME_ROW:
//                MyViewHolder_NON_PRIME holder_not_prime = (MyViewHolder_NON_PRIME) holder;
//                holder_not_prime.setData(crtObj);
//                break;
//        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        Landscape landscape = mData.get(position);
        if(landscape.isPrime())
            return PRIME_ROW;
        else
            return NON_PRIME_ROW;
    }

    public void removeItem(int position){
        mData.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, mData.size());

        notifyDataSetChanged();
    }

    public void addItem(int position, Landscape landscape){
        mData.add(position, landscape);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mData.size());
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView imgThumb, imgDelete, imgAdd;
        int pos;
        Landscape current;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            imgThumb = itemView.findViewById(R.id.img_row);
            imgDelete = itemView.findViewById(R.id.img_row_delete);
            imgAdd = itemView.findViewById(R.id.img_row_add);
        }

        public void setData(Landscape crtObj, int position) {
            this.title.setText(crtObj.getTitle());
            this.imgThumb.setImageResource(crtObj.getImageID());
            this.pos = position;
            this.current = crtObj;
        }

        public void setListeners() {
            imgDelete.setOnClickListener(MyViewHolder.this);
            imgAdd.setOnClickListener(MyViewHolder.this);
        }
        @Override
        public void onClick(View v){
            Log.i(TAG, "onClick before Operation at " + pos + " Size: " + mData.size());
            switch (v.getId()){
                case R.id.img_row_delete:
                    removeItem(pos);
                    break;
                case R.id.img_row_add:
                    addItem(pos, current);
                    break;
            }
            Log.i(TAG, "onClick after Operation - Size " + mData.size() + "\n\n" + mData.toString());
        }
    }

//    public class MyViewHolder_PRIME extends MyViewHolder{
//        TextView title;
//        ImageView imgThumb, imgRowType;
//
//        public MyViewHolder_PRIME(View itemView){
//            super(itemView);
//
//            title = itemView.findViewById(R.id.tvTitle);
//            imgThumb = itemView.findViewById(R.id.img_row);
//            imgRowType = itemView.findViewById(R.id.img_row_prime);
//        }
//         public void setData(Landscape current){
//            this.title.setText(current.getTitle());
//            this.imgThumb.setImageResource(current.getImageID());
//            this.imgRowType.setImageResource(R.drawable.prime);
//         }
//    }
//    public class MyViewHolder_NON_PRIME extends MyViewHolder{
//        TextView title;
//        ImageView imgThumb, imgRowType;
//
//        public MyViewHolder_NON_PRIME(View itemView){
//            super(itemView);
//
//            title = itemView.findViewById(R.id.tvTitle);
//            imgThumb = itemView.findViewById(R.id.img_row);
//            imgRowType = itemView.findViewById(R.id.img_row_prime);
//        }
//        public void setData(Landscape current){
//            this.title.setText(current.getTitle());
//            this.imgThumb.setImageResource(current.getImageID());
//            this.imgRowType.setImageResource(R.drawable.not_prime);
//        }
//    }
}
