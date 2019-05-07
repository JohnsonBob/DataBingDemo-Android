package com.johnson.databingtest.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.OrientationHelper
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
    private var studentList: ArrayList<StudentModle> = arrayListOf()
    private lateinit var gridLayoutManager:GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recycler_databinding)

        setListener()
        setStudentListAdapter()
        initData()
    }

    private fun initData() {
        for (i in 1..10) studentList.add(StudentModle("张三$i", "${10 + i}", if (i and 1 == 1) "男" else "女"))
    }

    private fun setStudentListAdapter() {
        studentListAdapter = object : RecyclerViewAdapter<StudentModle, ItemStudentListBinding>(this, studentList) {
            override fun getLayoutId(): Int {
                return R.layout.item_student_list
            }

            override fun convert(
                viewHolder: ViewHolder,
                viewDataBinding: ItemStudentListBinding,
                data: StudentModle,
                position: Int
            ) {
                viewDataBinding.shutdent = data
                viewDataBinding.executePendingBindings()
            }

        }
        gridLayoutManager = GridLayoutManager(this, 1, OrientationHelper.VERTICAL, false)
        gridLayoutManager.orientation = OrientationHelper.VERTICAL
        rv_shutdent_list.adapter = studentListAdapter
        rv_shutdent_list.layoutManager = gridLayoutManager
    }

    private fun setListener() {
        bt_add.setOnClickListener {
            var index = (1..100).shuffled().last()
            studentList.add(0, StudentModle("赵四$index", "${index}", if (index and 1 == 1) "男" else "女"))
            gridLayoutManager.scrollToPosition(0)       //移动到最前面
            studentListAdapter.notifyItemInserted(0)
        }

        bt_update.setOnClickListener {

        }

        bt_delete.setOnClickListener {
            if (studentList.size > 0) {
                studentList.removeAt(studentList.size - 1)
                studentListAdapter.notifyItemRemoved(studentList.size)
            }
        }

        bt_clear.setOnClickListener {
            studentList.clear()
            studentListAdapter.notifyDataSetChanged()
        }
    }

}
