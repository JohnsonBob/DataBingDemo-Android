package com.johnson.databingtest.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.johnson.databingtest.R
import com.johnson.databingtest.databinding.ActivityMain2Binding
import com.johnson.databingtest.modle.Goods
import com.johnson.databingtest.modle.User
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity : AppCompatActivity() {
    private lateinit var user: User
    private lateinit var good: Goods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        //测试单项数据绑定
        /*user = User("张三", "1234632")
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            userInfo = user
        }*/

        //测试双向绑定
        good = Goods("name", "描述信息")
        var activityMain2Binding = DataBindingUtil.setContentView<ActivityMain2Binding>(this,
            R.layout.activity_main2
        ).apply {
            goods = good
        }

        tv_name.setOnClickListener({
            activityMain2Binding.goods = Goods("nadsafme", "sdafddsafdsaf")
        })

        bt_recyclerView.setOnClickListener({})
    }

    companion object{
        @JvmStatic
        fun nameTextchang(goods: Goods) {
        }
    }

}
