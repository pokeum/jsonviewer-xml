package kr.pokeum.app.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.pokeum.app.JsonGenerator
import kr.pokeum.app.R
import kr.pokeum.app.databinding.ActivityJsonViewerBinding
import kr.pokeum.app.util.changeActionBarColor
import kr.pokeum.jsonviewer_xml.adapter.JsonViewerAdapter
import kr.pokeum.jsonviewer_xml.model.JsonElement

class JsonViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJsonViewerBinding

    private var jsonGenerator = JsonGenerator(this)
    private var jsonElement: JsonElement? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = TITLE
        binding = ActivityJsonViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeActionBarColor(resources.getColor(R.color.orange))

        jsonElement = jsonGenerator.generateFromCacheFile(CACHE_JSON_FILE)
        if (savedInstanceState != null) {
            jsonElement = savedInstanceState.getParcelable(KEY_JSON_ELEMENT)
        }
        initRecyclerView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_JSON_ELEMENT, jsonElement)
    }

    private fun initRecyclerView() {

        jsonElement?.let {
            val adapter = JsonViewerAdapter(it)
            binding.jsonRecyclerView.adapter = adapter
        }
    }

    companion object {

        private const val TITLE = "Json Viewer"
        private const val CACHE_JSON_FILE = "cache.json"

        private const val KEY_JSON_ELEMENT = "json_element"

        fun newIntent(context: Context): Intent {
            return Intent(context, JsonViewerActivity::class.java)
        }
    }
}