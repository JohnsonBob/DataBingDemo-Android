package com.johnson.databingtest.modle

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField

/**
 * @ClassName Goods
 * @Description TODO
 * @Author WangShunYi
 * @Date 2019/5/5 10:24
 */
class Goods constructor(name: String, details: String) {
    var name: ObservableField<String> = ObservableField(name)
    var details: ObservableField<String> = ObservableField(details)

    /*init {
        this.name = ObservableField(name)
        this.details = ObservableField(details)
    }*/
}