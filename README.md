# Android JSON Viewer

[![Generic badge](https://img.shields.io/badge/jitpack-v0.0.0-darkyellow?logo=jitpack&logoColor=white.svg)](https://jitpack.io/#pokeum/jsonviewer-xml/)
[![Generic badge](https://img.shields.io/badge/Google_Play-jsonviewer-lightblue?logo=googlePlay&logoColor=white)](https://play.google.com/store/apps/details?id=kr.pokeum.jsonviewer.app)

---

<div align="center">

### JSON Viewer and Beautifier

A lightweight package that will read JSON data, expand and collapse JSON view accordingly.

</div>

---

**Easiest way to format Json String**

```xml
<kr.pokeum.jsonviewer_xml.JsonRecyclerView
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  app:text="{PUT_YOUR_JSON_STRING}" />
```

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
    - [Basic](#basic)
    - [Advance](#advance)


## <a id="installation"> Installation

Add it in your root `build.gradle` at the end of repositories:

```gradle
allprojects {
  repositories {
    // ...
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency

```gradle
implementation 'com.github.pokeum:jsonviewer-xml:0.0.0'
```

## <a id="usage"> Usage

- ## <a id="basic"> Basic

  ### JsonParser.parse()
  
  Convert `String` into a `JsonElement` object

  **Example - Parsing JSON**
  ```kotlin
  val jsonString = "{ \"abc\": \"def\",\"xyz\": 123 }"
            
  val jsonParser = JsonParser.Builder().build()
  val jsonElement: JsonElement? = try {
    jsonParser.parse(jsonString)
  }
  // Raise a JSONException if it is not a JSONObject or JSONArray.
  catch (e: JSONException) { null }
  ```

  ### Display JSON

  #### [Method 1] Use JsonRecyclerView

  Add `JsonRecyclerView` in XML layout file:
  ```xml
  <kr.pokeum.jsonviewer_xml.JsonRecyclerView
    android:id="@+id/jsonViewer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:text="{PUT_YOUR_JSON_STRING}" />
  ```

  Change `JsonRecyclerView` text from the code:
  ```kotlin
  findViewById<JsonRecyclerView>(R.id.jsonViewer).setText("{PUT_YOUR_JSON_STRING}")
  ```

  #### [Method 2] Use RecyclerView

  Add `RecyclerView` in XML layout file:
  ```xml
  <androidx.recyclerview.widget.RecyclerView
    android:id="@+id/jsonViewer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    tools:ignore="SpeakableTextPresentCheck"
    tools:listitem="@layout/item_json_object" />
  ```
  
  Set Adapter in `RecyclerView`:
  ```kotlin
  val recyclerView = findViewById<RecyclerView>(R.id.jsonViewer)
  recyclerView.adapter = JsonViewerAdapter(/* JsonElement */)
  ```

- ## <a id="advance"> Advance

  ### JsonParser - Sort JSON

  **Example - Alphabetically**
  ```kotlin
  JsonParser.Builder()
    .setComparator(compareBy { it.key })
    .build()
  ```

  ### JsonElement - Save and Restore

  Save and Restore Data on Configuration Changed in Android using Bundle
    
  ```kotlin
  class YourActivity : AppCompatActivity() {

    private var jsonElement: JsonElement? = null

    override fun onCreate(savedInstanceState: Bundle?) {
      // ...
  
      if (savedInstanceState != null) {
        jsonElement = savedInstanceState.getParcelable("JSON_ELEMENT_KEY")  /* Restore */
      }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("JSON_ELEMENT_KEY", jsonElement)  /* Save */
    }
  }
  ```
