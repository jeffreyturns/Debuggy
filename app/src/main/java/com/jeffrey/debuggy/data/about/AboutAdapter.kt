package com.jeffrey.debuggy.data.about

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeffrey.debuggy.BuildConfig
import com.jeffrey.debuggy.R
import com.jeffrey.debuggy.data.developer.developersList
import com.jeffrey.debuggy.data.sectioned.CardSectionedAdapter
import com.jeffrey.debuggy.data.slot.SlotAction
import com.jeffrey.debuggy.data.slot.descriptionAboutList
import com.jeffrey.debuggy.data.slot.informationAboutList
import com.jeffrey.debuggy.databinding.ItemAboutBinding
import com.jeffrey.debuggy.ui.base.BaseViewHolder
import com.jeffrey.debuggy.util.Utils
import com.jeffrey.debuggy.util.extensions.addInsetPaddings

class AboutAdapter(private val context: Context, fragment: Fragment) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private val aboutAction: AboutAction = AboutAction(context, fragment)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.create(parent, ItemAboutBinding::inflate)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        with(holder.binding as ItemAboutBinding) {
            summary.text = BuildConfig.VERSION_NAME
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = ConcatAdapter(
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

            cardSupport.setOnClickListener {
                aboutAction.callSupportCard()
            }
            cardFeedback.setOnClickListener {
                aboutAction.callFeedbackCard()
            }
            cardNews.setOnClickListener {
                aboutAction.callNewsCard()
            }
            cardTranslate.setOnClickListener {
                aboutAction.callTranslateCard()
            }
            recyclerView.addInsetPaddings(bottom = true)
        }
    }

    override fun getItemCount() = 1
}