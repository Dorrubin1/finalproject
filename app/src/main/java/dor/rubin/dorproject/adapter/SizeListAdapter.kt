package dor.rubin.dorproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import dor.rubin.dorproject.R
import dor.rubin.dorproject.models.Size


class SizeListAdapter(context: Context, val list: List<Size>) :
    BaseAdapter() {
    inner class ViewHolder {
        var sizeLabel: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        var holder: ViewHolder? = null
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.spinner_item, parent, false)
            holder = ViewHolder()
            holder.sizeLabel = view.findViewById(R.id.text_size)
            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.sizeLabel?.text = list[position].size
        return view!!

    }

    override fun getItem(position: Int): Size {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

   // size of list - Size
    override fun getCount(): Int {
        return list.size
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getDropDownView(position, convertView, parent)
    }
    
}