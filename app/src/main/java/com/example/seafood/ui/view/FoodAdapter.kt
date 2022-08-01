package com.example.seafood.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seafood.R
import com.example.seafood.databinding.ItemFoodBinding
import com.example.seafood.domain.model.FoodInfo

class FoodAdapter(private var foodList: List<FoodInfo>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    
    private var dataBillList: MutableMap<String, List<Int>> = mutableMapOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FoodViewHolder(layoutInflater.inflate(R.layout.item_food, parent, false))

    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = foodList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = foodList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataset(list: List<FoodInfo>) {
        foodList = list
        dataBillList = mutableMapOf()
        this.notifyDataSetChanged()
    }

    inner class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemFoodBinding.bind(view)
        fun render(foodInfoModel: FoodInfo) {
            binding.rowTvFood.text = foodInfoModel.foodName
            binding.rowTvPrice.text = foodInfoModel.foodCost.toString()
            binding.rowEtAmount.setText(foodInfoModel.amount.toString())

            binding.rowEtAmount.setOnFocusChangeListener { view, b ->
                if (!b) {
                    foodInfoModel.amount = binding.rowEtAmount.text.toString().toInt()
                    dataBillList[foodInfoModel.foodName] =
                        listOf(foodInfoModel.foodCost,binding.rowEtAmount.text.toString().toInt())

                }
            }
        }
    }

    fun getDataBillList(): MutableMap<String,List<Int>> = dataBillList

}