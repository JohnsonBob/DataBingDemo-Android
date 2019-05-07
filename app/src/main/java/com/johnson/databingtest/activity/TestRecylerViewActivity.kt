package com.johnson.databingtest.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.johnson.databingtest.R
import com.johnson.databingtest.adapter.RecyclerViewAdapter
import com.johnson.databingtest.databinding.ItemStudentListBinding
import com.johnson.databingtest.modle.StudentModle
import kotlinx.android.synthetic.main.activity_test_recycler_databinding.*

/**
 * @ClassName TestRecylerViewActivity
 * @Description TODO
 * @Author WangShunYi
 * @Date 2019/5/7 9:49
 */
class TestRecylerViewActivity : AppCompatActivity() {
    private lateinit var studentListAdapter: RecyclerViewAdapter<StudentModle, ItemStudentListBinding>
    private var shutdentList: ArrayList<StudentModle> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_test_recycler_databinding)

        setListener()
        setStudentListAdapter()
    }

    private fun setStudentListAdapter() {
        studentListAdapter = object : RecyclerViewAdapter<StudentModle, ItemStudentListBinding>(this, shutdentList) {
            override fun getLayoutId(viewId: Int): Int {
                return R.layout.item_student_list
            }

            override fun convert(viewHolder: ViewHolder, data: StudentModle, position: Int) {
                viewDataBinding
            }

        }
        rv_shutdent_list
    }

    private fun setListener() {
        bt_add.setOnClickListener {

        }

        bt_update.setOnClickListener {

        }

        bt_delete.setOnClickListener {

        }

        bt_clear.setOnClickListener {

        }
    }

}
