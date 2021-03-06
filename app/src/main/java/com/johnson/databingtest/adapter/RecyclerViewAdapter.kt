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
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder<E>>() {
    abstract fun getLayoutId(): Int
    abstract fun convert(viewHolder: ViewHolder<E>, data: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): ViewHolder<E> {
        return ViewHolder.getViewHolder(parent, getLayoutId(), context)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder<E>, position: Int) {
        convert(viewHolder, dataList[position], position)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    class ViewHolder<E : ViewDataBinding> constructor(var dataBinding: E) : RecyclerView.ViewHolder(dataBinding.root) {
        private var mViews: SparseArray<View> = SparseArray()

        private fun <T : View> getView(id: Int): T {
            var view = mViews.get(id)

            var getView = {
                var myview = dataBinding.root.findViewById<T>(id)
                mViews.put(id, myview)
                mViews.get(id)
            }
            return (view ?: getView) as T
        }

        companion object {

            fun <E : ViewDataBinding> getViewHolder(parent: ViewGroup, layoutId: Int, context: Context): ViewHolder<E> {
                var viewDataBinding = DataBindingUtil.inflate<E>(
                    LayoutInflater.from(context),
                    layoutId, parent, false
                )
                return ViewHolder(viewDataBinding)
            }
        }

        fun setText(id: Int, value: String) {
            var view = getView<TextView>(id)
            view.text = value
        }
    }
}