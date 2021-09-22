package com.jeffrey.debuggy.data.about

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.developer.developersList
import com.jeffrey.debuggy.data.sectioned.CardSectionedAdapter
import com.jeffrey.debuggy.data.slot.SlotAction
import com.jeffrey.debuggy.data.slot.descriptionAboutList
import com.jeffrey.debuggy.data.slot.informationAboutList
import com.jeffrey.debuggy.databinding.ItemAboutBinding
import com.jeffrey.debuggy.utils.Utils
import com.jeffrey.debuggy.utils.extensions.addInsetPaddings

class AboutAdapter(private val context: Context, fragment: Fragment) :
    RecyclerView.Adapter<AboutAdapter.ViewHolder>() {

    private val aboutAction: AboutAction = AboutAction(context, fragment)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_about, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.summary.text = Utils.getBuildTime(context)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = ConcatAdapter(
                CardSectionedAdapter(
                    descriptionAboutList(context),
                    context.getString(R.string.header_description),
                    context
                ),
                CardSectionedAdapter(
                    informationAboutList(context, SlotAction(context)),
                    context.getString(R.string.header_information),
                    context,
                ),
                DeveloperCardAdapter(
                    developersList(context),
                    context.getString(R.string.header_contributors),
                    context
                )
            )

            binding.cardSupport.setOnClickListener {
                aboutAction.callSupportCard()
            }
            binding.cardFeedback.setOnClickListener {
                aboutAction.callFeedbackCard()
            }
            binding.cardNews.setOnClickListener {
                aboutAction.callNewsCard()
            }
            binding.cardTranslate.setOnClickListener {
                aboutAction.callTranslateCard()
            }
            binding.recyclerView.addInsetPaddings(bottom = true)
        }
    }

    override fun getItemCount() = 1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAboutBinding.bind(view)
    }
}