# Android JSON Viewer

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/pokeumcho)

[![Generic badge](https://img.shields.io/badge/jitpack-v0.0.2-darkyellow?logo=jitpack&logoColor=white.svg)](https://jitpack.io/#pokeum/jsonviewer-xml/)

---

<div align="center">

### JSON Viewer and Beautifier

A lightweight package that will read JSON data, expand and collapse JSON view accordingly.

</div>

---

<div align="center">
  
https://github.com/pokeum/jsonviewer-xml/assets/102505472/e2f260f0-cc28-4607-9ec4-d9744ec9330c

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
- [Custom Styles](#styles)


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
implementation 'com.github.pokeum:jsonviewer-xml:0.0.2'
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

## <a id="styles"> Custom Styles

### Color

|  | <img src="./image/screenshot/styles-color.png"  width="160"> |
| -- | -- |
| Key | `"friends"`, `"0"`, `"name"`, `"age"` |
| Value | `"Alice"`, `28` |
| Splitter | `:` |
| Type | `ABC`, `123` |
| Arrow | `∨` |
| Bracket | `[ ]`, `{ }` |
| Divider | `│` |

#### Use JsonRecyclerView

  ```xml
  <kr.pokeum.jsonviewer_xml.JsonRecyclerView
    ...
    app:keyColor="@color/key_color"
    app:valueColor="@color/value_color"
    app:splitterColor="@color/splitter_color"
    app:typeColor="@color/type_color"
    app:arrowColor="@color/arrow_color"
    app:bracketColor="@color/bracket_color"
    app:dividerColor="@color/divider_color" />
  ```

#### Use RecyclerView

  ```kotlin
  recyclerView.adapter = JsonViewerAdapter(/* JsonElement */).apply {
    setKeyColor(JsonViewerColor(/* color[, dark mode color] */))
    setValueColor(JsonViewerColor(/* ... */))
    setSplitterColor(JsonViewerColor(/* ... */))
    setTypeColor(JsonViewerColor(/* ... */))
    setArrowColor(JsonViewerColor(/* ... */))
    setBracketColor(JsonViewerColor(/* ... */))
    setDividerColor(JsonViewerColor(/* ... */))
  }
  ```