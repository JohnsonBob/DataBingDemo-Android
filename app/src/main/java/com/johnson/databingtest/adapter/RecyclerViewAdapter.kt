package com.johnson.databingtest.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * 万能RecyclerViewAdapter
 * @ClassName RecyclerViewAdapter
 * @Description TODO
 * @Author WangShunYi
 * @Date 2019/5/7 10:23
 */
abstract class RecyclerViewAdapter<T, E : ViewDataBinding>(var context: Context, var dataList: List<T>) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private lateinit var viewDataBinding: E
    abstract fun getLayoutId(): Int
    abstract fun convert(viewHolder: ViewHolder, viewDataBinding: E, data: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): ViewHolder {
        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            getLayoutId(), parent, false)

        return ViewHolder.getViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        convert(viewHolder, viewDataBinding, dataList[position], position)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    class ViewHolder constructor(var viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        var mViews: SparseArray<View> = SparseArray()

        fun <T : View> getView(id: Int): T {
            var view = mViews.get(id)

            var getView = {
                var myview = viewHolder.findViewById<T>(id)
                mViews.put(id, myview)
                mViews.get(id)
            }
            return (view ?: getView) as T
        }

        companion object {
            fun <E : ViewDataBinding> getViewHolder(viewDataBinding: E): ViewHolder {
                return ViewHolder(viewDataBinding.root)
            }
        }

        fun setText(id: Int, value: String) {
            var view = getView<TextView>(id)
            view.text = value
        }
    }
}