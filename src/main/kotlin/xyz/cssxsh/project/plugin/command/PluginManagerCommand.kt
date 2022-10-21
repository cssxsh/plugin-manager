package xyz.cssxsh.project.plugin.command

import kotlinx.coroutines.*
import net.mamoe.mirai.console.*
import net.mamoe.mirai.console.command.*
import net.mamoe.mirai.console.plugin.*
import xyz.cssxsh.project.plugin.*
import xyz.cssxsh.project.plugin.data.*

public object PluginManagerCommand : CompositeCommand(
    owner = PluginManagerPlugin,
    "plugin",
    description = "调整插件"
) {

    private val manager = MiraiConsole.pluginManager

    @SubCommand
    public suspend fun CommandSender.disable(id: String) {
        val target = manager.plugins.find { it.id == id || it.name == id }
        if (target == null) {
            sendMessage("未找到目标插件")
            return
        }
        PluginManagerData.plugins.add(target.id)
        try {
            manager.disablePlugin(plugin = target)
            (target as CoroutineScope).cancel("cancel by plugin-manager")
        } catch (cause: Exception) {
            PluginManagerPlugin.logger.warning("${target.id} 终止异常", cause)
        }
        sendMessage("目标插件 ${target.id} 关闭完毕")
    }

    @SubCommand
    public suspend fun CommandSender.enable(id: String) {
        val target = manager.plugins.find { it.id == id || it.name == id }
        if (target == null) {
            sendMessage("未找到目标插件")
            return
        }
        PluginManagerData.plugins.remove(target.id)
        try {
            manager.enablePlugin(plugin = target)
        } catch (cause: Exception) {
            PluginManagerPlugin.logger.warning("${target.id} 启动异常", cause)
        }

        sendMessage("目标插件 ${target.id} 开启完毕")
    }
}