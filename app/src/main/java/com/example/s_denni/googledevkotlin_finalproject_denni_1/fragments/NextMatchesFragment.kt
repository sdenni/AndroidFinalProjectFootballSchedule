package com.example.s_denni.googledevkotlin_finalproject_denni_1.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*
import com.example.s_denni.googledevkotlin_finalproject_denni_1.interfaces.MyLagaView
import com.example.s_denni.googledevkotlin_finalproject_denni_1.R
import com.example.s_denni.googledevkotlin_finalproject_denni_1.activities.LagaDetailActivity
import com.example.s_denni.googledevkotlin_finalproject_denni_1.adapters.LagaEnjingAdapter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.models.MyLaga
import com.example.s_denni.googledevkotlin_finalproject_denni_1.networks.DataRepository
import com.example.s_denni.googledevkotlin_finalproject_denni_1.presenters.LagaEnjingPresenter
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.invisible
import com.example.s_denni.googledevkotlin_finalproject_denni_1.tools.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.util.*
import kotlin.collections.ArrayList

class NextMatchesFragment : Fragment(), AnkoComponent<Context>, MyLagaView {
    private var lagaEnjings: MutableList<MyLaga> = mutableListOf()
    private lateinit var presenter: LagaEnjingPresenter
    private lateinit var adapterNextMatch: LagaEnjingAdapter
    private lateinit var reusableView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private var leagueName: String = "German Bundesliga"
    private var leagueId: String = "4328"
    private lateinit var textView: TextView
//    private var spinnerId: MutableList<String> = mutableListOf()
//    List<String> myArrayList
//    private var spinnerId : List<String> = ArrayList<String>()
//    private var spinnerId = MutableList<String>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerId = resources.getIntArray(R.array.leagueIdArray)

//        Log.d("TRACE", "Size spineritem "+spinnerItems.size)
//        spinnerId = resources.getStringArray(R.array.leagueIdArray)
//        spinnerId = Arrays.asList(resources.getStringArray(R.array.leagueIdArray))
//        spinnerId = Arrays.as


        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
//        val something = ArrayA
        spinner.adapter = spinnerAdapter

        adapterNextMatch = LagaEnjingAdapter(lagaEnjings) {
            context?.startActivity<LagaDetailActivity>("id_schedule" to "${it.scheduleId}")
        }

        reusableView.adapter = adapterNextMatch
        val request = DataRepository()
        val gson = Gson()
        presenter = LagaEnjingPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
//                val spinnerId = Arrays.asList(resources.getStringArray(R.array.leagueIdArray))
//                val spinnerId = resources.getStringArray(R.array.leagueIdArray)

                Log.d("TRACE", "Size id "+spinnerId.size)
                Log.d("TRACE", "Size "+spinnerId.getOrNull(2))
                Log.d("TRACE", "Size "+spinnerId[spinner.selectedItemPosition])
////                leagueId = spinnerId.getOrNull(2)
                Log.d("TRACE", "NextMatchesFragment "+spinner.selectedItemPosition+" || "+leagueId+" || "+leagueName+" || "+spinnerId[position].toString())

//                args?.getString(resources.getString(R.string.leagueOnly)).toString(),
                presenter.nimmLagaEnjing(spinnerId[position].toString(), true)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//        presenter.nimmLagaEnjing()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.title = "Daftar Klub"
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu?.findItem(R.id.menuSearch)
        val searchView = item?.actionView as android.widget.SearchView

        searchView.setOnQueryTextListener(object  : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.nimmLagaEnjing(query, false)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuSearch -> return true

            else -> return super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = android.widget.LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = R.id.spinner
            }

            swipeRefresh = swipeRefreshLayout() {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    reusableView = recyclerView {
                        id = R.id.list_nextMatch
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = android.support.v7.widget.LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(dataEnjing: List<MyLaga>) {
        swipeRefresh.isRefreshing = false
        lagaEnjings.clear()
        lagaEnjings.addAll(dataEnjing)
        adapterNextMatch.notifyDataSetChanged()
    }
}