package co.pokeum.app.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.pokeum.app.JsonGenerator
import co.pokeum.app.R
import co.pokeum.app.databinding.ActivityJsonViewerBinding
import co.pokeum.app.util.changeActionBarColor
import co.pokeum.jsonviewer.xml.adapter.JsonViewerAdapter
import co.pokeum.jsonviewer.xml.model.JsonElement

class JsonViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJsonViewerBinding

    private var jsonGenerator = JsonGenerator(this)
    private var jsonElement: JsonElement? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.title)
        binding = ActivityJsonViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeActionBarColor(resources.getColor(R.color.orange))

        jsonElement = jsonGenerator.getCacheData()
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
        private const val KEY_JSON_ELEMENT = "json_element"

        fun newIntent(context: Context): Intent {
            return Intent(context, JsonViewerActivity::class.java)
        }
    }
}