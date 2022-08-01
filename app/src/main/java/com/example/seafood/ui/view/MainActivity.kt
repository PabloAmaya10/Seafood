package com.example.seafood.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seafood.R
import com.example.seafood.databinding.ActivityMainBinding
import com.example.seafood.domain.model.FoodInfo
import com.example.seafood.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var foodListAdapter: FoodAdapter
    val viewModel: MainActivityViewModel by viewModels()
    private var listFood: List<FoodInfo>? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val btnBill = binding.rowCollectionCreditBtnPaid
        setContentView(binding.root)
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        val rvFood = binding.recyclerFood
        foodListAdapter = FoodAdapter(emptyList())
        rvFood.adapter = foodListAdapter

        viewModel.foodInfo.observe(this) {
            foodListAdapter.updateDataset(it)
        }
        viewModel.foodAccount.observe(this) {
            ShowAccountDialog.newInstance(it.toInt()).show(supportFragmentManager,ShowAccountDialog.TAG)
        }
        viewModel.error.observe(this) {
            Toast.makeText(
                applicationContext, R.string.error_act_main_list,
                Toast.LENGTH_SHORT
            ).show()
        }
        btnBill.setOnClickListener {
            viewModel.getAccount(foodListAdapter.getDataBillList())
        }
        viewModel.loadFoodInfo()
        rvFood.addItemDecoration(decoration)
    }

    private fun showAccount(account: String) {
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(this)
        val string = getString(R.string.warning_act_main_account) + "$ " + account
        builder.setMessage(string).setTitle(R.string.app_account)
            .setPositiveButton(R.string.app_accept) { _, _ ->
                viewModel.getFoodInfo()
            }
            .setNegativeButton(R.string.app_cancel, null)
        builder.create().show()
    }
}