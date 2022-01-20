
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.subham.Basisswipeapp.DataItem
import com.subham.Basisswipeapp.R
import kotlinx.android.synthetic.main.new_layout.view.*

class CardAdapter(private val list: ArrayList<DataItem>): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_layout,parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.setData(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }




    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(dataItem: DataItem){
            itemView.apply {
//               TvPageNum.text = dataItem.id
                TvId.text = dataItem.id
                TvText.text = dataItem.text
            }

        }
    }

}