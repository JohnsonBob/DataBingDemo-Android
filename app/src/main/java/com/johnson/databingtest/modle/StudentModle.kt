package com.johnson.databingtest.modle

import android.databinding.ObservableField

/**
 * @ClassName StudentModle
 * @Description TODO
 * @Author WangShunYi
 * @Date 2019/5/7 11:53
 */
class StudentModle constructor(name: String, age: String, sex: String) {
    var name: ObservableField<String> = ObservableField(name)
    var age: ObservableField<String> = ObservableField(age)
    var sex: ObservableField<String> = ObservableField(sex)
}