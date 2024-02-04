package kr.pokeum.app.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.pokeum.app.JsonGenerator
import kr.pokeum.app.R
import kr.pokeum.app.api.performNetworkRequest
import kr.pokeum.app.databinding.ActivityMainBinding
import kr.pokeum.app.databinding.DialogLoadFromUrlBinding
import kr.pokeum.app.util.changeActionBarColor
import kr.pokeum.app.util.writeCacheFile
import kr.pokeum.jsonviewer_xml.model.JsonElement
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var jsonGenerator = JsonGenerator(this)

    private val openJsonFileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                intent?.data?.let { uri ->
                    loadJsonElement { jsonGenerator.generateFromUri(uri) }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = TITLE
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeActionBarColor(resources.getColor(R.color.orange))
        initButton()
    }

    private fun initButton() {
        /** Load JSON from Text */
        binding.btnLoadFromText.setOnClickListener {
            loadJsonElement {
                jsonGenerator.generateFromString(binding.editJSONText.text.toString())
            }
        }
        /** Load JSON from File */
        binding.btnLoadFromFile.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/json"
            }
            openJsonFileLauncher.launch(intent)
        }
        /** Load JSON from URL */
        binding.btnLoadFromURL.setOnClickListener { showLoadJsonFromUrlDialog() }
    }

    private fun showLoadJsonFromUrlDialog() {
        val dialogBinding = DialogLoadFromUrlBinding.inflate(layoutInflater)
        val alertDialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.dialog_load_from_url_title))
            .setView(dialogBinding.root)
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, _ ->
                val url = dialogBinding.editText.text.toString().trim()
                performNetworkRequest(url) { response ->
                    loadJsonElement { jsonGenerator.generateFromString(response) }
                }
                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .create()
        alertDialog.show()
    }

    private fun loadJsonElement(loadFunc: () -> JsonElement?) {
        try {
            // write to cache file
            loadFunc.invoke()?.let {
                writeCacheFile(it.toString().toByteArray(), CACHE_JSON_FILE)
            }
            // start json viewer activity
            val intent = JsonViewerActivity.newIntent(this)
            startActivity(intent)
        } catch (_: JSONException) {
            Toast.makeText(
                this,
                "Input is not a JSONObject or JSONArray",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {

        private const val TITLE = "Json Viewer"
        private const val CACHE_JSON_FILE = "cache.json"
    }
}