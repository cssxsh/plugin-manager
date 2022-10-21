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
        manager.disablePlugin(plugin = target)
        try {
            (target as CoroutineScope).cancel("cancel by command")
        } catch (cause: Exception) {
            PluginManagerPlugin.logger.warning("${target.id} 终止异常", cause)
        }
        PluginManagerData.plugins.add(target.id)
        sendMessage("目标插件 ${target.id} 关闭完毕")
    }

    @SubCommand
    public suspend fun CommandSender.enable(id: String) {
        val target = manager.plugins.find { it.id == id || it.name == id }
        if (target == null) {
            sendMessage("未找到目标插件")
            return
        }
        manager.enablePlugin(plugin = target)

        sendMessage("目标插件 ${target.id} 开启完毕")
    }
}