package co.pokeum.jsonviewer.xml.model

interface Expandable {

    var expanded: Boolean

    fun toggleExpanded() {
        expanded = !expanded
    }

    fun expandAll()
    fun collapseAll()
}