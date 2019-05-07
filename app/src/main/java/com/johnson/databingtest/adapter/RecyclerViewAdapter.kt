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

    abstract fun getLayoutId(viewId: Int): Int
    abstract fun convert(viewHolder: ViewHolder, data: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): ViewHolder {
        return ViewHolder.viewHolder<E>(parent, getLayoutId(layoutId), context)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        convert(viewHolder, dataList[position], position)
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
            private lateinit var viewDataBinding: ViewDataBinding
            fun <E : ViewDataBinding> viewHolder(parent: ViewGroup, layoutId: Int, context: Context): ViewHolder {
                viewDataBinding = DataBindingUtil.inflate<E>(LayoutInflater.from(context),
                        layoutId, parent, false
                )
                return ViewHolder(viewDataBinding.root)
            }
        }

        fun setText(id: Int, value: String) {
            var view = getView<TextView>(id)
            view.text = value
        }
    }
}