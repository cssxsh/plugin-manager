package xyz.cssxsh.product.plugin.data

import net.mamoe.mirai.console.data.*

public object PluginManagerData : AutoSavePluginData("manager") {
    @ValueName("disable_plugins")
    public val plugins: MutableSet<String> by value()
}